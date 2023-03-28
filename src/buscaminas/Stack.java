package buscaminas;

/**
 * @author Jose Pablo Umaña Vega
 * Clase para la creacion de pilas
 * utilizando listas enlazadas. Esta
 * es especificamente una pila de listas
 * enlazadas de arreglos. 
 */
public class Stack {
    private ListaEnlazada stacklist = new ListaEnlazada();
    private int size;
    
    /**
     * metodo push de la pila
     * se inserta un nuevo 
     * elemento al comienzo
     * de la pila 
     * @param newElement un arreglo 
     */
    public void push(int[] newElement) {
        this.size++;
        this.stacklist.insertFirst(newElement);
    }
    
    /**
     * Metodo pop de la pila, 
     * se borra el elemento
     * al comienzo de la pila
     * @return el arreglo del primero nodo de la pila
     */
    public int[] pop() {
        this.size--;
        return this.stacklist.deleteFirst();
    }
    
    /**
     * metodo peek de la pila
     * @return el array del primer nodo de la pila
     */
    public int[] peek() {
        return this.stacklist.getHead();
    }
    
    /**
     * @return el tamaño de la pila 
     */
    public int size() {
        return this.size;
    }
    
    /**
     * Devuelve el tamaño de la
     * pila a 0
     */
    public void resetSize() {
        this.size = 0;
    }
}
