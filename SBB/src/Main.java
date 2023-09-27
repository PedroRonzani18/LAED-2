import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        long tempo = 0;
        long comp = 0;

        ArvoreSBB arvoreSBB;
        ArrayList<Integer> arrayList;

        for(int i=10000; i<= 100000; i+= 10000) {

            for(int k=0; k<400; k++) {
                arvoreSBB = new ArvoreSBB();

                Random random = new Random();
                arrayList = new ArrayList<>();

                for(int j=0; j < i; j++){
                    arvoreSBB.insere(new Item(j));
                }
/*
                while (!arrayList.isEmpty()) {
                    int randomIndex = random.nextInt(arrayList.size());
                    int number = arrayList.get(randomIndex);

                    arrayList.remove(randomIndex);
                }*/



                long start1 = System.nanoTime();
                arvoreSBB.pesquisa(new Item(i + 1));
                long end1 = System.nanoTime();
                long totalTime = end1 - start1;
                tempo += totalTime;
                comp += arvoreSBB.getQuantidadeComparacoes();
            }

            System.out.println("Elementos: " + i);
            System.out.println("Tempo: " + (tempo/400));
            System.out.println("Comparações: " + (comp/400));
            System.out.println();
        }
    }
}