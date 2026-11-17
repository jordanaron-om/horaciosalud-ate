package horaciosalud;
/** Clase abstracta que reúne los datos comunes de pacientes y médicos. */
public abstract class Persona {
     private final String codigo;
    private final String nombres;
    private final String apellidos;
    private final String numeroDocumento;
    private String telefono;

    protected Persona(String codigo, String nombres, String apellidos,
                      String numeroDocumento, String telefono) {
        this.codigo = validar(codigo, "El código");
        this.nombres = validar(nombres, "Los nombres");
        this.apellidos = validar(apellidos, "Los apellidos");
        this.numeroDocumento = validar(numeroDocumento, "El documento");
        this.telefono = validar(telefono, "El teléfono");
    }

    private static String validar(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " no puede estar vacío.");
        }
        return valor.trim();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public String getTelefono() {
        return telefono;
    }
/**
 * Actualiza el teléfono después de validar que no esté vacío.
 *
 * @param telefono nuevo teléfono ficticio de contacto
 */
public void setTelefono(String telefono) {
    this.telefono = validar(telefono, "El teléfono");
}
    public abstract String getTipoPersona();
}
