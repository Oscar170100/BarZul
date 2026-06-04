/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Usuario;

/**
 *
 * @author oscar
 */
public class UsuariosDAO {
    
    public Usuario login(int numEmpleado, String password) {
        
        Usuario usuario = null;
        
        String sql = "SELECT * FROM usuarios WHERE num_empleado = ? AND password = ?";
        
        try (Connection con = Conexion.getConexion();
            PreparedStatement statement = con.prepareStatement(sql)){
            
            statement.setInt(1, numEmpleado);
            statement.setString(2, password);
            
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                
                usuario = new Usuario();
                
                usuario.setId(rs.getInt("id"));
                usuario.setNumEmp(rs.getInt("num_empleado"));
                usuario.setRol(rs.getString("rol"));
                
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            
        }
        
        return usuario;
        
    } // Fin login
    
}
