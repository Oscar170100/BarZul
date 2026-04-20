/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uacm.barzul.bar;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author oscar
 */
public class SceneManager {
    
    // Cambiar ventana
    public static void cambiarVentana(ActionEvent event, String fxml) {
        try {
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxml));
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    //
    
    
    
}
