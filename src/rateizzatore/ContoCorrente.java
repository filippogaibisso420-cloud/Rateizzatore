package rateizzatore;

import java.util.ArrayList;

public class ContoCorrente {
    private ArrayList carte;
    private double plafond;

    public ContoCorrente(double plafond) {
        this.plafond = plafond;
    }

    public ArrayList getCarte() {
        return carte;
    }

    public double getPlafond() {
        return plafond;
    }   
}
