package main;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Scanner;

import entities.Author;
import entities.Book;

public class BookStoreCLI {
    private EntityManagerFactory emf;
    private EntityManager em;

    public BookStoreCLI() {
        this.emf = Persistence.createEntityManagerFactory("BookStorePU");
        this.em = emf.createEntityManager();
    }

    public Author addAuthor(String name, Date birthDate) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Author author = new Author();
            author.setName(name);
            author.setBirthDate(birthDate);
            em.persist(author);
            transaction.commit();
            return author;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    public void removeAuthor(Author author) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Author managedAuthor = em.find(Author.class, author.getId());
            if (managedAuthor != null) {
                em.remove(managedAuthor);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Book addBook(String title, Author author) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Book book = new Book();
            book.setTitle(title);
            book.getAuthors().add(author);
            author.getBooks().add(book);
            em.persist(book);
            transaction.commit();
            return book;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    public void assignAuthorToBook(Author author, Book book) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Book managedBook = em.find(Book.class, book.getId());
            Author managedAuthor = em.find(Author.class, author.getId());

            if (managedBook != null && managedAuthor != null) {

                System.out.println("Assigning Author ID: " + managedAuthor.getId() + " to Book ID: " + managedBook.getId());

                if (!managedBook.getAuthors().contains(managedAuthor)) {
                    managedBook.getAuthors().add(managedAuthor);
                    managedAuthor.getBooks().add(managedBook);
                } else {
                    System.out.println("This author is already assigned to the book.");
                }
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }



    public void removeBook(Book book) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Book managedBook = em.find(Book.class, book.getId());
            if (managedBook != null) {
                em.remove(managedBook);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void close() {
        if (em != null) em.close();
        if (emf != null) emf.close();
    }

    public static void main(String[] args) {
        BookStoreCLI store = new BookStoreCLI();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Add a new author:");
        String name = scanner.nextLine();
        Date birthDate = new Date();
        Author author = store.addAuthor(name, birthDate);

        System.out.println("Add a new book:");
        String title = scanner.nextLine();
        Book book = store.addBook(title, author);

        System.out.println("Assign author to book:");
        store.assignAuthorToBook(author, book);

        store.close();
        scanner.close();
    }
}
