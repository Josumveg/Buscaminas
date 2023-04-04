package buscaminas;
import java.util.Random;

/**
 * Clase computadora con herencia de la clase main. 
 * Se tienen los valores y metodos que permiten
 * la funcionalidad de dos modos de juego. Jugar contra
 * el dummy y el avanzado. 
 * @author Jose Pablo Umaña Vega
 */
public class Computadora extends main{
    static boolean turno = false; // variable que determina si es el turno de la computadora
    static boolean dummyfuncionando = false; // variable que determina si el dummy esta funcionando 
    static boolean avanzadofuncionando = false; // variable que determina si el avanzado esta funcionando 
    static ListaEnlazada listaminas = new ListaEnlazada(); // lista enlazada de minas que se han encontrado 
    static ListaEnlazada listasegura = new ListaEnlazada(); // lista enlazada de lugares seguros en los que no hay minas 
    static ListaEnlazada listageneral = new ListaEnlazada(); // lista general de todos los posibles lugares que se pueden jugar 
    static ListaEnlazada listaincertidumbre = new ListaEnlazada(); // lista enlazada de lugares que se pueden jugar donde no se sabe si hay una mina. 
    
    static Random r = new Random(); // se crea un objero de la clase Random 
    /**
     * Metodo dummy que elige un
     * espacio aleatorio para jugar. 
     */
    public static void dummy() {
        if (turno == true) { // si es el turno del dummy 
            int randi = r.nextInt(8);; // genera un indice i aleatorio 
            int randj = r.nextInt(8);; // genera un indice j aleatorio 
            while (Cuadricula.matrizvalores[randi][randj].revelado == true) { // genera numeros aleatorios hasta que elija un esppacio que no se haya revelado 
                randi = r.nextInt(8); // genera un indice i aleatorio
                randj = r.nextInt(8); // genera un indice j aleatorio
            }
            elegirEspacioDummy(randi, randj); // llama al metodo elegirEspacioDummy para revelar el espacio que se eligio 
            turno = false; // termina su turno 
        }
    }
    
    /**
     * Metodo avanzado que elige un espacio
     * para jugar en la cuadricula, basandose
     * en las listas seguras e incertidumbre 
     * para su eleccion. Se imprimen cada una de las
     * listas cada vez que se llama el metodo. 
     */
    public static void avanzado() {
        if (turno == true) { // si es el turno del avanzado 
            generarListaMinas(); // se genera la lista de minas 
            generarListaGeneral(); // se genera la lista general 
            Cuadricula.printLista(listageneral, "lista general"); // se imprime la lista general 
            System.out.println(" ");
            generarListasSegIncert(); // se generan las listas segura e incertidumbre 
            updateLista(listasegura); // se actualiza la lista segura 
            updateLista(listaincertidumbre); // se actualuza la lista incertidumbre 
            if (listasegura.isEmpty()) { // si la lista segura esta vacia 
                elegirEspacioAvanzado(listaincertidumbre.getFirst()[0], listaincertidumbre.getFirst()[1]); // se elige el espacio que esta primero en la lista incertidumbre 
                listaincertidumbre.deleteFirstNotReturn(); // se borra el espacio que se acaba de elegir 
            }
            else { // si hay valores en la lista segura 
                elegirEspacioAvanzado(listasegura.getFirst()[0], listasegura.getFirst()[1]); // se elige el espacio que esta primero en la lista segura  
                listasegura.deleteFirstNotReturn(); // se borra el espacio que se acaba de elegir 
            }
            turno = false; // se termina el turno del avanzado 
        }
    }
    
