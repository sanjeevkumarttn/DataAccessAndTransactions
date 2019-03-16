package com.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Question9 {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
        FetchUser fetchUser = ctx.getBean(FetchUser.class);
        System.out.println(fetchUser.getUser("Rehan"));
    }
}


class UserMapper implements RowMapper<USER> {

    @Override
    public USER mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new USER(rs.getString("username"), rs.getString("password"),
                rs.getString("name"), rs.getDate("dob").toString(), rs.getInt("age"));
    }
}

@Repository
class FetchUser {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FetchUser(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    USER getUser(String name) {
        String sql = "SELECT * FROM USER WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, new UserMapper());
    }
}

