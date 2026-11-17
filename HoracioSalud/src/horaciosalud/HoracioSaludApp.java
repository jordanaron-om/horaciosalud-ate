
package horaciosalud;
import java.time.LocalDate;
import java.util.Scanner;

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
                    case 0:
                        System.out.println("Saliendo del sistema.");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Escribe el número de una opción.");
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
}
