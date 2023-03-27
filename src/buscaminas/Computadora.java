package buscaminas;
import java.util.Random;

/**
 *
 * @author josep
 */
public class Computadora extends main{
    static boolean turno = false;
    static boolean dummyfuncionando = false;
    static boolean avanzadofuncionando = false;
    static ListaEnlazada listaminas = new ListaEnlazada();
    static ListaEnlazada listasegura = new ListaEnlazada();
    static ListaEnlazada listageneral = new ListaEnlazada();
    static ListaEnlazada listaincertidumbre = new ListaEnlazada();
    
    static Random r = new Random();
    static void dummy() {
        if (turno == true) {
            int randi = r.nextInt(8);;
            int randj = r.nextInt(8);;
            while (Cuadricula.matrizvalores[randi][randj].revelado == true) {
                randi = r.nextInt(8);
                randj = r.nextInt(8);
            }
            elegirEspacioDummy(randi, randj);
            turno = false;
        }
    }
    
    static void avanzado() {
        if (turno == true) {
            generarListaMinas();
            generarListaGeneral();
            Cuadricula.printLista(listageneral, "lista general");
            System.out.println(" ");
            generarListasSegIncert();
            updateLista(listasegura);
            updateLista(listaincertidumbre);
            Cuadricula.printLista(listasegura, "lista segura");
            System.out.println(" ");
            Cuadricula.printLista(listaincertidumbre, "lista incertidumbre");
            System.out.println(" ");
            Cuadricula.printLista(listageneral, "lista general");
            System.out.println(" ");
            if (listasegura.isEmpty()) {
                elegirEspacioAvanzado(listaincertidumbre.getFirst()[0], listaincertidumbre.getFirst()[1]);
                listaincertidumbre.deleteFirstNotReturn();
            }
            else {
                elegirEspacioAvanzado(listasegura.getFirst()[0], listasegura.getFirst()[1]);
                listasegura.deleteFirstNotReturn();
            }
            turno = false;
        }
    }
    
