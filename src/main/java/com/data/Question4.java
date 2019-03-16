package com.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

public class Question4 {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
        UserDao userDao = ctx.getBean(UserDao.class);
        userDao.userCount();
    }
}

@Repository
class UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    void userCount() {
        String sql = "SELECT COUNT(*) FROM USER";
        System.out.println("Number of users:  " + jdbcTemplate.queryForObject(sql, Integer.class));
    }
}
