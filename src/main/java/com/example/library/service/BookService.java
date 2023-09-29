package com.example.library.service;

import java.util.List;

import com.example.library.entity.Book;

public interface BookService {

	List<Book> getAllBooks(String title);

	Book getBookById(long id);

	Book createBook(Book book);

	Book updateBook(long id, Book book);

	void deleteBook(long id);

	List<Book> findByAuthor(long authorId);

	List<Book> findByGenre(long genreId);

	List<Book> findByLibraryBranch(long libraryId);

	List<Book> findByBorrower(long borrowerId);
}
