/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import dao.ProductosDAO;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.MisExcepcionesBar.CargarProductoException;

/**
 *
 * @author oscar
 */
public class Inventario {
    
    private static Inventario instancia;
    private ObservableList<Producto> productos;
    
    // ProductosDAO
    private ProductosDAO productosDao;
    
    // 
    private Inventario() {
        
        productos = FXCollections.observableArrayList();
        productosDao = new ProductosDAO();
        
        try {
            
            cargarProductosBD();
            
        } catch (CargarProductoException e) {
            System.out.println("Error al incializar inventario: " + e.getMessage());
        }
        
    } // Fin Inventario
    
    // Crea la instancia de la clase Inventario
    public static Inventario getInstancia() {
        
        if (instancia == null) {
            instancia = new Inventario();
        }
        
        return instancia;
    } // Fin getInstancia
    
    // 
    public ObservableList<Producto> getProductos() {
        return productos;
    }
    
    // Carga los datos desde la BD
    public void cargarProductosBD() throws CargarProductoException {
        
        try {
        
            productos.clear();
            
            List<Producto> lista = productosDao.obtenerTodosProductos();
            
            productos.addAll(lista);
           
        }catch (Exception e) {
            throw new CargarProductoException("Error al cargar productos de la base de datos: " + e.getMessage(), e);
        }

    } // Fin CargarDatos
    
    // Agregar Producto
    public boolean agregarProducto(Producto producto) {
        
        boolean agregado = productosDao.agregarProducto(producto);
        
        if (agregado) {
            productos.add(producto);
        }
        
        return agregado;
        
    } // Fin agregarProducto
    
    public boolean actualizarProducto(Producto producto) {
        
        return productosDao.actualizarProducto(producto);
    }
        
    public boolean eliminarProducto(Producto producto) {
        
        boolean eliminado = productosDao.eliminarProducto(producto.getIdProducto());
        
        if (eliminado) {
            
            productos.remove(producto);
            
        }
        
        return eliminado;
        
    } // Fin eliminarProducto
    
    
    
}
