import java.util.PriorityQueue;

public class Simulador {

    private PriorityQueue<Evento> eventos = new PriorityQueue<>();

    private Fila fila1 = new Fila(3, 2);
    private Fila fila2 = new Fila(5, 1);

    private GeradorAleatorio gerador = new GeradorAleatorio();

    private Estatisticas statsFila1 = new Estatisticas();
    private Estatisticas statsFila2 = new Estatisticas();

    private double tempoAtual = 0;

    public void iniciar() {

        eventos.add(new Evento(1.5, Evento.Tipo.CHEGADA_FILA1));

        while (gerador.usados < 100000 && !eventos.isEmpty()) {

            Evento e = eventos.poll();
            tempoAtual = e.tempo;

            statsFila1.atualizar(fila1.tamanhoAtual(), tempoAtual);
            statsFila2.atualizar(fila2.tamanhoAtual(), tempoAtual);

            switch (e.tipo) {

                case CHEGADA_FILA1:
                    chegadaFila1();
                    break;

                case SAIDA_FILA1:
                    saidaFila1();
                    break;

                case SAIDA_FILA2:
                    saidaFila2();
                    break;
            }
        }

        imprimirResultados();
    }

    private void chegadaFila1() {

        double prox = tempoAtual + gerador.uniforme(1, 4);
        eventos.add(new Evento(prox, Evento.Tipo.CHEGADA_FILA1));

        if (fila1.cheia()) {
            fila1.perdas++;
            return;
        }

        fila1.chegar(tempoAtual);

        if (fila1.servidorLivre()) {
            fila1.iniciarAtendimento();

            double tempoServico = gerador.uniforme(3, 4);
            eventos.add(new Evento(tempoAtual + tempoServico, Evento.Tipo.SAIDA_FILA1));
        }
    }

    private void saidaFila1() {

        fila1.finalizarAtendimento();

        // vai para fila 2
        if (fila2.cheia()) {
            fila2.perdas++;
        } else {
            fila2.chegar(tempoAtual);

            if (fila2.servidorLivre()) {
                fila2.iniciarAtendimento();

                double tempoServico = gerador.uniforme(2, 3);
                eventos.add(new Evento(tempoAtual + tempoServico, Evento.Tipo.SAIDA_FILA2));
            }
        }

        // atende próximo da fila1
        if (fila1.temFila()) {
            fila1.iniciarAtendimento();

            double tempoServico = gerador.uniforme(3, 4);
            eventos.add(new Evento(tempoAtual + tempoServico, Evento.Tipo.SAIDA_FILA1));
        }
    }

    private void saidaFila2() {

        fila2.finalizarAtendimento();

        if (fila2.temFila()) {
            fila2.iniciarAtendimento();

            double tempoServico = gerador.uniforme(2, 3);
            eventos.add(new Evento(tempoAtual + tempoServico, Evento.Tipo.SAIDA_FILA2));
        }
    }

    private void imprimirResultados() {

        System.out.println("===== RESULTADOS =====");

        System.out.println("Tempo total: " + tempoAtual);

        System.out.println("\nPerdas:");
        System.out.println("Fila 1: " + fila1.perdas);
        System.out.println("Fila 2: " + fila2.perdas);

        System.out.println("\nFila 1:");
        statsFila1.imprimir(tempoAtual);

        System.out.println("\nFila 2:");
        statsFila2.imprimir(tempoAtual);
    }
}
