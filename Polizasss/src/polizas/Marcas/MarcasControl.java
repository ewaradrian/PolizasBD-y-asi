package polizas.Marcas;
import java.awt.event.MouseAdapter;
import java.util.List;
import javax.swing.JOptionPane;

public class MarcasControl {
    private MarcasVista mv;
    private MarcasModelo mm;
    private List<Marca> lista;

    public MarcasControl(MarcasVista marcasVista) {
        this.mv = marcasVista;
        this.mm = new MarcasModelo();
        marcasEventos();
    }

    public void marcasEventos() {
        // Consultar
        mv.btn1.addActionListener(e -> {
            lista = mm.read();
            mv.actualizarVista(lista);
        });

        // Agregar con validación
        mv.btn_a.addActionListener(e -> {
            String nombre = mv.txt1.getText().trim();
            if (nombre.isEmpty() || nombre.equalsIgnoreCase("Marca...")) {
                JOptionPane.showMessageDialog(null, "El campo no puede estar vacío.");
                return;
            }
            if (mm.existeMarca(nombre)) {
                JOptionPane.showMessageDialog(null, "Esta marca ya se encuentra registrada.");
                return;
            }

            Object marca = new Marca(0, nombre);
            if (mm.create(marca) > 0) {
                JOptionPane.showMessageDialog(null, "Marca registrada correctamente.");
            }
            mv.actualizarVista(mm.read());
            mv.txt1.setText("");
        });

        // Actualizar con validación
        mv.btn_u.addActionListener(e -> {
            int fila = mv.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Seleccione un registro de la tabla primero.");
                return;
            }

            String nuevoNombre = mv.txt1.getText().trim();
            if (nuevoNombre.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El campo no puede estar vacío.");
                return;
            }

            int id = (int) mv.marcasModelo.getValueAt(fila, 0);
            String nombreActual = (String) mv.marcasModelo.getValueAt(fila, 1);

            // Solo verifica duplicados si el usuario cambió el nombre
            if (!nuevoNombre.equalsIgnoreCase(nombreActual) && mm.existeMarca(nuevoNombre)) {
                JOptionPane.showMessageDialog(null, "El nombre introducido ya pertenece a otra marca.");
                return;
            }

            Marca marca = new Marca(id, nuevoNombre);
            if (mm.update(marca) > 0) {
                JOptionPane.showMessageDialog(null, "Marca actualizada con éxito.");
            }
            mv.actualizarVista(mm.read());
        });

        // Eliminar
        mv.btn_d.addActionListener(e -> {
            int fila = mv.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Seleccione un registro de la tabla primero.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar esta marca?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                int id = (int) mv.marcasModelo.getValueAt(fila, 0);
                if (mm.delete(id) > 0) {
                    JOptionPane.showMessageDialog(null, "Marca eliminada correctamente.");
                }
                mv.actualizarVista(mm.read());
                mv.txt1.setText("");
            }
        });

        // Evento tabla
        mv.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int fila = mv.tabla.getSelectedRow();
                if (fila != -1) {
                    mv.txt1.setText((String) mv.marcasModelo.getValueAt(fila, 1));
                }
            }
        });
    }
}