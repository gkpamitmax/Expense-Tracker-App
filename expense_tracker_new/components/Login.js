import React, { useState } from 'react';
import { View, Text, TextInput, TouchableOpacity, StyleSheet, Alert } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import { setToken } from "../store/AsyncStorage"; // Update the path
import axios from 'axios';
const Login = () => {
  const navigation = useNavigation();
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const handleLogin = async () => {
    try {
      const response = await axios.post('http://localhost:8000/auth/token', { username, password });
      const token = response.data;
      // Save the token to AsyncStorage
      console.log(token);
      setToken(token);
      // Redirect to the Home page (or any other authenticated page)
      navigation.navigate('Home');
    } catch (error) {
      console.error('Login failed:', error.message);
      Alert.alert('Error', 'Invalid username or password. Please try again.');
    }
  };
  const handleRegisterLinkPress = () => {
    // Navigate to the Register page
    navigation.navigate('Register');
  };
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Expense Tracker</Text>
      <TextInput
        style={styles.input}
        placeholder="Username"
        value={username}
        onChangeText={(text) => setUsername(text)}
      />
      <TextInput
        style={styles.input}
        placeholder="Password"
        secureTextEntry
        value={password}
        onChangeText={(text) => setPassword(text)}
      />
      <TouchableOpacity style={styles.button} onPress={handleLogin}>
        <Text style={styles.buttonText}>Login</Text>
      </TouchableOpacity>
      <Text style={styles.registerLink} onPress={handleRegisterLinkPress}>
        Don't have an account? Register here.
      </Text>
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
    backgroundColor: '#F2F2F2', // Set a background color for better contrast
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
  registerLink: {
    color: 'blue',
    fontSize: 14,
    textDecorationLine: 'underline',
  },
});
export default Login;