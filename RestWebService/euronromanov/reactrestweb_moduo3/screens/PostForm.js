import { Text, SafeAreaView, StyleSheet, View,Alert } from 'react-native';
import { Input, Button } from '@rneui/base';
import { useState } from 'react'
import {getAllPostsService,
createPostService,createTypeDocumentService } from "../services/TestServices";

export const PostForm=({navigation})=>{
    const [subject, setSubject] = useState("");
    const [message, setMessage] = useState("");
     const [codigo, setCodigo] = useState("");
    const [description, setDescription] = useState("");

     const createPost=()=>{
       console.log("caja texto 1="+subject+" ,caja texto 2="+message)
    createPostService ({title:subject,body:message},()=>{Alert.alert("CONFIRMACION","Se a ingresado un nuevo POST")});
  }
   const createDocumentType=()=>{
       console.log(codigo+" "+description)
    createTypeDocumentService({codigo:""+codigo,descripcion:""+description},()=>{Alert.alert("CONFIRMACION","Se a ingresado un nuevo Tipo de documento")});
  }
    return (
        <View style={styles.container}>
        <Input
          value={codigo}
          onChangeText={setCodigo}
          placeholder="codigo del documento"
          label="codigo"
          
        />
        <Input
          value={description}
          onChangeText={setDescription}
          placeholder="descripcion de documento"
          label="descripción"
        />
        <Button
          title="Guardar"
          buttonStyle={styles.saveButton}
          onPress={createDocumentType}
        />
        <Button
          title="Test Web Services"
          buttonStyle={styles.saveButton}
           onPress={()=>{
        navigation.navigate("TestWebServicesNav")
      }}
        />
      </View>
 
    );
}
const styles = StyleSheet.create({
    container: {
      flex: 1,
      alignItems: 'center',
      justifyContent: 'center',
      backgroundColor: '#ecf0f1',
      padding: 8,
    },
    saveButton: {
      backgroundColor: 'green',
      marginTop:6
    },
  });