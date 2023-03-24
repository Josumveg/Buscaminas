package buscaminas;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.Random;


public class Cuadricula{
    
    int mina;
    int bandera;
    int numrev;
    boolean revelado;
    static int cantbanderas = 0;
    static int countersec = 0;
    static int countermin = 0;
    static boolean gameover = false;
    static boolean victoria = false;
    static Button [][] matrizboton = new Button[8][8];
    static Cuadricula [][] matrizvalores = new Cuadricula [8][8];
    static Button botonreset;
    static Label labelcantminasencontradas;
    
    Cuadricula(int mina, int bandera, int numrev, boolean revelado){
        this.mina = mina;
        this.bandera = bandera;
        this.numrev = numrev;
        this.revelado = revelado;
    }
    
    static int minminas = 8; // cantidad de minas que apareceran en cuadricula
    static void generarMinas() { //añade minas en orden random a matriz
        Random r = new Random();
        int cantminas = 0; // contador de cantidad de minas
        while (cantminas < minminas){ 
            for (int i=0; i<=7; i++) {
                for (int j=0; j<=7; j++) { // indices para recorrer matriz
                    int numrandom = r.nextInt(8); //num random del 1 al 8
                    if (cantminas == minminas){ // si ya se tienen las minas necesarias para el loop
                        break;
                    }
                    else {
                        if (numrandom == 1) { //se fija que no haya ya una mina en ese espacio 
                            if (matrizvalores[i][j].mina == 0){
                                matrizvalores[i][j].mina = 1;
                                cantminas++;
                            }
                        }
                    }
                }
            }
        }
    }
    
    static boolean esMina(int x) { // se fija si hay una mina en la cuadricula
        if (x==1 || x==-1) {
            return true; // retorna true si la hay 
        }
        else {
            return false; // false en caso contrario
        }
    }
    
    static void revelarMinas() { //revela las minas de la cuadricula (Game over)
        for (int i=0; i<=7; i++) {
            for (int j=0; j<=7; j++) { // indice para recorrer cuadricula
                if (Cuadricula.esMina(matrizvalores[i][j].mina)) { // se fija si hay una mina
                    matrizboton[i][j].setText("*"); // cambia el texto del boton para simbolizar una mina
                    matrizboton[i][j].setStyle("-fx-background-color: #F00000;"); // cambia el color a rojo 
                }
            }
        }
                
    }
    
    static boolean esPosValida(int i, int j) {
        if (i < 0 || j < 0 || i > 7 || j > 7){ // se fija que los indices no se salgan del array
            return false; // si se salen retorna false
        }
        else {
            return true; // en caso contrario retorna true
        }
    }
    
    static void generarNumAdy() { // genera los numeros de minas adyacentes y los añade a matrizvalores.numrev
        for (int i=0; i<=7; i++) { 
            for (int j=0; j<=7; j++) { // indices para recorrer la matriz
                int contador = 0; 
                if (!Cuadricula.esMina(matrizvalores[i][j].mina)) { // si no hay una mina en el espacio
                    if (esPosValida(i-1, j-1)) { // si el espacio de arriba a la izquierda no se sale del arreglo
                        if (Cuadricula.esMina(matrizvalores[i-1][j-1].mina)) { // si el espacio de arriba a la izquierda es una mina
                            contador++; // se aumenta el contador
                        }
                    }
                    if (esPosValida(i-1, j)) { // si el espacio de arriba no se sale del arreglo
                        if (Cuadricula.esMina(matrizvalores[i-1][j].mina)) { // si el espacio de arriba es una mina
                            contador++; // se aumenta el contador
                        }
                    }
                    if (esPosValida(i-1, j+1)) { // si el espacio de arriba a la derecha no se sale del arreglo
                        if (Cuadricula.esMina(matrizvalores[i-1][j+1].mina)) { // si el espacio de arriba a la derecha es una mina
                            contador++; // se aumenta el contador
                        }
                    }
                    if (esPosValida(i, j-1)) { // si el espacio de la izquierda no se sale del arreglo
                        if (Cuadricula.esMina(matrizvalores[i][j-1].mina)) { // si el espacio de la izquierda es una mina 
                            contador++; // se aumenta el contador
                        }
                    }
                    if (esPosValida(i, j+1)) { // si el espacio de la derecha no se sale del arreglo
                        if (Cuadricula.esMina(matrizvalores[i][j+1].mina)) { // si el espacio de la derecha es una mina
                            contador++; // se aumenta el contador
                        }
                    }
                    if (esPosValida(i+1, j-1)) { // si el espacio de abajo a la izquierda no se sale del arreglo
                        if (Cuadricula.esMina(matrizvalores[i+1][j-1].mina)) { // si el espacio de abajo a la izquierda es una mina
                            contador++; // se aumenta el contador
                        }
                    }
                    if (esPosValida(i+1, j)) { // si el espacio de abajo no se sale del arreglo
                        if (Cuadricula.esMina(matrizvalores[i+1][j].mina)) { // si el espacio de abajo es una mina 
                            contador++; // se aumenta el contador
                        }
                    }
                    if (esPosValida(i+1, j+1)) { // si el espacio de abajo a la derecha no se sale del arreglo
                        if (Cuadricula.esMina(matrizvalores[i+1][j+1].mina)) { // si el espacio de abajo a la derecha es una mina
                            contador++; // se aumenta el contador
                        }
                    }
                    matrizvalores[i][j].numrev = contador;
                }
                else {
                    matrizvalores[i][j].numrev = -1;
                }
               
            }
        }
    }
    
