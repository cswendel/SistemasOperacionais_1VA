import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class InterrupcaoProcesso {

    private static volatile boolean interrompido = false;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public static void main(String[] args) {
        log("Início do processo principal");

        Thread processoPrincipal = new Thread(() -> {
            for (int i = 1; i <= 20; i++) {
                if (interrompido) {
                    log("Processo principal interrompido! Executando rotina externa...");
                    executarRotinaExterna();
                    interrompido = false;
                    log("Rotina externa finalizada. Processo principal retomado.");
                }

                log("Executando passo " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    log("Thread principal foi interrompida diretamente.");
                    break;
                }
            }
            log("Processo principal finalizado.");
        });

        Thread rotinaInterrupcao = new Thread(() -> {
            try {
                Thread.sleep(2000); //Alterar esse valor caso queira testar diferentes momentos
                log("Interrupção disparada!");
                interrompido = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        processoPrincipal.start();
        rotinaInterrupcao.start();

        try {
            processoPrincipal.join();
            rotinaInterrupcao.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log("Fim da execução geral.");
    }

    private static void executarRotinaExterna() {
        try {
            log("Rotina externa: salvando estado...");
            Thread.sleep(2000); //Simula tempo de execução da rotina
            log("Rotina externa: estado salvo com sucesso.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void log(String mensagem) {
        String hora = LocalTime.now().format(formatter);
        System.out.println("[" + hora + "] " + mensagem);
    }
}