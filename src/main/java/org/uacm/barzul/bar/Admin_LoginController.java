/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.uacm.barzul.bar;
import modelo.MisExcepcionesBar.CargarProductoException;
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
import javafx.stage.Stage;
import modelo.Inventario;
import modelo.Producto;

/**
 * FXML Controller class
 *
 * @author oscar
 */
public class Admin_LoginController implements Initializable {

    @FXML private Button btnInventario;
    @FXML private Button btnEmpleados;
    @FXML private Button btnLogout;
    @FXML private Button btnAddProd;
    @FXML private Button btnEliminar;
    @FXML private Button btnVentas;
    @FXML private Button btnEditarProd;
    @FXML private TableColumn<Producto, String> nomProd;
    @FXML private TableColumn<Producto, String> tipoProd;
    @FXML private TableColumn<Producto, Float> precioProd;
    @FXML private TableColumn<Producto, Integer> cantidadProd;
    @FXML private TableView<Producto> tbProductos;
    
    private Producto producto = null;
    private ObservableList<Producto> prodObs = FXCollections.observableArrayList();
    private int opcion = 0;
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
        nomProd.setCellValueFactory( data -> new javafx.beans.property.SimpleStringProperty( data.getValue().getNombre()) );
        tipoProd.setCellValueFactory( data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTipo() ) );
        precioProd.setCellValueFactory( data -> new javafx.beans.property.SimpleFloatProperty( data.getValue().getPrecio() ).asObject());
        cantidadProd.setCellValueFactory( data -> new javafx.beans.property.SimpleIntegerProperty( data.getValue().getCantidad() ).asObject() );
        
        try {
        Inventario.getInstancia().cargarProductosTxt();
    } catch (modelo.MisExcepcionesBar.CargarProductoException e) {
        System.err.println("Error al cargar productos: " + e.getMessage());
        e.printStackTrace();
        
        // Mostrar alerta al usuario
        javafx.scene.control.Alert alerta = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alerta.setTitle("Error de carga");
        alerta.setHeaderText("No se pudieron cargar los productos");
        alerta.setContentText("Error: " + e.getMessage() + "\n\nVerifique el archivo productos.txt");
        alerta.showAndWait();
    }
        //Inventario.getInstancia().cargarProductosTxt();
        
        //cargarDatos();
        tbProductos.setItems(Inventario.getInstancia().getProductos());
        
        // Listener cuando se selecciona un producto
        tbProductos.getSelectionModel().selectedItemProperty().addListener(
            (obs, anterior, seleccionado) -> {
                producto = seleccionado;
                btnEditarProd.setDisable(seleccionado == null);
                btnEliminar.setDisable(seleccionado == null);
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
            tbProductos.setItems(Inventario.getInstancia().getProductos());
            
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
                
                // Recarga la lista una vez realizada la acción
                tbProductos.setItems(Inventario.getInstancia().getProductos());

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
            
            DialogoEliminarProdController controllerEl = loader.getController();
            controllerEl.setProducto(producto);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Eliminar Producto");
            stage.showAndWait();
            
            tbProductos.setItems(Inventario.getInstancia().getProductos());
            
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    } // fin eliminarProd
    
    public void setOp(int opcion) {
        this.opcion = opcion;
    }
    

    
}
