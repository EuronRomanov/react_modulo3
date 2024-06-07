import { Text, SafeAreaView, StyleSheet,View,FlatList,TouchableHighlight } from 'react-native';
import {getGrades} from '../services/GradeServices';
import { FAB,ListItem,Avatar} from '@rneui/base';
import { useState } from 'react';

export const ListForm=({navigation})=> {
    let [time,setTime]=useState()

    const refreshList=()=>{
      setTime(new Date().getTime());
    }
  let ItemGrade=({nota})=> {
    return (
      <TouchableHighlight onPress={()=>{

        navigation.navigate("GradeFormNav",{notita:nota,fnRefresh:refreshList});
      }}>
      <ListItem bottomDivider>
      <Avatar
      title={nota.subject.substring(0,1)} 
      containerStyle={{backgroundColor: "#6733b9"}}
      rounded
      />
      <ListItem.Content>
      <ListItem.Title>{nota.subject}</ListItem.Title>
       </ListItem.Content>
       <ListItem.Content>
      <ListItem.Title>{nota.grade}</ListItem.Title>
       </ListItem.Content>
       <ListItem.Chevron />
      </ListItem>
      </TouchableHighlight >
    );
  }
  return (
    <View style={styles.container}>
      <FlatList 
      data={getGrades()}
      renderItem={({item})=>{
        return <ItemGrade nota={item} />
      }
      
      }
      keyExtractor={(item)=>{ return item.subject;}}
      extraData={time}
      />
      <FAB 
      title="+"
      placement="right"
      onPress={()=>{
        navigation.navigate("GradeFormNav",{notita:null,fnRefresh:refreshList})
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