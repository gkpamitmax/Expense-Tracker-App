package com.hdfc.finance.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class Budget {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long budgetID;

	@ManyToOne
	@JoinColumn(name = "userID")
	private User user;

	@NotBlank(message = "Budget name is required")
    private String budgetName;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @Positive(message = "Total budget amount must be a positive value")
    private double totalBudgetAmount;

	public Budget() {
	}

	public Long getBudgetID() {
		return budgetID;
	}

	public void setBudgetID(Long budgetID) {
		this.budgetID = budgetID;
	}

	public String getBudgetName() {
		return budgetName;
	}

	public void setBudgetName(String budgetName) {
		this.budgetName = budgetName;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public double getTotalBudgetAmount() {
		return totalBudgetAmount;
	}

	public void setTotalBudgetAmount(double totalBudgetAmount) {
		this.totalBudgetAmount = totalBudgetAmount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
