public class Evento implements Comparable<Evento> {

    public enum Tipo {
        CHEGADA_FILA1,
        SAIDA_FILA1,
        SAIDA_FILA2
    }

    public double tempo;
    public Tipo tipo;

    public Evento(double tempo, Tipo tipo) {
        this.tempo = tempo;
        this.tipo = tipo;
    }

    @Override
    public int compareTo(Evento o) {
        return Double.compare(this.tempo, o.tempo);
    }
}
