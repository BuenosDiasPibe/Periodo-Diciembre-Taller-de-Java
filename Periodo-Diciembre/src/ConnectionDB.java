import java.sql.*;
public class ConnectionDB {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/recuperacion";
            String user = "admin";
            String password = "Mica";
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established.");
        } catch (SQLException e) {
            System.out.println("Connection Failed " + e.getMessage());
        }
        return conn;
    }
}
