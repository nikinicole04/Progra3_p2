import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private List<Jugador> lista;

    public Equipo() {
        lista = new ArrayList<>();
    }

    // Agregar jugador con validación de código único
    public void agregarJugador(Jugador nuevo) throws Exception {
        for (Jugador j : lista) {
            if (j.getCodigo() == nuevo.getCodigo())
                throw new Exception("El código no es único.");
        }
        lista.add(nuevo);
        ordenar(); // Ordenar por código
    }

    // Editar jugador por código
    public void editarJugador(int codigo, Jugador nuevoJugador) throws Exception {
        boolean encontrado = false;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCodigo() == codigo) {
                nuevoJugador.setCodigo(codigo); // mantener el código
                lista.set(i, nuevoJugador);
                ordenar();
                encontrado = true;
                break;
            }
        }
        if (!encontrado)
            throw new Exception("No se encontró el jugador.");
    }

    // Eliminar jugador
    public void eliminarJugador(int codigo) throws Exception {
        boolean eliminado = false;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCodigo() == codigo) {
                lista.remove(i);
                eliminado = true;
                break;
            }
        }
        if (!eliminado)
            throw new Exception("No existe el jugador.");
    }

    // Ordenamiento por código (burbuja)
    public void ordenar() {
        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = i + 1; j < lista.size(); j++) {
                if (lista.get(i).getCodigo() > lista.get(j).getCodigo()) {
                    Jugador aux = lista.get(i);
                    lista.set(i, lista.get(j));
                    lista.set(j, aux);
                }
            }
        }
    }

    // Búsqueda binaria (necesita lista ordenada)
    public Jugador buscarJugador(int codigo) throws Exception {
        int inf = 0;
        int sup = lista.size() - 1;
        while (inf <= sup) {
            int cen = (inf + sup) / 2;
            if (lista.get(cen).getCodigo() == codigo)
                return lista.get(cen);
            else if (codigo < lista.get(cen).getCodigo())
                sup = cen - 1;
            else
                inf = cen + 1;
        }
        throw new Exception("No se encontró el jugador.");
    }

    // Mostrar todos
    public String listarTodos() {
        StringBuilder sb = new StringBuilder();
        for (Jugador j : lista)
            sb.append(j.toString());
        return sb.toString();
    }
}