import java.util.List;

public interface personaDAO {
    public void insert(Persona persona);
    public void update(Persona persona);
    
    public Persona findById(Integer id);
    public void deleteById(Integer id);
    public List<Persona> findAll();
}
