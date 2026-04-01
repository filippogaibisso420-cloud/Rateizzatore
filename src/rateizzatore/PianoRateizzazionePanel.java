package rateizzatore;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * pannello che visualizza un piano di rateizzazione <br>
 * e permette di confermare la richiesta di rateizzazione <br>
 * stampandola su un file di testo
 */
public class PianoRateizzazionePanel extends BasePanel implements ActionListener {
    private Cliente cliente;
    private ArrayList<Movimento> movimenti;
    private double importoTotale;
    private Rata[] rateAttuali;
    private String codiceCarta;
    
    private JLabel lblInfo;
    private JComboBox<Integer> cmbRate;
    private JTable tblPiano;
    private JButton btnConferma;
    private JButton btnTorna;
    
    /**
     * Costruttore della classe PianoRateizzazionePanel. <br>
     * Inizializza l'interfaccia grafica e i dati relativi alla rateizzazione.
     * * @param parent il frame principale dell'applicazione
     * @param cliente il cliente loggato che richiede la rateizzazione
     * @param codiceCarta il codice della carta di credito selezionata
     * @param movimenti la lista dei movimenti da includere nel piano
     * @param importoTotale la somma degli importi dei movimenti scelti
     * @param parent il genitore del pannello
     */
    public PianoRateizzazionePanel(Cliente cliente, ArrayList<Movimento> movimenti,
            double importoTotale, String codiceCarta, MainApp parent) {
        super(parent);
        this.cliente = cliente;
        this.movimenti = movimenti;
        this.importoTotale = importoTotale;
        this.codiceCarta = codiceCarta;
        
        setLayout(new BorderLayout());
        creaGUI();
        aggiungiListener();
        aggiornaTabella();
    }
    
    private void creaGUI() {
        // nord
        JPanel pnlNord = new JPanel();
        lblInfo = new JLabel("Importo totale: " + String.format("%.2f €", importoTotale) + "   Seleziona rate: ");
        lblInfo.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 14));
        
        cmbRate = new JComboBox<>();
        for (int i = 3; i <= 10; i++) {
            cmbRate.addItem(i);
        }
        
        pnlNord.add(lblInfo);
        pnlNord.add(cmbRate);
        add(pnlNord, BorderLayout.NORTH);

        // centro
        JPanel pnlCentro = new JPanel();
        tblPiano = new JTable();
        tblPiano.getTableHeader().setReorderingAllowed(false);
        JScrollPane scroll = new JScrollPane(tblPiano);

        pnlCentro.add(scroll);
        add(pnlCentro, BorderLayout.CENTER);

        // sud
        JPanel pnlSud = new JPanel();
        btnConferma = new JButton("CONFERMA");
        btnTorna = new JButton("Torna indietro");
        
        pnlSud.add(btnConferma);
        pnlSud.add(btnTorna);
        add(pnlSud, BorderLayout.SOUTH);
    }

    private void aggiungiListener() {
        cmbRate.addActionListener(this);
        btnConferma.addActionListener(this);
        btnTorna.addActionListener(this);
    }
    
    @Override
    void reset() {
        lblInfo.setText("Importo totale: " + String.format("%.2f €", importoTotale) + "   Seleziona rate: ");
        cmbRate.setSelectedIndex(0); // Rimette a 3 rate
        aggiornaTabella();
    }
    
    private void aggiornaTabella() {
        int nRate = (Integer) cmbRate.getSelectedItem();

        Movimento movFittizio = new Movimento(null, null, importoTotale, null);
        rateAttuali = movFittizio.rateizza(nRate);
        
        ModelloRate modello = new ModelloRate(rateAttuali);
        tblPiano.setModel(modello);
    }
    
    /**
     * Gestisce gli eventi scatenati dall'interazione dell'utente <br>
     * con i bottoni e la tendina (JComboBox) presenti nel pannello.
     * * @param e l'evento generato dall'interazione dell'utente
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cmbRate) {
            aggiornaTabella();
            
        } else if(e.getSource() == btnTorna) {
            CardLayout cardLayout = (CardLayout) this.getParent().getLayout();
            cardLayout.show(this.getParent(), "MOVIMENTI");
            parent.setSize(800, 800);
            
        } else if(e.getSource() == btnConferma) {
            boolean scritto = stampaSuFile();
            if(scritto) {
                JOptionPane.showMessageDialog(this, 
                    "Piano di rateizzazione salvato in '" + cliente.getNome() + "_" + cliente.getCognome() + ".txt'", 
                    "Richiesta effettuata con successo", JOptionPane.INFORMATION_MESSAGE);
                
                parent.setSize(500, 400);
                CardLayout cardLayout = (CardLayout) this.getParent().getLayout();
                cardLayout.show(this.getParent(), "CLIENTE");
            }
        }
    }

    private boolean stampaSuFile() {
        String pathname = "dati/rateizzazioni/" + cliente.getNome() + "_" + cliente.getCognome() + ".txt";
        File ordine = new File(pathname);
        try {
            if(ordine.createNewFile()) {
                try (FileWriter fileOut = new FileWriter(ordine);
                BufferedWriter writer = new BufferedWriter(fileOut);) {
                    DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    
                    writer.write("\t\tRICHIESTA RATEIZZAZIONE");
                    writer.newLine();
                    
                    writer.write("Cliente: "+ cliente.getNome() + " " + cliente.getCognome());
                    writer.newLine();
                    
                    writer.write("Numero di carta: "+ codiceCarta);
                    writer.newLine();
                    
                    writer.write("Data: "+ LocalDate.now().format(formatterData));
                    writer.newLine();
                    writer.newLine();
                    
                    writer.write("importo totale rateizzato: "+ String.format(Locale.ITALY, "%,.2f", importoTotale));
                    writer.newLine();
                    
                    writer.write("Rate: "+ rateAttuali.length);
                    writer.newLine();
                    
                    ResourceBundle rb = ResourceBundle.getBundle("bundle.app");
                    writer.write("Tasso interesse: "+ rb.getString("tassoInteresse"));
                    writer.newLine();
                    writer.newLine();
                    
                    writer.write("\t\tPIANO RATEIZZAZIONE");
                    writer.newLine();
                    
                    writer.write("--------------------------------------------------");
                    writer.newLine();
                    
                    writer.write("Scadenza\t Importo \t Commissione");
                    writer.newLine();
                    
                    writer.write("--------------------------------------------------");
                    writer.newLine();
                    
                    for (int i = 0; i < rateAttuali.length; i++) {
                        Rata r = rateAttuali[i];
                        writer.write(r.getDataScadenza().format(formatterData) + "\t" + 
                                       String.format(Locale.ITALY, "%,.2f", r.getImporto()) + "\t\t" + 
                                       String.format(Locale.ITALY, "%,.2f", r.getCommissione()));
                        writer.newLine();
                    }
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, 
                    "Impossibile creare il file", 
                    "Errore creazione file", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
