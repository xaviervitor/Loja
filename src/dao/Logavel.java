package dao;

/**
 * Todas as implementacoes dessa classe terão o login garantido e controlado no
 * sistema
 *
 * @author Lucas
 */
public interface Logavel {

    static final String PATH = "logon.txt";

    /**
     * Método que persiste usuário e senha de todos as pessoas que podem
     * realizar login no sistema.
     *
     * @author Vitor
     * @param usuario Login do usuário
     * @param senha Senha do usuário
     *
     */
    void insertLogin(String usuario, String senha);

}
