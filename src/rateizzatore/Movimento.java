package rateizzatore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ResourceBundle;

/**
 * classe che implementa un movimento bancario composto dalla <br>
 * data contabile, data valuta, importo e descrizione
 */
public class Movimento  implements Serializable {
    private LocalDate dataContabile;
    private LocalDate dataValuta;
    private double importo;
    private String descrizione;

    public Movimento(LocalDate dataContabile, LocalDate dataValuta, double importo,
            String descrizione) {
        this.dataContabile = dataContabile;
        this.dataValuta = dataValuta;
        this.importo = importo;
        this.descrizione = descrizione;
    }
    
    public Rata[] rateizza(int nRate) {
        ResourceBundle rb = ResourceBundle.getBundle("bundle.app");
        double commissione = Double.parseDouble(rb.getString("commissione"));
        double tassoInteresse = Double.parseDouble(rb.getString("tassoInteresse"));
        double interesseMensile = tassoInteresse/100/12;
        BigDecimal bd1 = new BigDecimal(interesseMensile);
        interesseMensile = bd1.setScale(2, RoundingMode.HALF_UP).doubleValue();
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

    public LocalDate getDataContabile() {
        return dataContabile;
    }

    public LocalDate getDataValuta() {
        return dataValuta;
    }
    
    
}
