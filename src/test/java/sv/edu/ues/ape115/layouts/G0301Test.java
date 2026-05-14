package sv.edu.ues.ape115.layouts;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import sv.edu.ues.ape115.layouts.model.Producto;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.Arrays;

/**
 * Tests de autocalificación — G0301 Layout Managers y Bordes.
 * Total: 100 puntos distribuidos en 7 grupos.
 *
 * T01 (15 pts) — VentanaProductos: JSplitPane, JTabbedPane, JList, CompoundBorder
 * T02 (15 pts) — VitrinaLayouts:   GridLayout 2×3, CardLayout, TitledBorder
 * T03 (15 pts) — FormularioCliente: JTabbedPane ≥2 tabs, JTextArea, CompoundBorder
 * T04 (15 pts) — AppMenuLateral:   BoxLayout Y_AXIS, CardLayout, ≥5 botones
 * T05 (20 pts) — VistaMaestroDetalle: JSplitPane parámetros, JList con datos
 * T06 (15 pts) — DashboardIntegrado: JSplitPane + JTabbedPane 3 tabs + tamaño mínimo
 * T07 ( 5 pts) — Estándares: Producto.java, Main.java, sin null layout
 *
 * Ejecutar con: mvn test
 * Requiere Xvfb en CI: Xvfb :99 -screen 0 1280x1024x24 &
 *
 * @author APE 115 UES
 */
@TestMethodOrder(MethodOrderer.DisplayName.class)
class G0301Test {

    // ══════════════════════════════════════════════════════════════
    // T01 — VentanaProductos — Ejemplo completo (15 pts)
    // ══════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("T01 - Ejemplo VentanaProductos (15 pts)")
    class T01_Ejemplo {

        @Test @DisplayName("T01.1 — VentanaProductos existe y extiende JFrame")
        void existe_y_es_jframe() throws Exception {
            Class<?> cls = assertDoesNotThrow(() ->
                Class.forName("sv.edu.ues.ape115.layouts.ui.VentanaProductos"),
                "VentanaProductos.java debe estar en sv.edu.ues.ape115.layouts.ui");
            assertTrue(JFrame.class.isAssignableFrom(cls),
                "VentanaProductos debe extender JFrame");
        }

        @Test @DisplayName("T01.2 — VentanaProductos contiene un JSplitPane")
        void tiene_splitpane() throws Exception {
            SwingUtilities.invokeAndWait(() -> {
                sv.edu.ues.ape115.layouts.ui.VentanaProductos v =
                    new sv.edu.ues.ape115.layouts.ui.VentanaProductos();
                assertTrue(tieneTipo(v, JSplitPane.class),
                    "VentanaProductos debe contener un JSplitPane para la vista maestro-detalle");
                v.dispose();
            });
        }

        @Test @DisplayName("T01.3 — VentanaProductos contiene un JTabbedPane")
        void tiene_tabbedpane() throws Exception {
            SwingUtilities.invokeAndWait(() -> {
                sv.edu.ues.ape115.layouts.ui.VentanaProductos v =
                    new sv.edu.ues.ape115.layouts.ui.VentanaProductos();
                assertTrue(tieneTipo(v, JTabbedPane.class),
                    "VentanaProductos debe contener un JTabbedPane con las pestañas Datos y Descripción");
                v.dispose();
            });
        }

        @Test @DisplayName("T01.4 — JTabbedPane tiene al menos 2 pestañas")
        void tabbedpane_tiene_dos_tabs() throws Exception {
            SwingUtilities.invokeAndWait(() -> {
                sv.edu.ues.ape115.layouts.ui.VentanaProductos v =
                    new sv.edu.ues.ape115.layouts.ui.VentanaProductos();
                JTabbedPane t = encontrarTipo(v, JTabbedPane.class);
                assertNotNull(t, "JTabbedPane no encontrado en VentanaProductos");
                assertTrue(t.getTabCount() >= 2,
                    "JTabbedPane debe tener al menos 2 pestañas. Tiene: " + t.getTabCount());
                v.dispose();
            });
        }

        @Test @DisplayName("T01.5 — VentanaProductos contiene JList con datos precargados")
        void tiene_jlist_con_datos() throws Exception {
            SwingUtilities.invokeAndWait(() -> {
                sv.edu.ues.ape115.layouts.ui.VentanaProductos v =
                    new sv.edu.ues.ape115.layouts.ui.VentanaProductos();
                JList<?> lst = encontrarTipo(v, JList.class);
                assertNotNull(lst, "VentanaProductos debe contener una JList de productos");
                assertTrue(lst.getModel().getSize() > 0,
                    "La JList debe estar precargada con al menos un producto");
                v.dispose();
            });
        }

