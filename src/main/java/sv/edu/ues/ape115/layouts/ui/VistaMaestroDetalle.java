package sv.edu.ues.ape115.layouts.ui;

import sv.edu.ues.ape115.layouts.model.Producto;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Requerimiento R04 — Vista Maestro-Detalle con JSplitPane.
 *
 * Estructura requerida:
 *
 *  JFrame  BorderLayout
 *  └── CENTER → JSplitPane(HORIZONTAL_SPLIT, divider=260, resize=0.30, oneTouch=true)
 *       ├── Izquierda (Maestro)  JPanel BorderLayout  CompoundBorder "Productos"
 *       │    ├── NORTH → JTextField búsqueda (LineBorder azul)
 *       │    └── CENTER → JList<Producto> en JScrollPane (8 productos)
 *       └── Derecha (Detalle)    JPanel BorderLayout  CompoundBorder "Detalle del Producto"
 *            ├── CENTER → JPanel GridBagLayout
 *            │    ├── Fila 0: "Nombre:"     → JTextField  (span 2)
 *            │    ├── Fila 1: "Precio $:"   → JTextField
 *            │    ├── Fila 2: "Stock:"      → JTextField
 *            │    ├── Fila 3: "Categoría:"  → JComboBox  (span 2)
 *            │    └── Filas 4-5: "Descripción:" → JTextArea en JScrollPane (span 2 × 2 filas)
 *            └── SOUTH → FlowLayout(RIGHT)  Cancelar / Guardar
 *
 * Reglas de negocio:
 *   RN-R04.1 Seleccionar un producto en la JList carga todos sus campos en el detalle.
 *   RN-R04.2 Presionar Enter en el campo de búsqueda filtra la JList por nombre.
 *   RN-R04.3 JList y JTextArea en JScrollPane.
 *   RN-R04.4 JSplitPane: setResizeWeight(0.30).
 *
 * @author Genesis 
 */
public class VistaMaestroDetalle extends JFrame {

    private final List<Producto>             productos = new ArrayList<>();
    private final DefaultListModel<Producto> modelo    = new DefaultListModel<>();
    
    private JList<Producto> lstProductos;

    private JTextField txtNombre, txtPrecio, txtStock;
    private JComboBox<String> cboCat;
    private JTextArea txDesc;

    // TODO: declarar JList<Producto> lstProductos

    // TODO: declarar campos del formulario de detalle:
    //   JTextField txtNombre, txtPrecio, txtStock
    //   JComboBox<String> cboCat
    //   JTextArea txDesc

    public VistaMaestroDetalle() {
        super("R04 — Vista Maestro-Detalle: JSplitPane");
        cargarDatos();
        
        construirUI();
        
        setSize(860, 560);
        
        setMinimumSize(new Dimension(700, 480));
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setLocationRelativeTo(null);
        
        // TODO: construirUI()
        // TODO: setSize(860, 560)
        // TODO: setMinimumSize(new Dimension(700, 480))
        // TODO: setDefaultCloseOperation(EXIT_ON_CLOSE)
        // TODO: setLocationRelativeTo(null)
    }

    private void cargarDatos() {
        
        productos.add(new Producto("Laptop Dell",         "Electrónica",  899.99, 12, "Laptop Dell Inspiron 15, Core i5, 8GB RAM"));
        
        productos.add(new Producto("Mouse Inalámbrico",   "Accesorios",    25.99, 40, "Mouse ergonómico con receptor USB nano"));
        
        productos.add(new Producto("Teclado Mecánico",    "Accesorios",    75.50, 18, "Teclado mecánico con switches Cherry MX Blue"));
        
        productos.add(new Producto("Monitor 24\"",         "Electrónica",  210.00,  7, "Monitor Full HD IPS 24 pulgadas, 75Hz"));
        
        productos.add(new Producto("Silla Ergonómica",    "Hogar",         320.00,  5, "Silla de oficina con soporte lumbar ajustable"));
        
        productos.add(new Producto("Escritorio de Madera","Hogar",         185.00,  9, "Escritorio de 120cm con cajones laterales"));
        
        productos.add(new Producto("Auriculares Bluetooth","Electrónica",   59.99, 25, "Auriculares con cancelación de ruido activa"));
        
        productos.add(new Producto("Webcam HD",           "Accesorios",    45.00, 30, "Cámara web 1080p con micrófono integrado"));
        
        for (Producto p : productos) modelo.addElement(p);
        // TODO: Agregar 8 productos a la lista y al modelo
        //   Ejemplo: productos.add(new Producto("Laptop Dell","Electrónica",899.99,12,"desc"))
        //            modelo.addElement(prod)
    }

