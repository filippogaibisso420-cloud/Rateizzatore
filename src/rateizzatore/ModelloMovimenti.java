package rateizzatore;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * Modello dati per la tabella che  <br>
 * visualizza i movimenti di un cliente.
 */
public class ModelloMovimenti extends AbstractTableModel {
    private ArrayList<Movimento> movimenti;
    private String[] nomiColonne = {"Data Contabile", "Data Valuta", "Importo", "Descrizione"};

    public ModelloMovimenti(ArrayList<Movimento> movimenti) {
        this.movimenti = movimenti;
    }
    
    @Override
    public int getRowCount() {
        return movimenti.size();
    }

    @Override
    public int getColumnCount() {
        return nomiColonne.length;
    }
    
    @Override
    public String getColumnName(int colonna) {
        return nomiColonne[colonna];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Movimento movimento = movimenti.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return movimento.getDataContabile();
            case 1:
                return movimento.getDataValuta();
            case 2:
                return movimento.getImporto();
            case 3:
                return movimento.getDescrizione();
            default:
                return "";
        }
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }
}
