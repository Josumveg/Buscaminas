package buscaminas;

import java.io.IOException;
import java.util.Random;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.input.*;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 * @author Jose Pablo Umaña Vega
 * @version 19.0.2
 */
public class main extends Application{
    
    /**
     * Este programa crea una ventana utilizando java fx, en la cual se puede
     * jugar buscaminas con tres modalidades diferentes. Se puede jugar solo
     * o contra la computadora en dos formas. 
     * @param args 
     */
    
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Metodo que crea la ventana, labels y botones. Igualmente 
     * cambia los valores de dichos elementos para 
     * obtener la ventana resultante.
     * @param primaryStage
     * @throws Exception 
     */
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        /**
         * Se determina el titulo de la ventana
         */
        primaryStage.setTitle("Buscaminas");
        
        /**
         * Se crea el tipo de layout que se va a usar 
         * para la ventana
         */
        // Layout
        StackPane layout = new StackPane();
        
        /**
         * Se determina el tamaño del escenario y 
         * se agrega a la vemtama. 
         */
        // Escenario
        Scene scene = new Scene(layout, 500, 650);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Creacion de labels
        /**
         * Se crean los labels que se utilizan para mostrar 
         * toda la informacion necesaria al jugador
         */
        Label labelminasencontradas = new Label();
        labelminasencontradas.setText("Minas encontradas");
        labelminasencontradas.setTranslateX(125);
        labelminasencontradas.setTranslateY(-230);
        
        /**
         * El label que representa la cantidad de minas encontradas
         * se declara en la clase Cuadricula, esto es
         * porque es necesario cambiar el texto del label
         * desde metodos externos. Este label representa la cantidad
         * de banderas que el usuario ha puesto. 
         */
        Cuadricula.labelcantminasencontradas = new Label();
        Cuadricula.labelcantminasencontradas.setText("0");
        Cuadricula.labelcantminasencontradas.setAlignment(Pos.CENTER);
        Cuadricula.labelcantminasencontradas.setStyle("-fx-background-color: #DADAD7;");
        Cuadricula.labelcantminasencontradas.setMaxSize(50, 30);
        Cuadricula.labelcantminasencontradas.setTranslateX(125);
        Cuadricula.labelcantminasencontradas.setTranslateY(-200);
        
        /**
         * El label tiempo es un texto que
         * indica al usuario donde se encuentra
         * el tiempo transcurrido. 
         */
        Label labeltiempo = new Label();
        labeltiempo.setText("Tiempo");
        labeltiempo.setTranslateX(-125);
        labeltiempo.setTranslateY(-230);
        
        /**
         * El label de cantidad de tiempo representa
         * la cantidad de tiempo que ha transcurrido
         * desde el inicio de la aplicacion, o desde
         * la ultima vez que se haya reiniciado.
         */
        Label labelcanttiempo = new Label();
        labelcanttiempo.setText("");
        labelcanttiempo.setTranslateX(-125);
        labelcanttiempo.setTranslateY(-200);
        labelcanttiempo.setAlignment(Pos.CENTER);
        labelcanttiempo.setStyle("-fx-background-color: #DADAD7;");
        labelcanttiempo.setMaxSize(50, 30);
        
        /**
         * El label elegir modo es un texto
         * que indica al usuario donde elegir
         * el modo de juego
         */
        Label labelelegirmodo = new Label();
        labelelegirmodo.setText("Elegir contrincante");
        labelelegirmodo.setAlignment(Pos.TOP_CENTER);
        labelelegirmodo.setStyle("-fx-background-color: #DADAD7;");
        labelelegirmodo.setMaxSize(150,75);
        labelelegirmodo.setTranslateX(-125);
        labelelegirmodo.setTranslateY(275);
        
        /**
         * El label error presenta anuncios generales
         * al usuario cuando algo no se puede hacer. 
         */
        Label labelerror = new Label();
        labelerror.setText("");
        labelerror.setStyle("-fx-background-color: #FF0000;-fx-text-fill: white;");
        labelerror.setTranslateX(0);
        labelerror.setTranslateY(-275);
        
        /**
         * El label de cantidad de sugerencias
         * lleva la cuenta de las sugerencias que 
         * el usuario puede pedir. 
         */
        Cuadricula.labelcantsugerencias = new Label();
        Cuadricula.labelcantsugerencias.setText("0");
        Cuadricula.labelcantsugerencias.setTranslateX(200);
        Cuadricula.labelcantsugerencias.setTranslateY(275);
        
