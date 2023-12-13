package src;

import java.util.ArrayList;
import java.util.Random;

/**
 * A classe TravellingSalesman representa uma solução para o Problema do Caixeiro Viajante.
 * Ela fornece métodos para encontrar a solução ótima usando algoritmos de força bruta ou heurística.
 */
public class TravellingSalesman {
        
    private XGrafo xGrafo; // grafo de cidades e distâncias.
    private ArrayList<ArrayList<Integer>> paths; // lista de caminhos partindo do índice 'i' ('i' é a cidade de origem)
    private ArrayList<ArrayList<Integer>> solutions; // Temporário: Pega todas as possibilidades de caminhos e depois selecionamos o melhor deles
    private int costs[]; // custos de cada caminho partindo de cada cidade.
    private int bestSourceCity; // índice da melhor cidade de origem.
    private boolean bruteForce; // flag indicando se o algoritmo de força bruta está sendo usado.
    private boolean heuristic; // flag indicando se o algoritmo heurístico está sendo usado.

    /**
     * Constrói um objeto TravellingSalesman com o XGrafo fornecido.
     * 
     * @param xGrafo O objeto XGrafo que representa o grafo de cidades e distâncias.
     */
    public TravellingSalesman(XGrafo xGrafo) {
        this.xGrafo = xGrafo;
        solutions = new ArrayList<ArrayList<Integer>>();
        this.paths = new ArrayList<ArrayList<Integer>>();
        this.costs = new int[this.xGrafo.getNumVertices()];

        this.bruteForce = false;
        this.heuristic = false;
    }

    /**
     * Encontra a solução ótima usando o algoritmo de força bruta.
     * Este método calcula o caminho de custo mínimo partindo de cada cidade.
     */
    public void bruteForceSolution() {
        int numberOfCities = this.xGrafo.getNumVertices();

        // Para cada cidade, encontra o caminho de custo mínimo
        for (int i = 0; i < numberOfCities; i++) {

            ArrayList<Integer> al = new ArrayList<Integer>(); // Caminho atual sendo construído

            this.gerarCombinacoesCaminhos(i, al); // Gera todas as permutações possíveis de cidades

            Pair<Integer, Integer> p = minimumCostPath(this.solutions, i); // Calcula o caminho de custo mínimo

            this.paths.add(i, this.solutions.get(p.getFirst())); // Adiciona o caminho de custo mínimo à lista de caminhos
            this.costs[i] = p.getSecond(); // Adiciona o custo mínimo à lista de custos

            this.solutions = new ArrayList<ArrayList<Integer>>(); // Limpa a lista de soluções temporárias
        }

        bestSourceCity = obterMelhorCaminho();  // Encontra o índice da melhor cidade de origem

        int lowestCost = this.costs[bestSourceCity]; // Encontra o custo mínimo
        ArrayList<Integer> bestPath = this.paths.get(bestSourceCity); // Encontra o caminho de custo mínimo

        this.costs = new int[1]; // Limpa a lista de custos
        this.costs[0] = lowestCost; // Adiciona o custo mínimo à lista de custos
        this.paths = new ArrayList<ArrayList<Integer>>(); // Limpa a lista de caminhos
        this.paths.add(bestPath); // Adiciona o caminho de custo mínimo à lista de caminhos

        this.bruteForce = true;
        this.heuristic = false;
    }

    /**
     * Encontra a solução ótima usando um algoritmo heurístico.
     * Este método gera uma cidade de origem aleatória e constrói um caminho selecionando a cidade não visitada mais próxima em cada etapa.
     */
    public void heuristicSolution() {

        Random random = new Random();
        
        int sourceCity = random.nextInt(this.xGrafo.getNumVertices()); // Gera uma cidade de origem aleatória
        
        ArrayList<Integer> heuristicPath = new ArrayList<Integer>();
        heuristicPath.add(sourceCity); // Adiciona a cidade de origem ao caminho

        for (int i = 0; i < this.xGrafo.getNumVertices() - 1; i++) {
            int v = this.getTheLastAddedIndexNearestVertex(sourceCity, heuristicPath); // Encontra a cidade não visitada mais próxima da última cidade adicionada ao caminho
            heuristicPath.add(v); // Adiciona a cidade ao caminho
        }

        heuristicPath.add(sourceCity); // Adiciona a cidade de origem ao caminho

        this.costs[0] = this.heuristicMinimumCost(heuristicPath); // Calcula o custo mínimo do caminho

        this.paths.add(heuristicPath); // Adiciona o caminho à lista de caminhos

        this.heuristic = true;
        this.bruteForce = false;
    }

