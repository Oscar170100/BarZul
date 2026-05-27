/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.uacm.barzul.bar;

import dao.ProductosDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Inventario;
import modelo.Producto;
import modelo.TipoProducto;

/**
 * FXML Controller class
 *
 * @author maris
 */
public class EdIProductoController implements Initializable {

    @FXML private TextField txtNombre;
    @FXML private TextField txtPrecio;
    @FXML private Button btnAceptar;
    @FXML private Button btnCancelar;
    @FXML private TextField txtCantidad;
    @FXML private ChoiceBox<TipoProducto> chBox;
    
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
        
        ProductosDAO dao = new ProductosDAO();
        chBox.getItems().addAll(dao.obtenerTiposProducto());
        
        btnAceptar.setOnAction(event -> {
            guardarCambios();
            
            Stage stage = (Stage) btnAceptar.getScene().getWindow();
            stage.close();
        });
        
        btnCancelar.setOnAction(eh -> {
            Stage stage = (Stage) btnCancelar.getScene().getWindow();
            stage.close();
        });
        // Nombre -> solo permite letras y espacios
    txtNombre.textProperty().addListener((obs, oldValue, newValue) -> {
        if (!newValue.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*")) {
            txtNombre.setText(
                newValue.replaceAll("[^a-zA-ZáéíóúÁÉÍÓÚñÑ ]", "")
            );
        }
    });

    // Cantidad -> solo permite numeros
    txtCantidad.textProperty().addListener((obs, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) {
            txtCantidad.setText(
                newValue.replaceAll("[^\\d]", "")
            );
        }
    });

    // Precio -> recibe  numeros con decimal
    txtPrecio.textProperty().addListener((obs, oldValue, newValue) -> {

        if (!newValue.matches("\\d*(\\.\\d*)?")) {
            txtPrecio.setText(oldValue);
        }
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
            for (TipoProducto tipo: chBox.getItems()) {
                if (tipo.getIdTipo() == producto.getTipoId()) {
                    chBox.setValue(tipo);
                    break;
                }
            }
            // Se utiliza valueOf porque el precio es float y el setText es un String
            txtPrecio.setText(String.valueOf(producto.getPrecio()));
            txtCantidad.setText(String.valueOf(producto.getCantidad()));

        }
        
    }
    
    // Guarda los cambios si al editar el producto seleccionado
    public void guardarCambios() {
        
        String nombre = txtNombre.getText().trim();
        TipoProducto tipoSeleccionado = chBox.getValue();
        double precio;
        int cantidad;
        
        if (nombre.isEmpty()) {
            alerta("Error", "El nombre no puede estar vacio");
            return;
        }
        if (tipoSeleccionado == null){
            alerta("Error", "El Tipo no puede estar vacio");
            return;
        }
        
        try {
            // asignamos el valor del campo txtPrecio(String) convirtiendolo en flotante (Float)
            precio = Double.parseDouble(txtPrecio.getText());
            cantidad = Integer.parseInt(txtCantidad.getText());
            
            // Evaluamos que no haya escrito 0 o menor
            if (precio <= 0 || cantidad <= 0) {
                alerta("Atencion", "Valores invalidos");
                return;
            }

            // Actualizando los valores
            producto.setNombre(nombre);
            producto.setTipoId(tipoSeleccionado.getIdTipo());
            producto.setPrecio(precio);
            producto.setCantidad(cantidad);

            // Actualizar en la BD
            Inventario.getInstancia().actualizarProducto(producto);
            
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
    
}
