package loja;

import dao.ClienteDAO;
import dao.CarroDAO;
import dao.FuncionarioDAO;
import dao.Logavel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.LoginView;

/**
 * Classe principal. Inicializa todos os arquivos de persistência para o banco
 * de dados / controle de login e cria a visualização de login para inicializar
 * a interface gráfica.
 *
 * @author vitor
 */
public class Loja {

    public static void main(String[] args) {

        // Inicializa os arquivos e as constantes de inserção no banco de dados.
        new CarroDAO().init();
        new FuncionarioDAO().init();
        new ClienteDAO().init();

        // Cria o arquivo para controle de login, se não existir.
        File file = new File(Logavel.PATH);
        if (!file.exists()) {
            try {
                FileWriter writer = new FileWriter(file);
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        new LoginView().setVisible(true);
    }
}
