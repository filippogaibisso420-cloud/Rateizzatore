package rateizzatore;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InserimentoClienteDialog extends BaseDialog implements ActionListener {
    private JTextField txtRisposta;
    private String nome;
    private String cognome;
    
    public InserimentoClienteDialog(JFrame owner, String title) {
        super(owner, title);
        setSize(300, 200);
        setTitle("Aggiungi nuovo cliente");
        creaGui();
        
        setVisible(true);
        
    }

    @Override
    void reset() {
        
    }
    
            
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == txtRisposta) {
            String s = txtRisposta.getText();
            String[] string = s.split(" ");
            nome = string[0];
            cognome = string[1];
            
        }
    }

    private void creaGui() {
        // nord
        JLabel lblTitolo = new JLabel("Diventa nostro cliente");
        JPanel pnlNord = new JPanel();
        pnlNord.add(lblTitolo);
        add(pnlNord, BorderLayout.NORTH);
        
        // centro
        JLabel lblDomanda = new JLabel("Inserisci il tuo nome");
        txtRisposta = new JTextField("", 15);
        JPanel pnlCentro = new JPanel();
        pnlCentro.add(lblDomanda);
        pnlCentro.add(txtRisposta);
        add(pnlCentro, BorderLayout.CENTER);
        txtRisposta.addActionListener(this);
        
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }
    
}
