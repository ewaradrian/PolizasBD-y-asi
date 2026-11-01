package Polizas.General;
import polizas.Marcas.MarcasVista;
import polizas.Marcas.MarcasControl;
import javax.swing.JLabel;

// Clase que gestiona las interacciones (eventos) de la ventana principal
public class Controlador {
    protected Bienvenida vista;
    protected MarcasVista marcasVista;

    // Constructor que recibe la vista principal y activa los listeners de eventos
    public Controlador(Bienvenida vista) {
        this.vista = vista;
        this.eventos();
    }

    // Define qué pasa cuando el usuario interactúa con el menú
    void eventos() {
        // Muestra el panel "mensaje" al dar clic en Archivo -> Mensaje
        vista.mensaje.addActionListener(
            e -> vista.cl.show(vista.pc, "mensaje")
        );

        // Muestra el panel "home" al dar clic en Archivo -> Home
        vista.home.addActionListener (e -> {
            vista.pc.add(new JLabel("Panel Home"), "home");
            vista.cl.show(vista.pc, "home");
        });

        // Al dar clic en Base de Datos -> Marcas:
        vista.marcas.addActionListener(e -> {
            marcasVista = new MarcasVista(); // Instancia la vista de marcas
            new MarcasControl(marcasVista);  // Conecta la vista con su controlador específico
            vista.pc.add(marcasVista, "mk"); // Añade la vista al panel central con la etiqueta "mk"
            vista.cl.show(vista.pc, "mk");   // Muestra la vista "mk" usando el CardLayout
        });
    }
}