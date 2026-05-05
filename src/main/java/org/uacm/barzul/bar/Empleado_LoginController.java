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
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import modelo.Producto;

/**
 * FXML Controller class
 *
 * @author oscar
 */
public class Empleado_LoginController implements Initializable {

    // Columnas Tabla Productos en Inventario
    @FXML
    private TableColumn<Producto, String> productoNom;
    @FXML
    private TableColumn<Producto, Float> precioProd;
    @FXML
    private TableColumn<Producto, Integer> cantidadProd;
    // Tabla Productos
    @FXML
    private TableView<Producto> productos;
    
    @FXML
    private Button btnLogout;
    @FXML
    
    // Botone en la tabla Produtos
    private TableColumn<Producto, Void> accionesProd;
    
    // Columnas Tabla Cuenta
    @FXML
    private TableColumn<Producto, String> prodCuenta;
    @FXML
    private TableColumn<Producto, Float> subTotalCuenta;
    @FXML
    private TextField totalCuenta;
    // Tabla principal de cuenta
    @FXML
    private TableView<Producto> tablaProdCuenta;
    
    // Lista observable para almacenar los productos de la cuenta
    // Se crea una instancia vacia de la lista observable usando la clase FXCOllections
    ObservableList<Producto> listaCuenta = FXCollections.observableArrayList();
    
    // Celda aciones tabla cuenta
    @FXML
    private TableColumn<Producto, Void> accionesCuenta;
    @FXML
    private Button btnPago;
    
