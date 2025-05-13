import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeituraComBuffer {
    public static void main(String[] args) {
        String caminho = "arquivo_grande.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            long inicio = System.currentTimeMillis();
            while (reader.readLine() != null) {
                // Apenas lemos, não processamos
            }
            long fim = System.currentTimeMillis();
            System.out.println("Tempo de leitura COM buffer: " + (fim - inicio) + " ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}