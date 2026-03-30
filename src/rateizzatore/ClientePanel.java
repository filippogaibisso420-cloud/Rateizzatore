package rateizzatore;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * classe che visualizza un pannello <br>
 * che permette al cliente di poter <br>
 * accedere a tutte le sue funzioni
 */
public class ClientePanel extends BasePanel implements ActionListener {
    private Cliente client;
    
    private CardLayout cardLayout;
    private JPanel panel;
    private JButton btnAggiungiCarta;
    private JButton btnVisualizzaMovimenti;
    private BasePanel actualPanel;
    private MovimentiPanel movimentiPanel;
    
    public ClientePanel(MainApp parent, Cliente client) {
        super(parent);
        this.client = client;
        
        setLayout(new BorderLayout());
        cardLayout = new CardLayout(10, 10);
        panel = new JPanel(cardLayout);
        
        JPanel pnlCentro = new JPanel();
        JLabel lblTesto = new JLabel("Benvenuto/a " + client.getNome() + " " + client.getCognome());
        btnAggiungiCarta = new JButton("Aggiungi una carta al conto");
        btnVisualizzaMovimenti = new JButton("Visualizza i tuoi movimenti");
        btnAggiungiCarta.addActionListener(this);
        btnVisualizzaMovimenti.addActionListener(this);
    }

    @Override
    void reset() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
