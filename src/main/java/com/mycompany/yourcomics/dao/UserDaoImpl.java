/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.yourcomics.dao;

import com.mycompany.yourcomics.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

/**
 *
 * @author fedec
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Override
    public void create(User user) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            System.out.println("session : " + session);
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User read(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User login(String username, String password) {
        User user = null;
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            transaction = session.beginTransaction();

            user = (User) session.createQuery("FROM com.mycompany.yourcomics.entity.User WHERE username='" + username + "' AND password='" + password + "'").uniqueResult();

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}
