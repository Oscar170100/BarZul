/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author maris
 */
public class Empleado {
    
    private String nombreEmp;
    private int numEmpleado;
    private int edad;
    private int NumTelefono;
    
    // Metodo Constructor
    public Empleado(String nombreEmp, int numEmpleado, int edad, int NumTelefono) {
        this.nombreEmp = nombreEmp;
        this.numEmpleado = numEmpleado;
        this.edad= edad;
        this.NumTelefono=NumTelefono;
    }
    
    // Setters y Getters

    public String getNombreEmp() {
        return nombreEmp;
    }

    public void setNombreEmp(String nombre) {
        this.nombreEmp = nombre;
    }

    public int getNumEmpleado() {
        return numEmpleado;
    }

    public void setNumEmpleado(int numEmpleado) {
        this.numEmpleado = numEmpleado;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getNumTelefono() {
        return NumTelefono;
    }

    public void setNumTelefono(int NumTelefono) {
        this.NumTelefono = NumTelefono;
    }
   
    
    
 
    
    
    
}
