/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deby_lsp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */

public class Koneksi {
    Connection conn = null;
    public static Connection getKoneksi(){
        String driver = "com.mysql.jdbc.Driver";
        String host = "jdbc:mysql://localhost/deby_lsp";
        String user = "root";
        String pass = "";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(host,user,pass);
            System.out.println("Koneksi Berhasil");
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Koneksi Gagal "+e);
        }
        return null;
    }
    
        
}

