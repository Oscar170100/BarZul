/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.uacm.barzul.bar;

import dao.UsuariosDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import modelo.MisExcepcionesBar.ContraseñaIncorrectaException;
import modelo.Sesion;
import modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author oscar
 */
public class LoginController implements Initializable {

    @FXML private TextField txtUser;
    @FXML private Button btnLogin;
    @FXML private PasswordField txtContraseña;
    @FXML private Hyperlink txtOlvidePass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loginUser(ActionEvent event) {
        
        try {
            
            int numEmpleado = Integer.parseInt(txtUser.getText());
            String password = txtContraseña.getText();
            
            UsuariosDAO dao = new UsuariosDAO();
            
            Usuario usuario = dao.login(numEmpleado, password);
            
            Sesion.setUsuarioActual(usuario);
            
            if (usuario == null) {
                
                throw new ContraseñaIncorrectaException("Usuario o contraseña incorrecto", password);
                
            }
            
            if (usuario.getRol().equals("ADMIN")) {
                SceneManager.cambiarVentana(event, "Admin_Login.fxml");
            
            } else {
                SceneManager.cambiarVentana(event, "Empleado_Login.fxml");
            }
            
            
        } catch (NumberFormatException e) {
            mostrarError("Error", "El número de empleado debe ser numérico");
            
        } catch (ContraseñaIncorrectaException e) {
            mostrarError("Error", e.getMessage());
            
            txtContraseña.clear();
            txtContraseña.requestFocus();
        }

    } // Fin loginUser

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
