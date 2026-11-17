
package horaciosalud;
import java.time.LocalDate;
import java.util.Scanner;
import java.time.LocalDateTime;
public class HoracioSaludApp {

    private static final Scanner entrada = new Scanner(System.in);
    private static final GestorHoracioSalud gestor =
            GestorHoracioSalud.getInstancia();

    public static void main(String[] args) {
        int opcion;

        do {
            System.out.println("\n=== HORACIOSALUD ===");
            System.out.println("1. Registrar paciente");
            System.out.println("2. Registrar médico");
            System.out.println("3. Listar pacientes");
            System.out.println("4. Listar médicos");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(entrada.nextLine());

                switch (opcion) {
                    case 1:
                        registrarPaciente();
                        break;
                    case 2:
                        registrarMedico();
                        break;
                    case 3:
                        listarPacientes();
                        break;
                    case 4:
                        listarMedicos();
                        break;
                    case 5:
                        programarCita();
                        break;
                    case 6:
                        listarCitas();
                        break;
                    case 7:
                        registrarMedicamento();
                        break;
                    case 8:
                        ajustarStock();
                        break;
                    case 9:
                        listarMedicamentos();
                        break;
                    case 10:
                        generarReporte();
                        break;
                    case 0:
                        System.out.println("Saliendo del sistema.");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Escribe el número de una opción.");
                opcion = -1;
                } catch (java.time.format.DateTimeParseException e) {
    System.out.println("La fecha u hora no tiene el formato indicado.");
    opcion = -1;
            } catch (IllegalArgumentException e) {
                System.out.println("No se pudo completar la operación: "
                        + e.getMessage());
                opcion = -1;
            }
        } while (opcion != 0);
    }

    private static void registrarPaciente() {
        System.out.println("\n--- Registrar paciente ---");

        System.out.print("Código: ");
        String codigo = entrada.nextLine();

        System.out.print("Nombres: ");
        String nombres = entrada.nextLine();

        System.out.print("Apellidos: ");
        String apellidos = entrada.nextLine();

        System.out.print("Número de documento ficticio: ");
        String documento = entrada.nextLine();

        System.out.print("Teléfono ficticio: ");
        String telefono = entrada.nextLine();

        System.out.print("Fecha de nacimiento (AAAA-MM-DD): ");
        LocalDate fechaNacimiento = LocalDate.parse(entrada.nextLine());

        Paciente paciente = new Paciente(
                codigo, nombres, apellidos, documento, telefono,
                fechaNacimiento);

        gestor.registrarPaciente(paciente);
        System.out.println("Paciente registrado.");
    }

    private static void registrarMedico() {
        System.out.println("\n--- Registrar médico ---");

        System.out.print("Código: ");
        String codigo = entrada.nextLine();

        System.out.print("Nombres: ");
        String nombres = entrada.nextLine();

        System.out.print("Apellidos: ");
        String apellidos = entrada.nextLine();

        System.out.print("Número de documento ficticio: ");
        String documento = entrada.nextLine();

        System.out.print("Teléfono ficticio: ");
        String telefono = entrada.nextLine();

        System.out.print("Especialidad: ");
        String especialidad = entrada.nextLine();

        Medico medico = new Medico(
                codigo, nombres, apellidos, documento, telefono,
                especialidad);

        gestor.registrarMedico(medico);
        System.out.println("Médico registrado.");
    }

    private static void listarPacientes() {
        System.out.println("\n--- Pacientes registrados ---");

        if (gestor.getPacientes().isEmpty()) {
            System.out.println("Todavía no hay pacientes.");
            return;
        }

        for (Paciente paciente : gestor.getPacientes()) {
            System.out.println(paciente.getCodigo() + " - "
                    + paciente.getNombres() + " "
                    + paciente.getApellidos()
                    + " - Activo: " + paciente.isActivo());
        }
    }

