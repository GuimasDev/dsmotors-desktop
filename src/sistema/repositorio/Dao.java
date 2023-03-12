package repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Dao {
    private static String url = "jdbc:mysql://localhost:3306/oficina";
    private static String user = "root";
    private static String pwd = "";

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        // Verificar se o Driver do mysql foi referenciado.
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, user, pwd);

        return conn;
    }
}