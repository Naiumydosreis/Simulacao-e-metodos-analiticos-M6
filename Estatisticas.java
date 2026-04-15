import java.util.Map;
import java.util.TreeMap;

public class Estatisticas {

    private Map<Integer, Double> tempoEstados = new TreeMap<>();
    private double ultimoTempo = 0;

    public void atualizar(int estado, double tempoAtual) {
        double delta = tempoAtual - ultimoTempo;

        tempoEstados.put(estado,
                tempoEstados.getOrDefault(estado, 0.0) + delta);

        ultimoTempo = tempoAtual;
    }

    public void imprimir(double tempoTotal) {
        System.out.println("\nDistribuição de Probabilidades e Tempos Acumulados:");
        for (Map.Entry<Integer, Double> entry : tempoEstados.entrySet()) {
            double tempoAcumulado = entry.getValue();
            double probabilidade = tempoAcumulado / tempoTotal;
            System.out.printf("Estado %d: Tempo Acumulado = %.5f, Probabilidade = %.5f\n", entry.getKey(), tempoAcumulado, probabilidade);
        }
    }
}
