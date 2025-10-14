
public class Cardapio {
    private int idCardapio;
    private String listaItens;

    public Cardapio() {
    }

    public Cardapio(int idCardapio, String listaItens) {
        this.idCardapio = idCardapio;
        this.listaItens = listaItens;
    }

    public int getIdCardapio() {
        return idCardapio;
    }

    public void setIdCardapio(int idCardapio) {
        this.idCardapio = idCardapio;
    }

    public String getListaItens() {
        return listaItens;
    }

    public void setListaItens(String listaItens) {
        this.listaItens = listaItens;
    }
}
