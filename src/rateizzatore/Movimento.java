package rateizzatore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ResourceBundle;

/**
 * classe che implementa un movimento bancario composto dalla <br>
 * data contabile (data nella quale la banca ha registrato l'acquisto), <br>
 * data valuta (data nella quale è avvenuto l'acquisto ovvero la data <br>
 * in cui esso inizia a produrre effetti economici), importo e descrizione
 */
public class Movimento  implements Serializable {
    private LocalDate dataContabile;
    private LocalDate dataValuta;
    private double importo;
    private String descrizione;

    /**
     * Costruttore della classe Movimento. <br>
     * Crea un'istanza che rappresenta un'operazione bancaria, memorizzando <br>
     * le date di registrazione e di valuta, l'ammontare e la causale.
     * @param dataContabile la data in cui l'operazione è registrata dalla banca
     * @param dataValuta la data da cui decorrono gli interessi
     * @param importo il valore economico del movimento
     * @param descrizione la causale o descrizione dell'operazione
     */
    public Movimento(LocalDate dataContabile, LocalDate dataValuta, double importo,
            String descrizione) {
        this.dataContabile = dataContabile;
        this.dataValuta = dataValuta;
        this.importo = importo;
        this.descrizione = descrizione;
    }
    
    /**
     * Calcola il piano di ammortamento per la rateizzazione del movimento. <br>
     * Utilizza i parametri di configurazione (commissioni e tassi) per determinare <br>
     * l'importo della rata mensile costante e genera le relative scadenze.
     * @param nRate il numero di mensilità in cui dividere il pagamento
     * @return un array di oggetti Rata contenente il piano di rimborso calcolato
     */
    public Rata[] rateizza(int nRate) {
        ResourceBundle rb = ResourceBundle.getBundle("bundle.app");
        double commissione = Double.parseDouble(rb.getString("commissione"));
        double tassoInteresse = Double.parseDouble(rb.getString("tassoInteresse"));
        double interesseMensile = tassoInteresse/100/12;
        
        double temp = Math.pow((1+interesseMensile), -nRate);
        double pagamentoMensile = importo * (interesseMensile/(1-temp));
        BigDecimal bd2 = new BigDecimal(pagamentoMensile);
        pagamentoMensile = bd2.setScale(2, RoundingMode.HALF_UP).doubleValue();
        
        Rata[] rate = new Rata[nRate];
        for(int i=0; i<rate.length; i++) {
            LocalDate dataScadenza = LocalDate.now().plusMonths(1+i).with(TemporalAdjusters.lastDayOfMonth());
            rate[i] = new Rata(dataScadenza, pagamentoMensile, commissione);
        }
        return rate;
    }
    
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
    
    /**
     * @return data contabile del movimento
     */
    public LocalDate getDataContabile() {
        return dataContabile;
    }
    
    /**
     * @return data valuta del movimento
     */
    public LocalDate getDataValuta() {
        return dataValuta;
    }
}
