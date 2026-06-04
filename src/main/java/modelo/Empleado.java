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
    private int idEmpleado;
    private int numEmpleado;
    private String nombreEmp;
    private int edad;
    private String NumTelefono;
    private String pregunta;
    private String resp;
    private String password;
    
    // Metodo Constructor
    public Empleado(  int numEmpleado,String nombreEmp, int edad, String NumTelefono, String pregunta, String resp) {
        this.numEmpleado = numEmpleado;
        this.nombreEmp = nombreEmp;
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

    public String getNumTelefono() {
        return NumTelefono;
    }

    public void setNumTelefono(String NumTelefono) {
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
    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
   
}
