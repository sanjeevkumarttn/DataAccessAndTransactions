package com.question12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class ReadOnlyTransaction {

    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
        ReadOnly readOnly = ctx.getBean(ReadOnly.class);
        readOnly.insert();
    }
}

@Repository
class ReadOnly {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public void insert() {
        String sql = "INSERT INTO PEOPLE(id,name)VALUES(3,'Vineet')";
        jdbcTemplate.update(sql);
        //throw new RuntimeException();
    }
}

