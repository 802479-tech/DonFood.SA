package model;

public class ItemCardapio {
    private int id;
    private String nome;
    private TipoItemCardapio tipo;
    private double preco;

    public enum TipoItemCardapio {
        PRATO, BEBIDA
    }

    public ItemCardapio() {}

    public ItemCardapio(int id, String nome, TipoItemCardapio tipo, double preco) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoItemCardapio getTipo() {
        return tipo;
    }

    public void setTipo(TipoItemCardapio tipo) {
        this.tipo = tipo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return nome; 
    }
}
