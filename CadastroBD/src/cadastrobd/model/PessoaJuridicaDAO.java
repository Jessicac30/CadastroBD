package cadastrobd.model;

import cadastro.model.util.ConectorBD;
import cadastro.model.util.SequenceManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaDAO {


    public PessoaJuridica getPessoa(int id) {
        PessoaJuridica pessoaJuridica = null;
        String sql = "SELECT * FROM Pessoa INNER JOIN PessoaJuridica ON Pessoa.idPessoa = PessoaJuridica.Pessoa_idPessoa WHERE Pessoa.idPessoa = ?";

        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                pessoaJuridica = new PessoaJuridica(
                    rs.getInt("idPessoa"),
                    rs.getString("nome"),
                    rs.getString("logradouro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("CNPJ")
                );
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoaJuridica;
    }

    public List<PessoaJuridica> getPessoas() {
        List<PessoaJuridica> pessoasJuridicas = new ArrayList<>();
        String sql = "SELECT * FROM Pessoa INNER JOIN PessoaJuridica ON Pessoa.idPessoa = PessoaJuridica.Pessoa_idPessoa";

        try (Connection conn = ConectorBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                PessoaJuridica pessoaJuridica = new PessoaJuridica(
                    rs.getInt("idPessoa"),
                    rs.getString("nome"),
                    rs.getString("logradouro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("CNPJ")
                );
                pessoasJuridicas.add(pessoaJuridica);
            }
        } catch (SQLException e) {
        }
        return pessoasJuridicas;
    }

    public boolean incluir(PessoaJuridica pessoaJuridica) {
    Connection conn = null;
    PreparedStatement pstmtPessoa = null;
    PreparedStatement pstmtPessoaJuridica = null;
    
    try {
        conn = ConectorBD.getConnection();
        conn.setAutoCommit(false);

        
        int idPessoa = SequenceManager.getNextValue(conn, "NomeDaSequenciaPessoa");

        
        String sqlPessoa = "INSERT INTO Pessoa (idPessoa, nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        pstmtPessoa = conn.prepareStatement(sqlPessoa);
        pstmtPessoa.setInt(1, idPessoa);
        pstmtPessoa.setString(2, pessoaJuridica.getNome());
        pstmtPessoa.setString(3, pessoaJuridica.getLogradouro());
        pstmtPessoa.setString(4, pessoaJuridica.getCidade());
        pstmtPessoa.setString(5, pessoaJuridica.getEstado());
        pstmtPessoa.setString(6, pessoaJuridica.getTelefone());
        pstmtPessoa.setString(7, pessoaJuridica.getEmail());
        pstmtPessoa.executeUpdate();

        
        String sqlPessoaJuridica = "INSERT INTO PessoaJuridica (Pessoa_idPessoa, CNPJ) VALUES (?, ?)";
        pstmtPessoaJuridica = conn.prepareStatement(sqlPessoaJuridica);
        pstmtPessoaJuridica.setInt(1, idPessoa);
        pstmtPessoaJuridica.setString(2, pessoaJuridica.getCnpj());
        pstmtPessoaJuridica.executeUpdate();

        conn.commit();
        return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean alterar(PessoaJuridica pessoaJuridica) {
        String sqlPessoa = "UPDATE Pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE idPessoa = ?";
        String sqlPessoaJuridica = "UPDATE PessoaJuridica SET CNPJ = ? WHERE Pessoa_idPessoa = ?";

        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement pstmtPessoa = conn.prepareStatement(sqlPessoa);
             PreparedStatement pstmtPessoaJuridica = conn.prepareStatement(sqlPessoaJuridica)) {

            conn.setAutoCommit(false);

            pstmtPessoa.setString(1, pessoaJuridica.getNome());
            pstmtPessoa.setString(2, pessoaJuridica.getLogradouro());
            pstmtPessoa.setString(3, pessoaJuridica.getCidade());
            pstmtPessoa.setString(4, pessoaJuridica.getEstado());
            pstmtPessoa.setString(5, pessoaJuridica.getTelefone());
            pstmtPessoa.setString(6, pessoaJuridica.getEmail());
            pstmtPessoa.setInt(7, pessoaJuridica.getId());
            pstmtPessoa.executeUpdate();

            pstmtPessoaJuridica.setString(1, pessoaJuridica.getCnpj());
            pstmtPessoaJuridica.setInt(2, pessoaJuridica.getId());
            pstmtPessoaJuridica.executeUpdate();

            conn.commit();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean excluir(int id) {
        String sqlPessoaJuridica = "DELETE FROM PessoaJuridica WHERE Pessoa_idPessoa = ?";
        String sqlPessoa = "DELETE FROM Pessoa WHERE idPessoa = ?";

        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement pstmtPessoaJuridica = conn.prepareStatement(sqlPessoaJuridica);
             PreparedStatement pstmtPessoa = conn.prepareStatement(sqlPessoa)) {

            conn.setAutoCommit(false);

            pstmtPessoaJuridica.setInt(1, id);
            pstmtPessoaJuridica.executeUpdate();

            pstmtPessoa.setInt(1, id);
            pstmtPessoa.executeUpdate();

            conn.commit();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