    static void addAdyNoRevelados(int i, int j) {
        if (Cuadricula.esPosValida(i, j+1)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i][j+1].revelado == false) { // si el numero a revelar tiene minas alrededor, se pone el numero de minas alrededor
                if (listaminas.contains(i, j+1) == false) {
                    listaminas.insertFirst(new int [] {i,j+1});
                }
            }
        }
        if (Cuadricula.esPosValida(i+1, j+1)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i+1][j+1].revelado == false) { // si el numero a revelar tiene minas alrededor, se pone el numero de minas alrededor
                if (listaminas.contains(i+1, j+1) == false) {
                    listaminas.insertFirst(new int [] {i+1,j+1});
                }
            }
        }
        if (Cuadricula.esPosValida(i-1, j+1)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i-1][j+1].revelado == false) { // si el numero a revelar tiene minas alrededor, se pone el numero de minas alrededor
                if (listaminas.contains(i-1, j+1) == false) {
                    listaminas.insertFirst(new int [] {i-1,j+1});
                }
            }
        }
        if (Cuadricula.esPosValida(i+1, j)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i+1][j].revelado == false) { // si el numero a revelar tiene minas alrededor, se pone el numero de minas alrededor
                if (listaminas.contains(i+1, j) == false) {
                    listaminas.insertFirst(new int [] {i+1,j});
                }
            }
        }
        if (Cuadricula.esPosValida(i-1, j)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i-1][j].revelado == false) { // si el numero a revelar tiene minas alrededor, se pone el numero de minas alrededor
                if (listaminas.contains(i-1, j) == false) {
                    listaminas.insertFirst(new int [] {i-1,j});
                }
            }
        }
        if (Cuadricula.esPosValida(i, j-1)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i][j-1].revelado == false) { // si el numero a revelar tiene minas alrededor, se pone el numero de minas alrededor
                if (listaminas.contains(i, j-1) == false) {
                    listaminas.insertFirst(new int [] {i,j-1});
                }
            }
        }
        if (Cuadricula.esPosValida(i+1, j-1)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i+1][j-1].revelado == false) { // si el numero a revelar tiene minas alrededor, se pone el numero de minas alrededor
                if (listaminas.contains(i+1, j-1) == false) {
                    listaminas.insertFirst(new int [] {i+1,j-1});
                }
            }
        }
        if (Cuadricula.esPosValida(i-1, j-1)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i-1][j-1].revelado == false) { // si el numero a revelar tiene minas alrededor, se pone el numero de minas alrededor
                if (listaminas.contains(i-1, j-1) == false) {
                    listaminas.insertFirst(new int [] {i-1,j-1});
                }
            }
        }
    }
    
    static void addEspacioSeguro(int i, int j) {
        if (Cuadricula.esPosValida(i, j+1)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i][j+1].revelado == true) { // si el numero a revelar tiene minas alrededor, se pone el numero de minas alrededor
                if (Cuadricula.matrizvalores[i][j+1].numrev > 0) {
                    if (Cuadricula.matrizvalores[i][j+1].numrev == Cuadricula.cantMinasAdy(i, j+1)) {
                        if (listaminas.contains(i, j) == false) {
                            if (listasegura.contains(i, j) == false) {
                                listasegura.insertFirst(new int[] {i, j});
                            }
                        }
                    }
                }
            }
        }
        if (Cuadricula.esPosValida(i+1, j+1)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i+1][j+1].revelado == true) { // si el numero a revelar tiene minas alrededor, se pone el numero de minas alrededor
                if (Cuadricula.matrizvalores[i+1][j+1].numrev > 0) {
                    if (Cuadricula.matrizvalores[i+1][j+1].numrev == Cuadricula.cantMinasAdy(i+1, j+1)) {
                        if (listaminas.contains(i, j) == false) {
                            if (listasegura.contains(i, j) == false) {
                                listasegura.insertFirst(new int[] {i, j});
                            }
                        }
                    }
                }
            }
        }
        if (Cuadricula.esPosValida(i-1, j+1)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i-1][j+1].revelado == true) { // si el numero a revelar tiene minas alrededor, se pone el numero de minas alrededor
                if (Cuadricula.matrizvalores[i-1][j+1].numrev > 0) {
                    if (Cuadricula.matrizvalores[i-1][j+1].numrev == Cuadricula.cantMinasAdy(i-1, j+1)) {
                        if (listaminas.contains(i, j) == false) {
                            if (listasegura.contains(i, j) == false) {
                                listasegura.insertFirst(new int[] {i, j});
                            }
                        }
                    }
                }
            }
        }
        if (Cuadricula.esPosValida(i+1, j)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i+1][j].revelado == true) { // si el numero a revelar tiene minas alrededor, se pone el numero de minas alrededor
                if (Cuadricula.matrizvalores[i+1][j].numrev > 0) {
                    if (Cuadricula.matrizvalores[i+1][j].numrev == Cuadricula.cantMinasAdy(i+1, j)) {
                        if (listaminas.contains(i, j) == false) {
                            if (listasegura.contains(i, j) == false) {
                                listasegura.insertFirst(new int[] {i, j});
                            }
                        }
                    }
                }
            }
        }
        if (Cuadricula.esPosValida(i-1, j)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i-1][j].revelado == true) { // si el numero a revelar tiene minas alrededor, se pone el numero de minas alrededor
                if (Cuadricula.matrizvalores[i-1][j].numrev > 0) {
                    if (Cuadricula.matrizvalores[i-1][j].numrev == Cuadricula.cantMinasAdy(i-1, j)) {
                        if (listaminas.contains(i, j) == false) {
                            if (listasegura.contains(i, j) == false) {
                                listasegura.insertFirst(new int[] {i, j});
                            }
                        }
                    }
                }
            }
        }
        if (Cuadricula.esPosValida(i, j-1)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i][j-1].revelado == true) { // si el numero a revelar tiene minas alrededor, se pone el numero de minas alrededor
                if (Cuadricula.matrizvalores[i][j-1].numrev > 0) {
                    if (Cuadricula.matrizvalores[i][j-1].numrev == Cuadricula.cantMinasAdy(i, j-1)) {
                        if (listaminas.contains(i, j) == false) {
                            if (listasegura.contains(i, j) == false) {
                                listasegura.insertFirst(new int[] {i, j});
                            }
                        }
                    }
                }
            }
        }
        if (Cuadricula.esPosValida(i+1, j-1)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i+1][j-1].revelado == true) { // si el numero a revelar tiene minas alrededor, se pone el numero de minas alrededor
                if (Cuadricula.matrizvalores[i+1][j-1].numrev > 0) {
                    if (Cuadricula.matrizvalores[i+1][j-1].numrev == Cuadricula.cantMinasAdy(i+1, j-1)) {
                        if (listaminas.contains(i, j) == false) {
                            if (listasegura.contains(i, j) == false) {
                                listasegura.insertFirst(new int[] {i, j});
                            }
                        }
                    }
                }
            }
        }
        if (Cuadricula.esPosValida(i-1, j-1)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i-1][j-1].revelado == true) { // si el numero a revelar tiene minas alrededor, se pone el numero de minas alrededor
                if (Cuadricula.matrizvalores[i-1][j-1].numrev > 0) {
                    if (Cuadricula.matrizvalores[i-1][j-1].numrev == Cuadricula.cantMinasAdy(i-1, j-1)) {
                        if (listaminas.contains(i, j) == false) {
                            if (listasegura.contains(i, j) == false) {
                                listasegura.insertFirst(new int[] {i, j});
                            }
                        }
                    }
                }
            }
        }
    }
    
    static void updateLista(ListaEnlazada lista) { // se actualiza la lista para quitar los espacios que ya se han revelado
        for (int i=0; i<=7; i++) {
            for (int j=0; j<=7; j++) {
                if (Cuadricula.matrizvalores[i][j].revelado == true && lista.contains(i, j)) {
                    lista.delete(i, j);
                }
            }
        }
    }
    
    static void generarListaMinas() {
        for (int i=0; i<=7; i++) {
            for (int j=0; j<=7; j++) {
                if (Cuadricula.matrizvalores[i][j].revelado == true && Cuadricula.matrizvalores[i][j].numrev > 0) {
                    if (Cuadricula.matrizvalores[i][j].numrev + Cuadricula.cantidadReveladosAdy(i, j) == 8) {
                        addAdyNoRevelados(i, j);
                    }
                }
            }
        }
    }
    
    static void generarListasSegIncert() {
        Random r = new Random();
        int numrandom;
        while (listageneral.isEmpty() == false) {
            numrandom = r.nextInt(listageneral.size()); // genera un numero random del tamaño de la lista general para funcionar como indice
            addEspacioSeguro(listageneral.get(numrandom)[0], listageneral.get(numrandom)[1]); // se añaden los numeros del indice correspondiente a la lista segura
            if (listasegura.contains(listageneral.get(numrandom)[0], listageneral.get(numrandom)[1]) == false) {
                if (listaincertidumbre.contains(listageneral.get(numrandom)[0], listageneral.get(numrandom)[1]) == false) {
                    listaincertidumbre.insertFirst(new int[] {listageneral.get(numrandom)[0], listageneral.get(numrandom)[1]}); // si no se agregan a la lista segura, se agregan a la incertidumbre
                }
            }
            listageneral.deleteIndex(numrandom); // borra el numero elegido de la lista general
        }
    }
    static void generarListaGeneral() {
        for (int i=0; i<=7; i++) {
            for (int j=0; j<=7; j++) {
                if (Cuadricula.matrizvalores[i][j].revelado == false) {
                    listageneral.insertFirst(new int [] {i, j});
                }
            }
        }
    }
}
