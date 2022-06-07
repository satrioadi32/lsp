/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deby_lsp;

/**
 *
 * @author ASUS
 */
public class getTableMenu {
    String nama;
    String harga;
    String number;
    String stok;
    public getTableMenu(String number,String nama_menu,String stok,String harga){
        this.number=number;
        this.nama=nama_menu;
        this.harga=harga;
        this.stok=stok;
        
        
    }
    //id
    public String getNumber(){
        return this.number;
    }
    public void setNumber(String number){
        this.number=number;
    }

    
    //nama menu
    public String getNama(){
        return this.nama;
    }
    public void setNama(String nama_menu){
        this.nama=nama_menu;
    }
    
    
    //harga
    public String getHarga(){
        return this.harga;
    }
    public void setHarga(String harga){
        this.harga=harga;
    }
    
    
    //stok
    public String getStok(){
        return this.stok;
    }
    public void setStok(String stok){
        this.stok=stok;
    }
   
}