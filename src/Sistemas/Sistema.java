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

    /**
     * metodo que da opcion de mostrar los instrumentos
     */
    void consultarInventario();

    /**
     * metodo que actualiza el archivo para cerrar el programa
     * @return true si se pudo actualizar, false si no
     * @throws IOException si hubo un error
     */
    boolean cierre() throws IOException;

    /**
     * metodo que lee el archivo para guardar los instrumentos en la lista
     * @return true si se pudo leer correctamente, false si no
     * @throws IOException si hubo un error al leer
     */
    boolean agregarInstrumentos() throws IOException;


}
