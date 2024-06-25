// Handling financial goals set by users.
package com.hdfc.finance.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hdfc.finance.model.Goal;
import com.hdfc.finance.model.User;
import com.hdfc.finance.repository.GoalRepository;

@Service
public class GoalService {

	@Autowired
	private GoalRepository goalRepository;

	public Goal setFinancialGoal(User user, String goalName, double targetAmount, LocalDate targetDate) {

		Goal goal = new Goal();
		goal.setUser(user);
		goal.setGoalName(goalName);
		goal.setTargetAmount(targetAmount);
		goal.setTargetDate(targetDate);
		return goalRepository.save(goal);
	}

	public List<Goal> getGoalsByUser(User user) {
		return goalRepository.findByUser(user);
	}

	public Goal getGoalByName(User user, String goalName) {
		Optional<Goal> optionalGoal = goalRepository.findByUserAndGoalName(user, goalName);
		return optionalGoal.orElseThrow(() -> new IllegalArgumentException("Goal not found"));
	}

	public Goal updateGoal(Long goalId, String goalName, double targetAmount, LocalDate targetDate) {
		Optional<Goal> optionalGoal = goalRepository.findById(goalId);
		if (optionalGoal.isPresent()) {
			Goal goal = optionalGoal.get();
			goal.setGoalName(goalName);
			goal.setTargetAmount(targetAmount);
			goal.setTargetDate(targetDate);
			return goalRepository.save(goal);
		} else {
			throw new IllegalArgumentException("Goal not found");
		}
	}

	public void deleteGoal(Long goalId) {
		goalRepository.deleteById(goalId);
	}

	public double getGoalProgress(User user, String goalName) {
		Goal goal = goalRepository.findByUserAndGoalName(user, goalName)
				.orElseThrow(() -> new IllegalArgumentException("Goal not found"));

		double achievedAmount = 1000; // Random Value, Need to be changed later.
		// We need to think how to get this value from transactions/expenses and balance.
		return (achievedAmount / goal.getTargetAmount()) * 100;
	}

	public List<Goal> getAllGoalsForAdmin(User user) {
		validateAdminRole(user);
		return goalRepository.findAll();
	}

	private void validateAdminRole(User user) {
		String userRole = user.getRole();
		if (!userRole.equals("admin")) {
			throw new IllegalStateException("User does not have admin privileges");
		}
	}
}
