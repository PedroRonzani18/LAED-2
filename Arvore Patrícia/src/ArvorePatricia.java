/*
 * Pedro Augusto de Portilho Ronzani
 * Ulisses Andrade Carvalho
 * Lucas Andrade Brandão
 * 
 * Laboratorio de Algoritmos e Estruturas de Dados II
 */
import java.util.ArrayList;

public class ArvorePatricia {

    private static abstract class PatNo {
    }

    private static class PatNoInt extends PatNo {
        int index; // Índice do bit
        PatNo esq, dir; // Filhos esq e dir
    }

    private PatNo criaNoInt(int index, PatNo esq, PatNo dir) {

        PatNoInt p = new PatNoInt();
        p.index = index;
        p.esq = esq;
        p.dir = dir;
        return p;
    }

    private static class PatNoExt extends PatNo {
        String chave; // String armazenada no nó
        ArrayList<Integer> colunas = new ArrayList<>(); // lista de colunas onde a string foi encontrada
        ArrayList<Integer> linhas = new ArrayList<>(); // lista de linhas onde a string foi encontrada
    }

    private PatNo criaNoExt(String chave, int linha, int coluna) {

        PatNoExt p = new PatNoExt();
        p.chave = chave;
        p.linhas.add(linha);
        p.colunas.add(coluna);
        return p;
    }

    private PatNo raiz; // Raiz da arvore
    private int numeroBits; // Numero de bits que serão utilizados para a comparação das strings

    public ArvorePatricia(int numeroBits) {
        this.raiz = null;
        this.numeroBits = numeroBits;
    }

    public void pesquisa(String chave) {
        this.pesquisa(chave, this.raiz);
    }

    /**
     * @param chave   A string a ser pesquisada
     * @param noAtual O nó atual da árvore
     */
    private void pesquisa(String chave, PatNo noAtual) {

        // adiciona zeros à direita da string até que ela tenha 16 caracteres
        chave = pushZeros(chave);

        // remove os zeros adicionados à direita da string para exibir a palavra corretamente
        String palavra = popZeros(chave);

        // é um nó externo => chegou no final da árvore => string pode ou não ter sido encontrada
        if (this.eExterno(noAtual)) {  
            
            PatNoExt noExterno = (PatNoExt) noAtual;

            // se a string não for igual à string do nó externo, a string não foi encontrada
            if (!noExterno.chave.equals(chave)) {
                System.out.println("\"" + palavra + "\"" + " NAO encontrado");
                return;
            }

            System.out.println("\"" + palavra + "\"" + " encontrado");
            for (int i = 0; i < noExterno.linhas.size(); i++)
                System.out.println(i + " => (" + noExterno.linhas.get(i) + ", " + noExterno.colunas.get(i) + ")");
            System.out.println();

        } else { // é um nó interno => continua a pesquisa

            PatNoInt noInterno = (PatNoInt) noAtual;
            if (this.bit(noInterno.index, chave) == 0)
                pesquisa(chave, noInterno.esq);
            else
                pesquisa(chave, noInterno.dir);
        }
    }

    /**
     * Adiciona zeros à direita de uma string até que ela tenha 16 caracteres
     */
    private String pushZeros(String string) {

        while (string.length() < 16)
            string += "0";

        return string;
    }

    /**
     * Remove os zeros adicionados à direita de uma string
     */
    private String popZeros(String string) {

        int index = string.indexOf('0');
        if (index == -1)
            index = string.length();

        return string.substring(0, index);
    }

    public void insere(String chave, int linha, int coluna) {
        this.raiz = this.insere(chave, linha, coluna, this.raiz);
    }

