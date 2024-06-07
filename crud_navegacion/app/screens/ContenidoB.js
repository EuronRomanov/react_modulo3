import { Text, SafeAreaView, StyleSheet,View,Button } from 'react-native';

export const ContenidoB=()=> {
  return (
    <View style={styles.container}>
      <Text>Contenido B </Text>
      
    </View>
  );
}
const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    backgroundColor: '#ecf0f1',
    padding: 8,
  }
});