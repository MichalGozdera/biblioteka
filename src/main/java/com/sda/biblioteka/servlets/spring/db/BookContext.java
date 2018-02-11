package com.sda.biblioteka.servlets.spring.db;

import com.sda.biblioteka.servlets.spring.db.model.Book;

import com.sda.biblioteka.servlets.spring.db.model.Lend;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookContext {
    static SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    public void addBook(Book book) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.getTransaction();
            tx.begin();
            session.save(book);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
        }
    }

    public List<Book> findBook(String quer) {
        List<Book> books = new ArrayList<>();
        try (Session session = factory.openSession()) {
            Query query = session.createQuery(quer, Book.class);
            books = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return books;
    }

    public boolean isLend(Integer id) {
        List<Date> dates = null;
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("select l.lendingDate from Lend l inner join l.book b where l.returnDate IS NULL and l.book=" + id, Date.class);
            dates = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (dates.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public void deleteBook(Book book) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.getTransaction();
            tx.begin();
            session.delete(book);
            tx.commit();

        } catch (Exception ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
        }
    }

    public void updateBook(Book book) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.getTransaction();
            tx.begin();
            session.update(book);
            tx.commit();

        } catch (Exception ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
        }
    }

    public void returnBook(Integer id) {
        Transaction tx = null;
        Lend lend = null;
        try (Session session = factory.openSession()) {
            tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("select l from Lend l inner join l.book b where l.returnDate IS NULL and l.book=" + id, Lend.class);
            lend = (Lend) query.getSingleResult();
            lend.setReturnDate(new Date());
            session.update(lend);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
        }
    }


    public void borrowBook(Lend lend) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.getTransaction();
            tx.begin();
            session.save(lend);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
        }
    }

    public long getBooksCount() {
        long result = 0;
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("select count(b) from Book b", Long.class);
            result = (long) query.getSingleResult();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

}
