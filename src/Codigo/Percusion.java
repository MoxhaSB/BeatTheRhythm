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

    /**
     * metodo que retorna el tipo de percusion del instrumento
     * @return tipo de percusion
     */
    public String getTipoPercusion() {
        return tipoPercusion;
    }

    /**
     * metodo que retorna el material del cual se construyo el instrumento
     * @return material del insturmento
     */
    public String getMaterialConsutruccion() {
        return materialConsutruccion;
    }

    /**
     * metodo que retorna si la altura del instrumento
     * @return definida o indefinida
     */
    public String getAltura() {
        return altura;
    }

    /**
     * metodo que devuelve los datos del instrumento percusion de forma ordenada
     * @return datos del instrumento
     */
    public String toStringPercusion(){
        return " | Tipo Percusion: " + this.tipoPercusion + " | Material: " + this.materialConsutruccion + " | Altura: " + this.altura;
    }
}
