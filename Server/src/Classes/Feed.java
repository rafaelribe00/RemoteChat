/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;

/*
@author nicol
 */
public class Feed implements Serializable{
    private String nomeUser, msgUser;
    
    public Feed(String nomeUser, String msgUser){
        this.nomeUser = nomeUser;
        this.msgUser = msgUser;
    }
    
    public String getNomeUser(){
        return nomeUser;
    }
    
    public String getMsgUser(){
        return msgUser;
    }
}
