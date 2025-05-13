import java.util.*;
import java.util.Random;

class Processo {
    String Nome;
    int TempoRestante;

    public Processo(String Nome, int TempoExecucao) {
        this.Nome = Nome;
        this.TempoRestante = TempoExecucao;
    }
}

public class SimuladorProcessos {
    public static void main(String[] args) {
        Random NumeroAleatorio = new Random();

        List<Processo> Processos = new ArrayList<>();
            Processos.add(new Processo("P1", NumeroAleatorio.nextInt(10) + 1));
            Processos.add(new Processo("P2", NumeroAleatorio.nextInt(10) + 1));
            Processos.add(new Processo("P3", NumeroAleatorio.nextInt(10) + 1));

        System.out.println("=== EXECUÇÃO MONOPROGRAMADA ===");
        ExecutarMonoprogramado(Processos);

        //Quantum é a fatia de tempo/processamento que ele poderá utilizar para realizar sua tarefa
        int Quantum = NumeroAleatorio.nextInt(5) + 1;

        System.out.println("\n=== EXECUÇÃO MULTIPROGRAMADA (Quantum = " + Quantum + ") ===");
        ExecutarMultiprogramado(Processos, Quantum);
    }

    public static void ExecutarMonoprogramado(List<Processo> ListaProcessos) {
        List<Processo> Processos = CopiarLista(ListaProcessos);
        for (Processo p : Processos) {
            System.out.println("Iniciando " + p.Nome + " (Tempo total: " + p.TempoRestante + ")");
            while (p.TempoRestante > 0) {
                System.out.println(p.Nome + " executando... (Tempo restante: " + p.TempoRestante + ")");
                p.TempoRestante--;
                Pausar(500);
            }
            System.out.println(p.Nome + " FINALIZADO!\n");
        }
    }

    public static void ExecutarMultiprogramado(List<Processo> ListaProcessos, int Quantum) {
        Queue<Processo> Fila = new LinkedList<>(CopiarLista(ListaProcessos));

        while (!Fila.isEmpty()) {
            Processo ProcessoAtual = Fila.poll();
            
            System.out.println("\nExecutando " + ProcessoAtual.Nome + " (Tempo restante: " + ProcessoAtual.TempoRestante + ")");

            int TempoExecutado = Math.min(Quantum, ProcessoAtual.TempoRestante);
            for (int i = 0; i < TempoExecutado; i++) {
                ProcessoAtual.TempoRestante--;
                System.out.println(ProcessoAtual.Nome + " executando... (Tempo restante: " + ProcessoAtual.TempoRestante + ")");
                Pausar(500);
            }

            if (ProcessoAtual.TempoRestante > 0) {
                Fila.offer(ProcessoAtual);
            } else {
                System.out.println(ProcessoAtual.Nome + " FINALIZADO!\n");
            }
        }
    }

    private static List<Processo> CopiarLista(List<Processo> ListaProcessos) {
        List<Processo> Copia = new ArrayList<>();
        for (Processo p : ListaProcessos) {
            Copia.add(new Processo(p.Nome, p.TempoRestante));
        }
        return Copia;
    }

    private static void Pausar(int Milissegundos) {
        try {
            Thread.sleep(Milissegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}