/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uacm.barzul.bar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import modelo.Producto;

/**
 *
 * @author oscar
 */
public class ProductoController {
    
    // conectar productos entre pantallas
    private static ProductoController conexion;
    
    // Crear una lista de productos
    private ObservableList<Producto> listaProductos;
    
    // Constructor con el observablelist inicializado
    private ProductoController() {
        listaProductos = FXCollections.observableArrayList();
    }
    
    // Metodo para conectar los controladores con esta pantalla
    public static ProductoController getConexion() {
        // No hay conexion
        if (conexion == null) {
            conexion = new ProductoController();
        }
        
        // Existe conexion
        return conexion;
        
    }

    // Agrega Producto
    public void agregarProducto(Producto producto) {
        listaProductos.add(producto);
    }    
    
    // Actualiza producto
    public void actualizarProd(String nombre, String tipo, float precio, int cantidad) {
        
        // Lista de productos
        for (Producto producto : listaProductos) {
            
            // Si el nombre ingresado, coincide con el nombre del producto
            if (producto.getNombre().equals(nombre)) {
                
                producto.setNombre(nombre);
                producto.setTipo(tipo);
                producto.setCantidad(cantidad);
                producto.setPrecio(precio);
                
            } else {
                
                // Si no coincide ninguno
                Alert alertInfo = new Alert(Alert.AlertType.ERROR);
                alertInfo.setTitle("Error");
                alertInfo.setHeaderText("Error");
                alertInfo.setContentText("El producto no existe o no esta registrado");
                alertInfo.showAndWait();
                
            }
            
        } // For
        
    } // Actualizar
    
    // Mostrar Productos en pantalla
    public ObservableList<Producto> getListaProductos() {
        return listaProductos;
    }
        
    // Buscar por nombre
    public Producto prodBusqueda(String nombreProd) {
        
        for(Producto productos : listaProductos) {
            
            // Compara si algun producto contiene el nombre a buscar
            if (productos.getNombre().equals(nombreProd)) {
                return productos;
            }
            
        }
        // Si no encuentra nada, returna null
        return null;
    }
    
    // Elimina producto
    public void eliminaProducto(String nombreProd) {
        
        Producto producto = prodBusqueda(nombreProd);
        
        // si existe el producto, lo quita
        if (producto != null) {
            listaProductos.remove(nombreProd);
        }
        
    }
    
    //
    
    
    
    
}
