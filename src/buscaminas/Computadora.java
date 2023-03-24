package buscaminas;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;

/**
 *
 * @author josep
 */
public class Computadora extends main{
    static boolean turno = false;
    static boolean dummyfuncionando = false;
    static boolean avanzadofuncionando = false;
    
    static Random r = new Random();
    static void dummy() {
        if (turno == true) {
            int randi = r.nextInt(8);
            int randj = r.nextInt(8);
            main.elegirEspacio(randi, randj);
            turno = false;
        }
    }
    
}