    private static void listarMedicos() {
        System.out.println("5. Programar cita");
        System.out.println("6. Listar citas");
        System.out.println("7. Registrar medicamento");
        System.out.println("8. Ajustar stock de medicamento");
        System.out.println("9. Listar medicamentos");
        System.out.println("10. Generar reporte de atención");
        System.out.println("\n--- Médicos registrados ---");

        if (gestor.getMedicos().isEmpty()) {
            System.out.println("Todavía no hay médicos.");
            return;
        }

        for (Medico medico : gestor.getMedicos()) {
            System.out.println(medico.getCodigo() + " - "
                    + medico.getNombres() + " "
                    + medico.getApellidos() + " - "
                    + medico.getEspecialidad()
                    + " - Activo: " + medico.isActivo());
        }
    }
    private static void programarCita() {
    System.out.println("\n--- Programar cita ---");

    System.out.print("Código de la cita: ");
    String codigo = entrada.nextLine();

    System.out.print("Código del paciente: ");
    Paciente paciente = gestor.buscarPaciente(entrada.nextLine());

    System.out.print("Código del médico: ");
    Medico medico = gestor.buscarMedico(entrada.nextLine());

    System.out.print("Fecha y hora (AAAA-MM-DDTHH:MM): ");
    LocalDateTime fechaHora = LocalDateTime.parse(entrada.nextLine());

    CitaMedica cita = new CitaMedica(
            codigo, paciente, medico, fechaHora);

    gestor.programarCita(cita);
    System.out.println("Cita programada.");
}

private static void listarCitas() {
    System.out.println("\n--- Citas registradas ---");

    if (gestor.getCitas().isEmpty()) {
        System.out.println("Todavía no hay citas.");
        return;
    }

    for (CitaMedica cita : gestor.getCitas()) {
        System.out.println(cita.getCodigo()
                + " - Paciente: " + cita.getPaciente().getNombres()
                + " " + cita.getPaciente().getApellidos()
                + " - Médico: " + cita.getMedico().getNombres()
                + " - Fecha: " + cita.getFechaHora()
                + " - Estado: " + cita.getEstado());
    }
}

private static void registrarMedicamento() {
    System.out.println("\n--- Registrar medicamento ---");

    System.out.print("Código: ");
    String codigo = entrada.nextLine();

    System.out.print("Nombre: ");
    String nombre = entrada.nextLine();

    System.out.print("Presentación: ");
    String presentacion = entrada.nextLine();

    System.out.print("Stock inicial: ");
    int stock = Integer.parseInt(entrada.nextLine());

    System.out.print("Stock mínimo: ");
    int stockMinimo = Integer.parseInt(entrada.nextLine());

    Medicamento medicamento = new Medicamento(
            codigo, nombre, presentacion, stock, stockMinimo);

    gestor.registrarMedicamento(medicamento);
    System.out.println("Medicamento registrado.");
}

private static void ajustarStock() {
    System.out.print("Código del medicamento: ");
    Medicamento medicamento =
            gestor.buscarMedicamento(entrada.nextLine());

    if (medicamento == null) {
        System.out.println("No se encontró ese medicamento.");
        return;
    }

    System.out.println("1. Aumentar stock");
    System.out.println("2. Reducir stock");
    System.out.print("Elige una opción: ");
    int opcionStock = Integer.parseInt(entrada.nextLine());

    System.out.print("Cantidad: ");
    int cantidad = Integer.parseInt(entrada.nextLine());

    if (opcionStock == 1) {
        medicamento.aumentarStock(cantidad);
    } else if (opcionStock == 2) {
        medicamento.reducirStock(cantidad);
    } else {
        System.out.println("Opción no válida.");
        return;
    }

    System.out.println("Stock actual: " + medicamento.getStock());
}

private static void listarMedicamentos() {
    System.out.println("\n--- Inventario de medicamentos ---");

    if (gestor.getMedicamentos().isEmpty()) {
        System.out.println("Todavía no hay medicamentos.");
        return;
    }

    for (Medicamento medicamento : gestor.getMedicamentos()) {
        System.out.println(medicamento.getCodigo()
                + " - " + medicamento.getNombre()
                + " (" + medicamento.getPresentacion() + ")"
                + " - Stock: " + medicamento.getStock()
                + (medicamento.necesitaReposicion()
                        ? " - Requiere reposición" : ""));
    }
}

private static void generarReporte() {
    System.out.print("Fecha del reporte (AAAA-MM-DD): ");
    LocalDate fecha = LocalDate.parse(entrada.nextLine());

    ReporteAtencion reporte = new ReporteAtencion(gestor);
    System.out.println("\n" + reporte.generarResumen(fecha));
}
}
