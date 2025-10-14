import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnLogin;

    public LoginFrame() {
        setTitle("Login - Restaurante");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(3, 2, 10, 10));

        
        JLabel lblUsuario = new JLabel("Usuário:");
        txtUsuario = new JTextField();

        JLabel lblSenha = new JLabel("Senha:");
        txtSenha = new JPasswordField();

        btnLogin = new JButton("Entrar");

        
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText();
                String senha = new String(txtSenha.getPassword());

                
                if (usuario.equals("admin") && senha.equals("123")) {
                    JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
                    dispose(); 
                    new PedidoFrame().setVisible(true); 
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!");
                }
            }
        });

        
        painel.add(lblUsuario);
        painel.add(txtUsuario);
        painel.add(lblSenha);
        painel.add(txtSenha);
        painel.add(new JLabel());
        painel.add(btnLogin);

        add(painel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
