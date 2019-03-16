package com.question11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class RequiredTransaction {

    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
        UserDao1 userDao1 = ctx.getBean(UserDao1.class);
        userDao1.insert();

    }
}

@Repository
class UserDao1{
    private final
    JdbcTemplate jdbcTemplate;

    private final
    UserDao2 userDao2;

    @Autowired
    public UserDao1(JdbcTemplate jdbcTemplate, UserDao2 userDao2) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao2 = userDao2;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insert(){
        jdbcTemplate.update("INSERT INTO PEOPLE(id,name) VALUES (1,'Sanjeev')");
        try {
            userDao2.insert();
        }catch (Exception e){
            System.out.println("exception by userdao2");
        }

    }

}

@Repository
class UserDao2{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insert(){
        jdbcTemplate.update("INSERT INTO PEOPLE(id,name) VALUES (2,'Aman')");
        //throw new RuntimeException();

    }
}
