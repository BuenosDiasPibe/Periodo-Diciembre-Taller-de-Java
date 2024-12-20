
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.Connection;
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
    
    JScrollPane scroll = new JScrollPane(tabla);

    private String[] lista = {"nombre", "fecha nacimiento", "hijos/as?, nivel estudios"};

    public Pantalla1(Connection conn) {

        setTitle("Pantalla 1");
        setSize(800, 600);
        setResizable(false);
        //scroll.setViewportView();
        setVisible(true);
        
        setLayout(new FlowLayout());

        layout.setVgap(20);
        botonesPanel.setLayout(layout);

        nuevo.addActionListener(e -> {
            dispose();
            Pantalla2 pantalla2 = new Pantalla2(conn);
        });

        modificar.addActionListener(l -> {
            dispose();
            Pantalla2 pantalla = new Pantalla2(conn);
            pantalla.modifyInstance(new Persona()); //TODO get the selected row
        });

        add(scroll);
        botonesPanel.add(this.nuevo);
        botonesPanel.add(this.eliminar);
        botonesPanel.add(this.modificar);

        add(botonesPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // private JTable cargarPersonas(){
    //     PersonaDAOImplement persona = new PersonaDAOImplement();
    //     List<Persona> personas = persona.findAll();

    //     Object[][] data = new Object[personas.size()][4];
    //     for(int i = 0; i < personas.size(); i++){
    //         data[i][0] = personas.get(i).getNombre();
    //         data[i][1] = personas.get(i).getFechaNacimiento();
    //         data[i][2] = personas.get(i).getHijos();
    //         data[i][3] = personas.get(i).getNivelEstudios();
    //     }
        
    // }
}
