package sv.edu.ues.ape115.layouts.ui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 * Requerimiento R05 — Dashboard Integrado: todos los layouts.
 *
 * Estructura requerida (TODOS los layouts del laboratorio en una sola ventana):
 *
 * JFrame BorderLayout(8,8) ├── NORTH → JPanel FlowLayout(LEFT) título + 2
 * tarjetas de estadística │ Borde: MatteBorder(0,0,2,0) azul ← RN-R05.1 │ Las
 * tarjetas tienen LineBorder ← RN-R05.1 ├── WEST → JPanel BoxLayout(Y_AXIS) 4
 * botones de módulo ancho 170px │ Borde: CompoundBorder TitledBorder("Módulos")
 * ← RN-R05.1 ├── CENTER → JSplitPane(resizeWeight=0.70) ← RN-R05.4 │ ├──
 * Izquierda: JTabbedPane 3 pestañas ← RN-R05.2 │ │ ├── "Resumen" → JPanel
 * GridLayout(2,3) 6 tarjetas EtchedBorder ← RN-R05.3 │ │ ├── "Productos" →
 * JTable en JScrollPane │ │ └── "Actividad" → JTextArea en JScrollPane │ └──
 * Derecha: JPanel GridBagLayout formulario rápido 3 campos + botón │ Borde:
 * CompoundBorder TitledBorder ← RN-R05.5 └── SOUTH → JPanel FlowLayout(RIGHT)
 * estado + 2 botones Borde: MatteBorder(1,0,0,0) ← RN-R05.1
 *
 * Reglas de negocio: RN-R05.1 Cada región del BorderLayout tiene un borde
 * distinto. RN-R05.2 JTabbedPane con 3 pestañas con contenido real (no vacías).
 * RN-R05.3 Pestaña Resumen: GridLayout(2,3) con 6 tarjetas + EtchedBorder.
 * RN-R05.4 JSplitPane: setResizeWeight(0.70). RN-R05.5 Todos los paneles de
 * sección usan CompoundBorder. setMinimumSize(900, 550) obligatorio.
 *
 * @author Genesis
 */
public class DashboardIntegrado extends JFrame {

    public DashboardIntegrado() {
        super("R05 — Dashboard Integrado — Todos los Layouts");
        
        construirUI();
        
        setSize(1100, 680);
        
        setMinimumSize(new Dimension(900, 550));
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setLocationRelativeTo(null);
        
        // TODO: construirUI()
        // TODO: setSize(1100, 680)
        // TODO: setMinimumSize(new Dimension(900, 550))   ← obligatorio, lo verifica test T06.5
        // TODO: setDefaultCloseOperation(EXIT_ON_CLOSE)
        // TODO: setLocationRelativeTo(null)
    }

    private void construirUI() {
        
        JPanel root = new JPanel(new BorderLayout(8, 8));
        
        root.setBorder(new EmptyBorder(8, 10, 8, 10));
        
        setContentPane(root);
        
        root.add(crearNorth(),  BorderLayout.NORTH);
        
        root.add(crearWest(),   BorderLayout.WEST);
        
        root.add(crearCentro(), BorderLayout.CENTER);
        
        root.add(crearSouth(),  BorderLayout.SOUTH);
        
        // TODO: JPanel root = new JPanel(new BorderLayout(8, 8))
        // TODO: root.setBorder(EmptyBorder(8,10,8,10))
        // TODO: setContentPane(root)
        // TODO: root.add(crearNorth(),  BorderLayout.NORTH)
        // TODO: root.add(crearWest(),   BorderLayout.WEST)
        // TODO: root.add(crearCentro(), BorderLayout.CENTER)
        // TODO: root.add(crearSouth(),  BorderLayout.SOUTH)
    }

