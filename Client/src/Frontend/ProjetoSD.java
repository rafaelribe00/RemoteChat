/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Classes.AplicationSystem;

/**
 *
 * @author nicol
 */
public class ProjetoSD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AplicationSystem system = new AplicationSystem();
        Login janelaP = new Login(system);
        janelaP.setVisible(true);
    }
    
}
