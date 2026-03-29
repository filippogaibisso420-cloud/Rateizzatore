package rateizzatore;

import java.io.Serializable;
import java.util.ArrayList;

public class ContoCorrente implements Serializable {
    private String codiceConto;
    private ArrayList<CartaCredito> carte;
    private double plafond;

    public ContoCorrente(String codiceConto, CartaCredito c, double plafond) {
        this.codiceConto = codiceConto;
        carte = new ArrayList();
        carte.add(c);
        this.plafond = plafond;
    }

    public String getCodiceConto() {
        return codiceConto;
    }

    public ArrayList getCarte() {
        return carte;
    }

    public double getPlafond() {
        return plafond;
    }
    
    public void aggiungiCarta(CartaCredito c) {
        carte.add(c);
    }
    
    public void eliminaCarta(CartaCredito c) {
        carte.remove(c);
    }
}
