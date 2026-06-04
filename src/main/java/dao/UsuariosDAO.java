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
import modelo.Usuario;

/**
 *
 * @author oscar
 */
public class UsuariosDAO {
    
    public Usuario login(int numEmpleado, String password) {
        
        Usuario usuario = null;
        
        String sql = 
                "SELECT u.*, e.nombre "
                + "FROM usuarios u "
                + "INNER JOIN empleados e ON u.num_empleado = e.num_empleado "
                + "WHERE u.num_empleado = ? AND u.password = ?";
        
        try (Connection con = Conexion.getConexion();
            PreparedStatement statement = con.prepareStatement(sql)){
            
            statement.setInt(1, numEmpleado);
            statement.setString(2, password);
            
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                
                usuario = new Usuario();
                
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setNumEmp(rs.getInt("num_empleado"));
                usuario.setRol(rs.getString("rol"));
                
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            
        }
        
        return usuario;
        
    } // Fin login
    
    public boolean insertarUsuario(Usuario usuario) {
        
        String sql = "INSERT INTO usuarios (num_empleado, password, rol) "
                    + "VALUES (?, ?, ?)";
        
        try (Connection con = Conexion.getConexion();
            PreparedStatement statement = con.prepareStatement(sql)){
            
            statement.setInt(1, usuario.getNumEmp());
            statement.setString(2, usuario.getPassword());
            statement.setString(3, usuario.getRol());
            
            return statement.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("Error al guardar usuario: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    } // Fin insertarUsuario
    
    public boolean actualizarNumEmp(int numAnterior, int numNuevo, String password) {
        
        String sql = "UPDATE usuarios SET num_empleado = ?, password = ? WHERE num_empleado = ?";

        try (Connection con = Conexion.getConexion();
            PreparedStatement statement = con.prepareStatement(sql)){
            
            statement.setInt(1, numNuevo);
            statement.setString(2, password);
            statement.setInt(3, numAnterior);
            
            return statement.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
            return false;
            
        }
        
    } // Fin actualizarUsuario
    
    public boolean eliminarUsuario(int numEmpleado) {
        
        String sql = "DELETE FROM usuarios WHERE num_empleado = ?";
        
        try (Connection con = Conexion.getConexion();
            PreparedStatement statement = con.prepareStatement(sql)){
            
            statement.setInt(1, numEmpleado);
            
            return statement.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("Error al eliminar Usuario: " + e.getMessage());
            e.printStackTrace();
            
        }
        
        return false;
    } // Fin eliminarUsuario
    
    public List<Usuario> obtemerTodosUsuarios() {
        
        List<Usuario> usuarios = new ArrayList<>();
        
        String sql = "SELECT * FROM usuarios";
        
        try (Connection con = Conexion.getConexion();
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery()){
            
            while(rs.next()) {
                
                Usuario usuario = new Usuario();
                
                usuario.setId(rs.getInt("id"));
                usuario.setNumEmp(rs.getInt("num_empleado"));
                usuario.setPassword(rs.getString("password"));
                usuario.setRol(rs.getString("rol"));
                
                usuarios.add(usuario);
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        
        return usuarios;
    }
    
}
