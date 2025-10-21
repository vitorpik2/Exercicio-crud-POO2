package dao;

import dto.PedidoDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public void inserir(PedidoDTO pedido) throws SQLException {
        String sql = "INSERT INTO pedidos (id_cliente, data_pedido, valor_total) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pedido.getIdCliente());
            stmt.setDate(2, pedido.getDataPedido());
            stmt.setDouble(3, pedido.getValorTotal());
            stmt.executeUpdate();
        }
    }

    public List<PedidoDTO> listar() throws SQLException {
        List<PedidoDTO> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedidos";
        try (Connection conn = ConexaoDAO.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                PedidoDTO p = new PedidoDTO();
                p.setIdPedido(rs.getInt("id_pedido"));
                p.setIdCliente(rs.getInt("id_cliente"));
                p.setDataPedido(rs.getDate("data_pedido"));
                p.setValorTotal(rs.getDouble("valor_total"));
                pedidos.add(p);
            }
        }
        return pedidos;
    }

    public void atualizar(PedidoDTO pedido) throws SQLException {
        String sql = "UPDATE pedidos SET data_pedido=?, valor_total=? WHERE id_pedido=?";
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, pedido.getDataPedido());
            stmt.setDouble(2, pedido.getValorTotal());
            stmt.setInt(3, pedido.getIdPedido());
            stmt.executeUpdate();
        }
    }

    public void deletar(int idPedido) throws SQLException {
        String sql = "DELETE FROM pedidos WHERE id_pedido=?";
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPedido);
            stmt.executeUpdate();
        }
    }
}
