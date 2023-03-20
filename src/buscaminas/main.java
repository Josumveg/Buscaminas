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
        layout.getChildren().addAll();
        
    // Escenario
        Scene scene = new Scene(layout, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    // Matriz de botones
        int posx = -175;
        int posy = -150;
        Button [][] matriz = new Button[8][8];
        
        for (int i=0; i<=7; i++) {
            for (int j=0; j<=7; j++) {
                int col = j;
                int fila = i;
                if (j == 7){
                    matriz[fila][col] = new Button();
                    matriz[fila][col].setTranslateX(posx);
                    matriz[fila][col].setTranslateY(posy);
                    posx = -175;
                    posy = posy+50;
                    matriz[fila][col].setMaxSize(50,50);
                    matriz[fila][col].setOnAction(e -> {
                    matriz[fila][col].setText("0");
                    });
                    layout.getChildren().add(matriz[fila][col]);
                }
                else {
                    matriz[fila][col] = new Button();
                    matriz[fila][col].setTranslateX(posx);
                    matriz[fila][col].setTranslateY(posy);
                    posx = posx+50;
                    matriz[fila][col].setMaxSize(50,50);
                    matriz[fila][col].setOnAction(e -> {
                    matriz[fila][col].setText("0");
                    });
                    layout.getChildren().add(matriz[fila][col]);
                }
            }
        } 
    }
    
}
