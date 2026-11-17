package horaciosalud;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
public class ReporteAtencion {
    private final GestorHoracioSalud gestor;

    public ReporteAtencion(GestorHoracioSalud gestor) {
        if (gestor == null) {
            throw new IllegalArgumentException(
                    "El gestor no puede ser nulo.");
        }
        this.gestor = gestor;
    }

    public long contarPacientesActivos() {
        return gestor.getPacientes()
                .stream()
                .filter(Paciente::isActivo)
                .count();
    }

    public List<CitaMedica> obtenerCitasPorFecha(LocalDate fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException(
                    "La fecha no puede ser nula.");
        }

        return gestor.getCitas()
                .stream()
                .filter(cita -> cita.getFechaHora()
                        .toLocalDate().equals(fecha))
                .collect(Collectors.toList());
    }

    public List<Medicamento> obtenerMedicamentosParaReposicion() {
        return gestor.getMedicamentos()
                .stream()
                .filter(Medicamento::necesitaReposicion)
                .collect(Collectors.toList());
    }

    public String generarResumen(LocalDate fecha) {
        List<CitaMedica> citasDelDia = obtenerCitasPorFecha(fecha);

        long programadas = citasDelDia.stream()
                .filter(cita -> cita.getEstado()
                        == CitaMedica.Estado.PROGRAMADA)
                .count();

        long atendidas = citasDelDia.stream()
                .filter(cita -> cita.getEstado()
                        == CitaMedica.Estado.ATENDIDA)
                .count();

        long canceladas = citasDelDia.stream()
                .filter(cita -> cita.getEstado()
                        == CitaMedica.Estado.CANCELADA)
                .count();

        return "REPORTE DE ATENCIÓN - " + fecha + "\n"
                + "Pacientes activos: " + contarPacientesActivos() + "\n"
                + "Citas del día: " + citasDelDia.size() + "\n"
                + "Citas programadas: " + programadas + "\n"
                + "Citas atendidas: " + atendidas + "\n"
                + "Citas canceladas: " + canceladas + "\n"
                + "Medicamentos por reponer: "
                + obtenerMedicamentosParaReposicion().size();
    }
}
