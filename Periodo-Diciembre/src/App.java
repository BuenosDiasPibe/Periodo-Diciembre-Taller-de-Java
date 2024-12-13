import java.sql.Connection;
import javax.swing.SwingUtilities;

public class App {
    private Connection so;
    public static void main(String[] args) throws Exception {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Connection so = DatabaseConnection.getConnection(); //TODO no se si sirve
                
                Pantalla1 something = new Pantalla1(so);
                something.setVisible(true);

            }
        });
    }
}
