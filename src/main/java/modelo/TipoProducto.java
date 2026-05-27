/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author oscar
 */

public class TipoProducto {
    private int idTipo;
    private String nombreTipo;
    
    public TipoProducto(int idTipo, String nombreTipo) {
        
        this.idTipo = idTipo;
        this.nombreTipo = nombreTipo;
        
    }
    
    public int getIdTipo() {
        return idTipo;
    }
    
    public String getNombreTipo() {
        return nombreTipo;
    }
    
    // Muestra el nombre en el choisebox
    @Override
    public String toString() {
        return nombreTipo;
    }
    
}
