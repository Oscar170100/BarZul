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

/**
 * FXML Controller class
 *
 * @author oscar
 */
public class Olvide_ContraseñaController implements Initializable {

    @FXML
    private TextField txtUser;
    @FXML
    private Button btnRecuperarPass;
    @FXML
    private TextField txtUser1;
    @FXML
    private Button btnRecuperarPass1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void recuperarPass(ActionEvent event) {
    }
    
}
