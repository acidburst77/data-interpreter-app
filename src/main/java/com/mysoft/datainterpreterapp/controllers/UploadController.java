package com.mysoft.datainterpreterapp.controllers;

import com.mysoft.datainterpreterapp.models.DAO;
import com.mysoft.datainterpreterapp.models.Logs;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.sun.org.apache.xml.internal.security.keys.content.MgmtData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class UploadController {
    @GetMapping("/")
    public java.lang.String index() {
        return "index";
    }

    @PostMapping("/upload-csv-file")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) {
        // валидируем файл
        if(file.isEmpty()) {
            model.addAttribute("message", "Файл не был добавлен. Загрузите файл.");
            model.addAttribute("status", false);
        } else {
            // разбираем полученный файл
            try(Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                CsvToBean<Logs> csvToBean = new CsvToBeanBuilder<Logs>(reader)
                        .withType(Logs.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .withSeparator(';')
                        .build();

                List<Logs> logs = csvToBean.parse();

                DAO.deleteLogsTable();
                DAO.createLogsTable();

                for(Logs line: logs) {
                    Date date = new SimpleDateFormat("yyyy-MM-dd-HH").parse(line.getYmdh());
                    DAO.updateLogsTable("'" + line.getSsoid() + "', " +
                            "" + line.getTs() + ", " +
                            "'" + line.getGrp() + "', " +
                            "'" + line.getType() + "', " +
                            "'" + line.getSubType() + "', " +
                            "'" + line.getUrl() + "', " +
                            "'" + line.getOrgId() + "', " +
                            "'" + line.getFormId() + "', " +
                            "'" + line.getCode() + "', " +
                            "'" + line.getLtpa() + "', " +
                            "'" + line.getSudirresponse() + "', " +
                            "'" + date + "'");
                }

                model.addAttribute("logs", logs);
                model.addAttribute("status", true);
            } catch (IOException e) {
                model.addAttribute("message", "Произошла ошибка при обработке файла");
                model.addAttribute("status", false);
            } catch (SQLException e) {
                model.addAttribute("message", "Произошла ошибка при работе с базой данных");
                model.addAttribute("status", false);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return "index";
    }
}
