package cadastro.model.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SequenceManager {

    
    public static int getNextValue(Connection conn, String sequenceName) throws SQLException {
        String sql = "SELECT NEXT VALUE FOR " + sequenceName;
        try (PreparedStatement pstmt = ConectorBD.getPrepared(conn, sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Não foi possível obter o próximo valor da sequência.");
            }
        }
    }  
}
