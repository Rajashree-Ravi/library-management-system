package com.example.library.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.entity.LibraryBranch;
import com.example.library.repository.LibraryBranchRepository;
import com.example.library.service.LibraryBranchService;

@Service
public class LibraryBranchServiceImpl implements LibraryBranchService {

	@Autowired
	LibraryBranchRepository libraryBranchRepository;

	@Override
	public List<LibraryBranch> getAllLibraryBranchs() {
		List<LibraryBranch> libraryBranchs = new ArrayList<LibraryBranch>();
		libraryBranchRepository.findAll().forEach(libraryBranchs::add);
		return libraryBranchs;
	}

	@Override
	public LibraryBranch getLibraryBranchById(long id) {
		Optional<LibraryBranch> libraryBranch = libraryBranchRepository.findById(id);
		
		if(libraryBranch.isPresent())
			return libraryBranch.get();

		return null;
	}

	@Override
	public LibraryBranch createLibraryBranch(LibraryBranch libraryBranch) {
		return libraryBranchRepository.save(libraryBranch);
	}

	@Override
	public LibraryBranch updateLibraryBranch(long id, LibraryBranch libraryBranch) {
		Optional<LibraryBranch> updatedLibraryBranch = libraryBranchRepository.findById(id).map(existingLibraryBranch -> {
			return libraryBranchRepository.save(existingLibraryBranch.updateWith(libraryBranch));
		});

		if (updatedLibraryBranch.isPresent())
			return updatedLibraryBranch.get();

		return null;
	}

	@Override
	public void deleteLibraryBranch(long id) {
		libraryBranchRepository.deleteById(id);
	}
	
}
