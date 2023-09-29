package com.example.library.service;

import java.util.List;

import com.example.library.entity.LibraryBranch;

public interface LibraryBranchService {
	
	List<LibraryBranch> getAllLibraryBranchs();

	LibraryBranch getLibraryBranchById(long id);

	LibraryBranch createLibraryBranch(LibraryBranch libraryBranch);

	LibraryBranch updateLibraryBranch(long id, LibraryBranch libraryBranch);

	void deleteLibraryBranch(long id);
}
