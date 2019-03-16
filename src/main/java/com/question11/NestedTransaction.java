package com.question11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class NestedTransaction {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
        UserDaoNested1 userDaoNested1 = ctx.getBean(UserDaoNested1.class);
        userDaoNested1.insert();

    }

}

@Repository
class UserDaoNested1 {

    private final
    JdbcTemplate jdbcTemplate;

    private final
    UserDaoNested2 userDaoNested2;

    @Autowired
    public UserDaoNested1(JdbcTemplate jdbcTemplate, UserDaoNested2 userDaoRequiresNew2) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDaoNested2 = userDaoRequiresNew2;
    }

    @Transactional
    public void insert() {
        jdbcTemplate.update("INSERT INTO PEOPLE(id,name) VALUES (1,'Sanjeev')");
        try {
            userDaoNested2.insert();
        } catch (Exception e) {
            System.out.println("exception");
        }
    }
}

@Repository
class UserDaoNested2 {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoNested2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(propagation = Propagation.NESTED)
    public void insert() {
        jdbcTemplate.update("INSERT INTO PEOPLE(id,name) VALUES (2,'Aman')");
        throw new RuntimeException();
    }

}

