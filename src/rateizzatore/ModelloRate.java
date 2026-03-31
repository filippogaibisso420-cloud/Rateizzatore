package rateizzatore;

import javax.swing.table.AbstractTableModel;

/**
 * Modello dati per la tabella che  <br>
 * visualizza il piano di rateizzazione <br>
 * di un cliente.
 */
public class ModelloRate extends AbstractTableModel {
    private Rata[] rate;
    private String[] nomiColonne = {"Data Scadenza", "Importo", "Commissione"};

    public ModelloRate(Rata[] rate) {
        this.rate = rate;
    }
    
    @Override
    public int getRowCount() {
        return rate.length;
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
        Rata rata = rate[rowIndex];
        switch (columnIndex) {
            case 0:
                return rata.getDataScadenza();
            case 1:
                return rata.getImporto();
            case 2:
                return rata.getCommissione();
            default:
                return "";
        }
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }
}
