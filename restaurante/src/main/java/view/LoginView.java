package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import dao.ConexaoDAO;

/**
 * Tela de Login do sistema.
 * @author vitor
 */
public class LoginView extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnEntrar;
    private JLabel lblStatus;

    public LoginView() {
        setTitle("Login - Restaurante");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(4, 2, 10, 10));

        JLabel lblUsuario = new JLabel("Usuário:");
        JLabel lblSenha = new JLabel("Senha:");

        txtUsuario = new JTextField();
        txtSenha = new JPasswordField();
        btnEntrar = new JButton("Entrar");
        lblStatus = new JLabel("", SwingConstants.CENTER);

        add(lblUsuario);
        add(txtUsuario);
        add(lblSenha);
        add(txtSenha);
        add(new JLabel(""));
        add(btnEntrar);
        add(lblStatus);

        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticar();
            }
        });
    }

    private void autenticar() {
        String usuario = txtUsuario.getText();
        String senha = new String(txtSenha.getPassword());

        try (Connection con = ConexaoDAO.getConexao()) {
            String sql = "SELECT * FROM usuarios WHERE usuario=? AND senha=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Bem-vindo, " + usuario + "!");
                new ClienteView().setVisible(true);
                this.dispose();
            } else {
                lblStatus.setText("Usuário ou senha inválidos!");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao conectar ao banco: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
}
