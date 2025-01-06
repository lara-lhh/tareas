public class tareas {
    public static AsignacionTareas asignacionTareas = new AsignacionTareas();

    public static void main(String[] args) {
        String nombreFicheroEntrada = null, nombreFicheroSalida = null;
        boolean traza = false;
        ProbarTareas juego = new ProbarTareas();
        if (args.length == 0) {
            nombreFicheroEntrada = null;
            nombreFicheroSalida = null;
            traza = false;
            juego.ejecutar(nombreFicheroEntrada, nombreFicheroSalida, traza);
            return;
        }
        if (args[0].equals("-h")) {
            help();
        }
        if (args[0].equals("-t")) {
            traza = true;
            if (args.length == 1) {
                nombreFicheroEntrada = null;
                nombreFicheroSalida = null;
            }
            if (args.length == 2) {
                nombreFicheroEntrada = args[1];
                nombreFicheroSalida = null;
            }
            if (args.length == 3) {
                nombreFicheroEntrada = args[1];
                nombreFicheroSalida = args[2];
            }
            if (args.length >= 4) {
                mostrarError("Error con los argumentos de entrada. Use -h para más información");
                return;
            }

            juego.ejecutar(nombreFicheroEntrada, nombreFicheroSalida, traza);
            return;
        }

        if (!args[0].equals("-t") && !args[0].equals("-h")) {
            if (args.length == 1) {
                nombreFicheroEntrada = args[0];
                nombreFicheroSalida = null;
            }
            if (args.length == 2) {
                nombreFicheroEntrada = args[0];
                nombreFicheroSalida = args[1];
            }
            if (args.length >= 3) {
                mostrarError("Número de argumentos incorrecto. Use -h para más información");
                return;
            }
            juego.ejecutar(nombreFicheroEntrada, nombreFicheroSalida, traza);
            return;
        }
    }

    public static void help() {
        System.out.println("SINTAXIS: tareas [-t|-h] [fichero entrada] [fichero salida]");
        System.out.println("-t" + "                     " + " Traza el algoritmo");
        System.out.println("-h" + "                     " + " Muestra esta ayuda");
        System.out.println("[fichero de entrada]" + "   " + " Nombre del fichero de entrada");
        System.out.println("[fichero salida]" + "       " + " Nombre del fichero de salida");

    }
    public static void mostrarError(String texto) {
        System.out.println(texto);
    }


}
