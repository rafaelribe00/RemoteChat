/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;
import java.net.*;
import java.io.*;
import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.util.Timer;

/**
 *
 * @author nicol
 */
public class Servidor extends Thread {

    ServerSocket servidor = null; 
    AplicationSystem system;
    int porta;
    private volatile boolean flag = true;
    String SERVICE_NAME="/RegistoRemoteServer";
    
    public Servidor(AplicationSystem system){
        
        try{
            
            RegistoInterface regInt = (RegistoInterface) LocateRegistry.getRegistry(InetAddress.getByName("DESKTOP-BJABO8J").getHostAddress()).lookup(SERVICE_NAME);
            
            this.system = system;
            servidor = new ServerSocket(0);
            int porta = servidor.getLocalPort();
            String ip = InetAddress.getLocalHost().getHostAddress();
            system.getOnGoingUser().setPortaUser(porta);
            system.getOnGoingUser().setIpUser(ip);
            
            system.getEntireAppUsers().setAppUsers(regInt.getUsers(system.getOnGoingUser(), 0, ""));
            
            System.out.println("Servidor a espera de ligacoes no porto " + porta + "ip:" + ip);
            
            Timer time = new Timer();
            ScheduledCallRMI scr = new ScheduledCallRMI(system);
            time.schedule(scr, 0, 60000);
            
        }catch(Exception Except){
            System.out.println("Erro na execucao do servidor: " + Except);
            System.exit(1);    
        }
    }
    
    @Override
    public void run(){
         while(flag) {
            try {
                Socket ligacao = servidor.accept();			
		HandlerRequest handler = new HandlerRequest(ligacao, system);
		handler.start();
				
            } catch (IOException e) {
                System.out.println("Erro na execucao do servidor: "+e);
		System.exit(1);
            }
        }   
    }
    
    public void stopRunning(){
        flag = false;
    }
}
