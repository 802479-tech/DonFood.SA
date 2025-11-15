package view;

import controller.CardapioController;
import model.ItemCardapio;
import model.ItemCardapio.TipoItemCardapio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class CardapioView extends JFrame {
    private CardapioController controller = new CardapioController();
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField nomeField;
    private JComboBox<TipoItemCardapio> tipoComboBox;
    private JTextField precoField;
    private JButton salvarButton;
    private JButton excluirButton;
    private JButton novoButton;

    public CardapioView() {
        setTitle("Gerenciar Cardápio");
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
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nome", "Tipo", "Preço"}, 0);
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
        tipoComboBox = new JComboBox<>(TipoItemCardapio.values());
        precoField = new JTextField();

        // Linha 1: Nome
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Nome:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        formPanel.add(nomeField, gbc);

        // Linha 2: Tipo
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Tipo:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        formPanel.add(tipoComboBox, gbc);

        // Linha 3: Preço
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Preço:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        formPanel.add(precoField, gbc);

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
        List<ItemCardapio> itens = controller.listar();
        tableModel.setRowCount(0);
        for (ItemCardapio item : itens) {
            tableModel.addRow(new Object[]{item.getId(), item.getNome(), item.getTipo(), item.getPreco()});
        }
    }

    private void preencherFormulario() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            nomeField.setText(tableModel.getValueAt(selectedRow, 1).toString());
            tipoComboBox.setSelectedItem(tableModel.getValueAt(selectedRow, 2));
            precoField.setText(tableModel.getValueAt(selectedRow, 3).toString());
        }
    }

    private void salvar() {
        try {
            int selectedRow = table.getSelectedRow();
            ItemCardapio item = new ItemCardapio();

            if (selectedRow != -1) {
                item.setId((int) tableModel.getValueAt(selectedRow, 0));
            }

            item.setNome(nomeField.getText());
            item.setTipo((TipoItemCardapio) tipoComboBox.getSelectedItem());
            item.setPreco(Double.parseDouble(precoField.getText()));

            controller.salvar(item);
            atualizarTabela();
            limparFormulario();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Preço inválido. Use ponto como separador decimal.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
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
        tipoComboBox.setSelectedIndex(0);
        precoField.setText("");
        nomeField.requestFocus();
    }
}