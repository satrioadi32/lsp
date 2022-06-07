/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deby_lsp;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.stage.Stage;
import deby_lsp.Koneksi;
import deby_lsp.getKoneksi;
import deby_lsp.getTableCart;
import deby_lsp.getTableMenu;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * FXML Controller class
 *
 * @author SIRCLO
 */
public class OrderController implements Initializable {
    @FXML
    private TextField harga;
    
    @FXML
    private TextField stock;

    @FXML
    private TableColumn<getTableCart, String> harga_collom_keranjang;

    @FXML
    private TableColumn<getTableMenu,String> harga_collom_menu;

    @FXML
    private TableColumn<getTableCart, String> id_collom_keranjang;

    @FXML
    private TableColumn<getTableMenu,String> id_collom_menu;

    @FXML
    private TextField id_meja;

    @FXML
    private TextField id_menu;
    
    @FXML
    private TextField nama_pembeli;
    @FXML
    private TableColumn<getTableCart, String> id_menu_collom_keranjang;

    @FXML
    private TextField jumlah;

    @FXML
    private TextField jumlah_bayar;

    @FXML
    private TableColumn<getTableCart, String> jumlah_collom_keranjang;

    @FXML
    private TextField kembalian;

    @FXML
    private TextField nama_menu;

    @FXML
    private TableColumn<getTableCart, String> nama_menu_collom_keranjang;

    @FXML
    private TableColumn<getTableMenu,String> nama_menu_collom_menu;

    @FXML
    private TableColumn<getTableMenu,String> stok_collom_menu;

   @FXML
    private TableView<getTableCart> tableKeranjang;

    @FXML
    private TableView<getTableMenu> tableMenu;

    @FXML
    private TableColumn<getTableCart, String> total_harga_collom_keranjang;

    @FXML
    private TextField total_pembayaran;
    
    //Variable Global
    int stockBarang, jmlJual, sisaStock;
    
