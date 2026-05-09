/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.uacm.barzul.bar;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Empleado;

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btnAceptar.setOnAction(event -> {
            guardarCambios();
            
            Stage stage = (Stage) btnAceptar.getScene().getWindow();
            stage.close();
        });
        
        btnCancelar.setOnAction(eh -> {
            Stage stage = (Stage) btnCancelar.getScene().getWindow();
            stage.close();
        });
    }    
     public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
        
        if (empleado != null) {
            
            txtNombre.setText(empleado.getNombreEmp());
            // Se utiliza valueOf porque el precio es float y el setText es un String
            txtNoEmpleado.setText(String.valueOf(empleado.getNumEmpleado()));
            txtEdad.setText(String.valueOf((char) empleado.getEdad()));
            txtTelefono.setText(String.valueOf(empleado.getNumTelefono()));
            
        }
    }
     public void guardarCambios() {
        String nombre = txtNombre.getText().trim();
        float NumEmpleado;
        int Edad;
        int NumTelefono;
        
        
        if (nombre.isEmpty()) {
            alerta("Error", "El nombre no puede estar vacio");
        }
        try {
            // asignamos el valor del campo txtPrecio(String) convirtiendolo en flotante (Float)
            NumEmpleado = Float.parseFloat(txtNoEmpleado.getText());
            Edad = Integer.parseInt(txtEdad.getText());
            NumTelefono = Integer.parseInt(txtTelefono.getText());
            
            // Actualizando los valore
            empleado.setNombreEmp(nombre);
            empleado.setEdad(Edad);
            empleado.setNumEmpleado(NumEmpleado);
            empleado.setNumTelefono(NumTelefono);

            // Alerta de Exito
            alerta("Exito", "Valores actualizados correctamente");
            
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            // si ingresa otra cosa que no sea un numero, manda esta alerta
            alerta("Error", "El precio o la cantidad no son numeros validos");
        }
        
    }
      public void alerta(String titulo, String mensaje) {
        alertaInfo.setTitle(titulo);
        alertaInfo.setHeaderText(titulo);
        alertaInfo.setContentText(mensaje);
        alertaInfo.showAndWait();
    }
    
}
