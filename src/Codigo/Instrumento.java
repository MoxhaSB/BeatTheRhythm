package Codigo;
import ucn.*;

/**
 * clase padre de instrumentos
 */
public class Instrumento {

    private String nombre;
    /**
     * codigo del instrumento
     */
    private int codigo;
    /**
     * precio del instrumento
     */
    private int precio;
    /**
     * cantidad de instrumentos
     */
    private int stock;

    /**
     * Constructor
     * @param codigo codigo del instrumento
     * @param precio precio del instrumento
     * @param stock stock del instrumento
     * @param nombre nombre del insturmento
     */
    public Instrumento(int codigo, int precio, int stock, String nombre) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.precio = precio;
        this.stock = stock;
    }

    /**
     * metodo que retorna el codigo
     * @return
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * metodo que modifica un codigo
     * @param codigo
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * metodo que retorna el precio
     * @return
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * metodo que cambia el precio
     * @param precio
     */
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    /**
     * metodo que retorna el stock (cantidad de instrumentos)
     * @return
     */
    public int getStock() {
        return stock;
    }

    /**
     * metodo qque retorna el nombre
     * @return nombre del instrumento
     */
    public String getNombre(){
        return this.nombre;
    }

    public String toString(){
        return "Nombre: " + this.nombre +
                "\nCódigo: " + this.codigo +
                "\nStock: " + this.stock +
                "\nPrecio: " + this.precio;
    }
}
