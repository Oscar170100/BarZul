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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Empleado;
import modelo.Registro;

/**
 * FXML Controller class
 *
 * @author oscar
 */
public class DialogoAddEmpController implements Initializable {

    @FXML private TextField txtNoEmpleado;
    @FXML private TextField txtNombre;
    @FXML private TextField txtEdad;
    @FXML private Button btnAceptar;
    @FXML private Button btnCancelar;
    @FXML private TextField txtTelefono;
    Alert alertInfo = new Alert(Alert.AlertType.CONFIRMATION);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btnCancelar.setOnAction(eh -> {
            Stage stage = (Stage) btnCancelar.getScene().getWindow();
            stage.close();
            
        });
    }
    private void addEmp(ActionEvent event) {
        
             /**   try {
            
            String nombreEmp = txtNombre.getText().trim();
            float NumEmpleado = Float.parseFloat(txtNoEmpleado.getText().trim());
            int edad = Integer.parseInt(txtEdad.getText().trim());
            int NumTelefono = Integer.parseInt(txtTelefono.getText().trim());
            
            Empleado empleadoCreado = new Empleado(nombreEmp, NumEmpleado, edad, NumTelefono);
            
            Registro.getInstancia().agregarEmpleado(empleadoCreado);
            
            alertInfo.setTitle("Exito");
            alertInfo.setHeaderText("Empleado Agregado");
            alertInfo.setContentText("El Empleado ha sido agregado con Exito!");
            alertInfo.showAndWait();

            limpiarCampos();
            
            ((Stage) btnAceptar.getScene().getWindow()).close();
           
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    } // addProd*/
    
    /**private void limpiarCampos() {
        txtNombre.clear();
        txtEdad.clear();
        txtNoEmpleado.clear();
        txtTelefono.clear();*/
        
    }
    
    
    
    }
