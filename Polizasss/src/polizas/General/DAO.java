package polizas.General;
import java.util.List;

// Interfaz Data Access Object (Patrón de diseño DAO)
// Define las operaciones estándar (CRUD: Create, Read, Update, Delete) 
// que cualquier modelo de base de datos deberá implementar obligatoriamente.
public interface DAO {
    int create (Object obj);      // Insertar nuevo registro
    List read();                  // Leer registros
    int update (Object lista);    // Actualizar registro
    int delete (Object obj);      // Borrar registro
}