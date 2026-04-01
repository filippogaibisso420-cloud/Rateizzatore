package rateizzatore;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * pannello che permette di visualizzare tutti <br>
 * i movimenti del mese corrente e precedente di <br>
 * un cliente e di decidere quali importi rateizzare
 */
public class MovimentiPanel extends BasePanel implements ActionListener {
    private Cliente cliente;
    private CardLayout cardLayout;
    
    private ArrayList<CartaCredito> carte;
    private ArrayList<Movimento> movimenti;
    
    private JPanel panel;
    private JComboBox<String> cmbCarte;
    private JTable tblMovimenti;
    private JButton btnRateizza;
    private JButton btnTorna;
    
    private BasePanel actualPanel;
    private PianoRateizzazionePanel rateizzaPanel;
    
    /**
     * Costruttore della classe MovimentiPanel. <br>
     * Inizializza l'interfaccia grafica e carica i movimenti della prima carta.
     * @param parent il frame principale dell'applicazione
     * @param cliente il cliente di cui mostrare i movimenti
     */
    public MovimentiPanel(MainApp parent, Cliente cliente) {
        super(parent);
        this.cliente = cliente;
        carte = cliente.getConto().getCarte();
        movimenti = carte.get(0).getMovimenti();
        setLayout(new BorderLayout());
        cardLayout = new CardLayout(10, 10);
        
        creaGUI();
        aggiungiListener();
    }
    
    private void creaGUI() {
        // nord
        panel = new JPanel(cardLayout);
        
        JPanel pnlNord = new JPanel();
        JLabel lblTesto = new JLabel("Seleziona carta:");
        lblTesto.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 14));
        cmbCarte = new JComboBox<>();
        aggiornaCombo();
        pnlNord.add(lblTesto);
        pnlNord.add(cmbCarte);
        
        // centro
        JPanel pnlCentro = new JPanel();
        ModelloMovimenti modelloDati = new ModelloMovimenti(movimenti);
        tblMovimenti = new JTable(modelloDati);
        tblMovimenti.getTableHeader().setReorderingAllowed(false);
        JScrollPane scroll = new JScrollPane(tblMovimenti);
        pnlCentro.add(scroll);

        // sud
        JPanel pnlSud = new JPanel();
        btnTorna = new JButton("Torna indietro");
        btnRateizza = new JButton("Rateizza Movimento");
        pnlSud.add(btnRateizza);
        pnlSud.add(btnTorna);
        
        JPanel pnlMid = new JPanel();
        pnlMid.add(pnlNord, BorderLayout.NORTH);
        pnlMid.add(pnlCentro, BorderLayout.CENTER);
        pnlMid.add(pnlSud, BorderLayout.SOUTH);
        panel.add("MOVIMENTI", pnlMid);
        add(panel);
        
    }

    private void aggiungiListener() {
        cmbCarte.addActionListener(this);
        btnRateizza.addActionListener(this);
        btnTorna.addActionListener(this);
    }
    
    @Override
    void reset() {
        aggiornaCombo();
        aggiornaTabella();
    }

    /**
     * Gestisce le azioni dell'utente sui pulsanti della schermata <br>
     * e l'aggiornamento dei dati al cambio di carta nella tendina.
     * @param e l'evento generato dall'interazione dell'utente
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnTorna) {
            parent.setSize(500, 400);
            CardLayout cardLayout = (CardLayout)this.getParent().getLayout();
            cardLayout.show(this.getParent(), "CLIENTE");
        } else if(e.getSource() == btnRateizza) {

            int[] righeSelezionate = tblMovimenti.getSelectedRows();
            if(righeSelezionate.length == 0) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Devi selezionare almeno un movimento dalla tabella",
                    "Nessun movimento selezinato", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            ModelloMovimenti modello = (ModelloMovimenti) tblMovimenti.getModel();
            double importoTotale = 0;
            ArrayList<Movimento> movimentiSelezionati = new ArrayList<>();

            for(int i = 0; i < righeSelezionate.length; i++) {
                Movimento m = modello.getMovimentoAt(righeSelezionate[i]);
                movimentiSelezionati.add(m);
                importoTotale = importoTotale + m.getImporto();
            }
            
            double plafond = cliente.getConto().getPlafond();
            if(importoTotale > plafond) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Non puoi rateizzare! L'importo totale (" + importoTotale + "€) supera il tuo plafond (" + plafond + "€).", 
                    "Errore Plafond", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(rateizzaPanel == null) {
                String selezionato = (String) cmbCarte.getSelectedItem();
                String[] codiceSelezionato = selezionato.split(" ");
                CartaCredito carta = new CartaCredito(codiceSelezionato[1]);
                
                rateizzaPanel = new PianoRateizzazionePanel(cliente, movimentiSelezionati, 
                        importoTotale, carta.getNumeroCarta(), parent);
                panel.add(rateizzaPanel.getClass().getName(), rateizzaPanel);
            }
            
            parent.setSize(600, 500);
            actualPanel = rateizzaPanel;
            actualPanel.reset();         
            cardLayout.show(panel, actualPanel.getClass().getName());
        } else if(e.getSource() == cmbCarte) {
            aggiornaTabella();
        }
    }

    private void aggiornaCombo() {
        cmbCarte.removeAllItems();
        for (int i = 0; i < carte.size(); i++) {
            cmbCarte.addItem("Carta " + carte.get(i).getNumeroCarta());
        }
    }

    private void aggiornaTabella() {
        String selezionato = (String) cmbCarte.getSelectedItem();
        if (selezionato == null) return;
        
        String[] codiceSelezionato = selezionato.split(" ");
        CartaCredito carta = new CartaCredito(codiceSelezionato[1]);
        int i = carte.indexOf(carta);
        carta = carte.get(i);
        tblMovimenti.setModel(new ModelloMovimenti(carta.getMovimenti()));
    }
    
}
