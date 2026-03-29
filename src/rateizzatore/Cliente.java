package rateizzatore;

public class Cliente extends Utente {
    private ContoCorrente conto;

    public Cliente(String nome, String cognome, String password, ContoCorrente conto) {
        super(nome, cognome, password);
        this.conto = conto;
    }

    public ContoCorrente getConto() {
        return conto;
    }
     
}
