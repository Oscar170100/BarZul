/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.uacm.barzul.bar;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import modelo.Producto;

/**
 * FXML Controller class
 *
 * @author maris
 */
public class TicketController implements Initializable {

    @FXML
    private TableView<Producto> tbTicket;
    @FXML
    private TableColumn<Producto, Integer> celCantidad;
    @FXML
    private TableColumn<Producto, String> celDescripcion;
    @FXML
    private TableColumn<Producto, Double> celPrecio;
    @FXML
    private Text lblNumtiket;
    @FXML
    private TextField txtTotal;
    @FXML
    private Text lblFecha;
    @FXML
    private Text lblMesa;
    @FXML
    private Text lblTipoPago;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        celCantidad.setCellValueFactory(
            data -> new SimpleIntegerProperty(
                data.getValue().getCantidad()
            ).asObject()
        );
        
        celDescripcion.setCellValueFactory(
            data -> new SimpleStringProperty(
                data.getValue().getNombre()
            )
        );
        
        celPrecio.setCellValueFactory(
            data -> new SimpleDoubleProperty(
               data.getValue().getCantidad() * data.getValue().getPrecio()
            ).asObject()
        );
        
    } // Fin Initialize
    
    public void setDatos(int idVenta, ObservableList<Producto> productos, double total, String fecha, String mesa, String tipoPago) {

        lblFecha.setText(fecha);
        
        //lblNombreEmpleado.setText(String.valueOf(nombreEmpleado));
        
        lblNumtiket.setText(String.valueOf(idVenta));
        
        lblMesa.setText(mesa);
        
        lblTipoPago.setText(tipoPago);
        
        tbTicket.setItems(productos);
        
        txtTotal.setText(
            String.format("%.2f", total)
        );
        
    } // Fin setDatos
    
}
