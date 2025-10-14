
public class Mesa {
    private int idMesa;
    private String numero;
    private String pedidos;

    public Mesa() {
    }

    public Mesa(int idMesa, String numero, String pedidos) {
        this.idMesa = idMesa;
        this.numero = numero;
        this.pedidos = pedidos;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPedidos() {
        return pedidos;
    }

    public void setPedidos(String pedidos) {
        this.pedidos = pedidos;
    }
}
