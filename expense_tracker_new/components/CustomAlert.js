import React from 'react';
import { Modal, Text, TouchableOpacity, View, StyleSheet } from 'react-native';

const CustomAlert = ({ visible, onClose, onConfirm }) => {
  return (
    <Modal transparent animationType="slide" visible={visible} onRequestClose={onClose}>
      <TouchableOpacity style={styles.overlay} onPress={onClose}>
        <View style={styles.alertContainer}>
          <Text style={[styles.title, styles.whiteText]}>Are you sure?</Text>
          <Text style={styles.whiteText}>This action cannot be undone.</Text>
          <View style={styles.buttonsContainer}>
            <TouchableOpacity style={styles.button} onPress={onClose}>
              <Text style={styles.cancelButton}>Cancel</Text>
            </TouchableOpacity>
            <TouchableOpacity style={styles.button} onPress={onConfirm}>
              <Text style={styles.eraseButton}>Erase data</Text>
            </TouchableOpacity>
          </View>
        </View>
      </TouchableOpacity>
    </Modal>
  );
};

const styles = StyleSheet.create({
  overlay: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'rgba(0, 0, 0, 0.5)',
  },
  alertContainer: {
    backgroundColor: '#383838',
    padding: 20,
    borderRadius: 10,
    width: 300,
    alignItems: 'center', // Center align items
  },
  title: {
    fontSize: 18,
    fontWeight: 'bold',
    marginBottom: 10,
  },
  whiteText: {
    color: 'white',
  },
  buttonsContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between', // Align items with space between
    marginTop: 10,
    width: '100%', // Take full width
  },
  button: {
    flex: 1, // Take equal space
  },
  cancelButton: {
    color: '#0987ed',
    textAlign: 'left', // Align text to the left
  },
  eraseButton: {
    color: 'red',
    textAlign: 'right', // Align text to the right
  },
});

export default CustomAlert;
