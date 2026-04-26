/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.uacm.barzul.bar;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Producto;

/**
 * FXML Controller class
 *
 * @author oscar
 */
public class DialogoAddPRodController implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtTipo;
    @FXML
    private TextField txtPrecio;
    @FXML
    private Button btnAceptar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtCantidad;

    Alert alertInfo = new Alert(AlertType.CONFIRMATION);
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    private void cerrarDialogo(ActionEvent event) {
        ((Stage) btnCancelar.getScene().getWindow()).close();
    }    

    @FXML
    private void addProd(ActionEvent event) {
        try {
            
            String nombreProd = txtNombre.getText();
            String tipoProd = txtTipo.getText();
            float precio = Float.parseFloat(txtPrecio.getText());
            int cantidad = Integer.parseInt(txtCantidad.getText());
            
            Producto productoCreado = new Producto(nombreProd, tipoProd, precio, cantidad);
            
            ProductoController.getConexion().getListaProductos().add(productoCreado);
            
            alertInfo.setTitle("Exito");
            alertInfo.setHeaderText("Producto Agregado");
            alertInfo.setContentText("El Producto ha sido agregado con Exito!");
            alertInfo.showAndWait();
            
            ((Stage) btnAceptar.getScene().getWindow()).close();
            
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }
        
}
