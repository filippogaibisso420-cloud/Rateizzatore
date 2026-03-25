package rateizzatore;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Superclasse astratta che implementa una generica schermata <br>
 * di login
 */
abstract public class LoginPanel extends BasePanel implements ActionListener, MouseListener {
    boolean clickedPassword;
    boolean clickedNome;
    CardLayout cardLayout;
    JPanel panel;
    JTextField txtNome;
    JPasswordField password;
    
    
    public LoginPanel() {
        clickedPassword = false;
        setBackground(new Color(157, 151, 47));
        setLayout(new BorderLayout());
        cardLayout = new CardLayout(10, 10);
        panel = new JPanel(cardLayout);
        
        txtNome = new JTextField("nome e cognome", 20);
        password = new JPasswordField("Password:",20);
        password.setEchoChar((char) 0);
        aggiungiListener();
        
    }
    
    private void aggiungiListener() {
        txtNome.addActionListener(this);
        password.addActionListener(this);
        
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
