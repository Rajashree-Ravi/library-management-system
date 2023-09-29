package com.example.library.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "genre")
@Getter
@Setter
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "type")
	private String type;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "genres")
	@JsonIgnore
	private Set<Book> books = new HashSet<>();

	public Genre() {

	}

	public Genre(long id, String name, String description, String type, Set<Book> books) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.books = books;
	}

	public Genre updateWith(Genre genre) {
		return new Genre(this.id, genre.name, genre.description, genre.type, genre.books);
	}

	@Override
	public String toString() {
		return "Genre [id=" + id + ", name=" + name + ", description=" + description + ", type=" + type + ", books="
				+ books + "]";
	}

}
