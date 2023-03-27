package buscaminas;

public class Stack {
    private ListaEnlazada stacklist = new ListaEnlazada();

    public void push(int[] newElement) {
        this.stacklist.insertFirst(newElement);
    }

    public int[] pop() {
        return this.stacklist.deleteFirst();
    }

    public int[] peek() {
        return this.stacklist.getHead();
    }
}
