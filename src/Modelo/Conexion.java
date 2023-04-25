/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LAPTOP
 */
public class Conexion {
    private final String url="jdbc:mysql://107.180.2.89:3306/pruebatec"; //final= no va a cambiar
    PreparedStatement ps;
    Connection con;
   // Connection con=null;

    public Conexion() {
        try {
            //Cargamos el driver de la librería
            Class.forName("com.mysql.jdbc.Driver");
            //Cargamos los parametros de conexión
            con=DriverManager.getConnection(url,"pruebatec","Tecnica2@");
              //si la conexión es exitosa es diferente de null
            if (con!=null){
                System.out.println("la conexion funciona");
            }
        } catch (ClassNotFoundException ex) {
             //excepción por si no encuentra el driver
            System.out.println("problemas en el driver"+ex);
        } catch (SQLException ex){
            //excepción por si no coinciden las credenciales 
            System.out.println("problemas con el usuario o contraseña o nombre de la base de datos"+ex);
        }
    }
    //Método que devuelve la variable de conexión
    public Connection Conectado(){
        return this.con;
    }
     //Método que rompe la variable de conexión
    public void Desconectar(){
        Connection con=null;
        con=null;
        System.out.println("conexion base de datos se ha cerrado");
    }

}
