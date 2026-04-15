import java.util.LinkedList;
import java.util.Queue;

public class Fila {

    private int capacidade;
    private int servidores;
    private int ocupados;
    private Queue<Double> fila;

    public int perdas = 0;

    public Fila(int capacidade, int servidores) {
        this.capacidade = capacidade;
        this.servidores = servidores;
        this.ocupados = 0;
        this.fila = new LinkedList<>();
    }

    public boolean cheia() {
        return (fila.size() + ocupados) >= capacidade;
    }

    public boolean servidorLivre() {
        return ocupados < servidores;
    }

    public void chegar(double tempo) {
        fila.add(tempo);
    }

    public void iniciarAtendimento() {
        if (!fila.isEmpty() && servidorLivre()) {
            fila.poll();
            ocupados++;
        }
    }

    public void finalizarAtendimento() {
        if (ocupados > 0) {
            ocupados--;
        }
    }

    public boolean temFila() {
        return !fila.isEmpty();
    }

    public int tamanhoAtual() {
        return fila.size() + ocupados;
    }

    public int getOcupados() {
        return ocupados;
    }
}
