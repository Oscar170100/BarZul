/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oscar
 */

public class Conexion {
    
    private static final Logger LOGGER = Logger.getLogger(Conexion.class.getName());
    private static Connection conexion = null;
    
    // Configuracion 
    private static final String URL = "jdbc:postgresql://localhost:5432/bar_zully";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";
    private static final String DRIVER = "org.postgresql.Driver";
    
    public static Connection getConexion() {
        
        try {
            
            if (conexion == null || conexion.isClosed()) {
                // 1. Registrar el Driver
                Class.forName(DRIVER);
                
                // 2. Establecer Conexion
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);
                LOGGER.log(Level.INFO, "Conexion establecida exitosamente");
            }
            
        } catch (ClassNotFoundException e) {
            
            LOGGER.log(Level.SEVERE, "Error: No se encontró el driver JDBC", e);
            throw new RuntimeException("Driver JDBC no encontrado", e);
            
        } catch (SQLException e) {
            
            LOGGER.log(Level.SEVERE, "Error al conectar con la BD", e);
            throw new RuntimeException("Error al conectar con la BD", e);
            
        }
        
        return conexion;
    }
    
    public static void cerrarConexion() {
        
        try {
            
            if (conexion != null && !conexion.isClosed()) {
                
                conexion.close();
                LOGGER.log(Level.INFO, "Conexion cerrada");
            }
            
        } catch (SQLException e) {
            
            LOGGER.log(Level.SEVERE, "Error al cerrar conexion", e);
            
        }
    }
    
}
