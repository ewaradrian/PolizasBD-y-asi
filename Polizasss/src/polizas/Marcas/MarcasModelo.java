package polizas.Marcas;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import polizas.General.Conexion;
import polizas.General.DAO;

public class MarcasModelo extends Conexion implements DAO {
    
    public MarcasModelo() {
        super();
    }

    @Override
    public List read() {
        List<Marca> lista = new ArrayList<>();
        try {
            this.conectar();
            ps = cnx.prepareStatement("SELECT * FROM marcas");
            rs = ps.executeQuery();
            lista.clear();
            while (rs.next()) {
                lista.add(new Marca(rs.getInt("id"), rs.getString("nombre")));
            }
        } catch (SQLException ex) {
            System.out.println("Error:" + ex.getMessage());
        } finally {
            this.close(); // Usando el método close() de Conexion
        }
        return lista;
    }

    @Override
    public int create(Object obj) {
        try {
            this.conectar();
            Marca marca = (Marca) obj;
            ps = cnx.prepareStatement("INSERT INTO marcas (nombre) VALUES (?)");
            ps.setString(1, marca.getNombre());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error:" + ex.getMessage());
        } finally {
            this.close();
        }
        return 0;
    }

    // Nuevo método para evitar duplicados
    public boolean existeMarca(String nombre) {
        boolean existe = false;
        try {
            this.conectar();
            ps = cnx.prepareStatement("SELECT COUNT(*) FROM marcas WHERE nombre = ?");
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                existe = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error:" + ex.getMessage());
        } finally {
            this.close();
        }
        return existe;
    }

    // Implementación de Update
    @Override
    public int update(Object obj) {
        try {
            this.conectar();
            Marca marca = (Marca) obj;
            ps = cnx.prepareStatement("UPDATE marcas SET nombre = ? WHERE id = ?");
            ps.setString(1, marca.getNombre());
            ps.setInt(2, marca.getId());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error:" + ex.getMessage());
        } finally {
            this.close();
        }
        return 0;
    }

    // Implementación de Delete
    @Override
    public int delete(Object obj) {
        try {
            this.conectar();
            int id = (int) obj; // Recibe el ID
            ps = cnx.prepareStatement("DELETE FROM marcas WHERE id = ?");
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error:" + ex.getMessage());
        } finally {
            this.close();
        }
        return 0;
    }
}