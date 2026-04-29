/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.uacm.barzul.bar;

import java.net.URL;
import java.security.SecureRandom;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author oscar
 */
public class Olvide_ContraseñaController implements Initializable {

    @FXML
    private Button btnRecuperarPass;
    @FXML
    private TextField lblUser;
    @FXML
    private TextField lblTel;
    @FXML
    private ComboBox<String> cBox;
    @FXML
    private TextField respPreg;
    
    
    @FXML
    private Button btnAtras;

    private String caracteres;
    private String nuevoPassword;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cBox.getItems().add("¿Cuál es el nombre de tu mascota?");
        cBox.getItems().add("¿Cuál es tu lugar de nacimiento?");
        cBox.getItems().add("¿Cuál es tu bebida favorita?");
        cBox.getItems().add("¿Cuál fue tu primer trabajo?");
        
        btnAtras.setOnAction(eh -> {
            SceneManager.cambiarVentana(eh, "Login.fxml");
        });
    }    

    @FXML
    private void recuperarPass(ActionEvent event) {
        
        Alert alertInfo = new Alert(AlertType.INFORMATION);
        
        if (lblUser.getText() == "") {
            alertInfo.setTitle("Alerta");
            alertInfo.setHeaderText("Atención");
            alertInfo.setContentText("El campo de usuario esta vacio");
            alertInfo.showAndWait();
        }
        
        if (lblTel.getText() == "") {
            alertInfo.setTitle("Alerta");
            alertInfo.setHeaderText("Atención");
            alertInfo.setContentText("El campo de Telefono esta vacio");
            alertInfo.showAndWait();
        }
        
        if(respPreg.getText().equals("Hola")) {
            
            nuevoPassword = generarPass();
            
            alertInfo.setTitle("Exito");
            alertInfo.setHeaderText("Contraseña Reestablecida");
            alertInfo.setContentText("Su nueva contraseña es: " + nuevoPassword);
            alertInfo.showAndWait();
            
            
            
            SceneManager.cambiarVentana(event, "Login.fxml");
            
        } else {
            alertInfo.setTitle("Error");
            alertInfo.setHeaderText("Error!!!");
            alertInfo.setContentText("Respuesta Incorrecta");
            alertInfo.showAndWait();

        }
        
    }
    
    // Genera un password aleatorio de 8 caracteres
    private String generarPass() {
        caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%&*";
        
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        
        for(int i = 0; i < 3; i++) {
            int index = random.nextInt(caracteres.length());
            password.append(caracteres.charAt(index));
        }
        return password.toString();
    }
    
    
}
