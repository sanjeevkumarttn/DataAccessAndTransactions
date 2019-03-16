package com.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

public class Question5 {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
        GetUser getUser = ctx.getBean("getUser", GetUser.class);
        System.out.println("USER Name: "+getUser.getUserName("rohanttn"));
    }
}

@Repository
class GetUser {

    private final
    JdbcTemplate jdbcTemplate;

    @Autowired
    public GetUser(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    Object getUserName(String name) {
        String sql = "SELECT name FROM USER WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, String.class);
    }
}


