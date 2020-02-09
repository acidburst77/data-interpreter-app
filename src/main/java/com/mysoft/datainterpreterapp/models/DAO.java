package com.mysoft.datainterpreterapp.models;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.List;

public class DAO {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "postgres", "root12345");
    }

    public static void deleteLogsTable() throws SQLException, ClassNotFoundException {
        String sqlLine = "drop table if exists logs";

        try (Connection c = getConnection();
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlLine)) {
            System.out.println("Таблица с логами успешно создана!");
        } catch (SQLException e) {
            System.out.println("При удалении таблицы логовр произошла ошибка соединения с базой данных!");
            //e.printStackTrace();
        }
    }

    public static void createLogsTable() throws SQLException, ClassNotFoundException {
        String sqlLine = "create table if not exists logs (ssoid varchar (255), ts integer, grp varchar (100), type varchar (50)," +
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

    public static void updateLogsTable(String line) throws SQLException, ClassNotFoundException {
        String sqlLine = "insert into logs values (" + line + ")";

        try (Connection c = getConnection();
             Statement statement = c.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlLine);) {
            System.out.println("Таблица с логами успешно заполнена!");
        } catch (SQLException e) {
            System.out.println("При заполнении таблицы с логами произошла ошибка соединения с базой данных!");
            e.printStackTrace();
        }
    }
}
