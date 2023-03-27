package buscaminas;

public class Stack {
    private ListaEnlazada stacklist = new ListaEnlazada();
    private int size;

    public void push(int[] newElement) {
        this.size++;
        this.stacklist.insertFirst(newElement);
    }

    public int[] pop() {
        this.size--;
        return this.stacklist.deleteFirst();
        
    }

    public int[] peek() {
        return this.stacklist.getHead();
    }
    
    public int size() {
        return this.size;
    }
}
