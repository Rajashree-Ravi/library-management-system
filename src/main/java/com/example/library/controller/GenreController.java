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

import com.example.library.entity.Genre;
import com.example.library.handler.LibraryException;
import com.example.library.service.GenreService;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

	@Autowired
	private GenreService genreService;
	
	@GetMapping
	private ResponseEntity<List<Genre>> getAllGenres() {
		try {
			List<Genre> genres = genreService.getAllGenres();

			if (genres.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(genres, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	private ResponseEntity<Genre> getGenreById(@PathVariable("id") long id) {
		Genre genre = genreService.getGenreById(id);

		if (genre != null)
			return new ResponseEntity<>(genre, HttpStatus.OK);
		else
			throw new LibraryException("genre-not-found", String.format("Genre with id=%d not found", id),
					HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
		try {
			return new ResponseEntity<>(genreService.createGenre(genre), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Genre> updateGenre(@PathVariable("id") long id, @RequestBody Genre genre) {
		Genre updatedGenre = genreService.updateGenre(id, genre);

		if (updatedGenre != null)
			return new ResponseEntity<>(updatedGenre, HttpStatus.OK);
		else
			throw new LibraryException("genre-not-found", String.format("Genre with id=%d not found", id),
					HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	private ResponseEntity<HttpStatus> deleteGenre(@PathVariable("id") long id) {
		try {
			genreService.deleteGenre(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
