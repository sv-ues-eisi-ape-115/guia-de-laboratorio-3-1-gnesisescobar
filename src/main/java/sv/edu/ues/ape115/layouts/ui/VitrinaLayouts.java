package sv.edu.ues.ape115.layouts.ui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 * Requerimiento R01 — Vitrina de los 6 Layout Managers.
 *
 * Estructura requerida (leer guía sección 3, R01):
 *
 *  JFrame
 *  └── BorderLayout
 *      ├── NORTH  → FlowLayout(CENTER)  título + JLabel instrucción
 *      ├── CENTER → GridLayout(2, 3)    seis celdas, una por Layout Manager
 *      │    ├── Celda 1 BorderLayout  (TitledBorder azul)
 *      │    ├── Celda 2 FlowLayout    (TitledBorder verde)
 *      │    ├── Celda 3 GridLayout(3,3)(TitledBorder rojo)
 *      │    ├── Celda 4 GridBagLayout (TitledBorder violeta)
 *      │    ├── Celda 5 BoxLayout(Y)   (TitledBorder naranja)
 *      │    └── Celda 6 CardLayout     (TitledBorder verde-azulado)
 *      └── SOUTH  → FlowLayout(RIGHT)  botón Salir
 *
 * Reglas de negocio:
 *   RN-R01.1 Cada celda tiene TitledBorder con el nombre del layout.
 *   RN-R01.2 La celda 6 (CardLayout) tiene botones Anterior / Siguiente funcionales.
 *   RN-R01.3 La ventana es redimensionable; los layouts deben adaptarse.
 *   RN-R01.4 Cada celda usa un color diferente en su TitledBorder.
 *
 * @author Genesis 
 */
public class VitrinaLayouts extends JFrame {
    
    private final CardLayout cardDemo = new CardLayout();
    
    private final JPanel pnlCard = new JPanel(cardDemo);

    // TODO: declarar campo  private final CardLayout cardDemo = new CardLayout();
    // TODO: declarar campo  private final JPanel pnlCard = new JPanel(cardDemo);
    //
    // IMPORTANTE: el campo cardDemo DEBE ser de tipo CardLayout (lo verifica el test T02.3)

    public VitrinaLayouts() {
        super("R01 — Vitrina: Los 6 Layout Managers de Swing");
        
        JPanel root = new JPanel(new BorderLayout(8, 8));
        
        root.setBorder(new EmptyBorder(10, 12, 10, 12));
        
        setContentPane(root);
        
        root.add(crearNorth(),  BorderLayout.NORTH);
        
        root.add(crearGrilla(), BorderLayout.CENTER);
        
        root.add(crearSouth(),  BorderLayout.SOUTH);
        
        setSize(960, 640);
        
        setMinimumSize(new Dimension(740, 500));
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setLocationRelativeTo(null);
        // TODO: construir la UI (ver métodos de abajo)
        // TODO: setSize(960, 640)
        // TODO: setMinimumSize(new Dimension(740, 500))
        // TODO: setDefaultCloseOperation(EXIT_ON_CLOSE)
        // TODO: setLocationRelativeTo(null)
    }

