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
    private long NumTelefono;
    private String pregunta;
    private String resp;
    
    // Metodo Constructor
    public Empleado(String nombreEmp, int numEmpleado, int edad, long NumTelefono, String pregunta, String resp) {
        this.nombreEmp = nombreEmp;
        this.numEmpleado = numEmpleado;
        this.edad= edad;
        this.NumTelefono=NumTelefono;
        this.pregunta = pregunta;
        this.resp = resp;
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

    public long getNumTelefono() {
        return NumTelefono;
    }

    public void setNumTelefono(long NumTelefono) {
        this.NumTelefono = NumTelefono;
    }
    
    public String getPregunta() {
        return pregunta;
    }
    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
    
    public String getResp() {
        return resp;
    }
    public void setResp(String resp) {
        this.resp = resp;
    }
   
    
    
 
    
    
    
}
