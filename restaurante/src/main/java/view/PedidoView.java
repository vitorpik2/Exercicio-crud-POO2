package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import dao.PedidoDAO;
import dto.PedidoDTO;

/**
 * Tela de Cadastro de Pedidos.
 * @author vitor
 */
public class PedidoView extends JFrame {

    private JTextField txtIdCliente, txtData, txtValor;
    private JButton btnSalvar;

    public PedidoView() {
        setTitle("Cadastro de Pedidos");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(4, 2, 10, 10));

        JLabel lblIdCliente = new JLabel("ID Cliente:");
        JLabel lblData = new JLabel("Data (AAAA-MM-DD):");
        JLabel lblValor = new JLabel("Valor Total:");

        txtIdCliente = new JTextField();
        txtData = new JTextField();
        txtValor = new JTextField();

        btnSalvar = new JButton("Salvar Pedido");

        add(lblIdCliente); add(txtIdCliente);
        add(lblData); add(txtData);
        add(lblValor); add(txtValor);
        add(new JLabel("")); add(btnSalvar);

        btnSalvar.addActionListener(e -> salvarPedido());
    }

    private void salvarPedido() {
        PedidoDTO pedido = new PedidoDTO();
        try {
            pedido.setIdCliente(Integer.parseInt(txtIdCliente.getText()));
            pedido.setDataPedido(Date.valueOf(txtData.getText()));
            pedido.setValorTotal(Double.parseDouble(txtValor.getText()));

            PedidoDAO dao = new PedidoDAO();
            if (dao.inserirPedido(pedido)) {
                JOptionPane.showMessageDialog(this, "Pedido salvo com sucesso!");
                txtIdCliente.setText("");
                txtData.setText("");
                txtValor.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao salvar pedido!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PedidoView().setVisible(true));
    }
}
