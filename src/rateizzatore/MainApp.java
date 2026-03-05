package rateizzatore;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainApp extends JFrame implements ActionListener{
    JTextField txtUtente;
    
    public MainApp() {
        setTitle("Menù banca");
        setSize(500, 400);
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
        JLabel lblTitolo = new JLabel("Menù gestione banca");
        JPanel pnlNord = new JPanel(); 
        pnlNord.add(lblTitolo);
        pnlNord.add(lblIconaFoto);
        add(pnlNord, BorderLayout.NORTH);
        
        // centro
        txtUtente = new JTextField("Utente", 30);
        JPanel pnlCentro = new JPanel();
        pnlCentro.add(txtUtente);
        add(pnlCentro, BorderLayout.CENTER);
        
        // sud
        
    }

    private void aggiungiListener() {
        txtUtente.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
