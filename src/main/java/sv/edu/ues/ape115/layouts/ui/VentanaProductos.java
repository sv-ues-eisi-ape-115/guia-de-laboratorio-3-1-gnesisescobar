package sv.edu.ues.ape115.layouts.ui;

import sv.edu.ues.ape115.layouts.model.Producto;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejemplo paso a paso — G0301 Layout Managers y Bordes.
 *
 * Sigue los pasos de la guía (sección 2) para completar cada método.
 *
 * Layouts que debes usar:
 *   - BorderLayout  → construirUI() (estructura exterior del JFrame)
 *   - FlowLayout    → crearNorth()  y  crearSouth()
 *   - BoxLayout     → crearWest()   (panel de categorías)
 *   - GridBagLayout → crearFormulario() (campos del formulario)
 *   - GridLayout    → crearDetalle()    (pestaña Datos del JTabbedPane)
 *   - JSplitPane    → crearCentro()
 *   - JTabbedPane   → crearDetalle()
 *
 * Bordes que debes usar:
 *   - LineBorder    → crearNorth()
 *   - CompoundBorder (TitledBorder + EmptyBorder) → crearWest(), crearFormulario(), crearDetalle()
 *   - MatteBorder   → crearSouth()
 *
 * @author Genesis
 */
public class VentanaProductos extends JFrame {

    // ── Datos de la aplicación ────────────────────────────────────
    private final List<Producto>             productos = new ArrayList<>();
    private final DefaultListModel<Producto> modelo    = new DefaultListModel<>();

    // ── Componentes del formulario (izquierda del JSplitPane) ─────
    private JList<Producto>  lstProductos;
    private JTextField       txtNombre;
    private JTextField       txtPrecio;
    private JTextField       txtStock;
    private JComboBox<String> cboCat;

    // ── Componentes del detalle (derecha del JSplitPane) ──────────
    private JLabel   lblDNombre;
    private JLabel   lblDPrecio;
    private JLabel   lblDStock;
    private JLabel   lblDCat;
    private JTextArea txDesc;

    // ─────────────────────────────────────────────────────────────
    public VentanaProductos() {
        super("G0301 — Layout Managers y Bordes en Java Swing");
        cargarDatos();
        
        construirUI();
        
        setSize(1000, 660);
        
        setMinimumSize(new Dimension(800, 540));
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setLocationRelativeTo(null);
    }

    // ─────────────────────────────────────────────────────────────
    // PASO 3 (Guía §2.4): Estructura principal BorderLayout
    // ─────────────────────────────────────────────────────────────
    private void construirUI() {
        
         JPanel root = new JPanel(new BorderLayout(6, 6));
         
        root.setBorder(new EmptyBorder(10, 12, 10, 12));
        
        setContentPane(root);
        
        root.add(crearNorth(),  BorderLayout.NORTH);
        
        root.add(crearWest(),   BorderLayout.WEST);
        
        root.add(crearCentro(), BorderLayout.CENTER);
        
        root.add(crearSouth(),  BorderLayout.SOUTH);
        
        // TODO: Crear JPanel root con BorderLayout(6,6)
        // TODO: Aplicar EmptyBorder(10,12,10,12) como padding exterior
        // TODO: setContentPane(root)
        // TODO: Agregar crearNorth()     → BorderLayout.NORTH
        // TODO: Agregar crearWest()      → BorderLayout.WEST
        // TODO: Agregar crearCentro()    → BorderLayout.CENTER
        // TODO: Agregar crearSouth()     → BorderLayout.SOUTH
    }

