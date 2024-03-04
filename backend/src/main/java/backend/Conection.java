package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conection {
    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/users";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    }
    
    public void close(Connection conexao) throws SQLException {
        if (conexao != null) {
            conexao.close();
        }
    }
}
