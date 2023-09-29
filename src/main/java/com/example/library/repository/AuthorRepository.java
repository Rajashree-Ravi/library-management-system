package com.example.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{

}
