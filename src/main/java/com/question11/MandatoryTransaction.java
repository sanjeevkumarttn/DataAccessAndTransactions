package com.question11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class MandatoryTransaction {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
        UserDaoMandatory1 userDaoMandatory1 = ctx.getBean(UserDaoMandatory1.class);
        userDaoMandatory1.insert();
    }

}

@Repository
class UserDaoMandatory1 {

    private final
    JdbcTemplate jdbcTemplate;

    private final
    UserDaoMandatory2 userDaoMandatory2;

    @Autowired
    public UserDaoMandatory1(JdbcTemplate jdbcTemplate, UserDaoMandatory2 userDaoMandatory2) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDaoMandatory2 = userDaoMandatory2;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insert() {
        jdbcTemplate.update("INSERT INTO PEOPLE(id,name) VALUES (1,'Sanjeev')");
        try {
            userDaoMandatory2.insert();
        } catch (Exception e) {
            System.out.println("exception");
        }
    }
}

@Repository
class UserDaoMandatory2 {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoMandatory2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void insert() {
        jdbcTemplate.update("INSERT INTO PEOPLE(id,name) VALUES (2,'Aman')");
    }

}
