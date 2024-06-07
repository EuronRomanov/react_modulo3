import { Text, SafeAreaView, StyleSheet,View } from 'react-native';
import { Button } from 'react-native-paper';
export const ContenidoA=()=> {
  return (
    <View style={styles.container}>
      <Text>Contenido A </Text>
      <Button icon="camera" mode="contained" onPress={() => console.log('Pressed')}>
    Press me
  </Button>
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