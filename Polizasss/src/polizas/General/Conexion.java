package polizas.General;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Clase encargada de gestionar la conexión a la base de datos MySQL
public class Conexion {
    public Connection cnx;           // Objeto para la conexión
    public PreparedStatement ps;     // Objeto para ejecutar consultas SQL preparadas (seguras)
    public ResultSet rs;             // Objeto para almacenar los resultados de un SELECT

    // Método para establecer la conexión con la base de datos "polizas"
    public void conectar() {
        try {
            // URL de conexión, usuario "root", contraseña ""
            cnx = DriverManager.getConnection("jdbc:mysql://localhost/polizas", "root", "");
        } catch (SQLException e) {
            System.out.println("Error de conexión");
        }
    }

    // Método para cerrar la conexión y liberar recursos
    public void close() {
        try {
            cnx.close();
        } catch (SQLException ex) {
            System.out.println("Error:" + ex.getMessage());
        }
    }
}