package com.mysoft.datainterpreterapp.models;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    /**
     * Создание соединения с PostgreSQL
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "postgres", "root12345");
    }

    /**
     * Удаление таблицы с логами
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void deleteLogsTable() throws SQLException, ClassNotFoundException {
        String sqlLine = "DROP TABLE IF EXISTS logs";

        try (Connection c = getConnection();
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlLine)) {
            System.out.println("Таблица с логами успешно создана!");
        } catch (SQLException e) {
            System.out.println("При удалении таблицы логовр произошла ошибка соединения с базой данных!");
            //e.printStackTrace();
        }
    }

    /**
     * Создание таблицы с логами
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void createLogsTable() throws SQLException, ClassNotFoundException {
        String sqlLine = "CREATE TABLE IF NOT EXISTS logs (ssoid varchar (255), ts integer, grp varchar (100), type varchar (50)," +
                "subtype varchar (50), url varchar (50), orgId varchar (120), formId varchar (150), " +
                "code varchar (100), ltpa varchar (255), sudirresponse varchar(50), ymdh date)";

        try (Connection c = getConnection();
             Statement statement = c.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlLine);) {
            System.out.println("Таблица с логами успешно создана!");
        } catch (SQLException e) {
            System.out.println("При создание таблицы с логами произошла ошибка соединения с базой данных!");
            //e.printStackTrace();
        }
    }

    /**
     * Заполнение таблицы с логами из csv-файла
     * @param line
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void updateLogsTable(String line) throws SQLException, ClassNotFoundException {
        String sqlLine = "INSERT INTO logs VALUES (" + line + ")";

        try (Connection c = getConnection();
             Statement statement = c.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlLine);) {
            System.out.println("Таблица с логами успешно заполнена!");
        } catch (SQLException e) {
            System.out.println("При заполнении таблицы с логами произошла ошибка соединения с базой данных!");
            e.printStackTrace();
        }
    }

    /**
     * Логи за последний час
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static List<Logs> getLogssForLastHour() throws SQLException, ClassNotFoundException {
        ArrayList<Logs> logs = new ArrayList<Logs>();
        String sqlLine = "SELECT ssoid, formId, to_timestamp(ts) " +
                "FROM public.logs where to_timestamp(ts) >= (now() - interval '1 hour' )" +
                "order by to_timestamp(ts) asc";

        try (Connection c = getConnection();
             Statement statement = c.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlLine)) {
            while (resultSet.next()) {
                String ssoid = resultSet.getString(1);
                String formId = resultSet.getString(2);
                Long ts = resultSet.getLong(3);
                logs.add(new Logs(ssoid, formId, ts));
            }
        } catch (SQLException e) {
            System.out.println("При взятии логов за последний час было прервано соединение с базой данных.");
            e.printStackTrace();
        }

        return logs;
    }

    /**
     * Логи по топ-5 используемым формам
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static List<Logs> getLogsForTopFiveForms() throws SQLException, ClassNotFoundException {
        ArrayList<Logs> logs = new ArrayList<Logs>();
        String sqlLine = "SELECT formId, count(*) as countVisits FROM logs WHERE formId != '' GROUP BY formId ORDER BY countVisits DESC LIMIT 5";

        try (Connection c = getConnection();
             Statement statement = c.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlLine)) {
            while (resultSet.next()) {
                System.out.println(resultSet.getString(2));

                String formId = resultSet.getString(1);
                Integer countVisits = resultSet.getInt(2);

                logs.add(new Logs(formId, countVisits));
            }
        } catch (SQLException e) {
            System.out.println("В момент взятия логов по топ-5 формам было прервано соединение с базой данных.");
            e.printStackTrace();
        }

        return logs;
    }

    /**
     * Логи по активности пользователей (на каком щаге остановились)
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static List<Logs> getLogsForActiveUsers() throws SQLException, ClassNotFoundException {
        ArrayList<Logs> logs = new ArrayList<Logs>();
        String sqlLine = "select l1.ssoid, l1.grp,  l1.subtype, max(to_timestamp(l1.ts)) as maxTs\n" +
                "from logs as l1\n" +
                "where l1.grp like '%dszn_%' \n" +
                "and not exists (select subtype from logs where ssoid = l1.ssoid and subtype = 'send')\n" +
                "group by l1.ssoid, l1.subtype, l1.grp";

        try (Connection c = getConnection();
             Statement statement = c.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlLine)) {
            while (resultSet.next()) {
                String ssoid = resultSet.getString(1);
                String grp = resultSet.getString(2);
                String subType = resultSet.getString(3);
                String maxTs = resultSet.getString(4);
                logs.add(new Logs(ssoid, grp, subType, maxTs));
            }
        } catch (SQLException e) {
            System.out.println("При взятии логов было прервано соединение с базой данных.");
            e.printStackTrace();
        }

        return logs;
    }
}
