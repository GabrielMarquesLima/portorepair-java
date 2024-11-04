package br.com.fiap;

import java.sql.*;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class CarroDAO {

    public List<Carro> getAllCarros() throws SQLException {
        List<Carro> carros = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM T_PR_CARRO")) {

            while (rs.next()) {
                Carro carro = new Carro(
                        rs.getString("num_chassi"),
                        rs.getString("placa_carro"),
                        rs.getString("modelo_carro"),
                        rs.getString("montadora_carro"),
                        rs.getDate("ano_carro").toLocalDate()
                );
                carros.add(carro);
            }
        }
        return carros;
    }

    public Carro getCarro(String numChassi) throws SQLException {
        Carro carro = null;
        String sql = "SELECT * FROM T_PR_CARRO WHERE num_chassi = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, numChassi);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    carro = new Carro(
                            rs.getString("num_chassi"),
                            rs.getString("placa_carro"),
                            rs.getString("modelo_carro"),
                            rs.getString("montadora_carro"),
                            rs.getDate("ano_carro").toLocalDate()
                    );
                }
            }
        }
        return carro;
    }

    public void addCarro(Carro carro) throws SQLException {
        String sql = "INSERT INTO T_PR_CARRO (num_chassi, placa_carro, modelo_carro, montadora_carro, ano_carro) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, carro.getNumChassi());
            pstmt.setString(2, carro.getPlacaCarro());
            pstmt.setString(3, carro.getModeloCarro());
            pstmt.setString(4, carro.getMontadoraCarro());
            pstmt.setDate(5, Date.valueOf(carro.getAnoCarro()));
            pstmt.executeUpdate();
        }
    }

    public void updateCarro(Carro carro) throws SQLException {
        String sql = "UPDATE T_PR_CARRO SET placa_carro = ?, modelo_carro = ?, montadora_carro = ?, ano_carro = ? WHERE num_chassi = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, carro.getPlacaCarro());
            pstmt.setString(2, carro.getModeloCarro());
            pstmt.setString(3, carro.getMontadoraCarro());
            pstmt.setDate(4, Date.valueOf(carro.getAnoCarro()));
            pstmt.setString(5, carro.getNumChassi());
            pstmt.executeUpdate();
        }
    }

    public void deleteCarro(String numChassi) throws SQLException {
        String sql = "DELETE FROM T_PR_CARRO WHERE num_chassi = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, numChassi);
            pstmt.executeUpdate();
        }
    }
}
