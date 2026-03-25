package rateizzatore;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
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
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainApp extends JFrame implements ActionListener {
    JPanel pnlCentro;
    private JTextField txtUtente;
    private JButton btnCliente;
    private JButton btnAmministratore;
    private ArrayList<Cliente> clienti = new ArrayList();
    CardLayout cardLayout;
    BasePanel actualPanel;
    LoginAmministratorePanel loginAdminPanel;
    LoginClientePanel loginClientePanel;
    
    public MainApp() {
        setTitle("Menù banca");
        setSize(500, 400);
        setBackground(new Color(157, 151, 47));
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
            try(FileInputStream fos = new FileInputStream("/dati/ListaClienti.dat"); 
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
            try(FileOutputStream fos = new FileOutputStream("/dati/ListaClienti.dat"); 
                    ObjectOutputStream out = new ObjectOutputStream(fos)) {
                
            } catch (IOException ex) {
                System.out.println("Errore nell'apertura del file");
            }
        } else if(e.getSource() == btnAmministratore) {
            if (loginAdminPanel == null) {
                loginAdminPanel = new LoginAmministratorePanel();
                pnlCentro.add(loginAdminPanel.getClass().getName(), loginAdminPanel);
            }
            actualPanel = loginAdminPanel;
        }
        actualPanel.reset();         
        cardLayout.show(pnlCentro, actualPanel.getClass().getName());
    }
}
