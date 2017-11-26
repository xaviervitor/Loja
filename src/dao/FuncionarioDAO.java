/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Funcionario;

/**
 *
 * @author Lucas
 */
public class FuncionarioDAO extends DAO {

    private static final String PATH = "funcionario.txt";
    private static int lastAddedId;

    @Override
    public void init() {
        File file = new File(PATH);
        if (file.exists()) {
            lastAddedId = getMaxId(PATH);
            return;
        }

        try {
            FileWriter arquivo = new FileWriter(file);
            arquivo.close();
            lastAddedId = 0; // O valor de Id é incrementado antes de ser inserido
        } catch (IOException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insert(Funcionario funcionario) {
        super.insert(PATH,
                ++lastAddedId + ";"
                + funcionario.getNome() + ";"
                + funcionario.getCpf() + ";"
                + funcionario.getUsuario() + ";"
                + funcionario.getSenha()
                + System.getProperty("line.separator") // quebra de linha
        );
    }

    public List<Funcionario> selectAll() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(PATH));
            List<Funcionario> list = new ArrayList<>();
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");

                Funcionario funcionario = new Funcionario();
                funcionario.setId(Integer.parseInt(dados[0]));
                funcionario.setNome(dados[1]);
                funcionario.setCpf(dados[2]);
                funcionario.setUsuario(dados[3]);
                funcionario.setSenha(dados[4]);

                list.add(funcionario);
            }
            return list;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }
}
