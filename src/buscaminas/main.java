package buscaminas;

import java.util.Random;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.input.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Background;
import javafx.geometry.Pos;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;

public class main extends Application{

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Buscaminas");
        
        // Layout
        StackPane layout = new StackPane();
        
        // Escenario
        Scene scene = new Scene(layout, 500, 650);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Creacion de labels
        Label labelminasencontradas = new Label();
        labelminasencontradas.setText("Minas encontradas");
        labelminasencontradas.setTranslateX(125);
        labelminasencontradas.setTranslateY(-230);
        
        Cuadricula.labelcantminasencontradas = new Label();
        Cuadricula.labelcantminasencontradas.setText("0");
        Cuadricula.labelcantminasencontradas.setAlignment(Pos.CENTER);
        Cuadricula.labelcantminasencontradas.setStyle("-fx-background-color: #DADAD7;");
        Cuadricula.labelcantminasencontradas.setMaxSize(50, 30);
        Cuadricula.labelcantminasencontradas.setTranslateX(125);
        Cuadricula.labelcantminasencontradas.setTranslateY(-200);
        
        Label labeltiempo = new Label();
        labeltiempo.setText("Tiempo");
        labeltiempo.setTranslateX(-125);
        labeltiempo.setTranslateY(-230);
        
        Label labelcanttiempo = new Label();
        labelcanttiempo.setText("");
        labelcanttiempo.setTranslateX(-125);
        labelcanttiempo.setTranslateY(-200);
        labelcanttiempo.setAlignment(Pos.CENTER);
        labelcanttiempo.setStyle("-fx-background-color: #DADAD7;");
        labelcanttiempo.setMaxSize(50, 30);
        
        Label labelelegirmodo = new Label();
        labelelegirmodo.setText("Elegir contrincante");
        labelelegirmodo.setAlignment(Pos.TOP_CENTER);
        labelelegirmodo.setStyle("-fx-background-color: #DADAD7;");
        labelelegirmodo.setMaxSize(150,75);
        labelelegirmodo.setTranslateX(-125);
        labelelegirmodo.setTranslateY(275);
        
