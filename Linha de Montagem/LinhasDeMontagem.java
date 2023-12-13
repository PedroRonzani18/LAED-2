/**
 * A classe LinhasDeMontagem representa um sistema de linhas de montagem.
 * Ela fornece métodos para calcular o tempo mínimo necessário para montar um produto e o caminho de montagem correspondente.
 */
public class LinhasDeMontagem {
    private int e1, e2;
    private int a1[], a2[];
    private int numeroEstacoes;
    private int t1[], t2[];
    private int x1, x2;

    private int line[][];
    private int tempoResposta[][];

    private int tempoRespostaFinal;
    private int linhaRespostaFinal;

    private boolean ehProgramacaoDinam;

    /**
     * Constrói um objeto LinhasDeMontagem com os parâmetros fornecidos.
     *
     * @param e1              o tempo de entrada para a primeira linha de montagem
     * @param e2              o tempo de entrada para a segunda linha de montagem
     * @param a1              o tempo de montagem para cada estação na primeira linha de montagem
     * @param a2              o tempo de montagem para cada estação na segunda linha de montagem
     * @param t1              o tempo de transferência entre as estações na primeira linha de montagem
     * @param t2              o tempo de transferência entre as estações na segunda linha de montagem
     * @param x1              o tempo de saída para a primeira linha de montagem
     * @param x2              o tempo de saída para a segunda linha de montagem
     * @param numeroEstacoes  o número de estações em cada linha de montagem
     */
    LinhasDeMontagem(int e1, int e2, int a1[], int a2[], int t1[], int t2[], int x1, int x2, int numeroEstacoes) {
        this.e1 = e1;
        this.e2 = e2;
        this.x1 = x1;
        this.x2 = x2;
        this.numeroEstacoes = numeroEstacoes;

        this.a1 = new int[this.numeroEstacoes + 1]; // sem índice nulo
        this.a2 = new int[this.numeroEstacoes + 1]; // sem índice nulo

        this.t1 = new int[this.numeroEstacoes]; // sem índice nulo
        this.t2 = new int[this.numeroEstacoes]; // sem índice nulo

        for(int i = 1; i < this.numeroEstacoes; i++) {
            this.a1[i] = a1[i];
            this.a2[i] = a2[i];

            this.t1[i] = t1[i];
            this.t2[i] = t2[i];
        }

        this.a1[this.numeroEstacoes] = a1[this.numeroEstacoes];
        this.a2[this.numeroEstacoes] = a2[this.numeroEstacoes];

        this.line = new int[2][this.numeroEstacoes + 1];

        this.tempoResposta = new int[2][this.numeroEstacoes +1];
    }

