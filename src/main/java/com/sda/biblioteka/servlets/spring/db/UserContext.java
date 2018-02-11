package com.sda.biblioteka.servlets.spring.db;

import com.sda.biblioteka.servlets.spring.db.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import javax.persistence.NamedQuery;
import javax.persistence.Query;
import java.io.*;

import java.util.*;

public class UserContext {
    public static SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    private Boolean isLogged=Boolean.FALSE;

    public boolean addUser(User user) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.getTransaction();
            tx.begin();
            System.out.println(user);
            session.save(user);
            tx.commit();
            return true;
        } catch (Exception ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
            return false;

        }
    }

    public User findUser(String log) {
        Transaction tx = null;
        User user = null;
        try (Session session = factory.openSession()) {
            tx = session.getTransaction();
            tx.begin();
            Criteria cr = session.createCriteria(User.class);
            cr.add(Restrictions.eq("login", log));
            user = (User) cr.uniqueResult();
            tx.commit();

        } catch (Exception ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
        }
        return user;
    }


    public void deleteUser(String log) {
        Transaction tx = null;
        List<User> results;
        try (Session session = factory.openSession()) {
            tx = session.getTransaction();
            tx.begin();
            Criteria cr = session.createCriteria(User.class);
            cr.add(Restrictions.eq("login", log));
            User user = (User) cr.uniqueResult();
            session.delete(user);
            tx.commit();

        } catch (Exception ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
        }
    }

    public void updateUser(User user) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.getTransaction();
            tx.begin();
            session.update(user);
            tx.commit();

        } catch (Exception ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
        }
    }

    public long getUsersCount() {
        long result = 0;
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("select count(u) from User u", Long.class);
            result = (long) query.getSingleResult();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public Boolean getLogged() {
        return isLogged;
    }

    public void setLogged(Boolean logged) {
        isLogged = logged;
    }
}


