package horaciosalud;

import java.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.*;

public class PacienteTest {

    @Test
    public void pacienteNuevoIniciaActivo() {
        Paciente paciente = new Paciente(
                "P999", "Ana", "Prueba", "00000000", "900000000",
                LocalDate.of(2000, 5, 15)
        );

        assertTrue(paciente.isActivo());
    }

    @Test(expected = IllegalArgumentException.class)
    public void rechazaCodigoVacio() {
        new Paciente(
                "", "Ana", "Prueba", "00000000", "900000000",
                LocalDate.of(2000, 5, 15)
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void rechazaFechaDeNacimientoFutura() {
        new Paciente(
                "P998", "Ana", "Prueba", "00000000", "900000000",
                LocalDate.now().plusDays(1)
        );
    }

    @Test
    public void desactivarCambiaElEstado() {
        Paciente paciente = new Paciente(
                "P997", "Ana", "Prueba", "00000000", "900000000",
                LocalDate.of(2000, 5, 15)
        );

        paciente.desactivar();

        assertFalse(paciente.isActivo());
    }
}