package Codigo;
import ucn.*;

/**
 * Clase de un instrumento Percusion
 */
public class Percusion extends Instrumento{

    /**
     * tipo de percusion del instrumento (Membranófono, idiófono))
     */
    private String tipoPercusion;
    /**
     * material del cual está construido el instrumento
     */
    private String materialConsutruccion;
    /**
     * altura que tiene el instrumento (definido, indefinido)
     */
    private String altura;

    /**
     * CONSTRUCTOR
     * @param codigo codigo del instrumento
     * @param precio precio del instrumento
     * @param stock cantidad de instrumentos del tipo hay
     * @param nombre nombre del instrumento (Bongo, Cajón, Campanas Tubulares, Bombo)
     * @param tipoPercusion tipo de percusion del instrumento (Membranófono, idiófono))
     * @param materialConsutruccion material del cual está construido el instrumento (madera, metal, piel))
     * @param altura altura que tiene el instrumento (si es definida o indefinida
     */
    public Percusion(int codigo, int precio, int stock, String nombre, String tipoPercusion, String materialConsutruccion, String altura) {
        super(codigo, precio, stock,nombre);

        this.tipoPercusion = tipoPercusion;
        this.materialConsutruccion = materialConsutruccion;
        this.altura = altura;
    }


    //TODO GETTERS Y SETTERS
}
