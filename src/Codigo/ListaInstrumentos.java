package Codigo;

import ucn.StdOut;

public class ListaInstrumentos {


    /**
     * cantidad actual de instrumentos en la lista
     */
    private int cantidadActual;
    /**
     * cantidad maxima de instrumentos por lista
     */
    private int cantidadMaxima;
    /**
     * la lista de los instrumentos
     */
    private Instrumento listaInstrumento[];


    /**
     * CONSTRUCTOR
     * @param cantidadMaxima la cantidad maxima que puede tener la lista
     */
    public ListaInstrumentos(int cantidadMaxima) {

        //se  crea la lista de tipo instrumento
        this.listaInstrumento = new Instrumento[cantidadMaxima];

        //se guarda la cantidad maxima de la lista ingresada
        this.cantidadMaxima = cantidadMaxima;

        //cantidad inicial de la lista
        this.cantidadActual = 0;

    }

    /**
     * metodo que obtiene un instrumento
     * @param codigo codigo del instrumento
     * @return devuelve el instrumento, si no devuelve null
     */
    public Instrumento obtener (int codigo){

        for (int i = 0; i < this.listaInstrumento.length; i++) {

            Instrumento instrumento = this.listaInstrumento[i];

            if (instrumento.getCodigo() == codigo){
                return instrumento;
            }

        }

        return null;
    }

    /**
     * metodo que busca un objeto por su codigo
     * @param codigo codigo del producto
     * @return true si existe, false si no
     */
    public boolean buscar(int codigo){

        //busca un codigo, si existe devuelve true
        for (int j = 0; j < cantidadActual; j++) {

            if (listaInstrumento[j].getCodigo() == codigo){
                return true;
            }
        }
        //si no existe devuelve false
        return false;
    }

    /**
     * metodo que elimina un instrumento
     * @return true si se elimina correctamente, false si no se pud oeliminar
     */
    public boolean eliminar (int codigo){

        buscar(codigo);
        if (true) {

            for (int i = 0; i < cantidadActual; i++) {
                listaInstrumento[i] = listaInstrumento[i +  1];
            }
            cantidadActual--;
            return true;
        }else
            return false;
    }

    /**
     * metodo que agrega un instrumento
     * @param instrumento producto que se agrega
     */
    public void agregar(Instrumento instrumento){

        //validacion por si la lista está llena.
        if(this.cantidadActual >= this.cantidadMaxima){
            throw new IllegalArgumentException("La lista está llena.");

        }

        //si la lista no está llena, se agrega el instrumento
        this.listaInstrumento[this.cantidadActual] = instrumento;
        this.cantidadActual++;

    }

    /**
     * metodo que retorna la cantidad actual de instrumentos
     * @return retorna la cantidad actual de instrumentos
     */
    public int getCantidadActual() {
        return cantidadActual;
    }

    /**
     * Metodo que despliega los instrumentos
     */
    public void desplegarInstrumentos(){

        //verificar instancia del instrumento TODO AAAAAAAAAAAAAAAAAAA
        for (int i = 0; i <this.cantidadActual ; i++) {


            if (this.listaInstrumento[i] instanceof Cuerda) {
                StdOut.println("|CUERDA| :\n");
                StdOut.println(this.listaInstrumento[i].toString());

            } else if (this.listaInstrumento[i] instanceof Viento) {
                StdOut.println("|VIENTO| :\n");
                StdOut.println(this.listaInstrumento[i].toString());

            } else if (this.listaInstrumento[i] instanceof Percusion) {
                StdOut.println("|PERCUSION| :\n");
                StdOut.println(this.listaInstrumento[i].toString());

            }


        }

    }

}
