package br.com.fiap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private static final String USER = "rm554889";
    private static final String PASSWORD = "100805";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}