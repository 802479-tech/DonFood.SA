package view;

import controller.PedidoController;
import model.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class GerenciadorPedidosView extends JFrame {
    private PedidoController controller = new PedidoController();
    private DefaultTableModel tableModel;
    private JTable table;
    private JComboBox<String> statusComboBox;

    public GerenciadorPedidosView() {
        setTitle("Gerenciador de Pedidos");
        setSize(800, 600);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new MainView().setVisible(true);
            }
        });

        // Tabela de Pedidos
        tableModel = new DefaultTableModel(new Object[]{"Nº Pedido", "Total", "Status"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        // Painel de Ações
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        actionPanel.setBorder(BorderFactory.createTitledBorder("Mudar Status do Pedido Selecionado"));

        statusComboBox = new JComboBox<>(new String[]{"EM_PREPARO", "FINALIZADO"});
        JButton mudarStatusButton = new JButton("Mudar Status");
        JButton atualizarListaButton = new JButton("Atualizar Lista");

        actionPanel.add(new JLabel("Novo status:"));
        actionPanel.add(statusComboBox);
        actionPanel.add(mudarStatusButton);
        actionPanel.add(Box.createHorizontalStrut(20)); // Espaçador
        actionPanel.add(atualizarListaButton);
        add(actionPanel, BorderLayout.SOUTH);

        // Listeners
        mudarStatusButton.addActionListener(e -> mudarStatus());
        atualizarListaButton.addActionListener(e -> atualizarTabela());

        atualizarTabela();
    }

    private void atualizarTabela() {
        List<Pedido> pedidos = controller.listarPedidosAtivos();
        tableModel.setRowCount(0);
        for (Pedido pedido : pedidos) {
            tableModel.addRow(new Object[]{
                    pedido.getNumeroPedido(),
                    String.format("R$ %.2f", pedido.getTotal()),
                    pedido.getStatus()
            });
        }
    }

    private void mudarStatus() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int numeroPedido = (int) tableModel.getValueAt(selectedRow, 0);
            String novoStatus = (String) statusComboBox.getSelectedItem();

            // Encontra o ID do pedido pelo número
            List<Pedido> pedidos = controller.listarPedidosAtivos();
            int pedidoId = -1;
            for (Pedido p : pedidos) {
                if (p.getNumeroPedido() == numeroPedido) {
                    pedidoId = p.getId();
                    break;
                }
            }

            if (pedidoId != -1) {
                controller.atualizarStatus(pedidoId, novoStatus);
                atualizarTabela();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao encontrar o pedido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um pedido para alterar.", "Nenhum Pedido Selecionado", JOptionPane.WARNING_MESSAGE);
        }
    }
}