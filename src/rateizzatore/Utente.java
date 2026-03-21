package rateizzatore;

/**
 * classe che descrive un utente qualsiasi
 */
abstract public class Utente {
    protected String nome;
    protected String cognome;
    protected String password;

    public Utente(String nome, String cognome, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getPassword() {
        return password;
    }
    
}
