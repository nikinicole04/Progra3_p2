public class Jugador {
    private int codigo;
    private String nombre;
    private float rendimiento;
    private String posicion;

    public Jugador(int codigo, String nombre, float rendimiento, String posicion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.rendimiento = rendimiento;
        this.posicion = posicion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Jugador " +
                "codigo: " + codigo +
                ", nombre: " + nombre +
                ", rendimiento: " + rendimiento +
                ", posicion: " + posicion+"\n";
    }
}
