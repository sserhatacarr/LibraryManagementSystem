package dev.patika.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "publishers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(name = "establishment_year")
    private  int establishmentYear;
    private String adress;

    @OneToMany (mappedBy = "publisher", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Book> bookList;
}
