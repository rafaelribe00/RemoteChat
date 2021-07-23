/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.net.*;
import java.io.*;
import java.util.*;
import Frontend.PaginaInicial;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author nicol
 */
public class HandlerRequest extends Thread {

    Socket broadband;
    AplicationSystem system;
    BufferedReader in;
    PrintWriter out;
    String SERVICE_NAME="/RegistoRemoteServer";

    public HandlerRequest(Socket broadband, AplicationSystem system) {
        this.broadband = broadband;
        this.system = system;
        try {
            this.in = new BufferedReader(new InputStreamReader(broadband.getInputStream()));
            this.out = new PrintWriter(broadband.getOutputStream());

        } catch (IOException e) {
            System.out.println("Exec error: " + e);
            System.exit(1);
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("Connection accepted in ip: " + broadband.getInetAddress() + " in port " + broadband.getPort());

            String response;
            String msg = in.readLine();
            System.out.println("Request=" + msg);

            StringTokenizer tokens = new StringTokenizer(msg);
            String metodo = tokens.nextToken();

            if (metodo.equals("EnviarPedidoAmizade")) {
                response = "201";
                String email = tokens.nextToken();

                try {
                    RegistoInterface regInt = (RegistoInterface) LocateRegistry.getRegistry(InetAddress.getByName("DESKTOP-BJABO8J").getHostAddress()).lookup(SERVICE_NAME);
                    system.getEntireAppUsers().setAppUsers(regInt.getUsers(system.getOnGoingUser(), 0, ""));

                } catch (Exception Except) {
                    System.out.println("Erro na execucao do servidor: " + Except);
                    System.exit(1);
                }

                for (AppUser utilizador : system.getEntireAppUsers().getAppUsers()) {
                    if (utilizador.getAdressUser().equals(email)) {
                        if (!system.getOnGoingUser().getRequestsOnHold().contains(utilizador)) {
                            system.getOnGoingUser().insereRequest(utilizador);
                        }
                    }
                }

                System.out.println(response);
                out.println(response);
            } else if (metodo.equals("ResponderPedidoAmizade")) {
                response = "200";
                String email = tokens.nextToken();
                String resposta = tokens.nextToken();

                if (resposta.equals("YES")) {
                    for (AppUser utilizador : system.getEntireAppUsers().getAppUsers()) {
                        if (utilizador.getAdressUser().equals(email)) {
                            system.getOnGoingUser().insereFriend(utilizador);
                            system.getOnGoingUser().removeRequest(utilizador.getAdressUser());
                        }
                    }
                }

                System.out.println(response);
                out.println(response);
            } else if (metodo.equals("EnviarEstado")) {
                response = "200";
                String nome = tokens.nextToken();
                String estado = tokens.nextToken();
                String estadoFormatado = estado.replaceAll("&nbsp;", " ");

                Feed feed = new Feed(nome, estadoFormatado);
                system.getOnGoingUser().insereFeed(feed);

                System.out.println(response);
                out.println(response);
            } else {
                out.println("404");
            }

            out.flush();
            in.close();
            out.close();
            broadband.close();
        } catch (IOException e) {
            System.out.println("Exec error: " + e);
            System.exit(1);
        }
    }
}
