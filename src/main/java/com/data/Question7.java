package com.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

public class Question7 {

    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
        FetchQueryForMap fetchQueryForMap = ctx.getBean(FetchQueryForMap.class);
        fetchQueryForMap.display("Rehan");
    }
}

@Repository
class FetchQueryForMap{

    @Autowired
    JdbcTemplate jdbcTemplate;

    void display(String name) {
        String sql = "SELECT * FROM USER WHERE name = ?";
        System.out.println(jdbcTemplate.queryForMap(sql, new Object[]{name}));
    }
}
