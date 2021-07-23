/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface RegistoInterface extends Remote {

//	public ArrayList<Utilizador> getUsers(Utilizador user, int filtro, String param,  NewRegistoInterface cl) throws RemoteException;
        public ArrayList<AppUser> getUsers(AppUser user, int filtro, String param) throws RemoteException;

        public Boolean VerifyEmail(String email) throws RemoteException;
        
        public void addNewUserReg(AppUser user) throws RemoteException;
        
//        public Utilizador getUser(String nickname, NewRegistoInterface cl) throws RemoteException;
        public AppUser getUser(String email) throws RemoteException;
}
