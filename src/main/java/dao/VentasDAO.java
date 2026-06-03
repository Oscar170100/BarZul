/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.Venta;
import modelo.Producto;

/**
 *
 * @author oscar
 */
public class VentasDAO {
    
    public int registrarVenta(Venta venta) {
        
        String sql = "INSERT INTO ventas (mesa_id, total, empleado_id, tipo_pago) VALUES (?, ?, ?, ?) RETURNING id_venta";
        
        try (Connection con = Conexion.getConexion();
            PreparedStatement statement = con.prepareStatement(sql)) {
            
            statement.setInt(1, venta.getMesaId());
            statement.setDouble(2, venta.getTotal());
            statement.setInt(3, venta.getEmpleadoId());
            statement.setString(4, venta.getTipoPago());
            
            try(ResultSet rs = statement.executeQuery()) {
                
                if (rs.next()) {
                    return rs.getInt("id_venta");
                }
                
            }
            
        } catch (SQLException e) {
            
            System.out.println("Error al registrar venta: " + e.getMessage());
            
        }
        
        return -1;
        
    } // Fin registrarVenta
    
    public boolean registrarDetalleVenta(int idVenta, ObservableList<Producto> productos) {
        
        String sql = "INSERT INTO detalle_venta (venta_id, producto_id, cantidad, subtotal) VALUES (?, ?, ?, ?)";
        
        try (Connection con = Conexion.getConexion();
            PreparedStatement statement = con.prepareStatement(sql)){
            
            for (Producto p: productos) {
                
                statement.setInt(1, idVenta);
                statement.setInt(2, p.getIdProducto());
                statement.setInt(3, p.getCantidad());
                
                statement.setDouble(4, p.getCantidad() * p.getPrecio());
                
                statement.addBatch();
                
            }
            
            statement.executeBatch();
            
            return true;

        } catch (SQLException e) {
            
            System.out.println("Error al registrar el detalle: " + e.getMessage());
            e.printStackTrace();
        
            return false;
        }
        
    } // Fin registrarDetalleVenta
    
    public ObservableList <Venta> obtenerVentas() {
        
        ObservableList <Venta> ventas = FXCollections.observableArrayList();
        
        String sql = 
                "SELECT v.id_venta, " +
                    "v.mesa_id, " +
                    "v.empleado_id, " + 
                    "e.nombre, " +
                    "e.num_empleado, " +
                    "v.total, " +
                    "v.tipo_pago, " +
                    "v.fecha " +
                "FROM ventas v " +
                "INNER JOIN empleados e ON v.empleado_id = e.id_empleado " +
                "ORDER BY v.fecha DESC";
        
        try (Connection con = Conexion.getConexion();
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery()){
            
            while (rs.next()) {
                
                Venta venta = new Venta(
                    rs.getInt("mesa_id"),
                    rs.getInt("empleado_id"),
                    rs.getDouble("total"),
                    rs.getString("tipo_pago")
                );
                
                venta.setIdVenta(rs.getInt("id_venta"));
                venta.setNombreEmpleado(rs.getString("nombre"));
                venta.setNumEmpleado(rs.getInt("num_empleado"));
                venta.setFecha(rs.getTimestamp("fecha").toString());
                
                ventas.add(venta);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        
        return ventas;
    }
    
}
