package com.example.library.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.entity.Genre;
import com.example.library.repository.GenreRepository;
import com.example.library.service.GenreService;

@Service
public class GenreServiceImpl implements GenreService {
	
	@Autowired
	GenreRepository genreRepository;

	@Override
	public List<Genre> getAllGenres() {
		List<Genre> genres = new ArrayList<Genre>();
		genreRepository.findAll().forEach(genres::add);
		return genres;
	}

	@Override
	public Genre getGenreById(long id) {
		Optional<Genre> genre = genreRepository.findById(id);
		
		if(genre.isPresent())
			return genre.get();

		return null;
	}

	@Override
	public Genre createGenre(Genre genre) {
		return genreRepository.save(genre);
	}

	@Override
	public Genre updateGenre(long id, Genre genre) {
		Optional<Genre> updatedGenre = genreRepository.findById(id).map(existingGenre -> {
			return genreRepository.save(existingGenre.updateWith(genre));
		});

		if (updatedGenre.isPresent())
			return updatedGenre.get();

		return null;
	}

	@Override
	public void deleteGenre(long id) {
		genreRepository.deleteById(id);
	}

}
