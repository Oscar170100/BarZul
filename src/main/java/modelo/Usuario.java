/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author oscar
 */
public class Usuario {
    
    private int id;
    private String nombre;
    private int numEmpleado;
    private String password;
    private String rol;
    
    // Constructor
    public Usuario() {
        
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getNumEmp() {
        return numEmpleado;
    }
    public void setNumEmp(int numEmpleado) {
        this.numEmpleado = numEmpleado;
    }
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    
}
