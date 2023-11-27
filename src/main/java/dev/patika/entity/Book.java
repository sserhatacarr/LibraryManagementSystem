package dev.patika.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(name = "publish_year")
    private int publishYear;
    @Column(name = "stock_amount")
    private int stockAmount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id", referencedColumnName = "id")
    private Publisher publisher;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private java.util.List<BookBorrowing> bookBorrowingList;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_category", // yeni tablo adı
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private java.util.List<Category> categoryList;

    public List<Category> getCategoryList() {
        if (categoryList == null) {
            categoryList = new ArrayList<>();
        }
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }


}
