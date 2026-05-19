/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author oscar
 */
public class RegistroVenta {
    
    private static RegistroVenta instancia;
    
    private ObservableList<Venta> ventas;
    
    private final Path rutaArchivo = Paths.get("src/main/resources/ventas.txt");
    
    private RegistroVenta() {
        ventas = FXCollections.observableArrayList();
    }
    
    public static RegistroVenta getInstancia() {
        if (instancia == null) {
            instancia = new RegistroVenta();
        }
        return instancia;
    }
    
    public ObservableList<Venta> getVentas() {
        return ventas;
    }
    
    // Cargar Ventas
    public void cargarVentasTxt() {
        
        ventas.clear();
        
        try (BufferedReader lector = Files.newBufferedReader(rutaArchivo)) {
            
            String linea;
            
            while ((linea = lector.readLine()) != null) {
                
                String[] datos = linea.split(",");
                
                if (datos.length == 6) {
                    
                    int mesa = Integer.parseInt(datos[0].trim());
                    String empleado = datos[1].trim();
                    int numEmpleado = Integer.parseInt(datos[2].trim());
                    float total = Float.parseFloat(datos[3].trim());
                    String tipoPago = datos[4].trim();
                    String fecha = datos[5].trim();
                    
                    ventas.add(new Venta(mesa, empleado, numEmpleado, total, tipoPago, fecha));
                }
            }
            
        } catch (Exception e) {
            
            System.out.println("Error al cargar ventas: " + e.getMessage());
            
        }
    } // fin cargarVentasTxt
    
    // Guardar ventas
    public void guardarVentasTxt() {
        
        try (BufferedWriter escritor = Files.newBufferedWriter(rutaArchivo)) {
            
            for (Venta v: ventas) {
                escritor.write(
                    v.getMesa() + "," +
                    v.getEmpleado() + "," +
                    v.getNumEmpleado() + "," +
                    v.getTotal() + "," +
                    v.getTipoPago() + "," +
                    v.getFecha()
                );
                escritor.newLine();
            }
            
        } catch (Exception e) {
            
            System.out.println("Error al guardar ventas:" + e.getMessage());
        
        }
    } // Fin guardarVentasTxt
    
    // Agregar Venta
    public void agregarVenta(Venta venta) {
        ventas.add(venta);
        guardarVentasTxt();
    }
    
    
}
