import "react-native-gesture-handler"
import { Text, SafeAreaView, StyleSheet } from 'react-native';

import {ListProducto} from './app/screens/ListaProductos';
import {ProductoForm} from './app/screens/ProductoForm';
import {LoginForm} from './app/screens/Login';
import {RegistroForm} from './app/screens/RegistroForm';
import {CambioClaveForm} from './app/screens/CambioClave';
import {ContenidoA} from './app/screens/ContenidoA';
import {ContenidoB} from './app/screens/ContenidoB';

// You can import supported modules from npm
import { Card } from 'react-native-paper';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { createDrawerNavigator } from '@react-navigation/drawer';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { Input, Button,Icon } from '@rneui/base';
// or any files within the Snack

const StackProductos = createNativeStackNavigator();
const LoginStack = createNativeStackNavigator();
const Drawer = createDrawerNavigator();
const Tab = createBottomTabNavigator();


const TabNav=()=> {
   return (
    
      <Tab.Navigator>
        <Tab.Screen name="TabContenidoA" component={ContenidoA}  options={{   headerShown:false,
        tabBarLabel:"Configracion",
        tabBarIcon:()=>{return <Icon name="user" size={20} color="black" type="ant-design"/> } 
         }}
        
        />
        <Tab.Screen name="TabContenidoB" component={ContenidoB} options={{headerShown:false,
        tabBarLabel:"Acerca de",
        tabBarIcon:()=>{return <Icon name="mail" size={20} color="black" type="ant-design"/> } 
         }} />
      </Tab.Navigator>
    
  );
}




const LoginNav=()=> {
  return (
    
    <LoginStack.Navigator>
        <LoginStack.Screen name="LoginNavigation" component={LoginForm} options={{headerShown:false }} />
        <LoginStack.Screen name="RegistroNavigation" component={RegistroForm} options={{headerShown:false }}/>
        <LoginStack.Screen name="CambioClaveNavigation" component={CambioClaveForm} options={{headerShown:false }}/>
      </LoginStack.Navigator>
     
  );
}


const ProductNav=()=> {
  return (
    
    <StackProductos.Navigator>
        <StackProductos.Screen name="ListProductNav" component={ListProducto} />
        <StackProductos.Screen name="ProductFormNav" component={ProductoForm} />
      </StackProductos.Navigator>
     
  );
}

const DrawerNav=()=> {
  return (
    
      <Drawer.Navigator >
        <Drawer.Screen name="DrawerProductosNav" component={ProductNav} options={{title:"Productos"}}/>
        <Drawer.Screen name="DrawerEjemplosTab" component={TabNav} options={{title:"Ejemmplo tabs"}}/>
        <Drawer.Screen name="DrawerFinSesion" component={ProductNav} options={{title:"Finalizar sesion"}}/>
      </Drawer.Navigator>
   
  );
}
export default function App() {
  let login=true;
  return (
     <NavigationContainer>
    {login?<DrawerNav />: <LoginNav />}
     </NavigationContainer>
  );
}