    // ─────────────────────────────────────────────────────────────
    // PASO 4 (Guía §2.5): Panel NORTH — FlowLayout + LineBorder
    // ─────────────────────────────────────────────────────────────
    private JPanel crearNorth() {
        
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 6));
        
        panel.setBorder(BorderFactory.createLineBorder(new Color(25, 118, 210), 2));
        
        JLabel lbl = new JLabel("Gestión de Productos");
        
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 17));
        
        lbl.setForeground(new Color(25, 118, 210));
        
        JTextField txtBuscar = new JTextField(18);
        
        txtBuscar.addActionListener(e -> filtrar(txtBuscar.getText()));
        
        panel.add(lbl);
        
        panel.add(new JSeparator(SwingConstants.VERTICAL));
        
        panel.add(new JLabel("Buscar:"));
        
        panel.add(txtBuscar);
        
        // TODO: new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 6))
        // TODO: setBorder(LineBorder azul institucional, grosor 2)
        // TODO: Crear JLabel con el título de la aplicación
        //       fuente Segoe UI Bold 17, color azul institucional
        // TODO: Crear JTextField txtBuscar columnas=18
        // TODO: addActionListener en txtBuscar → llamar filtrar(texto)
        // TODO: panel.add(lbl), add(separador), add("Buscar:"), add(txtBuscar)
        // TODO: return panel
        return panel; // reemplazar
    }

    // ─────────────────────────────────────────────────────────────
    // PASO 5 (Guía §2.6): Panel WEST — BoxLayout(Y_AXIS) + CompoundBorder
    // ─────────────────────────────────────────────────────────────
    private JPanel crearWest() {
        
        JPanel panel = new JPanel();
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
            "Categorías", TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 11), 
            new Color(25, 118, 210)), new EmptyBorder(6, 8, 6, 8)));
        
        panel.setPreferredSize(new Dimension(165, 0));
        
        for (String cat : new String[]{"Todas", "Electrónica", "Hogar", "Accesorios"}) 
        {
            JButton btn = new JButton(cat);
            
            btn.setAlignmentX(Component.LEFT_ALIGNMENT);
            
            btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));
            
            btn.addActionListener(e -> filtrarCategoria(cat));
            
            panel.add(btn);
            
            panel.add(Box.createVerticalStrut(4));
        }
        panel.add(Box.createVerticalGlue());

        // TODO: new JPanel() + setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS))
        // TODO: setBorder CompoundBorder:
        //         exterior = TitledBorder("Categorías", EtchedBorder.LOWERED, azul)
        //         interior = EmptyBorder(6, 8, 6, 8)
        // TODO: setPreferredSize(new Dimension(165, 0))
        // TODO: for cada categoría en {"Todas","Electrónica","Hogar","Accesorios"}:
        //         JButton btn; setAlignmentX(LEFT); setMaximumSize(MAX, 34)
        //         addActionListener → filtrarCategoria(cat)
        //         panel.add(btn); panel.add(Box.createVerticalStrut(4))
        // TODO: panel.add(Box.createVerticalGlue())
        // TODO: return panel
        return panel; // reemplazar
    }

    // ─────────────────────────────────────────────────────────────
    // PASO 6 (Guía §2.7): Centro — JSplitPane
    // ─────────────────────────────────────────────────────────────
    private JSplitPane crearCentro() {
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, crearFormulario(), crearDetalle());
        
        splitPane.setDividerLocation(340);
        
        splitPane.setResizeWeight(0.40);
        
        splitPane.setOneTouchExpandable(true);
        
        // TODO: new JSplitPane(HORIZONTAL_SPLIT, crearFormulario(), crearDetalle())
        // TODO: setDividerLocation(340)
        // TODO: setResizeWeight(0.40)
        // TODO: setOneTouchExpandable(true)
        // TODO: return splitPane
        return splitPane; // reemplazar
    }

    // ─────────────────────────────────────────────────────────────
    // PASO 6 cont. — Formulario con GridBagLayout
    // ─────────────────────────────────────────────────────────────
    private JPanel crearFormulario() {
        
        JPanel panel = new JPanel(new GridBagLayout());
        
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
            "Datos del Producto", TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 11), 
            new Color(25, 118, 210)), new EmptyBorder(8, 10, 8, 10)));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 6, 5, 6);
        g.anchor = GridBagConstraints.WEST;

        g.gridx = 0; 
        g.gridy = 0; 
        g.fill = GridBagConstraints.NONE; 
        g.weightx = 0;
        panel.add(new JLabel("Nombre *:"), g);
        txtNombre = new JTextField();
        
        g.gridx = 1; 
        g.gridwidth = 3; 
        g.fill = GridBagConstraints.HORIZONTAL; 
        g.weightx = 1;
        panel.add(txtNombre, g);
        
        g.gridwidth = 1; 
        g.weightx = 0;


        g.gridx = 0; 
        g.gridy = 1; 
        g.fill = GridBagConstraints.NONE; 
        g.weightx = 0;
        panel.add(new JLabel("Precio $*:"), g);
        txtPrecio = new JTextField(7);
        
        g.gridx = 1; 
        g.fill = GridBagConstraints.HORIZONTAL; 
        g.weightx = 0.5;
        panel.add(txtPrecio, g);
        
        g.gridx = 2; 
        g.fill = GridBagConstraints.NONE; 
        g.weightx = 0;
        panel.add(new JLabel("Stock *:"), g);
        txtStock = new JTextField(5);
        
        g.gridx = 3; 
        g.fill = GridBagConstraints.HORIZONTAL; 
        g.weightx = 0.5;
        panel.add(txtStock, g);
        g.weightx = 0;


        g.gridx = 0; 
        g.gridy = 2; 
        g.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Categoría *:"), g);
        cboCat = new JComboBox<>(new String[]{"Electrónica", "Hogar", "Accesorios"});
        
        g.gridx = 1; 
        g.gridwidth = 3; 
        g.fill = GridBagConstraints.HORIZONTAL; 
        g.weightx = 1;
        panel.add(cboCat, g);
        
        g.gridwidth = 1; 
        g.weightx = 0;


        lstProductos = new JList<>(modelo);
        
        lstProductos.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarDetalle(lstProductos.getSelectedValue());
            }
        });
        
        JScrollPane scroll = new JScrollPane(lstProductos);
        g.gridx = 0; 
        g.gridy = 3; 
        g.gridwidth = 4;
        g.fill = GridBagConstraints.BOTH; 
        g.weightx = 1; 
        g.weighty = 1;
        panel.add(scroll, g);
        
        // TODO: new JPanel(new GridBagLayout())
        // TODO: setBorder CompoundBorder:
        //         TitledBorder("Datos del Producto", EtchedBorder, azul)
        //         + EmptyBorder(8, 10, 8, 10)

        // TODO: GridBagConstraints g; insets=Insets(5,6,5,6); anchor=WEST

        // TODO — Fila 0: etiqueta "Nombre *:" + txtNombre(span 3 cols)
        //   g.gridx=0; g.gridy=0; fill=NONE; weightx=0  → add etiqueta
        //   g.gridx=1; gridwidth=3; fill=HORIZONTAL; weightx=1 → add txtNombre
        //   resetear: gridwidth=1; weightx=0

        // TODO — Fila 1: "Precio $*:" (col 0) + txtPrecio (col 1) +
        //                "Stock *:" (col 2) + txtStock (col 3)

        // TODO — Fila 2: "Categoría *:" + cboCat(span 3)

        // TODO — Fila 3: lstProductos en JScrollPane (span 4 cols, fill=BOTH, weighty=1)
        //   lstProductos = new JList<>(modelo)
        //   addListSelectionListener → cargarDetalle(selectedValue) si !isAdjusting

        // TODO: return panel
        return panel; // reemplazar
    }

    // ─────────────────────────────────────────────────────────────
    // PASO 7 (Guía §2.8): Detalle — JTabbedPane
    // ─────────────────────────────────────────────────────────────
    private JTabbedPane crearDetalle() {
        
        JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);

        JPanel pDatos = new JPanel(new GridLayout(4, 2, 8, 8));
        
        pDatos.setBorder(new EmptyBorder(12, 12, 12, 12));
        
        pDatos.add(new JLabel("Nombre:"));
        
        lblDNombre = new JLabel("—");
        
        pDatos.add(lblDNombre);
        
        pDatos.add(new JLabel("Precio:"));
        
        lblDPrecio = new JLabel("—");
        
        pDatos.add(lblDPrecio);
        
        pDatos.add(new JLabel("Stock:"));
        
        lblDStock = new JLabel("—");
        
        pDatos.add(lblDStock);
        
        pDatos.add(new JLabel("Categoría:"));
        
        lblDCat = new JLabel("—");
        
        pDatos.add(lblDCat);
        
        tabs.addTab("Datos", null, pDatos, "Datos generales");

        txDesc = new JTextArea();
        
        txDesc.setLineWrap(true);
        
        txDesc.setWrapStyleWord(true);
        
        txDesc.setEditable(false);
        
        tabs.addTab("Descripción", null, new JScrollPane(txDesc), "Descripción completa");

        tabs.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
            "Detalle del Producto", TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 11), 
            new Color(25, 118, 210)), new EmptyBorder(4, 8, 4, 8)));
       
        // TODO: new JTabbedPane(JTabbedPane.TOP)

        // TODO — Pestaña 1 "Datos":
        //   JPanel pDatos = new JPanel(new GridLayout(4, 2, 8, 8))
        //   pDatos.setBorder(EmptyBorder(12,12,12,12))
        //   Agregar 4 pares etiqueta/JLabel para: Nombre, Precio, Stock, Categoría
        //   tabs.addTab("Datos", null, pDatos, "Datos generales")

        // TODO — Pestaña 2 "Descripción":
        //   txDesc = new JTextArea(); setLineWrap(true); setWrapStyleWord(true); setEditable(false)
        //   tabs.addTab("Descripción", null, new JScrollPane(txDesc), "Descripción completa")

        // TODO — setBorder del JTabbedPane:
        //   CompoundBorder: TitledBorder("Detalle del Producto", EtchedBorder, azul)
        //                   + EmptyBorder(4, 8, 4, 8)

        // TODO: return tabs
        return tabs; // reemplazar
    }

    // ─────────────────────────────────────────────────────────────
    // PASO 8 (Guía §2.9): Panel SOUTH — FlowLayout(RIGHT) + MatteBorder
    // ─────────────────────────────────────────────────────────────
    private JPanel crearSouth() {
        
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 6));
        
        panel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 210, 230)));
        
        JButton btnLimpiar  = crearBoton("Limpiar",  new Color(100, 100, 100));
        
        JButton btnEliminar = crearBoton("Eliminar", new Color(198, 40, 40));
        
        JButton btnNuevo    = crearBoton("+ Nuevo",  new Color(25, 118, 210));
        
        JButton btnGuardar  = crearBoton("Guardar",  new Color(46, 125, 50));
        
        btnLimpiar.addActionListener(e -> limpiar());
        
        btnEliminar.addActionListener(e -> eliminar());
        
        btnNuevo.addActionListener(e -> limpiar());
        
        btnGuardar.addActionListener(e -> guardar());
        
        panel.add(btnLimpiar);
        
        panel.add(btnEliminar);
        
        panel.add(btnNuevo);
        
        panel.add(btnGuardar);
        
        // TODO: new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 6))
        // TODO: setBorder(MatteBorder(1,0,0,0, Color(200,210,230)))  ← solo borde superior
        // TODO: Agregar botones (usar crearBoton):
        //         "Limpiar"  → Color(100,100,100)
        //         "Eliminar" → Color(198,40,40)
        //         "+ Nuevo"  → Color(25,118,210)
        //         "Guardar"  → Color(46,125,50)
        // TODO: Conectar acciones de los botones (limpiar, eliminar, limpiar, guardar)
        // TODO: return panel
        return panel; // reemplazar
    }

    // ─────────────────────────────────────────────────────────────
    // PASO 9 (Guía §2.10): Datos de muestra y lógica
    // ─────────────────────────────────────────────────────────────
    private void cargarDatos() {
        
        productos.add(new Producto("Laptop Dell", "Electrónica", 899.99, 12, "Laptop Dell Inspiron 15 pulgadas, Intel Core i5"));
        
        productos.add(new Producto("Mouse Inalámbrico", "Accesorios", 25.99, 40, "Mouse ergonómico con receptor USB nano"));
        
        productos.add(new Producto("Teclado Mecánico", "Accesorios", 75.50, 18, "Teclado mecánico con switches Cherry MX Blue"));
        
        productos.add(new Producto("Monitor 24\"", "Electrónica", 210.00, 7, "Monitor Full HD IPS 24 pulgadas, 75Hz"));
        
        productos.add(new Producto("Silla Ergonómica", "Hogar", 320.00, 5, "Silla de oficina con soporte lumbar ajustable"));
        
        productos.add(new Producto("Escritorio de Madera", "Hogar", 185.00, 9, "Escritorio de 120cm con cajones laterales"));
        
        productos.add(new Producto("Auriculares Bluetooth", "Electrónica", 59.99, 25, "Auriculares con cancelación de ruido activa"));
        
        productos.add(new Producto("Webcam HD", "Accesorios", 45.00, 30, "Cámara web 1080p con micrófono integrado"));
        
        productos.forEach(modelo::addElement);
        
        // TODO: Agregar 8 productos a la lista usando new Producto(nombre, cat, precio, stock, desc)
        // TODO: Al final: productos.forEach(modelo::addElement)
    }

    private void cargarDetalle(Producto p) {
        
        if (p == null) return;
        
        lblDNombre.setText(p.getNombre());
        
        lblDPrecio.setText("$" + p.getPrecio());
        
        lblDStock.setText(String.valueOf(p.getStock()));
        
        lblDCat.setText(p.getCategoria());
        
        txtNombre.setText(p.getNombre());
        
        txtPrecio.setText(String.valueOf(p.getPrecio()));
        
        txtStock.setText(String.valueOf(p.getStock()));
        
        cboCat.setSelectedItem(p.getCategoria());
        
        txDesc.setText(p.getDescripcion());
        
        // TODO: Si p es null, retornar
        // TODO: Actualizar los 4 JLabel del panel de detalle
        //       lblDNombre.setText(p.getNombre()), etc.
        // TODO: Actualizar también los campos del formulario
        //       txtNombre.setText(p.getNombre()), etc.
    }

    private void filtrar(String q) {
        
         modelo.clear();
         
        for (Producto p : productos) {
            if (q.isEmpty() || p.getNombre().toLowerCase().contains(q.toLowerCase())
                    || p.getCategoria().toLowerCase().contains(q.toLowerCase())) {
                modelo.addElement(p);
            }
        }
        
        // TODO: modelo.clear()
        // TODO: Para cada Producto en productos, agregar al modelo si
        //       q está vacío O el nombre/categoría contienen q (ignoreCase)
    }

    private void filtrarCategoria(String cat) {
        
        modelo.clear();
        
        for (Producto p : productos) {
            if (cat.equals("Todas") || p.getCategoria().equals(cat)) {
                modelo.addElement(p);
            }
        }
        
        // TODO: modelo.clear()
        // TODO: Agregar los Productos cuya categoría coincida (o cat=="Todas")
    }

    private void guardar() {
        
        if (txtNombre.getText().trim().isEmpty() || txtPrecio.getText().trim().isEmpty()
                || txtStock.getText().trim().isEmpty()) {
            
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos obligatorios.",
                    "Campos requeridos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        JOptionPane.showMessageDialog(this, "Producto guardado correctamente.",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
        limpiar();
        
        // TODO: Validar que txtNombre, txtPrecio, txtStock no estén vacíos
        //       Si falta alguno: JOptionPane.WARNING_MESSAGE
        // TODO: JOptionPane.INFORMATION_MESSAGE indicando éxito
        // TODO: limpiar()
    }

    private void eliminar() {
        
        Producto sel = lstProductos.getSelectedValue();
        if (sel == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar.");
            return;
        }
        
        int resp = JOptionPane.showConfirmDialog(this,
                "Desea eliminar el producto: " + sel.getNombre() + "?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        
        if (resp == JOptionPane.YES_OPTION) {
            productos.remove(sel);
            modelo.removeElement(sel);
            limpiar();
        }
        
        // TODO: Obtener el producto seleccionado en lstProductos
        //       Si null: JOptionPane informativo
        // TODO: JOptionPane.showConfirmDialog para confirmar
        //       Si YES: productos.remove(sel), modelo.removeElement(sel), limpiar()
    }

    private void limpiar() {
        
        txtNombre.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
        cboCat.setSelectedIndex(0);
        lstProductos.clearSelection();
        lblDNombre.setText("—");
        lblDPrecio.setText("—");
        lblDStock.setText("—");
        lblDCat.setText("—");
        txDesc.setText("");
        txtNombre.requestFocus();
        
        // TODO: Limpiar txtNombre, txtPrecio, txtStock
        // TODO: cboCat.setSelectedIndex(0)
        // TODO: lstProductos.clearSelection()
        // TODO: Resetear los 4 JLabel del detalle a "—"
        // TODO: txDesc.setText("")
        // TODO: txtNombre.requestFocus()
    }

    // ─────────────────────────────────────────────────────────────
    // Helper: botón con estilo unificado del laboratorio
    // NO modificar este método.
    // ─────────────────────────────────────────────────────────────
    private JButton crearBoton(String label, Color color) {
        JButton btn = new JButton(label);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(7, 18, 7, 18));
        return btn;
    }
}
