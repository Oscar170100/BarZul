/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author maris
 */
public class MisExcepcionesBar {
    // ecxcepcion par validar la edad 
        public static class EdadInvalidaException extends Exception {
        private int edadIngresada;
        
        public EdadInvalidaException(String mensaje, int edad) {
            super(mensaje);
            this.edadIngresada = edad;
        }
        
        public int getEdadIngresada() {
            return edadIngresada;
        }
    }
        // excepcion para validar el numero de telfono 
   
    public static class TelefonoInvalidoException extends Exception { 
         private String telefono;
    
        public TelefonoInvalidoException(String mensaje, String telefono) {
            super(mensaje);
            this.telefono= telefono;
        }
    
        public String getTelefonoIngresado() {
             return telefono;
        }
    }
    // excepcion para iniciar sesion
    
    public static class ContraseñaIncorrectaException extends Exception{
        private String contraseñaIngresada;
        
        public ContraseñaIncorrectaException (String mensaje, String contraseña){
            super(mensaje);
            this.contraseñaIngresada= contraseña;
        }
        public String getContraseñaIngresada(){
        return contraseñaIngresada;
        }
        
    }
    // excepcion para pagar con efectivo 
     public static class CantidadIncorrectaException extends Exception{
         private double cantidadIngresada;
         
         public CantidadIncorrectaException (String mensaje, double cantidad){
             super(mensaje);
             this.cantidadIngresada=cantidad;
         }
         public double getcantidadIngresada(){
            return cantidadIngresada;
         }
     } 
     // excepcion para cargar Producto 
      
    public static class CargarProductoException extends Exception {
        
        public CargarProductoException(String mensaje) {
            super(mensaje);
        }   
        
        public CargarProductoException(String mensaje, Throwable causa) {
            super(mensaje, causa);
        }
    
    }

    
}
