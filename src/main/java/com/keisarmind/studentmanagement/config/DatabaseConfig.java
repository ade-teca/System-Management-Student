package com.keisarmind.studentmanagement.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String URL = "jdbc:mysql://localhost:3306/sys_management_student";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    private static Connection connection = null;

    public DatabaseConfig() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();  // Logar a exceção apropriadamente em produção
                throw new RuntimeException("Erro ao conectar ao banco de dados", e);
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();  // Logar a exceção apropriadamente em produção
            } finally {
                connection = null;
            }
        }
    }
}

