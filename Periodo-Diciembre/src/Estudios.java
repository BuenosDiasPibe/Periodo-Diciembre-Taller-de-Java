public class Estudios {
    private int idEstudio;
    private String nombre;

    public Estudios(){}

    public Estudios(int idEstudio, String nombre) {
        this.idEstudio = idEstudio;
        this.nombre = nombre;
    }

    public int getIdEstudio() {
        return idEstudio;
    }

    public void setIdEstudio(int idEstudio) {
        this.idEstudio = idEstudio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
