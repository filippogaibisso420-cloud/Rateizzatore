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
        double d = Double.parseDouble(rb.getString("tassoInteresse"));
        
        d = d/12/100;
        System.out.println(d);
        BigDecimal bd= new BigDecimal(d);
        double normale = bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
        System.out.println(normale);
        
        double importo = 1000;
        int nRate = 3;
        double temp = Math.pow((1+d), -nRate);
        double pagamentoMensile = importo * (d/(1-temp));
        System.out.println(pagamentoMensile);
        BigDecimal pg = new BigDecimal(pagamentoMensile);
        double arrotondato = pg.setScale(2, RoundingMode.HALF_UP).doubleValue();
        System.out.println(arrotondato);
        System.out.println(Math.round(pagamentoMensile));
    }
    
}
