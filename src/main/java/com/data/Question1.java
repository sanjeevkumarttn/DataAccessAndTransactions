package com.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Question1 {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/SpringDemo";

    private static final String USER = "root";
    private static final String PASS = "K96sanjeev@";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection conn;

        Class.forName(JDBC_DRIVER);
        System.out.println("Connecting to a selected database...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        System.out.println("Connected database successfully...");
        System.out.println("Creating table in given database...");

        String sql = "CREATE TABLE USER " +
                "( id INT NOT NULL AUTO_INCREMENT, " +
                " username VARCHAR(25) UNIQUE , " +
                " password VARCHAR(25), " +
                "name VARCHAR(25), " +
                "dob DATE , " +
                " age INTEGER(5), " +
                " PRIMARY KEY ( id ))";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.execute();
        System.out.println("Created table in given database...");

    }
}