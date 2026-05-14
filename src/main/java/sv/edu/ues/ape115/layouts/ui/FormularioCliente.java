package sv.edu.ues.ape115.layouts.ui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 * Requerimiento R02 — Formulario de Cliente con GridBagLayout y JTabbedPane.
 *
 * Estructura requerida:
 *
 *  JFrame  BorderLayout
 *  ├── CENTER → JTabbedPane
 *  │    ├── Pestaña "Datos Personales"  (JPanel GridBagLayout + CompoundBorder)
 *  │    │    ├── Fila 0: "Nombre *:"      → JTextField (span 3 cols)
 *  │    │    ├── Fila 1: "Apellido *:"    → JTextField (span 3 cols)
 *  │    │    └── Fila 2: "Fecha nac.:"    → JTextField (col 1-2)
 *  │    │               "Género:"         → JComboBox  (col 3)
 *  │    └── Pestaña "Contacto"            (JPanel GridBagLayout + CompoundBorder)
 *  │         ├── Fila 0: "Email *:"       → JTextField (span 3 cols)
 *  │         ├── Fila 1: "Teléfono:"      → JTextField (col 1-2)
 *  │         │           "Ciudad:"        → JComboBox  (col 3)
 *  │         └── Filas 2-3: "Dirección:"  → JTextArea  (span 3 cols, 2 filas)
 *  │                          en JScrollPane
 *  └── SOUTH → JPanel FlowLayout(RIGHT)   botones Cancelar / Registrar
 *
 * Reglas de negocio:
 *   RN-R02.1 Campos * obligatorios; Registrar muestra JOptionPane.WARNING si falta alguno.
 *   RN-R02.2 JTextArea de Dirección en JScrollPane.
 *   RN-R02.3 Insets homogéneos Insets(5, 6, 5, 6) en todas las filas.
 *   RN-R02.4 Botón Cancelar limpia todos los campos.
 *
 * @author Genesis
 */
public class FormularioCliente extends JFrame {
    
    private JTextField txtNombre, txtApellido, txtFechaNac;
    private JComboBox<String> cboGenero = new JComboBox<>(new String[]{"Masculino", "Femenino", "Otro"});

    private JTextField txtEmail, txtTelefono;
    private JComboBox<String> cboCiudad = new JComboBox<>(new String[]{"San Salvador", 
        "Cojutepeque", "Suchitoto", "San Martin", "Ilobasco"});
    private JTextArea txDireccion;

    // ── Pestaña Datos Personales ──────────────────────────────────
    // TODO: declarar JTextField txtNombre, txtApellido, txtFechaNac
    // TODO: declarar JComboBox<String> cboGenero con opciones Masculino/Femenino/Otro
    

    // ── Pestaña Contacto ─────────────────────────────────────────
    // TODO: declarar JTextField txtEmail, txtTelefono
    // TODO: declarar JComboBox<String> cboCiudad con ciudades de El Salvador
    // TODO: declarar JTextArea txDireccion

    public FormularioCliente() {
        super("R02 — Formulario de Registro de Cliente");
        construirUI();
        setSize(620, 500);
        setMinimumSize(new Dimension(500, 420));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // TODO: llamar construirUI()
        // TODO: setSize(620, 500)
        // TODO: setMinimumSize(new Dimension(500, 420))
        // TODO: setDefaultCloseOperation(EXIT_ON_CLOSE)
        // TODO: setLocationRelativeTo(null)
    }

    private void construirUI() {
        
        JPanel root = new JPanel(new BorderLayout(8, 8));
        
        root.setBorder(new EmptyBorder(12, 16, 12, 16));
        
        setContentPane(root);

        JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);
        
        tabs.addTab("Datos Personales", null, crearPestanaDatos(), "Datos personales del cliente");
        
        tabs.addTab("Contacto", null, crearPestanaContacto(), "Información de contacto");
        
        root.add(tabs, BorderLayout.CENTER);

