package polizas.Marcas;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

// TableModel: Es el "cerebro" detrás de la tabla visual (JTable). Define cómo se muestran los datos de la lista de Marcas en filas y columnas.
public class MarcasTM extends AbstractTableModel{
    protected List<Marca> marcaslista; // La lista de datos reales
    protected String[] encabezados = {"id", "nombre"}; // Nombre de las columnas

    public MarcasTM(){
        marcaslista = new ArrayList<>(); // Inicializa la lista vacía
    }

    // Devuelve el nombre de la cabecera correspondiente
    @Override
    public String getColumnName (int cantidad) {
        return encabezados [cantidad];
    }

    // Permite cambiar los nombres de las columnas
    public void setColumnNames (String[] nuevosNombres) {
        if (nuevosNombres.length == encabezados.length) {
            this.encabezados = nuevosNombres;
            fireTableStructureChanged(); // Notifica a la vista que la estructura cambió para que se redibuje
        }else{
            throw new IllegalArgumentException("Valide los campos");
        }
    }

    // Borra los datos internos de la tabla
    public void vaciarlista() {
        marcaslista.clear();
    }

    // Obligatorio: Le dice al JTable cuántas filas debe dibujar
    @Override
    public int getRowCount() {
        return marcaslista.size();
    }

    // Obligatorio: Le dice al JTable cuántas columnas tiene
    @Override
    public int getColumnCount() {
        return encabezados.length;
    }

    // Obligatorio: El JTable llama a este método celda por celda para saber qué dibujar
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex != -1) {
            Marca m = marcaslista.get(rowIndex); // Obtiene el objeto de esa fila
            switch (columnIndex) {
                case 0:
                    return m.getId(); // Si es columna 0, muestra el ID
                case 1:
                    return m.getNombre(); // Si es columna 1, muestra el Nombre
                default:
                    return null;
            }
        }
        return null;
    }

    // Métodos utilitarios para manipular la lista interna y refrescar la tabla visual
    public void addMarca (Marca m) {
        marcaslista.add(m);
        fireTableRowsInserted (marcaslista.size ()-1, marcaslista.size ()-1);
    }

    public void updateMarca (int fila, Marca nueva) {
        marcaslista.set(fila, nueva);
        fireTableRowsUpdated (fila, fila);
    }

    public Marca getMarca (int fila) {
        return marcaslista.get(fila);
    }

    public void removeMarca (int fila) {
        marcaslista.remove(fila);
        fireTableRowsDeleted (fila, fila);
    }

    // Carga los datos provenientes de la Base de Datos al modelo
    void llenar (List<Marca> lista) {
        this.marcaslista = lista;
    }
}