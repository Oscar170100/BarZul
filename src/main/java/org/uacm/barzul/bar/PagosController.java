/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.uacm.barzul.bar;

import java.io.IOException;
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

    @FXML private Button btnEfectivo;
    @FXML private Button btnTarjeta;
    @FXML private Button btnRegresar;
    
    private float total;
    private String detallesPedido;
    private boolean pagoExitoso = false;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnRegresar.setOnAction(eh -> {
            ((Stage) btnRegresar.getScene().getWindow()).close();
        });
        
        // Recibe los valores del total a pagar de la cuenta
        
    }    

    @FXML
    private void manejarPagoEfectivo(ActionEvent event) {
          try  {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PaEfectivo.fxml"));
            Parent root = loader.load();
            
            PaEfectivoController controller = loader.getController();
            controller.setDatos(total);
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Pago en Efectivo");
            stage.showAndWait();
            
            if (stage.getUserData() != null && (boolean) stage.getUserData()) {
                setPagoExitoso(true);
            }
            
            cerrarVentana();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void manejarPagoTarjeta(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PaTarjeta.fxml"));
            Parent root = loader.load();
            
            PaTarjetaController controller = loader.getController();
            controller.setDatos(total);
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Pago con Tarjeta");
            stage.showAndWait();
            
            if (stage.getUserData() != null && (boolean) stage.getUserData()) {
                setPagoExitoso(true);
            }
            
            cerrarVentana();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public void setDatos(float total) {
        // Manda los totales 
        this.total = total;
  
    }
     public boolean isPagoExitoso() {
        return pagoExitoso;
    }

    public void setPagoExitoso(boolean estado) {
        this.pagoExitoso = estado;
    }

    private void cerrarVentana() {
        ((Stage) btnEfectivo.getScene().getWindow()).close();
    }
}