
public class Estoque {
    private int idEstoque;
    private String produtos;

    public Estoque() {
    }

    public Estoque(int idEstoque, String produtos) {
        this.idEstoque = idEstoque;
        this.produtos = produtos;
    }

    public int getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(int idEstoque) {
        this.idEstoque = idEstoque;
    }

    public String getProdutos() {
        return produtos;
    }

    public void setProdutos(String produtos) {
        this.produtos = produtos;
    }
}
