package Sistemas;
import ucn.*;

import java.io.IOException;

/**
 * La interface de la aplicacion
 */
public interface Sistema {

    /**
     * metodo donde se encuentra el menu
     * @throws IOException error al leer
     */
    void menu() throws IOException;
    /**
     * metodo que vende un instrumento
     */
    void venderInstrumento();


}
