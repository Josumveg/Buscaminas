package buscaminas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.control.ChoiceBox;

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

        // Matriz de botones
        int posx = -175;
        int posy = -150;
        
        for (int i=0; i<=7; i++) {
            for (int j=0; j<=7; j++) {
                int col = j;
                int fila = i;
                if (j == 7){
                    Cuadricula.matrizboton[fila][col] = new Button();
                    Cuadricula.matrizvalores[fila][col] = new Cuadricula(0, 0, 0);
                    Cuadricula.matrizboton[fila][col].setTranslateX(posx);
                    Cuadricula.matrizboton[fila][col].setTranslateY(posy);
                    posx = -175;
                    posy = posy+50;
                    Cuadricula.matrizboton[fila][col].setMaxSize(50,50);
                    Cuadricula.matrizboton[fila][col].setOnAction(e -> {
                        
                        if (Cuadricula.esMina(Cuadricula.matrizvalores[fila][col].mina)) {
                            Cuadricula.revelarMinas();
                        }
                        else if (Cuadricula.matrizvalores[fila][col].numrev == 0){
                            Cuadricula.matrizboton[fila][col].setStyle("-fx-background-color: #FFFFFF;");
                            Cuadricula.revelarCeros(fila, col);
                        }
                        else {
                            Cuadricula.matrizboton[fila][col].setText(Integer.toString(Cuadricula.matrizvalores[fila][col].numrev));
                        }
                    
                    });
                    layout.getChildren().add(Cuadricula.matrizboton[fila][col]);
                }
                else {
                    Cuadricula.matrizboton[fila][col] = new Button();
                    Cuadricula.matrizvalores[fila][col] = new Cuadricula(0, 0, 0);
                    Cuadricula.matrizboton[fila][col].setTranslateX(posx);
                    Cuadricula.matrizboton[fila][col].setTranslateY(posy);
                    posx = posx+50;
                    Cuadricula.matrizboton[fila][col].setMaxSize(50,50);
                    Cuadricula.matrizboton[fila][col].setOnAction(e -> {
                        
                        if (Cuadricula.esMina(Cuadricula.matrizvalores[fila][col].mina)) {
                            Cuadricula.revelarMinas();
                        }
                        else if (Cuadricula.matrizvalores[fila][col].numrev == 0){
                            Cuadricula.matrizboton[fila][col].setStyle("-fx-background-color: #FFFFFF;");
                            Cuadricula.revelarCeros(fila, col);
                        }
                        else {
                            Cuadricula.matrizboton[fila][col].setText(Integer.toString(Cuadricula.matrizvalores[fila][col].numrev));
                        }
                    
                    });
                    layout.getChildren().add(Cuadricula.matrizboton[fila][col]); 
                }
            }
        }
        
        // Valores de la cuadricula
        Cuadricula.generarMinas();
        Cuadricula.generarNumAdy();
        Cuadricula.revelarMinas();
    }
    
}
