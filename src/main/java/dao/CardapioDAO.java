package dao;

import model.ItemCardapio;
import model.ItemCardapio.TipoItemCardapio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardapioDAO {

    public void salvar(ItemCardapio item) {
        String sql = item.getId() == 0 ? 
            "INSERT INTO cardapio (nome, tipo, preco) VALUES (?, ?, ?)" :
            "UPDATE cardapio SET nome = ?, tipo = ?, preco = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getNome());
            stmt.setString(2, item.getTipo().name());
            stmt.setDouble(3, item.getPreco());
            if (item.getId() != 0) {
                stmt.setInt(4, item.getId());
            }
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ItemCardapio> listar() {
        List<ItemCardapio> itens = new ArrayList<>();
        String sql = "SELECT * FROM cardapio ORDER BY nome";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ItemCardapio item = new ItemCardapio(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    TipoItemCardapio.valueOf(rs.getString("tipo")),
                    rs.getDouble("preco")
                );
                itens.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itens;
    }

    public void excluir(int id) {
        String sql = "DELETE FROM cardapio WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
