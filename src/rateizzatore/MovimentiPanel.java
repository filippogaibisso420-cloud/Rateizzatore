package rateizzatore;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * pannello che permette di visualizzare tutti <br>
 * i movimenti  di un cliente e di decidere quali <br>
 * importi rateizzare
 */
public class MovimentiPanel extends BasePanel implements ActionListener {
    Cliente client;
    
    public MovimentiPanel(MainApp parent, Cliente client) {
        super(parent);
    }

    @Override
    void reset() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
