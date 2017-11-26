/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Lucas
 */
public interface Logavel {
    
    static final String PATH = "logon.txt";
     
    /**
     * @author Vitor
     * @param usuario
     * @param senha 
     * 
     */
    void insertLogin(String usuario, String senha);
    
}
