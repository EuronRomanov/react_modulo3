import { Text, SafeAreaView, StyleSheet, View } from 'react-native';
import { Input, Button } from '@rneui/base';
import { useState } from 'react';
import { saveGrade, updateGrade } from '../services/ProductosSrv';

export const ProductoForm = ({ navigation, route }) => {
  let isNew = true;
  let subjectR=null;
  let gradeR=null;

  if (route.params.notita != null) {
    isNew = false;
  }

  if (!isNew) {
    subjectR=route.params.notita.subject;
    gradeR=route.params.notita.grade;
  }

  let [subject, setSubject] = useState(subjectR);
  let [grade, setGrade] = useState(gradeR == null ? null : gradeR + '');
  let [errorSubject, setErrorSubject] = useState();
  let [errorGrade, setErrorGrade] = useState();
hasErrors= false;

  const save = () => {
    setErrorSubject(null);
    setErrorGrade(null);
    validate();
      
    if (!hasErrors) {
    
      if (isNew) {
        saveGrade({ subject: subject, grade: grade });
      } else{
        updateGrade({ subject: subject, grade: grade });
      }
       hasErrors=false;
       
      navigation.goBack();
      route.params.fnRefresh();
    }
  };
  const validate = () => {
    
    if (subject===null ) {
       
      setErrorSubject('Debe ingresar nombre');
      hasErrors=true;
     
    }
    
    let gradeFloat = parseFloat(grade);
    if (
      gradeFloat==null ||
      isNaN(gradeFloat) ||
      gradeFloat < 0 ||
      gradeFloat > 10
    ) {
       
      setErrorGrade('Debe ingresar uma nota entre 0 y 10');
      hasErrors=true;
    }
  };
  return (
    <View style={styles.container}>
      <Input
        value={subject}
        onChangeText={setSubject}
        placeholder="ejemplo producto"
        label="Nombre"
        errorMessage={errorSubject}
        disabled={!isNew}
      />
      <Input
        value={grade}
        onChangeText={setGrade}
        placeholder="0.0"
        label="Precio"
        errorMessage={errorGrade}
      />
      <Button
        title="Guardar"
        icon={{
          name: 'save',
          type: 'entypo',
          color: 'white',
        }}
        buttonStyle={styles.saveButton}
        onPress={save}
      />
    </View>
  );
};
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
  },
});
