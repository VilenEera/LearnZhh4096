package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class OrmDemo {
    public static void main(String[] args) throws SQLException {
        createTable();
    }

    private static void createTable() throws SQLException {
        String jdbcUrl = "jdbc:lealone:embed:test";
        System.setProperty("lealone.jdbc.url", jdbcUrl);


        try (
                Connection conn = DriverManager.getConnection(jdbcUrl);
                Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("RUNSCRIPT FROM './src/main/resources/tables.sql'");
        }
    }
}
