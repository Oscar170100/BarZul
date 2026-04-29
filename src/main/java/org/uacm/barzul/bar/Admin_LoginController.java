/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.uacm.barzul.bar;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.Producto;

/**
 * FXML Controller class
 *
 * @author oscar
 */
public class Admin_LoginController implements Initializable {

    @FXML
    private Button btnInventario;
    @FXML
    private Button btnEmpleados;
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnAddProd;
    
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnVentas;
    @FXML
    private Button btnEditarProd;
    @FXML
    private TableColumn<Producto, String> nomProd;
    @FXML
    private TableColumn<Producto, String> tipoProd;
    @FXML
    private TableColumn<Producto, Float> precioProd;
    @FXML
    private TableColumn<Producto, Integer> cantidadProd;
    @FXML
    private TableView<Producto> tbProductos;
    
    private Producto producto = null;
    private ObservableList<Producto> prodObs = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        // Boton para Cerrar Sesion
        btnLogout.setOnAction(eh -> {
            SceneManager.cambiarVentana(eh, "Login.fxml");
        });
        
        // Boton para cambiar de escena a los Empleados
        btnEmpleados.setOnAction(eh -> {
            SceneManager.cambiarVentana(eh, "Empleados.fxml");
        }); 
        
        // Boton para cambiar de escena al inventario
        btnVentas.setOnAction(eh -> {
            SceneManager.cambiarVentana(eh, "Ventas.fxml");
        });
        
        // Para ver los datos en la tabla despues de agregar un Nuevo prod
        nomProd.setCellValueFactory( data -> new javafx.beans.property.SimpleStringProperty( data.getValue().getNombre() ) );
        tipoProd.setCellValueFactory( data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTipo() ) );
        precioProd.setCellValueFactory( data -> new javafx.beans.property.SimpleFloatProperty( data.getValue().getPrecio() ).asObject());
        cantidadProd.setCellValueFactory( data -> new javafx.beans.property.SimpleIntegerProperty( data.getValue().getCantidad() ).asObject() );
        
        //cargarDatos();
        tbProductos.setItems(ProductoController.getConexion().getListaProductos());
        // Listener cuando se selecciona un producto
        tbProductos.getSelectionModel().selectedItemProperty().addListener(
            (Observable, oldValue, newValue) -> {
                producto = newValue;
                btnEditarProd.setDisable(newValue == null);
                btnEliminar.setDisable(newValue == null);
            }
        );
        
    }    
    
    @FXML
    private void agregarProducto(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DialogoAddPRod.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Agregar Producto");
            stage.showAndWait();
            
            // Recargar los datos de la tabla 
            tbProductos.setItems(ProductoController.getConexion().getListaProductos());
            
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }
    
    @FXML
    private void editarProd(ActionEvent event) {
        if (producto != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("EdIProducto.fxml"));
                Parent root = loader.load();

                EdIProductoController controllerEd = loader.getController();
                controllerEd.setProducto(producto);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Editar Producto");
                stage.showAndWait();
                
                tbProductos.setItems(ProductoController.getConexion().getListaProductos());

                tbProductos.refresh();
                } catch (Exception e) {
                    e.printStackTrace();
                    e.getMessage();
                }

        }
    }

    @FXML
    private void eliminarProd(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DialogoEliminarProd.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Eliminar Producto");
            stage.showAndWait();
            
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }
    
    // Metodo para cargar los productos desde el archivo
    private void cargarDatos() {
        
        // Lista temporar de los productos
        ObservableList<Producto> lista = FXCollections.observableArrayList();
        
        // Intenta ejecutar este bloque de codigo 
        try {
            
            // Manda llamar y abre el archivo "productos.txt"
            InputStream is = getClass().getResourceAsStream("/productos.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            String linea;
            
            // Lee linea por linea
            while((linea = br.readLine()) != null) {
                
                // Divide los datos separados por una ","
                String[] partes = linea.split(",");
                
                // Extrae los valores para las variables 
                String nombre = partes[0];
                String tipo = partes[1];
                float precio = Float.parseFloat(partes[2]);
                int cantidad = Integer.parseInt(partes[3]);
                
                // Crea un objeto de typo Producto con los valores y lo agrega a la lista temporal
                lista.add(new Producto(nombre, tipo, precio, cantidad));
                
            }
            
            // Agrega la lista a la tabla principal de productos
            tbProductos.setItems(lista);
            
        } catch (Exception e) {
            // Si ocurre algun error al intentar abrir el archivo, lo caputra y muestra el StackTrace
            //e.printStackTrace();
            e.getMessage();
        }
        
        
    }

    
}
