
public class Pedido {
    private int idPedido;
    private String dataHora;
    private String status;
    private String total;
    private String itens;

    public Pedido() {
    }

    public Pedido(int idPedido, String dataHora, String status, String total, String itens) {
        this.idPedido = idPedido;
        this.dataHora = dataHora;
        this.status = status;
        this.total = total;
        this.itens = itens;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getItens() {
        return itens;
    }

    public void setItens(String itens) {
        this.itens = itens;
    }
}
