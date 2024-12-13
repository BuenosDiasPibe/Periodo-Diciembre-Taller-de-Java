import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            preparedStatement.setInt(4, persona.getNivelDeEstudios().getIdEstudio());

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
            statement.setInt(4, persona.getNivelDeEstudios().getIdEstudio());
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

        String queryFindId = "SELECT personas.*, estudios.nombre " + "FROM personas INNER JOIN estudios " + "ON personas.idestudio = estudios.idestudio " + "WHERE idpersona = ?";
        try {
            statement = connection.prepareStatement(queryFindId);
            statement.setInt(1, id);
            result = statement.executeQuery();
            if(result.next()){
                Persona persona = new Persona();
                persona.setIdPersona(result.getInt("idpersona"));
                persona.setNombre(result.getString("nombre"));
                persona.setFechaNacimiento(result.getDate("nacimiento").toLocalDate());
                persona.setTieneHijos(result.getBoolean("hijoas"));
                persona.getNivelDeEstudios().setIdEstudio(result.getInt("idestudio"));
                persona.getNivelDeEstudios().setNombre(result.getString("nombre"));

                return persona;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DatabaseConnection.closeResultSet(result);
            DatabaseConnection.closeStatement(statement);
        }
        return null;
    }

    @Override
    public List<Persona> findAll() {
        PreparedStatement statement = null;
        ResultSet result = null;
        try{
            statement = connection.prepareStatement("SELECT personas.*, estudios.nombre " 
                                                    + "FROM personas INNER JOIN estudios " 
                                                    + "ON personas.idestudio = estudios.idestudio " 
                                                    + "ORDER BY nombre");

            result = statement.executeQuery();
            List<Persona> personas = new ArrayList<>();
            Map<Integer, Estudios> estudios = new HashMap<>();

            while(result.next()){
                Estudios estudio = estudios.get(result.getInt("idestudio"));

                if(estudio == null){
                    estudio = new Estudios();
                    estudios.put(result.getInt("idestudio"), estudio);
                }

                Persona persona = instancePersona(result, estudio);
                personas.add(persona);
            }
            return personas;
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            DatabaseConnection.closeResultSet(result);
            DatabaseConnection.closeStatement(statement);
        }
        return null;
    }
    
    private Estudios instanceEstudio(ResultSet result) throws SQLException{
        Estudios estudio = new Estudios();
        estudio.setIdEstudio(result.getInt("idestudio"));
        estudio.setNombre(result.getString("nombre"));
        return estudio;
    }

    private Persona instancePersona(ResultSet result, Estudios estudio) throws SQLException{
        Persona persona = new Persona();
        persona.setIdPersona(result.getInt("idpersona"));
        persona.setNombre(result.getString("nombre"));
        persona.setFechaNacimiento(result.getDate("nacimiento").toLocalDate());
        persona.setTieneHijos(result.getBoolean("hijoas"));
        persona.setNivelDeEstudios(estudio);
        return persona;
    }
}
