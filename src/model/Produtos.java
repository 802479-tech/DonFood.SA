
public class Produtos {
    private int idProdutos;
    private String nome;
    private String quantidade;
    private String unidade;

    public Produtos() {
    }

    public Produtos(int idProdutos, String nome, String quantidade, String unidade) {
        this.idProdutos = idProdutos;
        this.nome = nome;
        this.quantidade = quantidade;
        this.unidade = unidade;
    }

    public int getIdProdutos() {
        return idProdutos;
    }

    public void setIdProdutos(int idProdutos) {
        this.idProdutos = idProdutos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }
}
