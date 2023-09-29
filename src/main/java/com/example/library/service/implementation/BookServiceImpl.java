package com.example.library.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;

	@Override
	public List<Book> getAllBooks(String title) {
		List<Book> books = new ArrayList<Book>();

		if (title == null)
			bookRepository.findAll().forEach(books::add);
		else
			bookRepository.findByTitleContaining(title).forEach(books::add);

		return books;
	}

	@Override
	public Book getBookById(long id) {
		Optional<Book> book = bookRepository.findById(id);

		if (book.isPresent())
			return book.get();
		
		return null;
	}

	@Override
	public Book createBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public Book updateBook(long id, Book book) {

		Optional<Book> updatedBook = bookRepository.findById(id).map(existingBook -> {
			return bookRepository.save(existingBook.updateWith(book));
		});

		if (updatedBook.isPresent())
			return updatedBook.get();
		
		return null;
	}

	@Override
	public void deleteBook(long id) {
		bookRepository.deleteById(id);
	}

	@Override
	public List<Book> findByAuthor(long authorId) {
		return bookRepository.findByAuthorsId(authorId);
	}

	@Override
	public List<Book> findByGenre(long genreId) {
		return bookRepository.findByGenresId(genreId);
	}

	@Override
	public List<Book> findByLibraryBranch(long libraryId) {
		return bookRepository.findByLibraryId(libraryId);
	}

	@Override
	public List<Book> findByBorrower(long borrowerId) {
		return bookRepository.findByCurrentBorrowerId(borrowerId);
	}

}
