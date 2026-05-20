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
 * @author maris
 */
public class Registro {
    private static Registro instancia;
    private ObservableList<Empleado> empleados;
    
    // Crea una ruta del Archivo de texto
    private final Path rutaArchivo = Paths.get("src/main/resources/empleados.txt");
    
    private Registro() {
        empleados = FXCollections.observableArrayList();
    }
    
    // Crea la instancia de la clase Inventario
    public static Registro getInstancia() {
        
        if (instancia == null) {
            instancia = new Registro();
        }
        return instancia;
    } // Fin getInstancia
    
    public ObservableList<Empleado> getEmpleado() {
        return empleados;
    }
    
    // Carga los datos desde el Archivo .txt
    public void cargarEmpleadoTxt() {
        empleados.clear();
        
        try (BufferedReader lector = Files.newBufferedReader(rutaArchivo)){
            
            String linea;
            // Recorre el archivo de datos hasta encontrar un NULL
            while ((linea = lector.readLine()) != null) {
                String[] datos = linea.split(",");
                
                // Verifica que existan valores en el archivo para asignarlos
                if (datos.length == 6) {
                    String nombre = datos[0].trim();
                    int numEmpleado = Integer.parseInt(datos[1].trim());
                    int Edad= Integer.parseInt(datos[2].trim());
                    long NumTelefono = Long.parseLong(datos[3].trim());
                    String pregunta = datos[4].trim();
                    String resp = datos[5].trim();
    
                     // Crea y Agrega un nuevo objeto de tipo Empleado y le da los valores
                     empleados.add(new Empleado(nombre, numEmpleado, Edad, NumTelefono, pregunta, resp));
                }
            }
            
        } catch (Exception e) {
            // Imprime en pantalla el mensaje si ocurre algun error
            System.out.println("Error al cargar empleados: " + e.getMessage());
        }
    } // Fin CargarDatos
    
    // Guarda los Productos en el archivo de texto
    public void guardarempleadosTxt() {
        
        try (BufferedWriter escritor = Files.newBufferedWriter(rutaArchivo)){
            
            for (Empleado e: empleados) {
                escritor.write(
                    e.getNombreEmp() + "," +
                    e.getNumEmpleado() + "," +
                    e.getEdad() + "," +
                    e.getNumTelefono() + "," +
                    e.getPregunta() + "," +
                    e.getResp()
                );
                escritor.newLine();
            }
            
        } catch (Exception e) {
            System.out.println("Error al guardar empleado: " + e.getMessage());
        }
                
    } // Fin guardarEmpleados
    
    // Agregar Empleados
    public void agregarEmpleado(Empleado empleado) {
        
        empleados.add(empleado);
        guardarempleadosTxt();
        
    } // Fin agregarEmpleado
        
    public void eliminarEmpleado(Empleado empleado) {
        
        empleados.remove(empleado);
        guardarempleadosTxt();
        
    } // Fin eliminarEmpleado
    
}
    