    /**
     * Este método implementa a complementação da solução de força bruta para o Problema do Caixeiro Viajante.
     * Ele gera recursivamente todas as permutações possíveis de cidades para encontrar o caminho mais curto.
     * 
     * @param sourceCity A cidade de origem para o caminho.
     * @param al O caminho atual sendo construído.
     * @return O caminho mais curto encontrado, representado como uma lista de cidades, ou null se nenhum caminho for encontrado.
     */
    private ArrayList<Integer> gerarCombinacoesCaminhos(int cidadeOrigem, ArrayList<Integer> caminhoAtual) {

        int numeroCidades = this.xGrafo.getNumVertices(); // Número de cidades no grafo
        int ultimoIndiceCidadeAdicionada = -1; // Índice da última cidade adicionada ao caminho

        ArrayList<ArrayList<Integer>> todasPossibilidades = new ArrayList<ArrayList<Integer>>(); // Inicializa ArrayList para armazenar todas as possibilidades de caminho

        ArrayList<Integer> novoCaminho = null; // Inicializa ArrayList para armazenar o caminho atual

        if (caminhoAtual.size() > 0) // Se o caminho atual não estiver vazio, o último índice é o último elemento do caminho
            ultimoIndiceCidadeAdicionada = caminhoAtual.get(caminhoAtual.size() - 1);

        for (int i = 0; i < numeroCidades; i++) { // Para cada cidade

            novoCaminho = new ArrayList<Integer>(); // Inicializa ArrayList para armazenar o caminho atual
            novoCaminho.addAll(caminhoAtual); // Adiciona o caminho atual ao novo caminho

            // Se não for a cidade de origem, a coleção não contém a cidade e há uma conexão entre o ultimoIndiceCidadeAdicionada e i
            if (
                (i != cidadeOrigem) && // Se a cidade atual não seja a cidade de origem,
                (!novoCaminho.contains(i)) && // Se cidade atual não tenha sido visitada anteriormente no caminho atual
                (
                    (novoCaminho.size() == 0) || // Nesse caso, não há restrições sobre qual cidade pode ser escolhida como próxima cidade, pois ainda não há nenhuma cidade no caminho
                    (this.xGrafo.getMat()[ultimoIndiceCidadeAdicionada][i] != 0) // garantir que existe uma conexão entre a última cidade adicionada ao caminho e a cidade atual
                )
               ) {
                novoCaminho.add(i); // Adiciona a cidade atual ao caminho atual
                ArrayList<Integer> retornado = gerarCombinacoesCaminhos(cidadeOrigem, novoCaminho); // Chama o método recursivamente para encontrar o caminho mais curto
                todasPossibilidades.add(retornado); // Adiciona o caminho retornado à lista de caminhos
            }
        }

        int indiceCaminhoSelecionado = -1, i = 0;

        // Encontra o índice do caminho mais curto
        for (ArrayList<Integer> caminho : todasPossibilidades) {
            i++;
            if (caminho == null)
                continue;
            indiceCaminhoSelecionado = i - 1; // O índice do caminho é o índice do ArrayList menos 1
        }

        if (indiceCaminhoSelecionado > -1) // Se o índice do caminho for válido
            this.solutions.add(todasPossibilidades.get(indiceCaminhoSelecionado)); // Adiciona o caminho à lista de caminhos

        int tamanho = novoCaminho.size();
        if (tamanho > 0) {
            
            ultimoIndiceCidadeAdicionada = novoCaminho.get(tamanho - 1); // O índice da última cidade adicionada ao caminho é o último elemento do caminho
            
            if (
                (tamanho == numeroCidades - 1) && // Se o tamanho do caminho for igual ao número de cidades menos 1 (a cidade de origem) 
                (this.xGrafo.getMat()[ultimoIndiceCidadeAdicionada][cidadeOrigem] != 0) // Se houver uma conexão entre a última cidade adicionada ao caminho e a cidade de origem
               ) {
                novoCaminho.add(cidadeOrigem);
                return novoCaminho;
            }
        }

        return null;
    }

