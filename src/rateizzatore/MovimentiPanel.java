package rateizzatore;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.TableModel;

/**
 * pannello che permette di visualizzare tutti <br>
 * i movimenti  di un cliente e di decidere quali <br>
 * importi rateizzare
 */
public class MovimentiPanel extends BasePanel implements ActionListener {
    Cliente cliente;
    
    
    public MovimentiPanel(MainApp parent, Cliente cliente) {
        super(parent);
        this.cliente = cliente;
        setLayout(new BorderLayout());
        ArrayList<CartaCredito> carte = cliente.getConto().getCarte();
        ArrayList<Movimento> movimenti = carte.get(0).getMovimenti();
        
        TableModel modelloDati = new ModelloMovimenti(movimenti);
    }

    @Override
    void reset() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
