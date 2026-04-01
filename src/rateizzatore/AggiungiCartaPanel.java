package rateizzatore;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * pannello che permette a un cliente di <br>
 * aggiungere una nuova carta al conto
 */
public class AggiungiCartaPanel extends BasePanel implements ActionListener, MouseListener{
    private Cliente cliente;
    private boolean clickedOnce;
    private JTextField txtNumero;
    private JButton btnTorna;
    
    /**
     * Costruttore della classe AggiungiCartaPanel. <br>
     * Inizializza l'interfaccia utente con il campo di testo per l'inserimento del codice.
     * @param parent il frame principale dell'applicazione
     * @param cliente il cliente che sta registrando la nuova carta
     */
    public AggiungiCartaPanel(MainApp parent, Cliente cliente) {
        super(parent);
        this.cliente = cliente;
        setLayout(new BorderLayout());
        clickedOnce = false;
        creaGUI();
        aggiungiListener();
    }

    private void creaGUI() {
        // nord
        JPanel pnlCentro = new JPanel();
        txtNumero = new JTextField("Inserire il codice della nuova carta", 30);
        pnlCentro.add(txtNumero);
        
        // sud
        JPanel pnlSud = new JPanel();
        btnTorna = new JButton("Torna indietro");
        pnlSud.add(btnTorna);
        
        add(pnlCentro, BorderLayout.CENTER);
        add(pnlSud, BorderLayout.SOUTH);
    }

    private void aggiungiListener() {
        txtNumero.addActionListener(this);
        btnTorna.addActionListener(this);
        
        txtNumero.addMouseListener(this);
    }
    
    @Override
    void reset() {
        txtNumero.setText("Inserire il codice della nuova carta");
        clickedOnce = false;
        parent.setSize(500, 400);

    }

    /**
     * Gestisce le azioni dell'utente in seguito alla pressione dell'invio. <br>
     * Valida l'input e, in caso positivo, salva la nuova carta nei dati persistenti.
     * @param e l'evento generato dall'azione dell'utente
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() != btnTorna) {
            if(txtNumero.getText() == null || "Inserire il codice della nuova carta".equals(txtNumero.getText())
            ) return;
            
            cliente.getConto().aggiungiCarta(new CartaCredito(txtNumero.getText()));
            boolean aggiunto = parent.salvaClienti();
            if(aggiunto) {
                JOptionPane.showMessageDialog(this,
                            "Carta di credito aggiunta con successo",
                            "Carta registrata", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        CardLayout cardLayout = (CardLayout)this.getParent().getLayout();
        cardLayout.show(this.getParent(), "CLIENTE"); 
    }

    /**
     * Gestisce il click del mouse sul campo di testo, <br>
     * svuotando il testo di default al primo click dell'utente.
     * @param e l'evento generato dal click del mouse
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if(!clickedOnce) {
            txtNumero.setText("");
            clickedOnce = true;
        }
    }

    /**
     * Metodo non implementato
     * @param e (non considerato)
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Metodo non implementato
     * @param e (non considerato)
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Metodo non implementato
     * @param e (non considerato)
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Metodo non implementato
     * @param e (non considerato)
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }
}
