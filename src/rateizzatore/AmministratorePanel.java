package rateizzatore;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Classe che implementa e visualizza le <br>
 * funzioni di un amministratore
 */
class AmministratorePanel extends BasePanel implements ActionListener {
    private CardLayout cardLayout;
    private JPanel panel;
    private JButton btnTorna;
    private JButton btnCliente;
    private CreaContoPanel creaContoPanel;
    private BasePanel actualPanel;
    
    public AmministratorePanel(MainApp parent) {
        super(parent);
        setLayout(new BorderLayout());
        cardLayout = new CardLayout(10, 10);
        creaGUI();
    }
    
    private void creaGUI() {
        panel = new JPanel(cardLayout);
        
        JPanel pnlCentro = new JPanel();
        btnCliente = new JButton("Crea un nuovo conto corrente");
        btnCliente.addActionListener(this);
        pnlCentro.add(btnCliente);
        
        JPanel pnlSud = new JPanel();
        btnTorna = new JButton("Torna indietro");
        btnTorna.addActionListener(this);
        pnlSud.add(btnTorna);
        
        JPanel pnlMid = new JPanel();
        pnlMid.add(pnlCentro, BorderLayout.CENTER);
        pnlMid.add(pnlSud, BorderLayout.SOUTH);
        panel.add("AMMINISTRATORE", pnlMid);
        add(panel);
    }
    
    @Override
    void reset() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnTorna) {
            parent.tornaAlMenu();
        } else if(e.getSource() == btnCliente) {
            if(creaContoPanel == null) {
                creaContoPanel = new CreaContoPanel(parent);
                panel.add(creaContoPanel.getClass().getName(), creaContoPanel);
            }
            actualPanel = creaContoPanel;
            actualPanel.reset();         
            cardLayout.show(panel, actualPanel.getClass().getName());
        }
    }
}
