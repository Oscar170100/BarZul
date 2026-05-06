/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.uacm.barzul.bar;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import modelo.Inventario;

import modelo.Producto;

/**
 * FXML Controller class
 *
 * @author oscar
 */
public class DialogoEliminarProdController implements Initializable {

    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Label txtNomProd;
    
    private Producto producto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnCancelar.setOnAction(eh -> {
            
            Stage stage = (Stage) btnCancelar.getScene().getWindow();
            stage.close();
        }); // Fin btnCancelar
        
        btnEliminar.setOnAction(eh -> {

            eliminarProd();
            Stage stage = (Stage) btnEliminar.getScene().getWindow();
            stage.close();
        }); // Fin btnEliminar
    } // Fin initialize   
    
    // da valores al objeto producto 
    public void setProducto(Producto producto) {
        this.producto = producto;
        
        txtNomProd.setText(producto.getNombre());
    } // Fin setProducto
    
    private void eliminarProd() {
        
        if (producto != null) {
            // Elimina el producto del inventario
            Inventario.getInstancia().eliminarProducto(producto);
        }
    } // Fin eliminarProd
    
    



    
    
}
