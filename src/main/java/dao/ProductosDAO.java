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
import modelo.Producto;
import modelo.TipoProducto;

/**
 *
 * @author oscar
 */
public class ProductosDAO {
    
    public boolean agregarProducto(Producto producto) {
        String sql = "INSERT INTO productos (nom_prod, tipo_id, precio, cantidad) VALUES (?, ?, ?, ?)";
        
        try (Connection con = Conexion.getConexion();
            PreparedStatement statement = con.prepareStatement(sql)){
            
            statement.setString(1, producto.getNombre());
            statement.setInt(2, producto.getTipoId());
            statement.setDouble(3, producto.getPrecio());
            statement.setInt(4, producto.getCantidad());
            
            return statement.executeUpdate() > 0;
            
        } catch (SQLException e) {
            
            System.out.println("Error al agregar producto " + e.getMessage());
            return false;
            
        }
    } // Fin agregarProducto
    
    public List<Producto> obtenerTodosProductos(){
        
        List<Producto> productos = new ArrayList<>();
        
        String sql = "SELECT id_producto, nom_prod, tipo_id, precio, cantidad FROM productos";
        
        try (Connection con = Conexion.getConexion();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){
            
            while (rs.next()) {
                
                Producto p = new Producto(
                   rs.getInt("id_producto"),
                   rs.getString("nom_prod"),
                   rs.getInt("tipo_id"),
                   rs.getDouble("precio"),
                   rs.getInt("cantidad")
                );
                                
                productos.add(p);
            }
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        return productos;
    } // Fin obtenerTodosProductos
    
    public boolean actualizarProducto(Producto producto) {
        String sql = "UPDATE productos SET nom_prod = ?, tipo_id = ?, precio = ?, cantidad = ? WHERE id_producto = ?";
        
        try (Connection con = Conexion.getConexion();
            PreparedStatement statement = con.prepareStatement(sql)){
            
            statement.setString(1, producto.getNombre());
            statement.setInt(2, producto.getTipoId());
            statement.setDouble(3, producto.getPrecio());
            statement.setInt(4, producto.getCantidad());
            statement.setInt(5, producto.getIdProducto());
            
            return statement.executeUpdate()> 0;
            
        } catch (SQLException e) {
            
            System.out.println("Error al actualizar producto " + producto.getNombre() + ": " + e.getMessage());
            return false;
        }
            
    } // Fin actualizarProducto
    
    public boolean eliminarProducto(int idProducto) {
        
        String sql = "DELETE FROM productos WHERE id_producto = ?";
        
        try (Connection con = Conexion.getConexion();
            PreparedStatement statement = con.prepareStatement(sql)){
            
            statement.setInt(1, idProducto);
            return statement.executeUpdate() > 0;
            
        } catch (SQLException e) {
            
            System.out.println("Error al eliminar Producto: " + e.getMessage());
            return false;
            
        }
        
    } // Fin eliminarProducto

    public Producto buscarProducto(int idProducto) {
        
        String sql = "SELECT id_producto, nom_prod, tipo_id, precio, cantidad FROM productos WHERE id_producto = ?";
        
        try (Connection con = Conexion.getConexion();
            PreparedStatement statement = con.prepareStatement(sql)){
            
            statement.setInt(1, idProducto);
            
            try (ResultSet rs = statement.executeQuery()){
                
                if (rs.next()) {
                    Producto producto = new Producto (
                        rs.getInt("id_producto"),
                        rs.getString("nom_prod"),
                        rs.getInt("tipo_id"),
                        rs.getDouble("precio"),
                        rs.getInt("cantidad")
                    );
                                        
                    return producto;
                } // Fin if
                
            } catch (SQLException e) {
                
                System.out.println("Error en buscarProducto: " + e.getMessage());
                e.printStackTrace();
                
            }
            
        } catch (SQLException e) {
            
            System.out.println("Error en la conexion: " + e.getMessage());
            
        }
        
        return null;
    
    } // buscarProducto
    
    public List<TipoProducto> obtenerTiposProducto() {
        
        List<TipoProducto> lista = new ArrayList<>();
        
        String sql = "SELECT id_tipo, nom_tipo, estado FROM tipos";
        
        try (Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            
            while(rs.next()) {
                
                lista.add(
                    new TipoProducto(
                       rs.getInt("id_tipo"),
                       rs.getString("nom_tipo")
                    )
                );
            }
            
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return lista;
    } // Fin List
    
}
