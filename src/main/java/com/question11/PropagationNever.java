package com.question11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class PropagationNever {

    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
        UserDaoNever1 userDaoNever1 = ctx.getBean(UserDaoNever1.class);
        userDaoNever1.insert();
    }
}

@Repository
class UserDaoNever1 {

    private final
    JdbcTemplate jdbcTemplate;

    private final
    UserDaoNever2 userDaoNever2;

    @Autowired
    public UserDaoNever1(JdbcTemplate jdbcTemplate, UserDaoNever2 userDaoNever2) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDaoNever2 = userDaoNever2;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insert() {
        jdbcTemplate.update("INSERT INTO PEOPLE(id,name) VALUES (1,'Sanjeev')");
        try {
            userDaoNever2.insert();
        } catch (Exception e) {
            System.out.println("exception");
        }
    }
}

@Repository
class UserDaoNever2 {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoNever2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void insert() {
        jdbcTemplate.update("INSERT INTO PEOPLE(id,name) VALUES (2,'Aman')");
        throw new RuntimeException();
    }

}