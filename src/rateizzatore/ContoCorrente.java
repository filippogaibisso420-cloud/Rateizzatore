package rateizzatore;

import java.io.Serializable;
import java.util.ArrayList;

public class ContoCorrente implements Serializable {
    private String iban;
    private ArrayList<CartaCredito> carte;
    private double plafond;

    public ContoCorrente(String iban, CartaCredito c, double plafond) {
        this.iban = iban;
        carte = new ArrayList();
        carte.add(c);
        this.plafond = plafond;
    }

    public String getIban() {
        return iban;
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