        @Test @DisplayName("T01.6 — VentanaProductos usa CompoundBorder en los paneles")
        void usa_compound_border() throws Exception {
            SwingUtilities.invokeAndWait(() -> {
                sv.edu.ues.ape115.layouts.ui.VentanaProductos v =
                    new sv.edu.ues.ape115.layouts.ui.VentanaProductos();
                assertTrue(tieneBordeTipo(v, CompoundBorder.class),
                    "VentanaProductos debe usar CompoundBorder (TitledBorder+EmptyBorder) en los paneles");
                v.dispose();
            });
        }
    }

    // ══════════════════════════════════════════════════════════════
    // T02 — VitrinaLayouts — R01 (15 pts)
    // ══════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("T02 - R01 VitrinaLayouts (15 pts)")
    class T02_Vitrina {

        @Test @DisplayName("T02.1 — VitrinaLayouts existe")
        void clase_existe() {
            assertDoesNotThrow(() ->
                Class.forName("sv.edu.ues.ape115.layouts.ui.VitrinaLayouts"),
                "VitrinaLayouts.java debe estar en sv.edu.ues.ape115.layouts.ui");
        }

        @Test @DisplayName("T02.2 — VitrinaLayouts usa GridLayout en el panel CENTER")
        void usa_gridlayout() throws Exception {
            SwingUtilities.invokeAndWait(() -> {
                sv.edu.ues.ape115.layouts.ui.VitrinaLayouts v =
                    new sv.edu.ues.ape115.layouts.ui.VitrinaLayouts();
                assertTrue(tieneTipoLayout(v, GridLayout.class),
                    "VitrinaLayouts debe tener GridLayout(2,3) en el panel central");
                v.dispose();
            });
        }

        @Test @DisplayName("T02.3 — VitrinaLayouts declara un campo CardLayout")
        void tiene_campo_cardlayout() throws Exception {
            Class<?> cls = Class.forName("sv.edu.ues.ape115.layouts.ui.VitrinaLayouts");
            boolean tiene = Arrays.stream(cls.getDeclaredFields())
                .anyMatch(f -> f.getType() == CardLayout.class);
            assertTrue(tiene,
                "VitrinaLayouts debe declarar un campo CardLayout para la celda 6 (navegación entre tarjetas)");
        }

        @Test @DisplayName("T02.4 — VitrinaLayouts usa TitledBorder en las celdas")
        void usa_titled_border() throws Exception {
            SwingUtilities.invokeAndWait(() -> {
                sv.edu.ues.ape115.layouts.ui.VitrinaLayouts v =
                    new sv.edu.ues.ape115.layouts.ui.VitrinaLayouts();
                assertTrue(tieneBordeTipo(v, TitledBorder.class),
                    "Cada celda de VitrinaLayouts debe tener un TitledBorder con el nombre del layout");
                v.dispose();
            });
        }

        @Test @DisplayName("T02.5 — VitrinaLayouts tiene al menos 6 JButton")
        void tiene_minimo_seis_botones() throws Exception {
            SwingUtilities.invokeAndWait(() -> {
                sv.edu.ues.ape115.layouts.ui.VitrinaLayouts v =
                    new sv.edu.ues.ape115.layouts.ui.VitrinaLayouts();
                long n = contarTipo(v, JButton.class);
                assertTrue(n >= 6,
                    "VitrinaLayouts debe tener al menos 6 JButton (celdas de FlowLayout y GridLayout). Tiene: " + n);
                v.dispose();
            });
        }
    }

    // ══════════════════════════════════════════════════════════════
    // T03 — FormularioCliente — R02 (15 pts)
    // ══════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("T03 - R02 FormularioCliente (15 pts)")
    class T03_Formulario {

        @Test @DisplayName("T03.1 — FormularioCliente existe")
        void clase_existe() {
            assertDoesNotThrow(() ->
                Class.forName("sv.edu.ues.ape115.layouts.ui.FormularioCliente"));
        }

        @Test @DisplayName("T03.2 — FormularioCliente contiene JTabbedPane")
        void tiene_tabbedpane() throws Exception {
            SwingUtilities.invokeAndWait(() -> {
                sv.edu.ues.ape115.layouts.ui.FormularioCliente f =
                    new sv.edu.ues.ape115.layouts.ui.FormularioCliente();
                assertTrue(tieneTipo(f, JTabbedPane.class),
                    "FormularioCliente debe contener un JTabbedPane");
                f.dispose();
            });
        }

