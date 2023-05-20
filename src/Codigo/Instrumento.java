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
     * @return el codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * metodo que retorna el precio
     * @return el precio
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * metodo que retorna el stock (cantidad de instrumentos)
     * @return el stock del instrumento
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

    /**
     * metodo que retona los datos del instrumento
     * @return
     */
    public String toString(){
        return "Nombre: " + this.nombre + " Código: " + this.codigo + " Stock: " + this.stock + " Precio: " + this.precio;
    }
}
