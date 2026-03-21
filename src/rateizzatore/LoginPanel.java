package rateizzatore;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Superclasse astratta che implementa una generica schermata <br>
 * di login
 */
abstract public class LoginPanel extends BasePanel implements ActionListener, MouseListener {
    boolean clickedOnce=false;
    JTextField txtNome = new JTextField("nome e cognome", 20);
    JPasswordField password = new JPasswordField("Password:",20);

    public LoginPanel() {
        setBackground(new Color(157, 151, 47));
        setLayout(new BorderLayout());
        password.setEchoChar((char) 0);
        password.addActionListener(this);
        txtNome.addActionListener(this);
        password.addMouseListener(this);
    }

    @Override
    void reset() {
        txtNome.setText("nome e cognome");
        password.setText("Password:");
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(!clickedOnce) {
            password.setText("");
            password.setEchoChar('*');
            clickedOnce=true;
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