    /**
     * Se agregan los espacios adyacentes
     * que no han sido revelados a la lista
     * de minas. 
     * @param i fila en la que se encuentra el espacio 
     * @param j columna en la que se encuentra el espacio 
     */
    public static void addAdyNoRevelados(int i, int j) {
        if (Cuadricula.esPosValida(i, j+1)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i][j+1].revelado == false) { // si el espacio no esta revelado
                if (listaminas.contains(i, j+1) == false) { // si el espacio no se encuentra ya en la lista de minas
                    listaminas.insertFirst(new int [] {i,j+1}); // se agrega a la lista de minas
                }
            }
        }
        if (Cuadricula.esPosValida(i+1, j+1)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i+1][j+1].revelado == false) { // si el espacio no esta revelado
                if (listaminas.contains(i+1, j+1) == false) {  // si el espacio no se encuentra ya en la lista de minas
                    listaminas.insertFirst(new int [] {i+1,j+1}); // se agrega a la lista de minas
                }
            }
        }
        if (Cuadricula.esPosValida(i-1, j+1)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i-1][j+1].revelado == false) { // si el espacio no esta revelado
                if (listaminas.contains(i-1, j+1) == false) {  // si el espacio no se encuentra ya en la lista de minas
                    listaminas.insertFirst(new int [] {i-1,j+1}); // se agrega a la lista de minas
                }
            }
        }
        if (Cuadricula.esPosValida(i+1, j)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i+1][j].revelado == false) { // si el espacio no esta revelado
                if (listaminas.contains(i+1, j) == false) {  // si el espacio no se encuentra ya en la lista de minas
                    listaminas.insertFirst(new int [] {i+1,j}); // se agrega a la lista de minas
                }
            }
        }
        if (Cuadricula.esPosValida(i-1, j)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i-1][j].revelado == false) { // si el espacio no esta revelado
                if (listaminas.contains(i-1, j) == false) {  // si el espacio no se encuentra ya en la lista de minas
                    listaminas.insertFirst(new int [] {i-1,j}); // se agrega a la lista de minas
                }
            }
        }
        if (Cuadricula.esPosValida(i, j-1)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i][j-1].revelado == false) { // si el espacio no esta revelado
                if (listaminas.contains(i, j-1) == false) {  // si el espacio no se encuentra ya en la lista de minas
                    listaminas.insertFirst(new int [] {i,j-1}); // se agrega a la lista de minas
                }
            }
        }
        if (Cuadricula.esPosValida(i+1, j-1)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i+1][j-1].revelado == false) { // si el espacio no esta revelado
                if (listaminas.contains(i+1, j-1) == false) {  // si el espacio no se encuentra ya en la lista de minas
                    listaminas.insertFirst(new int [] {i+1,j-1}); // se agrega a la lista de minas
                }
            }
        }
        if (Cuadricula.esPosValida(i-1, j-1)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i-1][j-1].revelado == false) { // si el espacio no esta revelado
                if (listaminas.contains(i-1, j-1) == false) {  // si el espacio no se encuentra ya en la lista de minas
                    listaminas.insertFirst(new int [] {i-1,j-1}); // se agrega a la lista de minas
                }
            }
        }
    }
    
    /**
     * Se determina si el espacio es seguro, 
     * si lo es se añade a la lista enlazada
     * de espacios seguros. 
     * @param i fila en la que se encuentra el espacio 
     * @param j columna en la que se encuentra el espacio 
     */
    public static void addEspacioSeguro(int i, int j) {
        if (Cuadricula.esPosValida(i, j+1)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i][j+1].revelado == true) { // si el espacio de la derecha esta revelado 
                if (Cuadricula.matrizvalores[i][j+1].numrev > 0) { // si el espacio la cantidad de minas adyacentes del espacio de la derecha es mayor a 0
                    if (Cuadricula.matrizvalores[i][j+1].numrev == Cuadricula.cantMinasAdy(i, j+1)) { // si la cantidad de minas adyacentes del espacio de la derecha es igual a la cantidad de minas adyacentes tomando en cuenta la lista de minas
                        if (listaminas.contains(i, j) == false) { // si el espacio no se encuentra en la lista de minas 
                            if (listasegura.contains(i, j) == false) { // si el espacio no se encuentra ya en la lista segura 
                                listasegura.insertFirst(new int[] {i, j}); // se agrega el espacio a la lista segura 
                            }
                        }
                    }
                }
            }
        }
        if (Cuadricula.esPosValida(i+1, j+1)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i+1][j+1].revelado == true) { // si el espacio de abajo a la derecha esta revelado 
                if (Cuadricula.matrizvalores[i+1][j+1].numrev > 0) { // si el espacio la cantidad de minas adyacentes del espacio de la par es mayor a 0
                    if (Cuadricula.matrizvalores[i+1][j+1].numrev == Cuadricula.cantMinasAdy(i+1, j+1)) { // si la cantidad de minas adyacentes del espacio de la par es igual a la cantidad de minas adyacentes tomando en cuenta la lista de minas
                        if (listaminas.contains(i, j) == false) { // si el espacio no se encuentra en la lista de minas 
                            if (listasegura.contains(i, j) == false) { // si el espacio no se encuentra ya en la lista segura
                                listasegura.insertFirst(new int[] {i, j}); // se agrega el espacio a la lista segura 
                            }
                        }
                    }
                }
            }
        }
        if (Cuadricula.esPosValida(i-1, j+1)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i-1][j+1].revelado == true) { // si el espacio de arriba a la derecha esta revelado 
                if (Cuadricula.matrizvalores[i-1][j+1].numrev > 0) { // si el espacio la cantidad de minas adyacentes del espacio de la par es mayor a 0
                    if (Cuadricula.matrizvalores[i-1][j+1].numrev == Cuadricula.cantMinasAdy(i-1, j+1)) { // si la cantidad de minas adyacentes del espacio de la par es igual a la cantidad de minas adyacentes tomando en cuenta la lista de minas
                        if (listaminas.contains(i, j) == false) { // si el espacio no se encuentra en la lista de minas 
                            if (listasegura.contains(i, j) == false) { // si el espacio no se encuentra ya en la lista segura
                                listasegura.insertFirst(new int[] {i, j}); // se agrega el espacio a la lista segura 
                            }
                        }
                    }
                }
            }
        }
        if (Cuadricula.esPosValida(i+1, j)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i+1][j].revelado == true) { // si el espacio de abajo esta revelado 
                if (Cuadricula.matrizvalores[i+1][j].numrev > 0) { // si el espacio la cantidad de minas adyacentes del espacio de la par es mayor a 0
                    if (Cuadricula.matrizvalores[i+1][j].numrev == Cuadricula.cantMinasAdy(i+1, j)) { // si la cantidad de minas adyacentes del espacio de la par es igual a la cantidad de minas adyacentes tomando en cuenta la lista de minas
                        if (listaminas.contains(i, j) == false) { // si el espacio no se encuentra en la lista de minas 
                            if (listasegura.contains(i, j) == false) { // si el espacio no se encuentra ya en la lista segura
                                listasegura.insertFirst(new int[] {i, j}); // se agrega el espacio a la lista segura 
                            }
                        }
                    }
                }
            }
        }
        if (Cuadricula.esPosValida(i-1, j)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i-1][j].revelado == true) { // si el espacio de arriba esta revelado 
                if (Cuadricula.matrizvalores[i-1][j].numrev > 0) { // si el espacio la cantidad de minas adyacentes del espacio de la par es mayor a 0
                    if (Cuadricula.matrizvalores[i-1][j].numrev == Cuadricula.cantMinasAdy(i-1, j)) { // si la cantidad de minas adyacentes del espacio de la par es igual a la cantidad de minas adyacentes tomando en cuenta la lista de minas
                        if (listaminas.contains(i, j) == false) { // si el espacio no se encuentra en la lista de minas 
                            if (listasegura.contains(i, j) == false) { // si el espacio no se encuentra ya en la lista segura
                                listasegura.insertFirst(new int[] {i, j}); // se agrega el espacio a la lista segura 
                            }
                        }
                    }
                }
            }
        }
        if (Cuadricula.esPosValida(i, j-1)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i][j-1].revelado == true) { // si el espacio de la izquierda esta revelado 
                if (Cuadricula.matrizvalores[i][j-1].numrev > 0) { // si el espacio la cantidad de minas adyacentes del espacio de la par es mayor a 0
                    if (Cuadricula.matrizvalores[i][j-1].numrev == Cuadricula.cantMinasAdy(i, j-1)) { // si la cantidad de minas adyacentes del espacio de la par es igual a la cantidad de minas adyacentes tomando en cuenta la lista de minas
                        if (listaminas.contains(i, j) == false) { // si el espacio no se encuentra en la lista de minas 
                            if (listasegura.contains(i, j) == false) { // si el espacio no se encuentra ya en la lista segura
                                listasegura.insertFirst(new int[] {i, j}); // se agrega el espacio a la lista segura 
                            }
                        }
                    }
                }
            }
        }
        if (Cuadricula.esPosValida(i+1, j-1)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i+1][j-1].revelado == true) { // si el espacio de abajo a la izquierda esta revelado 
                if (Cuadricula.matrizvalores[i+1][j-1].numrev > 0) { // si el espacio la cantidad de minas adyacentes del espacio de la par es mayor a 0
                    if (Cuadricula.matrizvalores[i+1][j-1].numrev == Cuadricula.cantMinasAdy(i+1, j-1)) { // si la cantidad de minas adyacentes del espacio de la par es igual a la cantidad de minas adyacentes tomando en cuenta la lista de minas
                        if (listaminas.contains(i, j) == false) { // si el espacio no se encuentra en la lista de minas 
                            if (listasegura.contains(i, j) == false) { // si el espacio no se encuentra ya en la lista segura
                                listasegura.insertFirst(new int[] {i, j}); // se agrega el espacio a la lista segura 
                            }
                        }
                    }
                }
            }
        }
        if (Cuadricula.esPosValida(i-1, j-1)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i-1][j-1].revelado == true) { // si el espacio de arriba a la izquierda esta revelado 
                if (Cuadricula.matrizvalores[i-1][j-1].numrev > 0) { // si el espacio la cantidad de minas adyacentes del espacio de la par es mayor a 0
                    if (Cuadricula.matrizvalores[i-1][j-1].numrev == Cuadricula.cantMinasAdy(i-1, j-1)) { // si la cantidad de minas adyacentes del espacio de la par es igual a la cantidad de minas adyacentes tomando en cuenta la lista de minas
                        if (listaminas.contains(i, j) == false) { // si el espacio no se encuentra en la lista de minas 
                            if (listasegura.contains(i, j) == false) { // si el espacio no se encuentra ya en la lista segura
                                listasegura.insertFirst(new int[] {i, j}); // se agrega el espacio a la lista segura 
                            }
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Se actualiza una lista quitandole
     * los espacios que ya se hayan revelado. 
     * @param lista una lista enlazada de arrays 
     */
    public static void updateLista(ListaEnlazada lista) { // se actualiza la lista para quitar los espacios que ya se han revelado
        for (int i=0; i<=7; i++) {
            for (int j=0; j<=7; j++) {
                if (Cuadricula.matrizvalores[i][j].revelado == true && lista.contains(i, j)) { // si el espacio ya esta revelado y se encuentra en la lista
                    lista.delete(i, j); // se borra el espacio en la lista
                }
            }
        }
    }
    
    /**
     * Se genera la lista enlazada 
     * de minas. Se genera tomando
     * en cuenta la cantidad de 
     * espacios adyacentes revelados
     * y la variable de cantidad de
     * minas adyacentes de la matriz 
     * de valores. 
     */
    static void generarListaMinas() {
        for (int i=0; i<=7; i++) {
            for (int j=0; j<=7; j++) {
                if (Cuadricula.matrizvalores[i][j].revelado == true && Cuadricula.matrizvalores[i][j].numrev > 0) { // si el espacio esta revelado y la cantidad de minas adyacentes es mayor a 0
                    if (Cuadricula.matrizvalores[i][j].numrev + Cuadricula.cantidadReveladosAdy(i, j) == 8) { // si la cantidad de minas adyacentes y la cantidad de espacios revelados adyacentes es igual a 8
                        addAdyNoRevelados(i, j); // se agregan los espacios adyacentes no revelados a la lista de minas 
                    }
                }
            }
        }
    }
    
    /**
     * Se generan las listas segura e 
     * incertidumbre con los valores 
     * que se tienen dentro de la lista
     * general. Se elige un espacio 
     * aleatorio de la lista general 
     * y se determina si es seguro o no 
     * y se agrega a la lista correspondiente. 
     * Una vez que se hace esto el espacio se
     * borra de la lista general. Esto se 
     * repite hasta que la lista general se vacia. 
     */
    public static void generarListasSegIncert() {
        Random r = new Random(); // se genera un objeto random 
        int numrandom; // se declara la variable para un numero random 
        while (listageneral.isEmpty() == false) { // si la lista general no esta vacia 
            Cuadricula.printLista(listageneral, "lista general"); // se imprime la lista general 
            System.out.println(" ");
            numrandom = r.nextInt(listageneral.size()); // genera un numero random del tamaño de la lista general para funcionar como indice
            addEspacioSeguro(listageneral.get(numrandom)[0], listageneral.get(numrandom)[1]); // se añaden los numeros del indice correspondiente a la lista segura
            if (listasegura.contains(listageneral.get(numrandom)[0], listageneral.get(numrandom)[1]) == false) { // si el espacio no se encuentra en la lista segura 
                if (listaincertidumbre.contains(listageneral.get(numrandom)[0], listageneral.get(numrandom)[1]) == false) { // si el espacio no se encuentra ya en la lista incertidumbre
                    listaincertidumbre.insertFirst(new int[] {listageneral.get(numrandom)[0], listageneral.get(numrandom)[1]}); // si no se agregan a la lista segura, se agregan a la incertidumbre
                }
            }
            Cuadricula.printLista(listasegura, "lista segura"); // se imprime la lista segura 
            System.out.println(" ");
            Cuadricula.printLista(listaincertidumbre, "lista incertidumbre"); // se imprime la lista incertidumbre 
            System.out.println(" ");
            listageneral.deleteIndex(numrandom); // borra el numero elegido de la lista general
            Cuadricula.printLista(listageneral, "lista general"); // se imprime la lista general 
            System.out.println(" ");
        }
    }
    
    public static void generarListasSegIncertNoPrint() {
        Random r = new Random(); // se genera un objeto random 
        int numrandom; // se declara la variable para un numero random 
        while (listageneral.isEmpty() == false) { // si la lista general no esta vacia 
            numrandom = r.nextInt(listageneral.size()); // genera un numero random del tamaño de la lista general para funcionar como indice
            addEspacioSeguro(listageneral.get(numrandom)[0], listageneral.get(numrandom)[1]); // se añaden los numeros del indice correspondiente a la lista segura
            if (listasegura.contains(listageneral.get(numrandom)[0], listageneral.get(numrandom)[1]) == false) { // si el espacio no se encuentra en la lista segura 
                if (listaincertidumbre.contains(listageneral.get(numrandom)[0], listageneral.get(numrandom)[1]) == false) { // si el espacio no se encuentra ya en la lista incertidumbre
                    listaincertidumbre.insertFirst(new int[] {listageneral.get(numrandom)[0], listageneral.get(numrandom)[1]}); // si no se agregan a la lista segura, se agregan a la incertidumbre
                }
            }
            listageneral.deleteIndex(numrandom); // borra el numero elegido de la lista general
        }
    }
    
    /**
     * Se genera la lista general. 
     * Se le agragan todos los 
     * espacios posibles para elegir. 
     * Es decir, los espacios que no se
     * hayan revelado. 
     */
    public static void generarListaGeneral() {
        for (int i=0; i<=7; i++) {
            for (int j=0; j<=7; j++) {
                if (Cuadricula.matrizvalores[i][j].revelado == false) { // si el espacio no se ha revelado 
                    listageneral.insertFirst(new int [] {i, j}); // se agrega a la lista general
                }
            }
        }
    }
}
