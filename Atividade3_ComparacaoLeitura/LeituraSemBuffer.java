import java.io.FileReader;
import java.io.IOException;

public class LeituraSemBuffer {
    public static void main(String[] args) {
        String caminho = "arquivo_grande.txt";

        try (FileReader reader = new FileReader(caminho)) {
            long inicio = System.currentTimeMillis();
            int caractere;
            while ((caractere = reader.read()) != -1) {
                // Apenas lemos, não processamos
            }
            long fim = System.currentTimeMillis();
            System.out.println("Tempo de leitura SEM buffer: " + (fim - inicio) + " ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}