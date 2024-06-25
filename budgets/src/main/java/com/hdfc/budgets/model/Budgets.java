package com.hdfc.budgets.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class Budgets {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long budgetID;

	@Column(name = "user_name")
    private String username;

	@NotBlank(message = "Budget name is required")
    private String budgetName;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @Positive(message = "Total budget amount must be a positive value")
    private double totalBudgetAmount;

    
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Budgets(Long budgetID, String username, @NotBlank(message = "Budget name is required") String budgetName,
			@NotNull(message = "Start date is required") LocalDate startDate,
			@NotNull(message = "End date is required") LocalDate endDate,
			@Positive(message = "Total budget amount must be a positive value") double totalBudgetAmount) {
		super();
		this.budgetID = budgetID;
		this.username = username;
		this.budgetName = budgetName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalBudgetAmount = totalBudgetAmount;
	}

	public Budgets() {
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

	

}
