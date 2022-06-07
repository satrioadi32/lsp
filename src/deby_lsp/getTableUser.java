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
public class getTableUser {
    String username;
    String password;
    String role;
    String id;
    public getTableUser(String id,String username,String password,String role){
        this.id=id;
        this.username=username;
        this.password=password;
        this.role=role;
    }
    //id
    public String getId(){
        return this.id;
    }
    public void setId(String id){
        this.id=id;
    }

    
    //nama menu
    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username=username;
    }
    
    
    //harga
    public String getPassword(){
        return this.password;
    }
    public void setHarga(String password){
        this.password=password;
    }
    
    
    //stok
    public String getRole(){
        return this.role;
    }
    public void setRole(String role){
        this.role=role;
    }
    
}
