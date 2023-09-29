package com.example.library.entity;

import java.util.Set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "borrower")
@Getter
@Setter
public class Borrower {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "address")
	private String address;

	@Column(name = "membershipNo")
	private String membershipNo;

	@Column(name = "bookLimit")
	private int bookLimit;

	@OneToMany(mappedBy = "currentBorrower")
	private Set<Book> books;
	
	@ManyToOne
	@JoinColumn(name="library_branch_id")
    private LibraryBranch libraryBranch; 

	public Borrower() {

	}

	public Borrower(long id, String firstName, String lastName, String email, String address, String membershipNo,
			int bookLimit, Set<Book> books) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.membershipNo = membershipNo;
		this.bookLimit = bookLimit;
		this.books = books;
	}

	public Borrower updateWith(Borrower borrower) {
		return new Borrower(this.id, borrower.firstName, borrower.lastName, borrower.email, borrower.address,
				borrower.membershipNo, borrower.bookLimit, borrower.books);
	}

	@Override
	public String toString() {
		return "Borrower [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", address=" + address + ", membershipNo=" + membershipNo + ", bookLimit=" + bookLimit + ", books="
				+ books + "]";
	}

}
