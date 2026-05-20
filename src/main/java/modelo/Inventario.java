/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    
    // Crea una ruta del Archivo de texto
    private final Path rutaArchivo = Paths.get("src/main/resources/productos.txt");
    
    private Inventario() {
        productos = FXCollections.observableArrayList();
    }
    
    // Crea la instancia de la clase Inventario
    public static Inventario getInstancia() {
        
        if (instancia == null) {
            instancia = new Inventario();
        }
        return instancia;
    } // Fin getInstancia
    
    public ObservableList<Producto> getProductos() {
        return productos;
    }
    
    // Carga los datos desde el Archivo .txt
    public void cargarProductosTxt() throws CargarProductoException {
        productos.clear();
        
        try (BufferedReader lector = Files.newBufferedReader(rutaArchivo)){
            
            String linea;
            int numeroLinea=0;
      
                // Recorre el archivo de datos hasta encontrar un NULL
                while ((linea = lector.readLine()) != null) {
                    String[] datos = linea.split(",");
                    
                    // Verifica que existan valores en el archivo para asignarlos
                    if (datos.length == 4) {
                        try{
                        String nombre = datos[0].trim();
                        String tipo = datos[1].trim();
                        float precio = Float.parseFloat(datos[2].trim());
                        int cantidad = Integer.parseInt(datos[3].trim());
                        
                        // Crea y Agrega un nuevo objeto de tipo Producto y le da los valores
                        productos.add(new Producto(nombre, tipo, precio, cantidad));
                    } catch (NumberFormatException e) {
                        throw new CargarProductoException("Error de formato en línea " + numeroLinea + ": " + e.getMessage(), e);
                    }
                } else {
                    throw new CargarProductoException("Línea " + numeroLinea + " tiene formato incorrecto. Se esperaban 4 campos.", null);
                }
            }
        } catch (CargarProductoException e) {
            throw e;
        } catch (Exception e) {
            throw new CargarProductoException("Error al leer el archivo de productos: " + e.getMessage(), e);
        }

    } // Fin CargarDatos
    
    // Guarda los Productos en el archivo de texto
    public void guardarProductosTxt() {
        
        try (BufferedWriter escritor = Files.newBufferedWriter(rutaArchivo)){
            
            for (Producto p: productos) {
                escritor.write(
                    p.getNombre() + "," +
                    p.getTipo() + "," +
                    p.getPrecio() + "," +
                    p.getCantidad()
                );
                escritor.newLine();
            }
            
        } catch (Exception e) {
            System.out.println("Error al guardar producos: " + e.getMessage());
        }
                
    } // Fin guardarProductos
    
    // Agregar Producto
    public void agregarProducto(Producto producto) {
        
        productos.add(producto);
        guardarProductosTxt();
        
    } // Fin agregarProducto
        
    public void eliminarProducto(Producto producto) {
        
        productos.remove(producto);
        guardarProductosTxt();
        
    } // Fin eliminarProducto
    
    
    
}
