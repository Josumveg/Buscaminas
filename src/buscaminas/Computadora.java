package buscaminas;
import java.util.Random;

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
            int randi = r.nextInt(8);;
            int randj = r.nextInt(8);;
            while (Cuadricula.matrizvalores[randi][randj].revelado == true) {
                randi = r.nextInt(8);
                randj = r.nextInt(8);
            }
            elegirEspacio(randi, randj);
            turno = false;
        }
    }
    
}
