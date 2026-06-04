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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author oscar
 */
public class PaTarjetaController implements Initializable {

    @FXML private TextField txtNumeroTarjeta;
    @FXML private TextField txtMesExpiracion;
    @FXML private TextField txtAnioExpiracion;
    @FXML private PasswordField txtCodigoSeguridad;
    @FXML private Label lblTotal;
    @FXML private Button btnAceptar;
    @FXML private Button btnCancelar;
    
    private double total;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
        // TODO
        private boolean validarCampos() {
        // Validar que no estén vacíos
        if (txtNumeroTarjeta.getText().isEmpty() || 
            txtMesExpiracion.getText().isEmpty() || 
            txtAnioExpiracion.getText().isEmpty() || 
            txtCodigoSeguridad.getText().isEmpty()) {
            
            mostrarAlerta("Error", "Todos los campos son obligatorios");
             return false;
        }
            try {
            int mes = Integer.parseInt(txtMesExpiracion.getText());
            int año = Integer.parseInt(txtAnioExpiracion.getText());
            int codigo = Integer.parseInt(txtCodigoSeguridad.getText());
            
            if (mes <= 0 || mes > 12) {
                mostrarAlerta("Error", "Mes debe ser entre 1 y 12");
                return false;
            }
            
            if (año <= 0) {
                mostrarAlerta("Error", "Año debe ser positivo");
                return false;
            }
            
            if (codigo <= 0) {
                mostrarAlerta("Error", "Código de seguridad debe ser positivo");
                return false;
            }
            
            // Validar número de tarjeta (16 dígitos, solo números)
            String numeroTarjeta = txtNumeroTarjeta.getText().replaceAll("\\s+", "");
            if (!numeroTarjeta.matches("\\d{16}")) {
                mostrarAlerta("Error", "El número de tarjeta debe tener exactamente 16 dígitos");
                return false;
            }
            
            return true;
            
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Todos los campos numéricos deben contener solo dígitos positivos");
            return false;
        }
    }    
  // recibe el total de el pago
    public void setDatos(double total) {
        this.total = total;
        lblTotal.setText("$"+ String.format("%.2f",total));
    }
    

    @FXML
    private void manejarAceptar(ActionEvent event) {
        if (validarCampos()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pago");
            alert.setHeaderText(null);
            alert.setContentText("Pago realizado correctamente");
            alert.showAndWait();

            Stage stage = (Stage) btnAceptar.getScene().getWindow();
            stage.setUserData(true);
            stage.close();
            
        }
    }

    @FXML
    private void manejarCancelar(ActionEvent event) {
        cerrarVentana();
    }
    private void cerrarVentana(){
      ((Stage) btnCancelar.getScene().getWindow()).close();
    }
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
  
}