package com.hdfc.finance.repository;

import com.hdfc.finance.model.Goal;
import com.hdfc.finance.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface GoalRepository extends JpaRepository<Goal, Long> {
	List<Goal> findByUser(User user);
	Optional<Goal> findByUserAndGoalName(User user, String goalName);
	
}
