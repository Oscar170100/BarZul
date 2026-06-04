/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.uacm.barzul.bar;

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
import modelo.Empleado;
import modelo.Registro;

/**
 * FXML Controller class
 *
 * @author maris
 */
public class EmpleadosController implements Initializable {

    @FXML private Button btnInventario;
    @FXML private Button btnEmpleados;
    @FXML private Button btnVentas;
    
    @FXML private TableView<Empleado> tbEmpleados;
    
    @FXML private TableColumn<Empleado, Integer> NumEmpleado;
    @FXML private TableColumn< Empleado, String > Nombre;
    @FXML private TableColumn<Empleado, Integer> edad;
    @FXML private TableColumn<Empleado, String>NumTelefono;
    @FXML private TableColumn<Empleado, String> respSeg;
    @FXML private TableColumn<Empleado, String> password;
    
    @FXML private Button btnAgreEmple;
    @FXML private Button btnEliminar;
    @FXML private Button btnEditarInfo;
    
    @FXML private Button btnLogout;

    private Empleado empleado = null;
    private ObservableList<Empleado> empObs= FXCollections.observableArrayList();
    private int opcion = 0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        btnLogout.setOnAction(eh -> {
            SceneManager.cambiarVentana(eh, "Login.fxml");
        });
        
        btnInventario.setOnAction(eh -> {
            SceneManager.cambiarVentana(eh, "Admin_Login.fxml");
        });
        
        btnVentas.setOnAction(eh -> {
            SceneManager.cambiarVentana(eh, "Ventas.fxml");
        });
        NumEmpleado.setCellValueFactory( data -> new javafx.beans.property.SimpleIntegerProperty( data.getValue().getNumEmpleado()).asObject() );
        Nombre.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty( data.getValue().getNombreEmp()));
        edad.setCellValueFactory( data -> new javafx.beans.property.SimpleIntegerProperty( data.getValue().getEdad()).asObject() );
        NumTelefono.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNumTelefono()));      
        password.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty("*****"));
        respSeg.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty( data.getValue().getResp()));
        
        tbEmpleados.setItems(Registro.getInstancia().getEmpleado());
        
        // Listener cuando se selecciona un empleado
        tbEmpleados.getSelectionModel().selectedItemProperty().addListener(
            (obs, anterior, seleccionado) -> {
                empleado = seleccionado;
                btnEditarInfo.setDisable(seleccionado == null);
                btnEliminar.setDisable(seleccionado == null);
            }
        );
        
    }        
    
    @FXML
    private void agregarEmpleado(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DialogoAddEmp.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Agregar Empleado");
            stage.showAndWait();

            //Regarcar los datos de la tabla
            tbEmpleados.setItems(Registro.getInstancia().getEmpleado());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Problema en el codigo" + e.getMessage());
        }
    }

    @FXML
    private void eliminarEmpleado(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DialogoEmpElim.fxml"));
            Parent root = loader.load();
            
            DialogoEmpElimController controllerEl = loader.getController();
            controllerEl.setEmpleado(empleado);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Eliminar Empleado");
            stage.showAndWait();
            
            tbEmpleados.setItems(Registro.getInstancia().getEmpleado());
            
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    @FXML
    private void editarInfo(ActionEvent event) {
         if (empleado != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DialodoEmpEdi.fxml"));
                Parent root = loader.load();

                DialodoEmpEdiController controllerEd = loader.getController();
                controllerEd.setEmpleado(empleado);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Editar Empleado");
                stage.showAndWait();
                
                // Recarga la lista una vez realizada la acción
                tbEmpleados.setItems(Registro.getInstancia().getEmpleado());

                tbEmpleados.refresh();
                } catch (Exception e) {
                    e.printStackTrace();
                    e.getMessage();
                }

        }
        
    }
    
    public void setOp(int opcion) {
        this.opcion = opcion;
    }
     
}
