import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    // === Ajuste estes parâmetros para o seu ambiente ===
    private static final String URL    = "jdbc:postgresql://localhost:5432/sua_base";
    private static final String USUARIO = "seu_usuario";
    private static final String SENHA   = "sua_senha";

    // Opcional: carregar driver explicitamente (PostgreSQL)
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            // Se estiver usando JDBC 4+, provavelmente não é necessário.
        }
    }

    public static Connection obterConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
