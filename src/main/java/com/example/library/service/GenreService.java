package com.example.library.service;

import java.util.List;

import com.example.library.entity.Genre;

public interface GenreService {

	List<Genre> getAllGenres();

	Genre getGenreById(long id);

	Genre createGenre(Genre genre);

	Genre updateGenre(long id, Genre genre);

	void deleteGenre(long id);
}
