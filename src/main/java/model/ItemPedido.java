package model;

public class ItemPedido {
    private int id;
    private Pedido pedido;
    private ItemCardapio itemCardapio;
    private int quantidade;
    private double precoUnitario;
    private double subtotal;

    public ItemPedido() {}

    public ItemPedido(Pedido pedido, ItemCardapio itemCardapio, int quantidade) {
        this.pedido = pedido;
        this.itemCardapio = itemCardapio;
        this.quantidade = quantidade;
        this.precoUnitario = itemCardapio.getPreco();
        this.subtotal = this.precoUnitario * this.quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public ItemCardapio getItemCardapio() {
        return itemCardapio;
    }

    public void setItemCardapio(ItemCardapio itemCardapio) {
        this.itemCardapio = itemCardapio;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        this.subtotal = this.precoUnitario * this.quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
