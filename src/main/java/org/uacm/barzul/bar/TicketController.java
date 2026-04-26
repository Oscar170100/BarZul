/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.uacm.barzul.bar;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author maris
 */
public class TicketController implements Initializable {

    @FXML
    private TableView<?> tbTicket;
    @FXML
    private TableColumn<?, ?> celCantidad;
    @FXML
    private TableColumn<?, ?> celDescripcion;
    @FXML
    private TableColumn<?, ?> celPrecio;
    @FXML
    private Text lblNumtiket;
    @FXML
    private TextField txtTotal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
