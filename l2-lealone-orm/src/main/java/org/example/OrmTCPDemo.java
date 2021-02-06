package org.example;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class OrmTCPDemo {
    private static String StrPath = "./src/main/resources/tables.sql";

    public static void main(String[] args) throws Exception {
        System.out.println(new File(".").getCanonicalPath());
        createTable();
    }

    private static void createTable() throws SQLException {
        String jdbcUrl = "jdbc:lealone:tcp://127.0.0.1:9210/lealone";
        System.setProperty("lealone.jdbc.url", jdbcUrl);


        try (
                Connection conn = DriverManager.getConnection(jdbcUrl,"root","");

                Statement stmt = conn.createStatement()) {

                String path = new File(StrPath).getCanonicalPath();
                System.out.println(path);
                stmt.executeUpdate("RUNSCRIPT FROM '"+StrPath+"'");

//            ResultSet rs = stmt.executeQuery("SELECT * FROM test");
//            while (rs.next()) {
//                System.out.println("f1=" + rs.getInt(1) + " f2=" + rs.getLong(2));
//                System.out.println();
//            }
//            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
