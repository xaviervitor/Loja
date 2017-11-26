package loja;

import dao.ClienteDAO;
import dao.CarroDAO;
import dao.FuncionarioDAO;
import view.StartView;

public class Loja {

    public static void main(String[] args) {
        new CarroDAO().init();
        new FuncionarioDAO().init();
        new ClienteDAO().init();
        
        new StartView().setVisible(true);
    }

}
