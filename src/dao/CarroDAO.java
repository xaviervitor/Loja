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
import model.Carro;

/**
 * Classe que persiste instâncias de Carro
 *
 * @author Lucas
 */
public class CarroDAO extends DAO {

    private static final String PATH = "carro.txt";
    private static int lastAddedId;

    /**
     * Método que inicializa arquivos de persistência de carro e inicializa o
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
            Logger.getLogger(CarroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Persiste o carro especificado por parâmetro no arquivo de carros.
     *
     * @param carro o carro a ser inserido
     */
    public void insert(Carro carro) {
        super.insert(PATH,
                ++lastAddedId + ";"
                + carro.getChassi() + ";"
                + carro.getFabricante() + ";"
                + carro.getModelo() + ";"
                + carro.getVersao() + ";"
                + carro.getAno() + ";"
                + carro.getCor()
                + System.getProperty("line.separator") // quebra de linha
        );
    }

    /**
     * Recupera os dados persistidos no arquivo de carros.
     *
     * @return carros persistidos no banco de dados
     */
    public List<Carro> selectAll() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(PATH));
            List<Carro> list = new ArrayList<>();
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");

                Carro carro = new Carro();
                carro.setId(Integer.parseInt(dados[0]));
                carro.setChassi(dados[1]);
                carro.setFabricante(dados[2]);
                carro.setModelo(dados[3]);
                carro.setVersao(dados[4]);
                carro.setAno(Integer.parseInt(dados[5]));
                carro.setCor(dados[6]);

                list.add(carro);
            }
            return list;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CarroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CarroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }
}
