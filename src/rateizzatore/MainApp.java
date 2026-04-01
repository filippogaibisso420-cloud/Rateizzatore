package rateizzatore;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Classe principale dell'applicazione. <br>
 * Gestisce il frame principale, il caricamento e salvataggio dei dati persistenti <br>
 * (clienti e amministratori) e la navigazione tra le schermate principali.
 */
public class MainApp extends JFrame implements ActionListener {
    private JPanel pnlCentro;
    private JButton btnCliente;
    private JButton btnAmministratore;
    
    private ArrayList<Cliente> clienti;
    private ArrayList<Amministratore> amministratori;
    
    private CardLayout cardLayout;
    private BasePanel actualPanel;
    private LoginAmministratorePanel loginAdminPanel;
    private LoginClientePanel loginClientePanel;
    
    /**
     * Costruttore della classe MainApp. <br>
     * Avvia il caricamento dei database da file, configura la finestra principale <br>
     * e inizializza il layout e i componenti grafici del menù iniziale.
     */
    public MainApp() {
        amministratori = caricaAmministratori();
        clienti = caricaClienti();
        if(clienti == null) clienti = new ArrayList<>();
        if(amministratori == null) amministratori = new ArrayList<>();
        
        setTitle("NOVA AURUM S.p.A");
        setSize(500, 400);

        cardLayout = new CardLayout(10, 10);
        creaGui();
        aggiungiListener();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    
    /**
     * Metodo main principale dell'applicazione. <br>
     * Istanzia la classe MainApp avviando il programma.
     * @param args argomenti passati da riga di comando (non si considerano)
     */
    public static void main(String[] args) {
        new MainApp();
    }

    private void creaGui() {
        // nord
        JLabel lblTitolo = new JLabel("Nova Aurum S.p.A");
        lblTitolo.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 15));
        JPanel pnlNord = new JPanel(); 
        pnlNord.add(lblTitolo);
        add(pnlNord, BorderLayout.NORTH);
        
        // centro
        pnlCentro = new JPanel(cardLayout);
        btnCliente = new JButton("Accedi come cliente");
        btnAmministratore = new JButton("Accedi come amministratore");

        JPanel pnlMenuIniziale = new JPanel();
        pnlMenuIniziale.add(btnAmministratore);
        pnlMenuIniziale.add(btnCliente);
        pnlCentro.add("MENU", pnlMenuIniziale);
        add(pnlCentro, BorderLayout.CENTER);
        