    private JPanel crearNorth() {
        
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 8));
        
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLUE));
        
        JLabel titulo = new JLabel("Dashboard");
                        
        panel.add(titulo);
        
        panel.add(Box.createHorizontalStrut(20));
        
        panel.add(tarjetaStat("Total Productos", "8",   Color.BLUE));
        
        panel.add(tarjetaStat("Total Clientes",  "142", Color.GREEN));

        // TODO: FlowLayout(LEFT, 16, 8)
        // TODO: MatteBorder(0, 0, 2, 0, azul)   ← RN-R05.1
        // TODO: JLabel título
        // TODO: Box.createHorizontalStrut(20)
        // TODO: tarjetaStat("Total Productos", "8",  azul)
        // TODO: tarjetaStat("Total Clientes",  "142", verde)
        // TODO: return panel
        return panel;
    }

    /**
     * Tarjeta pequeña con valor estadístico y LineBorder de color.
     */
    private JPanel tarjetaStat(String etiqueta, String valor, Color color) {
        
        JPanel tarjeta = new JPanel(new BorderLayout(4, 0));
        
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color, 2), new EmptyBorder(6, 14, 6, 14)));
        
        JLabel lblValor = new JLabel(valor, SwingConstants.CENTER);
        
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 22));
        
        lblValor.setForeground(color);
        
        JLabel lblEtiqueta = new JLabel(etiqueta, SwingConstants.CENTER);
        
        lblEtiqueta.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        
        lblEtiqueta.setForeground(Color.GRAY);
        
        tarjeta.add(lblValor,    BorderLayout.CENTER);
        
        tarjeta.add(lblEtiqueta, BorderLayout.SOUTH);

        // TODO: new JPanel(new BorderLayout(4, 0))
        // TODO: CompoundBorder: LineBorder(color, 2) + EmptyBorder(6,14,6,14)
        // TODO: JLabel valor  → fuente Segoe UI Bold 22, color dado
        // TODO: JLabel etiqueta → fuente Segoe UI Plain 11, gris
        // TODO: return tarjeta
        return tarjeta;
    }

    private JPanel crearWest() {
        
        JPanel panel = new JPanel();
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        panel.setBackground(new Color(245, 247, 252));
        
        panel.setPreferredSize(new Dimension(170, 0));
        
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
            "Modulos", TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 11), 
            new Color(25, 118, 210)), new EmptyBorder(8, 6, 8, 6)));
        
        for (String item : new String[]
        {"📦 Productos","👤 Clientes","📈 Reportes","⚙ Config"}) 
        {
            JButton btn = new JButton(item);
            
            btn.setAlignmentX(Component.LEFT_ALIGNMENT);
            
            btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
            
            btn.setHorizontalAlignment(SwingConstants.LEFT);
                        
            panel.add(btn);
            
            panel.add(Box.createVerticalStrut(4));
            
        }
        panel.add(Box.createVerticalGlue());
        
        // TODO: new JPanel(); setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS))
        //       ← BoxLayout requerido por test T06.4
        // TODO: setBackground(new Color(245, 247, 252))
        // TODO: setPreferredSize(new Dimension(170, 0))
        // TODO: CompoundBorder: TitledBorder("Módulos", EtchedBorder, azul) + EmptyBorder
        //       ← RN-R05.5
        // TODO: Para cada item {"📦 Productos","👤 Clientes","📈 Reportes","⚙ Config"}:
        //         JButton; setAlignmentX(LEFT); setMaximumSize(MAX, 36); etc.
        // TODO: panel.add(Box.createVerticalGlue())
        // TODO: return panel
        return panel;
    }

    private JSplitPane crearCentro() {
        
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, crearTabbedPane(), crearFormularioRapido());
        
        split.setDividerLocation(520);
        
        split.setResizeWeight(0.70);
        
        split.setOneTouchExpandable(true);
        
        // TODO: new JSplitPane(HORIZONTAL_SPLIT, crearTabbedPane(), crearFormularioRapido())
        // TODO: setDividerLocation(520)
        // TODO: setResizeWeight(0.70)   ← RN-R05.4, lo verifica test T06.2
        // TODO: setOneTouchExpandable(true)
        // TODO: return split
        return split;
    }

    private JTabbedPane crearTabbedPane() {
        
        JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);
        
        tabs.addTab("Resumen",   null, crearPestanaResumen(),   "Estadisticas rapidas");
        
        tabs.addTab("Productos", null, crearPestanaProductos(), "Tabla de productos");
        
        tabs.addTab("Actividad", null, crearPestanaActividad(), "Registro de actividad");
        
        
        // TODO: new JTabbedPane(JTabbedPane.TOP)
        // TODO: addTab("Resumen",   null, crearPestanaResumen(),   "Estadísticas rápidas")
        // TODO: addTab("Productos", null, crearPestanaProductos(), "Tabla de productos")
        // TODO: addTab("Actividad", null, crearPestanaActividad(), "Registro de actividad")
        //       ← 3 pestañas requeridas por test T06.3
        // TODO: return tabs
        return tabs;
    }

    private JPanel crearPestanaResumen() {
        
        JPanel panel = new JPanel(new GridLayout(2, 3, 8, 8));
        
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        String[][] datos = {
            {"$1,240", "Ventas Hoy"}, {"3", "Stock Bajo"},
            {"142", "Clientes"}, {"7", "Pedidos"},
            {"2", "Devoluciones"}, {"$18,420", "Ganancia Mes"}};
        
        for (String[] d : datos) {
            
            JPanel tarjeta = new JPanel(new BorderLayout());
           
            tarjeta.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
            
            JLabel lblValor = new JLabel(d[0], SwingConstants.CENTER);
            
            lblValor.setFont(new Font("Segoe UI", Font.BOLD, 16));
            
            lblValor.setForeground(new Color(25, 118, 210));
            
            JLabel lblEtq = new JLabel(d[1], SwingConstants.CENTER);
            
            lblEtq.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            
            tarjeta.add(lblValor, BorderLayout.CENTER);
            
            tarjeta.add(lblEtq,   BorderLayout.SOUTH);
            
            panel.add(tarjeta);
        }
        
        // TODO: GridLayout(2, 3, 8, 8)   ← RN-R05.3
        // TODO: EmptyBorder(10,10,10,10)
        // TODO: 6 tarjetas con EtchedBorder(LOWERED)  ← RN-R05.3
        //       Datos: "Ventas Hoy"/$1,240 | "Stock Bajo"/3 | "Clientes"/142
        //              "Pedidos"/7         | "Devoluciones"/2| "Ganancia Mes"/$18,420
        //       Cada tarjeta: BorderLayout; valor en CENTER (negrita azul); etiqueta en SOUTH
        // TODO: return panel
        return panel; // reemplazar
    }

    private JPanel crearPestanaProductos() {
        
        JPanel panel = new JPanel(new BorderLayout());
        
        panel.setBorder(new EmptyBorder(8, 8, 8, 8));
        
        String[] cols = {"Nombre", "Categoria", "Precio", "Stock"};
        
        Object[][] filas = {
            {"Laptop Dell",        "Electronica", "$899.99", 12},
            {"Mouse Inalambrico",  "Accesorios",  "$25.99",  40},
            {"Silla Ergonomica",   "Hogar",       "$320.00",  5},
            {"Auriculares BT",     "Electronica", "$59.99",  25}
        };
        
        JTable tabla = new JTable(filas, cols);
        
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        
        // TODO: BorderLayout + EmptyBorder
        // TODO: JTable con columnas: Nombre, Categoría, Precio, Stock
        //       4 filas de datos de ejemplo en JScrollPane
        // TODO: return panel
        return panel; // reemplazar
    }

    private JPanel crearPestanaActividad() {
        
        JPanel panel = new JPanel(new BorderLayout());
        
        panel.setBorder(new EmptyBorder(8, 8, 8, 8));
        
        JTextArea area = new JTextArea();
        
        area.setEditable(false);
        
        area.setText(
            "2026-02-10 — Producto 'Laptop Dell' agregado\n" +
            "2026-02-11 — Cliente 'Juanito Perez' registrado\n" +
            "2026-02-12 — Venta #01 completada por $899.99\n" +
            "2026-02-13 — Stock actualizado para 'Mouse Inaláambrico'\n" +
            "2026-02-14 — Reporte mensual generado\n"
        );
        
        panel.add(new JScrollPane(area), BorderLayout.CENTER);
        
        // TODO: BorderLayout + EmptyBorder
        // TODO: JTextArea no editable con 5 líneas de log de ejemplo
        //       en JScrollPane
        // TODO: return panel
        return panel; // reemplazar
    }

    private JPanel crearFormularioRapido() {
        
        JPanel panel = new JPanel(new GridBagLayout());
        
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
           "Agregar Producto Rapido", TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 11), new Color(25, 118, 210)), new EmptyBorder(8, 10, 8, 10)));
        
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 6, 5, 6);
        g.anchor = GridBagConstraints.WEST;

        // Fila 0: Nombre
        g.gridx = 0; 
        g.gridy = 0; 
        g.fill = GridBagConstraints.NONE; 
        g.weightx = 0;
        panel.add(new JLabel("Nombre:"), g);
        g.gridx = 1; g.fill = GridBagConstraints.HORIZONTAL; g.weightx = 1;
        panel.add(new JTextField(15), g);

        
        g.gridx = 0; 
        g.gridy = 1; 
        g.fill = GridBagConstraints.NONE; 
        g.weightx = 0;
        panel.add(new JLabel("Precio $:"), g);
        g.gridx = 1; g.fill = GridBagConstraints.HORIZONTAL; g.weightx = 1;
        panel.add(new JTextField(15), g);

        
        g.gridx = 0; 
        g.gridy = 2; 
        g.fill = GridBagConstraints.NONE; 
        g.weightx = 0;
        panel.add(new JLabel("Stock:"), g);
        g.gridx = 1; g.fill = GridBagConstraints.HORIZONTAL; g.weightx = 1;
        panel.add(new JTextField(15), g);

        
        g.gridx = 1; 
        g.gridy = 3; 
        g.fill = GridBagConstraints.NONE;
        g.anchor = GridBagConstraints.EAST; 
        g.weightx = 0;
        panel.add(boton("Agregar", new Color(46, 125, 50)), g);
        g.anchor = GridBagConstraints.WEST;

        JPanel relleno = new JPanel();
        relleno.setOpaque(false);
        g.gridx = 0; 
        g.gridy = 4; 
        g.gridwidth = 2;
        g.fill = GridBagConstraints.BOTH; 
        g.weightx = 1; 
        g.weighty = 1;
        panel.add(relleno, g);
        
        
        // TODO: new JPanel(new GridBagLayout())
        // TODO: CompoundBorder: TitledBorder("Agregar Producto Rápido") + EmptyBorder ← RN-R05.5
        // TODO: GridBagConstraints; 3 filas: Nombre, Precio $, Stock (etiqueta + JTextField)
        // TODO: Fila 3: JButton "Agregar" alineado a la derecha (anchor=EAST)
        // TODO: Fila 4: relleno vertical (fill=BOTH, weighty=1)
        // TODO: return panel
        return panel; // reemplazar
    }

    private JPanel crearSouth() {
        
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 6));
        
        panel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 210, 230)));
        
        JLabel lblEstado = new JLabel("Estado: Sistema activo");
        
        lblEstado.setForeground(Color.GRAY);
        
        panel.add(lblEstado);
        
        panel.add(Box.createHorizontalStrut(20));
        
        panel.add(boton("Exportar",   Color.gray));
        
        panel.add(boton("Actualizar", Color.BLUE));
        
        // TODO: FlowLayout(RIGHT, 10, 6)
        // TODO: MatteBorder(1, 0, 0, 0, ...)   ← RN-R05.1
        // TODO: JLabel "Estado: Sistema activo" (gris)
        // TODO: Box.createHorizontalStrut(20)
        // TODO: JButton "Exportar"   (gris)
        // TODO: JButton "Actualizar" (azul)
        // TODO: return panel
        return panel; // reemplazar
    }

    // ── Helper: botón estándar ────────────────────────────────────
    // NO modificar.
    private JButton boton(String label, Color color) {
        JButton btn = new JButton(label);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setBorder(BorderFactory.createEmptyBorder(6, 16, 6, 16));
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DashboardIntegrado().setVisible(true));
    }
}
