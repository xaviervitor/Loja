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

public class Loja {

    public static void main(String[] args) {
        new CarroDAO().init();
        new FuncionarioDAO().init();
        new ClienteDAO().init();
        
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
