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
             // ----------------------------------------------------------------------------------
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
     public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
        
        if (empleado != null) {
            
            txtNombre.setText(empleado.getNombreEmp());
            // Se utiliza valueOf porque el precio es float y el setText es un String
            txtNoEmpleado.setText(String.valueOf(empleado.getNumEmpleado()));
            txtEdad.setText(String.valueOf( empleado.getEdad()));
            txtTelefono.setText(String.valueOf(empleado.getNumTelefono()));
            
        }
    }
     public boolean guardarCambios() {
        String nombre = txtNombre.getText().trim();
        int NumEmpleado;
        int Edad;
        long NumTelefono;
        
        
        if (nombre.isEmpty()) {
            alerta("Error", "El nombre no puede estar vacio");
        }
        
        try {
            
        if (txtTelefono.getText().trim().length() != 10) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Teléfono inválido");
            alerta.setHeaderText(null);
            alerta.setContentText("El número telefónico debe tener exactamente 10 dígitos");
            alerta.showAndWait();
            return false;
        }
            
            // asignamos el valor del campo txtPrecio(String) convirtiendolo en flotante (Float)
            NumEmpleado =Integer.parseInt(txtNoEmpleado.getText());
            Edad = Integer.parseInt(txtEdad.getText());
            NumTelefono = Long.parseLong(txtTelefono.getText());
            
            // Actualizando los valore
            empleado.setNombreEmp(nombre);
            empleado.setNumEmpleado(NumEmpleado);
            empleado.setEdad(Edad);
            empleado.setNumTelefono(NumTelefono);

            // Alerta de Exito
            alerta("Exito", "Valores actualizados correctamente");
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return false;
        }
        
    }
      public void alerta(String titulo, String mensaje) {
        alertaInfo.setTitle(titulo);
        alertaInfo.setHeaderText(titulo);
        alertaInfo.setContentText(mensaje);
        alertaInfo.showAndWait();
    }
    
}