        @Test @DisplayName("T03.3 — JTabbedPane tiene al menos 2 pestañas")
        void tabbedpane_dos_tabs() throws Exception {
            SwingUtilities.invokeAndWait(() -> {
                sv.edu.ues.ape115.layouts.ui.FormularioCliente f =
                    new sv.edu.ues.ape115.layouts.ui.FormularioCliente();
                JTabbedPane t = encontrarTipo(f, JTabbedPane.class);
                assertNotNull(t, "JTabbedPane no encontrado");
                assertTrue(t.getTabCount() >= 2,
                    "JTabbedPane debe tener al menos 2 pestañas (Datos Personales y Contacto). Tiene: "
                    + t.getTabCount());
                f.dispose();
            });
        }

        @Test @DisplayName("T03.4 — FormularioCliente tiene JTextArea en JScrollPane (Dirección)")
        void tiene_textarea() throws Exception {
            SwingUtilities.invokeAndWait(() -> {
                sv.edu.ues.ape115.layouts.ui.FormularioCliente f =
                    new sv.edu.ues.ape115.layouts.ui.FormularioCliente();
                assertTrue(tieneTipo(f, JTextArea.class),
                    "FormularioCliente debe tener JTextArea para el campo Dirección");
                f.dispose();
            });
        }

        @Test @DisplayName("T03.5 — FormularioCliente usa CompoundBorder en las pestañas")
        void usa_compound_border() throws Exception {
            SwingUtilities.invokeAndWait(() -> {
                sv.edu.ues.ape115.layouts.ui.FormularioCliente f =
                    new sv.edu.ues.ape115.layouts.ui.FormularioCliente();
                assertTrue(tieneBordeTipo(f, CompoundBorder.class),
                    "Cada pestaña debe usar CompoundBorder (TitledBorder + EmptyBorder)");
                f.dispose();
            });
        }
    }

    // ══════════════════════════════════════════════════════════════
    // T04 — AppMenuLateral — R03 (15 pts)
    // ══════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("T04 - R03 AppMenuLateral (15 pts)")
    class T04_MenuLateral {

        @Test @DisplayName("T04.1 — AppMenuLateral existe")
        void clase_existe() {
            assertDoesNotThrow(() ->
                Class.forName("sv.edu.ues.ape115.layouts.ui.AppMenuLateral"));
        }

        @Test @DisplayName("T04.2 — AppMenuLateral usa BoxLayout en el panel WEST")
        void usa_boxlayout() throws Exception {
            SwingUtilities.invokeAndWait(() -> {
                sv.edu.ues.ape115.layouts.ui.AppMenuLateral a =
                    new sv.edu.ues.ape115.layouts.ui.AppMenuLateral();
                assertTrue(tieneTipoLayout(a, BoxLayout.class),
                    "AppMenuLateral debe usar BoxLayout(Y_AXIS) en el panel de navegación lateral");
                a.dispose();
            });
        }

        @Test @DisplayName("T04.3 — AppMenuLateral declara un campo CardLayout")
        void tiene_campo_cardlayout() throws Exception {
            Class<?> cls = Class.forName("sv.edu.ues.ape115.layouts.ui.AppMenuLateral");
            boolean tiene = Arrays.stream(cls.getDeclaredFields())
                .anyMatch(f -> f.getType() == CardLayout.class);
            assertTrue(tiene,
                "AppMenuLateral debe declarar un campo CardLayout para el área de contenido central");
        }

        @Test @DisplayName("T04.4 — AppMenuLateral tiene al menos 5 botones de módulo")
        void tiene_cinco_botones() throws Exception {
            SwingUtilities.invokeAndWait(() -> {
                sv.edu.ues.ape115.layouts.ui.AppMenuLateral a =
                    new sv.edu.ues.ape115.layouts.ui.AppMenuLateral();
                long n = contarTipo(a, JButton.class);
                assertTrue(n >= 5,
                    "AppMenuLateral debe tener al menos 5 botones de navegación (más el de Salir). Tiene: " + n);
                a.dispose();
            });
        }