        // Implementacion de Timer 
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (Cuadricula.countersec == 59) {
                    Cuadricula.countermin++;
                    Cuadricula.countersec = -1;
                }
                if (Cuadricula.gameover == false) {
                    if (Cuadricula.countermin==0) {
                        if (Cuadricula.countersec < 9) {
                            Platform.runLater(() -> labelcanttiempo.setText("00:0" + Integer.toString(Cuadricula.countersec)));
                            Cuadricula.countersec++;
                        }
                        else {
                            Platform.runLater(() -> labelcanttiempo.setText("00:" + Integer.toString(Cuadricula.countersec)));
                            Cuadricula.countersec++;
                        }
                    }
                    else if (Cuadricula.countermin < 9) {
                        if (Cuadricula.countersec < 9) {
                            Platform.runLater(() -> labelcanttiempo.setText("0" + Integer.toString(Cuadricula.countermin) + ":0" + Integer.toString(Cuadricula.countersec)));
                            Cuadricula.countersec++;
                        }
                        else {
                            Platform.runLater(() -> labelcanttiempo.setText("0" + Integer.toString(Cuadricula.countermin) + ":" + Integer.toString(Cuadricula.countersec)));
                            Cuadricula.countersec++;
                        }
                    }
                    else {
                        if (Cuadricula.countersec < 9) {
                            Platform.runLater(() -> labelcanttiempo.setText(Integer.toString(Cuadricula.countermin) + ":0" + Integer.toString(Cuadricula.countersec)));
                            Cuadricula.countersec++;
                        }
                        else {
                            Platform.runLater(() -> labelcanttiempo.setText(Integer.toString(Cuadricula.countermin) + ":" + Integer.toString(Cuadricula.countersec)));
                            Cuadricula.countersec++;
                        }
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000l); 
        
        // Boton para hacer restart
        Cuadricula.botonreset = new Button();
        Cuadricula.botonreset.setText(":|");
        Cuadricula.botonreset.setStyle("-fx-font-size: 20;");
        Cuadricula.botonreset.setTranslateX(0);
        Cuadricula.botonreset.setTranslateY(-215);
        Cuadricula.botonreset.setMaxSize(50, 50);
        
        Cuadricula.botonreset.setOnAction(e -> {
            
            restart();
            Cuadricula.botonreset.setText(":|");
            Cuadricula.labelcantminasencontradas.setText("0");
        
        });
        
        // Boton para cerrar la ventana
        Button botonexit = new Button();
        botonexit.setText("X");
        botonexit.setTranslateX(185);
        botonexit.setTranslateY(-275);
        botonexit.setMaxSize(30, 30);
        botonexit.setStyle("-fx-background-color: #F00000;");
        botonexit.setOnAction(e -> {
            
            timer.cancel();
            primaryStage.close();
            
        });
        
        // Boton para Dummy
        Button botondummy = new Button();
        botondummy.setText("DUMMY");
        botondummy.setStyle("-fx-border-color: #0027FF;-fx-border-width: 3");
        botondummy.setTranslateX(-125);
        botondummy.setTranslateY(268);
        botondummy.setMaxSize(125, 10);
        botondummy.setOnAction(e -> {
        
            restart();
            Computadora.dummyfuncionando = true;
            Computadora.dummy();
        
        });
        
        
        // Boton para Avanzado
        Button botonavanzado = new Button();
        botonavanzado.setText("AVANZADO");
        botonavanzado.setStyle("-fx-border-color: #000000;");
        botonavanzado.setTranslateX(-125);
        botonavanzado.setTranslateY(295);
        botonavanzado.setMaxSize(125, 10);
        
        // Boton para sugerencia
        Button botonsugerencia = new Button();
        botonsugerencia.setText("  Solicitar\nSugerencia");
        botonsugerencia.setStyle("-fx-border-color: #000000;");
        botonsugerencia.setTranslateX(125);
        botonsugerencia.setTranslateY(275);
        botonsugerencia.setMaxSize(125, 50);
        botonsugerencia.setAlignment(Pos.CENTER);
        
            
        // Matriz de botones
        
        Random r = new Random();
        int numrandom = r.nextInt(8); // numero random que se usa para el color de la cuadricula
        int posx = -175;
        int posy = -150;
        
        for (int i=0; i<=7; i++) {
            for (int j=0; j<=7; j++) {
                int col = j;
                int fila = i;
                if (j == 7){
                    Cuadricula.matrizboton[fila][col] = new Button();
                    Cuadricula.matrizvalores[fila][col] = new Cuadricula(0, 0, 0, false);
                    Cuadricula.matrizboton[fila][col].setTranslateX(posx);
                    Cuadricula.matrizboton[fila][col].setTranslateY(posy);
                    posx = -175;
                    posy = posy+50;
                    Cuadricula.matrizboton[fila][col].setMaxSize(50,50);
                    Cuadricula.colorAleatorio(fila, col, numrandom);
                    Cuadricula.matrizboton[fila][col].setOnMouseClicked(e -> {
                        
                        Cuadricula.checkVictoria();
                        if (Cuadricula.gameover == false) {
                            if (e.getButton() == MouseButton.PRIMARY) {
                                if (Cuadricula.matrizvalores[fila][col].revelado == false) {
                                    if (Cuadricula.matrizvalores[fila][col].bandera == 0) {
                                        if (Cuadricula.esMina(Cuadricula.matrizvalores[fila][col].mina)) {
                                            Cuadricula.revelarMinas();
                                            Cuadricula.gameover = true;
                                            Cuadricula.botonreset.setText(":(");

                                        }
                                        else if (Cuadricula.matrizvalores[fila][col].numrev == 0){
                                            Cuadricula.revelarCeros(fila, col);
                                            ajustarMinasEncontradas();
                                            Cuadricula.labelcantminasencontradas.setText(Integer.toString(Cuadricula.cantbanderas));
                                            if (Computadora.dummyfuncionando == true){ // se fija si se esta jugando contra el dummy
                                                Computadora.turno = true;
                                                Computadora.dummy();
                                            }
                                        }
                                        else {
                                            Cuadricula.matrizvalores[fila][col].revelado = true;
                                            Cuadricula.matrizboton[fila][col].setText(Integer.toString(Cuadricula.matrizvalores[fila][col].numrev));
                                            Cuadricula.matrizboton[fila][col].setStyle("-fx-background-color: #DADAD7;-fx-border-color: #C2C2C2;");
                                            if (Computadora.dummyfuncionando == true){ // se fija si se esta jugando contra el dummy
                                                Computadora.turno = true;
                                                Computadora.dummy();
                                            }
                                        }
                                    Cuadricula.checkVictoria(); // se checkea si se ha ganado
                                    if (Cuadricula.victoria == true) {
                                        Cuadricula.botonreset.setText(":)"); // se cambia el boton a una cara feliz para sombolizar victoria
                                    }
                                    }
                                }
                            }
                            else if (e.getButton() == MouseButton.SECONDARY) {
                                if (Cuadricula.matrizvalores[fila][col].revelado == false) {
                                    if (Cuadricula.matrizvalores[fila][col].bandera == 0) {
                                        Cuadricula.matrizboton[fila][col].setText("|>");
                                        Cuadricula.matrizvalores[fila][col].bandera = 1;
                                        Cuadricula.cantbanderas++;
                                        Cuadricula.labelcantminasencontradas.setText(Integer.toString(Cuadricula.cantbanderas));
                                    }
                                    else {
                                        Cuadricula.matrizboton[fila][col].setText("");
                                        Cuadricula.matrizvalores[fila][col].bandera = 0;
                                        Cuadricula.cantbanderas--;
                                        Cuadricula.labelcantminasencontradas.setText(Integer.toString(Cuadricula.cantbanderas));
                                    }
                                }    
                            }
                        }
                    
                    });
                    layout.getChildren().add(Cuadricula.matrizboton[fila][col]);
                }
                else {
                    Cuadricula.matrizboton[fila][col] = new Button();
                    Cuadricula.matrizvalores[fila][col] = new Cuadricula(0, 0, 0, false);
                    Cuadricula.matrizboton[fila][col].setTranslateX(posx);
                    Cuadricula.matrizboton[fila][col].setTranslateY(posy);
                    posx = posx+50;
                    Cuadricula.matrizboton[fila][col].setMaxSize(50,50);
                    Cuadricula.colorAleatorio(fila, col, numrandom);
                    Cuadricula.matrizboton[fila][col].setOnMouseClicked(e -> {
                        
                        if (Cuadricula.gameover == false) {
                            if (e.getButton() == MouseButton.PRIMARY) {
                                if (Cuadricula.matrizvalores[fila][col].revelado == false) {
                                    if (Cuadricula.matrizvalores[fila][col].bandera == 0) {
                                        if (Cuadricula.esMina(Cuadricula.matrizvalores[fila][col].mina)) {
                                            Cuadricula.revelarMinas();
                                            Cuadricula.gameover = true;
                                            Cuadricula.botonreset.setText(":(");
                                        }
                                        else if (Cuadricula.matrizvalores[fila][col].numrev == 0){
                                            Cuadricula.revelarCeros(fila, col);
                                            ajustarMinasEncontradas();
                                            Cuadricula.labelcantminasencontradas.setText(Integer.toString(Cuadricula.cantbanderas));
                                            if (Computadora.dummyfuncionando == true){ // se fija si se esta jugando contra el dummy
                                                Computadora.turno = true;
                                                Computadora.dummy();
                                            }
                                        }
                                        else {
                                            Cuadricula.matrizvalores[fila][col].revelado = true;
                                            Cuadricula.matrizboton[fila][col].setText(Integer.toString(Cuadricula.matrizvalores[fila][col].numrev));
                                            Cuadricula.matrizboton[fila][col].setStyle("-fx-background-color: #DADAD7;-fx-border-color: #C2C2C2;");
                                            if (Computadora.dummyfuncionando == true){ // se fija si se esta jugando contra el dummy
                                                Computadora.turno = true;
                                                Computadora.dummy();
                                            }
                                        }
                                    Cuadricula.checkVictoria(); // se checkea si se ha ganado
                                    if (Cuadricula.victoria == true) {
                                        Cuadricula.botonreset.setText(":)"); // se cambia el boton a una cara feliz para sombolizar victoria
                                    }
                                    }
                                }
                            }
                            else if (e.getButton() == MouseButton.SECONDARY) {
                                if (Cuadricula.matrizvalores[fila][col].revelado == false) {
                                    if (Cuadricula.matrizvalores[fila][col].bandera == 0) {
                                        Cuadricula.matrizboton[fila][col].setText("|>");
                                        Cuadricula.matrizvalores[fila][col].bandera = 1;
                                        Cuadricula.cantbanderas++;
                                        Cuadricula.labelcantminasencontradas.setText(Integer.toString(Cuadricula.cantbanderas));
                                    }
                                    else {
                                        Cuadricula.matrizboton[fila][col].setText("");
                                        Cuadricula.matrizvalores[fila][col].bandera = 0;
                                        Cuadricula.cantbanderas--;
                                        Cuadricula.labelcantminasencontradas.setText(Integer.toString(Cuadricula.cantbanderas));
                                    }
                                }
                            }
                        }
                    
                    });
                    layout.getChildren().add(Cuadricula.matrizboton[fila][col]); 
                }
            }
        }
        
        
        
        // Metodos necesarios para el funcionamiento de la aplicacion 
        Cuadricula.generarMinas(); // se genera la matriz de minas aleatoria
        Cuadricula.generarNumAdy(); // se genera la matriz de numeros de minas adyacentes
        
        //Se añaden los objetos al layout
        layout.getChildren().addAll(labelminasencontradas, Cuadricula.labelcantminasencontradas, 
        labeltiempo, labelcanttiempo, Cuadricula.botonreset, botonexit, labelelegirmodo,
        botondummy, botonavanzado, botonsugerencia);
        
    }
    
