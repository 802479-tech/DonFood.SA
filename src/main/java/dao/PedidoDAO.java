package dao;

import model.Pedido;
import model.ItemPedido;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public int proximoNumeroPedido() {
        String sql = "SELECT MAX(numero_pedido) FROM pedido WHERE data = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(LocalDate.now()));
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) + 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1; // Retorna 1 se não houver pedidos hoje
    }

    public void salvar(Pedido pedido) {
        String sqlPedido = "INSERT INTO pedido (data, total, status, numero_pedido) VALUES (?, ?, ?, ?)";
        String sqlItens = "INSERT INTO pedido_itens (pedido_id, cardapio_id, quantidade, preco_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmtPedido = conn.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS)) {
                pedido.setNumeroPedido(proximoNumeroPedido());

                stmtPedido.setDate(1, Date.valueOf(pedido.getData()));
                stmtPedido.setDouble(2, pedido.getTotal());
                stmtPedido.setString(3, pedido.getStatus());
                stmtPedido.setInt(4, pedido.getNumeroPedido());
                stmtPedido.executeUpdate();

                try (ResultSet generatedKeys = stmtPedido.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        pedido.setId(generatedKeys.getInt(1));
                    }
                }

                try (PreparedStatement stmtItens = conn.prepareStatement(sqlItens)) {
                    for (ItemPedido item : pedido.getItens()) {
                        stmtItens.setInt(1, pedido.getId());
                        stmtItens.setInt(2, item.getItemCardapio().getId());
                        stmtItens.setInt(3, item.getQuantidade());
                        stmtItens.setDouble(4, item.getPrecoUnitario());
                        stmtItens.setDouble(5, item.getSubtotal());
                        stmtItens.addBatch();
                    }
                    stmtItens.executeBatch();
                }
                conn.commit();

            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pedido> listarPedidosAtivos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido WHERE status IN ('PENDENTE', 'EM_PREPARO') ORDER BY numero_pedido";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                pedidos.add(new Pedido(
                        rs.getInt("id"),
                        rs.getDate("data").toLocalDate(),
                        rs.getDouble("total"),
                        rs.getString("status"),
                        rs.getInt("numero_pedido")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    public void atualizarStatus(int pedidoId, String novoStatus) {
        String sql = "UPDATE pedido SET status = ? WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novoStatus);
            stmt.setInt(2, pedidoId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
