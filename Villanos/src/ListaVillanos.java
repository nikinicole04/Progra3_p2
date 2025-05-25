import java.util.ArrayList;

// Clase que gestiona una lista de villanos usando ArrayList
public class ListaVillanos {
    private ArrayList<Villano> lista = new ArrayList<>();

    // Métod para insertar villano ordenado por ID, evitando duplicados
    public boolean insertar(Villano nuevo) {
        for (Villano v : lista) {
            if (v.id == nuevo.id) {
                return false; // Ya existe
            }
        }

        // Insertar ordenadamente por ID
        int pos = 0;
        while (pos < lista.size() && lista.get(pos).id < nuevo.id) {
            pos++;
        }
        lista.add(pos, nuevo);
        return true;
    }

    // Búsqueda binaria por ID (lista debe estar ordenada por ID)
    public Villano buscar(int id) {
        int inicio = 0;
        int fin = lista.size() - 1;
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            Villano v = lista.get(medio);
            if (v.id == id) {
                return v;
            } else if (id < v.id) {
                fin = medio - 1;
            } else {
                inicio = medio + 1;
            }
        }
        return null;
    }

    // Filtrado por especialidad y ordenamiento por nivel de peligrosidad (mayor a menor)
    public ArrayList<Villano> filtrarYOrdenar(String especialidad) {
        ArrayList<Villano> filtrados = new ArrayList<>();
        for (Villano v : lista) {
            if (v.especialidad.equals(especialidad)) {
                filtrados.add(v);
            }
        }

        // Ordenamiento por selección descendente
        for (int i = 0; i < filtrados.size() - 1; i++) {
            int max = i;
            for (int j = i + 1; j < filtrados.size(); j++) {
                if (filtrados.get(j).nivelPeligrosidad > filtrados.get(max).nivelPeligrosidad) {
                    max = j;
                }
            }
            // Intercambio de elementos
            Villano temp = filtrados.get(i);
            filtrados.set(i, filtrados.get(max));
            filtrados.set(max, temp);
        }

        return filtrados;
    }

    // Conteo recursivo de villanos por ubicación
    public int contarPorUbicacion(String ubicacion) {
        return contarRecursivo(ubicacion, 0);
    }

    // Métod recursivo que cuenta los villanos que están en la ubicación dada
    private int contarRecursivo(String ubicacion, int index) {
        if (index >= lista.size()) {
            return 0;
        }
        if (lista.get(index).ubicacion.equals(ubicacion)) {
            return 1 + contarRecursivo(ubicacion, index + 1);
        } else {
            return contarRecursivo(ubicacion, index + 1);
        }
    }

    // Obtener toda la lista (por ejemplo para mostrar en JTable)
    public ArrayList<Villano> getLista() {
        return lista;
    }
}
