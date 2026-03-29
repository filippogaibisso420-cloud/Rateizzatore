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
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainApp extends JFrame implements ActionListener {
    private JPanel pnlCentro;
    private JTextField txtUtente;
    private JButton btnCliente;
    private JButton btnAmministratore;
    
    private ArrayList<Cliente> clienti;
    private ArrayList<Amministratore> amministratori;
    
    private CardLayout cardLayout;
    private BasePanel actualPanel;
    private LoginAmministratorePanel loginAdminPanel;
    private LoginClientePanel loginClientePanel;
    
    public MainApp() {
        amministratori = caricaAmministratori();
        clienti = caricaClienti();
        if(clienti == null) clienti = new ArrayList<>();
        if(amministratori == null) amministratori = new ArrayList<>();
        
        setTitle("Menù banca");
        setSize(500, 400);

        cardLayout = new CardLayout(10, 10);
        creaGui();
        aggiungiListener();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    /**
     * main principale dell'applicazione
     * @param args
     */
    public static void main(String[] args) {
        new MainApp();
    }

    private void creaGui() {
        // nord
        URL url = getClass().getResource("/img/Logo.png");
        JLabel lblIconaFoto = new JLabel(new ImageIcon(url));
        JLabel lblTitolo = new JLabel("Nova Aurum s.p.a.");
        lblTitolo.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 15));
        JPanel pnlNord = new JPanel(); 
        pnlNord.add(lblIconaFoto);
        pnlNord.add(lblTitolo);
        add(pnlNord, BorderLayout.NORTH);
        
        
        // centro
        pnlCentro = new JPanel(cardLayout);
        txtUtente = new JTextField("Cliente", 25);
        JLabel lblUtente = new JLabel("Inserire il cognome di un cliente nel nostro database");
        btnCliente = new JButton("Accedi come cliente");
        btnAmministratore = new JButton("Accedi come amministratore");

//        pnlCentro.add(lblUtente);
//        pnlCentro.add(txtUtente);
        JPanel pnlMenuIniziale = new JPanel();
        pnlMenuIniziale.add(btnAmministratore);
        pnlMenuIniziale.add(btnCliente);
        pnlCentro.add("MENU", pnlMenuIniziale);
        add(pnlCentro, BorderLayout.CENTER);
        
        // sud
        
    }

    private void aggiungiListener() {
        txtUtente.addActionListener(this);
        btnCliente.addActionListener(this);
        btnAmministratore.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == txtUtente) {
            if(txtUtente.getText() == null || txtUtente.getText() == "Cliente") return;
            boolean eof=false;
            try(FileInputStream fos = new FileInputStream("dati/ListaClienti.dat"); 
                    ObjectInputStream in = new ObjectInputStream(fos)) {
                while(!eof) {
                    Cliente tmp = (Cliente)in.readObject();
                    if(txtUtente.getText().contains(tmp.getCognome())) {
                        
                    }
                }
            } catch (EOFException ex) {
                eof=true;
            } catch (IOException ex) {
                System.out.println("Errore nell'apertura del file");
            } catch (ClassNotFoundException ex) {
                System.out.println("Classe cliente non trovata all'interno del file");
            }
        } else if(e.getSource() == btnCliente) {
            try(FileOutputStream fos = new FileOutputStream("dati/ListaClienti.dat"); 
                    ObjectOutputStream out = new ObjectOutputStream(fos)) {
                
            } catch (IOException ex) {
                System.out.println("Errore nell'apertura del file");
            }
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
        try(FileInputStream fos = new FileInputStream("dati/ListaAmministratori.dat");
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
        try(FileInputStream fos = new FileInputStream("dati/ListaClienti.dat");
                ObjectInputStream in = new ObjectInputStream(fos);) {
            while(!eof) {
                tmpList = (ArrayList<Cliente>) in.readObject();
            }
        } catch (EOFException ex) {
            eof = true;
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this,
                        "ci scusiamo per l'inconveniente ma non è stato possibile "
                                + "recuperare le informazioni sui clienti in questo"
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
     * @return valore che indica se l'amministratore è stato aggiunto o meno
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
     * @return valore che indica se il cliente è stato aggiunto o meno
     */
    public boolean aggiungiCliente(Cliente nuovo) {
        boolean aggiunto = false;
        if(!clienti.contains(nuovo)) {
            clienti.add(nuovo);
            aggiunto = salvaClienti();
        }
        return aggiunto;
    }

    private boolean salvaAmministratori() {
        try(FileOutputStream fos = new FileOutputStream("dati/listaAmministratori.dat");
                ObjectOutputStream out = new ObjectOutputStream(fos);) {
            
            out.writeObject(amministratori);
            return true;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                        "ci scusiamo per l'inconveniente ma non è possibile "
                                + "registrare il nuovo amministratore in questo "
                                + "momento riprovare più tardi",
                        "Errore registrazione amministratore",
                        JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    private boolean salvaClienti() {
        try(FileOutputStream fos = new FileOutputStream("dati/listaClienti.dat");
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

    public ArrayList<Cliente> getClienti() {
        return clienti;
    }

    public ArrayList<Amministratore> getAmministratori() {
        return amministratori;
    }
    
    public void tornaAlMenu() {
        cardLayout.show(pnlCentro, "MENU");
    }
    
}
