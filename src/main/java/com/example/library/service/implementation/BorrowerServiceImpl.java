package com.example.library.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.entity.Borrower;
import com.example.library.repository.BorrowerRepository;
import com.example.library.service.BorrowerService;

@Service
public class BorrowerServiceImpl implements BorrowerService {

	@Autowired
	BorrowerRepository borrowerRepository;

	@Override
	public List<Borrower> getAllBorrowers() {
		List<Borrower> borrowers = new ArrayList<Borrower>();
		borrowerRepository.findAll().forEach(borrowers::add);
		return borrowers;
	}

	@Override
	public Borrower getBorrowerById(long id) {
		Optional<Borrower> borrower = borrowerRepository.findById(id);
		
		if(borrower.isPresent())
			return borrower.get();
		return null;
	}

	@Override
	public Borrower createBorrower(Borrower borrower) {
		return borrowerRepository.save(borrower);
	}

	@Override
	public Borrower updateBorrower(long id, Borrower borrower) {
		Optional<Borrower> updatedBorrower = borrowerRepository.findById(id).map(existingBorrower -> {
			return borrowerRepository.save(existingBorrower.updateWith(borrower));
		});

		if (updatedBorrower.isPresent())
			return updatedBorrower.get();

		return null;
	}

	@Override
	public void deleteBorrower(long id) {
		borrowerRepository.deleteById(id);
	}

}
