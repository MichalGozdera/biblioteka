package com.sda.biblioteka.servlets.spring.db.model;

import com.sda.biblioteka.servlets.spring.db.BookContext;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "books")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Book {
    @Id
    @GeneratedValue
    private Integer bookId;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(name = "yearOfRelease")
    private Integer yearOfRelease;

    public Book() {
    }

    public Book(String title, String author, Category category, Integer rokWydania) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.yearOfRelease = rokWydania;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(Integer yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public boolean isLent() {
        BookContext bc1 = new BookContext();
        return bc1.isLend(this.bookId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return bookId.equals(book.bookId);
    }

    @Override
    public int hashCode() {
        return bookId.hashCode();
    }
}
