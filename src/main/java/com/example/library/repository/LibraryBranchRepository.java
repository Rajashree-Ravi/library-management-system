package com.example.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.entity.LibraryBranch;

public interface LibraryBranchRepository extends JpaRepository<LibraryBranch, Long>{

}
