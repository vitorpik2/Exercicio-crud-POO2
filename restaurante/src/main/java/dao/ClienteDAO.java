package dao;

import dto.ClienteDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void inserir(ClienteDTO cliente) throws SQLException {
        String sql = "INSERT INTO clientes (nome, telefone, email) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEmail());
            stmt.executeUpdate();
        }
    }

    public List<ClienteDTO> listar() throws SQLException {
        List<ClienteDTO> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Connection conn = ConexaoDAO.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ClienteDTO c = new ClienteDTO();
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setNome(rs.getString("nome"));
                c.setTelefone(rs.getString("telefone"));
                c.setEmail(rs.getString("email"));
                clientes.add(c);
            }
        }
        return clientes;
    }

    public void atualizar(ClienteDTO cliente) throws SQLException {
        String sql = "UPDATE clientes SET nome=?, telefone=?, email=? WHERE id_cliente=?";
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEmail());
            stmt.setInt(4, cliente.getIdCliente());
            stmt.executeUpdate();
        }
    }

    public void deletar(int idCliente) throws SQLException {
        String sql = "DELETE FROM clientes WHERE id_cliente=?";
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            stmt.executeUpdate();
        }
    }
}
