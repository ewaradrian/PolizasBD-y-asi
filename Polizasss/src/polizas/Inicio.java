package Polizas;
import Polizas.General.Controlador;
import Polizas.General.Bienvenida;
import javax.swing.SwingUtilities;

// Clase principal que arranca la aplicación
public class Inicio {
    private Controlador controlador;
    private Bienvenida vista;

    // Constructor que inicializa el patrón MVC (Modelo-Vista-Controlador)
    public Inicio(){
        vista = new Bienvenida(); // Crea la ventana principal
        controlador = new Controlador (vista); // Pasa la vista al controlador para gestionar sus eventos
    }

    // Método main: Punto de entrada del programa
    public static void main(String[] args) {
        // Asegura que la interfaz gráfica se ejecute en el hilo despachador de eventos de Swing (hilo seguro)
        new Inicio();
    }
}