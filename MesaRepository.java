import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MesaRepository implements CrudRepository<Mesa, Integer> {

    private static final String TABELA = "mesa";

    @Override
    public Integer salvar(Mesa m) throws Exception {
        String sql = "INSERT INTO " + TABELA + " (id_mesa, numero, pedidos) VALUES (?, ?, ?)";
        try (Connection c = Conexao.obterConexao();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, m.getIdMesa());
            ps.setString(2, m.getNumero());
            ps.setString(3, m.getPedidos());
            ps.executeUpdate();
            return m.getIdMesa();
        }
    }

    @Override
    public boolean atualizar(Mesa m) throws Exception {
        String sql = "UPDATE " + TABELA + " SET numero = ?, pedidos = ? WHERE id_mesa = ?";
        try (Connection c = Conexao.obterConexao();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, m.getNumero());
            ps.setString(2, m.getPedidos());
            ps.setInt(3, m.getIdMesa());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deletar(Integer id) throws Exception {
        String sql = "DELETE FROM " + TABELA + " WHERE id_mesa = ?";
        try (Connection c = Conexao.obterConexao();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public Optional<Mesa> buscarPorId(Integer id) throws Exception {
        String sql = "SELECT id_mesa, numero, pedidos FROM " + TABELA + " WHERE id_mesa = ?";
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
    public List<Mesa> listarTodos() throws Exception {
        String sql = "SELECT id_mesa, numero, pedidos FROM " + TABELA;
        List<Mesa> lista = new ArrayList<>();
        try (Connection c = Conexao.obterConexao();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    private Mesa mapear(ResultSet rs) throws SQLException {
        Mesa m = new Mesa();
        m.setIdMesa(rs.getInt("id_mesa"));
        m.setNumero(rs.getString("numero"));
        m.setPedidos(rs.getString("pedidos"));
        return m;
    }
}
