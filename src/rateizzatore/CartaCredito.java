package rateizzatore;

import java.io.Serializable;
import java.util.ArrayList;

public class CartaCredito implements Serializable {
    private String numeroCarta;
    private ArrayList<Movimento> movimenti;


    public CartaCredito(String numeroCarta) {
        this.numeroCarta = numeroCarta;
        movimenti = new ArrayList<>();
    }
    
    public void aggiungiMovimento(Movimento m) {
        movimenti.add(m);
    }
    
}
