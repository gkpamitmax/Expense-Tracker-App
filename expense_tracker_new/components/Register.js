import React, { useState } from 'react';
import { View, Text, TextInput, TouchableOpacity, StyleSheet, Alert } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import { setToken } from '../store/AsyncStorage';
import axios from 'axios';

const Register = () => {
  const navigation = useNavigation();

  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [balance, setBalance] = useState('');
  const [role, setRole] = useState('');

  const validateEmail = (email) => {
    // Basic email validation
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  };

  const validateUsername = (username) => {
    // Basic username validation (at least 3 characters)
    return username.length >= 3;
  };

  const validatePassword = (password) => {
    // Basic password validation (at least 6 characters)
    return password.length >= 6;
  };

  const validateBalance = (balance) => {
    // Basic balance validation (numeric and non-negative)
    const balanceRegex = /^\d+(\.\d{1,2})?$/;
    return balanceRegex.test(balance) && parseFloat(balance) >= 0;
  };

  const handleRegister = async () => {
    try {
      // Validate input fields
      if (!validateEmail(email)) {
        console.log("'Error', 'Please enter a valid email address.'");
        Alert.alert('Error', 'Please enter a valid email address.');
        
        return;
      }

      if (!validateUsername(username)) {
        console.log("Please enter a valid username (at least 3 characters).");
        Alert.alert('Error', 'Please enter a valid username (at least 3 characters).');
        
        return;
      }

      if (!validatePassword(password)) {
        console.log("Please enter a valid password (at least 6 characters).");
        Alert.alert('Error', 'Please enter a valid password (at least 6 characters).');
        
        return;
      }

      if (!validateBalance(balance)) {
        console.log("Please enter a valid balance (non-negative numeric value).");
        Alert.alert('Error', 'Please enter a valid balance (non-negative numeric value).');
        
        return;
      }

      // Send registration request
      const response = await axios.post('http://localhost:8000/auth/register', {
        username,
        email,
        password,
        balance: parseFloat(balance),
        role,
      });

    //   const token = response.data.token;

    //   // Save the token to AsyncStorage
    //   setToken(token);

      // Redirect to the Home page (or any other authenticated page)
      navigation.navigate('Login');
    } catch (error) {
      console.error('Registration failed:', error.message);
      Alert.alert('Error', 'Registration failed. Please try again.');
    }
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>User Registration</Text>
      <TextInput
        style={styles.input}
        placeholder="Email"
        value={email}
        onChangeText={(text) => setEmail(text)}
        keyboardType="email-address"
      />
      <TextInput
        style={styles.input}
        placeholder="Username"
        value={username}
        onChangeText={(text) => setUsername(text)}
      />
      <TextInput
        style={styles.input}
        placeholder="Password"
        value={password}
        onChangeText={(text) => setPassword(text)}
        secureTextEntry
      />
      <TextInput
        style={styles.input}
        placeholder="Balance"
        value={balance}
        onChangeText={(text) => setBalance(text)}
        keyboardType="numeric"
      />
      <TextInput
        style={styles.input}
        placeholder="Role"
        value={role}
        onChangeText={(text) => setRole(text)}
      />
      <TouchableOpacity style={styles.button} onPress={handleRegister}>
        <Text style={styles.buttonText}>Register</Text>
      </TouchableOpacity>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  title: {
    color: 'white',
    fontSize: 24,
    marginBottom: 20,
  },
  input: {
    height: 40,
    width: '80%',
    borderColor: 'gray',
    borderWidth: 1,
    marginBottom: 20,
    paddingLeft: 10,
    backgroundColor: '#f8f8f8', // Set a background color for better contrast
    color: '#333', // Set text color for better visibility
  },
  button: {
    backgroundColor: 'blue',
    padding: 10,
    borderRadius: 8,
  },
  buttonText: {
    color: 'white',
    fontSize: 16,
  },
});

export default Register;