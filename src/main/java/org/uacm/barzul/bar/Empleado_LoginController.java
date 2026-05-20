/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.uacm.barzul.bar;
import modelo.MisExcepcionesBar.CargarProductoException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.Inventario;
import modelo.Producto;


/**
 * FXML Controller class
 *
 * @author oscar
 */
public class Empleado_LoginController implements Initializable {

    // Columnas Tabla Productos en Inventario
    @FXML private TableColumn<Producto, String> productoNom;
    @FXML private TableColumn<Producto, Float> precioProd;
    @FXML private TableColumn<Producto, Integer> cantidadProd;
    // Tabla Productos
    @FXML private TableView<Producto> productos;
    @FXML private Button btnLogout;
    @FXML // Botone en la tabla Produtos
  private TableColumn<Producto, Void> accionesProd;
    
  // Columnas Tabla Cuenta
    @FXML private TableColumn<Producto, String> prodCuenta;
    @FXML private TableColumn<Producto, Float> subTotalCuenta;
    @FXML private TextField totalCuenta;
    // Tabla principal de cuenta
    @FXML
    private TableView<Producto> tablaProdCuenta;
    
    // Lista observable para almacenar los productos de la cuenta
    // Se crea una instancia vacia de la lista observable usando la clase FXCOllections
    //ObservableList<Producto> listaCuenta = FXCollections.observableArrayList();
    // se usa para consultar los productos
    private ObservableList<Producto> listaCuenta;
    // Celda aciones tabla cuenta
    @FXML private TableColumn<Producto, Void> accionesCuenta;
    @FXML private Button btnPago;
    
    //
    Alert alertInfo = new Alert(AlertType.INFORMATION);
    @FXML private Pane pane2;
    @FXML private Pane pane3;
    @FXML
    private Button btnMesa1;
    @FXML private Button btnMesa2;
    @FXML private Button btnMesa3;
    @FXML private Button btnMesa4;
    private Pane pane1;
    private Map<String, ObservableList<Producto>> pedidosPorMesa = new HashMap<>();
    @FXML private TextField lblBuscar;
    
    private Button mesaSeleccionada;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        // Registrando una venta
        /*
        Venta venta = new Venta(
            1,
            "Carlos",
            1001,
            350,
            "Efectivo",
            "18/05/2026"
        );
        RegistroVenta.getInstancia().agregarVenta(venta);
        */
        
        pedidosPorMesa.put("Mesa 1", FXCollections.observableArrayList());
        pedidosPorMesa.put("Mesa 2", FXCollections.observableArrayList());
        pedidosPorMesa.put("Mesa 3", FXCollections.observableArrayList());
        pedidosPorMesa.put("Mesa 4", FXCollections.observableArrayList());

        seleccionarMesa(btnMesa1, "Mesa 1");
       // conección de botones para darles funcionalidad 
        btnMesa1.setOnAction(e -> seleccionarMesa(btnMesa1, "Mesa 1"));
        btnMesa2.setOnAction(e -> seleccionarMesa(btnMesa2, "Mesa 2"));
        btnMesa3.setOnAction(e -> seleccionarMesa(btnMesa3, "Mesa 3"));
        btnMesa4.setOnAction(e -> seleccionarMesa(btnMesa4, "Mesa 4"));
        
        btnLogout.setOnAction(eh -> {
            SceneManager.cambiarVentana(eh, "Login.fxml");
        });
        
        // Da valores a las columnas, nombre, precio, cantidad
        productoNom.setCellValueFactory(data -> 
                new SimpleStringProperty(data.getValue().getNombre())
        );
        
        precioProd.setCellValueFactory(data -> 
                new SimpleFloatProperty(data.getValue().getPrecio()).asObject()
        );
        
        cantidadProd.setCellValueFactory(data -> 
                new SimpleIntegerProperty(data.getValue().getCantidad()).asObject()
        );
        
        // Instancia la clase Inventario y carga los datos del Inventario
        //Inventario.getInstancia().cargarProductosTxt();
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
        productos.setItems(Inventario.getInstancia().getProductos());
        
        // Busqueda de productos
        /* 
        FilteredList es un tipo de lista especial para filtrar datos que actualiza la tabla automaticamante
        y muestra solo algunos elementos
        */
        FilteredList<Producto> filtro = new FilteredList<>(
                // Esta es la lista original de productos en inventario
                // p -> true : significa que al inicio muestra todos los productos
                Inventario.getInstancia().getProductos(), p -> true
        );
        
