import java.util.*;
import java.util.Random;

class Documento {
    int tempoProcessamento;
    int prioridade;

    public Documento(int tempoProcessamento, int prioridade) {
        this.tempoProcessamento = tempoProcessamento;
        this.prioridade = prioridade;
    }

    @Override
    public String toString() {
        return "Tempo de processamento: " + tempoProcessamento + " segundos | Prioridade: " + prioridade;
    }
}

class Spooler {
    private PriorityQueue<Documento> fila;

    public Spooler() {
        fila = new PriorityQueue<>(Comparator.comparingInt(d -> d.prioridade));
    }

    public void adicionarDocumento(Documento documento) {
        fila.add(documento);
        System.out.println(documento + " adicionado à fila.");
    }

    public void processarFila() {
        while (!fila.isEmpty()) {
            Documento documentoAtual = fila.poll();
            System.out.println("Processando " + documentoAtual);
            try {
                Thread.sleep(documentoAtual.tempoProcessamento * 1000);
            } catch (InterruptedException e) {
                System.err.println("Erro ao processar o documento: " + e.getMessage());
            }
            System.out.println("Documento processado.");
        }
        System.out.println("Todos os documentos foram processados.");
    }
}

public class SimuladorFilaImpressao {
    public static void main(String[] args) {
        Random numeroAleatorio = new Random();
        Spooler spooler = new Spooler();

        for (int i = 0; i < 5; i++) {
            int tempoProcessamento = numeroAleatorio.nextInt(10) + 1;
            int prioridade = numeroAleatorio.nextInt(4) + 1;
            Documento doc = new Documento(tempoProcessamento, prioridade);
            spooler.adicionarDocumento(doc);
        }

        spooler.processarFila();
    }
}