        JPanel pBtns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 6));
        
        pBtns.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 210, 230)));
        
        JButton btnCancelar  = boton("Cancelar", new Color(100, 100, 100));
        
        JButton btnRegistrar = boton("Registrar", new Color(46, 125, 50));
        
        btnCancelar.addActionListener(e -> limpiar());
        
        btnRegistrar.addActionListener(e -> validarYRegistrar());
        
        pBtns.add(btnCancelar);
        
        pBtns.add(btnRegistrar);
        
        root.add(pBtns, BorderLayout.SOUTH);
        
        // TODO: JPanel root con BorderLayout(8,8)
        // TODO: root.setBorder(EmptyBorder(12,16,12,16))
        // TODO: setContentPane(root)

        // TODO: JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP)
        // TODO: tabs.addTab("Datos Personales", null, crearPestanaDatos(), "...")
        // TODO: tabs.addTab("Contacto",          null, crearPestanaContacto(), "...")
        // TODO: root.add(tabs, BorderLayout.CENTER)

        // TODO: JPanel pBtns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 6))
        // TODO: pBtns.setBorder(MatteBorder(1,0,0,0, ...))
        // TODO: JButton btnCancelar → limpiar()
        // TODO: JButton btnRegistrar → validarYRegistrar()
        // TODO: root.add(pBtns, BorderLayout.SOUTH)
    }

    private JPanel crearPestanaDatos() {
        
        JPanel panel = new JPanel(new GridBagLayout());
        
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
            "Datos Personales", TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 11), 
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
        panel.add(new JLabel("Apellido *:"), g);
        txtApellido = new JTextField();
        
        g.gridx = 1; 
        g.gridwidth = 3; 
        g.fill = GridBagConstraints.HORIZONTAL; 
        g.weightx = 1;
        panel.add(txtApellido, g);
        g.gridwidth = 1; 
        g.weightx = 0;

        
        g.gridx = 0; 
        g.gridy = 2; 
        g.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Fecha nac. (dd/MM/aaaa):"), g);
        txtFechaNac = new JTextField(10);
        g.gridx = 1; 
        g.gridwidth = 2; 
        g.fill = GridBagConstraints.HORIZONTAL; 
        g.weightx = 0.6;
        panel.add(txtFechaNac, g);
        
        g.gridx = 3; 
        g.gridwidth = 1; 
        g.fill = GridBagConstraints.HORIZONTAL; 
        g.weightx = 0.4;
        panel.add(cboGenero, g);
        g.weightx = 0;

        JPanel relleno = new JPanel();
        relleno.setOpaque(false);
        g.gridx = 0; 
        g.gridy = 3; 
        g.gridwidth = 4;
        g.fill = GridBagConstraints.BOTH; 
        g.weightx = 1; 
        g.weighty = 1;
        panel.add(relleno, g);
        
        // TODO: new JPanel(new GridBagLayout())
        // TODO: setBorder( CompoundBorder: TitledBorder("Datos Personales") + EmptyBorder )
        // TODO: GridBagConstraints g; insets=Insets(5,6,5,6); anchor=WEST

        // TODO — Fila 0: "Nombre *:" (col 0) + txtNombre (col 1-3 span=3, fill=HORIZONTAL)
        // TODO — Fila 1: "Apellido *:" + txtApellido (span=3)
        // TODO — Fila 2: "Fecha nac. (dd/MM/aaaa):" + txtFechaNac (col 1-2) + cboGenero (col 3)
        // TODO — Fila 3: panel vacío con fill=BOTH, weighty=1  (relleno vertical)

        // TODO: return panel
        return panel;
    }

    private JPanel crearPestanaContacto() {
        
        JPanel panel = new JPanel(new GridBagLayout());
        
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
            "Contacto", TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 11), 
            new Color(25, 118, 210)), new EmptyBorder(8, 10, 8, 10)));
        
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 6, 5, 6);
        g.anchor = GridBagConstraints.WEST;

        g.gridx = 0; 
        g.gridy = 0; 
        g.fill = GridBagConstraints.NONE; 
        g.weightx = 0;
        panel.add(new JLabel("Email *:"), g);
        txtEmail = new JTextField();
        
        g.gridx = 1; 
        g.gridwidth = 3; 
        g.fill = GridBagConstraints.HORIZONTAL; 
        g.weightx = 1;
        panel.add(txtEmail, g);
        g.gridwidth = 1; 
        g.weightx = 0;

        
        g.gridx = 0; 
        g.gridy = 1; 
        g.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Teléfono:"), g);
        txtTelefono = new JTextField(10);
        
        g.gridx = 1; 
        g.gridwidth = 2; 
        g.fill = GridBagConstraints.HORIZONTAL; 
        g.weightx = 0.6;
        panel.add(txtTelefono, g);
        
        g.gridx = 3; 
        g.gridwidth = 1; 
        g.fill = GridBagConstraints.HORIZONTAL; 
        g.weightx = 0.4;
        panel.add(cboCiudad, g);
        g.weightx = 0;
        

        g.gridx = 0; 
        g.gridy = 2; 
        g.fill = GridBagConstraints.NONE; 
        g.gridwidth = 1;
        panel.add(new JLabel("Dirección:"), g);

        txDireccion = new JTextArea();
        txDireccion.setLineWrap(true);
        txDireccion.setWrapStyleWord(true);
        g.gridx = 1; 
        g.gridwidth = 3; 
        g.gridheight = 2;
        g.fill = GridBagConstraints.BOTH; 
        g.weightx = 1; 
        g.weighty = 1;
        panel.add(new JScrollPane(txDireccion), g);
        
        // TODO: new JPanel(new GridBagLayout())
        // TODO: setBorder( CompoundBorder: TitledBorder("Contacto") + EmptyBorder )
        // TODO: GridBagConstraints g; insets=Insets(5,6,5,6); anchor=WEST

        // TODO — Fila 0: "Email *:" + txtEmail (span=3, fill=HORIZONTAL)
        // TODO — Fila 1: "Teléfono:" + txtTelefono (col 1-2) + cboCiudad (col 3)
        // TODO — Fila 2: "Dirección:" (col 0)
        // TODO — Filas 2-3: new JScrollPane(txDireccion) en col 1, span 3 cols × 2 filas
        //         txDireccion.setLineWrap(true); txDireccion.setWrapStyleWord(true)
        //         fill=BOTH, weightx=1, weighty=1

        // TODO: return panel
        return panel; // reemplazar
    }

    private void validarYRegistrar() {
        
        if (txtNombre.getText().trim().isEmpty() || txtApellido.getText().trim().isEmpty()
                || txtEmail.getText().trim().isEmpty()) {
            
            JOptionPane.showMessageDialog(this, "Por favor complete los campos obligatorios (*).",
                    "Campos requeridos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        JOptionPane.showMessageDialog(this,
                "Cliente registrado: " + txtNombre.getText() + " " + txtApellido.getText(),
                "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
        limpiar();
        
        // TODO: Verificar que txtNombre, txtApellido y txtEmail no estén vacíos (RN-R02.1)
        //       Si falta alguno: JOptionPane.showMessageDialog(..., WARNING_MESSAGE)
        //       Si todo OK: JOptionPane.INFORMATION_MESSAGE con nombre registrado + limpiar()
    }

    private void limpiar() {
        
        txtNombre.setText("");
        txtApellido.setText("");
        txtFechaNac.setText("");
        txtEmail.setText("");
        txtTelefono.setText("");
        txDireccion.setText("");
        cboGenero.setSelectedIndex(0);
        cboCiudad.setSelectedIndex(0);
        
        // TODO: Limpiar todos los JTextField (RN-R02.4)
        // TODO: Limpiar txDireccion
        // TODO: Resetear JComboBox a índice 0
    }

    // ── Helper: botón estándar del laboratorio ────────────────────
    // NO modificar.
    private JButton boton(String label, Color color) {
        JButton btn = new JButton(label);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setBackground(color); btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false); btn.setBorderPainted(false); btn.setOpaque(true);
        btn.setBorder(BorderFactory.createEmptyBorder(7, 18, 7, 18));
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormularioCliente().setVisible(true));
    }
}
