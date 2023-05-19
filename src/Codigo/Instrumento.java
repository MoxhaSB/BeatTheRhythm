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
     * metodo que retona los datos del instrumento
     * @return
     */
    public String toString(){
        return "Nombre: " + this.nombre + " Código: " + this.codigo + " Stock: " + this.stock + " Precio: " + this.precio;
    }
}