    // ── NORTH ─────────────────────────────────────────────────────
    private JPanel crearNorth() {
        
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(25, 118, 210)));
        
        JLabel titulo = new JLabel("Vitrina de Layout Managers");
        
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        
        titulo.setForeground(new Color(25, 118, 210));
        
        JLabel sub = new JLabel("Redimensiona la ventana para ver cómo se adapta cada layout");
        
        panel.add(titulo);
        
        panel.add(sub);
        // TODO: FlowLayout(CENTER)
        // TODO: MatteBorder inferior de 2px azul
        // TODO: JLabel título con fuente Segoe UI Bold 16, color azul
        // TODO: JLabel subtítulo con instrucción para redimensionar
        // TODO: return panel
        return panel; // reemplazar
    }

    // ── CENTER: GridLayout(2,3) ────────────────────────────────────
    private JPanel crearGrilla() {
        
        JPanel grilla = new JPanel(new GridLayout(2, 3, 10, 10));
        
        grilla.add(celda1_BorderLayout());
        
        grilla.add(celda2_FlowLayout());
        
        grilla.add(celda3_GridLayout());
        
        grilla.add(celda4_GridBagLayout());
        
        grilla.add(celda5_BoxLayout());
        
        grilla.add(celda6_CardLayout());
        
        // TODO: new JPanel(new GridLayout(2, 3, 10, 10))
        // TODO: grilla.add(celda1_BorderLayout())
        // TODO: grilla.add(celda2_FlowLayout())
        // TODO: grilla.add(celda3_GridLayout())
        // TODO: grilla.add(celda4_GridBagLayout())
        // TODO: grilla.add(celda5_BoxLayout())
        // TODO: grilla.add(celda6_CardLayout())
        // TODO: return grilla
        return grilla; // reemplazar
    }

    // ── Celda 1: BorderLayout ─────────────────────────────────────
    private JPanel celda1_BorderLayout() {
        
        JPanel celda = new JPanel(new BorderLayout(2, 2));
        
        celda.setBorder(titledBorde("BorderLayout", new Color(25, 118, 210)));
        
        celda.add(new JButton("NORTH"),  BorderLayout.NORTH);
        
        celda.add(new JButton("SOUTH"),  BorderLayout.SOUTH);
        
        celda.add(new JButton("EAST"),   BorderLayout.EAST);
        
        celda.add(new JButton("WEST"),   BorderLayout.WEST);
        
        JLabel centro = new JLabel("CENTER", SwingConstants.CENTER);
        
        centro.setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        centro.setForeground(new Color(25, 118, 210));
        
        celda.add(centro, BorderLayout.CENTER);
        // TODO: new JPanel(new BorderLayout(2, 2))
        // TODO: TitledBorder con nombre "BorderLayout" y color azul
        // TODO: JButton "NORTH"  → BorderLayout.NORTH
        // TODO: JButton "SOUTH"  → BorderLayout.SOUTH
        // TODO: JButton "EAST"   → BorderLayout.EAST
        // TODO: JButton "WEST"   → BorderLayout.WEST
        // TODO: JLabel  "CENTER" → BorderLayout.CENTER (centrado, negrita, azul)
        // TODO: return celda
        return celda; // reemplazar
    }

    // ── Celda 2: FlowLayout ───────────────────────────────────────
    private JPanel celda2_FlowLayout() {
        
        JPanel celda = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 4));
        
        celda.setBorder(titledBorde("FlowLayout", new Color(46, 125, 50)));
        
        for (int i = 1; i <= 8; i++) {
            
            JButton btn = new JButton("B" + i);
            btn.setPreferredSize(new Dimension(50, 28));
            celda.add(btn);
        }
        // TODO: new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 4))
        // TODO: TitledBorder "FlowLayout" en verde
        // TODO: 8 JButton pequeños (preferredSize 50×28 cada uno)
        //       Los botones fluyen y se reorganizan al redimensionar
        // TODO: return celda
        return celda; // reemplazar
    }

    // ── Celda 3: GridLayout(3,3) ──────────────────────────────────
    private JPanel celda3_GridLayout() {
        
        JPanel celda = new JPanel(new GridLayout(3, 3, 3, 3));
        
        celda.setBorder(titledBorde("GridLayout", new Color(198, 40, 40)));
        
        for (String n : new String[]{"7","8","9","4","5","6","1","2","3"}) {
            celda.add(new JButton(n));
        }
        // TODO: new JPanel(new GridLayout(3, 3, 3, 3))
        // TODO: TitledBorder "GridLayout" en rojo
        // TODO: 9 JButton con las cifras "7","8","9","4","5","6","1","2","3"
        // TODO: return celda
        return celda; // reemplazar
    }

    // ── Celda 4: GridBagLayout ────────────────────────────────────
    private JPanel celda4_GridBagLayout() {
        
        JPanel celda = new JPanel(new GridBagLayout());
        
        celda.setBorder(titledBorde("GridBagLayout", new Color(106, 27, 154)));
        
        GridBagConstraints g = new GridBagConstraints();
        
        g.insets = new Insets(3, 4, 3, 4);
        
        g.anchor = GridBagConstraints.WEST;
        
        for (String etq : new String[]{"Nombre:", "Email:", "Ciudad:"}) 
        {
            g.gridx = 0; 
            g.fill = GridBagConstraints.NONE; 
            g.weightx = 0;
            
            celda.add(new JLabel(etq), g);
            
            g.gridx = 1; 
            g.fill = GridBagConstraints.HORIZONTAL; 
            g.weightx = 1;
            celda.add(new JTextField(10), g);
            g.gridy = (g.gridy + 1);
        }
        // TODO: new JPanel(new GridBagLayout())
        // TODO: TitledBorder "GridBagLayout" en violeta/morado
        // TODO: GridBagConstraints g; insets=Insets(3,4,3,4); anchor=WEST
        // TODO: Para cada etiqueta en {"Nombre:","Email:","Ciudad:"}:
        //         gridx=0, fill=NONE → add JLabel
        //         gridx=1, fill=HORIZONTAL, weightx=1 → add JTextField(10)
        // TODO: return celda
        return celda; // reemplazar
    }

    // ── Celda 5: BoxLayout(Y_AXIS) ────────────────────────────────
    private JPanel celda5_BoxLayout() {
        
        JPanel panel = new JPanel();
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        panel.setBorder(titledBorde("BoxLayout Y_AXIS", new Color(230, 81, 0)));
        
        for (String item : new String[]{"Inicio","Productos","Clientes","Reportes","Configuración"}) {
            
            JLabel lbl = new JLabel("▸  " + item);
            lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(lbl);
            panel.add(Box.createVerticalStrut(5));
        }
        
        panel.add(Box.createVerticalGlue());
        // TODO: new JPanel(); setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS))
        // TODO: TitledBorder "BoxLayout Y_AXIS" en naranja
        // TODO: Para cada item en {"Inicio","Productos","Clientes","Reportes","Configuración"}:
        //         JLabel "▸  " + item; setAlignmentX(LEFT)
        //         panel.add(lbl); panel.add(Box.createVerticalStrut(5))
        // TODO: panel.add(Box.createVerticalGlue())
        // TODO: return celda
        return panel; // reemplazar
    }

    // ── Celda 6: CardLayout ───────────────────────────────────────
    private JPanel celda6_CardLayout() {
        
        JPanel celda = new JPanel(new BorderLayout(0, 4));
        
        celda.setBorder(titledBorde("CardLayout", new Color(0, 121, 107)));

        JPanel cardA = new JPanel(new GridBagLayout());
        
        cardA.setBackground(new Color(200, 230, 201));
        
        cardA.add(new JLabel("Tarjeta A"));
        
        JPanel cardB = new JPanel(new GridBagLayout());
        
        cardB.setBackground(new Color(187, 222, 251));
        
        cardB.add(new JLabel("Tarjeta B"));
        
        pnlCard.add(cardA, "A");
        
        pnlCard.add(cardB, "B");
        
        celda.add(pnlCard, BorderLayout.CENTER);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 0));
        
        JButton btnAnterior = new JButton("◀ Anterior");
        
        JButton btnSiguiente = new JButton("Siguiente ▶");
        
        btnAnterior.addActionListener(e -> cardDemo.previous(pnlCard));
        
        btnSiguiente.addActionListener(e -> cardDemo.next(pnlCard));
        
        botones.add(btnAnterior);
        
        botones.add(btnSiguiente);
        
        celda.add(botones, BorderLayout.SOUTH);
        
        // TODO: new JPanel(new BorderLayout(0, 4))
        // TODO: TitledBorder "CardLayout" en verde-azulado (ej: Color(0,121,107))
        //
        // TODO: Crear JPanel cardA con fondo verde claro y JLabel "Tarjeta A"
        // TODO: Crear JPanel cardB con fondo azul claro y JLabel "Tarjeta B"
        // TODO: pnlCard.add(cardA, "A"); pnlCard.add(cardB, "B")
        // TODO: celda.add(pnlCard, BorderLayout.CENTER)
        //
        // TODO: JPanel botones con FlowLayout(CENTER, 4, 0)
        //         JButton "◀ Anterior" → cardDemo.previous(pnlCard)
        //         JButton "Siguiente ▶" → cardDemo.next(pnlCard)
        // TODO: celda.add(botones, BorderLayout.SOUTH)
        // TODO: return celda
        return celda; // reemplazar
    }

    // ── SOUTH ──────────────────────────────────────────────────────
    private JPanel crearSouth() {
        
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 6));
        
        panel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 210, 230)));
        
        JButton btnSalir = new JButton("Salir");
        
        btnSalir.addActionListener(e -> dispose());
        
        panel.add(btnSalir);
        // TODO: FlowLayout(FlowLayout.RIGHT, 8, 6)
        // TODO: MatteBorder(1, 0, 0, 0, Color(200,210,230))
        // TODO: JButton "Salir" → dispose()
        // TODO: return panel
        return panel; // reemplazar
    }

    // ── Helper: TitledBorder envuelto en CompoundBorder ───────────
    // TODO: Implementar este método
    //   return BorderFactory.createCompoundBorder(
    //       BorderFactory.createTitledBorder(
    //           BorderFactory.createLineBorder(color, 2),
    //           titulo, TitledBorder.LEFT, TitledBorder.TOP,
    //           new Font("Segoe UI", Font.BOLD, 11), color),
    //       BorderFactory.createEmptyBorder(4, 6, 4, 6));
    private Border titledBorde(String titulo, Color color) {
        
        return BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(BorderFactory.createLineBorder(color, 2),
            titulo, TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 11), color),
            BorderFactory.createEmptyBorder(4, 6, 4, 6));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VitrinaLayouts().setVisible(true));
    }
}
