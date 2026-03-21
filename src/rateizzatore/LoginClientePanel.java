package rateizzatore;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * pannello che serve a visualizzare la schermata <br>
 * per il login di un cliente
 */
class LoginClientePanel extends LoginPanel implements ActionListener {
    JLabel lblTesto = new JLabel("Inserire credenziali cliente");
    
    public LoginClientePanel() {
        super();
        lblTesto.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 15));
        JPanel panel = new JPanel();
        panel.add(lblTesto);
        panel.add(txtNome);
        panel.add(password);
        add(panel, BorderLayout.CENTER);
    }
    
    
    @Override
    void reset() {
        super.reset();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
