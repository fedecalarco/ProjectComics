/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.yourcomics.dao;

import com.mycompany.yourcomics.entity.Comic;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author fedec
 */
public class ComicDaoImpl implements ComicDao {

    @Override
    public void create(Comic comic) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            System.out.println("session : " + session);
            transaction = session.beginTransaction();
            session.save(comic);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Comic read(Long id) {
        Comic comic = null;
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            System.out.println("session : " + session);
            transaction = session.beginTransaction();
            comic = (Comic) session.get(Comic.class, id);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comic;
    }

    @Override
    public void update(Comic comic) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Comic comic) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Comic> getAll() {

        List<Comic> lstAllComics = new ArrayList<Comic>();

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            System.out.println("session : " + session);
            transaction = session.beginTransaction();
            lstAllComics = session.createCriteria(Comic.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lstAllComics;
    }

}
