import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PedidoFrame extends JFrame {

    private JButton btnCriar, btnVer, btnExcluir, btnAtualizar, btnSair;
    private JLabel lblTitulo;

    public PedidoFrame() {
        setTitle("Sistema de Pedidos - Restaurante Gourmet");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(255, 140, 0)); 
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        lblTitulo = new JLabel("Painel de Gerenciamento de Pedidos");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(Color.WHITE);
        header.add(lblTitulo, BorderLayout.WEST);

        add(header, BorderLayout.NORTH);

        
        JPanel painelLateral = new JPanel();
        painelLateral.setBackground(new Color(245, 245, 245));
        painelLateral.setLayout(new GridLayout(5, 1, 10, 10));
        painelLateral.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        btnCriar = criarBotaoMenu("Criar Pedido");
        btnVer = criarBotaoMenu("Ver Pedidos");
        btnExcluir = criarBotaoMenu("Excluir Pedido");
        btnAtualizar = criarBotaoMenu("Atualizar Pedido");
        btnSair = criarBotaoMenu("Sair");

        painelLateral.add(btnCriar);
        painelLateral.add(btnVer);
        painelLateral.add(btnExcluir);
        painelLateral.add(btnAtualizar);
        painelLateral.add(btnSair);

        add(painelLateral, BorderLayout.WEST);

        
        JPanel painelCentral = new JPanel();
        painelCentral.setBackground(Color.WHITE);
        painelCentral.setLayout(new BorderLayout());

        JLabel lblBemVindo = new JLabel("<html><center>Bem-vindo ao Sistema de Pedidos!<br>Selecione uma ação no menu à esquerda.</center></html>");
        lblBemVindo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblBemVindo.setHorizontalAlignment(SwingConstants.CENTER);
        painelCentral.add(lblBemVindo, BorderLayout.CENTER);

        add(painelCentral, BorderLayout.CENTER);

        
        btnCriar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Função: Criar Pedido"));
        btnVer.addActionListener(e -> JOptionPane.showMessageDialog(this, "Função: Ver Pedidos"));
        btnExcluir.addActionListener(e -> JOptionPane.showMessageDialog(this, "Função: Excluir Pedido"));
        btnAtualizar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Função: Atualizar Pedido"));
        btnSair.addActionListener(e -> {
            int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente sair?", "Sair", JOptionPane.YES_NO_OPTION);
            if (opcao == JOptionPane.YES_OPTION) {
                dispose();
                new LoginFrame().setVisible(true);
            }
        });
    }

    
    private JButton criarBotaoMenu(String texto) {
        JButton botao = new JButton(texto);
        botao.setFocusPainted(false);
        botao.setBackground(new Color(255, 255, 255));
        botao.setFont(new Font("Segoe UI", Font.BOLD, 15));
        botao.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        botao.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                botao.setBackground(new Color(255, 228, 181)); 
            }
            public void mouseExited(MouseEvent e) {
                botao.setBackground(Color.WHITE);
            }
        });

        return botao;
    }
}
