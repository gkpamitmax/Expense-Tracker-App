package com.hdfc.finance.repository;

import com.hdfc.finance.model.BankAccount;
import com.hdfc.finance.model.Transaction;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	List<Transaction> findByBankAccountAndCategory(BankAccount bankAccount, String category);

	List<Transaction> findByBankAccountAndDateBetween(BankAccount bankAccount, LocalDate startDate, LocalDate endDate);
}
