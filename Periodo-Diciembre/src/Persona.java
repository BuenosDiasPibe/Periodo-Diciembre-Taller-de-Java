
import java.time.LocalDate;

public class Persona {
    private int idPersona;
    private String nombre;
    private LocalDate fechaNacimiento;
    private int nivelDeEstudios;
    private boolean tieneHijos;

    public Persona(int idPersona, String nombre, LocalDate fechaNacimiento, int nivelDeEstudios, boolean tieneHijos) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.nivelDeEstudios = nivelDeEstudios;
        this.tieneHijos = tieneHijos;
    }

    public int getIdPersona(){
        return this.idPersona;
    }
    public void setIdPersona(int idPersona){
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public int getNivelDeEstudios() {
        return nivelDeEstudios;
    }
    public void setNivelDeEstudios(int nivelDeEstudios) {
        this.nivelDeEstudios = nivelDeEstudios;
    }
    public boolean isTieneHijos() {
        return tieneHijos;
    }
    public void setTieneHijos(boolean tieneHijos) {
        this.tieneHijos = tieneHijos;
    }
    @Override
    public String toString() {
        return "Persona [" +
                "idPersona=" + idPersona +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", nivelDeEstudios=" + nivelDeEstudios +
                ", tieneHijos=" + tieneHijos +
                ']';
    }

}
