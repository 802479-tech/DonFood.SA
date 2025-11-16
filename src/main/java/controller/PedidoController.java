package controller;

import dao.PedidoDAO;
import model.Pedido;
import java.util.List;

public class PedidoController {
    private PedidoDAO pedidoDAO = new PedidoDAO();

    public void registrarPedido(Pedido pedido) {
        pedido.recalcularTotal();
        pedidoDAO.salvar(pedido);
    }

    public List<Pedido> listarPedidosAtivos() {
        return pedidoDAO.listarPedidosAtivos();
    }

    public void atualizarStatus(int pedidoId, String novoStatus) {
        pedidoDAO.atualizarStatus(pedidoId, novoStatus);
    }
}
