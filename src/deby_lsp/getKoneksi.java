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
public class getKoneksi {
    private static String id;
    private static String role;
    private static String username;
    public static String get_id(){
        return id;
    }
    public static void set_get_id(String id){
        getKoneksi.id=id;
    }
    public static String get_role(){
        return role;
    }
    public static void set_get_role(String role){
        getKoneksi.role=role;
    }
    public static String get_username(){
        return username;
    }
    public static void set_get_username(String username){
        getKoneksi.username=username;
    }
}
