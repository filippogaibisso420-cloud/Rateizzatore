package rateizzatore;

import java.io.Serializable;
import java.util.ArrayList;

public class CartaCredito implements Serializable {
    private String numeroCarta;
    private ArrayList<Movimento> meseCorrente;
    private ArrayList<Movimento> mesePrecedente;

    public CartaCredito(String numeroCarta) {
        this.numeroCarta = numeroCarta;
        mesePrecedente = new ArrayList();
        meseCorrente = new ArrayList();
    }

    public CartaCredito(String numeroCarta, ArrayList mesePrecedente) {
        this.numeroCarta = numeroCarta;
        this.mesePrecedente = mesePrecedente;
        meseCorrente = new ArrayList();
    }
    
    public void aggiungiMovimento(Movimento m) {
        meseCorrente.add(m);
    }
    
}
