package com.question12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class TimeoutTransaction {

    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
        Timeout1 timeout1 = ctx.getBean(Timeout1.class);
        timeout1.insert();
    }
}

@Repository
class Timeout1{

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Transactional(propagation = Propagation.REQUIRED,timeout = 2)
    public void insert() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
        String sql = "INSERT INTO PEOPLE(id,name)VALUES(3,'Vineet')";
        jdbcTemplate.update(sql);

    }

}

