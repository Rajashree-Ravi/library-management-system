package com.example.library.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.entity.Author;
import com.example.library.repository.AuthorRepository;
import com.example.library.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	AuthorRepository authorRepository;

	@Override
	public List<Author> getAllAuthors() {
		List<Author> authors = new ArrayList<Author>();
		authorRepository.findAll().forEach(authors::add);
		return authors;
	}

	@Override
	public Author getAuthorById(long id) {
		Optional<Author> author = authorRepository.findById(id);
		
		if(author.isPresent())
			return author.get();

		return null;
	}

	@Override
	public Author createAuthor(Author author) {
		return authorRepository.save(author);
	}

	@Override
	public Author updateAuthor(long id, Author author) {
		Optional<Author> updatedAuthor = authorRepository.findById(id).map(existingAuthor -> {
			return authorRepository.save(existingAuthor.updateWith(author));
		});

		if (updatedAuthor.isPresent())
			return updatedAuthor.get();

		return null;
	}

	@Override
	public void deleteAuthor(long id) {
		authorRepository.deleteById(id);
	}

}
