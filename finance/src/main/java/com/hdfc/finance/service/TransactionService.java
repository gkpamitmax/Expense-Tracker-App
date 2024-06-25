package com.hdfc.finance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hdfc.finance.model.BankAccount;
import com.hdfc.finance.model.Transaction;
import com.hdfc.finance.repository.TransactionRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	public Transaction addTransaction(BankAccount bankAccount, LocalDate date, double amount, String category,
			String description) {
		Transaction transaction = new Transaction();
		transaction.setBankAccount(bankAccount);
		transaction.setDate(date);
		transaction.setAmount(amount);
		transaction.setCategory(category);
		transaction.setDescription(description);

		return transactionRepository.save(transaction);
	}

	public List<Transaction> getTransactionsByCategory(BankAccount bankAccount, String category) {
		return transactionRepository.findByBankAccountAndCategory(bankAccount, category);
	}

	public List<Transaction> getTransactionsInDateRange(BankAccount bankAccount, LocalDate startDate,
			LocalDate endDate) {
		return transactionRepository.findByBankAccountAndDateBetween(bankAccount, startDate, endDate);
	}

}
