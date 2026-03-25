package rateizzatore;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * pannello che permette di registrare un nuovo <br>
 * amministratore su file
 */
public class CreaAmministratorePanel extends BasePanel implements ActionListener, 
        MouseListener {
    boolean clickedNome;
    boolean clickedConferma;
    boolean clickedPassword;
    boolean clickedPasswordBanca;
    JTextField txtNome;
    JPasswordField password;
    JPasswordField conferma;
    JPasswordField passwordBanca;

    public CreaAmministratorePanel() {
        clickedNome = false;
        clickedConferma = false;
        clickedPassword = false;
        clickedPasswordBanca = false;
        setBackground(new Color(157, 151, 47));
        setLayout(new BorderLayout());
        creaGUI();
        aggiungiListener();
    }
    
    @Override
    void reset() {
        txtNome.setText("Inserire nome e cognome del nuovo dipendente");
        password.setText("Inserire nuova password:");
        conferma.setText("Inserire nuovamente password:");
        passwordBanca.setText("Autorizza la registrazione con la password della banca:");
        
        password.setEchoChar((char) 0);
        conferma.setEchoChar((char) 0);
        passwordBanca.setEchoChar((char) 0);
        
        clickedNome = false;
        clickedPassword = false;
        clickedConferma = false;
        clickedPasswordBanca = false;
    }

    private void creaGUI() {
        JPanel panel = new JPanel();
        txtNome = new JTextField("Inserire nome e cognome del nuovo dipendente", 30);
        
        password = new JPasswordField("Inserire nuova password:", 30);
        password.setEchoChar((char) 0);
        
        conferma = new JPasswordField("Inserire nuovamente password:", 30);
        conferma.setEchoChar((char) 0);
        
        passwordBanca = new JPasswordField("Autorizza la registrazione con la password della banca:", 30);
        passwordBanca.setEchoChar((char) 0);
        
        panel.add(txtNome);
        panel.add(password);
        panel.add(conferma);
        panel.add(passwordBanca);
        panel.setBackground(new Color(157, 151, 47));
        add(panel);
    }
    
    private void aggiungiListener() {
        txtNome.addActionListener(this);
        password.addActionListener(this);
        conferma.addActionListener(this);
        passwordBanca.addActionListener(this);
        
        txtNome.addMouseListener(this);
        password.addMouseListener(this);
        conferma.addMouseListener(this);
        passwordBanca.addMouseListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(password.getPassword() == null || "Inserire nuova password:".equals(new String(password.getPassword())) 
                || conferma.getPassword() == null 
                || "Inserire nuovamente password:".equals(new String(conferma.getPassword()))
                || passwordBanca.getPassword() == null
                || "Autorizza la registrazione con la password della banca".equals(new String(passwordBanca.getPassword()))
                || txtNome.getText() == null || "nome e cognome".equals(txtNome.getText())) return;
        
        boolean error = false;
        String temp = new String(password.getPassword());
        if(!temp.equals(new String(conferma.getPassword()))) {
            error = true;
            JOptionPane.showMessageDialog(this,
                    "La password inserita e la password di conferma sono diverse",
                    "Password sbagliata", JOptionPane.ERROR_MESSAGE);
        }
        
        ResourceBundle rb = ResourceBundle.getBundle("bundle.app");
        String passwordSistema = rb.getString("password");
        if(!passwordSistema.equals(new String(passwordBanca.getPassword()))) {
            error = true;
            JOptionPane.showMessageDialog(this,
                    "La password della banca è errata",
                    "Password banca sbagliata", JOptionPane.ERROR_MESSAGE);
        }
        
        if(!error) {
            
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == txtNome) {
            if(!clickedNome) {
                txtNome.setText("");
                clickedNome = true;
            }
            
        } else if(e.getSource() == password) {
            if(!clickedPassword) {
                password.setText("");
                password.setEchoChar('*');
                clickedPassword = true;
            }
            
        } else if(e.getSource() == passwordBanca) {
            if(!clickedPasswordBanca) {
              passwordBanca.setText("");
              passwordBanca.setEchoChar('*');
              clickedPasswordBanca = true;  
            }
            
        } else if(e.getSource() == conferma) {
            if(!clickedConferma) {
                conferma.setText("");
                conferma.setEchoChar('*');
                clickedConferma = true;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