    //
    Alert alertInfo = new Alert(AlertType.INFORMATION);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        // Da valores a las colimnas, nombre, precio, cantidad
        productoNom.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre())
        );
        
        precioProd.setCellValueFactory(data -> new SimpleFloatProperty(data.getValue().getPrecio()).asObject()
        );
        
        cantidadProd.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getCantidad()).asObject()
        );
        
        // Llena la lista de la cuenta en la tabla
        tablaProdCuenta.setItems(listaCuenta);
        
        // Llena los valores de la tabla cuenta con: nombre y subTotal (precio * cantidad)
        prodCuenta.setCellValueFactory(data -> {
           // Se crea una variable de tipo producto 
           Producto prod = data.getValue();
           String pText = prod.getNombre() + " x" + prod.getCantidad();
           return new SimpleStringProperty(pText);
        });
        
        subTotalCuenta.setCellValueFactory(data -> {
            float subTotal = data.getValue().getCantidad() * data.getValue().getPrecio();
            return new SimpleFloatProperty(subTotal).asObject();
        });
        
        // Crea el boton dentro de la tabla Productos
        accionesProd.setCellFactory(param -> new TableCell<>() {
            
            // Boton de + 
            private final Button btnPlus = new Button("+");
            
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                
                if (empty) {
                    // Si la celda esta vacia, no hace nada
                    setGraphic(null);  
                } else {

                    // Busca el indice del boton en la tabla
                    Producto producto = getTableRow().getItem();                    
                    // Si la celda tiene algo
                    // Accion del boton +
                    // Asignas eventos aquí
                    btnPlus.setOnAction( event -> 
                            agregarAlPedido( getTableView().getItems().get(getIndex()) )
                    );

                    // Se deshabilita el boton de + Si no hay inventario
                    btnPlus.setDisable(producto.getCantidad() <= 0);

                    // Dandode indicacion de donde va a ir el boton de + y agrega el centrado
                    javafx.scene.layout.HBox box =
                        new javafx.scene.layout.HBox(5, btnPlus);

                    box.setStyle("-fx-alignment: center;");
                    setGraphic(box);

                } 
            }
             
        });
        
        // Crea los Botones dentro de la tabla Cuenta
        accionesCuenta.setCellFactory(param -> new TableCell<>() {
            private final Button btnMinus = new Button("-");
            
            @Override
            protected void updateItem(Void item, boolean empty) {
                
                super.updateItem(item, empty);
                
                if (empty) {
                    setGraphic(null);
                } else {
                    btnMinus.setOnAction(event -> {
                        Producto producto = getTableRow().getItem();
                        quitarDelPedido(producto);
                    });
                    
                    javafx.scene.layout.HBox box = new javafx.scene.layout.HBox(btnMinus);
                    box.setStyle("-fx-alignment: center;");
                    setGraphic(box);
                    
                }
                
            }
            
        });
        
        // Llamando al archivo para llenar la tabla productos
        //cargarDatos();
        productos.setItems(ProductoController.getConexion().getListaProductos());
    }    
    
    
    // Agrega Producto a la cuenta 
    private void agregarAlPedido(Producto producto) {
        
        // Disminuimos en 1 la cantidad del inventario en la tabla productos
        producto.setCantidad(producto.getCantidad() - 1);
        // Recargamos la tabla principal para ver el stock actual
        productos.refresh();
        
        // Busca si el producto ya existe en la cuenta
        for(Producto p: listaCuenta){
            
            // Evalua si el nombre es el mismo, aumenta la cantidad
            if (p.getNombre().equals(producto.getNombre())) {
                p.setCantidad(p.getCantidad() + 1);
                // Refresca la tabla
                tablaProdCuenta.refresh();
                // Recalcula el subtotal
                actualizarTotal();
                return;
            }
        }
        
        // Si no existe el producto en la cuenta
        // Agrega producto y con cantidad 1
        listaCuenta.add(new Producto(producto.getNombre(), producto.getTipo(), producto.getPrecio(), 1));
        
        // Refresca la tabla
        tablaProdCuenta.refresh();
        // Recalcula el subtotal
        actualizarTotal();
        
    }
    
    // Quitar de la cuenta
    private void quitarDelPedido(Producto producto) {
        
        // Recorre la lista de la cuenta
        for(Producto p: listaCuenta) {
            
            // Compara si existe el producto buscado con el de la cuenta
            if (p.getNombre().equals(producto.getNombre())) {
                
                // Regresando Stock al producto
                for(Producto enInventario : productos.getItems()) {
                    // Evalua si el nombre del producto es el mismo
                    if (enInventario.getNombre().equals(producto.getNombre())) {
                        // incrementa la cantidad en 1 al producto en inventario
                        enInventario.setCantidad(enInventario.getCantidad() + 1);
                        // Recargando la tabla de los productos
                        productos.refresh();
                        break;
                    }
                    
                }
                
                // Si hay mas de 1 producto, resta del la cuenta
                if (p.getCantidad() > 1) {
                    p.setCantidad(p.getCantidad() - 1);
                    
                } else {
                    // Si solo hay uno, elimina el producto
                    listaCuenta.remove(p);
                    
                }
                
                // Refresca la tabla
                tablaProdCuenta.refresh();
                // Recalcula el subtotal
                actualizarTotal();
                
            }
        }
    }
    
    
    // Calcula el Total de la cuenta
    private void actualizarTotal() {
        
        float total = 0;
        
        // Recorre la lista de productos en la cuenta
        for(Producto p: listaCuenta) {
            // Suma los subtotales
            total += p.getCantidad() * p.getPrecio();
        }
        
        // Muestra el total en formato con 2 decimales
        totalCuenta.setText(String.format("%.2f", total));
    }
    
    
    // QUITAR PARA EVITAR REGAÑOS DEL PROFE, POR NO SABER QUE HACE
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
            productos.setItems(lista);
            
            
        } catch (Exception e) {
            // Si ocurre algun error al intentar abrir el archivo, lo caputra y muestra el StackTrace
            e.printStackTrace();
        }
        
        
    }

    @FXML
    private void logout(ActionEvent event) {
        SceneManager.cambiarVentana(event, "Login.fxml");
    }

    @FXML
    private void pagar(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Pagos.fxml"));
            Parent root = loader.load();
            
            // Enviando datos hacia la ventada d
            PagosController controller = loader.getController();
            
            // Evaluamos que el total no sea 0 o este vacio
            String text = totalCuenta.getText();
            if (text == null || text.isEmpty()) {
                // Si el total de la cuenta es 0 o esta vacio, lanza la alerta
                alertInfo.setHeaderText("Advertencia");
                alertInfo.setTitle("Advertencia");
                alertInfo.setContentText("Campo Vacio");
                alertInfo.showAndWait();
            }
            
            float totalPago = Float.parseFloat(text);
            controller.setDatos(totalPago);
            
            // Crear una nueva ventana
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Metodo de Pago");
            
            stage.showAndWait();
            
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    
}
