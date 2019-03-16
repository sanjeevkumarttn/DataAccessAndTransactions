package com.data;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.Id;

public class Question6 {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
        RecordInsert recordInsert = ctx.getBean(RecordInsert.class);
        recordInsert.insertUser(new USER("rehanttn", "rehan14", "Rehan", "1994-09-14", 24));

    }
}

@Entity
class USER {
    @Id
    private String userName;
    private String password;
    private String name;
    private String dob;
    private int age;


    public USER(String userName, String password, String name, String dob, int age) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.dob = dob;
        this.age = age;
    }

    String getUserName() {
        return userName;
    }

    String getPassword() {
        return password;
    }

    String getName() {
        return name;
    }

    String getDob() {
        return dob;
    }

    int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "USER{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", dob='" + dob + '\'' +
                ", age=" + age +
                '}';
    }
}

@Repository
class RecordInsert {
    private final
    JdbcTemplate jdbcTemplate;

    @Autowired
    public RecordInsert(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    void insertUser(USER user) {
        System.out.println("Record inserting....");
        String sql = "INSERT INTO USER (username,password,name,age,dob)VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{
                user.getUserName(), user.getPassword(), user.getName(), user.getAge(), user.getDob()
        });
        System.out.println("Record inserted....");
    }
}