
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Pantalla1 extends JFrame{

    GridLayout layout = new GridLayout(3,1);

    JPanel botonesPanel = new JPanel();

    Boton nuevo = new Boton("nuevo");
    JButton eliminar = new Boton("eliminar");
    JButton modificar = new Boton("modificar");

    JTable tabla = new JTable(1,4);
    JScrollPane scroll = new JScrollPane(cargarTabla());

    private static String[] lista = {"a","b","c","d","e"};

    private JTable cargarTabla(){
        PersonaDAOImplement personaDAO = PersonaDAOImplement.getInstance();
        List<Persona> persona = personaDAO.findAll();

        Object[][] data = new Object[persona.size()][5];
        for(int i = 0; i < persona.size(); i++){
            data[i][0] = persona.get(i).getIdPersona();
            data[i][1] = persona.get(i).getNombre();
            data[i][2] = persona.get(i).getFechaNacimiento();
            data[i][3] = persona.get(i).getNivelDeEstudios().getNombre();
            data[i][4] = persona.get(i).isTieneHijos();
        }
        JTable table = new JTable(data, lista);

        return table;
    }


    public Pantalla1() {
        setTitle("Pantalla 1");
        setSize(800, 600);
        setResizable(false);
        setVisible(true);
        
        setLayout(new FlowLayout());

        layout.setVgap(20);
        botonesPanel.setLayout(layout);

        nuevo.addActionListener(e -> {
            dispose();
            Pantalla2 pantalla2 = new Pantalla2();
        });

        modificar.addActionListener(l -> {
            dispose();
            Pantalla2 pantalla = new Pantalla2();
            pantalla.modifyInstance(new Persona());
        });

        add(scroll);
        botonesPanel.add(this.nuevo);
        botonesPanel.add(this.eliminar);
        botonesPanel.add(this.modificar);

        add(botonesPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
