public class Reentrancia {

    // Variável global compartilhada — causa problemas
    private static StringBuilder global = new StringBuilder();

    public static void main(String[] args) {
        System.out.println("Iniciando teste com função NÃO REENTRANTE:");
        testarFuncaoNaoReentrante();

        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        System.out.println("\nIniciando teste com função REENTRANTE:");
        testarFuncaoReentrante();
    }

    // Função NÃO REENTRANTE: usa recurso global compartilhado sem sincronização
    public static void funcaoNaoReentrante(String texto) {
        global.setLength(0); // Limpa buffer
        for (char c : texto.toCharArray()) {
            global.append(c);
            try { Thread.sleep(5); } catch (InterruptedException e) {} // Simula processamento lento
        }
        System.out.println("Resultado: " + global);
    }

    public static void testarFuncaoNaoReentrante() {
        Runnable tarefa = () -> funcaoNaoReentrante(Thread.currentThread().getName());

        Thread t1 = new Thread(tarefa, "THREAD_A");
        Thread t2 = new Thread(tarefa, "THREAD_B");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {}
    }

    // Função REENTRANTE: usa variáveis locais, não compartilha estado
    public static void funcaoReentrante(String texto) {
        StringBuilder local = new StringBuilder();
        for (char c : texto.toCharArray()) {
            local.append(c);
            try { Thread.sleep(5); } catch (InterruptedException e) {}
        }
        System.out.println("Resultado: " + local);
    }

    public static void testarFuncaoReentrante() {
        Runnable tarefa = () -> funcaoReentrante(Thread.currentThread().getName());

        Thread t1 = new Thread(tarefa, "THREAD_A");
        Thread t2 = new Thread(tarefa, "THREAD_B");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {}
    }
}