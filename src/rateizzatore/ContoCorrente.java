package rateizzatore;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * classe che descrive un conto correntre
 */
public class ContoCorrente implements Serializable {
    private String codiceConto;
    private ArrayList<CartaCredito> carte;
    private double plafond;

    /**
     * costruttore della classe ContoCorrente. <br>
     * imposta il codice del conto, la prima carta <br>
     * di credito associata al conto e il plafond
     * @param codiceConto
     * @param c 
     * @param plafond tetto massimo che non si può superare quando si rateizzano più movimenti
     */
    public ContoCorrente(String codiceConto, CartaCredito c, double plafond) {
        this.codiceConto = codiceConto;
        carte = new ArrayList();
        carte.add(c);
        this.plafond = plafond;
    }
    
    /**
     * @return il codice del conto 
     */
    public String getCodiceConto() {
        return codiceConto;
    }

    /**
     * @return le carte associate al conto 
     */
    public ArrayList<CartaCredito> getCarte() {
        return carte;
    }

    /**
     * @return il fido del conto
     */
    public double getPlafond() {
        return plafond;
    }
    
    /**
     * associa una carta al conto
     * @param c la carta da associare al conto 
     */
    public void aggiungiCarta(CartaCredito c) {
        carte.add(c);
    }
    
    /**
     * cancella una carta dal conto
     * @param c la carta da eliminare
     */
    public void eliminaCarta(CartaCredito c) {
        carte.remove(c);
    }
}
