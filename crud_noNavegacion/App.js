import { Text, SafeAreaView, StyleSheet } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
// You can import supported modules from npm
import { Card } from 'react-native-paper';

// or any files within the Snack

import {GradeForm} from './app/screens/GradeForm';
import {ListForm} from './app/screens/ListGrades';

const StackGrades = createNativeStackNavigator();
export default function App() {
  return (
    <NavigationContainer>
    <StackGrades.Navigator>
        <StackGrades.Screen name="ListGradesNav" component={ListForm} />
        <StackGrades.Screen name="GradeFormNav" component={GradeForm} />
      </StackGrades.Navigator>
     </NavigationContainer>
  );
}

