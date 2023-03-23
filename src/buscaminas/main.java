package buscaminas;

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
        Scene scene = new Scene(layout, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Creacion de labels
        Label labelminasencontradas = new Label();
        labelminasencontradas.setText("Minas encontradas");
        labelminasencontradas.setTranslateX(125);
        labelminasencontradas.setTranslateY(-230);
        
        Label labelcantminasencontradas = new Label();
        labelcantminasencontradas.setText("0");
        labelcantminasencontradas.setAlignment(Pos.CENTER);
        labelcantminasencontradas.setStyle("-fx-background-color: #DADAD7;");
        labelcantminasencontradas.setMaxSize(50, 30);
        labelcantminasencontradas.setTranslateX(125);
        labelcantminasencontradas.setTranslateY(-200);
        
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
        
        // Implementacion de Timer 
        
        Timer timer = new Timer(); 
        TimerTask task = new TimerTask() {
            int countersec = 0;
            int countermin = 0;
            @Override
            public void run() {
                if (countersec == 59) {
                    countermin++;
                    countersec = -1;
                }
                if (Cuadricula.gameover == false) {
                    if (countermin==0) {
                        if (countersec < 9) {
                            Platform.runLater(() -> labelcanttiempo.setText("00:0" + Integer.toString(countersec)));
                            countersec++;
                        }
                        else {
                            Platform.runLater(() -> labelcanttiempo.setText("00:" + Integer.toString(countersec)));
                            countersec++;
                        }
                    }
                    else if (countermin < 9) {
                        if (countersec < 9) {
                            Platform.runLater(() -> labelcanttiempo.setText("0" + Integer.toString(countermin) + ":0" + Integer.toString(countersec)));
                            countersec++;
                        }
                        else {
                            Platform.runLater(() -> labelcanttiempo.setText("0" + Integer.toString(countermin) + ":" + Integer.toString(countersec)));
                            countersec++;
                        }
                    }
                    else {
                        if (countersec < 9) {
                            Platform.runLater(() -> labelcanttiempo.setText(Integer.toString(countermin) + ":0" + Integer.toString(countersec)));
                            countersec++;
                        }
                        else {
                            Platform.runLater(() -> labelcanttiempo.setText(Integer.toString(countermin) + ":" + Integer.toString(countersec)));
                            countersec++;
                        }
                    }
                }
                else {
                    timer.cancel();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000l); 
            
        // Matriz de botones
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
                    Cuadricula.matrizboton[fila][col].setStyle("-fx-border-color: #272323;");
                    Cuadricula.matrizboton[fila][col].setOnMouseClicked(e -> {
                        
                        if (Cuadricula.gameover == false) {
                            if (e.getButton() == MouseButton.PRIMARY) {
                                if (Cuadricula.esMina(Cuadricula.matrizvalores[fila][col].mina)) {
                                    Cuadricula.revelarMinas();
                                    Cuadricula.gameover = true;
                                }
                                else if (Cuadricula.matrizvalores[fila][col].numrev == 0){
                                    Cuadricula.revelarCeros(fila, col);
                                }
                                else {
                                    Cuadricula.matrizvalores[fila][col].revelado = true;
                                    Cuadricula.matrizboton[fila][col].setText(Integer.toString(Cuadricula.matrizvalores[fila][col].numrev));
                                    Cuadricula.matrizboton[fila][col].setStyle("-fx-background-color: #DADAD7;-fx-border-color: #C2C2C2;");
                                }
                            }
                            else if (e.getButton() == MouseButton.SECONDARY) {
                                if (Cuadricula.matrizvalores[fila][col].revelado == false) {
                                    if (Cuadricula.matrizvalores[fila][col].bandera == 0) {
                                        Cuadricula.matrizboton[fila][col].setText("|>");
                                        Cuadricula.matrizvalores[fila][col].bandera = 1;
                                        Cuadricula.cantbanderas++;
                                        labelcantminasencontradas.setText(Integer.toString(Cuadricula.cantbanderas));
                                    }
                                    else {
                                        Cuadricula.matrizboton[fila][col].setText("");
                                        Cuadricula.matrizvalores[fila][col].bandera = 0;
                                        Cuadricula.cantbanderas--;
                                        labelcantminasencontradas.setText(Integer.toString(Cuadricula.cantbanderas));
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
                    Cuadricula.matrizboton[fila][col].setStyle("-fx-border-color: #272323;");
                    Cuadricula.matrizboton[fila][col].setOnMouseClicked(e -> {
                        
                        if (Cuadricula.gameover == false) {
                            if (e.getButton() == MouseButton.PRIMARY) {
                                if (Cuadricula.esMina(Cuadricula.matrizvalores[fila][col].mina)) {
                                    Cuadricula.revelarMinas();
                                    Cuadricula.gameover = true;
                                }
                                else if (Cuadricula.matrizvalores[fila][col].numrev == 0){
                                    Cuadricula.revelarCeros(fila, col);
                                }
                                else {
                                    Cuadricula.matrizvalores[fila][col].revelado = true;
                                    Cuadricula.matrizboton[fila][col].setText(Integer.toString(Cuadricula.matrizvalores[fila][col].numrev));
                                    Cuadricula.matrizboton[fila][col].setStyle("-fx-background-color: #DADAD7;-fx-border-color: #C2C2C2;");
                                }
                            }
                            else if (e.getButton() == MouseButton.SECONDARY) {
                                if (Cuadricula.matrizvalores[fila][col].revelado == false) {
                                    if (Cuadricula.matrizvalores[fila][col].bandera == 0) {
                                        Cuadricula.matrizboton[fila][col].setText("|>");
                                        Cuadricula.matrizvalores[fila][col].bandera = 1;
                                        Cuadricula.cantbanderas++;
                                        labelcantminasencontradas.setText(Integer.toString(Cuadricula.cantbanderas));
                                    }
                                    else {
                                        Cuadricula.matrizboton[fila][col].setText("");
                                        Cuadricula.matrizvalores[fila][col].bandera = 0;
                                        Cuadricula.cantbanderas--;
                                        labelcantminasencontradas.setText(Integer.toString(Cuadricula.cantbanderas));
                                    }
                                }
                            }
                        }
                    
                    });
                    layout.getChildren().add(Cuadricula.matrizboton[fila][col]); 
                }
            }
        }
        
        
        // Valores de la cuadricula
        Cuadricula.generarMinas();
        Cuadricula.generarNumAdy();
        
        //Se añaden los objetos al layout
        layout.getChildren().addAll(labelminasencontradas, labelcantminasencontradas, labeltiempo, labelcanttiempo);
        
        
    }
    
    void restart() {
        
    }
    
}