    /**
     * Imprime a solução no console.
     * Este método imprime o custo mínimo e o caminho correspondente para o algoritmo selecionado.
     */
    public void printSolution() {

        if (this.bruteForce) {
            System.out.print("Menor custo Brute Force: ");
            System.out.println(this.costs[0] + "\n");

            System.out.print("Menor caminho Brute Force: ");
            System.out.print(bestSourceCity + " - ");

            int numberOfCities = this.xGrafo.getNumVertices(), i = 0;
            for (Integer city : this.paths.get(0)) {
                i++;
                System.out.print(city);
                if (i != numberOfCities)
                    System.out.print(" - ");
            }
        }

        if (this.heuristic) {
            System.out.print("Menor custo Heurístico: ");
            System.out.println(this.costs[0] + "\n");

            System.out.println("Menor caminho Heurístico: ");
            System.out.print(this.paths.get(0).get(0) + " - ");

            int numberOfCities = this.xGrafo.getNumVertices();

            for (int i = 1; i < numberOfCities + 1; i++) {
                System.out.print(this.paths.get(0).get(i));
                if (i != numberOfCities)
                    System.out.print(" - ");
            }
            System.out.println('\n');
        }
    }

    /**
     * Calcula o caminho de custo mínimo a partir de uma lista de caminhos partindo de uma cidade específica.
     * 
     * @param al A lista de caminhos partindo de uma cidade específica.
     * @param sourceCity O índice da cidade de origem.
     * @return Um objeto Pair contendo o índice do caminho de custo mínimo e o custo mínimo.
     */
    private Pair<Integer, Integer> minimumCostPath(ArrayList<ArrayList<Integer>> al, int sourceCity) {
        int numberOfCities = this.xGrafo.getNumVertices();
        int minCost = Integer.MAX_VALUE;
        int temp = 0;
        int i = -1, returnValue = -1;

        for (ArrayList<Integer> values : al) { // Para cada ArrayList
            i++;
            if ((values == null) || (values.size() < numberOfCities - 1)) {
                continue;
            }
            temp = 0;
            int previousCity = sourceCity;
            for (Integer value : values) {
                temp += this.xGrafo.getMat()[previousCity][value];
                previousCity = value;
            }
            if (temp < minCost) {
                minCost = temp;
                returnValue = i;
            }
        }
        Pair<Integer, Integer> pair = new Pair<Integer, Integer>(returnValue, minCost);
        return pair;
    }

    /**
     * Calcula o custo mínimo de um determinado caminho usando um algoritmo heurístico.
     * 
     * @param arrayList O caminho para calcular o custo.
     * @return O custo mínimo do caminho fornecido.
     */
    private int heuristicMinimumCost(ArrayList<Integer> arrayList) {
        int cost = 0;

        for (int i = 1; i < this.xGrafo.getNumVertices() + 1; i++) {
            cost += this.xGrafo.getMat()[arrayList.get(i - 1)][arrayList.get(i)];
        }
        return cost;
    }

    /**
     * Encontra o índice da melhor cidade de origem com o custo mínimo.
     * 
     * @return O índice da melhor cidade de origem.
     */
    public int obterMelhorCaminho() {
        int choice = -1;
        int min = Integer.MAX_VALUE;
        int numberOfCities = this.xGrafo.getNumVertices();

        for (int i = 0; i < numberOfCities; i++) {
            if (min > this.costs[i]) {
                min = this.costs[i];
                choice = i;
            }
        }
        return choice;
    }

    /**
     * Encontra o índice da cidade não visitada mais próxima da última cidade adicionada no caminho.
     * 
     * @param sourceCity O índice da cidade de origem.
     * @param heuristicPath O caminho atual.
     * @return O índice da cidade não visitada mais próxima.
     */
    private int getTheLastAddedIndexNearestVertex(int sourceCity, ArrayList<Integer> heuristicPath) {
        int numberOfAddedCities = heuristicPath.size();
        int lastAddedCity = heuristicPath.get(numberOfAddedCities - 1);

        int shortestPath = Integer.MAX_VALUE;
        int shortestPathIndex = Integer.MAX_VALUE;

        for (int j = 0; j < this.xGrafo.getNumVertices(); j++) {
            // A distância deve ser a menor, mas a cidade destino não pode ser a cidade de origem e não pode já estar no caminho
            if ((this.xGrafo.getMat()[lastAddedCity][j] < shortestPath) && (j != sourceCity) // Se a distância for a menor e a cidade destino não for a cidade de origem
                    && (!heuristicPath.contains(j)) && (j != lastAddedCity)) {// Se a cidade destino não estiver no caminho e não for a última cidade adicionada
                shortestPath = this.xGrafo.getMat()[lastAddedCity][j]; // A distância mais curta é a distância entre a última cidade adicionada e a cidade atual
                shortestPathIndex = j;
            }
        }
        return shortestPathIndex;
    }
}
