package rateizzatore;

import java.io.Serializable;

public class Cliente extends Utente implements Serializable {
    private ContoCorrente conto;

    public Cliente(String nome, String cognome, String password, ContoCorrente conto) {
        super(nome, cognome, password);
        this.conto = conto;
    }

    public ContoCorrente getConto() {
        return conto;
    }
     
}