        @Test @DisplayName("T04.5 — AppMenuLateral usa borde en el panel lateral")
        void usa_borde() throws Exception {
            SwingUtilities.invokeAndWait(() -> {
                sv.edu.ues.ape115.layouts.ui.AppMenuLateral a =
                    new sv.edu.ues.ape115.layouts.ui.AppMenuLateral();
                assertTrue(tieneBordeTipo(a, CompoundBorder.class)
                        || tieneBordeTipo(a, TitledBorder.class)
                        || tieneBordeTipo(a, MatteBorder.class),
                    "AppMenuLateral debe aplicar algún borde BorderFactory al panel WEST");
                a.dispose();
            });
        }
    }

    // ══════════════════════════════════════════════════════════════
    // T05 — VistaMaestroDetalle — R04 (20 pts)
    // ══════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("T05 - R04 VistaMaestroDetalle (20 pts)")
    class T05_MaestroDetalle {

        @Test @DisplayName("T05.1 — VistaMaestroDetalle existe")
        void clase_existe() {
            assertDoesNotThrow(() ->
                Class.forName("sv.edu.ues.ape115.layouts.ui.VistaMaestroDetalle"));
        }

        @Test @DisplayName("T05.2 — VistaMaestroDetalle contiene JSplitPane HORIZONTAL")
        void tiene_splitpane() throws Exception {
            SwingUtilities.invokeAndWait(() -> {
                sv.edu.ues.ape115.layouts.ui.VistaMaestroDetalle v =
                    new sv.edu.ues.ape115.layouts.ui.VistaMaestroDetalle();
                assertTrue(tieneTipo(v, JSplitPane.class),
                    "VistaMaestroDetalle debe contener un JSplitPane HORIZONTAL_SPLIT");
                v.dispose();
            });
        }

        @Test @DisplayName("T05.3 — JSplitPane tiene setOneTouchExpandable(true)")
        void splitpane_one_touch() throws Exception {
            SwingUtilities.invokeAndWait(() -> {
                sv.edu.ues.ape115.layouts.ui.VistaMaestroDetalle v =
                    new sv.edu.ues.ape115.layouts.ui.VistaMaestroDetalle();
                JSplitPane sp = encontrarTipo(v, JSplitPane.class);
                assertNotNull(sp, "JSplitPane no encontrado");
                assertTrue(sp.isOneTouchExpandable(),
                    "El JSplitPane debe tener setOneTouchExpandable(true) para mostrar los botones ◀▶");
                v.dispose();
            });
        }

        @Test @DisplayName("T05.4 — JSplitPane tiene dividerLocation > 0 y resizeWeight válido")
        void splitpane_parametros() throws Exception {
            SwingUtilities.invokeAndWait(() -> {
                sv.edu.ues.ape115.layouts.ui.VistaMaestroDetalle v =
                    new sv.edu.ues.ape115.layouts.ui.VistaMaestroDetalle();
                JSplitPane sp = encontrarTipo(v, JSplitPane.class);
                assertNotNull(sp);
                assertTrue(sp.getDividerLocation() > 0,
                    "setDividerLocation debe ser mayor que 0. Valor actual: " + sp.getDividerLocation());
                assertTrue(sp.getResizeWeight() > 0 && sp.getResizeWeight() < 1,
                    "setResizeWeight debe estar entre 0 y 1. Valor actual: " + sp.getResizeWeight());
                v.dispose();
            });
        }

        @Test @DisplayName("T05.5 — VistaMaestroDetalle contiene JList precargada con datos")
        void tiene_jlist_con_datos() throws Exception {
            SwingUtilities.invokeAndWait(() -> {
                sv.edu.ues.ape115.layouts.ui.VistaMaestroDetalle v =
                    new sv.edu.ues.ape115.layouts.ui.VistaMaestroDetalle();
                JList<?> lst = encontrarTipo(v, JList.class);
                assertNotNull(lst, "VistaMaestroDetalle debe contener una JList");
                assertTrue(lst.getModel().getSize() > 0,
                    "La JList debe estar precargada con al menos un producto. Tiene: "
                    + lst.getModel().getSize());
                v.dispose();
            });
        }

        @Test @DisplayName("T05.6 — VistaMaestroDetalle contiene JTextArea (descripción)")
        void tiene_textarea() throws Exception {
            SwingUtilities.invokeAndWait(() -> {
                sv.edu.ues.ape115.layouts.ui.VistaMaestroDetalle v =
                    new sv.edu.ues.ape115.layouts.ui.VistaMaestroDetalle();
                assertTrue(tieneTipo(v, JTextArea.class),
                    "VistaMaestroDetalle debe contener un JTextArea para la descripción del producto");
                v.dispose();
            });
        }

