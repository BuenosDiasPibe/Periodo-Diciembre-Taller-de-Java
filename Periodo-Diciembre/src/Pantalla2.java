
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Pantalla2 extends JFrame{
    
    GridLayout layout = new GridLayout(5,2);

    JPanel panel = new JPanel();

    JTextField nombre = new JTextField(16);
    JTextField nacimiento = new JTextField(16);
    JList lista = new JList();
    JCheckBox hijos = new JCheckBox("Hijos");
    Boton submit = new Boton("Submit");

    public Pantalla2(Connection conn) {
        setTitle("Pantalla 1");
        setSize(800, 600);
        setResizable(false);
        setVisible(true);
        
        setLayout(new FlowLayout());

        layout.setVgap(10);

        panel.setLayout(layout);

        panel.add(new JLabel("Nombre: "));
        panel.add(nombre);

        panel.add(new JLabel("Nacimiento"));
        panel.add(nacimiento);

        panel.add(new JLabel("Estudios"));
        panel.add(lista);

        panel.add(hijos);
        panel.add(new JLabel(""));

        panel.add(submit);

        submit.addActionListener(e -> {
            dispose();
            Pantalla1 pantalla1 = new Pantalla1(conn);
        });

        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void modifyInstance(Persona personaUpdate){
        this.nombre.setText(personaUpdate.getNombre());
        this.nacimiento.setText("" + personaUpdate.getFechaNacimiento());
        this.lista.setListData(new Vector<String>()); //TODO crear una clase estudiosDAO y añadir el metodo un findAll, luego, implementarlo en la lista
        this.hijos.setSelected(personaUpdate.isTieneHijos());
    }
}
