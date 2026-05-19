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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Inventario;
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
    private TextField txtPrecio;
    @FXML
    private Button btnAceptar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtCantidad;

    Alert alertInfo = new Alert(AlertType.CONFIRMATION);
    @FXML
    private ChoiceBox<String> chBox;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        
        btnCancelar.setOnAction(eh -> {
            Stage stage = (Stage) btnCancelar.getScene().getWindow();
            stage.close();
            
        });
        
        
        chBox.getItems().add("Botana");
        chBox.getItems().add("Bebida");
        chBox.getItems().add("Bebia Alcoholica");
        
        // Nombre -> solo permite letras y espacios
         txtNombre.textProperty().addListener((obs, oldValue, newValue) -> {
             if (!newValue.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*")) {
                 txtNombre.setText(
                      newValue.replaceAll("[^a-zA-ZáéíóúÁÉÍÓÚñÑ ]", "")
            );
         }
        });

    // Cantidad -> solo permite numeros
        txtCantidad.textProperty().addListener((obs, oldValue, newValue) -> {
             if (!newValue.matches("\\d*")) {
                 txtCantidad.setText(
                     newValue.replaceAll("[^\\d]", "")
             );
        }
    });

    // Precio -> numeros con decimal
         txtPrecio.textProperty().addListener((obs, oldValue, newValue) -> {

        
        if (!newValue.matches("\\d*(\\.\\d*)?")) {
            txtPrecio.setText(oldValue);
        }
    });
        
    }     

    @FXML
    private void addProd(ActionEvent event) {
        try {
            
        
            String nombreProd = txtNombre.getText().trim();
            String tipoProd = chBox.getValue().trim();
            float precio = Float.parseFloat(txtPrecio.getText().trim());
            
            // el presio no tiene que ser negativo
             if (precio <= 0) {

            Alert alerta = new Alert(AlertType.WARNING);
            alerta.setTitle("Precio inválido");
            alerta.setHeaderText(null);
            alerta.setContentText("El precio debe ser mayor a 0");
            alerta.showAndWait();

            return;
        }
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());
            // la cantidad deve ser mayor a 0
            if (cantidad < 0) {

            Alert alerta = new Alert(AlertType.WARNING);
            alerta.setTitle("Cantidad inválida");
            alerta.setHeaderText(null);
            alerta.setContentText("La cantidad no puede ser negativa");
            alerta.showAndWait();

            return;
        }
            Producto productoCreado = new Producto(nombreProd, tipoProd, precio, cantidad);
            
            Inventario.getInstancia().agregarProducto(productoCreado);
            
            alertInfo.setTitle("Exito");
            alertInfo.setHeaderText("Producto Agregado");
            alertInfo.setContentText("El Producto ha sido agregado con Exito!");
            alertInfo.showAndWait();

            limpiarCampos();
            
            ((Stage) btnAceptar.getScene().getWindow()).close();
           
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    } // addProd
    
    private void limpiarCampos() {
        txtNombre.clear();
        txtPrecio.clear();
        txtCantidad.clear();
        
    }
        
}
