public class DAOFactory {
    public static personaDAO createPersonaDAO(){
        return new PersonaDAOImplement(DatabaseConnection.getConnection());
    }
}
