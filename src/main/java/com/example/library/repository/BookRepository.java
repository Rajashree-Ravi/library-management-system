package com.example.library.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	List<Book> findByPublisher(String publisher);

	List<Book> findByTitleContaining(String title);
	
	List<Book> findByAuthorsId(long authorId);
	
	List<Book> findByGenresId(long genreId);
	
	List<Book> findByCurrentBorrowerId(long borrowerId);

	List<Book> findByLibraryId(long libraryId);
}