package com.question11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class PropagationNotSupported {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
        UserDaoNotSupported1 userDaoNotSupported1 = ctx.getBean(UserDaoNotSupported1.class);
        userDaoNotSupported1.insert();
    }
}

@Repository
class UserDaoNotSupported1 {

    private final
    JdbcTemplate jdbcTemplate;
    private final UserDaoNotSupported2 userDaoNotSupported2;

    @Autowired
    public UserDaoNotSupported1(JdbcTemplate jdbcTemplate, UserDaoNotSupported2 userDaoNotSupported2) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDaoNotSupported2 = userDaoNotSupported2;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    void insert() {

        jdbcTemplate.update("INSERT INTO PEOPLE(id,name) VALUES (1,'Sanjeev')");
        try {
            userDaoNotSupported2.insert();
        } catch (Exception e) {
            System.out.println("exception");
        }

    }

}

@Repository
class UserDaoNotSupported2 {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoNotSupported2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void insert() {

        jdbcTemplate.update("INSERT INTO PEOPLE(id,name) VALUES (2,'Aman')");

    }

}
