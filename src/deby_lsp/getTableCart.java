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
public class getTableCart {
    String id,idmenu,namamenu,jumlah,harga,total;
    public getTableCart(String id,String id_menu,String nama_menu,String jumlah,String harga,String total_harga){
        this.id=id;
        this.idmenu=id_menu;
        this.namamenu=nama_menu;
        this.jumlah=jumlah;
        this.harga=harga;
        this.total=total_harga;
        
        
    }
    //id
    public String getId(){
        return this.id;
    }
    public void setId(String id){
        this.id=id;
    }
    
    
     public String getIdmenu(){
        return this.idmenu;
    }
    public void setIdmenu(String id_menu){
        this.idmenu=id_menu;
    }
    
     public String getNamamenu(){
        return this.namamenu;
    }
    public void setNamamenu(String namamenu){
        this.namamenu=namamenu;
    }
    
     public String getJumlah(){
        return this.jumlah;
    }
    public void setJumlah(String jumlah){
        this.jumlah=jumlah;
    }
    
     public String getHarga(){
        return this.harga;
    }
    public void setHarga(String harga){
        this.harga=harga;
    }
    
     public String getTotal(){
        return this.total;
    }
    public void setTotal(String total){
        this.total=total;
    }
    
    

    
 
    

}
