package com.example.library.service;

import java.util.List;

import com.example.library.entity.Borrower;

public interface BorrowerService {

	List<Borrower> getAllBorrowers();

	Borrower getBorrowerById(long id);

	Borrower createBorrower(Borrower borrower);

	Borrower updateBorrower(long id, Borrower borrower);

	void deleteBorrower(long id);
}
