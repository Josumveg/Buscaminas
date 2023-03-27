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
            int randi = r.nextInt(8);
            int randj = r.nextInt(8);
            generarListaMinas();
            generarListaSegura();
            updateListaSegura();
            if (listasegura.isEmpty()) {
                while (Cuadricula.matrizvalores[randi][randj].revelado == true) {
                    randi = r.nextInt(8);
                    randj = r.nextInt(8);
                }
                elegirEspacioAvanzado(randi, randj);
                turno = false;
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
    
    static void addEspaciosSegurosAdy(int i, int j) {
        if (Cuadricula.esPosValida(i, j+1)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i][j+1].revelado == false) { // si el numero a revelar tiene minas alrededor, se pone el numero de minas alrededor
                if (listasegura.contains(i, j+1) == false) {
                    if (listaminas.contains(i, j+1) == false) {
                        listasegura.insertFirst(new int [] {i,j+1});
                    }
                }
            }
        }
        if (Cuadricula.esPosValida(i+1, j+1)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i+1][j+1].revelado == false) { // si el numero a revelar tiene minas alrededor, se pone el numero de minas alrededor
                if (listasegura.contains(i+1, j+1) == false) {
                    if (listaminas.contains(i+1, j+1) == false) {
                        listasegura.insertFirst(new int [] {i+1,j+1});
                    }
                }
            }
        }
        if (Cuadricula.esPosValida(i-1, j+1)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i-1][j+1].revelado == false) { // si el numero a revelar tiene minas alrededor, se pone el numero de minas alrededor
                if (listasegura.contains(i-1, j+1) == false) {
                    if (listaminas.contains(i-1, j+1) == false) {
                        listasegura.insertFirst(new int [] {i-1,j+1});
                    }
                }
            }
        }
        if (Cuadricula.esPosValida(i+1, j)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i+1][j].revelado == false) { // si el numero a revelar tiene minas alrededor, se pone el numero de minas alrededor
                if (listasegura.contains(i+1, j) == false) {
                    if (listaminas.contains(i+1, j) == false) {
                        listasegura.insertFirst(new int [] {i+1,j});
                    }
                }
            }
        }
        if (Cuadricula.esPosValida(i-1, j)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i-1][j].revelado == false) { // si el numero a revelar tiene minas alrededor, se pone el numero de minas alrededor
                if (listasegura.contains(i-1, j) == false) {
                    if (listaminas.contains(i-1, j) == false) {
                        listasegura.insertFirst(new int [] {i-1,j});
                    }
                }
            }
        }
        if (Cuadricula.esPosValida(i, j-1)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i][j-1].revelado == false) { // si el numero a revelar tiene minas alrededor, se pone el numero de minas alrededor
                if (listasegura.contains(i, j-1) == false) {
                    if (listaminas.contains(i, j-1) == false) {
                        listasegura.insertFirst(new int [] {i,j-1});
                    }
                }
            }
        }
        if (Cuadricula.esPosValida(i+1, j-1)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i+1][j-1].revelado == false) { // si el numero a revelar tiene minas alrededor, se pone el numero de minas alrededor
                if (listasegura.contains(i+1, j-1) == false) {
                    if (listaminas.contains(i+1, j-1) == false) {
                        listasegura.insertFirst(new int [] {i+1,j-1});
                    }
                }
            }
        }
        if (Cuadricula.esPosValida(i-1, j-1)) { // se revisa que los indices no se salgan del array
            if (Cuadricula.matrizvalores[i-1][j-1].revelado == false) { // si el numero a revelar tiene minas alrededor, se pone el numero de minas alrededor
                if (listasegura.contains(i-1, j-1) == false) {
                    if (listaminas.contains(i-1, j-1) == false) {
                        listasegura.insertFirst(new int [] {i-1,j-1});
                    }
                }
            }
        }
    }
    
    static void updateListaSegura() {
        for (int i=0; i<=7; i++) {
            for (int j=0; j<=7; j++) {
                if (Cuadricula.matrizvalores[i][j].revelado == true && listasegura.contains(i, j)) {
                    listasegura.delete(i, j);
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
    
    static void generarListaSegura() {
        for (int i=0; i<=7; i++) {
            for (int j=0; j<=7; j++) {
                if (Cuadricula.matrizvalores[i][j].revelado == true && Cuadricula.matrizvalores[i][j].numrev > 0) {
                    if (Cuadricula.cantMinasAdy(i, j) == Cuadricula.matrizvalores[i][j].numrev) {
                        addEspaciosSegurosAdy(i, j);
                    }
                }
            }
        }
    }
}