    static void revelarAdy(int i, int j) {
        if (esPosValida(i-1, j-1)) { // se revisa que los indices no se salgan del array
            matrizboton[i-1][j-1].setStyle("-fx-background-color: #DADAD7;-fx-border-color: #C2C2C2;"); // se cambian los colores del boton para simbolizar que se revela
            matrizvalores[i-1][j-1].revelado = true; // se refleja la revelacion en la matriz
            if (matrizvalores[i-1][j-1].numrev > 0) { // si el numero a revelar tiene minas alrededor, se pone el numero de minas alrededor
                matrizboton[i-1][j-1].setText(Integer.toString(matrizvalores[i-1][j-1].numrev));
            }
        }
        if (esPosValida(i-1, j)) { // se revisa que los indices no se salgan del array
            matrizboton[i-1][j].setStyle("-fx-background-color: #DADAD7;-fx-border-color: #C2C2C2;"); // se cambian los colores del boton para simbolizar que se revela
            matrizvalores[i-1][j].revelado = true; // se refleja la revelacion en la matriz
            if (matrizvalores[i-1][j].numrev > 0) { // si el numero a revelar tiene minas alrededor, se pone el numero de minas alrededor
                matrizboton[i-1][j].setText(Integer.toString(matrizvalores[i-1][j].numrev));
            }
        }
        if (esPosValida(i-1, j+1)) { // se revisa que los indices no se salgan del array
            matrizboton[i-1][j+1].setStyle("-fx-background-color: #DADAD7;-fx-border-color: #C2C2C2;"); // se cambian los colores del boton para simbolizar que se revela
            matrizvalores[i-1][j+1].revelado = true; // se refleja la revelacion en la matriz
            if (matrizvalores[i-1][j+1].numrev > 0) { // si el numero a revelar tiene minas alrededor, se pone el numero de minas alrededor
                matrizboton[i-1][j+1].setText(Integer.toString(matrizvalores[i-1][j+1].numrev));
            }
        }
        if (esPosValida(i, j-1)) { // se revisa que los indices no se salgan del array
            matrizboton[i][j-1].setStyle("-fx-background-color: #DADAD7;-fx-border-color: #C2C2C2;"); // se cambian los colores del boton para simbolizar que se revela
            matrizvalores[i][j-1].revelado = true; // se refleja la revelacion en la matriz
            if (matrizvalores[i][j-1].numrev > 0) { // si el numero a revelar tiene minas alrededor, se pone el numero de minas alrededor
                matrizboton[i][j-1].setText(Integer.toString(matrizvalores[i][j-1].numrev));
            }
        }
        if (esPosValida(i, j+1)) { // se revisa que los indices no se salgan del array
            matrizboton[i][j+1].setStyle("-fx-background-color: #DADAD7;-fx-border-color: #C2C2C2;"); // se cambian los colores del boton para simbolizar que se revela
            matrizvalores[i][j+1].revelado = true; // se refleja la revelacion en la matriz
            if (matrizvalores[i][j+1].numrev > 0) { // si el numero a revelar tiene minas alrededor, se pone el numero de minas alrededor
                matrizboton[i][j+1].setText(Integer.toString(matrizvalores[i][j+1].numrev));
            }
        }
        if (esPosValida(i+1, j-1)) { // se revisa que los indices no se salgan del array
            matrizboton[i+1][j-1].setStyle("-fx-background-color: #DADAD7;-fx-border-color: #C2C2C2;"); // se cambian los colores del boton para simbolizar que se revela
            matrizvalores[i+1][j-1].revelado = true; // se refleja la revelacion en la matriz
            if (matrizvalores[i+1][j-1].numrev > 0) { // si el numero a revelar tiene minas alrededor, se pone el numero de minas alrededor
                matrizboton[i+1][j-1].setText(Integer.toString(matrizvalores[i+1][j-1].numrev));
            }
        }
        if (esPosValida(i+1, j)) { // se revisa que los indices no se salgan del array
            matrizboton[i+1][j].setStyle("-fx-background-color: #DADAD7;-fx-border-color: #C2C2C2;"); // se cambian los colores del boton para simbolizar que se revela
            matrizvalores[i+1][j].revelado = true; // se refleja la revelacion en la matriz
            if (matrizvalores[i+1][j].numrev > 0) { // si el numero a revelar tiene minas alrededor, se pone el numero de minas alrededor
                matrizboton[i+1][j].setText(Integer.toString(matrizvalores[i+1][j].numrev));
            }
        }
        if (esPosValida(i+1, j+1)) { // se revisa que los indices no se salgan del array
            matrizboton[i+1][j+1].setStyle("-fx-background-color: #DADAD7;-fx-border-color: #C2C2C2;"); // se cambian los colores del boton para simbolizar que se revela
            matrizvalores[i+1][j+1].revelado = true; // se refleja la revelacion en la matriz
            if (matrizvalores[i+1][j+1].numrev > 0) { // si el numero a revelar tiene minas alrededor, se pone el numero de minas alrededor
                matrizboton[i+1][j+1].setText(Integer.toString(matrizvalores[i+1][j+1].numrev));
            }
        }
        matrizboton[i][j].setStyle("-fx-background-color: #DADAD7;-fx-border-color: #C2C2C2;"); // se cambian los colores del boton para simbolizar que se revela
        matrizvalores[i][j].revelado = true; // se refleja la revelacion en la matriz
            
    }
    
