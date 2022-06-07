/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deby_lsp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */
public class Deby_lsp extends Application {
    
    @Override
   public void start(Stage stage) throws Exception {
         Connection conn = Koneksi.getKoneksi();
         ResultSet rs = null;
         PreparedStatement pst = null;
        
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        
        Scene scene = new Scene(root);
        stage.setMaximized(true);
      
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
