import React from 'react';
import { Text, StyleSheet, View, TouchableOpacity, Dimensions } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import { ListItem } from '../components/ListItem';
import { BarChart } from 'react-native-chart-kit';
import TransportationExpenses from './CategoryWiseExpenses/TransportationExpenses';

const categoriesData = [
  'Transportation',
  'Food',
  'Utilities',
  'Medical Healthcare',
  'Personal Spending',
  'Entertainment',
  'Others',
];

const categoryBulletStyles = {
  Transportation: { backgroundColor: 'maroon' },
  Food: { backgroundColor: '#779933' },
  Utilities: { backgroundColor: 'indigo' },
  'Medical Healthcare': { backgroundColor: 'purple' },
  'Personal Spending': { backgroundColor: '#DCD427' },
  Entertainment: { backgroundColor: '#0092CC' },
  Others: { backgroundColor: 'pink' },
};

export const Categories = () => {
  const navigation = useNavigation();

  const handleCategoryPress = () => {
    // Navigate to the respective category page
    // navigation.navigate('TransportationExpenses', { TransportationExpenses });
  };



  return (
    <View style={{ margin: 16, borderRadius: 11, overflow: 'hidden' }}>
      {categoriesData.map((category, index) => (
        <TouchableOpacity key={index.toString()} onPress={() => handleCategoryPress()}>
          <ListItem
            label={
              <>
                <View style={[styles.bulletPoint, categoryBulletStyles[category]]} />
                <Text> {category}</Text>
              </>
            }
            style={{
              padding: 12,
              borderBottomWidth: 1,
              borderBottomColor: '#ccc',
              flexDirection: 'row',
              alignItems: 'center',
            }}
          />
        </TouchableOpacity>
      ))}
    {/* <View>
        <Text>Spending by Category</Text>
        <BarChart
          data={chartData}
          width={Dimensions.get('window').width}
          height={220}
          yAxisLabel="$"
          yAxisSuffix="k"
          yAxisInterval={1}
          chartConfig={{
            backgroundColor: '#e26a00',
            backgroundGradientFrom: '#fb8c00',
            backgroundGradientTo: '#ffa726',
            decimalPlaces: 2,
            color: (opacity = 1) => `rgba(255, 255, 255, ${opacity})`,
            labelColor: (opacity = 1) => `rgba(255, 255, 255, ${opacity})`,
            style: {
              borderRadius: 16,
            },
            propsForDots: {
              r: '6',
              strokeWidth: '2',
              stroke: '#ffa726',
            },
          }}
          style={{
            marginVertical: 8,
            borderRadius: 16,
          }}
          fromZero={true}
          xLabels={categoriesData}
          xAxisLabelRotation={45}
        />
      </View> */}
    </View>
  );
};

const styles = StyleSheet.create({
  bulletPoint: {
    width: 15,
    height: 15,
    borderRadius: 10,
    marginRight: 10,
    borderWidth: 1,
    borderColor: 'white',
  },
});
