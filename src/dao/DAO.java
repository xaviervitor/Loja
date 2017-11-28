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
 * Classe abstrata que realiza a persistência dos dados nos respectivos
 * arquivos.
 *
 * @author vitor
 */
public abstract class DAO {

    abstract public void init();

    /**
     * Registra instância fornecida num caminho de arquivo fornecido. Usada para
     * persistência de todas as classes de modelo.
     *
     * @param path caminho do arquivo
     * @param register registro fornecido
     */
    public void insert(String path, String register) {
        try {
            FileWriter writer = new FileWriter(path, true);
            writer.append(register);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Busca por todos os ids em um dado arquivo.
     *
     * @param path caminho para o arquivo de persistência
     *
     * @return lista de ids encontrados no arquivo
     */
    private List<Integer> getAllIds(String path) {
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
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    /**
     * Retorna o id máximo registrado em um dos arquivos de persistência.
     *
     * @param path caminho para o arquivo de persistência
     *
     * @return máximo id encontrado no arquivo
     */
    protected int getMaxId(String path) {
        return Collections.max(getAllIds(path));
    }
}
