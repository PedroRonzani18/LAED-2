/*
 * Pedro Augusto de Portilho Ronzani
 * Ulisses Andrade Carvalho
 * Lucas Andrade Brandão
 * 
 * Laboratorio de Algoritmos e Estruturas de Dados II
 */
import java.util.StringTokenizer;
import java.io.*;

public class ExtraiPalavra {

    private BufferedReader arquivo;
    private StringTokenizer texto;
    private int coluna = 0;
    private int linha = 0;

    public ExtraiPalavra(String arquivo) throws Exception {
        this.arquivo = new BufferedReader(new FileReader(arquivo));
        this.texto = null;
    }

    /**
     * Retorna a próxima palavra do arquivo de texto.
     */
    public String proximaPalavra() throws Exception {

        // Se não há mais palavras na linha atual, pula para a próxima linha
        if (texto == null || !texto.hasMoreTokens()) {
            this.coluna = 0;
            this.linha++;

            // Se não há mais linhas no arquivo, retorna null
            String linha = arquivo.readLine();
            if (linha == null)
                return null;

            // Se a linha é vazia, pula para a próxima linha
            this.texto = new StringTokenizer(linha, "1234567890/()-,.&*%;:<>?\t ");
            if (!texto.hasMoreTokens())
                return "";
        }

        this.coluna++;

        // Retorna o número com 16 dígitos completando com 0's
        String next = this.texto.nextToken();
        while (next.length() < 16) next += "0";
        return next;
    }

    public void fecharArquivos() throws Exception { this.arquivo.close(); }
    
    public int getColuna() { return coluna; }
    public int getLinha() { return linha; }
}