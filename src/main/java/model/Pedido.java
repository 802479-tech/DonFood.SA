package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private LocalDate data;
    private double total;
    private String status; // PENDENTE, EM_PREPARO, FINALIZADO
    private int numeroPedido;
    private List<ItemPedido> itens = new ArrayList<>();

    public Pedido() {
        this.data = LocalDate.now();
        this.status = "PENDENTE"; // Valor padrão para novos pedidos
    }

    public Pedido(int id, LocalDate data, double total, String status, int numeroPedido) {
        this.id = id;
        this.data = data;
        this.total = total;
        this.status = status;
        this.numeroPedido = numeroPedido;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public void recalcularTotal() {
        this.total = itens.stream().mapToDouble(ItemPedido::getSubtotal).sum();
    }
}
