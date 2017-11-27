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
import model.Cliente;

/**
 * Classe que persiste instâncias de Cliente.
 *
 * @author Lucas
 */
public class ClienteDAO extends DAO implements Logavel {

    private static final String PATH = "cliente.txt";
    private static int lastAddedId;

    /**
     * Método que inicializa arquivos de persistência de cliente e inicializa o
     * atributo lastAddedId.
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
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Persiste o cliente especificado por parâmetro no arquivo de clientes.
     *
     * @param cliente O cliente a ser inserido
     */
    public void insert(Cliente cliente) {
        super.insert(PATH,
                ++lastAddedId + ";"
                + cliente.getCpf() + ";"
                + cliente.getTelefone() + ";"
                + cliente.getNome() + ";"
                + cliente.getEndereco()
                + System.getProperty("line.separator") // quebra de linha
        );
    }

    /**
     * Recupera os dados persistidos no arquivo de clientes.
     *
     * @return Clientes persistidos no banco de dados
     */
    public List<Cliente> selectAll() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(PATH));
            List<Cliente> list = new ArrayList<>();
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");

                Cliente cliente = new Cliente();
                cliente.setId(Integer.parseInt(dados[0]));
                cliente.setCpf(dados[1]);
                cliente.setTelefone(Integer.parseInt(dados[2]));
                cliente.setNome(dados[3]);
                cliente.setEndereco(dados[4]);

                list.add(cliente);
            }
            return list;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
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
