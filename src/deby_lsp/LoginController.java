/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deby_lsp;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import deby_lsp.Koneksi;
import deby_lsp.getKoneksi;

/**
 * FXML Controller class
 *
 * @author SIRCLO
 */
public class LoginController implements Initializable {
    
    
      Connection conn = Koneksi.getKoneksi();
    ResultSet rs = null;
    PreparedStatement pst = null;
    int id;
    String role;
    @FXML
    private TextField password;

    @FXML
    private TextField username;

    @FXML
    void login(ActionEvent event) throws SQLException, IOException {
           if(username.getText().isEmpty()!=false && password.getText().isEmpty()!=false){
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Login ");
                        alert.setHeaderText(null);
                        alert.setContentText("Username Password Kosong ");
                        alert.showAndWait();
     } else {
        String sql = "SELECT * FROM tb_login WHERE username='"+username.getText()+"' AND password='"+password.getText()+"'";
       
        pst = conn.prepareStatement(sql);
        rs=pst.executeQuery();
            if(rs.next()){
                if("kasir".equals(rs.getString("role"))){
                    getKoneksi.set_get_id(rs.getString("id"));
                    getKoneksi.set_get_role(rs.getString("role"));
                    getKoneksi.set_get_username(rs.getString("username"));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Login ");
                        alert.setHeaderText(null);
                        alert.setContentText("Anda Login Sebagai Kasir ");
                        alert.showAndWait();
                    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                    FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("order.fxml"));
                    Parent root1=fxmlLoader.load();
                    Stage stage=new Stage();
                     stage.setMaximized(true);
                    stage.setScene(new Scene(root1));
                    stage.show();
                }else if("manager".equals(rs.getString("role"))){
                    getKoneksi.set_get_id(rs.getString("id"));
                    getKoneksi.set_get_role(rs.getString("role"));
                    getKoneksi.set_get_username(rs.getString("username"));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Login ");
                        alert.setHeaderText(null);
                        alert.setContentText("Anda Login Sebagai Manager ");
                        alert.showAndWait();
                    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                    FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("menu.fxml"));
                    Parent root1=fxmlLoader.load();
                    Stage stage=new Stage();
                     stage.setMaximized(true);
                    stage.setScene(new Scene(root1));
                    stage.show();
                }else{
                    getKoneksi.set_get_id(rs.getString("id"));
                    getKoneksi.set_get_role(rs.getString("role"));
                    getKoneksi.set_get_username(rs.getString("username"));
                      Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Login ");
                        alert.setHeaderText(null);
                        alert.setContentText("Anda Login Sebagai Admin ");
                        alert.showAndWait();
                    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                    FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("user.fxml"));
                    Parent root1=fxmlLoader.load();
                    Stage stage=new Stage();
                     stage.setMaximized(true);
                    stage.setScene(new Scene(root1));
                    stage.show();
                }
                
            }else{
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Login ");
                        alert.setHeaderText(null);
                        alert.setContentText("Akun belum terdaftar ");
                        alert.showAndWait();
            }
        
     }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}