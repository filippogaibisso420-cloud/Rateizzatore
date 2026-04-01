package rateizzatore;

/**
 * classe che descrive un cliente della banca
 */
public class Cliente extends Utente {
    private ContoCorrente conto;

    /**
     * Costruttore della classe Cliente. <br>
     * imposta il nome, cognome e password di un cliente
     * @param nome il nome dell'amministratore
     * @param cognome il cognome dell'amministratore
     * @param password la password dell'amministratore
     * @param conto il conto corrente associato al cliente
     */
    public Cliente(String nome, String cognome, String password, ContoCorrente conto) {
        super(nome, cognome, password);
        this.conto = conto;
    }

    /**
     * @return il conto corrente del cliente 
     */
    public ContoCorrente getConto() {
        return conto;
    }
     
}
