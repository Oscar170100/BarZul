/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import dao.EmpleadosDAO;
import dao.UsuariosDAO;
import java.security.SecureRandom;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import modelo.MisExcepcionesBar.CargarProductoException;

/**
 *
 * @author maris
 */
public class Registro {
    private static Registro instancia;
    private ObservableList<Empleado> empleados;
    Alert alertConfirm = new Alert(Alert.AlertType.CONFIRMATION);
    // DAO
    private EmpleadosDAO empleadosDao;
    private UsuariosDAO usuariosDao;
    
    private Registro() {
    
        empleados = FXCollections.observableArrayList();
        
        empleadosDao = new EmpleadosDAO();
        usuariosDao = new UsuariosDAO();
        
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
        
            Usuario usuario = new Usuario();
            
            usuario.setNumEmp(empleado.getNumEmpleado());
            String passwordGenerado = generarPass();
            usuario.setPassword(passwordGenerado);
            usuario.setRol("EMPLEADO");
           
            boolean usuarioGuardado = usuariosDao.insertarUsuario(usuario);
            
            if(!usuarioGuardado) {
                System.out.println("No se pudo crear el usuario para el empleado " + empleado.getNumEmpleado());
            }
            
            alertConfirm.setTitle("Exito!");
            alertConfirm.setContentText(
                "Empleado Creado correctamente! \n" +
                "\nNumero de empleado: " + empleado.getNumEmpleado()
                + "\nContraseña: " + passwordGenerado
            );
            alertConfirm.showAndWait();
            
        } // Fin if(agregado)
        
        return agregado;
        
    } // Fin agregarEmpleado
    
    public boolean actualizarEmpleado(Empleado empleado, int numAnterior){
        
        Usuario usuario = new Usuario();
        
        // Guardando nuevo numero de empleado
        usuario.setNumEmp(empleado.getNumEmpleado());
        // Cambiar contraseña
        usuario.setPassword(empleado.getPassword());
        //usuario.setRol("EMPLEADO");
        
        usuariosDao.actualizarNumEmp(numAnterior, empleado.getNumEmpleado(), empleado.getPassword());
        
        return empleadosDao.actualizarEmpleado(empleado);
    }
        
    public boolean eliminarEmpleado(Empleado empleado) {
        System.out.println("Estas eliminado empleado");
        
        usuariosDao.eliminarUsuario(empleado.getNumEmpleado());
        boolean eliminado = empleadosDao.eliminarEmpleado(empleado.getIdEmpleado());
        
        if (eliminado) {
            
            empleados.remove(empleado);
        }
        
        return eliminado;
        
    } // Fin eliminarEmpleado
    
    // Genera un password aleatorio de 4 caracteres
    private String generarPass() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%&*";
        
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        
        for(int i = 0; i < 4; i++) {
            int index = random.nextInt(caracteres.length());
            password.append(caracteres.charAt(index));
        }
        return password.toString();
    }
    
}
    