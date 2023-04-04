package buscaminas;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.Random;

/**
 * Clase cuadricula que contiene valores de la cuadricula. 
 * Se tienen métodos que realizan cambios a dichos
 * valores lo cual hace que cambie la cuadricula del juego. 
 * @author Jose Pablo Umaña Vega
 */
public class Cuadricula{
    
    int mina; // minas en la cuadricula, es un valor binario. 0 si no es mina, 1 si es mina. 
    int bandera; // banderas en la cuadricula, es un valor binario. 0 si no hay bandera, 1 si hay bandera. 
    int numrev; // numero de minas adyacentes en el espacio, si hay un -1, significa que hay una mina en el espacio
    boolean revelado; // se determina si el espacio esta revelado
    static int cantbanderas = 0; // cantidad de banderas que el usuario ha puesto en la cuadricula
    static int countersec = 0; // cantidad de segundos transcurridos 
    static int countermin = 0; // cantidad de minutos transcurridos 
    static int cantturnos = 0; // cantidad de turnos del usuario jugados 
    static int cantsugerencias = 0; // cantidad de sugerencias habilitadas para el usuario
    static boolean gameover = false; // variable que determina si el juego se ha acabado 
    static boolean victoria = false; // variable que determina si el juego se ha ganado 
    static Button [][] matrizboton = new Button[8][8]; // arreglo en dos dimensiones (matriz) de botones 
    static Cuadricula [][] matrizvalores = new Cuadricula [8][8]; // arreglo en dos dimensiones (matriz) de los valores 
    static Stack stacksugerencia = new Stack(); // pila de espacios que se van a sugerir al usuario 
    static Button botonreset; // boton para hacer reset
    static Label labelcantminasencontradas; // label para representar la cantidad de minas encontradas 
    static Label labelcantsugerencias; // label para representar la cantidad de sugerencias habilitadas para el usuario 
    
    Cuadricula(int mina, int bandera, int numrev, boolean revelado){ // constructor para objeto de la cuadricula 
        this.mina = mina; // minas en la cuadricula, es un valor binario. 0 si no es mina, 1 si es mina.
        this.bandera = bandera; // banderas en la cuadricula, es un valor binario. 0 si no hay bandera, 1 si hay bandera. 
        this.numrev = numrev; // numero de minas adyacentes en el espacio 
        this.revelado = revelado; // se determina si el espacio esta revelado
    }
    