    /**
     * Calcula o tempo mínimo necessário para montar um produto e o caminho de montagem correspondente
     * usando a abordagem de programação dinâmica.
     */
    public void solucaoProgramacaoDinamica() {

        // Calcula o tempo de resposta para a primeira estação das linhas de montagem
        this.tempoResposta[0][1] = this.e1 + this.a1[1]; // Tempo de entrada + Tempo de montagem
        this.tempoResposta[1][1] = this.e2 + this.a2[1];

        for(int i = 2; i < this.numeroEstacoes + 1; i++) {

            int firstPath = this.tempoResposta[0][i-1] + this.a1[i]; // Tempo de resposta da estação anterior + tempo de montagem
            int secondPath = this.tempoResposta[1][i-1] + this.a1[i] + this.t2[i-1]; // Tempo de resposta da estação anterior + tempo de montagem + tempo de transferência

            if(this.t2[i-1] == Integer.MAX_VALUE) // Se o tempo de transferência for infinito (não há uma estação de transferência entre as estações i-1 e i)
                secondPath = firstPath + 1; // Tempo de resposta da estação anterior + tempo de montagem + 1

            if(firstPath <= secondPath) { // Se o tempo de resposta da primeira linha for menor que o tempo de resposta da segunda linha
                
                this.tempoResposta[0][i] = firstPath; // Tempo de resposta da primeira linha
                this.line[0][i] = 1; // Define a linha de montagem escolhida para a estação atual como a primeira linha

            } else { // Se o tempo de resposta da primeira linha for maior que o tempo de resposta da segunda linha
                
                this.tempoResposta[0][i] = secondPath; // Tempo de resposta da segunda linha
                this.line[0][i] = 2; // Define a linha de montagem escolhida para a estação atual como a segunda linha
            }

            firstPath = this.tempoResposta[1][i-1] + this.a2[i]; // Tempo de resposta da estação anterior + tempo de montagem
            secondPath = this.tempoResposta[0][i-1] + this.t1[i-1] + this.a2[i]; // Tempo de resposta da estação anterior + tempo de transferência + tempo de montagem

            if(this.t1[i-1] == Integer.MAX_VALUE) // Se o tempo de transferência for infinito (não há uma estação de transferência entre as estações i-1 e i)
                secondPath = firstPath + 1; // Tempo de resposta da estação anterior + tempo de montagem + 1

            if(firstPath <= secondPath) { // Se o tempo de resposta da primeira linha for menor que o tempo de resposta da segunda linha
                this.tempoResposta[1][i] = firstPath; // Tempo de resposta da primeira linha
                this.line[1][i] = 2; // Define a linha de montagem escolhida para a estação atual como a segunda linha
            
            } else { // Se o tempo de resposta da primeira linha for maior que o tempo de resposta da segunda linha
                this.tempoResposta[1][i] = secondPath; // Tempo de resposta da segunda linha
                this.line[1][i] = 1; // Define a linha de montagem escolhida para a estação atual como a primeira linha
            }
        }

        int resposta1 = this.tempoResposta[0][this.numeroEstacoes] + this.x1; // Tempo de resposta da primeira linha + tempo de saída da primeira linha
        int resposta2 = this.tempoResposta[1][this.numeroEstacoes] + this.x2; // Tempo de resposta da segunda linha + tempo de saída da segunda linha

        if(resposta1 <= resposta2) { // Se o tempo de resposta da primeira linha for menor que o tempo de resposta da segunda linha
            this.tempoRespostaFinal = resposta1;
            this.linhaRespostaFinal = 1;
        }
        else {
            this.tempoRespostaFinal = resposta2;
            this.linhaRespostaFinal = 2;
        }

        this.ehProgramacaoDinam = true;
    }

    /**
     * Calcula o tempo mínimo necessário para montar um produto e o caminho de montagem correspondente
     * usando o algoritmo guloso.
     */
    public void solucaoAlgoritmoGuloso() {

        // Calcula o tempo de resposta para a primeira estação das linhas de montagem
        this.tempoResposta[0][1] = this.e1 + this.a1[1]; // Tempo de entrada + Tempo de montagem
        this.tempoResposta[1][1] = this.e2 + this.a2[1]; // Tempo de entrada + Tempo de montagem

        int menorCaminho = (tempoResposta[0][1] <= tempoResposta[1][1]) ? 0 : 1; // 0 para a primeira linha, 1 para a segunda linha

        this.line[menorCaminho][1] = menorCaminho + 1; // 1 para a primeira linha, 2 para a segunda linha

        for(int i = 2; i < this.numeroEstacoes + 1; i++) {

            if(menorCaminho == 0) { // Se o menor caminho for a primeira linha

                if (
                    this.a1[i] <= this.a2[i] + this.t1[i-1] || // Se o tempo de montagem da primeira linha for menor que o tempo de montagem da segunda linha + o tempo de transferência da segunda linha
                    this.t1[i-1] == Integer.MAX_VALUE // Se o tempo de transferência da primeira linha for infinito (não há uma estação de transferência entre as estações i-1 e i) 
                   ) {

                    this.tempoResposta[0][i] = this.a1[i] + this.tempoResposta[0][i-1]; // Tempo de montagem + tempo de resposta da estação anterior
                    this.line[0][i] = 1; // Define a linha de montagem escolhida para a estação atual como a primeira linha
                }
                else { // Se o tempo de montagem da primeira linha for maior que o tempo de montagem da segunda linha + o tempo de transferência da segunda linha

                    this.tempoResposta[1][i] = this.a2[i] + this.tempoResposta[0][i-1] + this.t1[i-1]; // Tempo de montagem + tempo de resposta da estação anterior + tempo de transferência
                    this.line[1][i] = 2; // Define a linha de montagem escolhida para a estação atual como a segunda linha
                    menorCaminho = 1; // Define o menor caminho como a segunda linha
                }
            }

            else {
                if (
                    this.a2[i] <= this.a1[i] + this.t2[i-1] || // Se o tempo de montagem da segunda linha for menor que o tempo de montagem da primeira linha + o tempo de transferência da primeira linha
                    this.t2[i-1] == Integer.MAX_VALUE // Se o tempo de transferência da segunda linha for infinito (não há uma estação de transferência entre as estações i-1 e i)
                   ) {

                    this.tempoResposta[1][i] = this.a2[i] + this.tempoResposta[1][i-1]; // Tempo de montagem + tempo de resposta da estação anterior
                    this.line[1][i] = 2; // Define a linha de montagem escolhida para a estação atual como a segunda linha
                }
                else {
                    this.tempoResposta[0][i] = this.a1[i] + this.t2[i-1] + this.tempoResposta[menorCaminho][i-1]; // Tempo de montagem + tempo de resposta da estação anterior + tempo de transferência
                    this.line[0][i] = 1; // Define a linha de montagem escolhida para a estação atual como a primeira linha
                    menorCaminho = 0; // Define o menor caminho como a primeira linha
                }
            }
        }

        if(this.tempoResposta[0][this.numeroEstacoes] == 0) { // Se o tempo de resposta da primeira linha for zero (não há uma estação de transferência entre as estações n e n+1)
            this.tempoRespostaFinal = this.tempoResposta[1][this.numeroEstacoes] + this.x2; // Tempo de resposta da segunda linha + tempo de saída da segunda linha
            this.linhaRespostaFinal = 2; // Define a linha de montagem escolhida para a estação atual como a segunda linha
        }
        else if(this.tempoResposta[1][this.numeroEstacoes] == 0) { // Se o tempo de resposta da segunda linha for zero (não há uma estação de transferência entre as estações n e n+1)
            this.tempoRespostaFinal = this.tempoResposta[0][this.numeroEstacoes] + this.x1; // Tempo de resposta da primeira linha + tempo de saída da primeira linha
            this.linhaRespostaFinal = 1; // Define a linha de montagem escolhida para a estação atual como a primeira linha
        }

        this.ehProgramacaoDinam = false;
    }

