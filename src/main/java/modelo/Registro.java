/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import dao.EmpleadosDAO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.MisExcepcionesBar.CargarProductoException;

/**
 *
 * @author maris
 */
public class Registro {
    private static Registro instancia;
    private ObservableList<Empleado> empleados;
    
    // EmpleadosDAO
    private EmpleadosDAO empleadosDao;
    
    
    private Registro() {
        empleados = FXCollections.observableArrayList();
        empleadosDao = new EmpleadosDAO();
    
    try{
        
    
        cargarEmpleadoBD();
    }catch (CargarProductoException e){
    System.out.println("Error al incializar inventario: " + e.getMessage());
        }
    } // fin de Registro
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
    
    // Carga los datos desde la BD
    public void cargarEmpleadoBD() throws CargarProductoException{
        
        try {
             empleados.clear();
             
            List<Empleado> lista = empleadosDao.obtenerTodosEmpleados();
          
            empleados.addAll(lista);
        } catch (Exception e) {
           throw new CargarProductoException("Error al cargar empleados de la base de datos: " + e.getMessage(), e);
        }
    } // Fin CargarDatos
    
    // Agregar Empleados
    public boolean agregarEmpleado(Empleado empleado) {
      boolean agregado = empleadosDao.agregarEmpleado(empleado);
      
      if (agregado) {
            empleados.add(empleado);
        }
        
        return agregado;
        
    } // Fin agregarEmpleado
    public boolean actualizarEmpleado(Empleado empleado){
    
        return empleadosDao.actualizarEmpleado(empleado);
    }
        
   public boolean eliminarEmpleado(Empleado empleado) {
        
        boolean eliminado = empleadosDao.eliminarEmpleado(empleado.getIdEmpleado());
        
        if (eliminado) {
            
            empleados.remove(empleado);
            
        }
        
        return eliminado;
        
    } // Fin eliminarEmpleado
    
}
    