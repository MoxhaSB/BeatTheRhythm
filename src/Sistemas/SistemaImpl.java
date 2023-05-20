package Sistemas;
import Codigo.*;
import ucn.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SistemaImpl implements Sistema {

    /**
     * Lista de instrumentos
     */
    private ListaInstrumentos listaInstrumentos;
    /**
     * Archvivo el cual se leen instrumentos
     */
    //private ArchivoEntrada archivoEntrada;

    /**
     * CONSTRUCTOR
     */
    public SistemaImpl() throws IOException {

        this.listaInstrumentos = new ListaInstrumentos(500);
        //se muestra el menú
        menu();

    }

    /**
     * metodo que tiene el menu (el codigo )
     * @throws IOException error en lectura
     */
    public void menu() throws IOException {

        int cerrar = -1;
        int opcionInt = 0;

        while (cerrar !=0) {

            //menu con validacion
            while(true) {
                //validacion por si ingresan un caracter no numerico
                try {
                    StdOut.println("~~~~~Bienvenido~~~~~\n*¿Qué desea hacer?\n\n|1| Agregar Instrumento \n|2| Vender Instrumento \n|3| Consultar Inventario \n|4| Cierre \n");
                    String opcionString = StdIn.readString();
                    opcionInt = Integer.parseInt(opcionString);
                } catch (Exception e) {
                    StdOut.println("Ingrese un dato valido");
                    continue;
                }
                break;
            }

            //opciones con validacion
            switch (opcionInt) {

                case 1:
                    //se lee el archivo y se agrega a la lista.
                    if(leerArchivo()){
                        StdOut.println("Los productos se agregaron correctamente.");
                    }else{
                        StdOut.println("Los productos NO fueron agregados correctamente");
                    }

                    break;

                case 2:

                    venderInstrumento(); //TERMINADO
                    break;

                case 3:

                    consultarInventario(); //TERMINADO
                    break;

                case 4:

                    if(actualizarArchivo()){
                        StdOut.println("El archivo se actualizó correctamente");
                    }else{
                        StdOut.println("El archivo NO se pudo actualizar ");
                    }
                    cerrar = 0;
                    break;

                default:
                    StdOut.println("Ingrese una opción válida...\n");
            }



        }
    }

    /**
     * metodo que vende un instrumento
     */
    public void venderInstrumento(){

        int codigoInt = 0;
        boolean terminar = false;
        Instrumento instrumento = null;
        while(true) {
            //validacion por si ingresan un dato no numerico
            while(true) {

                try{
                 StdOut.println("Ingrese el código del instrumento a vender (000 para VOLVER)");
                    String codigoString = StdIn.readString();
                    codigoInt = Integer.parseInt(codigoString);

                    }catch (Exception e){
                     StdOut.println("Ingrese un codigo de numeros.");
                     continue;
                }
                break;
            }

            if(codigoInt == 000){
                terminar = true;
                break;

            }

            //creo un instrumento que será igual al instrumento que me devuelvan por su codigo
            instrumento = listaInstrumentos.obtener(codigoInt);

            if (instrumento == null) {
                StdOut.println("El producto no existe");
            }else{
                break;
            }
        }
        while(!terminar) {
            boolean buscar = listaInstrumentos.buscar(codigoInt);


            if (buscar) {

                if (listaInstrumentos.obtener(codigoInt).getStock() == 0) {
                    StdOut.println("El producto está agotado.");
                }
                listaInstrumentos.eliminar(codigoInt);


            } else {
                StdOut.println("El producto no existe\n");
                return;
            }

            //se crea la boleta
            boleta(instrumento.getPrecio(), instrumento);
            terminar = true;
        }
    }

    /**
     * metodo que devuelve una boleta
     * @return
     */
    public void boleta(int precio, Instrumento instrumento){

        StdOut.println("\n~ ~ ~ ~ ~ BOLETA ~ ~ ~ ~ ~ \n");
        StdOut.println("Producto vendido: \n|+| " + instrumento.getNombre());
        StdOut.println("Precio: $" + precio);
    }

    /**
     * metodo que lee el archivo instrumentos.csv
     * @throws IOException error al leer
     */
    public boolean leerArchivo() throws IOException {
        ArchivoEntrada archivoEntrada;

        //hacer validacion entrada del archivo (saber si existe)


        archivoEntrada = new ArchivoEntrada("instrumentos.csv");

        while (!archivoEntrada.isEndFile()) {

            Registro regEntrada = archivoEntrada.getRegistro();

            try {
                int codigo = regEntrada.getInt();
                int precio = regEntrada.getInt();
                int stock = regEntrada.getInt();
                String tipo = regEntrada.getString();
                String nombre = regEntrada.getString();

                switch (tipo) {

                    case "cuerda" -> {
                        String tipoCuerda = regEntrada.getString();
                        int numCuerdas = regEntrada.getInt();
                        String material = regEntrada.getString();
                        String tipoSonido = regEntrada.getString();
                        String altura = regEntrada.getString();

                        if(altura.equalsIgnoreCase("null")){
                            //no se agrega
                        }
                        Cuerda instrumentoCuerda = new Cuerda(codigo,precio,stock,nombre,tipoCuerda,numCuerdas,material,tipoSonido);
                        listaInstrumentos.agregar(instrumentoCuerda);
                    }

                    case "viento" -> {
                        String tipoCuerda = regEntrada.getString();
                        String numCuerdas = regEntrada.getString(); //lo tomo como String para compararlo con la palabra "null"
                        String material = regEntrada.getString();
                        String tipoSonido = regEntrada.getString();
                        String altura = regEntrada.getString();
                        if(tipoCuerda.equalsIgnoreCase("null") && numCuerdas.equalsIgnoreCase("null") && tipoSonido.equalsIgnoreCase("null") && altura.equalsIgnoreCase("null") ){
                            //ignorarlos y no agregarlos al crear isnturmento
                        }

                        Viento instrumentoViento = new Viento(codigo,precio,stock,nombre,material);
                        listaInstrumentos.agregar(instrumentoViento);
                    }

                    case "percusion" -> {

                        String tipoCuerda = regEntrada.getString();
                        String numCuerdas = regEntrada.getString(); //lo tomo como String para compararlo con la palabra "null"
                        String material = regEntrada.getString();
                        String tipoSonido = regEntrada.getString();
                        String altura = regEntrada.getString();
                        if(tipoCuerda.equalsIgnoreCase("null") && numCuerdas.equalsIgnoreCase("null")){
                            //solo se ignoran y no se agregan
                        }
                        Percusion instrumentoPercusion = new Percusion(codigo,precio,stock,nombre,tipoSonido,material,altura);
                        listaInstrumentos.agregar(instrumentoPercusion);
                    }

                    default -> StdOut.println("El producto no se pudo agregar");
                }
            } catch (Exception e) {
                StdOut.print("|ERROR| Producto con campos invalidos");
            }
        }

        return true;
    }

    /**
     * Método que muestra el inventario
     */
    public void consultarInventario(){

        int opcionInt = -1;
        boolean terminar = false;
        while(!terminar) {

            try {
                StdOut.println("Consultar Inventario:\n");
                StdOut.print("|1| Desplegar los instrumentos\n|2| Buscar instrumento por Codigo\n|3| volver\nIngrese una opcion: ");
                String opcionString = StdIn.readString();
                opcionInt = Integer.parseInt(opcionString);

            } catch (Exception e) {
                StdOut.println("Ingrese una opcion valida.\n");
                continue;
            }

            switch (opcionInt) {

                case 1: //desplegar instrumentos

                    listaInstrumentos.desplegarInstrumentos();
                    break;

                case 2: // buscar instrumento


                    while (true) {
                        int codigo = -1;
                        while (true) {
                            try {
                                StdOut.println("Ingrese el codigo del instrumento (000 PARA VOLVER)");
                                String codigoString = StdIn.readString();
                                codigo = Integer.parseInt(codigoString);

                            } catch (Exception e) {
                                StdOut.println("|ERROR| Los codigos son numericos");
                                continue;
                            }
                            break;
                        }
                        if(codigo == 000){
                            break;
                        }
                        if (listaInstrumentos.buscar(codigo)) {
                            listaInstrumentos.obtener(codigo).toString();
                            break;

                        } else if (!listaInstrumentos.buscar(codigo)) {
                            StdOut.println("El instrumento no existe");
                        }
                    }
                    break;

                case 3: //volver
                    terminar =true;

                    break;

                default:
                    StdOut.println("Ingrese una opción válida");
            }

        }
    }

    /**
     * metodo que actualiza el archivo
     * @throws IOException error al leer
     */
    public boolean actualizarArchivo() throws IOException {

        ArchivoSalida archivoSalida;

        archivoSalida = new ArchivoSalida("instrumentos.csv");

        for (int i = 0; i < listaInstrumentos.getCantidadActual() ; i++) {
            Registro registroSalida = new Registro(10);

            /*
             int codigo = regEntrada.getInt();
                int precio = regEntrada.getInt();
                int stock = regEntrada.getInt();
                String tipo = regEntrada.getString();
                String nombre = regEntrada.getString();
             */

            if (listaInstrumentos.entregarInstrumento(i) instanceof  Cuerda) {
                registroSalida.agregarCampo(listaInstrumentos.entregarInstrumento(i).getCodigo()); //codigo
                registroSalida.agregarCampo(listaInstrumentos.entregarInstrumento(i).getPrecio()); //precio
                registroSalida.agregarCampo(listaInstrumentos.entregarInstrumento(i).getStock()); //stock
                registroSalida.agregarCampo("Cuerda"); //tipo es Cuerda ya que se instancio como cuerda
                registroSalida.agregarCampo(listaInstrumentos.entregarInstrumento(i).getNombre()); //nombre
                registroSalida.agregarCampo(((Cuerda) listaInstrumentos.entregarInstrumento(i)).getTipoCuerda()); //tipo cuerda
                registroSalida.agregarCampo(((Cuerda) listaInstrumentos.entregarInstrumento(i)).getNumCuerdas()); //numero de cuerdas
                registroSalida.agregarCampo(((Cuerda) listaInstrumentos.entregarInstrumento(i)).getMaterialConstruccion()); //material
                registroSalida.agregarCampo(((Cuerda) listaInstrumentos.entregarInstrumento(i)).getTipo()); //tipo sonido
                registroSalida.agregarCampo("null"); //altura null ya que no posee esa cualidad


            } else if (listaInstrumentos.entregarInstrumento(i) instanceof Viento) {
                registroSalida.agregarCampo(listaInstrumentos.entregarInstrumento(i).getCodigo()); //codigo
                registroSalida.agregarCampo(listaInstrumentos.entregarInstrumento(i).getPrecio()); //precio
                registroSalida.agregarCampo(listaInstrumentos.entregarInstrumento(i).getStock()); //stock
                registroSalida.agregarCampo("Viento"); //tipo es Viento ya que se instancio como viento
                registroSalida.agregarCampo(listaInstrumentos.entregarInstrumento(i).getNombre()); //nombre
                registroSalida.agregarCampo("null"); //tipo cuerda no tienen los insturmentos de viento
                registroSalida.agregarCampo("null"); //numero de cuerdas no tienen los instrumentos de viento
                registroSalida.agregarCampo(((Viento) listaInstrumentos.entregarInstrumento(i)).getMaterialConstruccion()); //material
                registroSalida.agregarCampo("null"); //tipo sonido no tiene
                registroSalida.agregarCampo("null"); //altura null ya que no posee esa cualidad



            } else if (listaInstrumentos.entregarInstrumento(i) instanceof  Percusion) {
                registroSalida.agregarCampo(listaInstrumentos.entregarInstrumento(i).getCodigo()); //codigo
                registroSalida.agregarCampo(listaInstrumentos.entregarInstrumento(i).getPrecio()); //precio
                registroSalida.agregarCampo(listaInstrumentos.entregarInstrumento(i).getStock()); //stock
                registroSalida.agregarCampo("Percusion"); //tipo es Percusion ya que se instancio como Percusion
                registroSalida.agregarCampo(listaInstrumentos.entregarInstrumento(i).getNombre()); //nombre
                registroSalida.agregarCampo("null"); //tipo cuerda no tienen los isntrumentos de percusion
                registroSalida.agregarCampo("null"); //numero de cuerdas no tienen los isntrumentos de percusion
                registroSalida.agregarCampo(((Percusion) listaInstrumentos.entregarInstrumento(i)).getMaterialConsutruccion()); //material
                registroSalida.agregarCampo(((Percusion) listaInstrumentos.entregarInstrumento(i)).getTipoPercusion()); //tipo sonido
                registroSalida.agregarCampo(((Percusion) listaInstrumentos.entregarInstrumento(i)).getAltura()); //altura

            }
            //se escribe el registro en el archivo de salida
            archivoSalida.writeRegistro(registroSalida);

        }

        return true;
    }









}
