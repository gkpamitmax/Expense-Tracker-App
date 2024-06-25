package com.hdfc.finance.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
public class Goal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long goalID;

	@ManyToOne
	@JoinColumn(name = "userID")
	private User user;

	@NotBlank(message = "Goal name is required")
	private String goalName;

	@Positive(message = "Target amount must be a positive value")
	private double targetAmount;

	@Positive(message = "Current amount must be a positive value")
	private double currentAmount;

	@NotBlank(message = "Target date is required")
	private LocalDate targetDate;

	public Goal() {
	}

	public Long getGoalID() {
		return goalID;
	}

	public void setGoalID(Long goalID) {
		this.goalID = goalID;
	}

	public String getGoalName() {
		return goalName;
	}

	public void setGoalName(String goalName) {
		this.goalName = goalName;
	}

	public double getTargetAmount() {
		return targetAmount;
	}

	public void setTargetAmount(double targetAmount) {
		this.targetAmount = targetAmount;
	}

	public double getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(double currentAmount) {
		this.currentAmount = currentAmount;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}