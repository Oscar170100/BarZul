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
import javafx.scene.control.ComboBox;
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
    @FXML
    private ComboBox<String> cbPregunta;
    @FXML
    private TextField txtResp;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cbPregunta.getItems().add("¿Cuál es el nombre de tu mascota?");
        cbPregunta.getItems().add("¿Cuál es tu lugar de nacimiento?");
        cbPregunta.getItems().add("¿Cuál es tu bebida favorita?");
        cbPregunta.getItems().add("¿Cuál fue tu primer trabajo?");
        
        btnCancelar.setOnAction(eh -> {
            Stage stage = (Stage) btnCancelar.getScene().getWindow();
            stage.close();    
        });
        

    // NoEmpleado  -> solo recibe numeros
    txtNoEmpleado.textProperty().addListener((obs, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) {
            txtNoEmpleado.setText(newValue.replaceAll("[^\\d]", ""));
        }
    });

    // Edad -> solo recibe numeros
    txtEdad.textProperty().addListener((obs, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) {
            txtEdad.setText(newValue.replaceAll("[^\\d]", ""));
        }
    });

    // Teléfono -> solo recibe numeros
    txtTelefono.textProperty().addListener((obs, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) {
            txtTelefono.setText(newValue.replaceAll("[^\\d]", ""));
        }
         if (txtTelefono.getText().length() > 10) {
        txtTelefono.setText(txtTelefono.getText().substring(0, 10));
    }
    });

    // Nombre -> solo  recibira letras, espacios y acentos
    txtNombre.textProperty().addListener((obs, oldValue, newValue) -> {
        if (!newValue.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*")) {
            txtNombre.setText(
                newValue.replaceAll("[^a-zA-ZáéíóúÁÉÍÓÚñÑ ]", "")
            );
        }
    });
    
    // Respuesta 
    /*
    txtResp.textProperty().addListener((obs, oldValue, newValue) -> {
        txtResp.setText(txtResp.getText());
    });
    */
    
    }
    
    
    @FXML
    private void addEmp(ActionEvent event) {
        
        if (cbPregunta.getValue() == null) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Pregunta");
            alerta.setHeaderText(null);
            alerta.setContentText("Selecciona una pregunta de seguridad");
            alerta.showAndWait();
            return;
        }
        
        try {

     
            if (txtTelefono.getText().trim().length() != 10) {

            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Teléfono inválido");
            alerta.setHeaderText(null);
            alerta.setContentText("El número telefónico debe tener exactamente 10 dígitos");
            alerta.showAndWait();
            return;
        }
        
            String nombreEmp = txtNombre.getText().trim();
            int numEmpleado = Integer.parseInt(txtNoEmpleado.getText().trim());
            int edad = Integer.parseInt(txtEdad.getText().trim());
            int NumTelefono = Integer.parseInt(txtTelefono.getText().trim());
            String pregunta = cbPregunta.getValue();
            String resp = txtResp.getText().trim();
            
            Empleado empleadoCreado = new Empleado(nombreEmp, numEmpleado, edad, NumTelefono, pregunta, resp);
            
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
    } // addProd
    
    private void limpiarCampos() {
        txtNombre.clear();
        txtEdad.clear();
        txtNoEmpleado.clear();
        txtTelefono.clear();
    }
    
}
