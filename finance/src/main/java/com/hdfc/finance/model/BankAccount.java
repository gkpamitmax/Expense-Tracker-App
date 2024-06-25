package com.hdfc.finance.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class BankAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountID;

	@ManyToOne
	@JoinColumn(name = "userID")
	private User user;

	@NotBlank(message = "Account name is required")
	private String accountName;

	@NotBlank(message = "Bank name is required")
	private String bankName;

	@NotBlank(message = "Account number is required")
	@Pattern(regexp = "^[0-9]{11}$|^[0-9]{12}$|^[0-9]{14}$", message = "Account number must be 11, 12, or 14 digits")
	private String accountNumber;

	@Min(value = 0, message = "Balance must be zero or greater")
	private double balance;

	public Long getAccountID() {
		return accountID;
	}

	public void setAccountID(Long accountID) {
		this.accountID = accountID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		if (balance < 0) {
			throw new IllegalArgumentException("Balance cannot be negative");
		}
		this.balance = balance;
	}

	public BankAccount() {
	}

	@OneToMany(mappedBy = "bankAccount")
	private List<Transaction> transactions;

}
