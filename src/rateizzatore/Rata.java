package rateizzatore;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * classe che descrive una rata <br>
 * all'interno di un piano di rateizzazione.
 */
public class Rata implements Serializable {
    private LocalDate dataScadenza;
    private double importo;
    private double commissione;

    /**
     * Costruttore della classe Rata. <br>
     * Imposta la data di scadenza, l'importo e la commissione applicata.
     * @param dataScadenza la data in cui scade il pagamento della rata
     * @param importo il valore della rata da pagare
     * @param commissione commissione decisa dalla banca
     */
    public Rata(LocalDate dataScadenza, double importo, double commissione) {
        this.dataScadenza = dataScadenza;
        this.importo = importo;
        this.commissione = commissione;
    }

    /**
     * @return data di scadenza della rata 
     */
    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    /**
     * @return valore da pagare della rata 
     */
    public double getImporto() {
        return importo;
    }
    
    /**
     * @return commissione decisa dalla banca 
     */
    public double getCommissione() {
        return commissione;
    }
    
}
