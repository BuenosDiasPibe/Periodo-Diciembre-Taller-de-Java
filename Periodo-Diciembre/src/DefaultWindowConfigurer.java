import javax.swing.JFrame;

public class DefaultWindowConfigurer implements WindowConfigurer{
    private String cambiarSiNecesario;

    public void setCambiarSiNecesario(String cambiarSiNecesario) {
        this.cambiarSiNecesario = cambiarSiNecesario;
    }
    public String getCambiarSiNecesario(){
        return cambiarSiNecesario;
    }
    public void configure(JFrame window){
        getCambiarSiNecesario();
    }
}
