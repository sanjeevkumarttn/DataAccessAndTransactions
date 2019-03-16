package com.data;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Question3 {

    public static void main(String[] args) throws SQLException {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
        DriverManagerDataSourceConnection driverManagerDataSourceConnection = ctx.getBean(DriverManagerDataSourceConnection.class);
        driverManagerDataSourceConnection.displayRecord();
        BasicDataSourceConnection basicDataSourceConnection = ctx.getBean(BasicDataSourceConnection.class);
        basicDataSourceConnection.displayRecord();
        SingleConnectionDataSourceConnection singleConnectionDataSourceConnection = ctx.getBean(SingleConnectionDataSourceConnection.class);
        singleConnectionDataSourceConnection.displayRecord();
    }
}

@Repository
class DriverManagerDataSourceConnection {

    private final DriverManagerDataSource driverManagerDataSource;

    @Autowired
    public DriverManagerDataSourceConnection(DriverManagerDataSource driverManagerDataSource) {
        this.driverManagerDataSource = driverManagerDataSource;
    }

    void displayRecord() throws SQLException {
        Connection connection = driverManagerDataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM USER");
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("Reading data using DriverManagerDataSource...");
        System.out.println("username\tpassword\tname\tdob\t\tage\n--------------------------------------------------------");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("username") + "\t" + resultSet.getString("password") + "\t\t" +
                    resultSet.getString("name") + "\t" + resultSet.getString("dob") + "\t" + resultSet.getString("age"));
        }
        preparedStatement.close();
        connection.close();
    }
}

@Repository
class BasicDataSourceConnection {

    private final BasicDataSource basicDataSource;

    @Autowired
    public BasicDataSourceConnection(BasicDataSource basicDataSource) {
        this.basicDataSource = basicDataSource;
    }

    void displayRecord() throws SQLException {
        Connection connection = basicDataSource.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM USER");
        ResultSet resultSet = preparedStatement.executeQuery();

        System.out.println("\nReading data using BasicDataSource...");
        System.out.println("username\tpassword\tname\tdob\t\tage\n--------------------------------------------------------");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("username") + "\t" + resultSet.getString("password") + "\t\t" +
                    resultSet.getString("name") + "\t" + resultSet.getString("dob") + "\t" + resultSet.getString("age"));
        }
        preparedStatement.close();
        connection.close();
    }
}

@Repository
class SingleConnectionDataSourceConnection {

    private final SingleConnectionDataSource singleConnectionDataSource;

    @Autowired
    public SingleConnectionDataSourceConnection(SingleConnectionDataSource singleConnectionDataSource) {
        this.singleConnectionDataSource = singleConnectionDataSource;
    }

    void displayRecord() throws SQLException {
        Connection connection = singleConnectionDataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM USER");
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("\nReading data using SingleConnectionDataSource...");
        System.out.println("username\tpassword\tname\tdob\t\tage\n--------------------------------------------------------");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("username") + "\t" + resultSet.getString("password") + "\t\t" +
                    resultSet.getString("name") + "\t" + resultSet.getString("dob") + "\t" + resultSet.getString("age"));
        }
        preparedStatement.close();
        connection.close();
    }
}


