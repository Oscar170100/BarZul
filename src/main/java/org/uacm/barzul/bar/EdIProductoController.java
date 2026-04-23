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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maris
 */
public class EdIProductoController implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtTipo;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtDisponibilidad;
    @FXML
    private Button btnAceptar;
    @FXML
    private Button btnCancelar;

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
    
}
