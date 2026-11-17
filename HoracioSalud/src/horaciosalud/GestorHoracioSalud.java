package horaciosalud;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class GestorHoracioSalud {
     private static final GestorHoracioSalud INSTANCIA =
            new GestorHoracioSalud();

    private final List<Paciente> pacientes = new ArrayList<>();
    private final List<Medico> medicos = new ArrayList<>();
    private final List<CitaMedica> citas = new ArrayList<>();
    private final List<Medicamento> medicamentos = new ArrayList<>();

    private GestorHoracioSalud() {
    }

    public static GestorHoracioSalud getInstancia() {
        return INSTANCIA;
    }

    public void registrarPaciente(Paciente paciente) {
        if (paciente == null || buscarPaciente(paciente.getCodigo()) != null) {
            throw new IllegalArgumentException(
                    "El paciente es nulo o su código ya está registrado.");
        }
        pacientes.add(paciente);
    }

    public Paciente buscarPaciente(String codigo) {
        for (Paciente paciente : pacientes) {
            if (paciente.getCodigo().equals(codigo)) {
                return paciente;
            }
        }
        return null;
    }

    public void registrarMedico(Medico medico) {
        if (medico == null || buscarMedico(medico.getCodigo()) != null) {
            throw new IllegalArgumentException(
                    "El médico es nulo o su código ya está registrado.");
        }
        medicos.add(medico);
    }

    public Medico buscarMedico(String codigo) {
        for (Medico medico : medicos) {
            if (medico.getCodigo().equals(codigo)) {
                return medico;
            }
        }
        return null;
    }

    public void registrarMedicamento(Medicamento medicamento) {
        if (medicamento == null
                || buscarMedicamento(medicamento.getCodigo()) != null) {
            throw new IllegalArgumentException(
                    "El medicamento es nulo o su código ya está registrado.");
        }
        medicamentos.add(medicamento);
    }

    public Medicamento buscarMedicamento(String codigo) {
        for (Medicamento medicamento : medicamentos) {
            if (medicamento.getCodigo().equals(codigo)) {
                return medicamento;
            }
        }
        return null;
    }

    public void programarCita(CitaMedica cita) {
        if (cita == null) {
            throw new IllegalArgumentException("La cita no puede ser nula.");
        }

        for (CitaMedica existente : citas) {
            if (existente.getCodigo().equals(cita.getCodigo())) {
                throw new IllegalArgumentException(
                        "El código de la cita ya está registrado.");
            }

            boolean mismaFecha = existente.getFechaHora()
                    .equals(cita.getFechaHora());
            boolean noCancelada = existente.getEstado()
                    != CitaMedica.Estado.CANCELADA;

            if (mismaFecha && noCancelada
                    && existente.getMedico().getCodigo()
                            .equals(cita.getMedico().getCodigo())) {
                throw new IllegalArgumentException(
                        "El médico ya tiene una cita en ese horario.");
            }
        }

        citas.add(cita);
    }

    public List<Paciente> getPacientes() {
        return Collections.unmodifiableList(pacientes);
    }

    public List<Medico> getMedicos() {
        return Collections.unmodifiableList(medicos);
    }

    public List<CitaMedica> getCitas() {
        return Collections.unmodifiableList(citas);
    }

    public List<Medicamento> getMedicamentos() {
        return Collections.unmodifiableList(medicamentos);
    }
}
