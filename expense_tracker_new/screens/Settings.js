import React,{useState} from 'react';
import { View} from 'react-native';
import { useNavigation } from '@react-navigation/native';
import Entypo from '@expo/vector-icons/Entypo';

import { ListItem } from '../components/ListItem';
import CustomAlert from '../components/CustomAlert';


export const Sett = () => {
  const navigation = useNavigation();
  const [isAlertVisible, setAlertVisible] = useState(false);

  const goToCategories = () => {
    // Navigate to the 'Categories' screen
    navigation.navigate('Categories');
  };
  const showAlert = () => {
    setAlertVisible(true);
  };
  
  const hideAlert = () => {
    setAlertVisible(false);
  };

  return (
    <View
      style={{
        margin: 16,
        borderRadius: 11,
        overflow: 'hidden',
      }}
    >
      <ListItem
        label='Categories'
        detail={
          <Entypo
            name='chevron-thin-right'
            color='white'
            style={{ opacity: 0.3 }}
            size={20}
          />
        }
        onClick={goToCategories} // Call the function to navigate when clicked
      />
      <ListItem
        isDestructive
        label='Erase all data'
        onClick={showAlert}
      />
      <CustomAlert
        visible={isAlertVisible}
        onClose={hideAlert}
        onConfirm={() => {
          // Handle erasing data here
          hideAlert();
        }}
      />
    </View>
  );
};