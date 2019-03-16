package com.question11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class PropagationSupports {

    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
        UserDaoSupports1 userDaoSupports1 = ctx.getBean(UserDaoSupports1.class);
        userDaoSupports1.insert();
    }
}

@Repository
class UserDaoSupports1 {

    private final
    JdbcTemplate jdbcTemplate;
    private final UserDaoSupports2 userDaoSupports2;

    @Autowired
    public UserDaoSupports1(JdbcTemplate jdbcTemplate, UserDaoSupports2 userDaoNotSupported2) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDaoSupports2 = userDaoNotSupported2;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    void insert() {

        jdbcTemplate.update("INSERT INTO PEOPLE(id,name) VALUES (1,'Sanjeev')");
        try {
            userDaoSupports2.insert();
        } catch (Exception e) {
            System.out.println("exception");
        }

    }

}

@Repository
class UserDaoSupports2 {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoSupports2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    void insert() {

        jdbcTemplate.update("INSERT INTO PEOPLE(id,name) VALUES (2,'Aman')");

    }

}