        // sud
        JLabel lblSlogan = new JLabel("NOVA AURUM: Il tuo futuro, il nostro impegno",
                SwingConstants.CENTER);
        lblSlogan.setFont(new Font("Verdana", Font.ITALIC, 11));
        add(lblSlogan, BorderLayout.SOUTH);
    }

    private void aggiungiListener() {
        btnCliente.addActionListener(this);
        btnAmministratore.addActionListener(this);
    }

    /**
     * Gestisce le azioni sui pulsanti del menù iniziale, <br>
     * indirizzando l'utente verso le schermate di login specifiche.
     * @param e l'evento generato dall'interazione dell'utente
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnCliente) {
            if (loginClientePanel == null) {
                loginClientePanel = new LoginClientePanel(this);
                pnlCentro.add(loginClientePanel.getClass().getName(), loginClientePanel);
            }
            actualPanel = loginClientePanel;
            
        } else if(e.getSource() == btnAmministratore) {
            if (loginAdminPanel == null) {
                loginAdminPanel = new LoginAmministratorePanel(this);
                pnlCentro.add(loginAdminPanel.getClass().getName(), loginAdminPanel);
            }
            actualPanel = loginAdminPanel;
        }
        actualPanel.reset();         
        cardLayout.show(pnlCentro, actualPanel.getClass().getName());
    }
    
    /**
     * metodo che carica da file tutti gli amministratori <br>
     * e li salva su una lista
     * @return la lista degli amministratori
     */
    private ArrayList<Amministratore> caricaAmministratori() {
        ArrayList<Amministratore> tmpList = new ArrayList<>();
        boolean eof = false;
        try(FileInputStream fos = new FileInputStream("dati/database/ListaAmministratori.dat");
                ObjectInputStream in = new ObjectInputStream(fos);) {
            while(!eof) {
                tmpList = (ArrayList<Amministratore>) in.readObject();
            }
        } catch (EOFException ex) {
            eof = true;
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this,
                        "ci scusiamo per l'inconveniente ma non è stato possibile "
                                + "recuperare le informazioni sugli amministratori "
                                + "in questo momento, riprovare più tardi",
                        "errore caricamento dati",
                        JOptionPane.ERROR_MESSAGE);
        }
        return tmpList;
    }
    
    /**
     * metodo che carica da file tutti i clienti <br>
     * e li salva su una lista
     * @return la lista dei clienti
     */
    private ArrayList<Cliente> caricaClienti() {
        ArrayList<Cliente> tmpList = new ArrayList<>();
        boolean eof = false;
        try(FileInputStream fos = new FileInputStream("dati/database/ListaClienti.dat");
                ObjectInputStream in = new ObjectInputStream(fos);) {
            while(!eof) {
                tmpList = (ArrayList<Cliente>) in.readObject();
            }
        } catch (EOFException ex) {
            eof = true;
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this,
                        "ci scusiamo per l'inconveniente ma non è stato possibile "
                                + "recuperare le informazioni sui clienti in questo "
                                + "momento, riprovare più tardi",
                        "errore caricamento dati",
                        JOptionPane.ERROR_MESSAGE);
        }
        return tmpList;
    }
    
    /**
     * metodo che permette di aggiungere <br>
     * un'amministratore
     * @param nuovo l'amministratore da aggiungere
     * @return true se l'amministratore è stato aggiunto e salvato correttamente, false altrimenti
     */
    public boolean aggiungiAmministratore(Amministratore nuovo) {
        boolean aggiunto = false;
        if(!amministratori.contains(nuovo)) {
            amministratori.add(nuovo);
            aggiunto = salvaAmministratori();
        }
        return aggiunto;
    }
    
    /**
     * metodo che permette di aggiungere <br>
     * un cliente
     * @param nuovo il cliente da aggiungere
     * @return @return true se il cliente è stato aggiunto e salvato correttamente, false altrimenti
     */
    public boolean aggiungiCliente(Cliente nuovo) {
        boolean aggiunto = false;
        if(!clienti.contains(nuovo)) {
            clienti.add(nuovo);
            aggiunto = salvaClienti();
        }
        return aggiunto;
    }

    /**
     * Sovrascrive il file di salvataggio degli amministratori <br>
     * serializzando la lista attualmente in memoria.
     * @return true se il salvataggio va a buon fine, false in caso di eccezioni
     */
    public boolean salvaAmministratori() {
        try(FileOutputStream fos = new FileOutputStream("dati/database/listaAmministratori.dat");
                ObjectOutputStream out = new ObjectOutputStream(fos);) {
            
            out.writeObject(amministratori);
            return true;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                        "ci scusiamo per l'inconveniente ma non è possibile "
                                + "registrare il nuovo amministratore in questo "
                                + " momento riprovare più tardi",
                        "Errore registrazione amministratore",
                        JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    /**
     * Sovrascrive il file di salvataggio dei clienti <br>
     * serializzando la lista attualmente in memoria.
     * @return true se il salvataggio va a buon fine, false in caso di eccezioni
     */
    public boolean salvaClienti() {
        try(FileOutputStream fos = new FileOutputStream("dati/database/listaClienti.dat");
                ObjectOutputStream out = new ObjectOutputStream(fos);) {
        
            out.writeObject(clienti);
            return true;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                        "ci scusiamo per l'inconveniente ma non è possibile "
                                + "registrare il nuovo cliente in questo momento "
                                + "riprovare più tardi",
                        "Errore registrazione cliente",
                        JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    /**
     * @return la lista contenente tutti i clienti 
     */
    public ArrayList<Cliente> getClienti() {
        return clienti;
    }

    /**
     * @return la lista contenente tuttigli amministratori
     */
    public ArrayList<Amministratore> getAmministratori() {
        return amministratori;
    }
    
    /**
     * Riporta l'utente al menù principale dell'applicazione 
     */
    public void tornaAlMenu() {
        cardLayout.show(pnlCentro, "MENU");
    }
    
}
