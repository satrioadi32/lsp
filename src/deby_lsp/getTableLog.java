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
public class getTableLog {
    String username,waktu,log;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
    public getTableLog(String username,String waktu,String log){
        this.username=username;
        this.waktu=waktu;
        this.log=log;
    }
}
