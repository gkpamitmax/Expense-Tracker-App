import React, { useState, useEffect } from 'react';
import { View, Text, StyleSheet, ScrollView } from 'react-native';
import axios from 'axios';
import theme from '../theme';

export const Expenses = () => {
  const [totalExpenses, setTotalExpenses] = useState(0);
  const [recentExpenses, setRecentExpenses] = useState([]);

  useEffect(() => {
    // Fetch expenses data from the API
    const fetchExpensesData = async () => {
      try {
        const response = await axios.get('http://localhost:8087/expenses/all',
        {
          headers: {
            Authorization: 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsImlhdCI6MTcwMDU2MDQ5MywiZXhwIjoxNzAwNTc4NDkzfQ._d5rsVheG_M_6IAF1mmirsuqCDrh-gnptq4bjSVmin0',
          },
        }
        );
        const expensesData = response.data;

        // Sort expenses by date in descending order
        const sortedExpenses = expensesData.sort((a, b) => new Date(b.date) - new Date(a.date));

        const total = sortedExpenses.reduce((sum, expense) => sum + expense.amount, 0);
        setTotalExpenses(total);
        
        // Take the most recent 5 expenses
        setRecentExpenses(sortedExpenses.slice(0, 5));
      } catch (error) {
        console.error('Error fetching expenses data:', error.message);
      }
    };

    fetchExpensesData();
  }, []);

  return (
    <View style={[styles.container, { backgroundColor: theme.colors.background }]}>
      <View style={[styles.totalExpensesCard, { backgroundColor: theme.colors.card }]}>
        <Text style={[styles.expensesText, { color: theme.colors.text }]}>
          Total Expenses : Rs {totalExpenses}
        </Text>
      </View>

      <ScrollView style={styles.recentExpensesScrollView}>
        {recentExpenses.map((expense) => (
          <View key={expense.expenseId} style={[styles.expenseItem, { backgroundColor: theme.colors.card }]}>
            <Text style={{ color: theme.colors.text }}>
              {expense.category} : Rs {expense.amount}
            </Text>
            <Text style={{ color: theme.colors.text, marginLeft: 'auto' }}>
              {expense.description}
            </Text>
          </View>
        ))}
      </ScrollView>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 16,
  },
  totalExpensesCard: {
    padding: 16,
    marginBottom: 16,
    borderRadius: 8,
  },
  expensesText: {
    fontSize: 18,
  },
  recentExpensesScrollView: {
    marginBottom: 16,
  },
  expenseItem: {
    padding: 12,
    marginBottom: 8,
    borderRadius: 4,
  },
});
