package Codigo;
import ucn.*;

/**
 * Clase de un instrumento de cuerda
 */
public class Cuerda extends Instrumento{

    /**
     * tipo de cuerda que tiene el instrumento
     */
    private String tipoCuerda;
    /**
     * cantidad de cuerdas que tiene el instrumento
     */
    private int numCuerdas;
    /**
     * material del cal está hecho el instrumento
     */
    private String materialConstruccion;
    /**
     * tipo de instrumento, si es acustico o electrico
     */
    private String tipo;

    /**
     * CONSTRUCTOR
     * @param codigo el codigo del instrumento
     * @param precio el precio del instrumento
     * @param stock cantidad de instrumentos que hay
     * @param nombre el nombre del instrumento (Guitarra, Bajo, Violín, Arpa)
     * @param tipoCuerda el tipo de cuerda que usa el instrumento (Nylon, acero, tripa)
     * @param numCuerdas cantidad de cuerdas que usa el instrumento
     * @param materialConstruccion material del cual está hecho el instrumento (madera, metal)
     * @param tipo tipo de instrumento (acústico, eléctrico)
     */
    public Cuerda(int codigo, int precio, int stock, String nombre, String tipoCuerda, int numCuerdas, String materialConstruccion, String tipo) {
        super(codigo, precio, stock,nombre);
        this.tipoCuerda = tipoCuerda;
        this.numCuerdas = numCuerdas;
        this.materialConstruccion = materialConstruccion;
        this.tipo = tipo;

    }

    /**
     * metodo que retorna el tipo de cuerda del instrumento
     * @return el tipo de cuerda
     */
    public String getTipoCuerda() {
        return tipoCuerda;
    }

    /**
     * metodo que retorna el numero de cuerda del instrumento
     * @return numero de cuerdas
     */
    public int getNumCuerdas() {
        return numCuerdas;
    }

    /**
     * metodo que retorna el material de construccion del instrumento
     * @return material de construccion de instrumento
     */
    public String getMaterialConstruccion() {
        return materialConstruccion;
    }

    /**
     * metodo que retorna el tipo del instrumento
     * @return tipo de instrumento
     */
    public String getTipo() {
        return tipo;
    }
}
