/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.uacm.barzul.bar;
// inportaciones de la clase de MisExcepcionesBar.

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Empleado;
import modelo.MisExcepcionesBar.EdadInvalidaException;
import modelo.MisExcepcionesBar.TelefonoInvalidoException;
import modelo.Registro;

/**
 * FXML Controller class
 *
 * @author maris
 */
public class DialodoEmpEdiController implements Initializable {

    @FXML
    private TextField txtNoEmpleado;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtEdad;
    @FXML
    private TextField txtTelefono;
    @FXML
    private Button btnAceptar;
    @FXML
    private Button btnCancelar;
    
    private Empleado empleado;
    
    Alert alertaInfo = new Alert(Alert.AlertType.INFORMATION);
    @FXML
    private ComboBox<String> cbPregunta;
    @FXML
    private TextField txtResp;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cbPregunta.getItems().add("¿Cuál es el nombre de tu mascota?");
        cbPregunta.getItems().add("¿Cuál es tu lugar de nacimiento?");
        cbPregunta.getItems().add("¿Cuál es tu bebida favorita?");
        cbPregunta.getItems().add("¿Cuál fue tu primer trabajo?");
        
        btnAceptar.setOnAction(event -> {

        boolean guardado = guardarCambios();
        if (guardado) {
        Stage stage = (Stage) btnAceptar.getScene().getWindow();
        stage.close();
    }
    });
      
        btnCancelar.setOnAction(eh -> {
            Stage stage = (Stage) btnCancelar.getScene().getWindow();
            stage.close();
        });
          // NoEmpleado  solo recibe numeros
         txtNoEmpleado.textProperty().addListener((obs, oldValue, newValue) -> {
             if (!newValue.matches("\\d*")) {
                  txtNoEmpleado.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

             // Edad solo recibe numeros
        txtEdad.textProperty().addListener((obs, oldValue, newValue) -> {
             if (!newValue.matches("\\d*")) {
                  txtEdad.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

             // Teléfono solo recibe numeros
        txtTelefono.textProperty().addListener((obs, oldValue, newValue) -> {
             if (!newValue.matches("\\d*")) {
                txtTelefono.setText(newValue.replaceAll("[^\\d]", ""));
            }
              if (txtTelefono.getText().length() > 10) {
                txtTelefono.setText(txtTelefono.getText().substring(0, 10));
            }
        });
        
        txtResp.textProperty().addListener((obs, oldValue, newValue) -> {
             if (!newValue.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*")) {
                 txtResp.setText(
                newValue.replaceAll("[^a-zA-ZáéíóúÁÉÍÓÚñÑ ]", "")
                );
             }
            
        });

        // Nombre solo  recibira letras, espacios y acentos
         txtNombre.textProperty().addListener((obs, oldValue, newValue) -> {
              if (!newValue.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*")) {
                 txtNombre.setText(
                newValue.replaceAll("[^a-zA-ZáéíóúÁÉÍÓÚñÑ ]", "")
                );
             }
        });
    }    
     public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
        
        if (empleado != null) {
            
            txtNombre.setText(empleado.getNombreEmp());
            // Se utiliza valueOf porque el precio es float y el setText es un String
            txtNoEmpleado.setText(String.valueOf(empleado.getNumEmpleado()));
            txtEdad.setText(String.valueOf( empleado.getEdad()));
            txtTelefono.setText(String.valueOf(empleado.getNumTelefono()));
            cbPregunta.setValue(empleado.getPregunta());
            txtResp.setText(empleado.getResp());
            
        }
    }
     public boolean guardarCambios() {
        String nombre = txtNombre.getText().trim();
        int NumEmpleado;
        int Edad;
        String NumTelefono=txtTelefono.getText().trim();
        String pregunta = cbPregunta.getValue();
        String resp = txtResp.getText().trim();
        
        
        if (nombre.isEmpty()) {
            alerta("Error", "El nombre no puede estar vacio");
        }
        
        try {
            // excepcion para la edad 
            String edadStr= txtEdad.getText().trim();
                    if (edadStr.isEmpty()){
                    throw new EdadInvalidaException("La edad no puede encontrarse vacia",0);
                    }
                    Edad = Integer.parseInt(edadStr);
                    if (Edad < 18){
                    throw new EdadInvalidaException("El empleado debe de ser mayor de 18 años",Edad);
                    }
            // excepcion para el numero de telefono 
            if(txtTelefono.getText().trim().length()!=10){
                    throw new TelefonoInvalidoException("El numero debe de contener exactamente 10 digitos",txtTelefono.getText().trim());
                }
                    
            NumEmpleado =Integer.parseInt(txtNoEmpleado.getText());
            Edad = Integer.parseInt(txtEdad.getText());
            
            
            // Actualizando los valores
            empleado.setNombreEmp(nombre);
            empleado.setNumEmpleado(NumEmpleado);
            empleado.setEdad(Edad);
            empleado.setNumTelefono(NumTelefono);
            empleado.setPregunta(pregunta);
            empleado.setResp(resp);

            // Guardar TXT
            Registro.getInstancia().guardarempleadosTxt();
            
            // Alerta de Exito
            alerta("Exito", "La inaformación fue actualizada correctamente");
            return true;
            
        } catch (EdadInvalidaException e) {
            mostrarError("Error - Edad Incorrecta", 
                e.getMessage() + "\nEdad ingresada: " + e.getEdadIngresada() + " años");
            txtEdad.requestFocus();
            txtEdad.selectAll();
            return false;
            
            } catch (TelefonoInvalidoException e) {
            mostrarError("Error - El telefono tiene que contener 10 dígitos ", 
                e.getMessage() + "\nNumero telefonico ingresado: " + e.getTelefonoIngresado());
            txtTelefono.requestFocus();
            txtTelefono.selectAll();
            return false; 
            
            
            
        }catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return false;
        } 
    }
      private void mostrarError(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
      public void alerta(String titulo, String mensaje) {
        alertaInfo.setTitle(titulo);
        alertaInfo.setHeaderText(titulo);
        alertaInfo.setContentText(mensaje);
        alertaInfo.showAndWait();
    }
    
}
