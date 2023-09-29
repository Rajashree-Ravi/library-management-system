package com.example.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.library.entity.Book;
import com.example.library.handler.LibraryException;
import com.example.library.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping
	private ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false) String title) {
		try {
			List<Book> books = bookService.getAllBooks(title);

			if (books.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(books, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	private ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
		Book book = bookService.getBookById(id);

		if (book != null)
			return new ResponseEntity<>(book, HttpStatus.OK);
		else
			throw new LibraryException("book-not-found", String.format("Book with id=%d not found", id),
					HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
		try {
			return new ResponseEntity<>(bookService.createBook(book), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @RequestBody Book book) {
		Book updatedBook = bookService.updateBook(id, book);

		if (updatedBook != null)
			return new ResponseEntity<>(updatedBook, HttpStatus.OK);
		else
			throw new LibraryException("book-not-found", String.format("Book with id=%d not found", id),
					HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	private ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") long id) {
		try {
			bookService.deleteBook(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/genre/{id}")
	private ResponseEntity<List<Book>> findByGenre(@PathVariable("id") long genreId) {
		try {
			List<Book> books = bookService.findByGenre(genreId);

			if (books.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(books, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/author/{id}")
	private ResponseEntity<List<Book>> findByAuthor(@PathVariable("id") long authorId) {
		try {
			List<Book> books = bookService.findByAuthor(authorId);

			if (books.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(books, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/library-branch/{id}")
	private ResponseEntity<List<Book>> findByLibraryBranch(@PathVariable("id") long libraryId) {
		try {
			List<Book> books = bookService.findByLibraryBranch(libraryId);

			if (books.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(books, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/borrower/{id}")
	private ResponseEntity<List<Book>> findByBorrower(@PathVariable("id") long borrowerId) {
		try {
			List<Book> books = bookService.findByBorrower(borrowerId);

			if (books.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(books, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
