
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
    JCheckBox hijos = new JCheckBox("Hijos");
    Boton submit = new Boton("Submit");

    private JComboBox cargarComboBox(){
        EstudiosDAOImplement estudiosDAO = EstudiosDAOImplement.getInstance();
        List<Estudios> estudio = estudiosDAO.findAll();

        String[] data = new String[estudio.size()];
        for(int i = 0; i < estudio.size(); i++){
            data[i] = estudio.get(i).getNombre();
        }
        return new JComboBox<String>(data);
    }
    private JComboBox combazobox = cargarComboBox();

    public Pantalla2() {
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
        panel.add(combazobox);

        panel.add(hijos);
        panel.add(new JLabel(""));

        panel.add(submit);

        submit.addActionListener(e -> {
            dispose();
            Pantalla1 pantalla1 = new Pantalla1();
        });

        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void modifyInstance(Persona persona){
        nombre.setText(persona.getNombre());
        nacimiento.setText(persona.getFechaNacimiento().toString());
        hijos.setSelected(persona.isTieneHijos());
        combazobox.setSelectedItem(persona.getNivelDeEstudios().getNombre());
    }
}
