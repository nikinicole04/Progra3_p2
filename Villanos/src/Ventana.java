import javax.swing.*;
import java.util.ArrayList;

public class Ventana extends JFrame {

    private JPanel principal;
    private JTextField txtID;
    private JTextField txtAlias;
    private JComboBox cbEspecialidad;
    private JComboBox cbPeligrosidad;
    private JComboBox cbUbicacion;
    private JButton btnAgregar;
    private JButton btnBuscar;
    private JButton btnFiltrar;
    private JButton btnContar;
    private JTextArea txtMostrar;

    private ListaVillanos gestor = new ListaVillanos();

    public Ventana() {
        setTitle("Gestión de Villanos");
        setContentPane(principal);
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Opciones iniciales
        cbEspecialidad.addItem("Explosivos");
        cbEspecialidad.addItem("Tecnología");
        cbEspecialidad.addItem("Psíquico");

        cbPeligrosidad.addItem("1");
        cbPeligrosidad.addItem("2");
        cbPeligrosidad.addItem("3");

        cbUbicacion.addItem("Arkham");
        cbUbicacion.addItem("Gotham");
        cbUbicacion.addItem("Metropolis");

        // Botón Agregar
        btnAgregar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtID.getText());
                String alias = txtAlias.getText();
                String esp = (String) cbEspecialidad.getSelectedItem();
                int peligro = Integer.parseInt((String) cbPeligrosidad.getSelectedItem());
                String ubi = (String) cbUbicacion.getSelectedItem();

                Villano nuevo = new Villano(id, alias, esp, peligro, ubi);
                if (gestor.insertar(nuevo)) {
                    JOptionPane.showMessageDialog(this, "Villano agregado");
                    mostrarLista(gestor.getLista());
                } else {
                    JOptionPane.showMessageDialog(this, "ID repetido");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error en los datos");
            }
        });

        // Botón Buscar
        btnBuscar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtID.getText());
                Villano v = gestor.buscar(id);
                if (v != null) {
                    txtAlias.setText(v.alias);
                    cbEspecialidad.setSelectedItem(v.especialidad);
                    cbPeligrosidad.setSelectedItem(String.valueOf(v.nivelPeligrosidad));
                    cbUbicacion.setSelectedItem(v.ubicacion);
                    JOptionPane.showMessageDialog(this, "Villano encontrado");
                } else {
                    JOptionPane.showMessageDialog(this, "No encontrado");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "ID inválido");
            }
        });

        // Botón Filtrar
        btnFiltrar.addActionListener(e -> {
            String esp = (String) cbEspecialidad.getSelectedItem();
            mostrarLista(gestor.filtrarYOrdenar(esp));
        });

        // Botón Contar
        btnContar.addActionListener(e -> {
            txtMostrar.setText("");
            for (int i = 0; i < cbUbicacion.getItemCount(); i++) {
                String ubi = (String) cbUbicacion.getItemAt(i);
                int c = gestor.contarPorUbicacion(ubi);
                txtMostrar.append(ubi + ": " + c + "\n");
            }
        });
    }

    // Método auxiliar para mostrar resultados en el JTextArea
    private void mostrarLista(ArrayList<Villano> lista) {
        txtMostrar.setText("");
        for (Villano v : lista) {
            txtMostrar.append("ID: " + v.id + ", Alias: " + v.alias +
                    ", Esp: " + v.especialidad + ", Nivel: " + v.nivelPeligrosidad +
                    ", Ubi: " + v.ubicacion + "\n");
        }
    }

    public static void main(String[] args) {
        new Ventana().setVisible(true);
    }
}
