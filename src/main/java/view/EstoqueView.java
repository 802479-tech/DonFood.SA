package view;

import controller.EstoqueController;
import model.ItemEstoque;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class EstoqueView extends JFrame {
    private EstoqueController controller = new EstoqueController();
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField nomeField;
    private JTextField quantidadeField;
    private JButton salvarButton;
    private JButton excluirButton;
    private JButton novoButton;

    public EstoqueView() {
        setTitle("Gerenciar Estoque");
        setSize(600, 500);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new MainView().setVisible(true);
            }
        });

        // Tabela
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nome", "Quantidade"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        // Painel do formulário
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Detalhes do Item"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        nomeField = new JTextField(20);
        quantidadeField = new JTextField();

        // Linha 1: Nome
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Nome:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        formPanel.add(nomeField, gbc);

        // Linha 2: Quantidade
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Quantidade:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        formPanel.add(quantidadeField, gbc);

        // Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        novoButton = new JButton("Novo");
        salvarButton = new JButton("Salvar");
        excluirButton = new JButton("Excluir");
        buttonPanel.add(novoButton);
        buttonPanel.add(salvarButton);
        buttonPanel.add(excluirButton);

        // Painel Sul
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(formPanel, BorderLayout.CENTER);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);
        southPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        add(southPanel, BorderLayout.SOUTH);

        // Listeners
        table.getSelectionModel().addListSelectionListener(e -> preencherFormulario());
        salvarButton.addActionListener(e -> salvar());
        excluirButton.addActionListener(e -> excluir());
        novoButton.addActionListener(e -> limparFormulario());

        atualizarTabela();
    }

    private void atualizarTabela() {
        List<ItemEstoque> itens = controller.listar();
        tableModel.setRowCount(0);
        for (ItemEstoque item : itens) {
            tableModel.addRow(new Object[]{item.getId(), item.getNome(), item.getQuantidade()});
        }
    }

    private void preencherFormulario() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            nomeField.setText(tableModel.getValueAt(selectedRow, 1).toString());
            quantidadeField.setText(tableModel.getValueAt(selectedRow, 2).toString());
        }
    }

    private void salvar() {
        try {
            int selectedRow = table.getSelectedRow();
            ItemEstoque item = new ItemEstoque();

            if (selectedRow != -1) {
                item.setId((int) tableModel.getValueAt(selectedRow, 0));
            }

            item.setNome(nomeField.getText());
            item.setQuantidade(Integer.parseInt(quantidadeField.getText()));

            controller.salvar(item);
            atualizarTabela();
            limparFormulario();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Quantidade inválida. Insira um número inteiro.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluir() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir este item?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                controller.excluir(id);
                atualizarTabela();
                limparFormulario();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um item para excluir.", "Nenhum Item Selecionado", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void limparFormulario() {
        table.clearSelection();
        nomeField.setText("");
        quantidadeField.setText("");
        nomeField.requestFocus();
    }
}
