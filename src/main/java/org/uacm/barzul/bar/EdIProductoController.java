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
import modelo.Producto;

/**
 * FXML Controller class
 *
 * @author maris
 */
public class EdIProductoController implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtTipo;
    @FXML
    private TextField txtPrecio;
    @FXML
    private Button btnAceptar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtCantidad;
 
    //
    private Producto producto;
    
    
    //
    Alert alertaInfo = new Alert(Alert.AlertType.INFORMATION);
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnAceptar.setOnAction(event -> {
            guardarCambios();
            
            Stage stage = (Stage) btnAceptar.getScene().getWindow();
            stage.close();
        });
        
    }  
    
    // llena los valores de los campos
    /*
    Producto producto -> son los valores que vienen del producto seleccionado
    primero se evalua que el producto no apunte a null
    se comienzan a llenar los con los valores del producto seleccionado
    Nombre, tipo, precio, cantidad
    */
    public void setProducto(Producto producto) {
        this.producto = producto;
        
        if (producto != null) {
            
            txtNombre.setText(producto.getNombre());
            txtTipo.setText(producto.getTipo());
            // Se utiliza valueOf porque el precio es float y el setText es un String
            txtPrecio.setText(String.valueOf(producto.getPrecio()));
            txtCantidad.setText(String.valueOf(producto.getCantidad()));

        }
        
    }
    
    // Guarda los cambios si al editar el producto seleccionado
    public void guardarCambios() {
        String nombre = txtNombre.getText().trim();
        String tipo = txtTipo.getText().trim();
        float precio;
        int cantidad;
        
        if (nombre.isEmpty()) {
            alerta("Error", "El nombre no puede estar vacio");
        }
        if (tipo.isEmpty()) {
            alerta("Error", "El Tipo no puede estar vacio");
        }
        
        try {
            // asignamos el valor del campo txtPrecio(String) convirtiendolo en flotante (Float)
            precio = Float.parseFloat(txtPrecio.getText());
            cantidad = Integer.parseInt(txtCantidad.getText());
            
            // Evaluamos que no haya escrito 0 o menor
            if (precio <= 0 || cantidad <= 0) {
                alerta("Atencion", "Valores invalidos");
                return;
            }

            // Actualizando los valores
            producto.setNombre(nombre);
            producto.setTipo(tipo);
            producto.setPrecio(precio);
            producto.setCantidad(cantidad);

            // Alerta de Exito
            alerta("Exito", "Valores actualizados correctamente");
            
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            // si ingresa otra cosa que no sea un numero, manda esta alerta
            alerta("Error", "El precio o la cantidad no son numeros validos");
        }
        
    }
    
    // Genera una alerta con titulo y mensaje
    public void alerta(String titulo, String mensaje) {
        alertaInfo.setTitle(titulo);
        alertaInfo.setHeaderText(titulo);
        alertaInfo.setContentText(mensaje);
        alertaInfo.showAndWait();
    }
    
    @FXML
    private void cerrarDialogo(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
    
}
