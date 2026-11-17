package horaciosalud;
import java.time.LocalDateTime;

public class CitaMedica {

    public enum Estado {
        PROGRAMADA, ATENDIDA, CANCELADA
    }

    private final String codigo;
    private final Paciente paciente;
    private final Medico medico;
    private final LocalDateTime fechaHora;
    private Estado estado;

    public CitaMedica(String codigo, Paciente paciente, Medico medico,
                      LocalDateTime fechaHora) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código no puede estar vacío.");
        }
        if (paciente == null || medico == null || fechaHora == null) {
            throw new IllegalArgumentException(
                    "Paciente, médico y fecha son obligatorios.");
        }
        if (!paciente.isActivo() || !medico.isActivo()) {
            throw new IllegalArgumentException(
                    "El paciente y el médico deben estar activos.");
        }

        this.codigo = codigo.trim();
        this.paciente = paciente;
        this.medico = medico;
        this.fechaHora = fechaHora;
        this.estado = Estado.PROGRAMADA;
    }

    public String getCodigo() {
        return codigo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public Estado getEstado() {
        return estado;
    }

    public void cancelar() {
        if (estado == Estado.ATENDIDA) {
            throw new IllegalStateException(
                    "No se puede cancelar una cita atendida.");
        }
        estado = Estado.CANCELADA;
    }

    public void marcarAtendida() {
        if (estado == Estado.CANCELADA) {
            throw new IllegalStateException(
                    "No se puede atender una cita cancelada.");
        }
        estado = Estado.ATENDIDA;
    }
}
