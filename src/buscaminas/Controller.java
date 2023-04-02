package buscaminas;

import org.firmata4j.firmata.*;
import org.firmata4j.IODevice;
import org.firmata4j.Pin;
import java.io.IOException;
import javafx.application.Platform;

public class Controller {
    
    static boolean controllerFuncionando = false;
    
    private static int controli = 0;
    private static int controlj = 0;
    
    static final String USBPORT = "COM3";
    
    static final int ButtonSel = 2;
    static final int ButtonRight = 3;
    static final int ButtonLeft = 4;
    static final int ButtonDown = 5;
    static final int ButtonUp = 6;
    
    static final int LED = 7;
    //static final int Buzzer = 8;
    
    static Pin RedLED;
    //static Pin activeBuzzer;
    
    public static void startController() throws IOException, InterruptedException {
        IODevice arduino = new FirmataDevice(USBPORT);
        
        try {
            
            arduino.start();
            arduino.ensureInitializationIsDone();
            System.out.println("El arduino se ha conectado");
            controllerFuncionando = true;
            
        }
        catch (IOException ioexception) {
            System.out.println("Trouble connecting to board");
        }
        finally {
            
            RedLED = arduino.getPin(LED);
            RedLED.setMode(Pin.Mode.OUTPUT);
            
            //activeBuzzer = arduino.getPin(Buzzer);
            //activeBuzzer.setMode(Pin.Mode.OUTPUT);
            
            Pin buttonSel = arduino.getPin(ButtonSel);
            buttonSel.setMode(Pin.Mode.INPUT);
            
            Pin buttonRight = arduino.getPin(ButtonRight);
            buttonRight.setMode(Pin.Mode.INPUT);
            
            Pin buttonLeft = arduino.getPin(ButtonLeft);
            buttonLeft.setMode(Pin.Mode.INPUT);
            
            Pin buttonDown = arduino.getPin(ButtonDown);
            buttonDown.setMode(Pin.Mode.INPUT);
            
            Pin buttonUp = arduino.getPin(ButtonUp);
            buttonUp.setMode(Pin.Mode.INPUT);
            
            
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
    
    public static void resetSizes() {
        for (int i=0; i<=7; i++) {
            for (int j=0; j<=7; j++) {
                Cuadricula.matrizboton[controli][controlj].setMaxSize(50, 50);
            }
        }
    }
}
