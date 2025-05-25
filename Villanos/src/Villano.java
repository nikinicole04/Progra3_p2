// Clase que representa a un Villano con sus atributos básicos
public class Villano {
    int id;
    String alias;
    String especialidad;
    int nivelPeligrosidad;
    String ubicacion;

    // Constructor
    public Villano(int id, String alias, String especialidad, int nivelPeligrosidad, String ubicacion) {
        this.id = id;
        this.alias = alias;
        this.especialidad = especialidad;
        this.nivelPeligrosidad = nivelPeligrosidad;
        this.ubicacion = ubicacion;
    }

    // Getter necesario para búsqueda

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getNivelPeligrosidad() {
        return nivelPeligrosidad;
    }

    public void setNivelPeligrosidad(int nivelPeligrosidad) {
        this.nivelPeligrosidad = nivelPeligrosidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "Villano{" +
                "id=" + id +
                ", alias='" + alias + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", nivelPeligrosidad=" + nivelPeligrosidad +
                ", ubicacion='" + ubicacion + '\'' +
                '}';
    }
}
