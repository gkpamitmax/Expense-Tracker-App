package com.hdfc.finance.repository;

import com.hdfc.finance.model.BankAccount;
import com.hdfc.finance.model.User;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
	Optional<BankAccount> findByUserAndAccountNumber(User user, String accountNumber);
	List<BankAccount> findByUser(User user);
}
