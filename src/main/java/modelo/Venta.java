/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import javafx.collections.ObservableList;

/**
 *
 * @author oscar
 */
public class Venta {
    
    private int mesa;
    private String empleado;
    private int numEmpleado;
    private float total;
    private String tipoPago;
    private String fecha;
    
    public Venta(int mesa, String empleado, int numEmpleado, float total, String tipoPago, String fecha) {
        
        this.mesa = mesa;
        this.empleado = empleado;
        this.numEmpleado = numEmpleado;
        this.total = total;
        this.tipoPago = tipoPago;
        this.fecha = fecha;
        
    }
    
    public int getMesa() {
        return mesa;
    }
    public String getEmpleado() {
        return empleado;
    }
    public int getNumEmpleado() {
        return numEmpleado;
    }
    public float getTotal() {
        return total;
    }
    public String getTipoPago() {
        return tipoPago;
    }
    
    public String getFecha() {
        return fecha;
    }
    
    
    
    
}
