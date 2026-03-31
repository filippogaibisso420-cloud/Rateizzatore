package rateizzatore;

import java.awt.BorderLayout;

/**
 * pannello che permette a un cliente di <br>
 * aggiungere una nuova carta al conto
 */
public class AggiungiCartaPanel extends BasePanel {
    private Cliente client;
    
    public AggiungiCartaPanel(MainApp parent, Cliente client) {
        super(parent);
        this.client = client;
        setLayout(new BorderLayout());
    }

    @Override
    void reset() {
    }
    
}
