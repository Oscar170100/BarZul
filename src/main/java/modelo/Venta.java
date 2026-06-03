/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author oscar
 */
public class Venta {
    
    private int idVenta;
    private int mesaId;
    private int empleadoId;
    private String nombreEmpleado;
    private int numEmpleado;
    private double total;
    private String tipoPago;
    private String fecha;
    
    public Venta(int mesaId, int empleadoId, double total, String tipoPago) {
        
        this.mesaId = mesaId;
        this.empleadoId = empleadoId;
        this.total = total;
        this.tipoPago = tipoPago;
        
    }
    
    public int getIdVenta() {
        return idVenta;
    }
    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }
    
    public int getMesaId() {
        return mesaId;
    }
    public void setMesaId(int mesaId) {
        this.mesaId = mesaId;
    }
    
    public int getEmpleadoId() {
        return empleadoId;
    }
    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }
    
    public String getNombreEmpleado() {
        return nombreEmpleado;
    }
    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }
    
    public int getNumEmpleado() {
        return numEmpleado;
    }
    public void setNumEmpleado(int numEmpleado) {
        this.numEmpleado = numEmpleado;
    }
    
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    
    public String getTipoPago() {
        return tipoPago;
    }
    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }
    
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
}
