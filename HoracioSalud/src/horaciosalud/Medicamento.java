package horaciosalud;

public class Medicamento {
     private final String codigo;
    private final String nombre;
    private final String presentacion;
    private int stock;
    private final int stockMinimo;

    public Medicamento(String codigo, String nombre, String presentacion,
                       int stock, int stockMinimo) {
        this.codigo = validarTexto(codigo, "El código");
        this.nombre = validarTexto(nombre, "El nombre");
        this.presentacion = validarTexto(presentacion, "La presentación");

        if (stock < 0 || stockMinimo < 0) {
            throw new IllegalArgumentException(
                    "El stock no puede ser negativo.");
        }

        this.stock = stock;
        this.stockMinimo = stockMinimo;
    }

    private static String validarTexto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " no puede estar vacío.");
        }
        return valor.trim();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public int getStock() {
        return stock;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void aumentarStock(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad debe ser mayor que cero.");
        }
        stock += cantidad;
    }

    public void reducirStock(int cantidad) {
        if (cantidad <= 0 || cantidad > stock) {
            throw new IllegalArgumentException(
                    "La cantidad no es válida para el stock disponible.");
        }
        stock -= cantidad;
    }

    public boolean necesitaReposicion() {
        return stock <= stockMinimo;
    }
}

