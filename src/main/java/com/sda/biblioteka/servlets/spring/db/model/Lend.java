package com.sda.biblioteka.servlets.spring.db.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lendings")
public class Lend {
    @Id
    @GeneratedValue
    private Integer lendId;
    @OneToOne
    @JoinColumn(name="user_id")
    private User user;
    @OneToOne
    @JoinColumn(name="book_id")
    private Book book;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lending_date")
    private Date lendingDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "return_date")
    private Date returnDate;

    public Lend() {
    }

    public Integer getLendId() {
        return lendId;
    }

    public void setLendId(Integer lendId) {
        this.lendId = lendId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getLendingDate() {
        return lendingDate;
    }

    public void setLendingDate(Date lendingDate) {
        this.lendingDate = lendingDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
