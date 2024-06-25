// Linking user bank accounts and transaction synchronization.
package com.hdfc.finance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hdfc.finance.model.BankAccount;
import com.hdfc.finance.model.User;
import com.hdfc.finance.repository.BankAccountRepository;

@Service
public class AccountLinkingService {

	@Autowired
	private BankAccountRepository bankAccountRepository;

	public BankAccount linkBankAccount(User user, String accountName, String bankName, String accountNumber) {

		// Check if the bank account is already linked
		if (bankAccountRepository.findByUserAndAccountNumber(user, accountNumber).isPresent()) {
			throw new IllegalStateException("Bank account is already linked");
		}

		BankAccount bankAccount = new BankAccount();
		bankAccount.setUser(user);
		bankAccount.setAccountName(accountName);
		bankAccount.setBankName(bankName);
		bankAccount.setAccountNumber(accountNumber);
		return bankAccountRepository.save(bankAccount);
	}

	public void unlinkBankAccount(User user, String accountNumber) {
		BankAccount bankAccount = bankAccountRepository.findByUserAndAccountNumber(user, accountNumber)
				.orElseThrow(() -> new IllegalArgumentException("Bank account not linked to this user"));

		bankAccountRepository.delete(bankAccount);
	}

	public double getCumulativeBalance(User user) {
		List<BankAccount> userBankAccounts = bankAccountRepository.findByUser(user);
		double cumulativeBalance = 0.0;
		for (BankAccount bankAccount : userBankAccounts) {
			cumulativeBalance += bankAccount.getBalance();
		}

		return cumulativeBalance;
	}

	public double getBalance(User user, String accountNumber) {
		BankAccount bankAccount = bankAccountRepository.findByUserAndAccountNumber(user, accountNumber)
				.orElseThrow(() -> new IllegalArgumentException("Bank account not linked to this user"));

		return bankAccount.getBalance();
	}

	public List<BankAccount> getAllBankAccountsForUser(User user) {
		return bankAccountRepository.findByUser(user);
	}

	public List<BankAccount> getAllBankAccountsForAdmin(User user) {
		validateAdminRole(user);
		return bankAccountRepository.findAll();
	}

	private void validateAdminRole(User user) {
		String userRole = user.getRole();
		if (!userRole.equals("admin")) {
			throw new IllegalStateException("User does not have admin privileges");
		}
	}

}
