package dao;

/**
 * Todas as implementacoes dessa interface terão o login garantido e controlado
 * no sistema
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
     * @param usuario login do usuário
     * @param senha senha do usuário
     *
     */
    void insertLogin(String usuario, String senha);

}
