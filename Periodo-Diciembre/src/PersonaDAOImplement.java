import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PersonaDAOImplement implements personaDAO {

    private Connection connection;

    public PersonaDAOImplement(Connection connection){
        this.connection = connection;
    }

    @Override
    public void insert(Persona persona) {
       PreparedStatement preparedStatement = null;
       try {
            preparedStatement = connection.prepareStatement("INSERT INTO personas " + "(nombre, nacimiento, hijoas, idestudio) " + "VALUES (?, ?, ?, ?);");
            preparedStatement.setString(1, persona.getNombre());
            preparedStatement.setDate(2, java.sql.Date.valueOf(persona.getFechaNacimiento()));
            preparedStatement.setBoolean(3, persona.isTieneHijos());
            preparedStatement.setInt(4, persona.getNivelDeEstudios());

            int filasAfectadas = preparedStatement.executeUpdate();
            if(filasAfectadas > 0){
                ResultSet result = preparedStatement.getGeneratedKeys();
                if(result.next()){
                    int id = result.getInt(1);
                    persona.setIdPersona(id);
                }
                DatabaseConnection.closeResultSet(result);  
            }
       } catch (Exception e) {
        e.printStackTrace();
       }finally{
        DatabaseConnection.closeStatement(preparedStatement);
       }
    }

    @Override
    public void deleteById(Integer idPersona) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM personas WHERE idpersona = ?");
            statement.setInt(1, idPersona);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DatabaseConnection.closeStatement(statement);
        }
    }

    @Override
    public void update(Persona persona) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("UPDATE personas " + "SET nombre = ?, nacimiento = ?, hijoas = ?, idestudio = ? " + "WHERE idpersona = ?");
            statement.setString(1, persona.getNombre());
            statement.setDate(2, java.sql.Date.valueOf(persona.getFechaNacimiento()));
            statement.setBoolean(3, persona.isTieneHijos());
            statement.setInt(4, persona.getNivelDeEstudios());
            statement.setInt(5, persona.getIdPersona());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DatabaseConnection.closeStatement(statement);
        }

    }

    @Override
    public Persona findById(Integer id) {
        PreparedStatement statement = null;
        ResultSet result = null;

        String queryFindId = "SELECT personas.*" + ;
    }

    @Override
    public List<Persona> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
    
}
