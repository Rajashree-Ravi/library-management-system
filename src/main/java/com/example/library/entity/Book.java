package com.example.library.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "book")
@Getter
@Setter
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "publisher")
	private String publisher;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<Author> authors = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<Genre> genres = new HashSet<>();
	
	@ManyToOne
    @JoinColumn(name="borrower_id")
    private Borrower currentBorrower;

	@ManyToOne
	@JoinColumn(name="library_branch_id")
    private LibraryBranch library; 
	
	public Book() {

	}

	public Book(long id, String title, String description, String publisher, Set<Author> authors, Set<Genre> genres) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.publisher = publisher;
		this.authors = authors;
		this.genres = genres;
	}

	public Book updateWith(Book book) {
		return new Book(this.id, book.title, book.description, book.publisher, book.authors, book.genres);
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", description=" + description + ", publisher=" + publisher
				+ ", authors=" + authors.toString() + ", genres=" + genres.toString() + "]";
	}

}