    /**
     * @param chave   A string a ser inserida
     * @param linha   A linha onde a string foi encontrada
     * @param coluna  A coluna onde a string foi encontrada
     * @param noAtual O nó atual da árvore
     * @return O nó raiz da árvore
     */
    private PatNo insere(String chave, int linha, int coluna, PatNo noAtual) {

        // se a árvore estiver vazia, cria um nó externo
        if (noAtual == null)
            return this.criaNoExt(chave, linha, coluna);

        // enquanto não for um nó externo, percorre a árvore
        PatNo noCorrente = noAtual;
        while (!this.eExterno(noCorrente)) {
            PatNoInt noInterno = (PatNoInt) noCorrente;
            noCorrente = (this.bit(noInterno.index, chave) == 1)
                    ? noInterno.dir
                    : noInterno.esq;
        }

        // faz casting para garantir que é externo
        PatNoExt noExterno = (PatNoExt) noCorrente;

        // compara as strings até encontrar um bit diferente
        int indice = 1;
        for (; (indice <= this.numeroBits) && (this.bit(indice, chave) == this.bit(indice, noExterno.chave)); indice++) {}

        // se a string já estiver na árvore, adiciona a linha e a coluna
        if (indice > this.numeroBits) {
            noExterno.linhas.add(linha);
            noExterno.colunas.add(coluna);
            return noAtual;
        }

        // se a string não estiver, insere a string entre dois nós
        return this.insereEntre(chave, linha, coluna, noAtual, indice);
    }

    /**
     * Insere uma string entre dois nós da árvore
     * 
     * @param chave   A string a ser inserida
     * @param linha   A linha onde a string foi encontrada
     * @param coluna  A coluna onde a string foi encontrada
     * @param noAtual O nó atual da árvore
     * @param indice  O índice do bit que será utilizado para a comparação
     * @return O nó raiz da árvore
     */
    private PatNo insereEntre(String chave, int linha, int coluna, PatNo noAtual, int indice) {

        PatNoInt noInterno = null;

        // se for intertno, faço um casting para garantir que é interno
        if (!this.eExterno(noAtual))
            noInterno = (PatNoInt) noAtual;

        // se for externo ou se o índice for menor que o índice do nó interno, cria um novo nó externo
        if (this.eExterno(noAtual) || (indice < noInterno.index)) {

            PatNo novoNo = this.criaNoExt(chave, linha, coluna);

            return (this.bit(indice, chave) == 1) ? this.criaNoInt(indice, noAtual, novoNo)
                    : this.criaNoInt(indice, novoNo, noAtual);

        } else { // não sendo um nó externo, ou sendo anterior a um interno, deve ser posicionado entre 2 nos

            // se o bit for 1, insere à direita, senão insere à esquerda
            if (this.bit(noInterno.index, chave) == 1)
                noInterno.dir = this.insereEntre(chave, linha, coluna, noInterno.dir, indice);
            else
                noInterno.esq = this.insereEntre(chave, linha, coluna, noInterno.esq, indice);
            return noInterno;
        }
    }

    /**
     * Método privado para obter o valor do bit de uma string em um determinado
     * índice.
     * 
     * @param index O índice do bit a ser obtido.
     * @param chave A string a ser analisada.
     * @return O valor do bit.
     */
    private int bit(int index, String chave) {

        int[] bits = new int[129];

        for (int i = 0; i < 16; i++) {

            // converte o caractere para binário
            String binaryChar = Integer.toBinaryString((int) chave.charAt(i));

            while (binaryChar.length() < 8)
                binaryChar = "0" + binaryChar;

            // cada caractere é representado por 8 bits => a posição do bit é um multiplo de 8 + 1 até o próxmo multiplo de 8
            int startBit = i * 8 + 1, endBit = startBit + 7;
            for (int j = startBit, k = 0; j <= endBit; j++, k++)
                bits[j] = (binaryChar.charAt(k) - 48); // -48 para conversão adequada de ASCII para binário
        }

        // retorna o bit na posição index
        return bits[index];
    }

    /**
     * Verifica se um nó é externo
     */
    private boolean eExterno(PatNo patNo) {
        Class classe = patNo.getClass();
        return classe.getName().equals(PatNoExt.class.getName());
    }
}