/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author nicol
 */
public class AplicationSystem{
    //variaveis de instancia
    private AppUser onGoingUser;
    private AppUsers entireAppUsers;
    
    public AplicationSystem() {
        this.entireAppUsers = new AppUsers();
        this.onGoingUser = new AppUser();
    }

    public AppUsers getEntireAppUsers() {
        return entireAppUsers;
    }
    
    public AppUser getOnGoingUser(){
        return onGoingUser;
    }
  
  public void setOnGoingUser(AppUser utilizador){
      this.onGoingUser = utilizador;
  }
}
