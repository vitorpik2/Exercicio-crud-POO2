package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import dao.ClienteDAO;
import dto.ClienteDTO;

/**
 * Tela de Cadastro e listagem de Clientes.
 * @author vitor
 */
public class ClienteView extends JFrame {

    private JTextField txtNome, txtTelefone, txtEmail;
    private JButton btnSalvar, btnListar;
    private JTextArea areaLista;

    public ClienteView() {
        setTitle("Gerenciamento de Clientes");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        setLayout(new FlowLayout());

        JLabel lblNome = new JLabel("Nome:");
        txtNome = new JTextField(20);
        JLabel lblTelefone = new JLabel("Telefone:");
        txtTelefone = new JTextField(15);
        JLabel lblEmail = new JLabel("Email:");
        txtEmail = new JTextField(20);

        btnSalvar = new JButton("Salvar");
        btnListar = new JButton("Listar Clientes");

        areaLista = new JTextArea(10, 40);
        areaLista.setEditable(false);

        add(lblNome); add(txtNome);
        add(lblTelefone); add(txtTelefone);
        add(lblEmail); add(txtEmail);
        add(btnSalvar); add(btnListar);
        add(new JScrollPane(areaLista));

        btnSalvar.addActionListener(e -> salvarCliente());
        btnListar.addActionListener(e -> listarClientes());
    }

    private void salvarCliente() {
        ClienteDTO cliente = new ClienteDTO();
        cliente.setNome(txtNome.getText());
        cliente.setTelefone(txtTelefone.getText());
        cliente.setEmail(txtEmail.getText());

        ClienteDAO dao = new ClienteDAO();
        if (dao.inserirCliente(cliente)) {
            JOptionPane.showMessageDialog(this, "Cliente salvo com sucesso!");
            txtNome.setText("");
            txtTelefone.setText("");
            txtEmail.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao salvar cliente!");
        }
    }

    private void listarClientes() {
        ClienteDAO dao = new ClienteDAO();
        List<ClienteDTO> lista = dao.listarClientes();
        areaLista.setText("");
        for (ClienteDTO c : lista) {
            areaLista.append(
                "ID: " + c.getIdCliente() + 
                " | Nome: " + c.getNome() + 
                " | Telefone: " + c.getTelefone() + 
                " | Email: " + c.getEmail() + "\n"
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClienteView().setVisible(true));
    }
}
