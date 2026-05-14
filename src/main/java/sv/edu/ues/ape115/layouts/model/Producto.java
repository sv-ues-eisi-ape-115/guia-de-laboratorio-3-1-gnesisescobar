package sv.edu.ues.ape115.layouts.model;

/**
 * Entidad Producto.
 *
 * POJO puro: sin imports de Swing.
 * NO modificar esta clase.
 *
 * @author APE 115 — UES
 */
public class Producto {

    private String nombre;
    private String categoria;
    private String descripcion;
    private double precio;
    private int    stock;

    public Producto(String nombre, String categoria,
                    double precio, int stock, String descripcion) {
        this.nombre      = nombre;
        this.categoria   = categoria;
        this.precio      = precio;
        this.stock       = stock;
        this.descripcion = descripcion;
    }

    public String getNombre()      { return nombre;      }
    public String getCategoria()   { return categoria;   }
    public double getPrecio()      { return precio;      }
    public int    getStock()       { return stock;       }
    public String getDescripcion() { return descripcion; }

    public void setNombre(String n)      { this.nombre = n;      }
    public void setCategoria(String c)   { this.categoria = c;   }
    public void setPrecio(double p)      { this.precio = p;      }
    public void setStock(int s)          { this.stock = s;       }
    public void setDescripcion(String d) { this.descripcion = d; }

    /** toString devuelve el nombre para que JList y JComboBox lo muestren. */
    @Override
    public String toString() { return nombre; }
}
