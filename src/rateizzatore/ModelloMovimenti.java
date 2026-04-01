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

    /**
     * Costruttore della classe ModelloMovimenti. <br>
     * Inizializza il modello con la lista delle transazioni effettuate.
     * @param movimenti l'ArrayList contenente i movimenti della carta
     */
    public ModelloMovimenti(ArrayList<Movimento> movimenti) {
        this.movimenti = movimenti;
    }
    
    /**
     * @return il numero di righe della tabella
     */
    @Override
    public int getRowCount() {
        return movimenti.size();
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
     * Formatta i valori numerici come valuta (aggiungendo il simbolo dell'euro).
     * @param rowIndex l'indice della riga
     * @param columnIndex l'indice della colonna
     * @return il dato (Object) da mostrare nella cella
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Movimento movimento = movimenti.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return movimento.getDataContabile();
            case 1:
                return movimento.getDataValuta();
            case 2:
                return String.format("%.2f €",movimento.getImporto());
            case 3:
                return movimento.getDescrizione();
            default:
                return "";
        }
    }
    
    /**
    * Restituisce l'oggetto Movimento corrispondente alla riga selezionata
     * @param row riga contenente il movimento
     * @return il movimento alla riga selezionata
    */
    public Movimento getMovimentoAt(int row) {
       return movimenti.get(row);
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
