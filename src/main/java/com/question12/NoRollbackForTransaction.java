package com.question12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

public class NoRollbackForTransaction {
    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
        NoRollback noRollback = ctx.getBean(NoRollback.class);
        noRollback.insert();
    }
}

@Repository
class NoRollback{

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Transactional(noRollbackFor = ArithmeticException.class)
    public void insert() {
        String sql = "INSERT INTO PEOPLE(id,name)VALUES(3,'Vineet')";
        jdbcTemplate.update(sql);
        int i=1/0;
        System.out.println(i);

    }
}
