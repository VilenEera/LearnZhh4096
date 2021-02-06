package org.example;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class OrmClientDemo {
    public static void main(String[] args) throws Exception {
        String tableDir = "src/main/resources";
        String str = new File(tableDir).getAbsolutePath();
        System.out.println(str);

      //  new OrmClientDemo().run(args);
    }




    //private String tableDir = getClass().getResource("/").toString().substring(6);
    private String serviceDir = "../petstore-service/src/main/resources";

    private void run(String[] args) throws Exception {
        parseArgs(args);

        // 创建PetStore数据库
        String jdbcUrl = "jdbc:lealone:tcp://localhost/lealone?user=root&password=";
        // runSql(jdbcUrl, "drop database if exists petstore");
        runSql(jdbcUrl, "create database if not exists `order`");
        runSql(jdbcUrl, "create database if not exists `customer`");
        // 执行建表脚本，同时自动生成对应的模型类的代码
       // runScript(tableDir + "tables.sql");
        // 初始化数据
        //runScript(tableDir + "/init-data.sql");

        // 执行服务创建脚本，同时自动生成对应的服务接口代码
        //runScript(serviceDir + "/services.sql");
    }

    private void parseArgs(String[] args) {
        for (int i = 0; i < args.length; i++) {
            args[i] = args[i].trim();
        }

        for (int i = 0; i < args.length; i++) {
            String a = args[i];
            switch (a) {
                case "-tableDir":
            //        tableDir = args[++i];
                    break;
                case "-serviceDir":
                    serviceDir = args[++i];
                    break;
                default:
                    System.out.println("选项名 '" + a + "' 无效");
                    System.exit(-1);
            }
        }
    }

    static void runScript(String scriptFile) throws Exception {
        String jdbcUrl = "jdbc:lealone:tcp://localhost/lealone?user=root&password=";
        runSql(jdbcUrl, "RUNSCRIPT FROM '" + scriptFile + "'");
    }

    static void runSql(String url, String sql) throws Exception {
        try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("execute sql: " + sql);
        }
    }
}
