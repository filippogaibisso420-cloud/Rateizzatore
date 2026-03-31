package rateizzatore;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ResourceBundle;
import javax.swing.JButton;
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
    private boolean clickedNome;
    private boolean clickedConferma;
    private boolean clickedPassword;
    private boolean clickedPasswordBanca;
    
    private CardLayout cardLayout;
    private JPanel panel;

    private JTextField txtNome;
    private JPasswordField password;
    private JPasswordField conferma;
    private JPasswordField passwordBanca;
    private JButton btnTorna;
    
    private BasePanel actualPanel;
    private AmministratorePanel adminPanel;

    public CreaAmministratorePanel(MainApp parent) {
        super(parent);
        clickedNome = false;
        clickedConferma = false;
        clickedPassword = false;
        clickedPasswordBanca = false;
        
        setLayout(new BorderLayout());
        cardLayout = new CardLayout(10, 10);
        panel = new JPanel(cardLayout);
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
        txtNome = new JTextField("Inserire nome e cognome del nuovo dipendente", 30);
        
        password = new JPasswordField("Inserire nuova password:", 30);
        password.setEchoChar((char) 0);
        
        conferma = new JPasswordField("Inserire nuovamente password:", 30);
        conferma.setEchoChar((char) 0);
        
        passwordBanca = new JPasswordField("Autorizza la registrazione con la password della banca:", 30);
        passwordBanca.setEchoChar((char) 0);
        
        btnTorna = new JButton("Torna indietro");
        
        JPanel pnlMid = new JPanel();
        pnlMid.add(txtNome);
        pnlMid.add(password);
        pnlMid.add(conferma);
        pnlMid.add(passwordBanca);
        pnlMid.add(btnTorna, BorderLayout.SOUTH);
        panel.add("CREATE_ADMIN", pnlMid);
        add(panel);
    }
    
    private void aggiungiListener() {
        txtNome.addActionListener(this);
        password.addActionListener(this);
        conferma.addActionListener(this);
        passwordBanca.addActionListener(this);
        btnTorna.addActionListener(this);
        
        txtNome.addMouseListener(this);
        password.addMouseListener(this);
        conferma.addMouseListener(this);
        passwordBanca.addMouseListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnTorna) {
            parent.tornaAlMenu();
        } else {
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
                String[] nominativo = new String[2];
                nominativo = txtNome.getText().split(" ");
                String nome = nominativo[0];
                String cognome = nominativo[1];
                String password = new String(this.password.getPassword());
                Amministratore admin = new Amministratore(nome, cognome, password);

                boolean aggiunto = parent.aggiungiAmministratore(admin);
                if(aggiunto) {
                    JOptionPane.showMessageDialog(this, "Amministratore "
                        + "creato con successo", "registrazione a buon fine",
                        JOptionPane.INFORMATION_MESSAGE);

                    if(adminPanel == null) {
                        adminPanel = new AmministratorePanel(parent, admin);
                        panel.add(adminPanel.getClass().getName(), adminPanel);
                    }
                    actualPanel = adminPanel;
                }
            }
        }
        
        actualPanel.reset();         
        cardLayout.show(panel, actualPanel.getClass().getName());
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
