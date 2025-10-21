import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutosRepository implements CrudRepository<Produtos, Integer> {

    private static final String TABELA = "produtos";

    @Override
    public Integer salvar(Produtos p) throws Exception {
        String sql = "INSERT INTO " + TABELA + " (id_produtos, nome, quantidade, unidade) VALUES (?, ?, ?, ?)";
        try (Connection c = Conexao.obterConexao();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, p.getIdProdutos());
            ps.setString(2, p.getNome());
            ps.setString(3, p.getQuantidade());
            ps.setString(4, p.getUnidade());
            ps.executeUpdate();
            return p.getIdProdutos();
        }
    }

    @Override
    public boolean atualizar(Produtos p) throws Exception {
        String sql = "UPDATE " + TABELA + " SET nome = ?, quantidade = ?, unidade = ? WHERE id_produtos = ?";
        try (Connection c = Conexao.obterConexao();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getNome());
            ps.setString(2, p.getQuantidade());
            ps.setString(3, p.getUnidade());
            ps.setInt(4, p.getIdProdutos());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deletar(Integer id) throws Exception {
        String sql = "DELETE FROM " + TABELA + " WHERE id_produtos = ?";
        try (Connection c = Conexao.obterConexao();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public Optional<Produtos> buscarPorId(Integer id) throws Exception {
        String sql = "SELECT id_produtos, nome, quantidade, unidade FROM " + TABELA + " WHERE id_produtos = ?";
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
    public List<Produtos> listarTodos() throws Exception {
        String sql = "SELECT id_produtos, nome, quantidade, unidade FROM " + TABELA;
        List<Produtos> lista = new ArrayList<>();
        try (Connection c = Conexao.obterConexao();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    private Produtos mapear(ResultSet rs) throws SQLException {
        Produtos p = new Produtos();
        p.setIdProdutos(rs.getInt("id_produtos"));
        p.setNome(rs.getString("nome"));
        p.setQuantidade(rs.getString("quantidade"));
        p.setUnidade(rs.getString("unidade"));
        return p;
    }
}
