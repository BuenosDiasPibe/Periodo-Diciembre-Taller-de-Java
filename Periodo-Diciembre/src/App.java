import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) throws Exception {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Pantalla1 something = new Pantalla1();
                something.setVisible(true);
            }
        });

    }
}
