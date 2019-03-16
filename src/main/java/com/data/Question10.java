package com.data;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

public class Question10 {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
        UserDAO userDAO = ctx.getBean(UserDAO.class);
        userDAO.sessionFactoryDemo();
    }
}

@Repository
class UserDAO {
    private final
    SessionFactory sessionFactoryBean;

    @Autowired
    public UserDAO(SessionFactory sessionFactoryBean) {
        this.sessionFactoryBean = sessionFactoryBean;
    }

    void sessionFactoryDemo() {
        String sql = "SELECT COUNT(*) FROM USER";
        org.hibernate.Query query;
        query = sessionFactoryBean.openSession().createQuery(sql);
        System.out.println("\nNumber of records: "+query.uniqueResult());
    }
}


