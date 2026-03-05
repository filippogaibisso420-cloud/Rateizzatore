package rateizzatore;

import java.time.LocalDate;

/**
 * classe che implementa un movimento bancario composto dalla <br>
 * data contabile, data valuta, importo e descrizione
 */
public class Movimento {
    private LocalDate dataContabile;
    private LocalDate dataValuta;
    private double importo;
    private String descrizione;

    /**
     * @return importo del movimento
     */
    public double getImporto() {
        return importo;
    }
    /**
     * @return descrizione del movimento
     */
    public String getDescrizione() {
        return descrizione;
    }
    
    
}
