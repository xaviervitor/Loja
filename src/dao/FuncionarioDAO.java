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
 * Classe que persiste instâncias de Funcionário
 *
 * @author Lucas
 */
public class FuncionarioDAO extends DAO implements Logavel {

    private static final String PATH = "funcionario.txt";
    private static int lastAddedId;

    /**
     * Método que inicializa arquivos de persistência de funcionário e
     * inicializa o atributo lastAddedId.
     *
     * Se o arquivo não existir, é criado e é atribuido 0 ao atributo
     * lastAddedId. Se o arquivo existir, o atribuido ao atributo o maior id
     * presente no arquivo.
     */
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

    /**
     * Persiste o funcionario especificado por parâmetro no arquivo de
     * funcionários.
     *
     * @param funcionario o funcionário a ser inserido
     */
    public void insert(Funcionario funcionario) {
        try {
            FileWriter writer = new FileWriter(PATH, true);
            writer.append(++lastAddedId + ";"
                    + funcionario.getNome() + ";"
                    + funcionario.getCpf() + ";"
                    + funcionario.getSalario()
                    + System.getProperty("line.separator")
            );
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Recupera os dados persistidos no arquivo de funcionários.
     *
     * @return funcionários persistidos no banco de dados
     */
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
                funcionario.setSalario(Integer.parseInt(dados[3]));

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

    @Override
    public void insertLogin(String usuario, String senha) {
        try {
            FileWriter writer = new FileWriter(Logavel.PATH, true);
            writer.append(usuario + ";" + senha + System.getProperty("line.separator"));
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
