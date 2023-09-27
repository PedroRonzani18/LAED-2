public class Main {
    public static void main(String[] args) {

        ArvoreSBB arvoreSBB;
        // ArvoreB arvoreB;

        for(int i=10000; i<= 100000; i+= 10000) {
            arvoreSBB = new ArvoreSBB();
            // arvoreB = new ArvoreB(6);

            for(int j=0; j < i; j++){
                arvoreSBB.insere(new Item(j));
                // arvoreB.insere(new Item(j));
            }

            // arvoreB.pesquisa(new Item(i + 1));
            arvoreSBB.pesquisa(new Item(i + 1));

            // System.out.println("Comparações: " + (arvoreB.getQuantidadeComparacoes()));
            System.out.println("Comparações " + i + " : " + (arvoreSBB.getQuantidadeComparacoes()));
        }
    }
}