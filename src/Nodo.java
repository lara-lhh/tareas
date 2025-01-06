import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Nodo implements Comparable<Nodo> {
    int[] asignaciones; // Vector que indica las tareas asignadas a agentes
    int costeAsig;      // Coste acumulado hasta este nodo
    int estimacionRealista;
    List<Integer> tareasSinAsignar; // Lista de tareas que aún no han sido asignadas
    int ultimoAgenteAsignado; // Último agente al que se le asignó una tarea

    // Constructor
    public Nodo(int n) {
        asignaciones = new int[n];
        Arrays.fill(asignaciones, 0);
        costeAsig = 0;
        estimacionRealista = 0;
        tareasSinAsignar = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            tareasSinAsignar.add(i); // Todas las tareas están sin asignar al inicio
        }
        ultimoAgenteAsignado = 0; // Ningún agente ha sido asignado todavía
    }
    public int compareTo(Nodo otro) {
        return Integer.compare(this.costeAsig, otro.costeAsig);
    }
    public String toString() {
        return  "Asignaciones: " + Arrays.toString(asignaciones) + " " + "\n"
                +"Coste:" + " "+ costeAsig + " " + "Estimación optimista:" + " " +estimacionRealista + " " + "\n"
                + "Tareas sin asignar"+ " " + tareasSinAsignar + " " + "\n"+
                "Último agente asignado:" + " " + ultimoAgenteAsignado + "\n" ;
    }

}
