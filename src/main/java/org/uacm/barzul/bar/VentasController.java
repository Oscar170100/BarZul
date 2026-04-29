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

/**
 * FXML Controller class
 *
 * @author maris
 */
public class VentasController implements Initializable {

    @FXML
    private Button btnInventario;
    @FXML
    private Button btnEmpleados;
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnVentas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        // Boton Logout
        btnLogout.setOnAction(eh ->{
            SceneManager.cambiarVentana(eh, "Login.fxml");
        });

        // Boton Inventario
        btnInventario.setOnAction(eh -> {
            SceneManager.cambiarVentana(eh, "Admin_Login.fxml");
        });

        // Boton Empleados
        btnEmpleados.setOnAction(eh -> {
            SceneManager.cambiarVentana(eh, "Empleados.fxml");
        });
        

    }    

    
}
