import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        ArvoreBinaria arvoreBinaria;
        ArrayList<Integer> arrayList;

        for(int i=1000; i<=9000; i+= 1000) {
            arvoreBinaria = new ArvoreBinaria();
            Random random = new Random();
            arrayList = new ArrayList<>();

            for(int j=0; j < i; j++){
                arrayList.add(j);
            }

            while (!arrayList.isEmpty()) {
                int randomIndex = random.nextInt(arrayList.size());
                int number = arrayList.get(randomIndex);

                arrayList.remove(randomIndex);

                arvoreBinaria.insere(new Item(number));
            }

            long start1 = System.nanoTime();
                arvoreBinaria.pesquisa(new Item(i + 1));
            long end1 = System.nanoTime();
            long totalTime = end1 - start1;

            System.out.println("Elementos: " + i);
            System.out.println("Tempo: " + totalTime);
            System.out.println("Comparações: " + arvoreBinaria.getQuantidadeComparacoes());
            System.out.println();
        }
    }
}