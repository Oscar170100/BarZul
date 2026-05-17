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
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button; 
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author oscar
 */
public class PaEfectivoController implements Initializable {

    @FXML private Label lblTotal;
    @FXML private TextField txtEfectivoRecibido;
    @FXML private Label lblCambio;
    @FXML private Button btnAceptar;
    @FXML private Button btnCancelar;
    // donde se guardara el total de la cuenta 
    private float total;

    /**
     * ializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
        // Resive solo numeros 
        txtEfectivoRecibido.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d*?")) {
                txtEfectivoRecibido.setText(oldValue);
            }
        });
    }
    // recibe el total de el pago
    public void setDatos(float total) {
        this.total = total;
        lblTotal.setText("$"+ String.format("%.2f",total));
    }

    @FXML
    private void manejarCancelar(ActionEvent event) {
        cerrarVentana();
    }

    private void cerrarVentana(){
        ((Stage) btnCancelar.getScene().getWindow()).close();
    }

    @FXML
    private void manejarAceptar(ActionEvent event) {
        
         try { 
            double efectivo = Double.parseDouble(txtEfectivoRecibido.getText());
        // valida que la cantidad ingresada sea igual o mayor ala del pago 
        if(efectivo < total ){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Pago insuficiente");
            alerta.setHeaderText(null);
            alerta.setContentText("El efectivo recibido no cubre el total");
               
            alerta.showAndWait();
                    return;
        }
        // Calcular cambio y lo muestra 
        double cambio = efectivo - total;
        try {
         lblCambio.setText("$"+String.format("%.2f", cambio));
            
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Pago realizado");
                alerta.setHeaderText(null);
                alerta.setContentText("El pago fue realizado correctamente\n"+ "Cambio: $"+ String.format("%.2f", cambio));
                  
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setUserData(true);
                alerta.showAndWait();
   
        } catch (Exception e) {

                e.printStackTrace();
                e.getMessage();
                 
            }
    }catch (Exception e) {

                e.printStackTrace();
                e.getMessage();

                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error");
                alerta.setHeaderText(null);
                alerta.setContentText("Ingrese un valor válido");

                alerta.showAndWait();
            }
    }
}