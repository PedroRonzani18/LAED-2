public class Main {

    public static void test(int e1, int e2, int a1[], int a2[], int t1[], int t2[], int x1, int x2, int n, int caso) {

        LinhasDeMontagem linhasDeMontagem = new LinhasDeMontagem(e1, e2, a1, a2, t1, t2, x1, x2, n);

        System.out.println("Algoritmo Guloso caso " + caso);
        linhasDeMontagem.solucaoAlgoritmoGuloso();
        System.out.println(linhasDeMontagem + "\n");

        linhasDeMontagem.reset();

        System.out.println("Programação Dinamica caso " + caso);
        linhasDeMontagem.solucaoProgramacaoDinamica();
        System.out.println(linhasDeMontagem + "\n");
    }

    public static void main(String args[]) {

        /* ---------------------------------------EXEMPLO 1--------------------------------------- */

        test(2, 4,
                new int[] { 0, 3, 5, 7, 10, 5, 9, 11, 9, 5, 2, 6 },
                new int[] { 0, 2, 6, 3, 9, 11, 4, 9, 3, 12, 4, 5 },
                new int[] { 0, 3, 5, 4, 2, 7, 5, 8, 1, Integer.MAX_VALUE, Integer.MAX_VALUE },
                new int[] { 0, 5, 3, 7, 5, 6, 2, 5, 2, Integer.MAX_VALUE, Integer.MAX_VALUE },
                3, 2, 11, 1);

        /* ---------------------------------------EXEMPLO 2--------------------------------------- */

        test(2, 4, 
                new int[] { 0, 5, 10, 6, 3, 8, 5, 3, 7, 12, 8 }, 
                new int[] { 0, 7, 3, 5, 3, 7, 6, 4, 9, 10, 9 },
                new int[] { 0, 4, 2, 7, 2, 5, 8, 2, Integer.MAX_VALUE, Integer.MAX_VALUE },
                new int[] { 0, 6, 1, 7, 3, 6, 4, 5, Integer.MAX_VALUE, Integer.MAX_VALUE }, 
                3, 2, 10, 2);
    }
}
