//Author: Karolyi Krisztian
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 255)
    private String title;

//    @ManyToOne
//    @JoinColumn(name = "author_id", nullable = false)
//    private Author author;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "book_author",
    joinColumns = @JoinColumn(name = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors = new HashSet<>();


    @Column
    private Date publishedAt;

    @Column
    private Integer pages;

    @Column (precision = 10, scale = 2)
    private BigDecimal price;

    @Column(length = 20)
    private String stockStatus;

//    public Book(String title, Author author, Date publishedAt, Integer pages, BigDecimal price, StockStatus stockStatus) {
//        this.title = title;
//        this.author = author;
//        this.publishedAt = publishedAt;
//        this.pages = pages;
//        this.price = price;
//        this.stockStatus = stockStatus.name();
//    }

    public Book(String title, Date publishedAt, Integer pages, BigDecimal price, StockStatus stockStatus) {
        this.title = title;
        this.publishedAt = publishedAt;
        this.pages = pages;
        this.price = price;
        this.stockStatus = stockStatus.name();
    }


    public Book() {
    	this.price = new BigDecimal("0");
    	this.stockStatus = StockStatus.UNKNOWN.toString();
    	this.pages = 0;
    	this.publishedAt = new Date();
    	this.authors = new HashSet();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}



