package rateizzatore;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Superclasse astratta che implementa una generica schermata <br>
 * di login
 */
abstract public class LoginPanel extends BasePanel implements ActionListener, MouseListener {
    protected boolean clickedPassword;
    protected boolean clickedNome;
    protected CardLayout cardLayout;
    protected JPanel panel;
    protected JTextField txtNome;
    protected JPasswordField password;
    protected JButton btnTorna;
    protected BasePanel actualPanel;
    
    
    public LoginPanel(MainApp parent) {
        super(parent);
        clickedPassword = false;
        clickedNome = false;
        
        setLayout(new BorderLayout());
        cardLayout = new CardLayout(10, 10);
        panel = new JPanel(cardLayout);
        
        txtNome = new JTextField("nome e cognome", 20);
        password = new JPasswordField("Password:",20);
        password.setEchoChar((char) 0);
        btnTorna = new JButton("Torna indietro");
        aggiungiListener();
        
    }
    
    private void aggiungiListener() {
        txtNome.addActionListener(this);
        password.addActionListener(this);
        btnTorna.addActionListener(this);
        
        txtNome.addMouseListener(this);
        password.addMouseListener(this);
    }
    
    @Override
    void reset() {
        txtNome.setText("nome e cognome");
        password.setText("Password:");
        password.setEchoChar((char) 0);
        
        clickedNome = false;
        clickedPassword = false;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnTorna) {
            parent.tornaAlMenu();
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
