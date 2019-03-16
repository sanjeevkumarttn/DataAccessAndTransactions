package com.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Question2 {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/SpringDemo";

    private static final String USER = "root";
    private static final String PASS = "K96sanjeev@";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection conn = null;

        Class.forName(JDBC_DRIVER);
        System.out.println("Connecting to a selected database...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        System.out.println("Connected database successfully...");
        System.out.println("Inserting data...");

        String query = "INSERT INTO USER (username, password, name, dob, age) values " +
                "('rohanttn', 'rohan12', 'Rohan', '1990-02-12' ,28), " +
                "('abhinavttn', 'abhinav26', 'Abhinav', '1993-08-26', 25)";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.execute();
        System.out.println("Inserted data...");
        conn.close();

    }
}
