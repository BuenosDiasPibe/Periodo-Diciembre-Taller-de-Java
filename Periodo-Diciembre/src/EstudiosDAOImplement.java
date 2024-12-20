
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstudiosDAOImplement implements EstudiosDAO {
    private static EstudiosDAOImplement instance = null;
    private Connection connection;

    public EstudiosDAOImplement(){
        this.connection = DatabaseConnection.getConnection();
    }

    public static EstudiosDAOImplement getInstance(){
        if(instance == null){
            instance = new EstudiosDAOImplement();
        }
        return instance;
    }

    @Override
    public Estudios findById(int id) {
        ResultSet result = null;

        String queryFIndId = "SELECT estudios.* FROM estudios WHERE idestudio = ?";
        try (PreparedStatement statement = connection.prepareStatement(queryFIndId)){
            statement.setInt(1,id);
            result = statement.executeQuery();

            if(result.next()){
                return new Estudios(
                    result.getInt("idestudio"),
                    result.getString("nombre")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DatabaseConnection.closeResultSet(result);
        }
        return null;
    }

    @Override
    public List<Estudios> findAll(){
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM estudios");
            result = statement.executeQuery();
            List<Estudios> estudios = new ArrayList<>();
            while(result.next()){
                estudios.add(new Estudios(
                    result.getInt("idestudio"),
                    result.getString("nombre")
                ));   
            }
            return estudios;
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DatabaseConnection.closeResultSet(result);
            DatabaseConnection.closeStatement(statement);
        }
        return null;
    }
}
