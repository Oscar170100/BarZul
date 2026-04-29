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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author maris
 */
public class EmpleadosController implements Initializable {

    @FXML
    private Button btnInventario;
    @FXML
    private Button btnEmpleados;
    @FXML
    private Button btnVentas;
    @FXML
    private TableView<?> tbEmpleados;
    @FXML
    private TableColumn<?, ?> NumEmpleado;
    @FXML
    private TableColumn<?, ?> Nombre;
    @FXML
    private TableColumn<?, ?> edad;
    @FXML
    private TableColumn<?, ?> Experienza;
    @FXML
    private Button btnAgreEmple;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnEditarInfo;
    @FXML
    private Button btnLogout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void agregarEmpleado(ActionEvent event) {
    }

    @FXML
    private void eliminarEmpleado(ActionEvent event) {
    }

    @FXML
    private void editarInfo(ActionEvent event) {
    }
    
}
