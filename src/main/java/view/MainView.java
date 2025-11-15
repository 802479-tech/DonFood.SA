package view;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    public MainView() {
        setTitle("Sistema de Gestão de Restaurante");
        setSize(450, 400); // Aumentar a altura para o novo botão
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Título
        JLabel titleLabel = new JLabel("Gestão de Restaurante", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Painel de botões
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        JButton pedidoButton = new JButton("Novo Pedido");
        JButton gerenciadorPedidosButton = new JButton("Gerenciar Pedidos");
        JButton cardapioButton = new JButton("Gerenciar Cardápio");
        JButton estoqueButton = new JButton("Gerenciar Estoque");

        // Estilo dos botões
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);
        pedidoButton.setFont(buttonFont);
        gerenciadorPedidosButton.setFont(buttonFont);
        cardapioButton.setFont(buttonFont);
        estoqueButton.setFont(buttonFont);

        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(pedidoButton, gbc);

        gbc.gridy = 1;
        buttonPanel.add(gerenciadorPedidosButton, gbc);

        gbc.gridy = 2;
        buttonPanel.add(cardapioButton, gbc);

        gbc.gridy = 3;
        buttonPanel.add(estoqueButton, gbc);

        add(buttonPanel, BorderLayout.CENTER);

        // Ações dos botões
        cardapioButton.addActionListener(e -> {
            new CardapioView().setVisible(true);
            this.dispose();
        });

        estoqueButton.addActionListener(e -> {
            new EstoqueView().setVisible(true);
            this.dispose();
        });

        pedidoButton.addActionListener(e -> {
            new PedidoView().setVisible(true);
            this.dispose();
        });

        gerenciadorPedidosButton.addActionListener(e -> {
            new GerenciadorPedidosView().setVisible(true);
            this.dispose();
        });
    }
}
