package com.example.library.service;

import java.util.List;

import com.example.library.entity.Author;

public interface AuthorService {

	List<Author> getAllAuthors();

	Author getAuthorById(long id);

	Author createAuthor(Author author);

	Author updateAuthor(long id, Author author);

	void deleteAuthor(long id);
}
