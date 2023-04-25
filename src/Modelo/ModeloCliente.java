/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LAPTOP
 */
public class ModeloCliente extends Conexion{
   
    
     
    
   
    //Metodo que calcula el inventario valorado y lo devuelve
    //String instalacion, String codigo
    public float calcularInventarioValorado(String instalacion,String codigo){
            
            ResultSet rs=null;
            float totalInventarioValorado=0;
            //Hacemos la conexión a la bd
            Connection con=Conectado();
             //Consulta sql que calcula el total del inventario valorado
            String sql="SELECT sum(total) as total from (SELECT  (sum(inventario*costo_unitario)) as total FROM producto_ where producto_.Instalacion=? and producto_.producto=? group by producto_.bodega) as tabla;";
        try {
            ps=con.prepareStatement(sql);
              //Colocamos los parametros para la consulta
            ps.setString(1, instalacion);
            ps.setString(2, codigo);
             //ejecutamos
            rs=ps.executeQuery();
              //recorremos los resultados
            if (rs.next()) {
                   //recuperamos el resultado
                totalInventarioValorado=rs.getFloat(1);
                String valor="";    
                 //Devolvemos el valor
                return totalInventarioValorado;
               // int idDireccion=rs.getInt(5);
                //String correo=rs.getString(6);
               
            }
        } catch (SQLException ex) {
             //Excepcion por si hay algún problema
            Logger.getLogger(ModeloCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalInventarioValorado;    
}
     //Metodo que hace todos los calculos totales de a cuerdo a los valores de inventario
    //String codigo, int cantidadCompra,String instalacion,int precio
     public float calcularTodo(String codigo,int cantidadCompra,String instalacion,int precio){
            //Declaramos las variables que necesitamos
            ResultSet rs=null;
            int totalInventario;
            float costoUnitario=0;
            float totalInventarioValorado=0;
            float costoPromedio=0;
            //Hacemos la conexión a la bd
            Connection con=Conectado();
             //Consulta sql que calcula el total del inventario final
            String sql="SELECT  sum(inventario) as total,costo_unitario FROM producto_ where producto_.Instalacion=? and producto_.producto=?";
        try {
            ps=con.prepareStatement(sql);
             //Colocamos los parametros para la consulta
            ps.setString(1, instalacion);
            ps.setString(2, codigo);
             //Ejecutamos
            rs=ps.executeQuery();
               //recorremos los resultados
            if (rs.next()) {
                   //recuperamos los resultados
                totalInventario=rs.getInt(1);
                costoUnitario=rs.getFloat(2);
                 //llamamos a la función calcularInventarioValorado que devuelve el inv valorado
                totalInventarioValorado=calcularInventarioValorado(instalacion,codigo);
                 //Cálculo costo entrada
                float costoEntrada=cantidadCompra*precio; 
                //Cálculo inventario final
                int inventarioFinal=totalInventario+cantidadCompra;
                 //Cálculo costo final
                float costoFinal=costoEntrada+totalInventarioValorado;
                 //Cálculo costo promedio
                costoPromedio=costoFinal/inventarioFinal;
                //Devolvemos el costo promedio
                return costoPromedio;
               // int idDireccion=rs.getInt(5);
                //String correo=rs.getString(6);
               
            }
        } catch (SQLException ex) {
             //Excepcion por si hay algún problema
            Logger.getLogger(ModeloCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return costoPromedio;
    
}
     
     
      public String actualizarListaPrecios(float porcentaje,String listaprecios){
            
            ResultSet rs=null;
            int totalInventario;
            float costoUnitario=0;
            float totalInventarioValorado=0;
            float costoPromedio=0;
            Connection con=Conectado();
            String sql="UPDATE `pruebatec`.`lista_precios` SET precio = precio+(precio*?) WHERE lista_precios = ?;";
        try {
            ps=con.prepareStatement(sql);
            ps.setFloat(1, porcentaje);
            ps.setString(2, listaprecios);
            ps.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCliente.class.getName()).log(Level.SEVERE, null, ex);
            return ex.toString();
        }
        return "Datos actualizados";
    
}
}
