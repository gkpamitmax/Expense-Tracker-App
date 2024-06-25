import React, { useState, useEffect } from "react";
import {
  KeyboardAvoidingView,
  TextInput,
  View,
  Text,
  TouchableOpacity,
  Modal,
  FlatList,
  Alert,
} from "react-native";
import { ListItem } from "../components/ListItem";
import axios from 'axios';

const categoriesData = [
  "Transportation",
  "Food",
  "Utilities",
  "Medical Healthcare",
  "Personal Spending",
  "Entertainment",
  "Others",
];

const categoryBulletStyles = {
  Transportation: { backgroundColor: "maroon" },
  Food: { backgroundColor: "green" },
  Utilities: { backgroundColor: "orange" },
  "Medical Healthcare": { backgroundColor: "purple" },
  "Personal Spending": { backgroundColor: "#DCD427" },
  Entertainment: { backgroundColor: "#0092CC" },
  Others: { backgroundColor: "pink" },
};

export const AddExpense = () => {
  const [amount, setAmount] = useState("");
  const [note,setNote] = useState("");
  const [selectedDate, setSelectedDate] = useState(new Date());
  const [selectedCategory, setSelectedCategory] = useState(null);
  const [isCategoryModalVisible, setCategoryModalVisible] = useState(false);


  const handleCategoryPress = () => {
    setCategoryModalVisible(true);
  };

  const handleCategorySelect = (category) => {
    setSelectedCategory(category);
    setCategoryModalVisible(false);
  };

  const renderCategoryItem = ({ item }) => (
    <TouchableOpacity
      style={{
        padding: 10,
        backgroundColor: "#151515",
        marginVertical: 5,
        borderRadius: 8,
      }}
      onPress={() => handleCategorySelect(item)}
    >
      <Text style={{ color: categoryBulletStyles[item].backgroundColor }}>
        {item}
      </Text>
    </TouchableOpacity>
  );

  useEffect(() => {
    // Perform actions when a category is selected, e.g., update state or perform other logic
    console.log("Selected Category:", selectedCategory);
  }, [selectedCategory]);



  const addExpenseApi = async () => {
    try {
      const response = await axios.post(
        'http://localhost:8087/expenses', // Replace with your actual API endpoint
        {
          amount: parseFloat(amount), // Ensure the amount is a number
          date: selectedDate.toISOString(),
          category: selectedCategory,
          description:note
        },
        {
          headers: {

            Authorization: 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsImlhdCI6MTcwMDU2MDQ5MywiZXhwIjoxNzAwNTc4NDkzfQ._d5rsVheG_M_6IAF1mmirsuqCDrh-gnptq4bjSVmin0', // Replace with your actual JWT token
          },
        }
      );
      
      console.log('Expense added successfully:', response.data);
      // Add any additional logic you need after a successful API call
    } catch (error) {
      console.error('Error adding expense:', error.message);
      // Handle the error appropriately (e.g., show an error message)
    }
  };

      


  const handleSubmit = () => {
    // ... (unchanged)
    if (!amount || !selectedCategory || !note) {
      let errorMessage = "Please";
  
      if (!amount) {
        errorMessage += " enter the amount";
      }
  
      if (!selectedCategory) {
        errorMessage += errorMessage.length > 6 ? ", select a category" : " select a category";
      }
  
      if (!note) {
        errorMessage += errorMessage.length > 6 ? ", and enter a note" : " enter a note";
      }
  
      errorMessage += ".";
  
      // Display an alert to the user
      Alert.alert("Error", errorMessage, [
        { text: "OK", onPress: () => { addExpenseApi()} }, // You can add a callback function for the OK button
      ]);
      
    }else {
      // Make the API call
        console.log("func call");
      addExpenseApi();
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
            label="Amount"
            detail={
              <TextInput
                placeholder="Amount"
                placeholderTextColor="grey"
                onChange={(event) => setAmount(event.nativeEvent.text)}
                value={amount}
                textAlign="right"
                keyboardType="numeric"
                inputAccessoryViewID="dismissKeyboard"
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
            label="Note"
            detail={
              <TextInput
                placeholder="Note"
                placeholderTextColor="grey"
                onChange={(event) => setNote(event.nativeEvent.text)}
                value={note}
                textAlign="right"
                inputAccessoryViewID="dismissKeyboard"
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
            label="Date"
            detail={
              <Text style={{ color: "#42a4f5", fontSize: 16 }}>
                {selectedDate.toDateString()}
              </Text>
            }
          />
          <ListItem
            label="Category"
            detail={
              <TouchableOpacity onPress={handleCategoryPress}>
                <Text
                  style={{
                    color: selectedCategory
                      ? categoryBulletStyles[selectedCategory].backgroundColor
                      : "#42a4f5",
                    fontSize: 16,
                  }}
                >
                  {selectedCategory || "Select Category"}
                </Text>
              </TouchableOpacity>
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
          <Text style={{ color: "white" }}>Submit Expense</Text>
        </TouchableOpacity>
      </KeyboardAvoidingView>

      <Modal
        visible={isCategoryModalVisible}
        animationType="slide"
        transparent={true}
        onRequestClose={() => setCategoryModalVisible(false)}
      >
        <View
          style={{
            flex: 1,
            justifyContent: "center",
            alignItems: "center",
            backgroundColor: "rgba(0, 0, 0, 0.5)",
          }}
        >
          <View
            style={{
              width: "80%",
              backgroundColor: "black",
              borderRadius: 15,
              padding: 16,
              borderColor: "grey", 
              borderWidth: 0, 
              flexDirection: "column",
            }}
          >
            <FlatList
              data={categoriesData}
              renderItem={renderCategoryItem}
              keyExtractor={(item) => item}
            />

            <TouchableOpacity
              onPress={() => setCategoryModalVisible(false)}
              style={{
                display: "flex",
                marginTop: 10,
                padding: 10,
                backgroundColor: "#151515",
                borderRadius: 8,
                borderWidth: 0,
                borderColor: "grey",
                alignItems: "center",
              }}
            >
              <Text style={{ fontWeight: 400, fontSize: 15, color: "red" }}>
                Close
              </Text>
            </TouchableOpacity>
          </View>
        </View>
      </Modal>
    </>
  );
}