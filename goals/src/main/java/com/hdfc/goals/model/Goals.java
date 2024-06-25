package com.hdfc.goals.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
public class Goals {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long goalID;

	@Column(name = "user_name")
    private String username;

	@NotBlank(message = "Goal name is required")
	private String goalName;

	@Positive(message = "Target amount must be a positive value")
	private double targetAmount;

	@Positive(message = "Current amount must be a positive value")
	private double currentAmount;

	@NotBlank(message = "Target date is required")
	private LocalDate targetDate;

	
	public Goals(Long goalID, String username, @NotBlank(message = "Goal name is required") String goalName,
			@Positive(message = "Target amount must be a positive value") double targetAmount,
			@Positive(message = "Current amount must be a positive value") double currentAmount,
			@NotBlank(message = "Target date is required") LocalDate targetDate) {
		super();
		this.goalID = goalID;
		this.username = username;
		this.goalName = goalName;
		this.targetAmount = targetAmount;
		this.currentAmount = currentAmount;
		this.targetDate = targetDate;
	}

	public Goals() {
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}