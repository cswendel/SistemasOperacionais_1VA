import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GeradorArquivo {
    public static void main(String[] args) {
        String caminho = "arquivo_grande.txt";
        int tamanhoMB = 100;
        long bytesParaGerar = tamanhoMB * 1024L * 1024L;

        try (FileWriter writer = new FileWriter(caminho)) {
            Random random = new Random();
            long gerado = 0;

            while (gerado < bytesParaGerar) {
                String linha = "Linha aleatória " + random.nextInt(1000000) + "\n";
                writer.write(linha);
                gerado += linha.getBytes().length;
            }

            System.out.println("Arquivo gerado com sucesso: " + caminho);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}