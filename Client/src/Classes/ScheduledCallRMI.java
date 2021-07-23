/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.util.*;

/**
 *
 * @author nicol
 */
public class ScheduledCallRMI extends TimerTask {

    AplicationSystem system;
    String SERVICE_NAME="/RegistoRemoteServer";
    
    public ScheduledCallRMI(AplicationSystem system){
        this.system = system;
    }
    
    public void run() {
        try {
            RegistoInterface regInt = (RegistoInterface) LocateRegistry.getRegistry(InetAddress.getByName("DESKTOP-BJABO8J").getHostAddress()).lookup(SERVICE_NAME);
            system.getEntireAppUsers().setAppUsers(regInt.getUsers(system.getOnGoingUser(), 0, ""));
            System.out.println("Scheduled Call Runned");
            
        } catch (Exception Except) {
            System.out.println("Erro na execucao do servidor: " + Except);
            System.exit(1);
        }
    }
}
