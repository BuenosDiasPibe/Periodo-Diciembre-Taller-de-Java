import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAOImplement implements personaDAO {
    private static PersonaDAOImplement instance = null;

    private Connection connection;

    public PersonaDAOImplement(){
        this.connection = DatabaseConnection.getConnection();
    }

    public static PersonaDAOImplement getInstance(){
        if(instance == null){
            instance = new PersonaDAOImplement();
        }
        return instance;
    }

    @Override
    public void insert(Persona persona) {
       PreparedStatement preparedStatement = null;
       try {
            preparedStatement = connection.prepareStatement("INSERT INTO personas " + "(nombre, nacimiento, hijoas, idestudio) " + "VALUES (?, ?, ?, ?);");
            preparedStatement.setString(1, persona.getNombre());
            preparedStatement.setDate(2, new Date(persona.getFechaNacimiento().getTime()));
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
            statement.setDate(2, new Date(persona.getFechaNacimiento().getTime()));
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

        String queryFindId = "SELECT * FROM personas WHERE idpersona = ?;";

        try {
            statement = connection.prepareStatement(queryFindId);
            statement.setInt(1, id);
            result = statement.executeQuery();
            if(result.next()){
                Persona persona = new Persona();
                Estudios estudio = new Estudios();
                persona.setIdPersona(result.getInt("idpersona"));
                persona.setNombre(result.getString("nombre"));
                persona.setFechaNacimiento(result.getDate("nacimiento"));
                estudio.setIdEstudio(result.getInt("idestudio"));
                persona.setNivelDeEstudios(estudio);
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
            statement = connection.prepareStatement("SELECT * FROM personas");

            result = statement.executeQuery();
            List<Persona> personas = new ArrayList<>();

            while(result.next()){
                personas.add(new Persona(
                    result.getInt("idpersona"),
                    result.getString("nombre"),
                    result.getDate("nacimiento"),
                    EstudiosDAOImplement.getInstance().findById(result.getInt("idestudio")),
                    result.getBoolean("hijoas")
                ));
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

    private Persona instancePersona(ResultSet result, Estudios estudio) throws SQLException{
        Persona persona = new Persona();
        persona.setIdPersona(result.getInt("idpersona"));
        persona.setNombre(result.getString("nombre"));
        persona.setFechaNacimiento(result.getDate("nacimiento"));
        persona.setTieneHijos(result.getBoolean("hijoas"));
        persona.setNivelDeEstudios(estudio);
        return persona;
    }
    
}
