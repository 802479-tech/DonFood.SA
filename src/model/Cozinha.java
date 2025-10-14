
public class Cozinha {
    private int idCozinha;
    private String pedidosPendentes;

    public Cozinha() {
    }

    public Cozinha(int idCozinha, String pedidosPendentes) {
        this.idCozinha = idCozinha;
        this.pedidosPendentes = pedidosPendentes;
    }

    public int getIdCozinha() {
        return idCozinha;
    }

    public void setIdCozinha(int idCozinha) {
        this.idCozinha = idCozinha;
    }

    public String getPedidosPendentes() {
        return pedidosPendentes;
    }

    public void setPedidosPendentes(String pedidosPendentes) {
        this.pedidosPendentes = pedidosPendentes;
    }
}
