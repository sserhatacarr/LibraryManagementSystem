package dev.patika.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "loan_books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "borrower_name")
    private String  borrowerName;
    @Column(name = "borrowing_date")
    private LocalDate borrowingDate;
    @Column(name = "return_date")
    private LocalDate returnDate;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

}
