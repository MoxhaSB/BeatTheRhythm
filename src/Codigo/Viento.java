package Codigo;
import ucn.*;

/**
 * Clase de un instrumento Viento
 */
public class Viento extends Instrumento{

    /**
     *material del que está hecho el instrumento
     */
    private String materialConstruccion;

    /**
     * CONSTRUCTOR
     * @param codigo codigo del instrumento
     * @param precio precio del instrumento
     * @param stock cantidad del tipo de instrumento
     * @param nombre nombre del instrumento (Trompeta, Saxofón, Clarinete, Flauta traversa)
     * @param materialConstruccion material del que está hecho el instrumento (madera, metal)
     */
    public Viento(int codigo, int precio, int stock, String nombre, String materialConstruccion) {
        super(codigo, precio, stock,nombre);
        this.materialConstruccion = materialConstruccion;
    }

    /**
     * Metodo que devuelve el material de construccion del instrumento
     * @return el material de construccion
     */
    public String getMaterialConstruccion() {
        return materialConstruccion;
    }
}
