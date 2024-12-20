import java.util.List;
public interface EstudiosDAO {
    public Estudios findById(int id);
    public List<Estudios> findAll();
}
