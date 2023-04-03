package buscaminas;

import org.firmata4j.firmata.*;
import org.firmata4j.IODevice;
import org.firmata4j.Pin;
import java.io.IOException;
import javafx.application.Platform;

/**
 * Clase para conectar el codigo con
 * el control de arduino. 
 * @author Jose Pablo Umaña Vega
 */
public class Controller {
    
    static boolean controllerFuncionando = false; // boolean para saber si el control esta en uso
    
    private static int controli = 0; // indice fila para seleccionar un espacio en la cuadricula
    private static int controlj = 0; // indice columna para seleccionar un espacio en la cuadricula
    
    static final String USBPORT = "COM4"; // puerto USB en el que se conecta el arduino
    
    static final int ButtonSel = 2; // pin donde se encuentra el boton para seleccionar
    static final int ButtonRight = 3; // pin donde se encuentra el boton para moverse hacia la derecha
    static final int ButtonLeft = 4; // pin donde se encuentra el boton para moverse hacia la izquierda 
    static final int ButtonDown = 5; // pin donde se encuentra el boton para moverse hacia abajo 
    static final int ButtonUp = 6; // pin donde se encuentra el boton para moverse hacia arriba 
    
    static final int LED = 7; // pin donde se encuentra el LED 
    //static final int Buzzer = 8;
    
    static Pin RedLED;
    //static Pin activeBuzzer;
    
    /**
     * Se inicia la conexion con el arduino
     * mediante firmata. Una vez conectado se
     * tiene un ciclo while que debe correr
     * en un Thread para leer los inputs del
     * control. Los botones de direccion modifican
     * las variables controli y controlj 
     * al presionar el boton para seleccionar se 
     * revela el espacio en la cuadricula donde se
     * encuentran los indices controli y controlj.
     * @throws IOException
     * @throws InterruptedException 
     */
    public static void startController() throws IOException, InterruptedException {
        IODevice arduino = new FirmataDevice(USBPORT); // se crea el objeto arduino con el puerto en el que esta conectado
        
        try {
            
            arduino.start(); // se inicializa la conexion con el arduino
            arduino.ensureInitializationIsDone(); // se asegura que la conexion se haya completado 
            System.out.println("El arduino se ha conectado"); // se hace un print para saber cuando esta listo para usar 
            controllerFuncionando = true; // se cambia la variable a true para saber que el control esta en uso
            
        }
        catch (IOException ioexception) { // en caso de que falle la condicion con el arduino 
            System.out.println("Trouble connecting to board"); // se hace un print para saber que fallo la conexion
        }
        finally { // aqui se pone el codigo que se quiere ejecutar
            
            RedLED = arduino.getPin(LED); // se le asigna el pin del arduino donde se encuentra el LED a una variable
            RedLED.setMode(Pin.Mode.OUTPUT); // se le asigna el modo output al LED
            
            //activeBuzzer = arduino.getPin(Buzzer);
            //activeBuzzer.setMode(Pin.Mode.OUTPUT);
            
            Pin buttonSel = arduino.getPin(ButtonSel); // se le asigna el pin del arduino donde se encuentra el boton a una variable
            buttonSel.setMode(Pin.Mode.INPUT); // se le asigna el modo input al boton 
            
            Pin buttonRight = arduino.getPin(ButtonRight); // se le asigna el pin del arduino donde se encuentra el boton a una variable
            buttonRight.setMode(Pin.Mode.INPUT); // se le asigna el modo input al boton 
            
            Pin buttonLeft = arduino.getPin(ButtonLeft); // se le asigna el pin del arduino donde se encuentra el boton a una variable
            buttonLeft.setMode(Pin.Mode.INPUT); // se le asigna el modo input al boton 
            
            Pin buttonDown = arduino.getPin(ButtonDown); // se le asigna el pin del arduino donde se encuentra el boton a una variable
            buttonDown.setMode(Pin.Mode.INPUT); // se le asigna el modo input al boton 
            
            Pin buttonUp = arduino.getPin(ButtonUp); // se le asigna el pin del arduino donde se encuentra el boton a una variable
            buttonUp.setMode(Pin.Mode.INPUT); // se le asigna el modo input al boton 
            
            /**
             * Loop que registra cuando se presionan los
             * botones de direccion y seleccion. Al presionar
             * los botones de direccion se cambia el tamaño
             * del boton donde se encuentra el controli y 
             * controlj para visualizar donde se va a
             * seleccionar. 
             */
            while (controllerFuncionando == true) { 
                if (buttonRight.getValue() != 0) {
                    if (controlj < 7) {
                        resetSizes();
                        controlj++;
                        Cuadricula.matrizboton[controli][controlj].setMaxSize(40, 40);
                        Thread.sleep(200);
                    }
                }
                if (buttonLeft.getValue() != 0) {
                    if (controlj > 0) {
                        resetSizes();
                        controlj--;
                        Cuadricula.matrizboton[controli][controlj].setMaxSize(40, 40);
                        Thread.sleep(200);
                    }
                }
                if (buttonUp.getValue() != 0) {
                    if (controli > 0) {
                        resetSizes();
                        controli--;
                        Cuadricula.matrizboton[controli][controlj].setMaxSize(40, 40);
                        Thread.sleep(200);
                    }
                }
                if (buttonDown.getValue() != 0) {
                    if (controli < 7) {
                        resetSizes();
                        controli++;
                        Cuadricula.matrizboton[controli][controlj].setMaxSize(40, 40);
                        Thread.sleep(200);
                    }
                }
                if (buttonSel.getValue() != 0) {
                    Platform.runLater(() -> main.elegirEspacioController(controli, controlj));
                    Thread.sleep(250);
                }
            }
            
            arduino.stop();
            controllerFuncionando = false;
            
        }
    }
    
    /**
     * Se reinician los tamaños de
     * los botones de la cuadricula. 
     * Vuelven a su tamaño inicial. 
     */
    public static void resetSizes() {
        for (int i=0; i<=7; i++) {
            for (int j=0; j<=7; j++) {
                Cuadricula.matrizboton[controli][controlj].setMaxSize(50, 50);
            }
        }
    }
}
