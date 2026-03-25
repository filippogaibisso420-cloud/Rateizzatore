package rateizzatore;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * pannello che serve a visualizzare la schermata <br>
 * per il login di un amministratore
 */
public class LoginAmministratorePanel extends LoginPanel implements ActionListener {
    JLabel lblTesto;
    JButton btnAggiungi;
    BasePanel actualPanel;
    AmministratorePanel adminPanel;
    CreaAmministratorePanel creaAdminPanel;
    
    public LoginAmministratorePanel() {
        super();
        creaGUI();
    }
    
    private void creaGUI() {
        // nord
        lblTesto = new JLabel("Inserire credenziali amministratore");
        lblTesto.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 15));
        JPanel pnlNord = new JPanel();
        pnlNord.add(lblTesto);
        
        // centro
        JPanel pnlCentro = new JPanel();
        pnlCentro.add(txtNome);
        pnlCentro.add(password);
        
        // sud
        btnAggiungi = new JButton("Registra nuovo amministratore");
        JPanel pnlSud = new JPanel();
        pnlSud.add(btnAggiungi);
        btnAggiungi.addActionListener(this);
        
        JPanel pnlMid = new JPanel();
        pnlMid.add(pnlNord);
        pnlMid.add(pnlCentro);
        pnlMid.add(pnlSud);
        
        panel.add("LOGIN", pnlMid);
        add(panel, BorderLayout.CENTER);
    }
    
    @Override
    void reset() {
        super.reset();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnAggiungi) {
            if(creaAdminPanel == null) {
                creaAdminPanel = new CreaAmministratorePanel();
                panel.add(creaAdminPanel.getClass().getName(), creaAdminPanel);
            }
            actualPanel = creaAdminPanel;
        } else {
            if(password.getPassword() == null || "Password".equals(new String(password.getPassword())) 
                || txtNome.getText() == null || "nome e cognome".equals(txtNome.getText())) return;
        
            String psword = new String(password.getPassword());
            String[] nominativo = txtNome.getText().split(" ");
            String nome = nominativo[0];
            String cognome = nominativo[1];
            Amministratore admin = new Amministratore(nome, cognome, psword);

            boolean eof = false;
            try(FileInputStream fos = new FileInputStream("/dati/listaAmministratori.dat");
                    ObjectInputStream in = new ObjectInputStream(fos);) {
                boolean trovato = false;
                while(!eof && !trovato) {
                    Amministratore temp = (Amministratore)in.readObject();
                    if(admin.equals(temp)) {
                        if(adminPanel == null) {
                            adminPanel = new AmministratorePanel();
                            panel.add(adminPanel.getClass().getName(), adminPanel);
                        }
                        actualPanel = adminPanel;
                        trovato = true;
                    }
                }

                if(!trovato) JOptionPane.showMessageDialog(this, "Credenziali errate",
                        "Errore inserimento credenziali", JOptionPane.ERROR_MESSAGE);

            } catch (EOFException ex) {
                eof=true;
            } catch (IOException ex) {
                System.out.println("Errore nell'apertura del file");
            } catch (ClassNotFoundException ex) {
                System.out.println("Classe cliente non trovata");
            }
        }
        actualPanel.reset();         
        cardLayout.show(panel, actualPanel.getClass().getName());
    }
}
