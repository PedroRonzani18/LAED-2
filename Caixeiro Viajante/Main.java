import java.util.Random;

import src.TextFileHandler;
import src.TravellingSalesman;
import src.XGrafo;

public class Main {

    public static void parte1() {

        System.out.println("===============================================================");
        for (int i = 2; i < 12; i++) {
            XGrafo grafo = gerarEntradas(i);
            System.out.println(+i + " cidades:\n");
            System.out.println(grafo);

            TravellingSalesman travellingSalesman = new TravellingSalesman(grafo);

            long start1 = System.nanoTime();
            travellingSalesman.bruteForceSolution();
            long end1 = System.nanoTime();
            long totalTime = end1 - start1;

            travellingSalesman.printSolution();
            System.out.println("\n\nTempo de execução: " + totalTime + " nanosegundos");

            System.out.println();
            System.out.println("===============================================================");
        }
    }

    public static void parte2() {

        String paths[] = { "cases/pa561.tsp", "cases/si535.tsp", "cases/si1032.tsp" };

        System.out.println("===============================================================");

        for (String path : paths) {
            TextFileHandler.openFile(path);
            TextFileHandler.readRecords();

            XGrafo grafo = TextFileHandler.getGrafo();

            TravellingSalesman tsm = new TravellingSalesman(grafo);
            tsm.heuristicSolution();
            tsm.printSolution();
            System.out.println("===============================================================");
        }
    }

    public static XGrafo gerarEntradas(int numberOfCities) {

        XGrafo xGrafo = new XGrafo(numberOfCities, false);
        Random random = new Random();

        for (int i = 0; i < numberOfCities; i++) {
            for (int j = i + 1; j < numberOfCities; j++) {
                xGrafo.inserirAresta(i, j, random.nextInt(1000));
            }
        }
        return xGrafo;
    }

    public static void main(String args[]) {

        parte1();
        parte2();
    }
}
