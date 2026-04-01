package rateizzatore;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * pannello che permette di aggiungere <br>
 * un movimento
 */
public class AcquistaPanel extends BasePanel implements ActionListener, MouseListener{
    private Cliente cliente;
    
    private boolean clickedCarta;
    private boolean clickedData;
    private boolean clickedImporto;
    private boolean clickedDescrizione;
    
    private JTextField txtCarta;
    private JTextField txtData;
    private JTextField txtImporto;
    private JTextField txtDescrizione;
    private JButton btnTorna;
    
    /**
     * Costruttore della classe AcquistaPanel. <br>
     * Inizializza l'interfaccia con i campi di testo per l'inserimento dei dati.
     * @param parent il frame principale dell'applicazione
     * @param cliente il cliente che sta effettuando il nuovo acquisto
     */
    public AcquistaPanel(MainApp parent, Cliente cliente) {
        super(parent);
        this.cliente = cliente;
        setLayout(new BorderLayout());
        
        clickedCarta = false;
        clickedData = false;
        clickedImporto = false;
        clickedDescrizione = false;
        
        creaGUI();
        aggiungiListener();
    }
    
    private void creaGUI() {
        JPanel pnlCentro = new JPanel();
        txtCarta = new JTextField("Inserire il codice della carta sulla quale "
                + "si vuole aggiungere il movimento", 40);
        txtData = new JTextField("In che data si è fatto l'acquisto?", 40);
        txtImporto = new JTextField("Inserire l'importo dell'acquisto", 40);
        txtDescrizione = new JTextField("Inserire una descrizione dell'acquisto:", 40);
        pnlCentro.add(txtCarta);
        pnlCentro.add(txtData);
        pnlCentro.add(txtImporto);
        pnlCentro.add(txtDescrizione);
        
        JPanel pnlSud = new JPanel();
        btnTorna = new JButton("Torna indietro");
        pnlSud.add(btnTorna);
        
        add(pnlCentro, BorderLayout.CENTER);
        add(pnlSud, BorderLayout.SOUTH);
    }

    private void aggiungiListener() {
        txtCarta.addActionListener(this);
        txtData.addActionListener(this);
        txtImporto.addActionListener(this);
        txtDescrizione.addActionListener(this);
        btnTorna.addActionListener(this);
        
        txtCarta.addMouseListener(this);
        txtData.addMouseListener(this);
        txtImporto.addMouseListener(this);
        txtDescrizione.addMouseListener(this);
    }
    
    @Override
    void reset() {
        txtCarta.setText("Inserire il codice della carta sulla quale si vuole "
                + "aggiungere il movimento");
        txtData.setText("In che data si è fatto l'acquisto?");
        txtImporto.setText("Inserire l'importo dell'acquisto");
        txtDescrizione.setText("Inserire una descrizione dell'acquisto:");
        
        clickedCarta = false;
        clickedData = false;
        clickedImporto = false;
        clickedDescrizione = false;
        
        parent.setSize(500, 400);
    }

    /**
     * Controlla la validità dei dati inseriti nei campi di testo <br>
     * e, se corretti, crea il nuovo movimento aggiornando la carta.
     * @param e l'evento generato dal click sul pulsante
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() != btnTorna) {
            if(txtCarta.getText() == null || "Inserire il codice della carta sulla quale si vuole aggiungere il movimento".equals(txtCarta.getText())
                || txtData.getText() == null
                || "In che data si è fatto l'acquisto?".equals(txtData.getText())
                || txtImporto.getText() == null
                || "Inserire l'importo dell'acquisto".equals(txtImporto.getText())
                || txtDescrizione.getText() == null
                || "Inserire una descrizione dell'acquisto:".equals(txtDescrizione.getText())
            ) return;

            LocalDate dataValuta;
            try {
                dataValuta = LocalDate.parse(txtData.getText());    
            } catch(DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this,
                            "La data va inserita in formato AAAA-MM-GG",
                            "Errore inserimento data", JOptionPane.ERROR_MESSAGE);
                
                return;
            }
            double importo = Double.parseDouble(txtImporto.getText());
            CartaCredito carta = new CartaCredito(txtCarta.getText());
            ArrayList<CartaCredito> carte = cliente.getConto().getCarte();
            
            if(carte.contains(carta) && dataValuta.isBefore(LocalDate.now()) 
                    && importo > 0) {
                int i = carte.indexOf(carta);
                carta = carte.get(i);
                Movimento mov = new Movimento(LocalDate.now(), dataValuta, 
                        importo, txtDescrizione.getText());
                carta.aggiungiMovimento(mov);
                boolean aggiunto = parent.salvaClienti();
                if(aggiunto) {
                    JOptionPane.showMessageDialog(this,
                            "Il movimento è stato aggiunto alla tua carta",
                            "Acquisto effettuato", JOptionPane.INFORMATION_MESSAGE);
                }
                
            } else {
                JOptionPane.showMessageDialog(this,
                            "I dati inseriti non sono corretti",
                            "Errore inserimento dati", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        CardLayout cardLayout = (CardLayout)this.getParent().getLayout();
        cardLayout.show(this.getParent(), "CLIENTE");
    }

    /**
     * Gestisce il click del mouse sui campi di testo, <br>
     * svuotando il testo di default al primo click dell'utente.
     * @param e l'evento generato dal click del mouse
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == txtCarta) {
            if(!clickedCarta) {
                txtCarta.setText("");
                clickedCarta = true;
            }
            
        } else if(e.getSource() == txtData) {
            if(!clickedData) {
                txtData.setText("");
                clickedData = true;
            }
            
        } else if(e.getSource() == txtImporto) {
            if(!clickedImporto) {
                txtImporto.setText("");
                clickedImporto = true;
            }
            
        } else if(e.getSource() == txtDescrizione) {
            if(!clickedDescrizione) {
                txtDescrizione.setText("");
                clickedDescrizione = true;
            }
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
