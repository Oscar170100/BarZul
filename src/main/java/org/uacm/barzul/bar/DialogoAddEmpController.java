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
import modelo.MisExcepcionesBar.EdadInvalidaException;
import modelo.MisExcepcionesBar.TelefonoInvalidoException;
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
    }
    
    @FXML
    private void addEmp(ActionEvent event) {
        
        try {
           // excepcion para la edad 
            String edadStr= txtEdad.getText().trim();
                    if (edadStr.isEmpty()){
                    throw new EdadInvalidaException("La edad no puede encontrarse vacia",0);
                    }
            int Edad = Integer.parseInt(edadStr);
                    if (Edad < 18){
                    throw new EdadInvalidaException("El empleado debe de ser mayor de 18 años",Edad);
                    }
            // excepcion para el numero de telefono 
            if(txtTelefono.getText().trim().length()!=10){
                    throw new TelefonoInvalidoException("El numero debe de contener exactamente 10 digitos",txtTelefono.getText().trim());
                }
            
            String nombreEmp = txtNombre.getText().trim();
            int numEmpleado = Integer.parseInt(txtNoEmpleado.getText().trim());
            int edad = Integer.parseInt(txtEdad.getText().trim());
            long NumTelefono = Long.parseLong(txtTelefono.getText().trim());
            
            Empleado empleadoCreado = new Empleado(nombreEmp, numEmpleado, edad, NumTelefono);
            
            Registro.getInstancia().agregarEmpleado(empleadoCreado);
            
            alertInfo.setTitle("Exito");
            alertInfo.setHeaderText("Empleado Agregado");
            alertInfo.setContentText("El Empleado ha sido agregado con Exito!");
            alertInfo.showAndWait();

            limpiarCampos();
            
            ((Stage) btnAceptar.getScene().getWindow()).close();
        } catch (EdadInvalidaException e) {
            mostrarError("Error - Edad Incorrecta", 
                e.getMessage() + "\nEdad ingresada: " + e.getEdadIngresada() + " años");
            txtEdad.requestFocus();
            txtEdad.selectAll();
            
        }catch (TelefonoInvalidoException e) {
            mostrarError("Error - Teléfono Inválido", 
                e.getMessage() + "\nTeléfono ingresado: " + e.getTelefonoIngresado());
            txtTelefono.requestFocus();
            txtTelefono.selectAll();   
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
    private void mostrarError(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}