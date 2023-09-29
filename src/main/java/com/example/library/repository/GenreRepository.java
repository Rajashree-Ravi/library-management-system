package com.example.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long>{

}
