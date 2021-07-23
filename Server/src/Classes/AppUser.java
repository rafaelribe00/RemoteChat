/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;
import java.util.ArrayList;

public class AppUser implements Serializable{
    
    //variaveis de instancia
    private String nameUser,adressUser,cursoUMinho, ipUser;
    private int portaUser;
    private ArrayList<AppUser> friendsUser;
    private ArrayList<AppUser> requestsOnHold;
    private ArrayList<Feed> feed;
    
    //construtor
    public AppUser (String nameUser,String adressUser,String cursoUMinho, String ipUser, int portaUser) {
        this.nameUser=nameUser;
        this.adressUser=adressUser;
        this.cursoUMinho=cursoUMinho;
        this.ipUser=ipUser;
        this.portaUser=portaUser;
        friendsUser = new ArrayList<>();
        requestsOnHold = new ArrayList<>();
        feed = new ArrayList<>();
    }
    
     public AppUser (String nome,String email,String curso) {
        this.nameUser=nome;
        this.adressUser=email;
        this.cursoUMinho=curso;
        friendsUser = new ArrayList<>();
        requestsOnHold = new ArrayList<>();
        feed = new ArrayList<>();
    }
    
    public AppUser(){
        
    }
    
    //modificadores
    public void setNameUser(String novoNome) {
        nameUser=novoNome;
    }
    public void setAdressUser(String novoEmail) {
        adressUser=novoEmail;
    }
    public void setCursoUMinho(String novoCurso) {
        cursoUMinho=novoCurso;
    }
    public void setPortaUser(int novaPorta) {
        portaUser = novaPorta;
    }
    public void setIpUser(String novoIp){
        ipUser = novoIp;
    }
    
        public void setFriendsUser(ArrayList<AppUser> amigos){
        this.friendsUser = amigos;
    }
    
    public void setListaRequestsOnHold(ArrayList<AppUser> pendentes){
        this.requestsOnHold = pendentes;
    }
    
    //seletores
    public String getNameUser() {
        return nameUser;
    }
    public String getAdressUser() {
        return adressUser;
    }
    public String getCursoUMinho() {
        return cursoUMinho;
    }
    public int getPortaUser(){
        return portaUser;
    }
    public String getIpUser(){
        return ipUser;
    }
    
    public ArrayList<Feed> getFeed(){
        return feed;
    }
    
    public ArrayList<AppUser> getFriendsUser(){
        return friendsUser;
    }
    
    public ArrayList<AppUser> getRequestsOnHold(){
        return requestsOnHold;
    }
    
    
    public void insereFriend(AppUser utilizador) {
        friendsUser.add(utilizador);
    }
    
    public void insereRequest(AppUser utilizador){
        requestsOnHold.add(utilizador);
    }
    
    public void removeFriend(String email) {
        AppUser us = new AppUser();
        
        for(AppUser u : friendsUser){
            if(u.getAdressUser().equals(email)){
                us = u;
            }
        }
        
        friendsUser.remove(us);
    }
    
    public void removeRequest(String email){
        AppUser us = new AppUser();
        
        for(AppUser u : requestsOnHold){
            if(u.getAdressUser().equals(email)){
                us = u;
            }
        }
        
        requestsOnHold.remove(us);
    }
    
    public void insereFeed(Feed feed){
        this.feed.add(feed);
    }

    //outros metodos
        //toString
    @Override
    public String toString(){
        return "Nome: " +nameUser +"\n" +"Curso: " + cursoUMinho;
    }
}