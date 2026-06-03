/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author oscar
 */
public class Producto implements java.io.Serializable  {
    
    private int idProducto;
    private String nombre;
    private int tipoId;
    private double precio;
    private int cantidad;
    
    // Metodo Constructor
    public Producto(int idProducto, String nombre, int tipoId, double precio, int cantidad) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.tipoId = tipoId;
        this.precio = precio;
        this.cantidad = cantidad;
    }
    
    public Producto(String nombre, int tipoId, double precio, int cantidad) {
        this.nombre = nombre;
        this.tipoId = tipoId;
        this.precio = precio;
        this.cantidad = cantidad;
    }
    
    // Setters y Getters
    public int getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getTipoId() {
        return tipoId;
    }
    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }
    
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
    
    
    
}
