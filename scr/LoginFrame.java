import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnLogin;

    public LoginFrame() {
        setTitle("Login - Restaurante Gourmet");
        setSize(350, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridBagLayout());
        painel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);

        JLabel lblTitulo = new JLabel("Acesso ao Sistema");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(255, 140, 0));

        JLabel lblUsuario = new JLabel("Usuário:");
        txtUsuario = new JTextField(15);

        JLabel lblSenha = new JLabel("Senha:");
        txtSenha = new JPasswordField(15);

        btnLogin = new JButton("Entrar");
        btnLogin.setBackground(new Color(255, 140, 0));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setFocusPainted(false);

        btnLogin.addActionListener(e -> {
            String usuario = txtUsuario.getText();
            String senha = new String(txtSenha.getPassword());
            if (usuario.equals("admin") && senha.equals("123")) {
                JOptionPane.showMessageDialog(this, "Login realizado com sucesso!");
                dispose();
                new PedidoFrame().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos!");
            }
        });

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        painel.add(lblTitulo, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++; gbc.gridx = 0; painel.add(lblUsuario, gbc);
        gbc.gridx = 1; painel.add(txtUsuario, gbc);

        gbc.gridy++; gbc.gridx = 0; painel.add(lblSenha, gbc);
        gbc.gridx = 1; painel.add(txtSenha, gbc);

        gbc.gridy++; gbc.gridx = 0; gbc.gridwidth = 2;
        painel.add(btnLogin, gbc);

        add(painel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
