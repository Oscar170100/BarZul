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
    private float NumEmpleado;
    private int Edad;
    private int NumTelefono;
    
    // Metodo Constructor
    public Empleado(String nombreEmp, float NumEmpleado, int Edad, int NumTelefono) {
        this.nombreEmp = nombreEmp;
        this.NumEmpleado = NumEmpleado;
        this.Edad= Edad;
        this.NumTelefono=NumTelefono;
    }
    
    // Setters y Getters

    public String getNombreEmp() {
        return nombreEmp;
    }

    public void setNombreEmp(String nombre) {
        this.nombreEmp = nombre;
    }

    public float getNumEmpleado() {
        return NumEmpleado;
    }

    public void setNumEmpleado(float NumEmpleado) {
        this.NumEmpleado = NumEmpleado;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int Edad) {
        this.Edad = Edad;
    }

    public int getNumTelefono() {
        return NumTelefono;
    }

    public void setNumTelefono(int NumTelefono) {
        this.NumTelefono = NumTelefono;
    }
   
    
    
 
    
    
    
}
