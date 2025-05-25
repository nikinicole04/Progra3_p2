import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class Ventana extends JFrame {
    private JTextField txtID, txtAlias;
    private JComboBox<String> cbEspecialidad, cbPeligrosidad, cbUbicacion;
    private JTable tabla;
    private JTextArea txtArea;
    private ListaVillanos gestor = new ListaVillanos();

    public Ventana() {
        setTitle("Escuadrón Suicida – Gestión de Villanos");
        setSize(800, 600);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Campos
        JLabel l1 = new JLabel("ID:");
        l1.setBounds(20, 20, 100, 20);
        add(l1);
        txtID = new JTextField();
        txtID.setBounds(120, 20, 100, 20);
        add(txtID);

        JLabel l2 = new JLabel("Alias:");
        l2.setBounds(20, 50, 100, 20);
        add(l2);
        txtAlias = new JTextField();
        txtAlias.setBounds(120, 50, 100, 20);
        add(txtAlias);

        JLabel l3 = new JLabel("Especialidad:");
        l3.setBounds(20, 80, 100, 20);
        add(l3);
        cbEspecialidad = new JComboBox<>(new String[]{
                "💣 Explosivos", "💻 Hackeo", "🧠 Manipulación", "🎭 Disfraz", "🧬 Biotecnología"
        });
        cbEspecialidad.setBounds(120, 80, 150, 20);
        add(cbEspecialidad);

        JLabel l4 = new JLabel("Peligrosidad:");
        l4.setBounds(20, 110, 100, 20);
        add(l4);
        cbPeligrosidad = new JComboBox<>();
        for (int i = 1; i <= 10; i++) cbPeligrosidad.addItem(String.valueOf(i));
        cbPeligrosidad.setBounds(120, 110, 50, 20);
        add(cbPeligrosidad);

        JLabel l5 = new JLabel("Ubicación:");
        l5.setBounds(20, 140, 100, 20);
        add(l5);
        cbUbicacion = new JComboBox<>(new String[]{
                "🏥 Arkham", "🏢 Belle Reve", "🛸 Área 51", "🏰 Fortaleza Oscura", "👻 Zona Fantasma"
        });
        cbUbicacion.setBounds(120, 140, 150, 20);
        add(cbUbicacion);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(20, 180, 100, 30);
        add(btnAgregar);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(130, 180, 100, 30);
        add(btnBuscar);

        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBounds(240, 180, 100, 30);
        add(btnFiltrar);

        JButton btnContar = new JButton("Contar por Ubicación");
        btnContar.setBounds(350, 180, 200, 30);
        add(btnContar);

        txtArea = new JTextArea();
        txtArea.setBounds(20, 220, 300, 100);
        add(txtArea);

        tabla = new JTable();
        tabla.setBounds(20, 340, 740, 200);
        add(tabla);

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
                    mostrarTabla(gestor.getLista());
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
            mostrarTabla(gestor.filtrarYOrdenar(esp));
        });

        // Botón Contar
        btnContar.addActionListener(e -> {
            txtArea.setText("");
            for (int i = 0; i < cbUbicacion.getItemCount(); i++) {
                String ubi = cbUbicacion.getItemAt(i);
                int c = gestor.contarPorUbicacion(ubi);
                txtArea.append(ubi + ": " + c + "\n");
            }
        });

        setVisible(true);
    }

    // Métod para mostrar lista en JTable
    private void mostrarTabla(java.util.List<Villano> lista) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Alias");
        model.addColumn("Especialidad");
        model.addColumn("Nivel");
        model.addColumn("Ubicación");

        for (Villano v : lista) {
            model.addRow(new Object[]{
                    v.id, v.alias, v.especialidad, v.nivelPeligrosidad, v.ubicacion
            });
        }
        tabla.setModel(model);
    }

    // Métod principal
    public static void main(String[] args) {
        new Ventana();
    }
}
