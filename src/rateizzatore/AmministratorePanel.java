package rateizzatore;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Classe che implementa e visualizza le <br>
 * funzioni di un amministratore
 */
class AmministratorePanel extends BasePanel implements ActionListener {
    CardLayout cardLayout;
    JPanel panel;
    JButton btnTorna;
    JButton btnCliente;
    CreaContoPanel creaContoPanel;
    
    public AmministratorePanel() {
        setBackground(new Color(157, 151, 47));
        setLayout(new BorderLayout());
        cardLayout = new CardLayout(10, 10);
        panel = new JPanel(cardLayout);
        
        JPanel pnlCentro = new JPanel();
        btnCliente = new JButton("Crea un nuovo conto corrente");
        pnlCentro.add(btnTorna);
        
        JPanel pnlSud = new JPanel();
        btnTorna = new JButton("Torna indietro");
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
        if(e.getSource() == btnCliente) {
            if(creaContoPanel == null) {
                creaContoPanel = new CreaContoPanel();
                panel.add(creaContoPanel.getClass().getName(), creaContoPanel);
            }
            
        } else if(e.getSource() == btnTorna) {
            CardLayout cardLayout = (CardLayout)this.getParent().getLayout();
            cardLayout.show(this.getParent(), "MENU");
        }
    }
    
}
