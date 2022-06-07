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
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import deby_lsp.Koneksi;
import deby_lsp.getKoneksi;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author SIRCLO
 */
public class UserController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField id;

    @FXML
    private TableColumn<getTableUser,String> id_collom;

    @FXML
    private TextField password;

    @FXML
    private TableColumn<getTableUser,String> password_collom;

    @FXML
    private TextField role;

    @FXML
    private TableColumn<getTableUser,String> role_collom;

    @FXML
    private TableView<getTableUser> tableUser;

    @FXML
    private TextField username;

    @FXML
    private TableColumn<getTableUser,String> username_collom;
    
    public void log(String logs){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        
        try{
            int id;
            String id_user = getKoneksi.get_id();
            String sql = "INSERT INTO tb_logs VALUES(id,"+ id_user+",'"+ formattedDate+"','"+ logs +"')";
            java.sql.Connection conn = (Connection)Koneksi.getKoneksi();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
        }catch(SQLException e){
            System.out.println(e);
        }
    }

    @FXML
    void deleteUser(ActionEvent event) {
        String query = "DELETE FROM tb_login WHERE id =" + id.getText() + "";
         if(id.getText().isEmpty()!=false ){
            username.setText("");
            password.setText("");
            role.setText("");
            id.setText("");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Delete User");
            alert.setHeaderText(null);
            alert.setContentText("Data Tidak Ada");
            alert.showAndWait();
        }else{
            executeQuery(query);
             username.setText("");
            password.setText("");
            role.setText("");
            id.setText("");
            tableUser.getItems().clear();
            getData();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete User");
            alert.setHeaderText(null);
            alert.setContentText("Berhasil Delete User");
            log("Menghapus Akun");
            alert.showAndWait();
        }
         
    }

    @FXML
    void editUser(ActionEvent event) throws SQLException {
        Connection conn = Koneksi.getKoneksi();
       
        
       
        if(id.getText().isEmpty()!=false ){
          username.setText("");
            password.setText("");
            role.setText("");
            id.setText("");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Update User");
            alert.setHeaderText(null);
            alert.setContentText("Gagal Update User");
            alert.showAndWait();
        }else{
//           cek jika update data tapi tidak mengubah username
            String sql2 = "SELECT * FROM tb_login WHERE username='"+username.getText()+"' AND id='"+id.getText()+"'";
       
            PreparedStatement psst = conn.prepareStatement(sql2);
            ResultSet rs = psst.executeQuery();
            
//            cek jika mengubah username dengan yang tidak ada di database
            String sql3 = "SELECT * FROM tb_login WHERE username='"+username.getText()+"'";
       
            PreparedStatement pssst = conn.prepareStatement(sql3);
            ResultSet rss = pssst.executeQuery();
            if(rs.next()){
                
                  
                       String query = "UPDATE tb_login SET username = ?, password=?, role = ? WHERE id= ?";
                        PreparedStatement pst = conn.prepareStatement(query);
                        pst.setString(1, username.getText());
                        pst.setString(2, password.getText());
                        pst.setString(3, role.getText());
                     
                        pst.setString(4, id.getText());
                            System.out.println(query);
                        pst.execute();
                        username.setText("");
                        password.setText("");
                        role.setText("");
                        id.setText("");
                        tableUser.getItems().clear();
                        getData();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Update User");
                        alert.setHeaderText(null);
                        alert.setContentText("Berhasil Update User");
                        alert.showAndWait();
                  
                
                
            }else{
                if(rss.next()){
                    username.setText("");
                    password.setText("");
                    role.setText("");
                    id.setText("");
                      Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Update User");
                        alert.setHeaderText(null);
                        alert.setContentText("Username Sudah Ada ");
                        alert.showAndWait();
                }else{
                    
                       String query = "UPDATE tb_login SET username = ?, password=?, role = ? WHERE id = ?";
                        PreparedStatement pst = conn.prepareStatement(query);
                        pst.setString(1, username.getText());
                        pst.setString(2, password.getText());
                        pst.setString(3, role.getText());
                  
                        pst.setString(6, id.getText());
                            System.out.println(query);
                        pst.execute();
                       username.setText("");
                        password.setText("");
                        role.setText("");
                        id.setText("");
                        tableUser.getItems().clear();
                        getData();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Update User");
                        alert.setHeaderText(null);
                        alert.setContentText("Berhasil Update User");
                        log("Mengubah Akun");
                        alert.showAndWait();
                  
                
                }
              
            }
          
        }

    }

    @FXML
    void simpanUser(ActionEvent event) throws SQLException {
          String sql = "insert into tb_login values(null,'"+username.getText()+"','"+password.getText()+"','"+role.getText()+"')";
         if(username.getText()==null || password.getText()==null || role.getText()==null){
            username.setText("");
            password.setText("");
            role.setText("");
            id.setText("");
           
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Add User");
            alert.setHeaderText(null);
            alert.setContentText("Data Tidak Boleh Kosong");
            alert.showAndWait();
            
         } else {
            Connection conn = Koneksi.getKoneksi();
            String sql2 = "SELECT * FROM tb_login WHERE username='"+username.getText()+"'";
       
             PreparedStatement pst = conn.prepareStatement(sql2);
             ResultSet rs = pst.executeQuery();
             if(rs.next()){
                 username.setText("");
                 password.setText("");
                 role.setText("");
                 id.setText("");
                 Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Add User");
                    alert.setHeaderText(null);
                    alert.setContentText("Username Sudah Ada");
                    alert.showAndWait();
             }else{
                   executeQuery(sql);
                   username.setText("");
                    password.setText("");
                    role.setText("");
                    id.setText("");
                    tableUser.getItems().clear();
                    getData();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Add User");
                    alert.setHeaderText(null);
                    alert.setContentText("Berhasil Menambahkan User");
                    log("Menambahkan Akun");
                    alert.showAndWait();
             }
           
         }
    }
     private void executeQuery(String query) {
        Connection conn = Koneksi.getKoneksi();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
     
      @FXML
    void manageUser(ActionEvent event) {
        try{
            Parent parent = FXMLLoader.load(getClass().getResource("user.fxml"));
            Scene scene = new Scene(parent);
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setTitle("Dashboard");
            window.setMaximized(true);
            window.setMaximized(true);
            window.centerOnScreen();
            window.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
        @FXML
    void logPage(ActionEvent event)throws IOException {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("log.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setMaximized(true);
        stage.setScene(new Scene(root1));
        stage.show();
    }
     
//    @FXML
//    void logPage(ActionEvent event)throws IOException {
////        try{
////            Parent parent = FXMLLoader.load(getClass().getResource("log.fxml"));
////            Scene scene = new Scene(parent);
////            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
////            window.setScene(scene);
////            window.setTitle("Dashboard");
////            window.setMaximized(true);
////            window.setMaximized(true);
////            window.centerOnScreen();
////            window.show();
////        }catch(IOException e){
////            e.printStackTrace();
////        }
//
//        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
//
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("log.fxml"));
//        Parent root1 = fxmlLoader.load();
//        Stage stage = new Stage();
//        stage.setMaximized(true);
//        stage.setScene(new Scene(root1));
//        stage.show();
//    }
    
     ObservableList<getTableUser> oblist=FXCollections.observableArrayList();
    public void getData(){
          try{
            Connection conn = Koneksi.getKoneksi();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rst = stm.executeQuery("SELECT * FROM tb_login");
          
            
            
            // LOPPING DATA
            while(rst.next()){
               
                oblist.add(new getTableUser(rst.getString("id"),rst.getString("username"),rst.getString("password"),rst.getString("role")));
                
             
            }
            
           
            // SET DATA IN TABLE
            id_collom.setCellValueFactory(new PropertyValueFactory<getTableUser,String>("id"));
            username_collom.setCellValueFactory(new PropertyValueFactory<getTableUser,String>("username"));
            password_collom.setCellValueFactory(new PropertyValueFactory<getTableUser,String>("password"));
            role_collom.setCellValueFactory(new PropertyValueFactory<getTableUser,String>("role"));
            
            tableUser.setItems(oblist);
            
            tableUser.setOnMouseClicked(event->{
                this.events();
             });
            
        }catch(SQLException e){
               System.out.println("ERROR: "+ e );
        }
    }
    private void events(){
        
        for(getTableUser getData: tableUser.getSelectionModel().getSelectedItems()){
            for(int i=0;i<1;i++){
                username.setText(getData.getUsername());
                password.setText(getData.getPassword());
                role.setText(getData.getRole());
                id.setText(getData.getId());
                
                
            }
        }
    }
     @FXML
    void logout(ActionEvent event) throws IOException {
        getKoneksi.set_get_id("");
        getKoneksi.set_get_role("");
        getKoneksi.set_get_username("");
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                FXMLLoader fxmlLoaderss= new FXMLLoader(getClass().getResource("login.fxml"));
                    Parent root3=fxmlLoaderss.load();
                    Stage stagess=new Stage();
                     stagess.setMaximized(true);
                    stagess.setScene(new Scene(root3));
                    stagess.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getData();
    }    
    
}

