/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.ArrayList;

/**
 *
 * @author nicol
 */
public class AppUsers {
    
    //Array list para todos os utilizadores
     ArrayList<AppUser> allAppUsers;
    
    //construtor
    public AppUsers() {
        allAppUsers=new ArrayList<>();
    }
    public ArrayList<AppUser> getAppUsers(){
        return allAppUsers;
   }
    
    //adicionar um novo utilizador
    public void insereAppUsers(AppUser utilizador) {
        allAppUsers.add(utilizador);
    }
    
    //retorna o numero de utilizadores
    public String numeroAppUsers () {
        return allAppUsers.size() + "";
    }

    public void setAppUsers(ArrayList<AppUser> users) {
        allAppUsers = users;
    }
}
