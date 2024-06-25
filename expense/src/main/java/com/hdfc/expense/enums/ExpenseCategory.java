package com.hdfc.expense.enums;

public enum ExpenseCategory {
    TRANSPORTATION,
    FOOD,
    UTILITIES,
    MEDICAL_HEALTHCARE("Medical & Healthcare"),
    PERSONAL_SPENDING("Personal Spending"),
    ENTERTAINMENT,
    OTHERS;

    private final String label;

    ExpenseCategory() {
        this.label = name();
    }

    ExpenseCategory(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}