        /**
         * Implementacion de un timer para llevar
         * la cuenta del tiempo desde que se empezo
         * el juego o el ultimo reinicio. El timer
         * llega solo hasta 59 minutos, 59 segundos. 
         */
        // Implementacion de Timer 
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            /**
             * Metodo de la herramienta TimerTask
             * que hace que cada segundo se actualiza el
             * label de cantidad de tiempo para representar
             * el tiempo que ha transcurrido
             */
            @Override
            public void run() {
                if (Cuadricula.countersec == 59) { // si la cantidad de segundos ya llegado al minuto 
                    Cuadricula.countermin++; // se agrega un minuto al contador 
                    Cuadricula.countersec = -1; // el contador vuelve a -1 
                }
                if (Cuadricula.gameover == false) { // se verifica que el juego no se haya terminado 
                    if (Cuadricula.countermin==0) { // si no ha pasado un minuto o mas
                        if (Cuadricula.countersec < 9) { // si han pasado menos de 9 segundos
                            Platform.runLater(() -> labelcanttiempo.setText("00:0" + Integer.toString(Cuadricula.countersec))); // se actualiza el tiempo en el label de tiempo 
                            Cuadricula.countersec++; // se añade un segundo al contador
                        }
                        else { // si han pasado mas de 9 segundos
                            Platform.runLater(() -> labelcanttiempo.setText("00:" + Integer.toString(Cuadricula.countersec))); // se actualiza el tiempo en el label de tiempo 
                            Cuadricula.countersec++; // se añade un segundo al contador
                        }
                    }
                    else if (Cuadricula.countermin < 9) { // si han pasado menos de 9 minutos 
                        if (Cuadricula.countersec < 9) { // si han pasado menos de 9 segundos
                            Platform.runLater(() -> labelcanttiempo.setText("0" + Integer.toString(Cuadricula.countermin) + ":0" + Integer.toString(Cuadricula.countersec))); // se actualiza el tiempo en el label de tiempo 
                            Cuadricula.countersec++; // se añade un segundo al contador
                        }
                        else { // si han pasado mas de 9 segundos 
                            Platform.runLater(() -> labelcanttiempo.setText("0" + Integer.toString(Cuadricula.countermin) + ":" + Integer.toString(Cuadricula.countersec))); // se actualiza el tiempo en el label de tiempo 
                            Cuadricula.countersec++; // se añade un segundo al contador
                        }
                    }
                    else { // si han pasado mas de 9 minutos
                        if (Cuadricula.countersec < 9) { // si han pasado menos de 9 segundos 
                            Platform.runLater(() -> labelcanttiempo.setText(Integer.toString(Cuadricula.countermin) + ":0" + Integer.toString(Cuadricula.countersec))); // se actualiza el tiempo en el label de tiempo 
                            Cuadricula.countersec++; // se añade un segundo al contador
                        }
                        else { // si han pasado mas de 9 segundos 
                            Platform.runLater(() -> labelcanttiempo.setText(Integer.toString(Cuadricula.countermin) + ":" + Integer.toString(Cuadricula.countersec))); // se actualiza el tiempo en el label de tiempo 
                            Cuadricula.countersec++; // se añade un segundo al contador
                        }
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000l); //hace que se corra el metodo run cada segundo sin delay
        
        // Boton para iniciar control arduino
        Button botoniniciarcontroller = new Button();
        botoniniciarcontroller.setText("Iniciar control");
        botoniniciarcontroller.setTranslateX(-150);
        botoniniciarcontroller.setTranslateY(-275);
        botoniniciarcontroller.setOnAction(e -> {
            
            new Thread(() -> {
                try {
                    Controller.startController();
                }
                catch (IOException | InterruptedException ioexception) {
                    System.out.println("Error conectando controller");
                }
            }).start();
            
        });
        
        // Boton para hacer restart
        /**
         * El boton reset tiene una doble funcionalidad
         * al presionarlo se reinicia el juego, y tambien
         * representa si se ha ganado o perdido el juego, 
         * y si el juego sigue en curso. 
         */
        Cuadricula.botonreset = new Button();
        Cuadricula.botonreset.setText(":|");
        Cuadricula.botonreset.setStyle("-fx-font-size: 20;");
        Cuadricula.botonreset.setTranslateX(0);
        Cuadricula.botonreset.setTranslateY(-215);
        Cuadricula.botonreset.setMaxSize(50, 50);
        
        Cuadricula.botonreset.setOnAction(e -> {
            
            /**
             * Cuando el boton se presiona, se corre 
             * el metodo restart
             */
            restart();
        
        });
        
        // Boton para cerrar la ventana
        /**
         * Boton que cierra la ventana. Al cerrar la ventana
         * de forma normal el timer sigue corriendo, lo cual
         * hace que el programa siga corriendo aun cuando se cierra.
         */
        Button botonexit = new Button();
        botonexit.setText("X");
        botonexit.setTranslateX(185);
        botonexit.setTranslateY(-275);
        botonexit.setMaxSize(30, 30);
        botonexit.setStyle("-fx-background-color: #F00000;");
        botonexit.setOnAction(e -> {
            /**
             * Al presionar el boton se cancela
             * el timer y se cierra la ventana
             */
            Controller.controllerFuncionando = false;
            timer.cancel();
            primaryStage.close();
            
        });
        
        // Boton para Dummy
        /**
         * Boton para iniciar el juego 
         * contra la computadora en la 
         * modalidad dummy
         */
        Button botondummy = new Button();
        botondummy.setText("DUMMY");
        botondummy.setStyle("-fx-border-color: #0027FF;-fx-border-width: 3");
        botondummy.setTranslateX(-125);
        botondummy.setTranslateY(268);
        botondummy.setMaxSize(125, 10);
        botondummy.setOnAction(e -> {
            
            /**
             * Al precionar el boton se llama
             * al metodo restart para reiniciar el
             * juego, y se llama al metodo dummy 
             * para iniciar la modalidad. 
             */
            restart();
            Computadora.dummyfuncionando = true;
            Computadora.dummy();
        
        });
        
        
        // Boton para Avanzado
        /**
         * Boton para iniciar el juego 
         * contra la computadora en la 
         * modalidad avanzada
         */
        Button botonavanzado = new Button();
        botonavanzado.setText("AVANZADO");
        botonavanzado.setStyle("-fx-border-color: #DA0000;-fx-border-width: 3");
        botonavanzado.setTranslateX(-125);
        botonavanzado.setTranslateY(295);
        botonavanzado.setMaxSize(125, 10);
        botonavanzado.setOnAction(e -> {
            
            /**
             * Al precionar el boton se llama
             * al metodo restart para reiniciar el
             * juego, y se llama al metodo avanzado 
             * para iniciar la modalidad. 
             */
            restart();
            Computadora.avanzadofuncionando = true;
            Computadora.avanzado();
        
        });
        
        // Boton para sugerencia
        /**
         * Boton para recivir una sugerencia
         * de la pila de sugerencias. Este se
         * puede utilizar una vez cada cinco
         * turnos. 
         */
        Button botonsugerencia = new Button();
        botonsugerencia.setText("  Solicitar\nSugerencia");
        botonsugerencia.setStyle("-fx-border-color: #12CA00;-fx-border-width: 3");
        botonsugerencia.setTranslateX(125);
        botonsugerencia.setTranslateY(275);
        botonsugerencia.setMaxSize(125, 50);
        botonsugerencia.setAlignment(Pos.CENTER);
        botonsugerencia.setOnAction(e -> {
            
            /**
             * Cuando se presiona el boton se verifica que el
             * juego no se haya acabado, luego se verifica que 
             * el jugador tenga sugerencias acumuladas y crea
             * la pila de sugerencias. Si la pila no esta vacia,
             * se hace pop a las sugerencias de espacios ya revelados. 
             * Si se encuentra un espacio habilitado, se vuelve 
             * de color verde para que el usuario pueda ver el espacio 
             * sugeridoy se consume la sugerencia. Si la pila esta
             * vacia, se notifica al usuario que no hay sugerencias
             * y no se consume la sugerencia.
             */
            if (Cuadricula.gameover == false) { // si el juego no se ha acabado
                if (Cuadricula.cantsugerencias > 0) { // si el jugador tiene sugerencias habilitadas
                    Cuadricula.generarStackSugerencias(); // se crea la pila de sugerencias
                    if (Cuadricula.stacksugerencia.size() > 0) { // si la pila no esta vacia
                        try { // se necesita un try catch, porque al hacer peek da la pila vacia da error
                            while(Cuadricula.matrizvalores[Cuadricula.stacksugerencia.peek()[0]][Cuadricula.stacksugerencia.peek()[1]].revelado == true) {
                                Cuadricula.stacksugerencia.pop(); // se le hace pop a los espacios que ya estan revelados
                            }
                            if (Cuadricula.stacksugerencia.size() == 0) { // si la pila queda vacia despues de los pops
                                labelerror.setText("No hay sugerencias posibles"); // se notifica al usuario 
                            }
                            else { // si no queda vacia despues de los pops 
                                Cuadricula.matrizboton[Cuadricula.stacksugerencia.peek()[0]][Cuadricula.stacksugerencia.peek()[1]].setStyle("-fx-background-color: #12CA00;"); // el primer espacio de la pila de sugerencias se vuelve verde
                                Cuadricula.stacksugerencia.pop(); // se quita el espacio de la pila porque ya fue usado
                                Cuadricula.cantsugerencias--; // se consume la sugerencia 
                                Cuadricula.labelcantsugerencias.setText(Integer.toString(Cuadricula.cantsugerencias)); // se actualiza el label para notificar al usuario las sugerencias que le quedan
                            }
                        }
                        catch (Exception except) { // si da un error es porque la pila esta vacia
                            labelerror.setText("No hay sugerencias posibles"); // se notifica al usuario que no hay espacios posibles
                        }
                    }
                    else { // si la pila esta vacia
                        labelerror.setText("No hay sugerencias posibles"); // se notifica al usuario que no hay espacios posibles
                    }
                }
                else { // si el jugador no tiene sugerencias habilitadas
                    labelerror.setText("Solo se puede obtener una sugerencia cada 5 turnos"); // se le notifica al usuario que no tiene sugerencias habilitadas
                }
            }
        
        });
            
        // Matriz de botones
        /**
         * Se crea la matriz de botones utilizando arrays
         * y un ciclo for. Igualmente se crea la matriz 
         * de valores utilizada para representar cada boton 
         * y espacio en la cuadricula del juego. Se utiliza
         * el metodo colorAleatorio para darle un color 
         * aleatorio a la cuadricula cada vez que se crea
         * la matriz. Se le asignan los valores necesarios
         * a los botones como tamaño, color y posicion. 
         */
        
        Random r = new Random(); // se crea un objeto de la clase random 
        int numrandom = r.nextInt(8); // numero random que se usa para el color de la cuadricula
        int posx = -175; // posicion x inicial del primer boton 
        int posy = -150; // posicion y inicial del primer boton 
        
        for (int i=0; i<=7; i++) {
            for (int j=0; j<=7; j++) {
                int col = j; // no se puede usar j porque no es final, entonces se crea una variable diferente para usar como indice
                int fila = i; // no se puede usar i porque no es final, entonces se crea una variable diferente para usar como indice
                if (j == 7){ // si el j es igual a 7, significa que se esta poniendo la ultima columna en la fila, por lo tanto sus valores de x y y deben ser diferentes
                    Cuadricula.matrizboton[fila][col] = new Button(); // se crea un nuevo objeto boton y se agrega a la matriz
                    Cuadricula.matrizvalores[fila][col] = new Cuadricula(0, 0, 0, false); // se crea un nuevo objeto cuadricula y se le asignan valores y se agrega a la matriz
                    Cuadricula.matrizboton[fila][col].setTranslateX(posx); // se asigna la posicion del boton en x 
                    Cuadricula.matrizboton[fila][col].setTranslateY(posy); // se asigna la posicion del boton en y 
                    posx = -175; // se retorna la posicion en x a su valor original
                    posy = posy+50; // se baja la posicion del boton para poner la siguiente fila
                    Cuadricula.matrizboton[fila][col].setMaxSize(50,50); // se asigna el tamaño del boton
                    Cuadricula.colorAleatorio(fila, col, numrandom); // se le asigna un coloa aleatorio al boton
                    layout.getChildren().add(Cuadricula.matrizboton[fila][col]); // se añade el boton a la ventana 
                    Cuadricula.matrizboton[fila][col].setOnMouseClicked(e -> { // funcionalidad de cada boton, todos tienen la misma funcionalidad
                        
                        /**
                         * Al presionar el boton el label de error se borra. Se
                         * verifica que el juego no se haya terminado. Si ya se ha
                         * terminado el juego no se puede presionar ningun boton. 
                         * 
                         * Si el boton se presiona con el click izquierdo se verifica
                         * que el espacio no se haya revelado ya ni tenga una bandera. 
                         * Se notifica al usuario si tiene una nueva sugerencia habilitada. 
                         * Si el espacio presionado es una mina se revelan las minas y se 
                         * acaba el juego. Si el espacio seleccionado es un cero, se revelan
                         * todos los ceros adyacentes. Si el espacio presionado es un numero
                         * diferente a cero, se revela solo ese espacio. Si alguno de los 
                         * contrincantes esta activado, se habilita el turno del contrincante. 
                         * Por ultimo se verifica si se ha ganado. Al ganar se revelan las
                         * minas de color verde.
                         * 
                         * Si el boton se presiona con el click derecho, se pone una bandera
                         * y el boton no se puede presionar hata que se quite la bandera. La 
                         * bandera se quita presionando el boton con el click derecho en el 
                         * espacio que haya una bandera.
                         */
                        labelerror.setText(""); // se borra el label de error 
                        if (Cuadricula.gameover == false) { // se verifica que el juego no se haya acabado 
                            if (e.getButton() == MouseButton.PRIMARY) { // si el boton se presiona con el click izquierdo
                                if (Cuadricula.matrizvalores[fila][col].revelado == false) { // se verifica que el espacio no este ya revelado
                                    if (Cuadricula.matrizvalores[fila][col].bandera == 0) { // se verifica que no haya una bandera 
                                        Cuadricula.cantturnos++; // se añade un turno a la cantidad de turnos 
                                        if (Cuadricula.cantturnos % 5 == 0) { // cada cinco turnos
                                            Cuadricula.cantsugerencias++; // se habilita una nueva sugerencia 
                                            Cuadricula.labelcantsugerencias.setText(Integer.toString(Cuadricula.cantsugerencias)); // se notifica al usuario la cantidad de sugerencias que le quedan
                                        }
                                        if (Cuadricula.esMina(Cuadricula.matrizvalores[fila][col].mina)) { // si el espacio es una mina 
                                            Cuadricula.revelarMinas(); // se llama al metodo revelarMinas para revelar las minas 
                                            Cuadricula.gameover = true; // se acaba el juego 
                                            Cuadricula.botonreset.setText(":("); // el boton reset se actualiza para representar que se ha acabado el juego 
                                        }
                                        else if (Cuadricula.matrizvalores[fila][col].numrev == 0){ // si el numero es un cero 
                                            Cuadricula.revelarCeros(fila, col); // se revelan los ceros adyacentes 
                                            ajustarMinasEncontradas(); // se ajustan la cantidad de minas encontradas en caso de que hubieran banderas en los espacios que se revelan
                                            Cuadricula.labelcantminasencontradas.setText(Integer.toString(Cuadricula.cantbanderas)); // se le notifica al usuario la cantidad de minas encontradas 
                                            if (Computadora.dummyfuncionando == true){ // se fija si se esta jugando contra el dummy
                                                Computadora.turno = true; // se le da el turno a la computadora
                                                Computadora.dummy(); // se llama al metodo dummy para que elija un espacio
                                            }
                                            else if (Computadora.avanzadofuncionando == true) { // se fija si se esta jugando contra el avanzado
                                                Computadora.turno = true; // se le da el turno a la computadora
                                                Computadora.avanzado(); // se llama al metodo avanzado para que elija un espacio
                                            }
                                        }
                                        else { // si el espacio es diferente a cero y no es una mina 
                                            Cuadricula.matrizvalores[fila][col].revelado = true; // se revela el espacio
                                            Cuadricula.matrizboton[fila][col].setText(Integer.toString(Cuadricula.matrizvalores[fila][col].numrev)); // se le pone el numero al espacio
                                            Cuadricula.matrizboton[fila][col].setStyle("-fx-background-color: #DADAD7;-fx-border-color: #C2C2C2;"); // se cambia el color para hacer mas claro que los espacios fueron revelados 
                                            if (Computadora.dummyfuncionando == true){ // se fija si se esta jugando contra el dummy
                                                Computadora.turno = true; // se habilita el turno a la computadora
                                                Computadora.dummy(); // se llama al metodo dummy para que elija un espacio
                                            }
                                            else if (Computadora.avanzadofuncionando == true) {
                                                Computadora.turno = true; // se habilita el turno a la computadora
                                                Computadora.avanzado(); // se llama al metodo avanzado para que elija un espacio
                                            }
                                        }
                                    Cuadricula.checkVictoria(); // se checkea si se ha ganado
                                    if (Cuadricula.victoria == true) { // si se ha ganado
                                        Cuadricula.botonreset.setText(":)"); // se cambia el boton a una cara feliz para sombolizar victoria
                                    }
                                    }
                                }
                            }
                            else if (e.getButton() == MouseButton.SECONDARY) { // si el boton se presiona con el click derecho 
                                if (Cuadricula.matrizvalores[fila][col].revelado == false) { // se verifica que el espacio no este revelado
                                    if (Cuadricula.matrizvalores[fila][col].bandera == 0) { // si no hay una bandera
                                        Cuadricula.matrizboton[fila][col].setText("|>"); // se cambia el texto para simbolizar que hay una bandera
                                        Cuadricula.matrizvalores[fila][col].bandera = 1; // se cambia el valor en la matriz para saber que hay una bandera
                                        Cuadricula.cantbanderas++; // aumenta la cantidad de banderas 
                                        Cuadricula.labelcantminasencontradas.setText(Integer.toString(Cuadricula.cantbanderas)); // se notifica al usuario la cantidad de banderas que hay
                                        if (Controller.controllerFuncionando == true) {
                                            try {
                                                Controller.RedLED.setValue(1);
                                                Thread.sleep(100);
                                                Controller.RedLED.setValue(0);
                                            }
                                            catch (IOException | InterruptedException ioexception) {
                                                System.out.println("Error conectando a controller");
                                            }
                                        }
                                    }
                                    else { // si el espacio ya tiene una bandera
                                        Cuadricula.matrizboton[fila][col].setText(""); // se cambia el texto para simbolizar que ya no hay bandera 
                                        Cuadricula.matrizvalores[fila][col].bandera = 0; // se cambia el valor en la matriz para saber que ya no hay bandera
                                        Cuadricula.cantbanderas--; // se disminuye la cantidad de banderas 
                                        Cuadricula.labelcantminasencontradas.setText(Integer.toString(Cuadricula.cantbanderas)); // se notifica al usuario la cantidad de banderas que hay
                                    }
                                }    
                            }
                        }
                    });
                }
                else { // los botones en las columnas antes de la ultima tienen valores x diferentes, pero tienen la misma funcionalidad
                    Cuadricula.matrizboton[fila][col] = new Button(); // se crea el objeto boton y se agrega a la matriz
                    Cuadricula.matrizvalores[fila][col] = new Cuadricula(0, 0, 0, false); // se crea el objeto cuadricula y se agrega a la matriz
                    Cuadricula.matrizboton[fila][col].setTranslateX(posx);
                    Cuadricula.matrizboton[fila][col].setTranslateY(posy);
                    posx = posx+50;
                    Cuadricula.matrizboton[fila][col].setMaxSize(50,50);
                    Cuadricula.colorAleatorio(fila, col, numrandom);
                    layout.getChildren().add(Cuadricula.matrizboton[fila][col]);
                    Cuadricula.matrizboton[fila][col].setOnMouseClicked(e -> {
                        
                        /**
                         * Al presionar el boton el label de error se borra. Se
                         * verifica que el juego no se haya terminado. Si ya se ha
                         * terminado el juego no se puede presionar ningun boton. 
                         * 
                         * Si el boton se presiona con el click izquierdo se verifica
                         * que el espacio no se haya revelado ya ni tenga una bandera. 
                         * Se notifica al usuario si tiene una nueva sugerencia habilitada. 
                         * Si el espacio presionado es una mina se revelan las minas y se 
                         * acaba el juego. Si el espacio seleccionado es un cero, se revelan
                         * todos los ceros adyacentes. Si el espacio presionado es un numero
                         * diferente a cero, se revela solo ese espacio. Si alguno de los 
                         * contrincantes esta activado, se habilita el turno del contrincante. 
                         * Por ultimo se verifica si se ha ganado. Al ganar se revelan las
                         * minas de color verde.
                         * 
                         * Si el boton se presiona con el click derecho, se pone una bandera
                         * y el boton no se puede presionar hata que se quite la bandera. La 
                         * bandera se quita presionando el boton con el click derecho en el 
                         * espacio que haya una bandera.
                         */
                        labelerror.setText("");
                        if (Cuadricula.gameover == false) { // se verifica que el juego no se haya acabado 
                            if (e.getButton() == MouseButton.PRIMARY) { // si el boton se presiona con el click izquierdo
                                if (Cuadricula.matrizvalores[fila][col].revelado == false) { // se verifica que el espacio no este ya revelado
                                    if (Cuadricula.matrizvalores[fila][col].bandera == 0) { // se verifica que no haya una bandera 
                                        Cuadricula.cantturnos++; // se añade un turno a la cantidad de turnos 
                                        if (Cuadricula.cantturnos % 5 == 0) { // cada cinco turnos
                                            Cuadricula.cantsugerencias++; // se habilita una nueva sugerencia 
                                            Cuadricula.labelcantsugerencias.setText(Integer.toString(Cuadricula.cantsugerencias)); // se notifica al usuario la cantidad de sugerencias que le quedan
                                        }
                                        if (Cuadricula.esMina(Cuadricula.matrizvalores[fila][col].mina)) { // si el espacio es una mina 
                                            Cuadricula.revelarMinas(); // se llama al metodo revelarMinas para revelar las minas 
                                            Cuadricula.gameover = true; // se acaba el juego 
                                            Cuadricula.botonreset.setText(":("); // el boton reset se actualiza para representar que se ha acabado el juego 
                                        }
                                        else if (Cuadricula.matrizvalores[fila][col].numrev == 0){ // si el numero es un cero 
                                            Cuadricula.revelarCeros(fila, col); // se revelan los ceros adyacentes 
                                            ajustarMinasEncontradas(); // se ajustan la cantidad de minas encontradas en caso de que hubieran banderas en los espacios que se revelan
                                            Cuadricula.labelcantminasencontradas.setText(Integer.toString(Cuadricula.cantbanderas)); // se le notifica al usuario la cantidad de minas encontradas 
                                            if (Computadora.dummyfuncionando == true){ // se fija si se esta jugando contra el dummy
                                                Computadora.turno = true; // se le da el turno a la computadora
                                                Computadora.dummy(); // se llama al metodo dummy para que elija un espacio
                                            }
                                            else if (Computadora.avanzadofuncionando == true) { // se fija si se esta jugando contra el avanzado
                                                Computadora.turno = true; // se le da el turno a la computadora
                                                Computadora.avanzado(); // se llama al metodo avanzado para que elija un espacio
                                            }
                                        }
                                        else { // si el espacio es diferente a cero y no es una mina 
                                            Cuadricula.matrizvalores[fila][col].revelado = true; // se revela el espacio
                                            Cuadricula.matrizboton[fila][col].setText(Integer.toString(Cuadricula.matrizvalores[fila][col].numrev)); // se le pone el numero al espacio
                                            Cuadricula.matrizboton[fila][col].setStyle("-fx-background-color: #DADAD7;-fx-border-color: #C2C2C2;"); // se cambia el color para hacer mas claro que los espacios fueron revelados 
                                            if (Computadora.dummyfuncionando == true){ // se fija si se esta jugando contra el dummy
                                                Computadora.turno = true; // se habilita el turno a la computadora
                                                Computadora.dummy(); // se llama al metodo dummy para que elija un espacio
                                            }
                                            else if (Computadora.avanzadofuncionando == true) {
                                                Computadora.turno = true; // se habilita el turno a la computadora
                                                Computadora.avanzado(); // se llama al metodo avanzado para que elija un espacio
                                            }
                                        }
                                    Cuadricula.checkVictoria(); // se checkea si se ha ganado
                                    if (Cuadricula.victoria == true) { // si se ha ganado
                                        Cuadricula.botonreset.setText(":)"); // se cambia el boton a una cara feliz para sombolizar victoria
                                    }
                                    }
                                }
                            }
                            else if (e.getButton() == MouseButton.SECONDARY) { // si el boton se presiona con el click derecho 
                                if (Cuadricula.matrizvalores[fila][col].revelado == false) { // se verifica que el espacio no este revelado
                                    if (Cuadricula.matrizvalores[fila][col].bandera == 0) { // si no hay una bandera
                                        Cuadricula.matrizboton[fila][col].setText("|>"); // se cambia el texto para simbolizar que hay una bandera
                                        Cuadricula.matrizvalores[fila][col].bandera = 1; // se cambia el valor en la matriz para saber que hay una bandera
                                        Cuadricula.cantbanderas++; // aumenta la cantidad de banderas 
                                        Cuadricula.labelcantminasencontradas.setText(Integer.toString(Cuadricula.cantbanderas)); // se notifica al usuario la cantidad de banderas que hay
                                        if (Controller.controllerFuncionando == true) {
                                            try {
                                                Controller.RedLED.setValue(1);
                                                Thread.sleep(100);
                                                Controller.RedLED.setValue(0);
                                            }
                                            catch (IOException | InterruptedException ioexception) {
                                                System.out.println("Error conectando a controller");
                                            }
                                        }
                                    }
                                    else { // si el espacio ya tiene una bandera
                                        Cuadricula.matrizboton[fila][col].setText(""); // se cambia el texto para simbolizar que ya no hay bandera 
                                        Cuadricula.matrizvalores[fila][col].bandera = 0; // se cambia el valor en la matriz para saber que ya no hay bandera
                                        Cuadricula.cantbanderas--; // se disminuye la cantidad de banderas 
                                        Cuadricula.labelcantminasencontradas.setText(Integer.toString(Cuadricula.cantbanderas)); // se notifica al usuario la cantidad de banderas que hay
                                    }
                                }    
                            }
                        }
                    });
                }
            }
        }
        
        
        
        // Metodos necesarios para el funcionamiento de la aplicacion 
        /**
         * Se llama a los metodos generarMinas y generarNumAdy
         * para poner minas aleatoriamente en la matriz de valores
         * y generar los numeros de minas adyacentes en la matriz 
         * de valores. 
         */
        Cuadricula.generarMinas(); // se genera la matriz de minas aleatoria
        Cuadricula.generarNumAdy(); // se genera la matriz de numeros de minas adyacentes
        
        //Se añaden los objetos al layout
        /**
         * Se añaden los labels y botones al layout.
         */
        layout.getChildren().addAll(labelminasencontradas, Cuadricula.labelcantminasencontradas, 
        labeltiempo, labelcanttiempo, Cuadricula.botonreset, botonexit, labelelegirmodo,
        botondummy, botonavanzado, botonsugerencia, labelerror, Cuadricula.labelcantsugerencias
        , botoniniciarcontroller);
        
    }
    
    /**
     * Se asignan todos los valores a sus 
     * valores iniciales para reiniciar
     * el juego. 
     */
    public static void restart() {
        Cuadricula.cantbanderas = 0; // se reinicia la cantidad de banderas 
        Cuadricula.labelcantminasencontradas.setText(Integer.toString(Cuadricula.cantbanderas)); // se reinicia el label de cantidad de minas encontradas 
        Cuadricula.countersec = -1; // se reinicia el contador de segundos 
        Cuadricula.countermin = 0; // se reinicia el contador de minutos 
        Cuadricula.gameover = false; // empieza el juego 
        Cuadricula.victoria = false; // se quita la victoria 
        Cuadricula.cantturnos = 0; // se reinicia la cantidad de turnos
        Cuadricula.cantsugerencias = 0;  // se reinicia la cantidad de sugerencias habilitadas 
        Cuadricula.botonreset.setText(":|");  // se reinicia la cara en el boton de reset
        Computadora.dummyfuncionando = false; // el dummy para de funcionar 
        Computadora.avanzadofuncionando = false; // el avanzado para de funcionar 
        Computadora.turno = false; // se le quita el turno a la computadora
        Computadora.listaminas.empty(); // se vacia la lista de minas 
        Computadora.listasegura.empty(); // se vacia la lista segura 
        Computadora.listageneral.empty(); // se vacia la lista general 
        Computadora.listaincertidumbre.empty(); // se vacia la lista incertidumbre  
        Computadora.listaminas.resetSize(); // el tamaño de la lista vuelve a 0
        Computadora.listasegura.resetSize(); // el tamaño de la lista vuelve a 0
        Computadora.listageneral.resetSize(); // el tamaño de la lista vuelve a 0
        Computadora.listaincertidumbre.resetSize(); // el tamaño de la lista vuelve a 0
        Cuadricula.emptyStack(Cuadricula.stacksugerencia); // se vacia la pila de sugerencias 
        Cuadricula.stacksugerencia.resetSize(); // el tamaño de la pila vuelve a 0
        Cuadricula.labelcantsugerencias.setText("0"); // se notifica al usuario la cantidad de sugerencias habilitadas
        Controller.resetSizes();
        Random r = new Random(); // se crea un objeto random 
        int numrandom = r.nextInt(8); // se genera un numero random del 0 al 7 para elegir un color aleatorio 
        for (int i=0; i<=7; i++) {
            for (int j=0; j<=7; j++) {
                Cuadricula.matrizvalores[i][j].mina = 0; // se quitan las minas de la matriz de valores
                Cuadricula.matrizvalores[i][j].bandera = 0; // se quitan las banderas de la matriz de valores
                Cuadricula.matrizvalores[i][j].numrev = 0; // se quitan los numeros adyacentes de la matriz de valores
                Cuadricula.matrizvalores[i][j].revelado = false; // se hace que no esten revelados los espacios en la matriz de valores
                Cuadricula.matrizboton[i][j].setText(""); // se quitan los textos de los botones 
                Cuadricula.colorAleatorio(i, j, numrandom); // se asigna un color aleatorio a los botones
            }
        }
        Cuadricula.generarMinas(); // se vuelven a generar las minas en la matriz de valores
        Cuadricula.generarNumAdy(); // se vuelven a generar los numeros adyacentes en la matriz de valores
        
    }
    
    /**
     * Se ajusta la cantidad de minas encontradas utilizando un 
     * ciclo for. Si hay una bandera y el espacio ya se ha revelado, 
     * se quita la bandera. 
     */
    public static void ajustarMinasEncontradas() {
        for (int i=0; i<=7; i++) {
            for (int j=0; j<=7; j++) {
                if (Cuadricula.matrizvalores[i][j].bandera == 1 && Cuadricula.matrizvalores[i][j].revelado && Cuadricula.matrizvalores[i][j].numrev == 0) { // si hay una bandera, el espacio se ha revelado y el numero es un 0
                    Cuadricula.matrizboton[i][j].setText(""); // se quita el texto del boton 
                    Cuadricula.cantbanderas--; // se disminuye la cantidad de banderas 
                    Cuadricula.matrizvalores[i][j].bandera = 0; // se quita la bandera de la matriz de valores
                }
                if (Cuadricula.matrizvalores[i][j].bandera == 1 && Cuadricula.matrizvalores[i][j].revelado && Cuadricula.matrizvalores[i][j].numrev > 0) { // si hay una bandera, el espacio se ha revelado y el numero es diferente de 0
                    Cuadricula.matrizboton[i][j].setText(Integer.toString(Cuadricula.matrizvalores[i][j].numrev)); // se le pone el numero de minas adyacentes al boton
                    Cuadricula.cantbanderas--; // se disminuye la cantidad de banderas 
                    Cuadricula.matrizvalores[i][j].bandera = 0; // se quita la bandera de la matriz de valores
                }
            }
        }
    }
    
    /**
     * Se verifica que el espacio no se haya revelado ya ni tenga una bandera.  
     * Si el espacio presionado es una mina se revelan las minas y se 
     * acaba el juego. Si el espacio seleccionado es un cero, se revelan
     * todos los ceros adyacentes. Si el espacio presionado es un numero
     * diferente a cero, se revela solo ese espacio. Si alguno de los 
     * Por ultimo se verifica si se ha ganado. Al ganar se revelan las
     * minas de color verde.
     * @param i fila del espacio que se va a revelar 
     * @param j columna del espacio que se va a revelar
     */
    public static void elegirEspacioDummy(int i, int j) {
        if (Cuadricula.esMina(Cuadricula.matrizvalores[i][j].mina)) { // si el espacio es una mina 
            Cuadricula.updateCuadricula(); // se actualiza la cuadricula para quitar el marcador de donde eligió el dummy
            Cuadricula.revelarMinasDummy(); // revela las minas en el color que representa al dummt 
            Cuadricula.gameover = true; // se termina el juego 
            Cuadricula.botonreset.setText(":("); // se pone el texto en el boton de reset para visualizar que se ha acabado el juego 

        }
        else if (Cuadricula.matrizvalores[i][j].numrev == 0){ // si el espacio que se elije es 0
            Cuadricula.revelarCeros(i, j); // se revelan los ceros adyacentes
            ajustarMinasEncontradas(); // se ajusta la cantidad de minas encontradas 
            Cuadricula.labelcantminasencontradas.setText(Integer.toString(Cuadricula.cantbanderas)); // se notifica al usuario de la cantidad de minas encontradas 
            Cuadricula.updateCuadricula(); // se actualiza la cuadricula para quitar el marcador de donde eligió el dummy
            Cuadricula.matrizboton[i][j].setStyle("-fx-background-color: #DADAD7;-fx-border-color: #0027FF;-fx-border-width: 3"); // se cambia el color del borde en el color que representa al dummy 
        }
        else { // si el espacio que se elije es diferente de 0
            Cuadricula.matrizvalores[i][j].revelado = true; // se cambia el valor a revelado en la matriz de valores
            Cuadricula.matrizboton[i][j].setText(Integer.toString(Cuadricula.matrizvalores[i][j].numrev)); // se cambia el texto del boton para representar las minas adyacentes 
            Cuadricula.updateCuadricula(); // se actualiza la cuadricula para quitar el marcador de donde eligió el dummy
            Cuadricula.matrizboton[i][j].setStyle("-fx-background-color: #DADAD7;-fx-border-color: #0027FF;-fx-border-width: 3"); // se cambia el color del borde en el color que representa al dummy 
            ajustarMinasEncontradas(); // se ajusta la cantidad de minas encontradas 
            Cuadricula.labelcantminasencontradas.setText(Integer.toString(Cuadricula.cantbanderas)); // se notifica al usuario de la cantidad de minas encontradas 
        }
        Cuadricula.checkVictoria(); // se checkea si se ha ganado
        if (Cuadricula.victoria == true) { // si se ha ganado 
            Cuadricula.botonreset.setText(":)"); // se cambia el boton a una cara feliz para sombolizar victoria
        }
    }
    
    /**
     * Se verifica que el espacio no se haya revelado ya ni tenga una bandera.  
     * Si el espacio presionado es una mina se revelan las minas y se 
     * acaba el juego. Si el espacio seleccionado es un cero, se revelan
     * todos los ceros adyacentes. Si el espacio presionado es un numero
     * diferente a cero, se revela solo ese espacio. Si alguno de los 
     * Por ultimo se verifica si se ha ganado. Al ganar se revelan las
     * minas de color verde.
     * @param i fila del espacio que se va a revelar 
     * @param j columna del espacio que se va a revelar
     */
    public static void elegirEspacioAvanzado(int i, int j) {
        Cuadricula.checkVictoria(); // se llama al metodo para verificar si se ha ganado 
        if (Cuadricula.victoria == false) { // se verifica si se ha ganado 
            if (Cuadricula.esMina(Cuadricula.matrizvalores[i][j].mina)) { // si el espacio elegido es una mina 
                Cuadricula.updateCuadricula(); // se actualiza la cuadricula para quitar el marcador de donde eligió el avanzado 
                Cuadricula.revelarMinasAvanzado(); // se revelan las minas del color que representa el avanzado 
                Cuadricula.gameover = true; // se acaba el juego 
                Cuadricula.botonreset.setText(":("); // se cambia el texto del boton reset para representar la perdida 

            }
            else if (Cuadricula.matrizvalores[i][j].numrev == 0){ // si el espacio elegido es 0 
                Cuadricula.revelarCeros(i, j); // se revelan los ceros adyacentes 
                ajustarMinasEncontradas(); // se ajusta la cantidad de minas encontradas 
                Cuadricula.labelcantminasencontradas.setText(Integer.toString(Cuadricula.cantbanderas)); // se notifica al usuario de la cantidad de minas encontradas
                Cuadricula.updateCuadricula(); // se actualiza la cuadricula para quitar el marcador de donde eligió el dummy
                Cuadricula.matrizboton[i][j].setStyle("-fx-background-color: #DADAD7;-fx-border-color: #DA0000;-fx-border-width: 3"); // se cambia el color del borde en el color que representa al avanzado 
            }
            else {
                Cuadricula.matrizvalores[i][j].revelado = true; // se cambia el valor en la matriz de valores a revelado 
                Cuadricula.matrizboton[i][j].setText(Integer.toString(Cuadricula.matrizvalores[i][j].numrev)); // se le pone al boton la cantidad de minas adyacentes
                Cuadricula.updateCuadricula(); // se actualiza la cuadricula para quitar el marcador de donde eligió el dummy
                Cuadricula.matrizboton[i][j].setStyle("-fx-background-color: #DADAD7;-fx-border-color: #DA0000;-fx-border-width: 3"); // se cambia el color del borde en el color que representa al avanzado 
                ajustarMinasEncontradas(); // se ajustan la cantidad de minas encontradas 
                Cuadricula.labelcantminasencontradas.setText(Integer.toString(Cuadricula.cantbanderas)); // se notidica al usuario de la cantidad de minas encontradas 
            }
        }
        else {
            Cuadricula.botonreset.setText(":)"); // se cambia el boton a una cara feliz para sombolizar victoria
        }
    }
    
    public static void elegirEspacioController(int i, int j) {
        Cuadricula.checkVictoria(); // se llama al metodo para verificar si se ha ganado 
        if (Cuadricula.victoria == false) { // se verifica si se ha ganado 
            if (Cuadricula.esMina(Cuadricula.matrizvalores[i][j].mina)) { // si el espacio elegido es una mina 
                Cuadricula.updateCuadricula(); // se actualiza la cuadricula para quitar el marcador de donde eligió el avanzado 
                Cuadricula.revelarMinas(); // se revelan las minas del color que representa el avanzado 
                Cuadricula.gameover = true; // se acaba el juego 
                Cuadricula.botonreset.setText(":("); // se cambia el texto del boton reset para representar la perdida 
                Controller.resetSizes();
            }
            else if (Cuadricula.matrizvalores[i][j].numrev == 0){ // si el espacio elegido es 0 
                Cuadricula.revelarCeros(i, j); // se revelan los ceros adyacentes 
                ajustarMinasEncontradas(); // se ajusta la cantidad de minas encontradas 
                Cuadricula.labelcantminasencontradas.setText(Integer.toString(Cuadricula.cantbanderas)); // se notifica al usuario de la cantidad de minas encontradas
                Cuadricula.updateCuadricula(); // se actualiza la cuadricula para quitar el marcador de donde eligió el control
                if (Computadora.dummyfuncionando == true){ // se fija si se esta jugando contra el dummy
                    Computadora.turno = true; // se le da el turno a la computadora
                    Computadora.dummy(); // se llama al metodo dummy para que elija un espacio
                }
                else if (Computadora.avanzadofuncionando == true) { // se fija si se esta jugando contra el avanzado
                    Computadora.turno = true; // se le da el turno a la computadora
                    Computadora.avanzado(); // se llama al metodo avanzado para que elija un espacio
                }
            }
            else {
                Cuadricula.matrizvalores[i][j].revelado = true; // se cambia el valor en la matriz de valores a revelado 
                Cuadricula.matrizboton[i][j].setText(Integer.toString(Cuadricula.matrizvalores[i][j].numrev)); // se le pone al boton la cantidad de minas adyacentes
                Cuadricula.updateCuadricula(); // se actualiza la cuadricula para quitar el marcador de donde eligió el control
                ajustarMinasEncontradas(); // se ajustan la cantidad de minas encontradas 
                Cuadricula.labelcantminasencontradas.setText(Integer.toString(Cuadricula.cantbanderas)); // se notidica al usuario de la cantidad de minas encontradas 
                if (Computadora.dummyfuncionando == true){ // se fija si se esta jugando contra el dummy
                    Computadora.turno = true; // se le da el turno a la computadora
                    Computadora.dummy(); // se llama al metodo dummy para que elija un espacio
                }
                else if (Computadora.avanzadofuncionando == true) { // se fija si se esta jugando contra el avanzado
                    Computadora.turno = true; // se le da el turno a la computadora
                    Computadora.avanzado(); // se llama al metodo avanzado para que elija un espacio
                }
            }
            Cuadricula.checkVictoria(); // se checkea si se ha ganado
            if (Cuadricula.victoria == true) { // si se ha ganado
                Cuadricula.botonreset.setText(":)"); // se cambia el boton a una cara feliz para sombolizar victoria
            }
        }
        else {
            Cuadricula.botonreset.setText(":)"); // se cambia el boton a una cara feliz para sombolizar victoria
        }
    }
}
