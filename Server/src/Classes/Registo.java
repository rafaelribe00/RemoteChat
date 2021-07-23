/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;


public class Registo extends UnicastRemoteObject implements RegistoInterface {
	
	private ArrayList<RegistoUser> registoUsers = new ArrayList<RegistoUser>();
	
	public Registo() throws RemoteException {
		super();
	}

        //Devolve Utilizadores
//        public ArrayList<Utilizador> getUsers(Utilizador user, int filtro, String param, NewRegistoInterface regInt) throws RemoteException {
	public ArrayList<AppUser> getUsers(AppUser user, int filtro, String param) throws RemoteException {
		
//                Atualiza(user, regInt);
                Atualiza(user);
            
		return getIPList(filtro, param);
	}
	
	private ArrayList<AppUser> getIPList(int filtro, String param){
		ArrayList<AppUser> result = new ArrayList<AppUser>();
		for (RegistoUser registo : registoUsers) {
                    if(filtro == 0){ //Todos Users
			if (!registo.timeOutPassed(180*1000)) {
				result.add(registo.getUtilizador());
			}
                    }
                    else if(filtro == 1){ //Pesquisa Nickname
                        if(!registo.timeOutPassed(180*1000) && registo.getUtilizador().getNameUser().equals(param)){
                            result.add(registo.getUtilizador());
                        }
                    }
                    else if(filtro == 2){ //Pesquisa email
                        if(!registo.timeOutPassed(180*1000) && registo.getUtilizador().getAdressUser().equals(param)){
                            result.add(registo.getUtilizador());
                        }
                    }
                    else if(filtro == 3){ //Pesquisa curso
                        if(!registo.timeOutPassed(180*1000) && registo.getUtilizador().getCursoUMinho().equals(param)){
                            result.add(registo.getUtilizador());
                        }
                    }
		}
		return result;
	}
        
        
        //Verifica se Email está a ser utilizado
        public Boolean VerifyEmail(String email){
            Boolean existe = false;
            
            for(RegistoUser registo : registoUsers){
                if(registo.getUtilizador().getAdressUser().equals(email)){
                    existe = true;
                }
            }
            System.out.println(email);
            return existe;
        }
        
        public void addNewUserReg(AppUser user){
            long actualTime = new Date().getTime();
            RegistoUser reg = new RegistoUser(user, actualTime);
            System.out.println("User added: " + user.toString());
            registoUsers.add(reg);   
        }
        
        
        //Devolve Utilizador que faz login
//        public Utilizador getUser(String email, NewRegistoInterface regInt){
        public AppUser getUser(String email){
            
            AppUser user = new AppUser();
            
            for(RegistoUser registo : registoUsers){
                if(registo.getUtilizador().getAdressUser().equals(email)){
                    user = registo.getUtilizador();
                }
                
            System.out.println("ola");
            }
            
//            Atualiza(user, cl);
            Atualiza(user);
            
            return user;
        } 
        
        
        //Atualiza e Adiciona Novo Registo
//        public void Atualiza(Utilizador user, NewRegistoInterface regInt){
        public void Atualiza(AppUser user){
            
            long actualTime = new Date().getTime();
            boolean existe = false;
                
            synchronized(this) {
                for(RegistoUser regUser : registoUsers){
                    if (regUser.getUtilizador().getAdressUser().equals(user.getAdressUser())) {
                        regUser.getUtilizador().setFriendsUser(user.getFriendsUser());
                        regUser.getUtilizador().setListaRequestsOnHold(user.getRequestsOnHold());
                        regUser.getUtilizador().setPortaUser(user.getPortaUser());
                        regUser.getUtilizador().setIpUser(user.getIpUser());
                        regUser.setLastSeen(actualTime);
//                        regUser.setCl(regInt);
                        existe = true;
                    }
                }
                if(existe == false){
//                    RegistoUser registo = new RegistoUser(user, actualTime, regInt);
                    RegistoUser registo = new RegistoUser(user, actualTime);
                    registoUsers.add(registo);
                }
            }
        } 
}

class RegistoUser {
	
	private AppUser utilizador;
	private long lastSeen;
//        String ip;
//        int porta;
//	private NewRegistoInterface cl;

	public RegistoUser(AppUser utilizador, long lastSeen) {
		this.utilizador = utilizador;
		this.lastSeen = lastSeen;
//		this.cl = cl;
	}

	public AppUser getUtilizador () {
		return this.utilizador;
	}

//        public NewRegistoInterface getCl () {
//		return this.cl;
//	}
        
//        public void setIP(String ip){
//            this.ip = ip;
//        }
//        
//        public void setPorta(int porta){
//            this.porta = porta;
//        }
        
	public void setLastSeen(long time){
		this.lastSeen = time;
	}

//	public void setCl(NewRegistoInterface cl){
//		this.cl = cl;
//	}

	public boolean timeOutPassed(int timeout){
		boolean result = false;
		long timePassedSinceLastSeen = new Date().getTime() - this.lastSeen;
		if (timePassedSinceLastSeen >= timeout)
			result = true;
		return result;
	}
}