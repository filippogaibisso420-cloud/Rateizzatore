package rateizzatore;

import javax.swing.JPanel;

/**
 * classe base per l'implementazione delle <br>
 * varie schermate dell'applicazione
 */
abstract public class BasePanel extends JPanel {
    protected MainApp parent;
    
    public BasePanel(MainApp parent) {
        this.parent = parent;
    }

    abstract void reset();
}
