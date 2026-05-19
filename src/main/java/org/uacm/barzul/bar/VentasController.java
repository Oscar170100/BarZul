/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.uacm.barzul.bar;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import modelo.RegistroVenta;
import modelo.Venta;

/**
 * FXML Controller class
 *
 * @author maris
 */
public class VentasController implements Initializable {

    @FXML private Button btnInventario;
    @FXML private Button btnEmpleados;
    @FXML private Button btnLogout;
    @FXML private Button btnVentas;
    @FXML private TableView<Venta> tbVentas;
    @FXML private TableColumn<Venta, Integer> colMesa;
    @FXML private TableColumn<Venta, String> colEmp;
    @FXML private TableColumn<Venta, Integer> colNumEmp;
    @FXML private TableColumn<Venta, Float> colTotal;
    @FXML private TableColumn<Venta, String> colTipoPago;
    @FXML private TableColumn<Venta, String> colFecha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        // Boton Logout
        btnLogout.setOnAction(eh ->{
            SceneManager.cambiarVentana(eh, "Login.fxml");
        });

        // Boton Inventario
        btnInventario.setOnAction(eh -> {
            SceneManager.cambiarVentana(eh, "Admin_Login.fxml");
        });

        // Boton Empleados
        btnEmpleados.setOnAction(eh -> {
            SceneManager.cambiarVentana(eh, "Empleados.fxml");
        });
        
        // Configurar columnas
        colMesa.setCellValueFactory(
            data -> new SimpleIntegerProperty(
                data.getValue().getMesa()
            ).asObject()
        );
        
        colEmp.setCellValueFactory(
            data -> new SimpleStringProperty(
                data.getValue().getEmpleado()
            )
        );
        
        colNumEmp.setCellValueFactory(
            data -> new SimpleIntegerProperty(
                data.getValue().getNumEmpleado()
            ).asObject()
        );
        
        colTotal.setCellValueFactory(
            data -> new SimpleFloatProperty(
                data.getValue().getTotal()
            ).asObject()
        );

        colTipoPago.setCellValueFactory(
            data -> new SimpleStringProperty(
                data.getValue().getTipoPago()
            )
        );
        
        colFecha.setCellValueFactory(
            data -> new SimpleStringProperty(
                data.getValue().getFecha()
            )
        );
        
        // Cargar Ventas del Txt
        RegistroVenta.getInstancia().cargarVentasTxt();
        
        // Mostrar ventas en la tabla
        tbVentas.setItems(
            RegistroVenta.getInstancia().getVentas()
        );
    }    

    
}
