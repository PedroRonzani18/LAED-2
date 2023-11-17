public class Main {

    public static void main(String[] args) {

        /* ----------------------- GRAFO 1 ----------------------- */

        Grafo g1 = new Grafo(6);

        g1.insereAresta(1, 2, 8, 3);
        g1.insereAresta(1, 3, 4, 10);
        g1.insereAresta(1, 4, 5, 2);

        g1.insereAresta(2, 5, 9, 5);

        g1.insereAresta(3, 4, 1, 7);
        g1.insereAresta(3, 5, 5, 2);

        g1.insereAresta(4, 3, 5, 7);
        g1.insereAresta(4, 5, 2, 7);

        XAEDsMaps xaeDsMaps1 = new XAEDsMaps(g1, 1, 5);

        try {

            System.out.println("\nDistância 1: ");

            xaeDsMaps1.obterArvoreCMCDistancia();

            System.out.println("\nTempo 1: ");

            xaeDsMaps1.obterArvoreCMCTempo();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /* ----------------------- GRAFO 2 ----------------------- */

        Grafo g2 = new Grafo(6);

        g2.insereAresta(1, 2, 3, 3);
        g2.insereAresta(1, 4, 5, 5);

        g2.insereAresta(2, 3, 2, 6);

        g2.insereAresta(3, 5, 2, 2);

        g2.insereAresta(4, 2, 3, 1);
        g2.insereAresta(4, 3, 5, 4);
        g2.insereAresta(4, 5, 9, 6);

        g2.insereAresta(5, 1, 6, 3);
        g2.insereAresta(5, 3, 4, 7);

        XAEDsMaps xaeDsMaps2 = new XAEDsMaps(g2, 1, 5);

        try {

            System.out.println("\nDistância 2: ");

            xaeDsMaps2.obterArvoreCMCDistancia();

            System.out.println("\nTempo 2: ");

            xaeDsMaps2.obterArvoreCMCTempo();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println();

    }

}