     @FXML
    void clear(ActionEvent event) {
        if(id_meja.getText()==null){
             total_pembayaran.setText("");
            jumlah_bayar.setText("");
            id_meja.setText("");
            kembalian.setText("");
            nama_pembeli.setText("");
            tableKeranjang.getItems().clear();
            getDataKeranjang();
        }else{
             String query = "DELETE FROM tb_cart WHERE id_meja =" + id_meja.getText() + "";
             executeQuery(query);
             tableKeranjang.getItems().clear();
             getDataKeranjang();
             total_pembayaran.setText("");
            jumlah_bayar.setText("");
            id_meja.setText("");
            kembalian.setText("");
            nama_pembeli.setText("");
        }
       
        
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
    void bayar(ActionEvent event) {
         
         String sql = "insert into tb_tranksaksi values(null,'"+getKoneksi.get_id()+"','"+id_meja.getText()+"','"+nama_pembeli.getText()+"','"+total_pembayaran.getText()+"')";
         
         System.out.println(sql);
         if(total_pembayaran.getText()==null || jumlah_bayar.getText()==null ){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Tranksaksi");
            alert.setHeaderText(null);
            alert.setContentText("Anda Belum Memilih Pesanan");
            alert.showAndWait();
            
         } else {
            int KembalianUang=Integer.parseInt(jumlah_bayar.getText())-Integer.parseInt(total_pembayaran.getText());
            executeQuery(sql);
            String query = "DELETE FROM tb_cart WHERE id_meja =" + id_meja.getText() + "";
            executeQuery(query);
          
       
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Tranksaksi");
            alert.setHeaderText(null);
            alert.setContentText("Tranksaksi Berhasil");
            alert.showAndWait();
             total_pembayaran.setText("");
            jumlah_bayar.setText("");
            id_meja.setText("");
            tableKeranjang.getItems().clear();
            getDataKeranjang();
            kembalian.setText(Integer.toString(KembalianUang));
            log("Melakukan Transaksi");
           
           
           
//            getData();

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
    void tambahKeranjang(ActionEvent event) throws SQLException {
        int totalHarga=Integer.parseInt(jumlah.getText())*Integer.parseInt(harga.getText());
         String sql = "insert into tb_cart values(null,'"+id_menu.getText()+"','"+id_meja.getText()+"','"+nama_menu.getText()+"','"+jumlah.getText()+"','"+harga.getText()+"','"+totalHarga+"')";
         if(id_meja.getText()==null || id_menu.getText()==null || nama_menu.getText()==null || jumlah.getText()==null || harga.getText()==null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Tambah Keranjang");
            alert.setHeaderText(null);
            alert.setContentText("Data Tidak Boleh Kosong");
            alert.showAndWait();
            
         } else {
            executeQuery(sql);
            id_meja.setText("");
            nama_menu.setText("");
            jumlah.setText("");
            harga.setText("");
            id_menu.setText("");
            stock.setText("");
         
          
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Tambah Keranjang");
            alert.setHeaderText(null);
            alert.setContentText("Berhasil Menambahkan Ke Keranjang");
            alert.showAndWait();
            
            this.sisaStock();
            
            tableKeranjang.getItems().clear();
            getDataKeranjang();
//            getData();

         }

    }
    
    private void sisaStock(){
        stockBarang = Integer.parseInt(stock.getText());
        jmlJual = Integer.parseInt(jumlah.getText());
            
        sisaStock = (stockBarang - jmlJual);
            
        try {
            String sql = "UPDATE tb_menu SET stok = " + sisaStock + " WHERE nama_menu = '" + nama_menu.getText() + "'";
            java.sql.Connection conn = (Connection)Koneksi.getKoneksi();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            
            
        } catch (SQLException e){
            System.out.println(e);
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
//     void sumKeranjang() throws SQLException{
//            Connection conn = koneksi.getKoneksi();
//            java.sql.Statement stm = conn.createStatement();
//            String sql="SELECT SUM(total_harga)  FROM tb_keranjang WHERE id_meja="+id_meja.getText();
//            java.sql.ResultSet rst = stm.executeQuery(sql);
//            System.out.println(rst.getString("total_harga"));
//            total_pembayaran.setText(rst.getString("total_harga"));
//     }
      ObservableList<getTableCart> oblistKeranjang=FXCollections.observableArrayList();
    public void getDataKeranjang(){
          try{
            Connection conn = Koneksi.getKoneksi();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rst = stm.executeQuery("SELECT * FROM tb_cart WHERE id_meja="+id_meja.getText());
          
            int totalHargaField = 0;
            
            // LOPPING DATA
            while(rst.next()){
                String id =rst.getString("id");
                String id_menu = rst.getString("id_menu");
                String nama_menu= rst.getString("nama_menu");
                String jumlah = rst.getString("jumlah");
                String harga = rst.getString("harga");
                String total_harga = rst.getString("total_harga");
                oblistKeranjang.add(new getTableCart(id,id_menu,nama_menu,jumlah,harga,total_harga));
                totalHargaField +=rst.getInt("total_harga");
             
            }
            total_pembayaran.setText(Integer.toString(totalHargaField));
           
            // SET DATA IN TABLE
            id_collom_keranjang.setCellValueFactory(new PropertyValueFactory<getTableCart,String>("id"));
            id_menu_collom_keranjang.setCellValueFactory(new PropertyValueFactory<getTableCart,String>("idmenu"));
            nama_menu_collom_keranjang.setCellValueFactory(new PropertyValueFactory<getTableCart,String>("namamenu"));
            jumlah_collom_keranjang.setCellValueFactory(new PropertyValueFactory<getTableCart,String>("jumlah"));
            harga_collom_keranjang.setCellValueFactory(new PropertyValueFactory<getTableCart,String>("harga"));
            total_harga_collom_keranjang.setCellValueFactory(new PropertyValueFactory<getTableCart,String>("total"));
            
            
            
            
            tableKeranjang.setItems(oblistKeranjang);
            
//            tableMenu.setOnMouseClicked(event->{
//                this.events();
//             });
            
        }catch(SQLException e){
               System.out.println("ERROR: "+ e );
        }
    }

    ObservableList<getTableMenu> oblist=FXCollections.observableArrayList();
    public void getDataMenu(){
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
            id_collom_menu.setCellValueFactory(new PropertyValueFactory<getTableMenu,String>("number"));
            nama_menu_collom_menu.setCellValueFactory(new PropertyValueFactory<getTableMenu,String>("nama"));
            harga_collom_menu.setCellValueFactory(new PropertyValueFactory<getTableMenu,String>("harga"));
            stok_collom_menu.setCellValueFactory(new PropertyValueFactory<getTableMenu,String>("stok"));
            
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
                id_menu.setText(getData.getNumber());
                nama_menu.setText(getData.getNama());
                harga.setText(getData.getHarga());
                stock.setText(getData.getStok());
            }
        }
    }
    
    

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getDataMenu();
        // TODO
    }    
    
}
