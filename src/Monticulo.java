import java.util.ArrayList;
import java.util.List;

public class Monticulo {
    private List<Nodo> lista; // Lista para almacenar los nodos

    // Constructor
    public Monticulo() {
        lista = new ArrayList<>();
    }

    // Agregar un nodo al montículo
    public void agregar(Nodo nodo) {
        lista.add(nodo);
        ordenarMonticulo();
    }

    // Sacar el nodo más prometedor
    public Nodo extraerMasPrometedor() {
        if (lista.isEmpty()) {
            return null;
        }
        Nodo masPrometedor = lista.get(0); // El nodo más prometedor está al inicio
        lista.remove(0); // Eliminar el nodo más prometedor
        return masPrometedor;
    }

    // Devuelve si el montículo está vacío
    public boolean estaVacio() {
        return lista.isEmpty();
    }

    // Ordenar la lista para mantener el nodo más prometedor al inicio
    private void ordenarMonticulo() {
        lista.sort((nodo1, nodo2) -> Integer.compare(nodo1.estimacionRealista, nodo2.estimacionRealista));
    }
}
