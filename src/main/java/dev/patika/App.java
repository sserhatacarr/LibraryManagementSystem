package dev.patika;

import dev.patika.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("postgresPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Author author = new Author();
        author.setName("J. R. R. Tolkien");
        author.setAddress("United Kingdom");
        author.setCountry("United Kingdom");
        author.setBirthDate(java.time.LocalDate.of(1892, 1, 3));
        entityManager.persist(author);

        Publisher publisher = new Publisher();
        publisher.setName("Allen & Unwin");
        publisher.setAdress("London");
        publisher.setEstablishmentYear(1914);
        entityManager.persist(publisher);

        Book book = new Book();
        book.setName("The Lord of the Rings");
        book.setPublishYear(1954);
        book.setStockAmount(100);
        book.setAuthor(author);
        book.setPublisher(publisher);
        entityManager.persist(book);
        entityManager.flush(); // This ensures that the book is immediately persisted and its ID is generated

        if (book.getId() == 0) {
            System.out.println("Book ID is not generated");
            return;
        }

        Category category = new Category();
        category.setName("Fantasy");
        category.setDescription("Fantasy is a genre of speculative fiction set in a fictional universe, often inspired by real world myth and folklore.");
        entityManager.persist(category);

        BookBorrowing bookBorrowing = new BookBorrowing();
        bookBorrowing.setBorrowerName("John Doe");
        bookBorrowing.setBorrowingDate(java.time.LocalDate.now());
        bookBorrowing.setReturnDate(java.time.LocalDate.now().plusDays(7));
        bookBorrowing.setBook(book);
        entityManager.persist(bookBorrowing);

        Book bookcategory = entityManager.find(Book.class, book.getId());

        if (bookcategory == null) {
            System.out.println("No book found with the given ID");
            return;
        }

        List<Category> categoryList = bookcategory.getCategoryList();

        if (categoryList == null) {
            System.out.println("Category list is null");
            return;
        }

        categoryList.add(category);
        bookcategory.setCategoryList(categoryList);
        entityManager.merge(bookcategory);

        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();

    }

}