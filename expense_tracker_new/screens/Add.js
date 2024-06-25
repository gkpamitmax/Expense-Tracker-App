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
  Switch,
} from "react-native";
import { ListItem } from "../components/ListItem";

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

export const Add = ({ isExpense }) => {
  const [amount, setAmount] = useState("");
  const [note, setNote] = useState("");
  const [selectedDate, setSelectedDate] = useState(new Date());
  const [selectedCategory, setSelectedCategory] = useState(null);
  const [isCategoryModalVisible, setCategoryModalVisible] = useState(false);
  const [dateInput, setDateInput] = useState("");

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

  const handleSubmit = () => {
    if (isExpense) {
      if (!amount || !selectedCategory || !note) {
        let errorMessage = "Please";

        if (!amount) {
          errorMessage += " enter the amount";
        }

        if (!selectedCategory) {
          errorMessage +=
            errorMessage.length > 6
              ? ", select a category"
              : " select a category";
        }

        if (!note) {
          errorMessage +=
            errorMessage.length > 6 ? ", and enter a note" : " enter a note";
        }

        errorMessage += ".";

        // Display an alert to the user
        Alert.alert("Error", errorMessage, [
          { text: "OK", onPress: () => {} }, // You can add a callback function for the OK button
        ]);
      }
    } else {
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

        errorMessage += ".";

        // Display an alert to the user
        Alert.alert("Error", errorMessage, [
          { text: "OK", onPress: () => {} }, // You can add a callback function for the OK button
        ]);
      } else {
        const [day, month, year] = dateInput.split(" ");
        const selectedDate = new Date(`${year}-${month}-${day}`);

        // Perform the action for submitting the goal or expense, e.g., save to database
        console.log("Amount:", amount);
        console.log("Note:", note);
        console.log("End Date:", selectedDate);

        // Reset the fields after submission if needed
        setAmount("");
        setNote("");
        setDateInput("");
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
            label="Amount"
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
            label="Note"
            detail={
              <TextInput
                placeholder="Note"
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

          {isExpense ? (
            <>
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
            </>
          ) : (
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
          )}
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
          <Text style={{ color: "white" }}>
            {isExpense ? "Submit Expense" : "Add Goal"}
          </Text>
        </TouchableOpacity>
      </KeyboardAvoidingView>

      {isExpense && (
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
      )}
    </>
  );
};
