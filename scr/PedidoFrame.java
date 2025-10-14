import javax.swing.*;
import java.awt.*;

public class PedidoFrame extends JFrame {

    private JButton btnCriar, btnVer, btnExcluir, btnAtualizar;

    public PedidoFrame() {
        setTitle("Gerenciamento de Pedidos - Restaurante");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(2, 2, 20, 20));

        btnCriar = new JButton("Criar Pedido");
        btnVer = new JButton("Ver Pedidos");
        btnExcluir = new JButton("Excluir Pedido");
        btnAtualizar = new JButton("Atualizar Pedido");

        
        btnCriar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Função: Criar Pedido"));
        btnVer.addActionListener(e -> JOptionPane.showMessageDialog(this, "Função: Ver Pedidos"));
        btnExcluir.addActionListener(e -> JOptionPane.showMessageDialog(this, "Função: Excluir Pedido"));
        btnAtualizar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Função: Atualizar Pedido"));

        painel.add(btnCriar);
        painel.add(btnVer);
        painel.add(btnExcluir);
        painel.add(btnAtualizar);

        add(painel, BorderLayout.CENTER);
    }
}
