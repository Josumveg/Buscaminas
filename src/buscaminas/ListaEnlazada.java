package buscaminas;


public class ListaEnlazada {
    
    class Node {
        private int[] data;
        private Node next;
        
        public Node(int[] data) {
            this.next = null;
            this.data = data;
        }
        
        public int[] getData() {
            return this.data;
        }
        
        public void setData(int[] data) {
            this.data = data;
        }
        
        public Node getNext() {
            return this.next;
        }
        
        public void setNext(Node node) {
            this.next = node;
        }
    }
    
    private Node head;
    private int size;

    public ListaEnlazada() {
        this.head = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public int size() {
        return this.size;
    }

    public void insertFirst(int[] data) {
        Node newNode = new Node(data);
        newNode.next = this.head;
        this.head = newNode;
        this.size++;
    }

    public int[] deleteFirst() {
        if (this.head != null) {
            Node temp = this.head;
            this.head = this.head.next;
            this.size--;
            return temp.getData();
        }
        else {
            return null;
        }
    }
    
    public void deleteFirstNotReturn() {
        if (this.head != null) {
            this.head = this.head.next;
            this.size--;
        }
    }

    public int[] delete(int i, int j) {
        Node current = this.head;
        Node previous = this.head;

        while (current != null) {
            if (current.getData()[0] == i && current.getData()[1] == j) {
                if (current == this.head) {
                    this.head = this.head.getNext();
                    this.size--;
                }
                else {
                    previous.setNext(current.getNext());
                    this.size--;
                }
                return current.getData();
            }
            else {
                previous = current;
                current = current.getNext();
            }
        }
        return null;
    }
    
    public int[] getFirst() {
        if (this.head == null) {
            return null;
        }
        else {
            return this.head.getData();
        }
    }
    
    public boolean contains(int i, int j) {
        Node current = this.head;
        while (current != null) {
            if (current.getData()[0] == i && current.getData()[1] == j) {
                return true;
            }
            else {
                current = current.getNext();
            }
        }
        return false;
    }
    
    public void recorrerLista() {
        Node current = this.head;
        while (current != null) {
            System.out.print(current.getData()[0]);
            System.out.print(current.getData()[1]);
            System.out.println("");
            current = current.getNext();
        }
    }
    
    public void empty() {
        this.head = null;
    }
    
    public int[] getHead() {
        if (this.head == null) {
            return null;
        }
        else {
            return this.head.getData();
        }
    }
    
    public int[] get(int searchValue) {
        Node current = this.head;
        int counter = 0;
        while (current != null) {
            if (counter == searchValue) {
                return current.getData();
            }
            else {
                current = current.getNext();
                counter++;
            }
        }
        return null;
    }
    
    public void deleteIndex(int i) {
        Node current = this.head;
        Node previous = this.head;
        int counter = 0;

        while (current != null) {
            if (counter == i) {
                if (current == this.head) {
                    this.head = this.head.getNext();
                    counter++;
                    this.size--;
                }
                else {
                    previous.setNext(current.getNext());
                    counter++;
                    this.size--;
                }
            }
            else {
                previous = current;
                current = current.getNext();
                counter++;
            }
        }
    }
    
    public void resetSize() {
        this.size = 0;
    }
    
}
