package horaciosalud;
import java.time.LocalDate;
	/** Representa a un paciente registrado en el sistema. */
public class Paciente extends Persona {

    private final LocalDate fechaNacimiento;
    private boolean activo;

    public Paciente(String codigo, String nombres, String apellidos,
                    String numeroDocumento, String telefono,
                    LocalDate fechaNacimiento) {
        super(codigo, nombres, apellidos, numeroDocumento, telefono);

        if (fechaNacimiento == null
                || fechaNacimiento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "La fecha de nacimiento no es válida.");
        }

        this.fechaNacimiento = fechaNacimiento;
        this.activo = true;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public boolean isActivo() {
        return activo;
    }

    public void desactivar() {
        activo = false;
    }

    @Override
    public String getTipoPersona() {
        return "Paciente";
    }
}