    static void restart() {
        Cuadricula.cantbanderas = 0;
        Cuadricula.labelcantminasencontradas.setText(Integer.toString(Cuadricula.cantbanderas));
        Cuadricula.countersec = -1;
        Cuadricula.countermin = 0;
        Cuadricula.gameover = false;
        Cuadricula.victoria = false;
        Cuadricula.botonreset.setText(":|");
        Computadora.dummyfuncionando = false;
        Computadora.avanzadofuncionando = false;
        Computadora.turno = false;
        Random r = new Random();
        int numrandom = r.nextInt(8);
        for (int i=0; i<=7; i++) {
            for (int j=0; j<=7; j++) {
                Cuadricula.matrizvalores[i][j].mina = 0;
                Cuadricula.matrizvalores[i][j].bandera = 0;
                Cuadricula.matrizvalores[i][j].numrev = 0;
                Cuadricula.matrizvalores[i][j].revelado = false;
                Cuadricula.matrizboton[i][j].setText("");
                Cuadricula.colorAleatorio(i, j, numrandom);
            }
        }
        Cuadricula.generarMinas();
        Cuadricula.generarNumAdy();
        
    }
    
    static void ajustarMinasEncontradas() {
        for (int i=0; i<=7; i++) {
            for (int j=0; j<=7; j++) {
                if (Cuadricula.matrizvalores[i][j].bandera == 1 && Cuadricula.matrizvalores[i][j].revelado && Cuadricula.matrizvalores[i][j].numrev == 0) {
                    Cuadricula.matrizboton[i][j].setText("");
                    Cuadricula.cantbanderas--;
                    Cuadricula.matrizvalores[i][j].bandera = 0;
                }
                if (Cuadricula.matrizvalores[i][j].bandera == 1 && Cuadricula.matrizvalores[i][j].revelado && Cuadricula.matrizvalores[i][j].numrev > 0) {
                    Cuadricula.matrizboton[i][j].setText(Integer.toString(Cuadricula.matrizvalores[i][j].numrev));
                    Cuadricula.cantbanderas--;
                    Cuadricula.matrizvalores[i][j].bandera = 0;
                }
            }
        }
    }
    static void elegirEspacio(int i, int j) {
        Random r = new Random();
        while (Cuadricula.matrizvalores[i][j].revelado == true) {
            i = r.nextInt(8);
            j = r.nextInt(8);
        }
        
        if (Cuadricula.esMina(Cuadricula.matrizvalores[i][j].mina)) {
            Cuadricula.updateCuadricula();
            Cuadricula.revelarMinasDummy();
            Cuadricula.gameover = true;
            Cuadricula.botonreset.setText(":(");

        }
        else if (Cuadricula.matrizvalores[i][j].numrev == 0){
            Cuadricula.revelarCeros(i, j);
            ajustarMinasEncontradas();
            Cuadricula.labelcantminasencontradas.setText(Integer.toString(Cuadricula.cantbanderas));
            Cuadricula.updateCuadricula();
            Cuadricula.matrizboton[i][j].setStyle("-fx-background-color: #DADAD7;-fx-border-color: #0027FF;-fx-border-width: 3");
        }
        else {
            Cuadricula.matrizvalores[i][j].revelado = true;
            Cuadricula.matrizboton[i][j].setText(Integer.toString(Cuadricula.matrizvalores[i][j].numrev));
            Cuadricula.updateCuadricula();
            Cuadricula.matrizboton[i][j].setStyle("-fx-background-color: #DADAD7;-fx-border-color: #0027FF;-fx-border-width: 3");
            ajustarMinasEncontradas();
            Cuadricula.labelcantminasencontradas.setText(Integer.toString(Cuadricula.cantbanderas));
        }
        Cuadricula.checkVictoria(); // se checkea si se ha ganado
        if (Cuadricula.victoria == true) {
            Cuadricula.botonreset.setText(":)"); // se cambia el boton a una cara feliz para sombolizar victoria
        }
    }
}
