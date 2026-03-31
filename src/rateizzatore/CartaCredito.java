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

    public String getNumeroCarta() {
        return numeroCarta;
    }

    public ArrayList<Movimento> getMovimenti() {
        return movimenti;
    }
    
    /**
     * metodo che confronta se l'oggetto istanza è uguale <br>
     * ad un altro oggetto
     * @param obj oggetto da confrontare
     * @return valore che indica se gli oggetti sono uguali o meno
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        CartaCredito carta = (CartaCredito) obj;
        return numeroCarta.equals(carta.getNumeroCarta());
        }
}
