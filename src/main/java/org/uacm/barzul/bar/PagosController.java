/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.uacm.barzul.bar;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maris
 */
public class PagosController implements Initializable {

    @FXML
    private Button btnEfectivo;
    @FXML
    private Button btnTarjeta;
    
    private float total;
    @FXML
    private Button btnRegresar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnRegresar.setOnAction(eh -> {
            ((Stage) btnRegresar.getScene().getWindow()).close();
        });
        
    }    

    @FXML
    private void manejarPagoEfectivo(ActionEvent event) {
        SceneManager.cambiarVentana(event, "PaEfectivo.fxml");
    }

    @FXML
    private void manejarPagoTarjeta(ActionEvent event) {
        SceneManager.cambiarVentana(event, "PaTarjeta.fxml");
    }

    
    public void setDatos(float total) {
        // Manda los totales 
        this.total = total;
  
    }

    
}
