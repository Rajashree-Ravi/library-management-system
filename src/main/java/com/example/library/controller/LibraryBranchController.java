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

import com.example.library.entity.LibraryBranch;
import com.example.library.handler.LibraryException;
import com.example.library.service.LibraryBranchService;

@RestController
@RequestMapping("/api/library-branches")
public class LibraryBranchController {

	@Autowired
	private LibraryBranchService libraryBranchService;
	
	@GetMapping
	private ResponseEntity<List<LibraryBranch>> getAllLibraryBranchs() {
		try {
			List<LibraryBranch> libraryBranchs = libraryBranchService.getAllLibraryBranchs();

			if (libraryBranchs.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(libraryBranchs, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	private ResponseEntity<LibraryBranch> getLibraryBranchById(@PathVariable("id") long id) {
		LibraryBranch libraryBranch = libraryBranchService.getLibraryBranchById(id);

		if (libraryBranch != null)
			return new ResponseEntity<>(libraryBranch, HttpStatus.OK);
		else
			throw new LibraryException("library-not-found", String.format("Library with id=%d not found", id),
					HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<LibraryBranch> createLibraryBranch(@RequestBody LibraryBranch libraryBranch) {
		try {
			return new ResponseEntity<>(libraryBranchService.createLibraryBranch(libraryBranch), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<LibraryBranch> updateLibraryBranch(@PathVariable("id") long id, @RequestBody LibraryBranch libraryBranch) {
		LibraryBranch updatedLibraryBranch = libraryBranchService.updateLibraryBranch(id, libraryBranch);

		if (updatedLibraryBranch != null)
			return new ResponseEntity<>(updatedLibraryBranch, HttpStatus.OK);
		else
			throw new LibraryException("library-not-found", String.format("Library with id=%d not found", id),
					HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	private ResponseEntity<HttpStatus> deleteLibraryBranch(@PathVariable("id") long id) {
		try {
			libraryBranchService.deleteLibraryBranch(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
