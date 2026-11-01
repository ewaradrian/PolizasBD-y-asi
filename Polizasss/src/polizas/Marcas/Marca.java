package polizas.Marcas;
// Clase Modelo o POJO (Plain Old Java Object) que representa la entidad "Marca"
public class Marca {
    protected int id;           // Coincide con idMarca de la BD
    protected String nombre;    // Coincide con nombreMarca de la BD

    // Método que devuelve el nombre de la marca cuando se intenta imprimir el objeto 
    // (útil para que el JComboBox muestre el nombre y no la referencia en memoria)
    @Override
    public String toString(){
        return this.nombre;
    }

    // Constructor para inicializar el objeto
    public Marca (int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y Setters para acceder y modificar las propiedades privadas/protegidas
    public Integer getId() {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre (String nombre) {
        this.nombre = nombre;
    }
}