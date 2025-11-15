package view;

import controller.CardapioController;
import controller.PedidoController;
import model.ItemCardapio;
import model.ItemPedido;
import model.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class PedidoView extends JFrame {
    private PedidoController pedidoController = new PedidoController();
    private CardapioController cardapioController = new CardapioController();
    private Pedido pedidoCorrente = new Pedido();

    private DefaultTableModel itensPedidoModel;
    private JTable itensPedidoTable;
    private JComboBox<ItemCardapio> cardapioComboBox;
    private JSpinner quantidadeSpinner;
    private JButton adicionarItemButton;
    private JButton finalizarPedidoButton;
    private JButton removerItemButton;
    private JLabel totalLabel;

    public PedidoView() {
        setTitle("Novo Pedido");
        setSize(700, 550);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new MainView().setVisible(true);
            }
        });

        // Painel Superior: Seleção de Itens
        JPanel selecaoPanel = new JPanel(new GridBagLayout());
        selecaoPanel.setBorder(BorderFactory.createTitledBorder("Adicionar Item ao Pedido"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        cardapioComboBox = new JComboBox<>();
        quantidadeSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        adicionarItemButton = new JButton("Adicionar Item");

        gbc.gridx = 0;
        gbc.gridy = 0;
        selecaoPanel.add(new JLabel("Item:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        selecaoPanel.add(cardapioComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        selecaoPanel.add(new JLabel("Qtd:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        selecaoPanel.add(quantidadeSpinner, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        selecaoPanel.add(adicionarItemButton, gbc);

        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(selecaoPanel, BorderLayout.CENTER);
        northPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(northPanel, BorderLayout.NORTH);

        // Painel Central: Itens do Pedido
        itensPedidoModel = new DefaultTableModel(new Object[]{"Item", "Qtd", "Preço Unit.", "Subtotal"}, 0);
        itensPedidoTable = new JTable(itensPedidoModel);
        JScrollPane scrollPane = new JScrollPane(itensPedidoTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Itens do Pedido"));
        add(scrollPane, BorderLayout.CENTER);

        // Painel Inferior: Total e Finalizar
        JPanel rodapePanel = new JPanel(new BorderLayout(10, 10));
        rodapePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        totalLabel = new JLabel("Total: R$ 0.00");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        finalizarPedidoButton = new JButton("Finalizar Pedido");
        removerItemButton = new JButton("Remover Item");

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(removerItemButton);
        buttonsPanel.add(finalizarPedidoButton);

        rodapePanel.add(totalLabel, BorderLayout.WEST);
        rodapePanel.add(buttonsPanel, BorderLayout.EAST);
        add(rodapePanel, BorderLayout.SOUTH);

        // Ações
        adicionarItemButton.addActionListener(e -> adicionarItem());
        removerItemButton.addActionListener(e -> removerItem());
        finalizarPedidoButton.addActionListener(e -> finalizarPedido());

        carregarItensCardapio();
    }

    private void carregarItensCardapio() {
        List<ItemCardapio> itens = cardapioController.listar();
        for (ItemCardapio item : itens) {
            cardapioComboBox.addItem(item);
        }
    }

    private void adicionarItem() {
        ItemCardapio itemSelecionado = (ItemCardapio) cardapioComboBox.getSelectedItem();
        int quantidade = (int) quantidadeSpinner.getValue();

        if (itemSelecionado != null) {
            ItemPedido itemPedido = new ItemPedido(pedidoCorrente, itemSelecionado, quantidade);
            pedidoCorrente.getItens().add(itemPedido);
            atualizarTabelaItens();
            atualizarTotal();
        }
    }

    private void removerItem() {
        int selectedRow = itensPedidoTable.getSelectedRow();
        if (selectedRow != -1) {
            pedidoCorrente.getItens().remove(selectedRow);
            atualizarTabelaItens();
            atualizarTotal();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um item para remover.", "Nenhum Item Selecionado", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void atualizarTabelaItens() {
        itensPedidoModel.setRowCount(0);
        for (ItemPedido item : pedidoCorrente.getItens()) {
            itensPedidoModel.addRow(new Object[]{
                    item.getItemCardapio().getNome(),
                    item.getQuantidade(),
                    String.format("R$ %.2f", item.getPrecoUnitario()),
                    String.format("R$ %.2f", item.getSubtotal())
            });
        }
    }

    private void atualizarTotal() {
        pedidoCorrente.recalcularTotal();
        totalLabel.setText(String.format("Total: R$ %.2f", pedidoCorrente.getTotal()));
    }

    private void finalizarPedido() {
        if (pedidoCorrente.getItens().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O pedido está vazio!", "Pedido Vazio", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Deseja finalizar o pedido no valor de " + String.format("R$ %.2f", pedidoCorrente.getTotal()) + "?", "Confirmar Pedido", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            pedidoController.registrarPedido(pedidoCorrente);
            JOptionPane.showMessageDialog(this, "Pedido Nº " + pedidoCorrente.getNumeroPedido() + " registrado com sucesso!", "Pedido Registrado", JOptionPane.INFORMATION_MESSAGE);

            // Reset para um novo pedido
            pedidoCorrente = new Pedido();
            atualizarTabelaItens();
            atualizarTotal();
        }
    }
}
