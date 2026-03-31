package rateizzatore;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
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
    private JButton btnAddAcquisto;
    private JButton btnAggiungiCarta;
    private JButton btnVisualizzaMovimenti;
    private JButton btnTorna;
    
    private BasePanel actualPanel;
    private AcquistaPanel acquistaPanel;
    private AggiungiCartaPanel addCartaPanel;
    private MovimentiPanel movimentiPanel;
    
    
    
    
    public ClientePanel(MainApp parent, Cliente client) {
        super(parent);
        this.client = client;
        setLayout(new BorderLayout());
        cardLayout = new CardLayout(10, 10);
        panel = new JPanel(cardLayout);
        creaGUI();
        aggiungiListener();
    }
    
    private void creaGUI() {
        JPanel pnlCentro = new JPanel();
        
        JLabel lblTesto = new JLabel("Benvenuto/a " + client.getNome() + " " + client.getCognome());
        lblTesto.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 22));
        
        btnAddAcquisto = new JButton("Registra un acquisto");
        btnAggiungiCarta = new JButton("Aggiungi una carta al conto");
        btnVisualizzaMovimenti = new JButton("Visualizza i tuoi movimenti");
        btnTorna = new JButton("Log Out");
        
        pnlCentro.add(lblTesto, BorderLayout.NORTH);
        pnlCentro.add(btnAddAcquisto, BorderLayout.CENTER);
        pnlCentro.add(btnAggiungiCarta, BorderLayout.CENTER);
        pnlCentro.add(btnVisualizzaMovimenti, BorderLayout.CENTER);
        pnlCentro.add(btnTorna, BorderLayout.SOUTH);
        
        panel.add("CLIENTE", pnlCentro);
        add(panel);
    }
    
    private void aggiungiListener() {
        btnAddAcquisto.addActionListener(this);
        btnAggiungiCarta.addActionListener(this);
        btnVisualizzaMovimenti.addActionListener(this);
        btnTorna.addActionListener(this);
    }
    
    @Override
    void reset() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnTorna) {
            parent.tornaAlMenu();
        } else if(e.getSource() == btnAddAcquisto) {
            if(acquistaPanel == null) {
                acquistaPanel = new AcquistaPanel(parent, client);
                panel.add(acquistaPanel.getClass().getName(), acquistaPanel);
            }
            actualPanel = movimentiPanel;
            
        } else if(e.getSource() == btnAggiungiCarta) {
            if(addCartaPanel == null) {
                addCartaPanel = new AggiungiCartaPanel(parent, client);
                panel.add(addCartaPanel.getClass().getName(), addCartaPanel);
            }
            actualPanel = addCartaPanel;
            
        } else if(e.getSource() == btnVisualizzaMovimenti) {
            if(movimentiPanel == null) {
                movimentiPanel = new MovimentiPanel(parent, client);
                panel.add(movimentiPanel.getClass().getName(), movimentiPanel);
            }
            actualPanel = movimentiPanel;
        } 
        actualPanel.reset();   
        cardLayout.show(panel, actualPanel.getClass().getName());
    }
}