    static int minminas = 8; // cantidad de minas que apareceran en cuadricula
    /**
     * Añade minas en espacios aleatorios en la matriz de valores. 
     * El metodo depende de la variable minminas. Si esta
     * cambia la cantidad de minas en la matriz cambia de igual manera. 
     */
    public static void generarMinas() { //añade minas en orden random a matriz
        Random r = new Random();
        int cantminas = 0; // contador de cantidad de minas
        while (cantminas < minminas){ 
            for (int i=0; i<=7; i++) {
                for (int j=0; j<=7; j++) { // indices para recorrer matriz
                    int numrandom = r.nextInt(8); //num random del 1 al 8
                    if (cantminas == minminas){ // si ya se tienen las minas requeridas, se para el loop
                        break;
                    }
                    else { // si aun falta poner minas 
                        if (numrandom == 1) { //si el numero random es un 1, se pone una mina en el espacio
                            if (matrizvalores[i][j].mina == 0){ // si no hay una mina en el espacio
                                matrizvalores[i][j].mina = 1; // se pone una mina 
                                cantminas++; // se aumenta la cantidad de minas
                            }
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Determina si un espacio es una mina, 
     * dado del valor del espacio en la matriz
     * de valores. 
     * @param x la variable mina en un espacio determinado de la matriz valores
     * @return true si es una mina, false en caso contrario
     */
    public static boolean esMina(int x) { // se fija si hay una mina en la cuadricula
        if (x==1 || x==-1) { // si x es 1 o -1, significa que hay una mina en el espacio
            return true; // retorna true si la hay 
        }
        else {
            return false; // false en caso contrario
        }
    }
    
    /**
     * Revela los espacios en los que haya minas. 
     * Dichos espacio se ponen de color rojo y 
     * se les pone un texto con * para representar
     * la mina
     */
    public static void revelarMinas() { //revela las minas de la cuadricula (Game over)
        for (int i=0; i<=7; i++) {
            for (int j=0; j<=7; j++) { // indice para recorrer cuadricula
                if (Cuadricula.esMina(matrizvalores[i][j].mina)) { // se fija si hay una mina
                    matrizboton[i][j].setText("*"); // cambia el texto del boton para simbolizar una mina
                    matrizboton[i][j].setStyle("-fx-background-color: #F00000;"); // cambia el color a rojo 
                }
            }
        }
                
    }
    
    /**
     * Revela los espacios en los que haya minas. 
     * Dichos espacio se ponen del color que representa al dummy y 
     * se les pone un texto con * para representar
     * la mina
     */
    public static void revelarMinasDummy(){
        for (int i=0; i<=7; i++) {
            for (int j=0; j<=7; j++) { // indice para recorrer cuadricula
                if (Cuadricula.esMina(matrizvalores[i][j].mina)) { // se fija si hay una mina
                    matrizboton[i][j].setText("*"); // cambia el texto del boton para simbolizar una mina
                    matrizboton[i][j].setStyle("-fx-background-color: #0027FF;-fx-text-fill: white;"); // cambia el color a azul y el texto a blanco
                }
            }
        }
    }
    
    /**
     * Revela los espacios en los que haya minas. 
     * Dichos espacio se ponen del color que representa
     * al avanzado y se les pone un texto con * para 
     * representar la mina
     */
    public static void revelarMinasAvanzado(){
        for (int i=0; i<=7; i++) {
            for (int j=0; j<=7; j++) { // indice para recorrer cuadricula
                if (Cuadricula.esMina(matrizvalores[i][j].mina)) { // se fija si hay una mina
                    matrizboton[i][j].setText("*"); // cambia el texto del boton para simbolizar una mina
                    matrizboton[i][j].setStyle("-fx-background-color: #DA0000;-fx-text-fill: white;"); // cambia el color a rojo y el texto a blanco
                }
            }
        }
    }
    
    /**
     * Determina si un espacio se encuentra
     * dentro de un arreglo en dos dimensiones
     * con las dimensiones especificas 8x8.
     * @param i fila en la que se encuentra el espacio
     * @param j columna en la que se encuentra el espacio
     * @return true si el espacio esta dentro del arreglo, false en caso contrario
     */
    public static boolean esPosValida(int i, int j) {
        if (i < 0 || j < 0 || i > 7 || j > 7){ // se fija que los indices no se salgan del array
            return false; // si se salen retorna false
        }
        else {
            return true; // en caso contrario retorna true
        }
    }
    
    /**
     * Se generan los numeros de minas 
     * adyacentes y se agregan a la variable
     * numrev en la matriz de valores. Esto
     * se hace para todos los espacios en la matriz. 
     */
    public static void generarNumAdy() { // genera los numeros de minas adyacentes y los añade a matrizvalores.numrev
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
                    matrizvalores[i][j].numrev = contador; // se asigna el contador a la variable numrev en la matriz de valores
                }
                else { // si el espacio es una mina
                    matrizvalores[i][j].numrev = -1; // se le asigna un -1 para representar que hay una mina
                }
               
            }
        }
    }
    
    /**
     * Se revelan los numeros inmediatamente
     * adyacentes a un espacio. Los botones
     * asociados a los espacios se les asigna el
     * color gris para simbolizar que se han revelado, 
     * y si la cantidad de minas adyacentes es diferente
     * de 0, se les pone la cantidad de minas adyacentes. 
     * @param i fila del espacio que se quiere revelar sus adyacentes
     * @param j columna del espacio que se quiere revelar sus adyacentes
     */
    public static void revelarAdy(int i, int j) {
        if (esPosValida(i-1, j-1)) { // se revisa que los indices no se salgan del array
            matrizboton[i-1][j-1].setStyle("-fx-background-color: #DADAD7;-fx-border-color: #C2C2C2;"); // se cambian los colores del boton para simbolizar que se revela
            matrizvalores[i-1][j-1].revelado = true; // se refleja la revelacion en la matriz
            if (matrizvalores[i-1][j-1].numrev > 0) { // si el numero de cantidad de minas adyacentes es mayor a cero
                matrizboton[i-1][j-1].setText(Integer.toString(matrizvalores[i-1][j-1].numrev)); // se asigna el texto al boton de la cantidad de minas adyacentes
            }
        }
        if (esPosValida(i-1, j)) { // se revisa que los indices no se salgan del array
            matrizboton[i-1][j].setStyle("-fx-background-color: #DADAD7;-fx-border-color: #C2C2C2;"); // se cambian los colores del boton para simbolizar que se revela
            matrizvalores[i-1][j].revelado = true; // se refleja la revelacion en la matriz
            if (matrizvalores[i-1][j].numrev > 0) { // si el numero de cantidad de minas adyacentes es mayor a cero
                matrizboton[i-1][j].setText(Integer.toString(matrizvalores[i-1][j].numrev));  // se asigna el texto al boton de la cantidad de minas adyacentes
            }
        }
        if (esPosValida(i-1, j+1)) { // se revisa que los indices no se salgan del array
            matrizboton[i-1][j+1].setStyle("-fx-background-color: #DADAD7;-fx-border-color: #C2C2C2;"); // se cambian los colores del boton para simbolizar que se revela
            matrizvalores[i-1][j+1].revelado = true; // se refleja la revelacion en la matriz
            if (matrizvalores[i-1][j+1].numrev > 0) { // si el numero de cantidad de minas adyacentes es mayor a cero
                matrizboton[i-1][j+1].setText(Integer.toString(matrizvalores[i-1][j+1].numrev));  // se asigna el texto al boton de la cantidad de minas adyacentes
            }
        }
        if (esPosValida(i, j-1)) { // se revisa que los indices no se salgan del array
            matrizboton[i][j-1].setStyle("-fx-background-color: #DADAD7;-fx-border-color: #C2C2C2;"); // se cambian los colores del boton para simbolizar que se revela
            matrizvalores[i][j-1].revelado = true; // se refleja la revelacion en la matriz
            if (matrizvalores[i][j-1].numrev > 0) { // si el numero de cantidad de minas adyacentes es mayor a cero
                matrizboton[i][j-1].setText(Integer.toString(matrizvalores[i][j-1].numrev));  // se asigna el texto al boton de la cantidad de minas adyacentes
            }
        }
        if (esPosValida(i, j+1)) { // se revisa que los indices no se salgan del array
            matrizboton[i][j+1].setStyle("-fx-background-color: #DADAD7;-fx-border-color: #C2C2C2;"); // se cambian los colores del boton para simbolizar que se revela
            matrizvalores[i][j+1].revelado = true; // se refleja la revelacion en la matriz
            if (matrizvalores[i][j+1].numrev > 0) { // si el numero de cantidad de minas adyacentes es mayor a cero
                matrizboton[i][j+1].setText(Integer.toString(matrizvalores[i][j+1].numrev));  // se asigna el texto al boton de la cantidad de minas adyacentes
            }
        }
        if (esPosValida(i+1, j-1)) { // se revisa que los indices no se salgan del array
            matrizboton[i+1][j-1].setStyle("-fx-background-color: #DADAD7;-fx-border-color: #C2C2C2;"); // se cambian los colores del boton para simbolizar que se revela
            matrizvalores[i+1][j-1].revelado = true; // se refleja la revelacion en la matriz
            if (matrizvalores[i+1][j-1].numrev > 0) { // si el numero de cantidad de minas adyacentes es mayor a cero
                matrizboton[i+1][j-1].setText(Integer.toString(matrizvalores[i+1][j-1].numrev));  // se asigna el texto al boton de la cantidad de minas adyacentes
            }
        }
        if (esPosValida(i+1, j)) { // se revisa que los indices no se salgan del array
            matrizboton[i+1][j].setStyle("-fx-background-color: #DADAD7;-fx-border-color: #C2C2C2;"); // se cambian los colores del boton para simbolizar que se revela
            matrizvalores[i+1][j].revelado = true; // se refleja la revelacion en la matriz
            if (matrizvalores[i+1][j].numrev > 0) { // si el numero de cantidad de minas adyacentes es mayor a cero
                matrizboton[i+1][j].setText(Integer.toString(matrizvalores[i+1][j].numrev));  // se asigna el texto al boton de la cantidad de minas adyacentes
            }
        }
        if (esPosValida(i+1, j+1)) { // se revisa que los indices no se salgan del array
            matrizboton[i+1][j+1].setStyle("-fx-background-color: #DADAD7;-fx-border-color: #C2C2C2;"); // se cambian los colores del boton para simbolizar que se revela
            matrizvalores[i+1][j+1].revelado = true; // se refleja la revelacion en la matriz
            if (matrizvalores[i+1][j+1].numrev > 0) { // si el numero de cantidad de minas adyacentes es mayor a cero
                matrizboton[i+1][j+1].setText(Integer.toString(matrizvalores[i+1][j+1].numrev));  // se asigna el texto al boton de la cantidad de minas adyacentes
            }
        }
        matrizboton[i][j].setStyle("-fx-background-color: #DADAD7;-fx-border-color: #C2C2C2;"); // se cambian los colores del boton para simbolizar que se revela
        matrizvalores[i][j].revelado = true; // se refleja la revelacion en la matriz
            
    }
    
    /**
     * Revela todos los ceros adyacentes
     * a un espacio seleccionado. Este metodo
     * se llama unicamente si el espacio que
     * se selecciona es un 0.
     * @param i fila donde se encuentra el espacio 
     * @param j columna donde se encuentra el espacio
     */
    public static void revelarCerosAdy(int i, int j) { // revela los espacios con 0 cantidad de minas adyacentes, adyacentes al espacio que se selecciona
        Cuadricula.revelarAdy(i, j); // se revelan los espacios inmediatamente adyacente
        int posoriginali = i; // se guarda la posicion original de i
        int posoriginalj = j; // se guarda la posicion original de j
        while (j >= 0) { // revela hacia la izquierda
            while (i <= 7) { // revela hacia abajo
                if (matrizvalores[i][j].numrev == 0 ) { // si es un cero revela sus adyacentes
                    Cuadricula.revelarAdy(i, j); // se revelan los espacios inmediatamente adyacentes
                    i++; // se mueve una fila hacia abajo
                }
                else { // si es algo diferente a cero para el ciclo
                    break;
                }
            }
            i = posoriginali; // se devuelve a la posicion i original
            while (i >= 0) { // revela hacia arriba
                if (matrizvalores[i][j].numrev == 0 ) { // si es un cero revela sus adyacentes
                    Cuadricula.revelarAdy(i, j); // se revelan los espacios inmediatamente adyacentes
                    i--; // se mueve una fila hacia arriba
                }
                else { // si es algo diferente a cero para el ciclo
                    break;
                }
            }
            i = posoriginali;
            if (matrizvalores[i][j].numrev == 0 ) { // si es un cero revela sus adyacentes
                Cuadricula.revelarAdy(i, j); // se revelan los espacios inmediatamente adyacentes
                j--; // se mueve una columna a la izquierda
            }
            else { // si es algo diferente a cero para el ciclo
                break;
            }
        }
        j = posoriginalj; // se devuelve a la posicion j original
        while (j <= 7) { // revela hacia la derecha
            while (i <= 7) { // revela hacia abajo
                if (matrizvalores[i][j].numrev == 0 ) { // si es un cero revela sus adyacentes
                    Cuadricula.revelarAdy(i, j); // se revelan los espacios inmediatamente adyacentes
                    i++; // se mueve una fila hacia abajo 
                }
                else { // si es algo diferente a cero para el ciclo
                    break;
                }
            }
            i = posoriginali; // se devuelve a la posicion i original
            while (i >= 0) { // revela hacia arriba
                if (matrizvalores[i][j].numrev == 0 ) { // si es un cero revela sus adyacentes
                    Cuadricula.revelarAdy(i, j); // se revelan los espacios inmediatamente adyacentes
                    i--; // se mueve una fila hacia arriba 
                }
                else { // si es algo diferente a cero para el ciclo
                    break;
                }
            }
            i = posoriginali; // se devuelve a la posicion i original
            if (matrizvalores[i][j].numrev == 0 ) { // si es un cero revela sus adyacentes
                Cuadricula.revelarAdy(i, j); // se revelan los espacios inmediatamente adyacentes
                j++; // se mueve una columna a la derecha
                }
            else { // si es algo diferente a cero para el ciclo
                break;
            }
        }
    }
    
    /**
     * Este metodo igualmente revela todos los
     * ceros adyacentes al espacio que se elije,
     * pero cada vez que va a revelar llama al metodo
     * revelarCerosAdy para que se ejecute el metodo
     * en cada uno de los espacios para asegurarse que
     * todos los ceros sean revelados. 
     * @param i fila donde se encuentra el espacio 
     * @param j columna donde se encuentra el espacio 
     */
    public static void revelarCeros(int i, int j) { // repite el ciclo de revelar ceros adyacentes en toda la columna
        Cuadricula.revelarCerosAdy(i, j);
        int posoriginali = i; // se guarda la posicion original de i
        int posoriginalj = j; // se guarda la posicion original de j
        while (j >= 0) { // revela hacia la izquierda
            while (i <= 7) { // revela hacia abajo
                if (matrizvalores[i][j].numrev == 0 ) { // si es un cero revela sus adyacentes
                    Cuadricula.revelarCerosAdy(i, j); // se llama al metodo para realizar todo el proceso en el espacio 
                    i++; // se mueve una fila hacia abajo 
                }
                else { // si es algo diferente a cero para el ciclo
                    break;
                }
            }
            i = posoriginali; // vuelve a la posicion original 
            while (i >= 0) { // revela hacia arriba
                if (matrizvalores[i][j].numrev == 0 ) { // si es un cero revela sus adyacentes
                    Cuadricula.revelarCerosAdy(i, j); // se llama al metodo para realizar todo el proceso en el espacio 
                    i--; // se mueve una fila hacia arriba 
                }
                else { // si es algo diferente a cero para el ciclo
                    break;
                }
            }
            i = posoriginali; // se devuelve a la posicion i original
            if (matrizvalores[i][j].numrev == 0 ) { // si es un cero revela sus adyacentes
                Cuadricula.revelarCerosAdy(i, j); // se llama al metodo para realizar todo el proceso en el espacio 
                j--; // se mueve una columna a la izquierda 
            }
            else { // si es algo diferente a cero para el ciclo
                break;
            }
        }
        j = posoriginalj; // se devuelve a la posicion j original
        while (j <= 7) { // revela hacia la derecha
            while (i <= 7) { // revela hacia abajo
                if (matrizvalores[i][j].numrev == 0 ) { // si es un cero revela sus adyacentes
                    Cuadricula.revelarCerosAdy(i, j); // se llama al metodo para realizar todo el proceso en el espacio 
                    i++; // se mueve una fila hacia abajo 
                }
                else { // si es algo diferente a cero para el ciclo
                    break;
                }
            }
            i = posoriginali; // se devuelve a la posicion i original
            while (i >= 0) { // revela hacia arriba
                if (matrizvalores[i][j].numrev == 0 ) { // si es un cero revela sus adyacentes
                    Cuadricula.revelarCerosAdy(i, j); // se llama al metodo para realizar todo el proceso en el espacio 
                    i--; // se mueve una fila hacia arriba 
                }
                else { // si es algo diferente a cero para el ciclo
                    break;
                }
            }
            i = posoriginali; // vuelve a la posicion en la fila original 
            if (matrizvalores[i][j].numrev == 0 ) { // si es un cero revela sus adyacentes
                Cuadricula.revelarCerosAdy(i, j); // se llama al metodo para realizar todo el proceso en el espacio 
                j++; // se mueve una columna a la derecha 
                }
            else { // si es algo diferente a cero para el ciclo
                break;
            }
        }
    }
    
    /**
     * Se asigna un color al boton en
     * ma matriz de botones dependiendo
     * del numero que se de en el parametro.
     * @param i fila del espacio al que se le quiere cambiar el color 
     * @param j columna del espacio al que se le quiere cambiar el color 
     * @param numrandom numero aleatorio del 0 al 7
     */
    public static void colorAleatorio(int i, int j, int numrandom) { // genera un color aleatorio para los botones de la matriz
        if (numrandom == 0) { // si el numero aleatorio es 0
            matrizboton[i][j].setStyle("-fx-background-color: #0093ff;-fx-border-color: #272323;"); // se cambia el color del boton 
        }
        else if (numrandom == 1) { // si el numero aleatorio es 1
            matrizboton[i][j].setStyle("-fx-background-color: #f4f162;-fx-border-color: #272323;"); // se cambia el color del boton 
        }
        else if (numrandom == 2) { // si el numero aleatorio es 2
            matrizboton[i][j].setStyle("-fx-background-color: #EF83DA;-fx-border-color: #272323;"); // se cambia el color del boton 
        }
        else if (numrandom == 3) { // si el numero aleatorio es 3
            matrizboton[i][j].setStyle("-fx-background-color: #8DEAD0;-fx-border-color: #272323;"); // se cambia el color del boton 
        }
        else if (numrandom == 4) { // si el numero aleatorio es 4
            matrizboton[i][j].setStyle("-fx-background-color: #51B0F7;-fx-border-color: #272323;"); // se cambia el color del boton 
        }
        else if (numrandom == 5) { // si el numero aleatorio es 5
            matrizboton[i][j].setStyle("-fx-background-color: #B676F3;-fx-border-color: #272323;"); // se cambia el color del boton 
        }
        else if (numrandom == 6) { // si el numero aleatorio es 6
            matrizboton[i][j].setStyle("-fx-background-color: #F3B86D;-fx-border-color: #272323;"); // se cambia el color del boton 
        }
        else if (numrandom == 7) { // si el numero aleatorio es 7
            matrizboton[i][j].setStyle("-fx-background-color: #ECAB5D;-fx-border-color: #272323;"); // se cambia el color del boton 
        }
    }
    
    /**
     * Se verifica si se ha ganado
     * el juego dados los valores
     * de la matriz de valores al 
     * momento de llamar el metodo. 
     */
    public static void checkVictoria() {
        int cantrevelados = 0; // cantidad de espacios que se han revelado 
        for (int i=0; i<=7; i++) {
            for (int j=0; j<=7; j++) {
                if (matrizvalores[i][j].revelado == true) { // si un espacio se ha revelado 
                    cantrevelados++; // se agrega uno al contador 
                }
            }
        }
        if (64 - cantrevelados == minminas) { // si la cantidad de espacios en total de la cuadricula menos la cantidad de revelados es igual a la cantidad de minas en la cuadricula
            gameover = true; // se acaba el juego 
            victoria = true; // se gana el juego 
            for (int i=0; i<=7; i++) {
                for (int j=0; j<=7; j++) {
                    if (esMina(matrizvalores[i][j].mina)) { // se revelan las minas de color verde 
                        matrizboton[i][j].setStyle("-fx-background-color: #87F57A;");
                    }
                }
            }
            
        }
    }
    
    /**
     * Se actualiza la cuadricula para representar
     * los botones que ya se hayan revelado. Dichos
     * botones se les asigna el color gris para simbolizar
     * que se han revelado. 
     */
    public static void updateCuadricula() {
        for (int i=0; i<=7; i++) {
            for (int j=0; j<=7; j++) {
                if (matrizvalores[i][j].revelado == true) { // si el espacio ya esta revelado 
                    matrizboton[i][j].setStyle("-fx-background-color: #DADAD7;-fx-border-color: #C2C2C2;"); // se le asigna el color gris al boton 
                }
            }
        }
    }
    
    /**
     * Se determina la cantidad de minas
     * adyacentes a un espacio utilizando
     * como base la lista enlazada de minas.
     * Esto quiere decir que depende de los
     * espacios que se hayan revelado. Se utiliza
     * para generar la lista segura de la computadora. 
     * @param i fila en la que se encuentra el espacio 
     * @param j columna en la que se encuentra el espacio
     * @return cantidad de minas adyacentes a un espacio, tomando en cuenta los espacios que se han revelado
     */
    public static int cantMinasAdy(int i, int j) {
        int cantminas = 0; // contador de cantidad de minas adyacentes
        if (esPosValida(i, j+1)) { // se revisa que los indices no se salgan del array
            if (Computadora.listaminas.contains(i, j+1)) { // si el espacio esta en la lista de minas
                cantminas++; // se agrega uno al contador
            }
        }
        if (esPosValida(i+1, j+1)) { // se revisa que los indices no se salgan del array
            if (Computadora.listaminas.contains(i+1, j+1)) { // si el espacio esta en la lista de minas
                cantminas++; // se agrega uno al contador
            }
        }
        if (esPosValida(i-1, j+1)) { // se revisa que los indices no se salgan del array
            if (Computadora.listaminas.contains(i-1, j+1)) { // si el espacio esta en la lista de minas
                cantminas++; // se agrega uno al contador
            }
        }
        if (esPosValida(i+1, j)) { // se revisa que los indices no se salgan del array
            if (Computadora.listaminas.contains(i+1, j)) { // si el espacio esta en la lista de minas
                cantminas++; // se agrega uno al contador
            }
        }
        if (esPosValida(i-1, j)) { // se revisa que los indices no se salgan del array
            if (Computadora.listaminas.contains(i-1, j)) { // si el espacio esta en la lista de minas
                cantminas++; // se agrega uno al contador
            }
        }
        if (esPosValida(i, j-1)) { // se revisa que los indices no se salgan del array
            if (Computadora.listaminas.contains(i, j-1)) { // si el espacio esta en la lista de minas
                cantminas++; // se agrega uno al contador
            }
        }
        if (esPosValida(i+1, j-1)) { // se revisa que los indices no se salgan del array
            if (Computadora.listaminas.contains(i+1, j-1)) { // si el espacio esta en la lista de minas
                cantminas++; // se agrega uno al contador
            }
        }
        if (esPosValida(i-1, j-1)) { // se revisa que los indices no se salgan del array
            if (Computadora.listaminas.contains(i-1, j-1)) { // si el espacio esta en la lista de minas
                cantminas++; // se agrega uno al contador
            }
        }
        return cantminas; // retorna el contador de minas dayacentes
    }
    
    /**
     * Cantidad de espacios inmediatamente adyacentes
     * que se han revelado, dado cierto
     * espacio en la cuadricula. 
     * @param i fila en la que se encuentra el espacio 
     * @param j columna en la que se encuentra el espacio 
     * @return cantidad de espacios inmediatamente adyacentes revelados 
     */
    public static int cantidadReveladosAdy(int i, int j) {
        int cantrevelados = 0; // contador de cantidad de espacios adyacentes revelados 
        if (esPosValida(i, j+1)) { // se revisa que los indices no se salgan del array
            if (matrizvalores[i][j+1].revelado == true) { // si el espacio ya esta revelado 
                cantrevelados++; // se agrega 1 al contador 
            }
        }
        if (esPosValida(i+1, j+1)) { // se revisa que los indices no se salgan del array
            if (matrizvalores[i+1][j+1].revelado == true) { // si el espacio ya esta revelado 
                cantrevelados++; // se agrega 1 al contador 
            }
        }
        if (esPosValida(i-1, j+1)) { // se revisa que los indices no se salgan del array
            if (matrizvalores[i-1][j+1].revelado == true) { // si el espacio ya esta revelado 
                cantrevelados++; // se agrega 1 al contador 
            }
        }
        if (esPosValida(i+1, j)) { // se revisa que los indices no se salgan del array
            if (matrizvalores[i+1][j].revelado == true) { // si el espacio ya esta revelado 
                cantrevelados++; // se agrega 1 al contador 
            }
        }
        if (esPosValida(i-1, j)) { // se revisa que los indices no se salgan del array
            if (matrizvalores[i-1][j].revelado == true) { // si el espacio ya esta revelado 
                cantrevelados++; // se agrega 1 al contador 
            }
        }
        if (esPosValida(i, j-1)) { // se revisa que los indices no se salgan del array
            if (matrizvalores[i][j-1].revelado == true) { // si el espacio ya esta revelado 
                cantrevelados++; // se agrega 1 al contador 
            }
        }
        if (esPosValida(i+1, j-1)) { // se revisa que los indices no se salgan del array
            if (matrizvalores[i+1][j-1].revelado == true) { // si el espacio ya esta revelado 
                cantrevelados++; // se agrega 1 al contador 
            }
        }
        if (esPosValida(i-1, j-1)) { // se revisa que los indices no se salgan del array
            if (matrizvalores[i-1][j-1].revelado == true) { // si el espacio ya esta revelado 
                cantrevelados++; // se agrega 1 al contador 
            }
        }
        return cantrevelados; // retorna la cantidad de espacios revelados inmediatamente adyacentes
    }
    
    /**
     * Se genera la pila de sugerencias
     * utilizando la lista de espacios seguros. 
     * Obtiene todos los valores de la lista
     * segura y se les hace push a la pila. 
     */
    public static void generarStackSugerencias() {
        Computadora.generarListaMinas(); // se genera la lista de minas 
        Computadora.generarListaGeneral(); // se genera la lista general 
        Computadora.generarListasSegIncertNoPrint(); // se generan las listas segura e incertidumbre 
        if (Computadora.listasegura.isEmpty() == false) { // si la lista segura esta vacia no se hace nada 
            stacksugerencia.push(Computadora.listasegura.getFirst()); // se le hace push al valor de la lista segura con el indice del contador
        }
    }
    
    /**
     * Vacia una pila 
     * @param stack una pila
     */
    public static void emptyStack(Stack stack) {
        while (stack.size() > 0) { // mientas que el tamaño sea mayor a 0
            stack.pop(); // se le hace pop a la pila 
        }
    }
    
    /**
     * Se imprimen los valores en la consola
     * de una lista enlazada de arrays de tamaño 2.  
     * @param lista una lista enlazada de arrays de tamaño 2
     * @param nombrelista nombre de la lista 
     */
    public static void printLista(ListaEnlazada lista, String nombrelista) {
        int counter = 0; // contador 
        System.out.print("Contenido de " + nombrelista + " ");
        while (counter < lista.size()) { // mientras que el contador sea menor al tamaño de la lista
            System.out.print("(" + lista.get(counter)[0] + "," + lista.get(counter)[1] + ")"); // se imprimen los dos valores del array entre parentesis
            counter++; // se incrementa el contador 
        }
    }
}
