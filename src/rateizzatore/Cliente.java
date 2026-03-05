package rateizzatore;

public class Cliente {
    private String nome;
    private String cognome;
    private ContoCorrente conto;

    public Cliente(String nome, String cognome, ContoCorrente conto) {
        this.nome = nome;
        this.cognome = cognome;
        this.conto = conto;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public ContoCorrente getConto() {
        return conto;
    }
     
}
