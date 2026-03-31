package rateizzatore;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * pannello che serve a visualizzare la schermata <br>
 * per il login di un cliente
 */
public class LoginClientePanel extends LoginPanel implements ActionListener {
    private ClientePanel clientPanel;
    
    public LoginClientePanel(MainApp parent) {
        super(parent);
        JLabel lblTesto = new JLabel("Inserire credenziali cliente");
        lblTesto.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 20));
        
        JPanel pnlMid = new JPanel();
        pnlMid.add(lblTesto);
        pnlMid.add(txtNome);
        pnlMid.add(password);
        pnlMid.add(btnTorna);
        panel.add("LOGIN_CLIENT", pnlMid);
        add(panel, BorderLayout.CENTER);
    }
    
    @Override
    void reset() {
        super.reset();
        cardLayout.show(panel, "LOGIN_CLIENT");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if(password.getPassword() == null || "Password:".equals(new String(password.getPassword())) 
                || txtNome.getText() == null || "nome e cognome".equals(txtNome.getText())) return;
        
        String psword = new String(password.getPassword());
        String[] nominativo = txtNome.getText().split(" ");
        String nome = nominativo[0];
        String cognome = nominativo[1];
        ContoCorrente conto = null;
        Cliente client = new Cliente(nome, cognome, psword, conto);
        
        ArrayList<Cliente> clienti = parent.getClienti();
        if(clienti.contains(client)) {
            int i = clienti.indexOf(client);
            client = clienti.get(i);
            
            if(clientPanel == null) {
                clientPanel = new ClientePanel(parent, client);
                panel.add(clientPanel.getClass().getName(), clientPanel);
            }
            actualPanel = clientPanel;
        } else {
            JOptionPane.showMessageDialog(this, "Credenziali errate",
                    "Errore inserimento credenziali", JOptionPane.ERROR_MESSAGE);
        }
        actualPanel.reset();         
        cardLayout.show(panel, actualPanel.getClass().getName());
    }
}
