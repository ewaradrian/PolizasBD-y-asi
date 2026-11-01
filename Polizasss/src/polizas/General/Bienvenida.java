package Polizas.General;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;

// Clase que representa la ventana principal de la aplicación
public class Bienvenida extends JFrame {
    // Declaración de componentes del menú superior
    protected JMenuBar mb;
    protected JMenu mA, mBD;
    protected JMenuItem home;
    protected JMenuItem mila, mib_o, mensaje, marcas, personas;
    
    // CardLayout permite cambiar entre diferentes paneles (pantallas) en el mismo espacio
    protected CardLayout cl; 
    protected JPanel pi, ps, pd, pc; // Paneles: Izquierdo, Superior, Derecho, Central

    public Bienvenida () {
        // Configuración básica de la ventana
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // Cierra la app al salir
        this.setExtendedState (JFrame.MAXIMIZED_BOTH); // Maximiza la ventana al iniciar
        this.setBackground (Color.yellow);
        
        this.menu(); // Construye la barra de menús

        // Configuración del Panel izquierdo
        pi = new JPanel();
        pi.setBackground(Color.white);
        pi.add(new JLabel("Panel izquierdo"));
        pi.setPreferredSize (new Dimension (200, getHeight()));

        // Configuración del Panel Superior (Título de la aplicación)
        ps = new JPanel();
        ps.setBackground (new Color (20, 110, 55)); // Verde oscuro
        JLabel titulo = new JLabel("Sistema de Gestión...");
        titulo.setForeground(Color.white);
        Font fuente = new Font("Arial", Font.BOLD, 18);
        titulo.setFont(fuente);
        ps.add(titulo);

        // Configuración del Panel Central usando CardLayout
        cl = new CardLayout();
        pc = new JPanel(cl); // Se le asigna el layout de cartas
        // Se añaden "cartas" (paneles) al panel central con una etiqueta para identificarlos
        pc.add(new JLabel("Welcome to Java ..."), "home");
        pc.add(new Mensaje(), "mensaje");

        // Configuración del Panel derecho
        pd = new JPanel();
        pd.setBackground (Color.white);
        pd.add(new JLabel("Panel derecho "));
        pd.setPreferredSize(new Dimension (200, getHeight()));

        // Layout principal de la ventana (BorderLayout organiza en Norte, Sur, Este, Oeste y Centro)
        this.setLayout(new BorderLayout());
        this.add(pi, BorderLayout.WEST);
        this.add(pd, BorderLayout.EAST);
        this.add(ps, BorderLayout.NORTH);
        this.add(pc, BorderLayout.CENTER);
        
        this.setVisible(true); // Muestra la ventana
    }

    // Método para crear y agregar los elementos del menú
    void menu() {
        this.mb = new JMenuBar();
        this.setJMenuBar (mb);

        // Menú Archivo
        this.mA = new JMenu("Archivo");
        home = new JMenuItem("Home");
        this.mA.add(home);
        this.mensaje = new JMenuItem("Mensaje");
        this.mA.add(mensaje);

        // Menú Base de Datos
        this.mBD = new JMenu("Base de Datos");
        marcas = new JMenuItem("Marcas");
        mBD.add(marcas);

        // Agregar menús a la barra principal
        this.mb.add(mA);
        this.mb.add(mBD);
    }
}