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

    /**
     * Costruttore della classe ModelloRate. <br>
     * Inizializza il modello con l'array di rate generato dal piano.
     * @param rate l'array contenente le singole rate calcolate
     */
    public ModelloRate(Rata[] rate) {
        this.rate = rate;
    }
    
    /**
     * @return il numero di righe della tabella
     */
    @Override
    public int getRowCount() {
        return rate.length;
    }

    /**
     * @return il numero di colonne della tabella
     */
    @Override
    public int getColumnCount() {
        return nomiColonne.length;
    }
    
    /**
     * Restituisce l'intestazione della colonna specificata. <br>
     * @param colonna l'indice della colonna di cui ottenere il nome
     * @return il nome della colonna
     */
    @Override
    public String getColumnName(int colonna) {
        return nomiColonne[colonna];
    }

    /**
     * Restituisce il valore da inserire in una specifica cella della tabella. <br>
     * Provvede a formattare gli importi economici con il simbolo dell'euro.
     * @param rowIndex l'indice della riga
     * @param columnIndex l'indice della colonna
     * @return il dato (Object) da mostrare nella cella
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Rata rata = rate[rowIndex];
        switch (columnIndex) {
            case 0:
                return rata.getDataScadenza();
            case 1:
                return String.format("%.2f €",rata.getImporto());
            case 2:
                return String.format("%.2f €",rata.getCommissione());
            default:
                return "";
        }
    }
    
    /**
     * Impedisce la modifica manuale delle celle della tabella. <br>
     * @param row l'indice della riga
     * @param col l'indice della colonna
     * @return false in ogni caso, rendendo la tabella di sola lettura
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }
}
