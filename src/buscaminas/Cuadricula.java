package buscaminas;

import javafx.scene.control.Button;
import java.util.Random;


public class Cuadricula {
    
    int mina;
    int bandera;
    int numrev;
    static Button [][] matrizboton = new Button[8][8];
    static Cuadricula [][] matrizvalores = new Cuadricula [8][8];
    
    Cuadricula(int mina, int bandera, int numrev){
        this.mina = mina;
        this.bandera = bandera;
        this.numrev = numrev;
    }
    
    static void generarMinas() { //añade minas en orden random a matriz
        Random r = new Random();
        int cantminas = 0; // contador de cantidad de minas
        int minminas = 8; // cantidad de minas que apareceran en cuadricula
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
                    matrizboton[i][j].setText("*"); // revela la mina
                }
            }
        }
                
    }
    
    static boolean esPosValida(int i, int j) {
        if (i < 0 || j < 0 || i > 7 || j > 7){ 
            return false;
        }
        else {
            return true;
        }
    }
    
    static void generarNumAdy() {
        for (int i=0; i<=7; i++) {
            for (int j=0; j<=7; j++) { 
                int contador = 0;
                if (!Cuadricula.esMina(matrizvalores[i][j].mina)) {
                    if (esPosValida(i-1, j-1)) {
                        if (Cuadricula.esMina(matrizvalores[i-1][j-1].mina)) {
                            contador++;
                        }
                    }
                    if (esPosValida(i-1, j)) {
                        if (Cuadricula.esMina(matrizvalores[i-1][j].mina)) {
                            contador++;
                        }
                    }
                    if (esPosValida(i-1, j+1)) {
                        if (Cuadricula.esMina(matrizvalores[i-1][j+1].mina)) {
                            contador++;
                        }
                    }
                    if (esPosValida(i, j-1)) {
                        if (Cuadricula.esMina(matrizvalores[i][j-1].mina)) {
                            contador++;
                        }
                    }
                    if (esPosValida(i, j+1)) {
                        if (Cuadricula.esMina(matrizvalores[i][j+1].mina)) {
                            contador++;
                        }
                    }
                    if (esPosValida(i+1, j-1)) {
                        if (Cuadricula.esMina(matrizvalores[i+1][j-1].mina)) {
                            contador++;
                        }
                    }
                    if (esPosValida(i+1, j)) {
                        if (Cuadricula.esMina(matrizvalores[i+1][j].mina)) {
                            contador++;
                        }
                    }
                    if (esPosValida(i+1, j+1)) {
                        if (Cuadricula.esMina(matrizvalores[i+1][j+1].mina)) {
                            contador++;
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
        if (esPosValida(i-1, j-1)) {
            matrizboton[i-1][j-1].setStyle("-fx-background-color: #FFFFFF;");
            if (matrizvalores[i-1][j-1].numrev > 0) {
                matrizboton[i-1][j-1].setText(Integer.toString(matrizvalores[i-1][j-1].numrev));
            }
        }
        if (esPosValida(i-1, j)) {
            matrizboton[i-1][j].setStyle("-fx-background-color: #FFFFFF;");
            if (matrizvalores[i-1][j].numrev > 0) {
                matrizboton[i-1][j].setText(Integer.toString(matrizvalores[i-1][j].numrev));
            }
        }
        if (esPosValida(i-1, j+1)) {
            matrizboton[i-1][j+1].setStyle("-fx-background-color: #FFFFFF;");
            if (matrizvalores[i-1][j+1].numrev > 0) {
                matrizboton[i-1][j+1].setText(Integer.toString(matrizvalores[i-1][j+1].numrev));
            }
        }
        if (esPosValida(i, j-1)) {
            matrizboton[i][j-1].setStyle("-fx-background-color: #FFFFFF;");
            if (matrizvalores[i][j-1].numrev > 0) {
                matrizboton[i][j-1].setText(Integer.toString(matrizvalores[i][j-1].numrev));
            }
        }
        if (esPosValida(i, j+1)) {
            matrizboton[i][j+1].setStyle("-fx-background-color: #FFFFFF;");
            if (matrizvalores[i][j+1].numrev > 0) {
                matrizboton[i][j+1].setText(Integer.toString(matrizvalores[i][j+1].numrev));
            }
        }
        if (esPosValida(i+1, j-1)) {
            matrizboton[i+1][j-1].setStyle("-fx-background-color: #FFFFFF;");
            if (matrizvalores[i+1][j-1].numrev > 0) {
                matrizboton[i+1][j-1].setText(Integer.toString(matrizvalores[i+1][j-1].numrev));
            }
        }
        if (esPosValida(i+1, j)) {
            matrizboton[i+1][j].setStyle("-fx-background-color: #FFFFFF;");
            if (matrizvalores[i+1][j].numrev > 0) {
                matrizboton[i+1][j].setText(Integer.toString(matrizvalores[i+1][j].numrev));
            }
        }
        if (esPosValida(i+1, j+1)) {
            matrizboton[i+1][j+1].setStyle("-fx-background-color: #FFFFFF;");
            if (matrizvalores[i+1][j+1].numrev > 0) {
                matrizboton[i+1][j+1].setText(Integer.toString(matrizvalores[i+1][j+1].numrev));
            }
        }
        matrizboton[i][j].setStyle("-fx-background-color: #FFFFFF;");
            
    }
    
    static void revelarCerosAdy(int i, int j) {
        Cuadricula.revelarAdy(i, j);
        int posoriginali = i;
        int posoriginalj = j;
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
            i = posoriginali;
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
        j = posoriginalj;
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
            i = posoriginali;
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
                j++;
                }
            else { // si es algo diferente a cero para el ciclo
                break;
            }
        }
    }
    
    static void revelarCeros(int i, int j) {
        int posoriginali = i;
        Cuadricula.revelarCerosAdy(i, j);
        while (i <= 7) { // revela hacia abajo
            if (matrizvalores[i][j].numrev == 0 ) { // si es un cero revela sus adyacentes
                Cuadricula.revelarAdy(i, j);
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
                Cuadricula.revelarAdy(i, j);
                Cuadricula.revelarCerosAdy(i, j);
                i--;
            }
            else { // si es algo diferente a cero para el ciclo
                break;
            }
        } 
    }
}
