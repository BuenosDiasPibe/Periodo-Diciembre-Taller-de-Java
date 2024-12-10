
import java.awt.FlowLayout;
import java.awt.GridLayout;
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

    JTable tabla = new JTable(1,3);
    JScrollPane scroll = new JScrollPane(tabla);

    public Pantalla1() {
        setTitle("Pantalla 1");
        setSize(800, 600);
        setResizable(false);
        setVisible(true);
        
        setLayout(new FlowLayout());

        layout.setVgap(20);
        botonesPanel.setLayout(layout);

        add(scroll);
        botonesPanel.add(this.nuevo); //TODO añadir funcionalidad para cambiar entre pantallas
        botonesPanel.add(this.eliminar);
        botonesPanel.add(this.modificar); //TODO añadir funcionalidad para cambiar entre pantallas

        add(botonesPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
