package com.example.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.entity.Author;
import com.example.library.handler.LibraryException;
import com.example.library.service.AuthorService;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

	@Autowired
	private AuthorService authorService;
	
	@GetMapping
	private ResponseEntity<List<Author>> getAllAuthors() {
		try {
			List<Author> authors = authorService.getAllAuthors();

			if (authors.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(authors, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	private ResponseEntity<Author> getAuthorById(@PathVariable("id") long id) {
		Author author = authorService.getAuthorById(id);

		if (author != null)
			return new ResponseEntity<>(author, HttpStatus.OK);
		else
			throw new LibraryException("author-not-found", String.format("Author with id=%d not found", id),
					HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
		try {
			return new ResponseEntity<>(authorService.createAuthor(author), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Author> updateAuthor(@PathVariable("id") long id, @RequestBody Author author) {
		Author updatedAuthor = authorService.updateAuthor(id, author);

		if (updatedAuthor != null)
			return new ResponseEntity<>(updatedAuthor, HttpStatus.OK);
		else
			throw new LibraryException("author-not-found", String.format("Author with id=%d not found", id),
					HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	private ResponseEntity<HttpStatus> deleteAuthor(@PathVariable("id") long id) {
		try {
			authorService.deleteAuthor(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
