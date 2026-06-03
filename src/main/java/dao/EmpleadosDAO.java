/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Empleado;

/**
 *
 * @author oscar
 */
public class EmpleadosDAO {
   public boolean  agregarEmpleado(Empleado empleado){
       String sql = "INSERT INTO empleados (num_empleado, nombre, edad, telefono, pregunta, respuesta)VALUES (?, ?, ?, ?, ?, ?)";
       
       try(Connection con = Conexion.getConexion();
          PreparedStatement statement = con.prepareStatement(sql)){
           
           statement.setInt(1, empleado.getNumEmpleado());
           statement.setString(2, empleado.getNombreEmp());
           statement.setInt(3, empleado.getEdad());
           statement.setString(4, empleado.getNumTelefono());
           statement.setString(5, empleado.getPregunta());
           statement.setString(6, empleado.getResp());
           
           return statement.executeUpdate() > 0;

       }catch (SQLException e){ 
           
           System.out.println("Error al agregar el empleado: "+ e.getMessage());
           return false;
       }  
   }// fin de agregarEmpleado
       
    public List<Empleado> obtenerTodosEmpleados(){
        
        List<Empleado> empleados = new ArrayList<>();
        String sql="SELECT id_empleado, num_empleado, nombre, edad, telefono, pregunta, respuesta FROM empleados";
        
        try (Connection con = Conexion.getConexion();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){
            
            while (rs.next()) {
                
                Empleado e = new Empleado(
                     rs.getInt("num_empleado"),
                     rs.getString("nombre"),
                     rs.getInt("edad"),
                     rs.getString("telefono"),
                     rs.getString("pregunta"),
                     rs.getString("respuesta")
                );
                
                // e.setIdEmpleado(rs.getInt("id_empleado"));
                e.setNumEmpleado(rs.getInt("num_empleado"));
                
                empleados.add(e);
            }
        }catch(SQLException e){
               System.out.println("Error: " + e.getMessage());     
                    }
        return empleados;
    }//Fin de obtener todosEmpleados
    
    public boolean actualizarEmpleado(Empleado empleado){
        String sql =" UPDATE empleados SET num_empleado = ? , nombre = ? , edad = ? , telefono = ? , pregunta = ? , respuesta = ? WHERE id_empleado = ?";
    
        try(Connection con = Conexion.getConexion();
            PreparedStatement statement = con.prepareStatement(sql)){
        
            statement.setInt(1, empleado.getNumEmpleado());
            statement.setString(2, empleado.getNombreEmp());
            statement.setInt(3, empleado.getEdad());
            statement.setString(4, empleado.getNumTelefono());
            statement.setString(5, empleado.getPregunta());
            statement.setString(6, empleado.getResp());
            statement.setInt(7, empleado.getIdEmpleado());
            return statement.executeUpdate()>0;
        }catch (SQLException e) {
            
            System.out.println("Error al actualizar empleado " + empleado.getNombreEmp() + ": " + e.getMessage());
            return false;
        }
    
    }// fin de ActualizarEmpleado
     public boolean eliminarEmpleado(int idEmpleado){
     
         String sql = "DELETE FROM empleados WHERE id_empleado = ?";

         try (Connection con = Conexion.getConexion();
            PreparedStatement statement = con.prepareStatement(sql)){
            
            statement.setInt(1, idEmpleado);
             return statement.executeUpdate() > 0;
     }catch (SQLException e) {
            
            System.out.println("Error al eliminar Producto: " + e.getMessage());
            return false;
     }
         
    }// fin eliminarEmpleado
   
}