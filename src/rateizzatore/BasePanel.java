package rateizzatore;

import javax.swing.JPanel;

/**
 * classe base per l'implementazione delle <br>
 * varie schermate dell'applicazione
 */
abstract public class BasePanel extends JPanel {
    protected MainApp parent;
    
    /**
     * crea il collegamento tra il pannello <br>
     * e il padre
     * @param parent il padre del pannello
     */
    public BasePanel(MainApp parent) {
        this.parent = parent;
    }
    
    /**
     * metodo che ripristina il panel allo <br>
     * stato originale
     */
    abstract void reset();
}
