package com.mysoft.datainterpreterapp.controllers;

import com.mysoft.datainterpreterapp.models.DAO;
import com.mysoft.datainterpreterapp.models.Logs;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class StatisticsController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    @GetMapping("/last-hour")
    public String lastHourLogs(Model model) throws SQLException, ClassNotFoundException {
        List<Logs> logs = DAO.getLogssForLastHour();
        model.addAttribute("logs", logs);

        return "last-hour";
    }

    @GetMapping("/active-users")
    public String activeUsersLogs(Model model) throws SQLException, ClassNotFoundException {
        List<Logs> logs = DAO.getLogsForActiveUsers();
        model.addAttribute("logs", logs);

        return "active-users";
    }

    @GetMapping("/top-five-forms")
    public String topFiveFormsLogs(Model model) throws SQLException, ClassNotFoundException {
        List<Logs> logs = DAO.getLogsForTopFiveForms();
        model.addAttribute("logs", logs);

        return "top-five-forms";
    }
}
