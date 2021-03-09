package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class RunSqlScript {
    public static void main(String[] args) throws Exception {
        String jdbcUrl = "jdbc:lealone:tcp://localhost/lealone?user=root&password=";
        runSql(jdbcUrl, "create database if not exists test");

        // 执行建表脚本，同时自动生成对应的模型类的代码
        runScript("./src/main/resources/tables.sql");

        // 执行服务创建脚本，同时自动生成对应的服务接口代码
        //runScript("./src/main/resources/services.sql");
    }

    static void runScript(String scriptFile) throws Exception {
        String jdbcUrl = "jdbc:lealone:tcp://localhost/test?user=root&password=";
        runSql(jdbcUrl, "RUNSCRIPT FROM '" + scriptFile + "'");
    }

    static void runSql(String url, String sql) throws Exception {
        try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("execute sql: " + sql);
        }
    }
}