    static void revelarCerosAdy(int i, int j) { // revela los espacios sin nada adyacentes al espacio que se selecciona
        Cuadricula.revelarAdy(i, j);
        int posoriginali = i; // se guarda la posicion original de i
        int posoriginalj = j; // se guarda la posicion original de j
        while (j >= 0) { // revela hacia la izquierda
            while (i <= 7) { // revela hacia abajo
                if (matrizvalores[i][j].numrev == 0 ) { // si es un cero revela sus adyacentes
                    Cuadricula.revelarAdy(i, j);
                    i++;
                }
                else { // si es algo diferente a cero para el ciclo
                    break;
                }
            }
            i = posoriginali; // se devuelve a la posicion i original
            while (i >= 0) { // revela hacia arriba
                if (matrizvalores[i][j].numrev == 0 ) { // si es un cero revela sus adyacentes
                    Cuadricula.revelarAdy(i, j);
                    i--;
                }
                else { // si es algo diferente a cero para el ciclo
                    break;
                }
            }
            i = posoriginali;
            if (matrizvalores[i][j].numrev == 0 ) { // si es un cero revela sus adyacentes
                Cuadricula.revelarAdy(i, j);
                j--;
            }
            else { // si es algo diferente a cero para el ciclo
                break;
            }
        }
        j = posoriginalj; // se devuelve a la posicion j original
        while (j <= 7) { // revela hacia la derecha
            while (i <= 7) { // revela hacia abajo
                if (matrizvalores[i][j].numrev == 0 ) { // si es un cero revela sus adyacentes
                    Cuadricula.revelarAdy(i, j);
                    i++;
                }
                else { // si es algo diferente a cero para el ciclo
                    break;
                }
            }
            i = posoriginali; // se devuelve a la posicion i original
            while (i >= 0) { // revela hacia arriba
                if (matrizvalores[i][j].numrev == 0 ) { // si es un cero revela sus adyacentes
                    Cuadricula.revelarAdy(i, j);
                    i--;
                }
                else { // si es algo diferente a cero para el ciclo
                    break;
                }
            }
            i = posoriginali; // se devuelve a la posicion i original
            if (matrizvalores[i][j].numrev == 0 ) { // si es un cero revela sus adyacentes
                Cuadricula.revelarAdy(i, j);
                j++;
                }
            else { // si es algo diferente a cero para el ciclo
                break;
            }
        }
    }
    