    private void construirUI() {
        
        JPanel root = new JPanel(new BorderLayout(8, 8));
        
        root.setBorder(new EmptyBorder(10, 12, 10, 12));
        
        setContentPane(root);
        
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, crearMaestro(), crearDetalle());
        
        split.setDividerLocation(260);
        
        split.setResizeWeight(0.30);
        
        split.setOneTouchExpandable(true);
        
        root.add(split, BorderLayout.CENTER);
        
        // TODO: JPanel root con BorderLayout(8,8) + EmptyBorder(10,12,10,12)
        // TODO: setContentPane(root)
        // TODO: JSplitPane split = new JSplitPane(HORIZONTAL_SPLIT, crearMaestro(), crearDetalle())
        // TODO: split.setDividerLocation(260)
        // TODO: split.setResizeWeight(0.30)       ← RN-R04.4
        // TODO: split.setOneTouchExpandable(true)  ← requerido por test T05.3
        // TODO: root.add(split, BorderLayout.CENTER)
    }

    private JPanel crearMaestro() {
        
        JPanel panel = new JPanel(new BorderLayout(0, 6));
        
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Productos", TitledBorder.LEFT, 
                TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 11), 
                new Color(25, 118, 210)), new EmptyBorder(6, 8, 6, 8)));

        JTextField txtBuscar = new JTextField();
        
        txtBuscar.setBorder(BorderFactory.createLineBorder(new Color(25, 118, 210)));
        
        txtBuscar.addActionListener(e -> filtrar(txtBuscar.getText()));
        
        panel.add(txtBuscar, BorderLayout.NORTH);

        lstProductos = new JList<>(modelo);
        
        lstProductos.addListSelectionListener(e -> {
            
            if (!e.getValueIsAdjusting()) {
                cargarDetalle(lstProductos.getSelectedValue());
            }
        });
        
        panel.add(new JScrollPane(lstProductos), BorderLayout.CENTER);
        
        // TODO: new JPanel(new BorderLayout(0, 6))
        // TODO: CompoundBorder: TitledBorder("Productos", EtchedBorder, azul) + EmptyBorder
        //
        // NORTH: JTextField txtBuscar con LineBorder azul
        //   addActionListener → filtrar(txtBuscar.getText())  ← RN-R04.2
        //
        // CENTER: lstProductos = new JList<>(modelo) en JScrollPane  ← RN-R04.3
        //   addListSelectionListener → si !isAdjusting: cargarDetalle(selectedValue)  ← RN-R04.1
        //
        // TODO: return panel
        return panel; // reemplazar
    }

    private JPanel crearDetalle() {
        
        JPanel panel = new JPanel(new BorderLayout(0, 8));
        
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
            "Detalle del Producto", TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 11), 
            new Color(25, 118, 210)), new EmptyBorder(6, 8, 6, 8)));

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 6, 5, 6);
        g.anchor = GridBagConstraints.WEST;

        g.gridx = 0; 
        g.gridy = 0; 
        g.fill = GridBagConstraints.NONE; 
        g.weightx = 0;
        form.add(new JLabel("Nombre:"), g);
        txtNombre = new JTextField();
        
        g.gridx = 1; 
        g.gridwidth = 2; g.fill = GridBagConstraints.HORIZONTAL; 
        g.weightx = 1;
        form.add(txtNombre, g);
        g.gridwidth = 1; 
        g.weightx = 0;


        g.gridx = 0; 
        g.gridy = 1; 
        g.fill = GridBagConstraints.NONE;
        form.add(new JLabel("Precio $:"), g);
        txtPrecio = new JTextField();
        
        g.gridx = 1; 
        g.gridwidth = 2; 
        g.fill = GridBagConstraints.HORIZONTAL; 
        g.weightx = 1;
        form.add(txtPrecio, g);
        g.gridwidth = 1; 
        g.weightx = 0;


        g.gridx = 0; 
        g.gridy = 2; 
        g.fill = GridBagConstraints.NONE;
        form.add(new JLabel("Stock:"), g);
        txtStock = new JTextField();
        
        g.gridx = 1; 
        g.gridwidth = 2; 
        g.fill = GridBagConstraints.HORIZONTAL; 
        g.weightx = 1;
        form.add(txtStock, g);
        g.gridwidth = 1; 
        g.weightx = 0;


        g.gridx = 0; 
        g.gridy = 3; 
        g.fill = GridBagConstraints.NONE;
        form.add(new JLabel("Categoría:"), g);
        cboCat = new JComboBox<>(new String[]{"Electrónica", "Hogar", "Accesorios"});
        
        g.gridx = 1; 
        g.gridwidth = 2; 
        g.fill = GridBagConstraints.HORIZONTAL; 
        g.weightx = 1;
        form.add(cboCat, g);
        g.gridwidth = 1; 
        g.weightx = 0;


        g.gridx = 0; 
        g.gridy = 4; 
        g.fill = GridBagConstraints.NONE;
        form.add(new JLabel("Descripción:"), g);
        txDesc = new JTextArea();
        txDesc.setLineWrap(true);
        txDesc.setWrapStyleWord(true);
       
        g.gridx = 1; 
        g.gridwidth = 2; 
        g.gridheight = 2;
        g.fill = GridBagConstraints.BOTH; 
        g.weightx = 1; 
        g.weighty = 1;
        form.add(new JScrollPane(txDesc), g);

        panel.add(form, BorderLayout.CENTER);

        JPanel pSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 6));
        
        JButton btnCancelar = new JButton("Cancelar");
        
        JButton btnGuardar  = new JButton("Guardar");
        
        btnCancelar.addActionListener(e -> limpiar());
        
        pSouth.add(btnCancelar);
        
        pSouth.add(btnGuardar);
        
        panel.add(pSouth, BorderLayout.SOUTH);
        
        // TODO: new JPanel(new BorderLayout(0, 8))
        // TODO: CompoundBorder: TitledBorder("Detalle del Producto", EtchedBorder, azul) + EmptyBorder
        //
        // CENTER: JPanel con GridBagLayout
        //   Fila 0: "Nombre:"      + txtNombre  (span 2 cols, fill=HORIZONTAL)
        //   Fila 1: "Precio $:"    + txtPrecio
        //   Fila 2: "Stock:"       + txtStock
        //   Fila 3: "Categoría:"   + cboCat     (span 2 cols)
        //   Filas 4-5: "Descripción:" + new JScrollPane(txDesc)  ← RN-R04.3
        //              gridwidth=2, gridheight=2, fill=BOTH, weighty=1
        //
        // SOUTH: FlowLayout(RIGHT) con botones Cancelar / Guardar
        //
        // TODO: return panel
        return panel; // reemplazar
    }

    private void cargarDetalle(Producto p) {
        
        if (p == null) return;
        
        txtNombre.setText(p.getNombre());
        
        txtPrecio.setText(String.valueOf(p.getPrecio()));
        
        txtStock.setText(String.valueOf(p.getStock()));
        
        cboCat.setSelectedItem(p.getCategoria());
        
        txDesc.setText(p.getDescripcion());
        
        // TODO: Si p es null, retornar
        // TODO: Llenar txtNombre, txtPrecio, txtStock con datos del producto
        // TODO: cboCat.setSelectedItem(p.getCategoria())
        // TODO: txDesc.setText(p.getDescripcion())
    }

    private void filtrar(String q) {
        
        modelo.clear();
        
        for (Producto p : productos) {
            if (q.isEmpty() || p.getNombre().toLowerCase().contains(q.toLowerCase())) {
                modelo.addElement(p);
            }
        }
        
        // TODO: modelo.clear()
        // TODO: Agregar productos cuyo nombre contenga q (ignoreCase), o q está vacío
    }

    private void limpiar() {
        
        txtNombre.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
        txDesc.setText("");
        lstProductos.clearSelection();
        
        // TODO: Limpiar todos los campos de texto
        // TODO: lstProductos.clearSelection()
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VistaMaestroDetalle().setVisible(true));
    }
}
