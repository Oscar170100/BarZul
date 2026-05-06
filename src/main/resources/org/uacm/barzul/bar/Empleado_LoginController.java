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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author maris
 */
public class Empleado_LoginController implements Initializable {

    @FXML
    private Button btnLogout;
    @FXML
    private Pane Pene1;
    @FXML
    private Button btnPago;
    @FXML
    private Pane pane2;
    @FXML
    private TableView<?> productos;
    @FXML
    private TableColumn<?, ?> productoNom;
    @FXML
    private TableColumn<?, ?> precioProd;
    @FXML
    private TableColumn<?, ?> cantidadProd;
    @FXML
    private TableColumn<?, ?> accionesProd;
    @FXML
    private Pane pane3;
    @FXML
    private TableView<?> tablaProdCuenta;
    @FXML
    private TableColumn<?, ?> prodCuenta;
    @FXML
    private TableColumn<?, ?> subTotalCuenta;
    @FXML
    private TableColumn<?, ?> accionesCuenta;
    @FXML
    private TextField totalCuenta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void logout(ActionEvent event) {
    }

    @FXML
    private void pagar(ActionEvent event) {
    }
    
}
