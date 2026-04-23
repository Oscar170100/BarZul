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
 * @author oscar
 */
public class Admin_LoginController implements Initializable {

    @FXML
    private Button btnInventario;
    @FXML
    private Button btnEmpleados;
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnAddProd;
    
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnVentas;
    @FXML
    private Button btnEditarProd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        // Boton para Cerrar Sesion
        btnLogout.setOnAction(eh -> {
            SceneManager.cambiarVentana(eh, "Login.fxml");
        });
        
        // Boton para cambiar de escena al inventario
        btnVentas.setOnAction(eh -> {
            SceneManager.cambiarVentana(eh, "Ventas.fxml");
        });
    }    

    @FXML
    private void agregarProducto(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DialogoAddPRod.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Agregar Producto");
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }
    
    @FXML
    private void editarProd(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EdIProducto.fxml"));
            Parent root = loader.load();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Editar Producto");
            stage.showAndWait();
            
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    @FXML
    private void eliminarProd(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DialogoEliminarProd.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Eliminar Producto");
            stage.showAndWait();
            
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }
    
}
