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
    private ArchivoEntrada archivoEntrada;

    /**
     * CONSTRUCTOR
     */
    public SistemaImpl() {

        this.listaInstrumentos = new ListaInstrumentos(500);
        //se muestra el menú
        menu();

    }

    /**
     * metodo que contiene el menu
     */
    // TODO TERMINAR EL MENU
    public void menu() {

        int cerrar = -1;
        int opcionInt = 0;

        while (cerrar !=0) {

            //validacion por si ingresan un caracter no numerico
            try {
                StdOut.println("~~~~~Bienvenido~~~~~\n*¿Qué desea hacer?\n\n|1| Agregar Instrumento \n|2| Vender Instrumento \n|3| Consultar Inventario \n|4| Cierre \n");
                String opcionString = StdIn.readString();
                opcionInt = Integer.parseInt(opcionString);
            }catch (Exception e){
                StdOut.println("Ingrese un dato valido");
                continue;
            }

            switch (opcionInt) {

                case 1:
                    int salirAgregarInstrumento = 1;

                    if(agregarInstrumentos(salirAgregarInstrumento) == -1 ){
                        break;
                    }
                    break;

                case 2:

                    venderInstrumento();
                    break;

                case 3:

                    consultarInventario();
                    break;

                case 4:

                    //TODO AL CERRAR SE DEBE ACTUALIZAR EL STOCK, POR LO TANTO EL ARCHIVO
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

        //validacion por si ingresan un dato no numerico
        while(true) {

            try{
            StdOut.println("Ingrese el código del instrumento a vender");
            String codigoString = StdIn.readString();
            codigoInt = Integer.parseInt(codigoString);

            }catch (Exception e){
                StdOut.println("Ingrese un codigo de numeros.");
            }
            break;
        }

        //creo un instrumento que será igual al instrumento que me devuelvan por su codigo
        Instrumento instrumento = listaInstrumentos.obtener(codigoInt);
        boolean buscar = listaInstrumentos.buscar(codigoInt);


        if(buscar){

            if(listaInstrumentos.obtener(codigoInt).getStock() == 0){
                StdOut.println("El producto está agotado.");
            }
            listaInstrumentos.eliminar(codigoInt);



        }else{
            StdOut.println("El producto no existe\n");
            return;
        }

        //se crea la boleta
        boleta(instrumento.getPrecio(),instrumento );

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
     * Metodo que lee el archivo .csv
     */
    public void leerArchivo() throws IOException {


       /*
    codigo= 0,1234567890
    precio = 100
    stock= 10
    tipo=cuerda
    instrumento(nombre) = guitarra
    tipoCuerda = nylon
    cantidadCuerdas = 6
    material = madera
    tipoSonido = electrico
    altura = null
     */
        //todo dentro hay que colocar el poner el agregar
        archivoEntrada = new ArchivoEntrada("instrumentos.csv");

        while(!archivoEntrada.isEndFile()){
            //no recordaba que se ponia para leerlo xD
            Registro registro = new Registro(2);
            int codigo =7; //le puse valores al azar para que no saliera error
            int precio =5;
            int stock =5;
            String tipo = "f";
            String nombre = "a";

            /*esto te sirve, si el nombre con el que se ingresó es cuerda, viento o percusion, tiene que omitir los
            espacios, ya que no todos tienen los mismo atributos, y ahi mismo creas los objetos y los agregas

             */
            if(tipo.equalsIgnoreCase("cuerda")){


            } else if (tipo.equalsIgnoreCase("viento")) {


            } else if (tipo.equalsIgnoreCase("percusion")) {


            }

        }


    }

    /**
     * Método que muestra el inventario
     */
    public void consultarInventario(){
        listaInstrumentos.desplegarInstrumentos();
    }

    /**
     * Metodo que agrega un instrumento
     */
    public int agregarInstrumentos(int salirAgregarInstrumento){

        StdOut.println("Agregar Instrumento:\n");
        StdOut.println("~~~~~¿Qué tipo de instrumento desea agregar?~~~~~");
        StdOut.println("|1| Instrumento de CUERDA\n|2| Instrumento de VIENTO \n|3| Instrumento de PERCUSION\n|4| Volver atrás");
        int opcionInstrumento = StdIn.readInt();

        switch (opcionInstrumento){

            case 1: //CUERDA
                StdOut.println("\nIngrese el codigo: ");
                int codigoCuerda = StdIn.readInt();
                StdOut.println("Ingrese el precio: \n Precio: $");
                int precioCuerda = StdIn.readInt();
                StdOut.println("Ingrese el stock del instrumento: ");
                int stockCuerda = StdIn.readInt();
                StdOut.println("Ingrese el nombre del instrumento: ");
                String nombreCuerda = StdIn.readString();
                StdOut.println("Ingrese el tipo de cuerda: ");
                String tipoCuerda = StdIn.readString();
                StdOut.println("Ingrese el numero de cuerdas: ");
                int numCuerdas = StdIn.readInt();
                StdOut.println("Ingrese el material del cual está construido el instrumento: ");
                String materialConstruccionCuerda = StdIn.readString();
                StdOut.println("Ingrese el tipo de instrumento (acustico o electrico): ");
                String acusticoElectrico = StdIn.readString();

                //se crea el instrumento y se guarda en la lista
                Cuerda instrumentoCuerda = new Cuerda(codigoCuerda,precioCuerda,stockCuerda,nombreCuerda,tipoCuerda,numCuerdas,materialConstruccionCuerda,acusticoElectrico);
                listaInstrumentos.agregar(instrumentoCuerda);

                break;

            case 2: //VIENTO

                System.out.println("\nIngrese el codigo: ");
                int codigoViento = StdIn.readInt();
                System.out.print("Ingrese el precio: \n Precio: $");
                int precioViento = StdIn.readInt();
                System.out.println("Ingrese el stock del instrumento: ");
                int stockViento = StdIn.readInt();
                System.out.println("Ingrese el nombre del instrumento: ");
                String nombreViento = StdIn.readString();
                System.out.println("Ingrese el material del cual está construido el instrumento: ");
                String materialConstruccionViento = StdIn.readString();

                //se crea el instrumento y se guarda en la lista
                Viento instrumentoViento = new Viento(codigoViento,precioViento,stockViento,nombreViento,materialConstruccionViento);
                listaInstrumentos.agregar(instrumentoViento);

                break;

            case 3: // PERCUSION
                System.out.println("\nIngrese el codigo: ");
                int codigoPercusion = StdIn.readInt();
                System.out.println("Ingrese el precio: \n Precio: $");
                int precioPercusion = StdIn.readInt();
                System.out.println("Ingrese el stock del instrumento: ");
                int stockPercusion = StdIn.readInt();
                System.out.println("Ingrese el nombre del instrumento: ");
                String nombrePercusion = StdIn.readString();
                System.out.println("Ingrese el tipo de percusion del instrumento (Membranófono o idiófono): ");
                String tipoPercusion = StdIn.readString();
                System.out.println("Ingrese el material del cual está construido el instrumento: ");
                String materialConstruccionPercusion = StdIn.readString();
                System.out.println("Ingrese la altura del instrumento (definida o indefinida)");
                String altura = StdIn.readString();

                //se crea el instrumento y se guarda en la lista
                Percusion instrumentoPercusion = new Percusion(codigoPercusion,precioPercusion,stockPercusion,nombrePercusion,tipoPercusion,materialConstruccionPercusion,altura);
                listaInstrumentos.agregar(instrumentoPercusion);
                break;

            case 4: //VOLVER ATRAS
                salirAgregarInstrumento = -1;


                break;

            default:
                StdOut.println("\nIngrese una opción válida.");
        }
        return  salirAgregarInstrumento;

    }






}
