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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author oscar
 */
public class PaTarjetaController implements Initializable {

    @FXML
    private Button btnRegre;
    @FXML
    private TextField txtNumeroTarjeta;
    @FXML
    private TextField txtMesExpiracion;
    @FXML
    private TextField txtAnioExpiracion;
    @FXML
    private PasswordField txtCodigoSeguridad;
    @FXML
    private Label lblTotal;
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
    private void aceptarDatos(ActionEvent event) {
    }

    @FXML
    private void manejarAceptar(ActionEvent event) {
    }

    @FXML
    private void manejarCancelar(ActionEvent event) {
    }
    
}