    static void revelarCeros(int i, int j) { // repite el ciclo de revelar ceros adyacentes en toda la columna
        Cuadricula.revelarCerosAdy(i, j);
        int posoriginali = i; // se guarda la posicion original de i
        int posoriginalj = j; // se guarda la posicion original de j
        while (j >= 0) { // revela hacia la izquierda
            while (i <= 7) { // revela hacia abajo
                if (matrizvalores[i][j].numrev == 0 ) { // si es un cero revela sus adyacentes
                    Cuadricula.revelarCerosAdy(i, j);
                    i++;
                }
                else { // si es algo diferente a cero para el ciclo
                    break;
                }
            }
            i = posoriginali; 
            while (i >= 0) { // revela hacia arriba
                if (matrizvalores[i][j].numrev == 0 ) { // si es un cero revela sus adyacentes
                    Cuadricula.revelarCerosAdy(i, j);
                    i--;
                }
                else { // si es algo diferente a cero para el ciclo
                    break;
                }
            }
            i = posoriginali; // se devuelve a la posicion i original
            if (matrizvalores[i][j].numrev == 0 ) { // si es un cero revela sus adyacentes
                Cuadricula.revelarCerosAdy(i, j);
                j--;
            }
            else { // si es algo diferente a cero para el ciclo
                break;
            }
        }
        j = posoriginalj; // se devuelve a la posicion j original
        while (j <= 7) { // revela hacia la derecha
            while (i <= 7) { // revela hacia abajo
                if (matrizvalores[i][j].numrev == 0 ) { // si es un cero revela sus adyacentes
                    Cuadricula.revelarCerosAdy(i, j);
                    i++;
                }
                else { // si es algo diferente a cero para el ciclo
                    break;
                }
            }
            i = posoriginali; // se devuelve a la posicion i original
            while (i >= 0) { // revela hacia arriba
                if (matrizvalores[i][j].numrev == 0 ) { // si es un cero revela sus adyacentes
                    Cuadricula.revelarCerosAdy(i, j);
                    i--;
                }
                else { // si es algo diferente a cero para el ciclo
                    break;
                }
            }
            i = posoriginali;
            if (matrizvalores[i][j].numrev == 0 ) { // si es un cero revela sus adyacentes
                Cuadricula.revelarCerosAdy(i, j);
                j++;
                }
            else { // si es algo diferente a cero para el ciclo
                break;
            }
        }
    }
    
    static void colorAleatorio(int i, int j, int numrandom) { // genera un color aleatorio para los botones de la matriz
        if (numrandom == 0) {
            matrizboton[i][j].setStyle("-fx-background-color: #0093ff;-fx-border-color: #272323;");
        }
        else if (numrandom == 1) {
            matrizboton[i][j].setStyle("-fx-background-color: #f4f162;-fx-border-color: #272323;");
        }
        else if (numrandom == 2) {
            matrizboton[i][j].setStyle("-fx-background-color: #EF83DA;-fx-border-color: #272323;");
        }
        else if (numrandom == 3) {
            matrizboton[i][j].setStyle("-fx-background-color: #8DEAD0;-fx-border-color: #272323;");
        }
        else if (numrandom == 4) {
            matrizboton[i][j].setStyle("-fx-background-color: #51B0F7;-fx-border-color: #272323;");
        }
        else if (numrandom == 5) {
            matrizboton[i][j].setStyle("-fx-background-color: #B676F3;-fx-border-color: #272323;");
        }
        else if (numrandom == 6) {
            matrizboton[i][j].setStyle("-fx-background-color: #F3B86D;-fx-border-color: #272323;");
        }
        else if (numrandom == 7) {
            matrizboton[i][j].setStyle("-fx-background-color: #ECAB5D;-fx-border-color: #272323;");
        }
    }
    
    static void checkVictoria() {
        int cantrevelados = 0;
        for (int i=0; i<=7; i++) {
            for (int j=0; j<=7; j++) {
                if (matrizvalores[i][j].revelado == true) {
                    cantrevelados++;
                }
            }
        }
        if (64 - cantrevelados == minminas) {
            gameover = true;
            victoria = true;
            for (int i=0; i<=7; i++) {
                for (int j=0; j<=7; j++) {
                    if (esMina(matrizvalores[i][j].mina)) {
                        matrizboton[i][j].setStyle("-fx-background-color: #87F57A;");
                    }
                }
            }
            
        }
    }
}
