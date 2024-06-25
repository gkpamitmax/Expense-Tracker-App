package com.hdfc.goals.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;

import com.hdfc.goals.model.Goals;
import com.hdfc.goals.repository.GoalsRepository;

@Service
public class GoalsService {

	private final GoalsRepository goalsRepository;

	public GoalsService(GoalsRepository goalsRepository) {
		this.goalsRepository = goalsRepository;
	}

	public List<Goals> getAllGoalsByUsername(String username) {
		return goalsRepository.findByUsername(username);
	}

	public List<Goals> getAllGoalsForAdmin() {
		return goalsRepository.findAll();
	}

	public Goals addGoal(Goals goal) {
		return goalsRepository.save(goal);
	}

	public void deleteGoal(Long goalId) {

		Optional<Goals> optionalGoal = goalsRepository.findById(goalId);
		if (optionalGoal.isPresent()) {
			Goals goal = optionalGoal.get();

			goalsRepository.deleteById(goalId);
		} else {
			throw new IllegalArgumentException("Goal not found");
		}
	}

	public Goals updateGoal(Long goalId, Goals updatedGoal) {
		Goals goal = goalsRepository.findById(goalId).orElse(null);

		
		goal.setCurrentAmount(goal.getCurrentAmount()+updatedGoal.getCurrentAmount());
		
		
		return goalsRepository.save(goal);

	}
	
	public Goals getGoalById(Long goalId){
		return goalsRepository.findById(goalId).orElse(null);
	}

}