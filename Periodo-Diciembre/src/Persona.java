public class Persona {
    private String nombre;
    private int fechaNacimiento;
    private String nivelDeEstudios;
    private boolean tieneHijos;

    public Persona(String nombre, int fechaNacimiento, String nivelDeEstudios, boolean tieneHijos) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.nivelDeEstudios = nivelDeEstudios;
        this.tieneHijos = tieneHijos;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(int fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public String getNivelDeEstudios() {
        return nivelDeEstudios;
    }
    public void setNivelDeEstudios(String nivelDeEstudios) {
        this.nivelDeEstudios = nivelDeEstudios;
    }
    public boolean isTieneHijos() {
        return tieneHijos;
    }
    public void setTieneHijos(boolean tieneHijos) {
        this.tieneHijos = tieneHijos;
    }
}
