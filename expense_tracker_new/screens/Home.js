import React from 'react';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { Expenses } from './Expenses';
import { Reports } from './Reports';
import { Add } from './Add';
import { Sett } from './Settings';
import { Ionicons } from '@expo/vector-icons';
import { AddExpense } from './AddExpense';
import { AddGoals } from './AddGoals';

const Tab = createBottomTabNavigator();


export const Home = () => (
  <Tab.Navigator
        screenOptions={({ route }) => ({
          tabBarHideOnKeyboard: true,
          tabBarIcon: ({ focused, color, size }) => {
            let iconName;

            if (route.name === 'Expenses') {
              iconName = focused ? 'ios-wallet' : 'ios-wallet-outline';
            } else if (route.name === 'Reports') {
              iconName = focused ? 'ios-analytics' : 'ios-analytics-outline';
            } else if (route.name === 'Add Expense') {
              iconName = focused ? 'ios-add-circle' : 'ios-add-circle-outline';
            } else if (route.name === 'Settings') {
              iconName = focused ? 'ios-settings' : 'ios-settings-outline';
            }else if (route.name === 'Add Goal') {
              iconName = focused ? 'ios-time' : 'ios-time-outline';
            }

            return <Ionicons name={iconName} size={size} color={color} />;
          },
        })}
        tabBarOptions={{
          activeTintColor: 'purple',
          inactiveTintColor: 'gray',
          style: {
            display: 'flex',
          },
        }}
      >
        <Tab.Screen name="Expenses" component={Expenses} />
        <Tab.Screen name="Reports" component={Reports} />
        <Tab.Screen name="Add Expense" component={AddExpense} />
        <Tab.Screen name="Add Goal" component={AddGoals} />
        <Tab.Screen name="Settings" component={Sett} />
      </Tab.Navigator> 
);


