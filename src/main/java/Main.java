import dao.Conexao;
import view.MainView;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        criarBancoDeDados();

        SwingUtilities.invokeLater(() -> {
            new MainView().setVisible(true);
        });
    }

    private static void criarBancoDeDados() {
        String sqlCardapio = "CREATE TABLE IF NOT EXISTS cardapio (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "tipo TEXT NOT NULL," +
                "preco REAL NOT NULL);";

        String sqlEstoque = "CREATE TABLE IF NOT EXISTS estoque (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL UNIQUE," +
                "quantidade INTEGER NOT NULL);";

        String sqlPedido = "CREATE TABLE IF NOT EXISTS pedido (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "data DATE NOT NULL," +
                "total REAL NOT NULL);";

        String sqlPedidoItens = "CREATE TABLE IF NOT EXISTS pedido_itens (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "pedido_id INTEGER NOT NULL," +
                "cardapio_id INTEGER NOT NULL," +
                "quantidade INTEGER NOT NULL," +
                "preco_unitario REAL NOT NULL," +
                "subtotal REAL NOT NULL," +
                "FOREIGN KEY (pedido_id) REFERENCES pedido(id)," +
                "FOREIGN KEY (cardapio_id) REFERENCES cardapio(id));";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sqlCardapio);
            stmt.execute(sqlEstoque);
            stmt.execute(sqlPedido);
            stmt.execute(sqlPedidoItens);
            System.out.println("Banco de dados e tabelas criados com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
