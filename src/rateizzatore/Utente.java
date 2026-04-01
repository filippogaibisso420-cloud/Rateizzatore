package rateizzatore;

import java.io.Serializable;

/**
 * classe che descrive un utente qualsiasi
 */
abstract public class Utente implements Serializable {
    protected String nome;
    protected String cognome;
    protected String password;

    /**
     * Costruttore della classe astratta Utente.<br>
     * imposta il nome, cognome e password di un utente generico
     * @param nome il nome dell'utente
     * @param cognome il cognome dell'utente
     * @param password la password dell'utente
     */
    public Utente(String nome, String cognome, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.password = password;
    }

    /**
     * @return nome dell'utente 
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return cognome dell'utente 
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * @return password dell'utente 
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * metodo che confronta se l'oggetto istanza è uguale <br>
     * ad un altro oggetto
     * @param obj oggetto da confrontare
     * @return valore che indica se gli oggetti sono uguali o meno
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Utente utente = (Utente) obj;
        return password.equals(utente.getPassword()) && nome.equals(utente.getNome())
                    && cognome.equals(utente.getCognome());
        }
}