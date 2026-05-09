/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.uacm.barzul.bar;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import modelo.Empleado;
import modelo.Registro;

/**
 * FXML Controller class
 *
 * @author maris
 */
public class DialogoEmpElimController implements Initializable {

    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Label txtNomEm;
    
    private Empleado empleado;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnCancelar.setOnAction(eh -> {
            
            Stage stage = (Stage) btnCancelar.getScene().getWindow();
            stage.close();
        }); // Fin btnCancelar
        
        btnEliminar.setOnAction(eh -> {

            eliminarEmp();
            Stage stage = (Stage) btnEliminar.getScene().getWindow();
            stage.close();
        }); // Fin btnEliminar
    } // Fin initialize   
    
    
    public void setEmpleado(Empleado empleado){
        this.empleado = empleado;
        
        txtNomEm.setText(" " + empleado.getNombreEmp()+ " ?");
    } // Fin setProducto
    
    private void eliminarEmp() {
        
        if (empleado != null) {
            // Elimina el producto del inventario
            Registro.getInstancia().eliminarEmpleado(empleado);
        }
    } // Fin eliminarProd
    
    }    
    
