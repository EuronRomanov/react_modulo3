import { Text, SafeAreaView, StyleSheet,View,FlatList,TouchableHighlight } from 'react-native';
import {getProductos} from '../services/ProductosSrv';
import { FAB,ListItem,Avatar} from '@rneui/base';
import { useState } from 'react';

export const ListProducto=({navigation})=> {
    let [time,setTime]=useState()

    const refreshList=()=>{
      setTime(new Date().getTime());
    }
  let ItemGrade=({producto})=> {
    return (
      <TouchableHighlight onPress={()=>{

        navigation.navigate("ProductFormNav",{notita:producto,fnRefresh:refreshList});
      }}>
      <ListItem bottomDivider>
      
      <ListItem.Content>
      <ListItem.Title>{producto.nombre}</ListItem.Title>
       </ListItem.Content>
       <ListItem.Content>
      <ListItem.Title>{producto.precioVenta}</ListItem.Title>
       </ListItem.Content>
       <ListItem.Chevron />
      </ListItem>
      </TouchableHighlight >
    );
  }
  return (
    <View style={styles.container}>
      <FlatList 
      data={getProductos()}
      renderItem={({item})=>{
        return <ItemGrade producto={item} />
      }
      
      }
      keyExtractor={(item)=>{ return item.id;}}
      extraData={time}
      />
      <FAB 
      title="+"
      placement="right"
      onPress={()=>{
        navigation.navigate("ProductFormNav",{notita:null,fnRefresh:refreshList})
      }}
      />
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