package cadastrobd.model;

import cadastro.model.util.ConectorBD;
import cadastro.model.util.SequenceManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaDAO {

    
    public PessoaFisica getPessoa(int id) {
        PessoaFisica pessoaFisica = null;
        String sql = "SELECT * FROM Pessoa INNER JOIN PessoaFisica ON Pessoa.idPessoa = PessoaFisica.Pessoa_idPessoa WHERE Pessoa.idPessoa = ?";

        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    pessoaFisica = new PessoaFisica(
                            rs.getInt("idPessoa"),
                            rs.getString("nome"),
                            rs.getString("logradouro"),
                            rs.getString("cidade"),
                            rs.getString("estado"),
                            rs.getString("telefone"),
                            rs.getString("email"),
                            rs.getString("CPF")
                    );
                }
            }
        } catch (SQLException e) {
        }
        return pessoaFisica;
    }

    
    public List<PessoaFisica> getPessoas() {
        List<PessoaFisica> pessoasFisicas = new ArrayList<>();
        String sql = "SELECT * FROM Pessoa INNER JOIN PessoaFisica ON Pessoa.idPessoa = PessoaFisica.Pessoa_idPessoa";

        try (Connection conn = ConectorBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                PessoaFisica pessoaFisica = new PessoaFisica(
                    rs.getInt("idPessoa"),
                    rs.getString("nome"),
                    rs.getString("logradouro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("CPF")
                );
                pessoasFisicas.add(pessoaFisica);
            }
        } catch (SQLException e) {
        }
        return pessoasFisicas;
    }
    
        public boolean incluir(PessoaFisica pessoaFisica) {
        Connection conn = null;
        PreparedStatement pstmtPessoa = null;
        PreparedStatement pstmtPessoaFisica = null;
        
        try {
            conn = ConectorBD.getConnection();
            conn.setAutoCommit(false);

            int idPessoa = SequenceManager.getNextValue(conn, "Seq_idPessoa");

            String sqlPessoa = "INSERT INTO Pessoa (idPessoa, nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmtPessoa = conn.prepareStatement(sqlPessoa);
            pstmtPessoa.setInt(1, idPessoa);
            pstmtPessoa.setString(2, pessoaFisica.getNome());
            pstmtPessoa.setString(3, pessoaFisica.getLogradouro());
            pstmtPessoa.setString(4, pessoaFisica.getCidade());
            pstmtPessoa.setString(5, pessoaFisica.getEstado());
            pstmtPessoa.setString(6, pessoaFisica.getTelefone());
            pstmtPessoa.setString(7, pessoaFisica.getEmail());
            pstmtPessoa.executeUpdate();

            String sqlPessoaFisica = "INSERT INTO PessoaFisica (Pessoa_idPessoa, CPF) VALUES (?, ?)";
            pstmtPessoaFisica = conn.prepareStatement(sqlPessoaFisica);
            pstmtPessoaFisica.setInt(1, idPessoa);
            pstmtPessoaFisica.setString(2, pessoaFisica.getCpf());
            pstmtPessoaFisica.executeUpdate();

            conn.commit();
        return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
            
            } finally {
            Statement generatedKeys = null;
            ConectorBD.close(generatedKeys);
            ConectorBD.close(pstmtPessoa);
            ConectorBD.close(pstmtPessoaFisica);
            ConectorBD.close(conn);
        }
    }

    public boolean alterar(PessoaFisica pessoaFisica) {
        String sqlPessoa = "UPDATE Pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE idPessoa = ?";
        String sqlPessoaFisica = "UPDATE PessoaFisica SET CPF = ? WHERE Pessoa_idPessoa = ?";

        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement pstmtPessoa = conn.prepareStatement(sqlPessoa);
             PreparedStatement pstmtPessoaFisica = conn.prepareStatement(sqlPessoaFisica)) {

            conn.setAutoCommit(false);


            pstmtPessoa.setString(1, pessoaFisica.getNome());
            pstmtPessoa.setString(2, pessoaFisica.getLogradouro());
            pstmtPessoa.setString(3, pessoaFisica.getCidade());
            pstmtPessoa.setString(4, pessoaFisica.getEstado());
            pstmtPessoa.setString(5, pessoaFisica.getTelefone());
            pstmtPessoa.setString(6, pessoaFisica.getEmail());
            pstmtPessoa.setInt(7, pessoaFisica.getId());
            pstmtPessoa.executeUpdate();


            pstmtPessoaFisica.setString(1, pessoaFisica.getCpf());
            pstmtPessoaFisica.setInt(2, pessoaFisica.getId());
            pstmtPessoaFisica.executeUpdate();

            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluir(int id) {
    String sqlDeleteMovimentos = "DELETE FROM Movimento WHERE Pessoa_idPessoa = ?";
    String sqlDeletePessoaFisica = "DELETE FROM PessoaFisica WHERE Pessoa_idPessoa = ?";
    String sqlDeletePessoa = "DELETE FROM Pessoa WHERE idPessoa = ?";

    try (Connection conn = ConectorBD.getConnection();
         PreparedStatement pstmtDeleteMovimentos = conn.prepareStatement(sqlDeleteMovimentos);
         PreparedStatement pstmtDeletePessoaFisica = conn.prepareStatement(sqlDeletePessoaFisica);
         PreparedStatement pstmtDeletePessoa = conn.prepareStatement(sqlDeletePessoa)) {

        conn.setAutoCommit(false);

        pstmtDeleteMovimentos.setInt(1, id);
        pstmtDeleteMovimentos.executeUpdate();

        pstmtDeletePessoaFisica.setInt(1, id);
        pstmtDeletePessoaFisica.executeUpdate();

        pstmtDeletePessoa.setInt(1, id);
        pstmtDeletePessoa.executeUpdate();

        conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
    } finally {;
        }
    }
    
    public PessoaFisica getPessoaPorCpf(String cpf) {
        PessoaFisica pessoaFisica = null;
        String sql = "SELECT * FROM Pessoa INNER JOIN PessoaFisica ON Pessoa.idPessoa = PessoaFisica.Pessoa_idPessoa WHERE PessoaFisica.CPF = ?";

        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cpf);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                pessoaFisica = new PessoaFisica(
                        rs.getInt("idPessoa"),
                        rs.getString("nome"),
                        rs.getString("logradouro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("CPF")
                );
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoaFisica;
    }
}
