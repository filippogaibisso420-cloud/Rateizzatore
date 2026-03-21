package bundle;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ResourceBundle;

public class LetturaBundle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle("bundle.app");
        String lang = rb.getString("language");
        System.out.println(lang);
        double d = Double.parseDouble(rb.getString("tassoInteresse"));
        d = d/12/100;
        BigDecimal bd= new BigDecimal(d);
        BigDecimal arrotondato = bd.setScale(2, RoundingMode.HALF_UP);
        System.out.println(arrotondato);
        double normale = bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
        System.out.println(normale);
        double importo = 1000;
        int nRate = 10;
        double temp = Math.pow((1+normale), -nRate);
        double pagamentoMensile = importo * (normale/(1-temp));
        System.out.println(pagamentoMensile);
        BigDecimal pg = new BigDecimal(pagamentoMensile);
        arrotondato = pg.setScale(2, RoundingMode.HALF_UP);
        System.out.println(arrotondato);
        System.out.println(Math.round(pagamentoMensile));
    }
    
}
