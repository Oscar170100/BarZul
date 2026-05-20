/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.uacm.barzul.bar;

import org.uacm.barzul.bar.SceneManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import modelo.MisExcepcionesBar.ContraseñaIncorrectaException;

/**
 * FXML Controller class
 *
 * @author oscar
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtUser;
    @FXML
    private Button btnLogin;
    @FXML
    private PasswordField txtContraseña;
    @FXML
    private Hyperlink txtOlvidePass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loginUser(ActionEvent event) {
        
        String usuario = txtUser.getText();
        String password = txtContraseña.getText();
        
        Alert alertInfo = new Alert(AlertType.INFORMATION);
        
        try {
        if (usuario.equals("Admin") && password.equals("123456")) {
            // Ingreso como ADMINISTRADOR
            SceneManager.cambiarVentana(event, "Admin_Login.fxml");
            
        } else if (usuario.equals("emp") && password.equals("123")) {
            // Ingreso como EMPLEADO
            SceneManager.cambiarVentana(event, "Empleado_Login.fxml");
        
        } 
        
        else{
            throw new ContraseñaIncorrectaException("Contraseña incorrecta ",password);
        }
        }catch(ContraseñaIncorrectaException e){
            mostrarError("Error en contraseña ", 
            e.getMessage());
             txtContraseña.clear();
            txtContraseña.requestFocus();
        
               
        }
    }

    @FXML
    private void olvidePass(ActionEvent event) {
        SceneManager.cambiarVentana(event, "Olvide_Contraseña.fxml");
    }
    private void mostrarError(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    
}
}