    /**
     * Reseta o sistema de linhas de montagem para o estado inicial.
     */
    public void reset() {
        for(int i = 0; i < this.numeroEstacoes + 1; i++) {
            this.tempoResposta[0][i] = 0;
            this.tempoResposta[1][i] = 0;

            this.line[0][i] = 0;
            this.line[1][i] = 0;
        }

        this.tempoRespostaFinal = 0;
        this.linhaRespostaFinal = 0;
    }

    /**
     * Retorna o tempo mínimo necessário para montar um produto.
     *
     * @return o tempo mínimo necessário para montar um produto
     */
    public int getTempoRespostaFinal() {
        return this.tempoRespostaFinal;
    }

    /**
     * Retorna a linha de montagem pela qual o produto sai.
     *
     * @return a linha de montagem pela qual o produto sai (1 para a primeira linha, 2 para a segunda linha)
     */

    public int getLinhaRespostaFinal() { 
        return this.linhaRespostaFinal;
    }

    /**
     * Retorna uma representação em string do caminho de montagem e do tempo mínimo necessário para montar um produto.
     *
     * @return uma representação em string do caminho de montagem e do tempo mínimo necessário para montar um produto
     */
    @Override
    public String toString() {
        int temp[] = new int[this.numeroEstacoes + 1];
        int temporario = linhaRespostaFinal;

        String s = "Tempo mínimo: " + Integer.toString(this.getTempoRespostaFinal()) + "\n\n";
        s += "Caminho: \n\n";

        if(this.ehProgramacaoDinam){
            for(int i = 1; i < this.numeroEstacoes + 1; i++) {
                temp[this.numeroEstacoes -i + 1] = temporario;
                    temporario = this.line[temporario-1][this.numeroEstacoes -i + 1];
            }

            for(int i = 1; i < this.numeroEstacoes + 1; i++) {
                s += Integer.toString(temp[i]) + " -> ";
            }
        }
        else {
            for(int i = 1; i < this.numeroEstacoes + 1; i++) {
                s += ((this.line[0][i] == 1) ? "1" : "2") + " -> "; 
            }
        }

        s += Integer.toString(linhaRespostaFinal) + "\n";

        return s;
    }
}
