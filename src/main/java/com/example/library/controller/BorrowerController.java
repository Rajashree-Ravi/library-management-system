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

import com.example.library.entity.Borrower;
import com.example.library.handler.LibraryException;
import com.example.library.service.BorrowerService;

@RestController
@RequestMapping("/api/borrowers")
public class BorrowerController {

	@Autowired
	private BorrowerService borrowerService;

	@GetMapping
	private ResponseEntity<List<Borrower>> getAllBorrowers() {
		try {
			List<Borrower> borrowers = borrowerService.getAllBorrowers();

			if (borrowers.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(borrowers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	private ResponseEntity<Borrower> getBorrowerById(@PathVariable("id") long id) {
		Borrower borrower = borrowerService.getBorrowerById(id);

		if (borrower != null)
			return new ResponseEntity<>(borrower, HttpStatus.OK);
		else
			throw new LibraryException("borrower-not-found", String.format("Borrower with id=%d not found", id),
					HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<Borrower> createBorrower(@RequestBody Borrower borrower) {
		try {
			return new ResponseEntity<>(borrowerService.createBorrower(borrower), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Borrower> updateBorrower(@PathVariable("id") long id, @RequestBody Borrower borrower) {
		Borrower updatedBorrower = borrowerService.updateBorrower(id, borrower);

		if (updatedBorrower != null)
			return new ResponseEntity<>(updatedBorrower, HttpStatus.OK);
		else
			throw new LibraryException("borrower-not-found", String.format("Borrower with id=%d not found", id),
					HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	private ResponseEntity<HttpStatus> deleteBorrower(@PathVariable("id") long id) {
		try {
			borrowerService.deleteBorrower(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
