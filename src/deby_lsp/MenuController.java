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
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class MenuController implements Initializable {
    
    
    @FXML
    private TableColumn<getTableMenu, String> harga_collumn;

    @FXML
    private TableColumn<getTableMenu, String> id_collom;
    
    @FXML
    private TableColumn<getTableMenu, String> nama_menu_collom;
    
    @FXML
    private TableColumn<getTableMenu, String> stok_collumn;

    

    @FXML
    private TableView<getTableMenu> tableMenu;
    
    
    @FXML
    private TextField harga;

    @FXML
    private TextField id;

    @FXML
    private TextField nama_menu;

    @FXML
    private TextField stok;
    
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
    void logout(ActionEvent event) throws IOException  {
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
    void simpanMenu(ActionEvent event) {
         String sql = "insert into tb_menu values(null,'"+nama_menu.getText()+"','"+harga.getText()+"','"+stok.getText()+"')";
         if(nama_menu.getText()==null || harga.getText()==null || stok.getText()==null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Tambah Menu");
            alert.setHeaderText(null);
            alert.setContentText("Data Tidak Boleh Kosong");
            alert.showAndWait();
            
         } else {
            executeQuery(sql);
            nama_menu.setText("");
            harga.setText("");
             stok.setText("");
            id.setText("");
          
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Tambah Menu");
            alert.setHeaderText(null);
            alert.setContentText("Berhasil Menambahkan Menu");
            alert.showAndWait();
            tableMenu.getItems().clear();
            log("Menambahkan Menu");
            getData();

         }
    }
    @FXML
    void deleteMenu(ActionEvent event) {
         String query = "DELETE FROM tb_menu WHERE id =" + id.getText() + "";
         if(id.getText().isEmpty()!=false ){
             nama_menu.setText("");
            stok.setText("");
            harga.setText("");
            id.setText("");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Delete Menu");
            alert.setHeaderText(null);
            alert.setContentText("Data Tidak Ada");
            alert.showAndWait();
        }else{
            executeQuery(query);
            nama_menu.setText("");
            stok.setText("");
            harga.setText("");
            id.setText("");
            tableMenu.getItems().clear();
            getData();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete Menu");
            alert.setHeaderText(null);
            alert.setContentText("Berhasil Delete Menu");
            log("Menghapus Menu");
            alert.showAndWait();
        }
    }

    @FXML
    void editMenu(ActionEvent event) throws SQLException {
        Connection conn = Koneksi.getKoneksi();
       
        
       
        if(id.getText().isEmpty()!=false ){
            nama_menu.setText("");
            stok.setText("");
            harga.setText("");
            id.setText("");
         
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Update Menu");
            alert.setHeaderText(null);
            alert.setContentText("Gagal Update Menu");
            alert.showAndWait();
        }else{
             String query = "UPDATE tb_menu SET nama_menu = ?, harga=?, stok = ? WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(query);

            pst.setString(1, nama_menu.getText());
            pst.setString(2, harga.getText());
            pst.setString(3, stok.getText());
            pst.setString(4, id.getText());
            pst.execute();
            nama_menu.setText("");
            stok.setText("");
            harga.setText("");
            id.setText("");
            tableMenu.getItems().clear();
            getData();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Menu");
            alert.setHeaderText(null);
            alert.setContentText("Berhasil Update Menu");
            log("Mengubah Menu");
            alert.showAndWait();
        }
    }
    ObservableList<getTableMenu> oblist=FXCollections.observableArrayList();
    public void getData(){
          try{
            Connection conn = Koneksi.getKoneksi();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rst = stm.executeQuery("SELECT * FROM tb_menu");
          
            
            
            // LOPPING DATA
            while(rst.next()){
                String nama_menu = rst.getString("nama_menu");
                String stok= rst.getString("stok");
                String harga = rst.getString("harga");
                System.out.println(rst.getString("nama_menu"));
                oblist.add(new getTableMenu(rst.getString("id"),rst.getString("nama_menu"),stok,harga));
                
             
            }
            
           
            // SET DATA IN TABLE
            id_collom.setCellValueFactory(new PropertyValueFactory<getTableMenu,String>("number"));
            nama_menu_collom.setCellValueFactory(new PropertyValueFactory<getTableMenu,String>("nama"));
            harga_collumn.setCellValueFactory(new PropertyValueFactory<getTableMenu,String>("harga"));
            stok_collumn.setCellValueFactory(new PropertyValueFactory<getTableMenu,String>("stok"));
            
            tableMenu.setItems(oblist);
            
            tableMenu.setOnMouseClicked(event->{
                this.events();
             });
            
        }catch(SQLException e){
               System.out.println("ERROR: "+ e );
        }
    }
    private void events(){
        
        for(getTableMenu getData: tableMenu.getSelectionModel().getSelectedItems()){
            for(int i=0;i<1;i++){
                nama_menu.setText(getData.getNama());
                harga.setText(getData.getHarga());
                stok.setText(getData.getStok());
                id.setText(getData.getNumber());
                
                
            }
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         getData();
    }    
    
}
