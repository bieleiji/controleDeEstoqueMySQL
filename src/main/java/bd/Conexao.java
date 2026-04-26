package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection conectar() {
        try {
            // preencha esses campos com as informações do seu banco

            String dataBase = "loja"; // ←
            String url = "jdbc:mysql://localhost:3306/" + dataBase;
            String usuario = "root";// ←
            String senha = "1234";// ←

            return DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
