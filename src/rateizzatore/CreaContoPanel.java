package rateizzatore;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * pannello che permette di creare un nuovo <br>
 * conto corrente
 */
public class CreaContoPanel extends BasePanel implements ActionListener, MouseListener {
    private boolean clickedNome;
    private boolean clickedPassword;
    private boolean clickedCodiceConto;
    private boolean clickedPlafond;
    private boolean clickedCodiceCarta;
    
    private JTextField txtNome;
    private JPasswordField password;
    private JTextField txtCodiceConto;
    private JTextField txtPlafond;
    private JTextField txtCodiceCarta;
    
    public CreaContoPanel(MainApp parent) {
        super(parent);
        setLayout(new BorderLayout());
        
        clickedNome = false;
        clickedPassword = false;
        clickedCodiceConto = false;
        clickedPlafond = false;
        clickedCodiceCarta = false;
        
        creaGUI();
        aggiungiListener();
    }
    
    private void creaGUI() {
        txtNome = new JTextField("A chi verrà intestato questo conto?", 30);
        password = new JPasswordField("Inserire la nuova password del conto", 30);
        password.setEchoChar((char) 0);
        txtCodiceConto = new JTextField("Inserire il codice del conto", 30);
        txtPlafond = new JTextField("Inserire il plafond per questo conto");
        txtCodiceCarta = new JTextField("Inserire il codice della prima carta da aggiungere al conto", 30);
        
        JPanel pnlCentro = new JPanel();
        pnlCentro.add(txtNome);
        pnlCentro.add(password);
        pnlCentro.add(txtCodiceConto);
        pnlCentro.add(txtPlafond);
        pnlCentro.add(txtCodiceCarta);
        add(pnlCentro, BorderLayout.CENTER);
    }

    private void aggiungiListener() {
        txtNome.addActionListener(this);
        password.addActionListener(this);
        txtCodiceConto.addActionListener(this);
        txtPlafond.addActionListener(this);
        txtCodiceCarta.addActionListener(this);
        
        txtNome.addMouseListener(this);
        password.addMouseListener(this);
        txtCodiceConto.addMouseListener(this);
        txtPlafond.addMouseListener(this);
        txtCodiceCarta.addMouseListener(this);
    }
    
    @Override
    void reset() {
        txtNome.setText("A chi verrà intestato questo conto?");
        password.setText("Inserire la nuova password del conto");
        txtCodiceConto.setText("Inserire il codice del conto");
        txtPlafond.setText("Inserire il plafond per questo conto");
        txtCodiceCarta.setText("Inserire il codice della prima carta da aggiungere al conto");
        
        password.setEchoChar((char) 0);
        clickedNome = false;
        clickedPassword = false;
        clickedCodiceConto = false;
        clickedPlafond = false;
        clickedCodiceCarta = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(txtNome.getText() == null || "A chi verrà intestato questo conto?".equals(txtNome.getText())
                || password.getPassword() == null 
                || "Inserire la nuova password del conto".equals(new String(password.getPassword())) 
                || txtCodiceConto.getText() == null
                || "Inserire il codice del conto".equals(txtCodiceConto.getText())
                || txtPlafond.getText() == null
                || "Inserire il plafond per questo conto".equals(txtPlafond.getText())
                || txtCodiceCarta.getText() == null
                || "Inserire il codice della prima carta da aggiungere al conto".equals(txtCodiceCarta.getText())
            ) return;
        
        String[] nominativo = new String[2];
        nominativo = txtNome.getText().split(" ");
        String nome = nominativo[0];
        String cognome = nominativo[1];
        String password = new String(this.password.getPassword());
        String codiceConto = txtCodiceConto.getText();
        double plafond = Double.parseDouble(txtPlafond.getText());
        String codiceCarta = txtCodiceCarta.getText();
            
        CartaCredito carta = new CartaCredito(codiceCarta);
        ContoCorrente conto = new ContoCorrente(codiceConto, carta, plafond);
        Cliente cliente = new Cliente(nome, cognome, password, conto);
            
        boolean aggiunto = parent.aggiungiCliente(cliente);
           
        if(aggiunto) {
            JOptionPane.showMessageDialog(this, "Cliente creato "
                + "con successo", "registrazione a buon fine",
                JOptionPane.INFORMATION_MESSAGE);
        }
        
        CardLayout cardLayout = (CardLayout)this.getParent().getLayout();
        cardLayout.show(this.getParent(), "AMMINISTRATORE");
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
            
        } else if(e.getSource() == txtCodiceConto) {
            if(!clickedCodiceConto) {
                txtCodiceConto.setText("");
                clickedCodiceConto = true;
            }
            
        } else if(e.getSource() == txtPlafond) {
            if(!clickedPlafond) {
                txtPlafond.setText("");
                clickedPlafond = true;
            }
            
        } else if(e.getSource() == txtCodiceCarta) {
            if(!clickedCodiceCarta) {
                txtCodiceCarta.setText("");
                clickedCodiceCarta = true;
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
