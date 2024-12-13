import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/recuperacion","root","Mica");
            System.out.println("Connection established.");
        } catch (SQLException e) {
            System.out.println("Connection Failed " + e.getMessage());
        }
        return connection;
    }

    public static void closeConnection(Connection connection){
        if(connection != null){
            try {
                connection.close();
                System.out.println("closed");
            } catch (SQLException e) {
                e.printStackTrace(); //TODO create exception
            }
        }
    }

    public static void closeStatement(PreparedStatement statement){
        if(statement != null){
            try{
                statement.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    public static void closeResultSet(ResultSet resultSet){
        if(resultSet != null){
            try{
                resultSet.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
