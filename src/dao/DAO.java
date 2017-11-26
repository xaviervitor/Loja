/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vitor
 */
public abstract class DAO {
    
    abstract public void init();
    
    public void insert(String path, String register) {
        try {
            FileWriter writer = new FileWriter(path, true);
            writer.append(register);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private List<Integer> getAllIds(String path){
        List<Integer> list = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String linha;
            while ((linha = br.readLine()) != null) {
                list.add(Integer.parseInt(linha.split(";")[0]));
            }

            if (list.isEmpty()) {
                list.add(0); // Se nao existir nenhum registro, o valor de lastAddedId sera zero
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
    
    protected int getMaxId(String path) {
        return Collections.max(getAllIds(path));
    }
}
