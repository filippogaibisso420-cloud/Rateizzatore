package rateizzatore;

/**
 * classe che descrive un utente amministratore
 */
public class Amministratore extends Utente {

    /**
     * Costruttore della classe Amministratore. <br>
     * imposta il nome, cognome e password di un amministratore
     * @param nome il nome dell'amministratore
     * @param cognome il cognome dell'amministratore
     * @param password la password dell'amministratore
     */
    public Amministratore(String nome, String cognome, String password) {
        super(nome, cognome, password);
    }
}