        @Test @DisplayName("T05.7 — VistaMaestroDetalle usa TitledBorder en los dos paneles")
        void usa_titled_border() throws Exception {
            SwingUtilities.invokeAndWait(() -> {
                sv.edu.ues.ape115.layouts.ui.VistaMaestroDetalle v =
                    new sv.edu.ues.ape115.layouts.ui.VistaMaestroDetalle();
                assertTrue(tieneBordeTipo(v, TitledBorder.class),
                    "VistaMaestroDetalle debe usar TitledBorder en los paneles Productos y Detalle");
                v.dispose();
            });
        }
    }

    // ══════════════════════════════════════════════════════════════
    // T06 — DashboardIntegrado — R05 (15 pts)
    // ══════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("T06 - R05 DashboardIntegrado (15 pts)")
    class T06_Dashboard {

        @Test @DisplayName("T06.1 — DashboardIntegrado existe")
        void clase_existe() {
            assertDoesNotThrow(() ->
                Class.forName("sv.edu.ues.ape115.layouts.ui.DashboardIntegrado"));
        }

        @Test @DisplayName("T06.2 — DashboardIntegrado contiene JSplitPane")
        void tiene_splitpane() throws Exception {
            SwingUtilities.invokeAndWait(() -> {
                sv.edu.ues.ape115.layouts.ui.DashboardIntegrado d =
                    new sv.edu.ues.ape115.layouts.ui.DashboardIntegrado();
                assertTrue(tieneTipo(d, JSplitPane.class),
                    "DashboardIntegrado debe contener un JSplitPane en el CENTER");
                d.dispose();
            });
        }

        @Test @DisplayName("T06.3 — JTabbedPane tiene al menos 3 pestañas")
        void tabbedpane_tres_tabs() throws Exception {
            SwingUtilities.invokeAndWait(() -> {
                sv.edu.ues.ape115.layouts.ui.DashboardIntegrado d =
                    new sv.edu.ues.ape115.layouts.ui.DashboardIntegrado();
                JTabbedPane t = encontrarTipo(d, JTabbedPane.class);
                assertNotNull(t, "DashboardIntegrado debe tener un JTabbedPane");
                assertTrue(t.getTabCount() >= 3,
                    "JTabbedPane debe tener al menos 3 pestañas (Resumen, Productos, Actividad). Tiene: "
                    + t.getTabCount());
                d.dispose();
            });
        }

        @Test @DisplayName("T06.4 — DashboardIntegrado usa BoxLayout en el WEST")
        void usa_boxlayout() throws Exception {
            SwingUtilities.invokeAndWait(() -> {
                sv.edu.ues.ape115.layouts.ui.DashboardIntegrado d =
                    new sv.edu.ues.ape115.layouts.ui.DashboardIntegrado();
                assertTrue(tieneTipoLayout(d, BoxLayout.class),
                    "DashboardIntegrado debe usar BoxLayout(Y_AXIS) en el panel de módulos WEST");
                d.dispose();
            });
        }

        @Test @DisplayName("T06.5 — DashboardIntegrado tiene setMinimumSize >= 900×550")
        void tiene_tamano_minimo() throws Exception {
            SwingUtilities.invokeAndWait(() -> {
                sv.edu.ues.ape115.layouts.ui.DashboardIntegrado d =
                    new sv.edu.ues.ape115.layouts.ui.DashboardIntegrado();
                Dimension min = d.getMinimumSize();
                assertNotNull(min, "getMinimumSize() no debe ser null");
                assertTrue(min.width >= 900 && min.height >= 550,
                    "DashboardIntegrado debe tener setMinimumSize(≥900, ≥550). Es: " + min);
                d.dispose();
            });
        }
    }

    // ══════════════════════════════════════════════════════════════
    // T07 — Estándares del laboratorio (5 pts)
    // ══════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("T07 - Estándares del laboratorio (5 pts)")
    class T07_Estandares {

        @Test @DisplayName("T07.1 — Producto.java tiene los 5 campos (nombre, categoría, descripción, precio, stock)")
        void producto_tiene_cinco_campos() throws Exception {
            Class<?> cls = Class.forName("sv.edu.ues.ape115.layouts.model.Producto");
            long campos = Arrays.stream(cls.getDeclaredFields())
                .filter(f -> !f.isSynthetic()).count();
            assertTrue(campos >= 5,
                "Producto debe tener al menos 5 campos. Tiene: " + campos);
        }

