package rateizzatore;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * classe che descrive una carta di credito
 */
public class CartaCredito implements Serializable {
    private String numeroCarta;
    private ArrayList<Movimento> movimenti;

    /**
     * Il costruttore della classe CartaCredito. <br>
     * imposta il numero della carta di credito
     * @param numeroCarta il numero della carta
     */
    public CartaCredito(String numeroCarta) {
        this.numeroCarta = numeroCarta;
        movimenti = new ArrayList<>();
    }
    
    /**
     * metodo che aggiunge un movimento alla carta di credito <br>
     * che viene ordinato cronologicamente in base alle data valuta. <br>
     * Se il movimento è più vecchio di un mese non verrà aggiunto alla lista
     * @param m il movimento da aggiungere
     */
    public void aggiungiMovimento(Movimento m) {
        LocalDate oggi = LocalDate.now();
        LocalDate data = m.getDataValuta();

        int mesiOggi = oggi.getYear() * 12 + oggi.getMonthValue();
        int mesiMovimento = data.getYear() * 12 + data.getMonthValue();
        int differenzaMesi = mesiOggi - mesiMovimento;
        
        if(differenzaMesi > 1) {
            return;
        }
        if(movimenti.isEmpty()) {
            movimenti.addFirst(m);
        } else {
            int i=0;
            while(i < movimenti.size() && m.getDataValuta().isAfter(movimenti.get(i).getDataValuta())) {
                i++;
            }
            movimenti.add(i, m); 
        }
    }

    /**
     * @return il codice della carta 
     */
    public String getNumeroCarta() {
        return numeroCarta;
    }

    /**
     * @return la lista dei movimenti della carta 
     */
    public ArrayList<Movimento> getMovimenti() {
        return movimenti;
    }
    
    /**
     * metodo che confronta se l'oggetto istanza è uguale <br>
     * ad un altro oggetto
     * @param obj oggetto da confrontare
     * @return true se le carte hanno lo stesso numero, false altrimenti
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        CartaCredito carta = (CartaCredito) obj;
        return numeroCarta.equals(carta.getNumeroCarta());
    }
}
