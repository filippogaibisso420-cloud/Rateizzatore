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
 * Classe che implementa e visualizza le <br>
 * funzioni di un amministratore
 */
public class AmministratorePanel extends BasePanel implements ActionListener {
    private Amministratore admin;
    private CardLayout cardLayout;
    private JPanel panel;
    private JButton btnTorna;
    private JButton btnCliente;
    private CreaContoPanel creaContoPanel;
    private BasePanel actualPanel;
    
    /**
     * Costruttore della classe AmministratorePanel. <br>
     * Inizializza il pannello principale per le funzioni dell'amministratore, <br>
     * configurando il layout e l'interfaccia grafica iniziale.
     * @param parent il frame principale dell'applicazione
     * @param admin l'amministratore che ha effettuato il login
     */
    public AmministratorePanel(MainApp parent, Amministratore admin) {
        super(parent);
        this.admin = admin;
        setLayout(new BorderLayout());
        cardLayout = new CardLayout(10, 10);
        creaGUI();
    }
    
    private void creaGUI() {
        panel = new JPanel(cardLayout);
        
        JLabel lblTesto = new JLabel("Benvenuto/a " + admin.getNome() + " " + admin.getCognome());
        lblTesto.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 22));
        
        btnCliente = new JButton("Crea un nuovo conto corrente");
        btnCliente.addActionListener(this);
        
        btnTorna = new JButton("Log Out");
        btnTorna.addActionListener(this);
        
        JPanel pnlCentro = new JPanel();
        pnlCentro.add(lblTesto, BorderLayout.NORTH);
        pnlCentro.add(btnCliente, BorderLayout.CENTER);
        pnlCentro.add(btnTorna, BorderLayout.SOUTH);
        panel.add("AMMINISTRATORE", pnlCentro);
        add(panel);
    }
    
    @Override
    void reset() {
    }

    /**
     * Gestisce gli eventi generati dai pulsanti del pannello. <br>
     * Permette di effettuare il log out o di navigare verso <br>
     * la schermata di creazione di un nuovo conto corrente.
     * @param e l'evento generato dall'interazione dell'utente
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnTorna) {
            parent.tornaAlMenu();
        } else if(e.getSource() == btnCliente) {
            if(creaContoPanel == null) {
                creaContoPanel = new CreaContoPanel(parent);
                panel.add(creaContoPanel.getClass().getName(), creaContoPanel);
            }
            actualPanel = creaContoPanel;
            actualPanel.reset();         
            cardLayout.show(panel, actualPanel.getClass().getName());
        }
    }
}
