import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class ProbarTareas {
    int[][] costes;
    AsignacionTareas asignacion = new AsignacionTareas();

    public void ejecutar(String nombreEntrada, String nombreSalida, boolean traza) {
        boolean valido = false;

        if (nombreEntrada != null) {
            try {
                leerFicheroEntrada(nombreEntrada);
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Error. Cantidad o tipo de los valores no válido");
            } catch (FileNotFoundException e) {
                System.out.println("Error al abrir el fichero. Fichero no encontrado");
            } catch (IOException e) {
                System.out.println("Error al leer desde el fichero");
            } catch (NullPointerException e) {
                System.out.println("Error al leer el fichero");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error. Cantidad o tipo de los valores no válido");
            }
        } else {
            try {
                leerEntradaEstandar();
                valido = true;
            } catch (InputMismatchException ex) {
                System.out.println("Error de lectura a través de teclado");
            } catch (NullPointerException e) {
                System.out.println("Error. Cantidad o tipo de los valores no válido");
            } catch (NumberFormatException e){
                System.out.println("Error. Tipo de valores no válido");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error. Cantidad o tipo de los valores no válido");
            }

        }
        if (valido) {
            if(traza){
                asignacion.asignaTareas(costes);
                List<String> trazaCompleta = asignacion.obtenerTrazaCompleta();
                if(nombreSalida != null){
                    try {
                        escribirFicheroSalida(nombreSalida, trazaCompleta);
                    } catch (IOException e) {
                        System.out.println("Error con el fichero de salida");
                    }
                } else{
                    escribirSalidaEstandar(trazaCompleta);
                }
            } else {
                asignacion.asignaTareas(costes);
                List<String> pasosAsignacion = asignacion.getPasos();
                if(nombreSalida != null){
                    try{
                        escribirFicheroSalida(nombreSalida, pasosAsignacion);
                    } catch (IOException e) {
                        System.out.println("Error con el fichero de salida");
                    }
                } else{
                    escribirSalidaEstandar(pasosAsignacion);
                }
            }

        }
    }

    private void leerFicheroEntrada(String nombre) throws FileNotFoundException, IOException, NumberFormatException, NullPointerException {
        int[][] matriz;
        FileReader fr = null;
        BufferedReader br = null;
        String[] dimensiones = null;
        String linea;

        try {
            fr = new FileReader(nombre);
            br = new BufferedReader(fr);
            // Leer la primera línea para obtener las dimensiones
            dimensiones = br.readLine().trim().split("\\s+");
            int filas = Integer.parseInt(dimensiones[0]);
            int columnas = Integer.parseInt(dimensiones[1]);
            costes = new int[filas][columnas];
            // Leer el resto de las líneas para llenar la matriz
            for (int i = 0; i < filas; i++) {
                linea = br.readLine();
                String[] numeros = linea.split("\\s+");
                for (int j = 0; j < columnas; j++) {
                    costes[i][j] = Integer.parseInt(numeros[j]);
                }
            }
        } catch (FileNotFoundException ex) {
            throw ex;
        } catch (IOException ex) {
            throw ex;
        } catch (NullPointerException ex) {
            throw ex;
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw ex;
        }
        finally {
            if (br != null) {
                br.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
    }

    private void leerEntradaEstandar() throws InputMismatchException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese las dimensiones de la tabla (filas columnas):");
        int filas = sc.nextInt();
        int columnas = sc.nextInt();
        sc.nextLine();
        costes = new int[filas][columnas];
        System.out.println("Ingrese los valores de la tabla (agentes x tareas):");
        for (int i = 0; i < filas; i++) {
            String linea = sc.nextLine();
            String[] valores = linea.split("\\s+"); // Dividir por espacios
            for (int j = 0; j < columnas; j++) {
                costes[i][j] = Integer.parseInt(valores[j]); // Convertir a entero y asignar
            }
        }
    }

    private void escribirFicheroSalida(String nombre, List<String> trazas) throws IOException {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try{
            fw = new FileWriter(nombre);
            bw = new BufferedWriter(fw);
            for (String traza : trazas) {
                bw.write(traza);
                bw.newLine();
            }
        } catch(IOException e){
            throw e;
        } finally {
            if (bw != null) {
                bw.close();
            }
            if (fw != null) {
                fw.close();
            }
        }
    }

    private void escribirSalidaEstandar(List<String> trazaPasos) {
        for (String paso : trazaPasos) {
            System.out.println(paso);
        }
        System.out.println();
    }




}



