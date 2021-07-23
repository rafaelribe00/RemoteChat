/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {
	
	String SERVICE_NAME="/RegistoRemoteServer";

	private void bindRMI(Registo reg) throws RemoteException{
                
		System.getProperties().put( "java.security.policy", "./src/Classes/server.policy");

		if( System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		try { 
			LocateRegistry.createRegistry(1099);
		} catch( RemoteException e) {
			
		}
		try {
		  LocateRegistry.getRegistry(InetAddress.getByName("DESKTOP-BJABO8J").getHostAddress(),1099).rebind(SERVICE_NAME, reg);
		  } catch( Exception e) {
		  	System.out.println("Registry not found: " + e);
		  }
	}

	public Server() {
		super();
	}
	
	public void createRegisto(){
		
		Registo registo = null;
		try {
			registo = new Registo();
		} catch (RemoteException e1) {
			System.err.println("unexpected error...");
			e1.printStackTrace();
		}
		
		try {
			bindRMI(registo);
                        System.out.println("Bind Success");
		} catch (RemoteException e1) {
			System.err.println("erro ao registar o stub...");
			e1.printStackTrace();
		}
		
	}
}
