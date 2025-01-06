import java.util.ArrayList;
import java.util.List;


public class AsignacionTareas {
    public List<String> traza = new ArrayList<>();
    public List<String> pasos = new ArrayList<>();

    public int[] asignaTareas(int[][] costes) {
        Monticulo monticulo = new Monticulo();
        Nodo nodoInicial = new Nodo(costes.length);
        monticulo.agregar(nodoInicial);

        int cotaSuperior = cotaSup(costes);
        traza.add("Estimación pesimista = " + cotaSuperior);
        Nodo mejorSolucion = nodoInicial;

        while (!monticulo.estaVacio()) {
            Nodo nodo = monticulo.extraerMasPrometedor();
            traza.add("\n" + "NODO EXTRAÍDO DEL MONTÍCULO: " + "\n"+ nodo.toString() );
            // Si el nodo es una solución válida
            if (nodo.tareasSinAsignar.isEmpty()) {
                if (nodo.estimacionRealista <=  cotaSuperior) {
                    cotaSuperior = nodo.estimacionRealista;
                    traza.add("Estimación pesismista = " + cotaSuperior);
                    mejorSolucion = nodo;
                    traza.add("Mejor solucion = " + mejorSolucion);
                }
            }  else if (nodo.estimacionRealista <= cotaSuperior) {
                    List<Nodo> hijos = compleciones(nodo, costes);
                    for (Nodo hijo : hijos) {
                        traza.add("\n" + "NODOS HIJO GENERADOS: "+ "\n" +  hijo.toString());
                        if (hijo.estimacionRealista  <= cotaSuperior) {
                            monticulo.agregar(hijo);
                        }
                    }
                }
            }
        traza.add("\n" +"ASIGNACIÓN ÓPTIMA : ");
        pasos.add("-----------");
        for (int i = 0; i < mejorSolucion.asignaciones.length; i++) {
            pasos.add((i + 1) + " " + mejorSolucion.asignaciones[i]);
            traza.add("Agente " + (i + 1) + " -> Tarea " + mejorSolucion.asignaciones[i]);
        }
        return mejorSolucion.asignaciones;
    }

    public List<String> obtenerTrazaCompleta() {
        return traza;
    }

    public List<String> getPasos(){
        return pasos;
    }


    public List<Nodo> compleciones(Nodo nodo, int[][] costes) {
        List<Nodo> listaCompleciones = new ArrayList<>();
        for (int tarea : nodo.tareasSinAsignar) {
            Nodo hijo = new Nodo(costes.length);

            // Copiar estado del nodo actual al hijo
            System.arraycopy(nodo.asignaciones, 0,hijo.asignaciones, 0, costes.length);
            hijo.tareasSinAsignar = new ArrayList<>(nodo.tareasSinAsignar);
            hijo.costeAsig = nodo.costeAsig;
            hijo.ultimoAgenteAsignado = nodo.ultimoAgenteAsignado + 1;
            // Asignar tarea al siguiente agente
            hijo.asignaciones[hijo.ultimoAgenteAsignado -1] = tarea;
            hijo.costeAsig = costes[hijo.ultimoAgenteAsignado - 1][tarea - 1] + nodo.costeAsig;
            // Eliminar la tarea asignada de la lista de tareas sin asignar
            hijo.tareasSinAsignar.remove((Integer) tarea);
            hijo.estimacionRealista = hijo.costeAsig + calcularEstimacion(hijo, costes);

            listaCompleciones.add(hijo);
        }
        return listaCompleciones;
    }

    public int cotaSup(int[][] costes) {
        int estimacion = 0;
        int n = costes.length;
        for (int i = 0; i < n; i++) {
            estimacion += costes[i][i];
        }
        return estimacion;
    }
    public static int calcularEstimacion(Nodo nodo, int[][] costes) {
        int estimacion = 0;
        for (int tarea : nodo.tareasSinAsignar) {
            int minimo = Integer.MAX_VALUE;
            for (int agente = nodo.ultimoAgenteAsignado + 1; agente < costes.length + 1; agente++) {
                minimo = Math.min(minimo, costes[agente - 1][tarea - 1]);
            }
            estimacion += minimo;
        }
        return estimacion;
    }


}