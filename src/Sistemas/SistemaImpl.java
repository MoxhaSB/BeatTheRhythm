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
                    StdOut.println("\n~~~~~Bienvenido~~~~~\n*¿Qué desea hacer?\n\n|1| Agregar Instrumento \n|2| Vender Instrumento \n|3| Consultar Inventario \n|4| Cierre \n");
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
                    if(agregarInstrumentos() && listaInstrumentos.getCantidadActual()!=0){
                        StdOut.println("Los productos se agregaron correctamente.");
                    }else{
                        StdOut.println(" Los productos NO fueron agregados");
                    }

                    break;

                case 2:

                    venderInstrumento();
                    break;

                case 3:

                    consultarInventario();
                    break;

                case 4:

                    if(cierre()){
                        StdOut.println("El archivo se actualizó correctamente");
                    }else{
                        StdOut.println("// El archivo NO se pudo actualizar ");
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
                 StdOut.println("Ingrese el código del instrumento a vender (0 para VOLVER)");
                    String codigoString = StdIn.readString();
                    codigoInt = Integer.parseInt(codigoString);

                    }catch (Exception e){
                     StdOut.println("Ingrese un codigo de numeros\n");
                     continue;
                }
                break;
            }

            if(codigoInt == 0){
                terminar = true;
                break;

            }

            //creo un instrumento que será igual al instrumento que me devuelvan por su codigo
            instrumento = listaInstrumentos.obtener(codigoInt);

            if (instrumento == null) {
                StdOut.println("El producto no existe\n");
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
                //al vender un instrumento se le quita 1 a su stock
                listaInstrumentos.obtener(codigoInt).setStock(listaInstrumentos.obtener(codigoInt).getStock()-1);


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
     * metodo que despliega los instrumentos segundo su instancia
     * @param cantidadActual la cantidad actual de instrumentos
     */
    public void desplegarInstrumentos(int cantidadActual) {

        //verificar instancia del instrumento
        if(cantidadActual!=0) {
            for (int i = 0; i < cantidadActual; i++) {

                if (listaInstrumentos.entregarInstrumento(i) instanceof Cuerda) {
                    StdOut.println("|CUERDA| :");
                    StdOut.println(listaInstrumentos.entregarInstrumento(i).toString() + ((Cuerda) listaInstrumentos.entregarInstrumento(i)).toStringCuerda());

                } else if (listaInstrumentos.entregarInstrumento(i) instanceof Viento) {
                    StdOut.println("|VIENTO| :");
                    StdOut.println(listaInstrumentos.entregarInstrumento(i).toString() + ((Viento) listaInstrumentos.entregarInstrumento(i)).toStringViento());

                } else if (listaInstrumentos.entregarInstrumento(i) instanceof Percusion) {
                    StdOut.println("|PERCUSION| :");
                    StdOut.println(listaInstrumentos.entregarInstrumento(i).toString() + ((Percusion) listaInstrumentos.entregarInstrumento(i)).toStringPercusion());
                }
            }
        }else{
            StdOut.println("No hay instrumentos");
        }
    }

    /**
     * metodo que devuelve una boleta
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
    public boolean agregarInstrumentos() throws IOException {

        ArchivoEntrada archivoEntrada;

        //validacion de existencia del archivo
        try{
        archivoEntrada = new ArchivoEntrada("instrumentos.csv");

        }catch (Exception e){
            StdOut.print("El archivo no existe ");
            return false;
        }


        while (!archivoEntrada.isEndFile()) {

            Registro regEntrada = archivoEntrada.getRegistro();

            try {
                int codigo = regEntrada.getInt();
                int precio = regEntrada.getInt();
                int stock = regEntrada.getInt();
                String tipo = regEntrada.getString();
                String nombre = "";

                switch (tipo) {

                    case "Cuerda" -> {

                        nombre = regEntrada.getString();
                        String tipoCuerda = regEntrada.getString();
                        int numCuerdas = regEntrada.getInt();
                        String material = regEntrada.getString();
                        String tipoSonido = regEntrada.getString();
                        String altura = regEntrada.getString();

                        //validacion de datos de un instrumento de cuerda
                        if(nombre.equalsIgnoreCase("guitarra")||nombre.equalsIgnoreCase("bajo")||nombre.equalsIgnoreCase("violin")||nombre.equalsIgnoreCase("arpa")){
                            if(tipoCuerda.equalsIgnoreCase("nylon")||tipoCuerda.equalsIgnoreCase("acero")||tipoCuerda.equalsIgnoreCase("tripa")){
                                if(material.equalsIgnoreCase("madera") || material.equalsIgnoreCase("metal")){
                                    if(tipoSonido.equalsIgnoreCase("electrico") || tipoSonido.equalsIgnoreCase("acustico")){
                                        if(altura.equalsIgnoreCase("null")){
                                            //se crea el instrumento y se guarda
                                            Cuerda instrumentoCuerda = new Cuerda(codigo,precio,stock,nombre,tipoCuerda,numCuerdas,material,tipoSonido);
                                            listaInstrumentos.agregar(instrumentoCuerda);
                                        }
                                    }
                                }
                            }
                        }


                    }

                    case "Viento" -> {

                        nombre = regEntrada.getString();
                        String tipoCuerda = regEntrada.getString();
                        String numCuerdas = regEntrada.getString(); //lo tomo como String para compararlo con la palabra "null"
                        String material = regEntrada.getString();
                        String tipoSonido = regEntrada.getString();
                        String altura = regEntrada.getString();

                        //validacion de datos de un instrumento de viento
                        if(nombre.equalsIgnoreCase("trompeta")||nombre.equalsIgnoreCase("saxofon")||nombre.equalsIgnoreCase("clarinete")|| nombre.equalsIgnoreCase("flauta traversa")){
                            if(material.equalsIgnoreCase("madera")||material.equalsIgnoreCase("metal")){
                                if(tipoCuerda.equalsIgnoreCase("null") && numCuerdas.equalsIgnoreCase("null") && tipoSonido.equalsIgnoreCase("null") && altura.equalsIgnoreCase("null") ){
                                    //se crea el instrumento y se guarda
                                    Viento instrumentoViento = new Viento(codigo,precio,stock,nombre,material);
                                    listaInstrumentos.agregar(instrumentoViento);
                                }
                            }
                        }
                    }

                    case "Percusion" -> {

                        nombre = regEntrada.getString();
                        String tipoCuerda = regEntrada.getString();
                        String numCuerdas = regEntrada.getString(); //lo tomo como String para compararlo con la palabra "null"
                        String material = regEntrada.getString();
                        String tipoSonido = regEntrada.getString();
                        String altura = regEntrada.getString();

                        //validacion datos para un insturmento de percusion
                        if(nombre.equalsIgnoreCase("bongo")||nombre.equalsIgnoreCase("cajon")|nombre.equalsIgnoreCase("campanas tubulares")||nombre.equalsIgnoreCase("bombo")){
                            if(tipoSonido.equalsIgnoreCase("membranofono")||tipoSonido.equalsIgnoreCase("idiofono")){
                                if(material.equalsIgnoreCase("madera")||material.equalsIgnoreCase("metal")||material.equalsIgnoreCase("piel")){
                                    if(altura.equalsIgnoreCase("definida")||altura.equalsIgnoreCase("indefinida")){
                                        if(tipoCuerda.equalsIgnoreCase("null") && numCuerdas.equalsIgnoreCase("null")){
                                            //se crea el objeto y se guarda
                                            Percusion instrumentoPercusion = new Percusion(codigo,precio,stock,nombre,tipoSonido,material,altura);
                                            listaInstrumentos.agregar(instrumentoPercusion);
                                        }
                                    }
                                }
                            }
                        }

                    }

                    default -> StdOut.println("El instrumento " + nombre + " no se pudo agregar");
                }
            } catch (Exception e) {
                StdOut.print("|ERROR| Producto con campos invalidos. ");
                return false;
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
                StdOut.println("\nConsultar Inventario:\n");
                StdOut.println("|1| Desplegar los instrumentos\n|2| Buscar instrumento por Codigo\n|3| volver\nIngrese una opcion: ");
                String opcionString = StdIn.readString();
                opcionInt = Integer.parseInt(opcionString);

            } catch (Exception e) {
                StdOut.println("Ingrese una opcion valida.\n");
                continue;
            }

            switch (opcionInt) {

                case 1: //desplegar instrumentos

                    desplegarInstrumentos(listaInstrumentos.getCantidadActual());

                    break;

                case 2: // buscar instrumento


                    while (true) {
                        int codigo = -1;
                        while (true) {
                            try {
                                StdOut.println("\nIngrese el codigo del instrumento (0 PARA VOLVER)");
                                String codigoString = StdIn.readString();
                                codigo = Integer.parseInt(codigoString);

                            } catch (Exception e) {
                                StdOut.println("|ERROR| Los codigos son numericos\n");
                                continue;
                            }
                            break;
                        }
                        if(codigo == 0){
                            break;
                        }
                        if (listaInstrumentos.buscar(codigo)) {
                            if(listaInstrumentos.obtener(codigo) instanceof Cuerda){
                                Instrumento ins = listaInstrumentos.obtener(codigo);
                                Cuerda cuerda = (Cuerda) ins;
                                StdOut.println("|CUERDA| \n" +listaInstrumentos.obtener(codigo).toString() + cuerda.toStringCuerda());

                            } else if (listaInstrumentos.obtener(codigo) instanceof Viento) {
                                Instrumento ins2 = listaInstrumentos.obtener(codigo);
                                StdOut.println(ins2.toString());
                                Viento viento = (Viento) ins2;
                                StdOut.println("|VIENTO| \n" + listaInstrumentos.obtener(codigo).toString() +viento.toStringViento());

                            } else if (listaInstrumentos.obtener(codigo) instanceof Percusion) {
                                Instrumento ins3 = listaInstrumentos.obtener(codigo);
                                StdOut.println(ins3.toString());
                                Percusion percusion = (Percusion) ins3;
                                StdOut.println("|PERCUSION| \n" + listaInstrumentos.obtener(codigo).toString()+ percusion.toStringPercusion());
                            }


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
    public boolean cierre() throws IOException {

        ArchivoSalida archivoSalida;

        try {
            archivoSalida = new ArchivoSalida("instrumentos.csv");

            for (int i = 0; i < listaInstrumentos.getCantidadActual(); i++) {
                Registro registroSalida = new Registro(10);

                if (listaInstrumentos.entregarInstrumento(i) instanceof Cuerda) {
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


                } else if (listaInstrumentos.entregarInstrumento(i) instanceof Percusion) {
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
        }catch(Exception e){
            StdOut.print("Hubo un error al crear el archivo ");
            return false;
        }
    }












}
