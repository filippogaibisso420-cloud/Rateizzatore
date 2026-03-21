package rateizzatore;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * classe che descrive una rata
 */
public class Rata implements Serializable {
    private LocalDate dataScadenza;
    private double importo;
    private double commisione;

    public Rata(LocalDate dataScadenza, double importo, double commisione) {
        this.dataScadenza = dataScadenza;
        this.importo = importo;
        this.commisione = commisione;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public double getImporto() {
        return importo;
    }

    public double getCommisione() {
        return commisione;
    }
    
}
