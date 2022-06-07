/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deby_lsp;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class LogController implements Initializable {

    /**
     * Initializes the controller class.
     * 
     */
      @FXML
    private TableColumn<getTableLog, String> col_activity;

    @FXML
    private TableColumn<getTableLog, String> col_nama;

    @FXML
    private TableColumn<getTableLog, String> col_waktu;

    @FXML
    private TableView<getTableLog> tableLog;

//    @FXML
//    ObservableList<getTableLog> getDataLogObservableList = FXCollections.observableArrayList();
    
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    
    public Connection connectDb(){
      try{
      Class.forName("com.mysql.jdbc.Driver");
          connect = DriverManager.getConnection("jdbc:mysql://localhost/deby_lsp", "root", "");
          return connect;
      }catch(Exception e){
      
      }
        
        return null;
    
    }
    java.sql.Connection conn = Koneksi.getKoneksi();
    
     public ObservableList<getTableLog> dataList(){
        connect = connectDb();
        ObservableList<getTableLog> dataList = FXCollections.observableArrayList();
        
        String sql = "SELECT tb_login.username,tb_logs.waktu,tb_logs.log FROM tb_login JOIN tb_logs ON tb_logs.id_user=tb_login.id";
        try {

            Statement st;
            st = connect.createStatement();
            result = st.executeQuery(sql);
            while (result.next()) {

                dataList.add(new getTableLog(
                        result.getString("username"),
                        result.getString("waktu"),
                        result.getString("log")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
        
    }
    
    public void show(){
        ObservableList<getTableLog> showList = dataList();
        col_waktu.setCellValueFactory(new PropertyValueFactory<>("waktu"));
        col_nama.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_activity.setCellValueFactory(new PropertyValueFactory<>("log"));

        tableLog.setItems(showList);
        
//        try{
//            java.sql.Connection conn = (Connection)Koneksi.getKoneksi();
//            java.sql.Statement stm = conn.createStatement();
//            java.sql.ResultSet rst = stm.executeQuery("SELECT * FROM tb_logs");
//            
//            // LOPPING DATA
//            while(rst.next()){
//                String nama = rst.getString("nama");
//                String waktu = rst.getString("waktu");
//                String log = rst.getString("log");
//                
//                getDataLogObservableList.add(new getTableLog(nama,waktu,log));
//                
//            }
//            // SET DATA IN TABLE
//            col_waktu.setCellValueFactory(new PropertyValueFactory<>("waktu"));
//            col_nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
//            col_activity.setCellValueFactory(new PropertyValueFactory<>("log"));
//            
//            tableLog.setItems(getDataLogObservableList);
//            
//        }catch(SQLException e){
//               System.out.println("ERROR: "+ e );
//        }
    }
    
     @FXML
    void logPage(ActionEvent event) {
        try{
            Parent parent = FXMLLoader.load(getClass().getResource("log.fxml"));
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        show();
    }    
    
}
