import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PedidoRepository implements CrudRepository<Pedido, Integer> {

    private static final String TABELA = "pedido";

    @Override
    public Integer salvar(Pedido p) throws Exception {
        String sql = "INSERT INTO " + TABELA + " (id_pedido, data_hora, status, total, itens) VALUES (?, ?, ?, ?, ?)";
        try (Connection c = Conexao.obterConexao();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, p.getIdPedido());
            ps.setString(2, p.getDataHora());
            ps.setString(3, p.getStatus());
            ps.setString(4, p.getTotal());
            ps.setString(5, p.getItens());
            ps.executeUpdate();
            return p.getIdPedido();
        }
    }

    @Override
    public boolean atualizar(Pedido p) throws Exception {
        String sql = "UPDATE " + TABELA + " SET data_hora = ?, status = ?, total = ?, itens = ? WHERE id_pedido = ?";
        try (Connection c = Conexao.obterConexao();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getDataHora());
            ps.setString(2, p.getStatus());
            ps.setString(3, p.getTotal());
            ps.setString(4, p.getItens());
            ps.setInt(5, p.getIdPedido());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deletar(Integer id) throws Exception {
        String sql = "DELETE FROM " + TABELA + " WHERE id_pedido = ?";
        try (Connection c = Conexao.obterConexao();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public Optional<Pedido> buscarPorId(Integer id) throws Exception {
        String sql = "SELECT id_pedido, data_hora, status, total, itens FROM " + TABELA + " WHERE id_pedido = ?";
        try (Connection c = Conexao.obterConexao();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapear(rs));
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Pedido> listarTodos() throws Exception {
        String sql = "SELECT id_pedido, data_hora, status, total, itens FROM " + TABELA;
        List<Pedido> lista = new ArrayList<>();
        try (Connection c = Conexao.obterConexao();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    private Pedido mapear(ResultSet rs) throws SQLException {
        Pedido p = new Pedido();
        p.setIdPedido(rs.getInt("id_pedido"));
        p.setDataHora(rs.getString("data_hora"));
        p.setStatus(rs.getString("status"));
        p.setTotal(rs.getString("total"));
        p.setItens(rs.getString("itens"));
        return p;
    }
}
