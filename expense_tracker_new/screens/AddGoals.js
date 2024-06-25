import React, { useState } from "react";
import {
  KeyboardAvoidingView,
  TextInput,
  View,
  Text,
  TouchableOpacity,
  Alert,
} from "react-native";
import axios from "axios";
import { ListItem } from "../components/ListItem";
import { getToken } from '../store/AsyncStorage';

export const AddGoals = () => {
  const [amount, setAmount] = useState("");
  const [note, setNote] = useState("");
  const [dateInput, setDateInput] = useState("");
  const [currentAmount, setCurrentAmount] = useState("");


  const addGoalApi = async () => {
        const day = dateInput.substring(0, 2);
        const month = dateInput.substring(2, 4);
        const year = dateInput.substring(4);
        const newSelectedDate = new Date(`${year}-${month}-${day}`);
    try {
      const token = await getToken();
      const response = await axios.post(
        'http://localhost:9100/goals',
        {
          targetAmount: parseFloat(amount),
          goalName: note,
          targetDate: newSelectedDate, // Format the date
          currentAmount: parseFloat(currentAmount),
        },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      console.log('Goal added successfully:', response.data);

      // Reset the fields after submission if needed
      setAmount("");
      setNote("");
      setDateInput("");
      setCurrentAmount("");
    } catch (error) {
      console.error('Error adding goal:', error.message);
      // Handle the error appropriately (e.g., show an error message)
    }
  };

  const handleSubmit = () => {
    if (!amount || !note || !dateInput) {
      let errorMessage = "Please";

      if (!amount) {
        errorMessage += " enter the amount";
      }

      if (!note) {
        errorMessage +=
          errorMessage.length > 6 ? ", and enter a note" : " enter a note";
      }

      if (!dateInput) {
        errorMessage +=
          errorMessage.length > 6
            ? ", and select a valid date"
            : " select a valid date";
      }

      if (!currentAmount) {
        errorMessage +=
          errorMessage.length > 6
            ? ", and enter the current amount"
            : " enter the current amount";
      }

      errorMessage += ".";

      // Display an alert to the user
      Alert.alert("Error", errorMessage, [
        { text: "OK", onPress: () => {} },
      ]);
    } else {
      const amountRegex = /^\d+(\.\d{1,2})?$/;
      const currentAmountRegex = /^\d+(\.\d{1,2})?$/;

      if (!amountRegex.test(amount)) {
        // Display an alert for invalid amount format
        Alert.alert("Error", "Please enter a valid numeric amount.", [
          { text: "OK", onPress: () => {} },
        ]);
      } 
      
      if (!currentAmountRegex.test(currentAmount)) {
        // Display an alert for invalid currentAmount format
        Alert.alert("Error", "Please enter a valid numeric current amount.", [
          { text: "OK", onPress: () => {} },
        ]);
      }
      // Validation of date: Check if the date input is 8 characters long and non-zero
      if (dateInput.length === 8 && /^[1-9]\d{7}$/.test(dateInput)) {
        const day = dateInput.substring(0, 2);
        const month = dateInput.substring(2, 4);
        const year = dateInput.substring(4);
        const newSelectedDate = new Date(`${year}-${month}-${day}`);
        // Check if the selected date is a valid date
        if (isNaN(newSelectedDate.getTime())) {
          Alert.alert("Error", "Please enter a valid date.", [
            { text: "OK" },
          ]);
        } else {
          addGoalApi();
        }
      } else {
        Alert.alert(
          "Error",
          "Please enter a valid date in the format DDMMYYYY.",
          [{ text: "OK" }]
        );
      }

    }
  
  };

  return (
    <>
      <KeyboardAvoidingView
        behavior="padding"
        keyboardVerticalOffset={112}
        style={{ margin: 16, flex: 1, alignItems: "center" }}
      >
        <View
          style={{
            borderRadius: 11,
            overflow: "hidden",
            width: "100%",
          }}
        >

          <ListItem
            label="Name"
            detail={
              <TextInput
                placeholder="Goal Name"
                placeholderTextColor="grey"
                onChange={(event) => setNote(event.nativeEvent.text)}
                value={note}
                textAlign="right"
                style={{
                  height: 40,
                  color: "white",
                  flex: 1,
                  borderRadius: 8,
                  paddingLeft: 8,
                  fontSize: 16,
                }}
              />
            }
          />

          <ListItem
            label="Target Amount"
            detail={
              <TextInput
                placeholder="Amount"
                placeholderTextColor="grey"
                onChange={(event) => setAmount(event.nativeEvent.text)}
                value={amount}
                textAlign="right"
                keyboardType="numeric"
                style={{
                  height: 40,
                  color: "white",
                  flex: 1,
                  borderRadius: 8,
                  paddingLeft: 8,
                  fontSize: 16,
                }}
              />
            }
          />

          <ListItem
            label="Current Amount"
            detail={
              <TextInput
                placeholder="Current Amount"
                placeholderTextColor="grey"
                onChange={(event) => setCurrentAmount(event.nativeEvent.text)}
                value={currentAmount}
                textAlign="right"
                keyboardType="numeric"
                style={{
                  height: 40,
                  color: "white",
                  flex: 1,
                  borderRadius: 8,
                  paddingLeft: 8,
                  fontSize: 16,
                }}
              />
            }
          />

          <ListItem
            label="End Date"
            detail={
              <TextInput
                placeholder="DD MM YYYY"
                placeholderTextColor="grey"
                onChange={(event) => setDateInput(event.nativeEvent.text)}
                value={dateInput}
                textAlign="right"
                keyboardType="numeric"
                style={{
                  height: 40,
                  color: "white",
                  flex: 1,
                  borderRadius: 8,
                  paddingLeft: 8,
                  fontSize: 16,
                }}
              />
            }
          />
        </View>

        <TouchableOpacity
          onPress={handleSubmit}
          style={{
            marginTop: 20,
            padding: 10,
            backgroundColor: "#42a4f5",
            borderRadius: 8,
          }}
        >
          <Text style={{ color: "white" }}>Add Goal</Text>
        </TouchableOpacity>
      </KeyboardAvoidingView>
    </>
  );
};