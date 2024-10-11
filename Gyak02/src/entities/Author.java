package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
public class Author{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, length = 255)
	private String name;
	private Date birthdate;

//@OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    @ManyToMany(mappedBy = "authors", cascade = {CascadeType.PERSIST})
    private Set<Book> books = new HashSet<>();


	public Author(String name, Date birthdate) {

		this.name = name;
		this.birthdate = birthdate;
		this.books = new HashSet<Book>();
	}

	public Author(String name) {
        this.name = name;
    }

	public Author(){

	}

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id = id;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", birthdate=" + birthdate + "]";
	}


	public Set<Book> getBooks() {
		return books;
	}

	public void addBook(Book book){
		this.books.add(book);
	}

	public void setBirthDate(Date birthDate) {
		// TODO Auto-generated method stub
		this.birthdate = birthDate;
	}

}























