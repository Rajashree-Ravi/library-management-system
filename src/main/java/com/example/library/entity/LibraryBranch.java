package com.example.library.entity;

import java.util.Set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "library_branch")
@Getter
@Setter
public class LibraryBranch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "branchName")
	private String branchName;

	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "pincode")
	private int pincode;

	@OneToMany(mappedBy = "library")
	private Set<Book> books;

	@OneToMany(mappedBy = "libraryBranch")
	private Set<Borrower> borrowers;

	public LibraryBranch() {

	}

	public LibraryBranch(long id, String branchName, String address, String city, String state, int pincode,
			Set<Book> books, Set<Borrower> borrowers) {
		super();
		this.id = id;
		this.branchName = branchName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.books = books;
		this.borrowers = borrowers;
	}

	public LibraryBranch updateWith(LibraryBranch libraryBranch) {
		return new LibraryBranch(this.id, libraryBranch.branchName, libraryBranch.address, libraryBranch.city,
				libraryBranch.state, libraryBranch.pincode, libraryBranch.books, libraryBranch.borrowers);
	}

	@Override
	public String toString() {
		return "LibraryBranch [id=" + id + ", branchName=" + branchName + ", address=" + address + ", city=" + city
				+ ", state=" + state + ", pincode=" + pincode + ", books=" + books + ", borrowers=" + borrowers + "]";
	}

}
