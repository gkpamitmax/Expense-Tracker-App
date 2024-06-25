import React from 'react';
import { View, Text, StyleSheet,ScrollView } from 'react-native';

// import RandomPieChart from '../RandomChart/RandomPieChart';
import {
  LineChart,
  BarChart,
  PieChart,
  ProgressChart,
  ContributionGraph,
  StackedBarChart
} from "react-native-chart-kit";
import { Dimensions } from "react-native";
const screenWidth = Dimensions.get("window").width;

const dataProgress = {
  labels: ["Home", "Bike", "Travel"], // optional
  data: [0.4, 0.6, 0.8]
};
const datapie = [
  {
    name: "Transport",
    population: 21500000,
    color: "rgba(131, 167, 234, 1)",
    legendFontColor: "#7F7F7F",
    legendFontSize: 15
  },
  {
    name: "Food",
    population: 2800000,
    color: "#F00",
    legendFontColor: "#7F7F7F",
    legendFontSize: 15
  },
  {
    name: "Utils",
    population: 527612,
    color: "red",
    legendFontColor: "#7F7F7F",
    legendFontSize: 15
  },
  {
    name: "Health",
    population: 8538000,
    color: "#ffffff",
    legendFontColor: "#7F7F7F",
    legendFontSize: 15
  },
  {
    name: "Personal",
    population: 11920000,
    color: "rgb(0, 0, 255)",
    legendFontColor: "#7F7F7F",
    legendFontSize: 15
  },
  {
    name: "Entertainment",
    population: 1920000,
    color: "rgb(0, 0, 100)",
    legendFontColor: "#7F7F7F",
    legendFontSize: 15
  },
  {
    name: "Others",
    population: 4420000,
    color: "rgb(999, 0, 999)",
    legendFontColor: "#7F7F7F",
    legendFontSize: 15
  },
];
const chartConfig = {
  backgroundGradientFrom: "#1E2923",
  backgroundGradientTo: "#08130D",
  color: (opacity = 1) => `rgba(26, 255, 146, ${opacity})`
};




export const Reports = () => (
  
  <ScrollView style={styles.container}>
    <View>
  <Text style={styles.textline}>Monthly Expenses</Text>
  <LineChart
    data={{
      labels: ["January", "February", "March", "April", "May", "June"],
      datasets: [
        {
          data: [
            Math.random() * 100,
            Math.random() * 100,
            Math.random() * 100,
            Math.random() * 100,
            Math.random() * 100,
            Math.random() * 100
          ]
        }
      ]
    }}
    width={Dimensions.get("window").width-10} // from react-native
    height={220}
    yAxisLabel="$"
    yAxisSuffix="k"
    yAxisInterval={1} // optional, defaults to 1
    chartConfig={{
      backgroundColor: "#6A5ACD", // Purple color
      backgroundGradientFrom: "#483D8B", // Darker shade of purple
      backgroundGradientTo: "#9370DB", // Lighter shade of purple
      decimalPlaces: 2, // optional, defaults to 2dp
      color: (opacity = 1) => `rgba(255, 255, 255, ${opacity})`,
      labelColor: (opacity = 1) => `rgba(255, 255, 255, ${opacity})`,
      style: {
        borderRadius: 16
      },
      propsForDots: {
        r: "6",
        strokeWidth: "2",
        stroke: "#9370DB" // Use the same color as backgroundGradientTo for dots
      }
    }}
    bezier
    style={{
      marginVertical: 8,
      borderRadius: 16
    }}
  />
</View>

<View>
  <Text style={styles.textline}>Categorywise Expenses</Text>
  <BarChart
    data={{
      labels: ["Transport", "Food", "Utilitis", "Health", "Personal", "  Entertain","Others"],
      datasets: [
        {
          data: [
            Math.random() * 100,
            Math.random() * 100,
            Math.random() * 100,  
            Math.random() * 100,
            Math.random() * 100,
            Math.random() * 100
             
          ]
        }
      ]
    }}
    width={Dimensions.get("window").width-10} // from react-native
    height={220}
    yAxisLabel="$"
    yAxisSuffix="k"
    yAxisInterval={1} // optional, defaults to 1
    chartConfig={{
      backgroundColor: "black",
      backgroundGradientFrom: "#fb8c00",
      backgroundGradientTo: "#ffa726",
      decimalPlaces: 1, // optional, defaults to 2dp
      color: (opacity = 1) => `rgba(255, 255, 255, ${opacity})`,
      labelColor: (opacity = 1) => `rgba(255, 255, 255, ${opacity})`,
      style: {
        borderRadius: 16
      },
      propsForDots: {
        r: "",
        strokeWidth: "2",
        stroke: "#ffa726"
      }
    }}
    bezier
    style={{
      marginVertical: 8,
      borderRadius: 16
    }}
  />
  <View>
  <Text style={styles.textline}>Percentage Share of each category in Expenses</Text>
  <PieChart
  data={datapie}
  width={screenWidth}
  height={220}
  chartConfig={chartConfig}
  accessor={"population"}
  backgroundColor={"transparent"}
  paddingLeft={"15"}
  center={[5, 14]}
/>
<Text style={styles.textline}>Goals and their Savings</Text>

<ProgressChart
  data={dataProgress}
  width={screenWidth}
  height={220}
  strokeWidth={16}
  radius={32}
  chartConfig={chartConfig}
  hideLegend={false}
/>
  </View> 
</View>
  </ScrollView>
);

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'black', // Adjust the background color as needed
    padding : 5
  },
  expensesText: {
    color: 'white',
    fontSize: 20,
    marginBottom: 10,
  },
  textline:{
    color : 'white'
  }
});
