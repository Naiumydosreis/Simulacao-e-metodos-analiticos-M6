import java.util.Random;

public class GeradorAleatorio {

    private Random random = new Random();
    public int usados = 0;

    public double uniforme(double min, double max) {
        usados++;
        return min + (max - min) * random.nextDouble();
    }
}
