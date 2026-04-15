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
        System.out.println("\nDistribuição de Probabilidades:");
        for (Map.Entry<Integer, Double> entry : tempoEstados.entrySet()) {
            double prob = entry.getValue() / tempoTotal;
            System.out.printf("Estado %d: %.5f\n", entry.getKey(), prob);
        }
    }
}