        @Test @DisplayName("T07.2 — Producto tiene toString() que devuelve el nombre")
        void producto_tostring() {
            Producto p = new Producto("Laptop","Electrónica",899.0,10,"Desc");
            assertEquals("Laptop", p.toString(),
                "Producto.toString() debe retornar el nombre para mostrarse en JList/JComboBox");
        }

        @Test @DisplayName("T07.3 — Main.java existe en el paquete app")
        void main_existe() {
            assertDoesNotThrow(() ->
                Class.forName("sv.edu.ues.ape115.layouts.app.Main"),
                "Main.java debe estar en sv.edu.ues.ape115.layouts.app");
        }

        @Test @DisplayName("T07.4 — Ninguna clase de ui usa null layout (contentPane con layout)")
        void sin_null_layout() throws Exception {
            String[] clases = {
                "sv.edu.ues.ape115.layouts.ui.VentanaProductos",
                "sv.edu.ues.ape115.layouts.ui.VitrinaLayouts",
                "sv.edu.ues.ape115.layouts.ui.FormularioCliente",
                "sv.edu.ues.ape115.layouts.ui.AppMenuLateral",
                "sv.edu.ues.ape115.layouts.ui.VistaMaestroDetalle",
                "sv.edu.ues.ape115.layouts.ui.DashboardIntegrado",
            };
            for (String nombre : clases) {
                try {
                    Class<?> cls = Class.forName(nombre);
                    SwingUtilities.invokeAndWait(() -> {
                        try {
                            JFrame f = (JFrame) cls.getDeclaredConstructor().newInstance();
                            assertNotNull(f.getContentPane().getLayout(),
                                nombre + " tiene el ContentPane con null layout (setLayout(null))");
                            f.dispose();
                        } catch (Exception ex) {
                            fail("Error al instanciar " + nombre + ": " + ex.getMessage());
                        }
                    });
                } catch (ClassNotFoundException ignored) {
                    // Si la clase no existe, T01-T06 ya lo reportaron
                }
            }
        }
    }

    // ══════════════════════════════════════════════════════════════
    // Helpers de inspección de árbol de componentes
    // ══════════════════════════════════════════════════════════════

    /** Busca recursivamente el primer componente del tipo dado. */
    @SuppressWarnings("unchecked")
    private static <T extends Component> T encontrarTipo(Container c, Class<T> tipo) {
        if (tipo.isInstance(c)) return (T) c;
        for (Component child : c.getComponents()) {
            if (tipo.isInstance(child)) return (T) child;
            if (child instanceof Container) {
                T found = encontrarTipo((Container) child, tipo);
                if (found != null) return found;
            }
        }
        return null;
    }

    /** Devuelve true si existe al menos un componente del tipo dado. */
    private static boolean tieneTipo(Container c, Class<?> tipo) {
        return encontrarTipo(c, tipo) != null;
    }

    /** Devuelve true si algún contenedor usa el Layout Manager del tipo dado. */
    private static boolean tieneTipoLayout(Container root, Class<?> layoutTipo) {
        if (root.getLayout() != null && layoutTipo.isInstance(root.getLayout()))
            return true;
        for (Component child : root.getComponents()) {
            if (child instanceof Container ct && tieneTipoLayout(ct, layoutTipo))
                return true;
        }
        return false;
    }

    /** Devuelve true si algún JComponent tiene el tipo de borde indicado. */
    private static boolean tieneBordeTipo(Container c, Class<?> borderTipo) {
        if (c instanceof JComponent jc) {
            Border b = jc.getBorder();
            if (borderTipo.isInstance(b)) return true;
            if (b instanceof CompoundBorder cb) {
                if (borderTipo.isInstance(cb.getOutsideBorder())
                 || borderTipo.isInstance(cb.getInsideBorder())) return true;
            }
        }
        for (Component child : c.getComponents()) {
            if (child instanceof Container ct && tieneBordeTipo(ct, borderTipo))
                return true;
        }
        return false;
    }

    /** Cuenta cuántos componentes del tipo dado existen en el árbol. */
    private static long contarTipo(Container c, Class<?> tipo) {
        long n = tipo.isInstance(c) ? 1 : 0;
        for (Component child : c.getComponents()) {
            if (child instanceof Container ct) n += contarTipo(ct, tipo);
            else if (tipo.isInstance(child))   n++;
        }
        return n;
    }
}
