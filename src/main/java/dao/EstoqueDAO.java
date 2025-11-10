package dao;

import model.ItemEstoque;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO {

    public void salvar(ItemEstoque item) {
        String sql = item.getId() == 0 ?
                "INSERT INTO estoque (nome, quantidade) VALUES (?, ?)" :
                "UPDATE estoque SET nome = ?, quantidade = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getNome());
            stmt.setInt(2, item.getQuantidade());
            if (item.getId() != 0) {
                stmt.setInt(3, item.getId());
            }
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ItemEstoque> listar() {
        List<ItemEstoque> itens = new ArrayList<>();
        String sql = "SELECT * FROM estoque ORDER BY nome";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ItemEstoque item = new ItemEstoque(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("quantidade")
                );
                itens.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itens;
    }

    public void excluir(int id) {
        String sql = "DELETE FROM estoque WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
