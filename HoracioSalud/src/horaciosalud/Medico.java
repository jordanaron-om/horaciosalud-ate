package horaciosalud;
/** Representa a un médico y su especialidad dentro del establecimiento. */
public class Medico extends Persona {

    private final String especialidad;
    private boolean activo;

    public Medico(String codigo, String nombres, String apellidos,
                  String numeroDocumento, String telefono,
                  String especialidad) {
        super(codigo, nombres, apellidos, numeroDocumento, telefono);

        if (especialidad == null || especialidad.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "La especialidad no puede estar vacía.");
        }

        this.especialidad = especialidad.trim();
        this.activo = true;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public boolean isActivo() {
        return activo;
    }

    public void desactivar() {
        activo = false;
    }

    @Override
    public String getTipoPersona() {
        return "Médico";
    }
}
