import React, { useState } from 'react';
import { View, Button, Modal } from 'react-native';
import { ColorPicker, fromHsv } from 'react-native-color-picker';

const CustomColorPicker = ({ visible, onClose, onSelectColor }) => {
  const [selectedColor, setSelectedColor] = useState('#000000');

  const handleColorChange = (color) => {
    setSelectedColor(color);
  };

  const handleSelectColor = () => {
    onSelectColor(selectedColor);
    onClose();
  };

  return (
    <Modal transparent visible={visible} onRequestClose={onClose}>
      <View
        style={{
          flex: 1,
          justifyContent: 'center',
          alignItems: 'center',
          backgroundColor: 'rgba(0, 0, 0, 0.5)',
        }}
      >
        <View
          style={{
            padding: 24,
            width: '80%',
            justifyContent: 'center',
            alignItems: 'center',
            backgroundColor: 'white',
            borderRadius: 12,
          }}
        >
          <ColorPicker
            color={selectedColor}
            onColorChange={handleColorChange}
            style={{ width: '100%', height: 300 }}
          />
          <Button title="Select" onPress={handleSelectColor} />
          <Button title="Cancel" onPress={onClose} />
        </View>
      </View>
    </Modal>
  );
};

export default CustomColorPicker;