        /* 
        lblBuscar es el nombre que se le da al campo de buscar
        El Listener detecta (escucha) cuando se escribe en el campo
        Anterior = ag
        Nuevo =agu 
        y asi sucesivamente 
        */
        lblBuscar.textProperty().addListener((obs, anterior, nuevo) -> {
            
            // Definiendo las reglas del filtro
            filtro.setPredicate(producto -> {
                // Si el campo lblBuscar esta vacio, mostrar todos los elementos
                if (nuevo == null || nuevo.isEmpty()) {
                    // true: Si, muestralo en la tabla
                    return true;
                }
                
                // convierte el texto buscado a minusculas
                String texto = nuevo.toLowerCase();
                
                // Buscar por nombre
                // si el nombre del producto (en minusculas) contiene el texto ingresado a buscar
                if (producto.getNombre().toLowerCase().contains(texto)) {
                    return true;
                }
                
                // Buscar por Tipo
                // Si el Tipo de producto (en minusculas) contiene el texto ingresado
                if (producto.getTipo().toLowerCase().contains(texto)) {
                    return true;
                }
                
                // Si no encuentra coincidencia en tipo o producto
                return false;
            });
            
        });
        // Mostrar el producto buscado por tipo o por nombre en la tabla productos
        productos.setItems(filtro);
                
        // Llena los valores de la tabla CUENTA con: nombre y subTotal (precio * cantidad)
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
                    btnPlus.setOnAction( event -> agregarAlPedido(producto));

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
                
                if (empty || getTableRow().getItem() == null) {
                    
                    setGraphic(null);
                    
                } else {
                    Producto producto = getTableRow().getItem();
                    btnMinus.setOnAction(event -> { 
                        quitarDelPedido(producto);
                    });
                    
                    javafx.scene.layout.HBox box = new javafx.scene.layout.HBox(btnMinus);
                    box.setStyle("-fx-alignment: center;");
                    setGraphic(box);
                    
                }
                
            }
            
        });
        
        } // Fin Inicialize  
    
    // Agrega Producto a la cuenta 
    private void agregarAlPedido(Producto producto) {
        
        // Si la cantidad es 0 o menor, no hagas nada
        if (producto.getCantidad() <= 0) {
            return;
        }
        
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
        listaCuenta.add(new Producto(
                producto.getNombre(), 
                producto.getTipo(), 
                producto.getPrecio(), 
                1
        ));
        
        // Refresca la tabla
        tablaProdCuenta.refresh();
        // Recalcula el subtotal
        actualizarTotal();
        
    } // Fin agregarAlPedido
    
    // Quitar de la cuenta
    private void quitarDelPedido(Producto producto) {
        
        // Crea una nuevo productoDel de tipo Producto
        Producto productoDel = null;
        
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
                    productoDel = p;
                    
                }                
                break;
       
            }
        }
        
        if (productoDel != null) {
            listaCuenta.remove(productoDel);
        }
        // Refresca la tabla
        tablaProdCuenta.refresh();
        // Recalcula el subtotal
        actualizarTotal();
    } // Fin quitarDelPedido
    
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
    } // Fin actualizarTotal
    
    @FXML
    private void pagar(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Pagos.fxml"));
            Parent root = loader.load();
            
            // Enviando datos hacia la ventada de pagos
            PagosController controller = loader.getController();
            
            // Evaluamos que el total no sea 0 o este vacio
            String text = totalCuenta.getText();
            if (text == null || text.isEmpty()) {

                 alertInfo.setHeaderText("Advertencia");
                 alertInfo.setTitle("Advertencia");
                 alertInfo.setContentText("Campo Vacío");
                 alertInfo.showAndWait();

                  return;
            }
            
            float totalPago = Float.parseFloat(text);
            
            if (totalPago <= 0) {
            alertInfo.setHeaderText("Advertencia");
            alertInfo.setTitle("Advertencia");
            alertInfo.setContentText("El total debe ser mayor a 0");
            alertInfo.showAndWait();
            return;
        }
            
            controller.setDatos(totalPago);
            
            // Crear una nueva ventana
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Metodo de Pago");
            
            stage.showAndWait();
            pedidosPorMesa.get(mesaActual).clear();
            listaCuenta.clear();
            totalCuenta.clear();
            
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    } // Fin pagar
    
    // Bottar Datos de la cuenta

    @FXML
    private void logout(ActionEvent event) {
    }

    private void showPane1(ActionEvent event) {
        this.pane1. setOpacity(1);
        this.pane2.setOpacity(1);
        this.pane3.setOpacity(1);
        this.pane3.toFront();
    }

    private String mesaActual;

    private void seleccionarMesa(Button boton, String mesa) {

        mesaActual = mesa;
        listaCuenta = pedidosPorMesa.get(mesa);
        tablaProdCuenta.setItems(listaCuenta);
        actualizarTotal();
        
        if (mesaSeleccionada != null) {
            mesaSeleccionada.setStyle("");
        }
        
        // Guardar nuevo boton seleccionado
        mesaSeleccionada = boton;
        
        // Remarcar boton seleccionado
        boton.setStyle(
            "-fx-background-color: #009688;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 14px;"
        );
        
        //System.out.println("Mesa seleccionada: " + mesa);
    }
    
} // Fin class Empleado_LoginController