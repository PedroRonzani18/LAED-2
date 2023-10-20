import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        System.out.println("Crescente");

        for(int j=10000; j<= 100000; j += 10000) {
            Item v[] = new Item[j];

            for (int i = 0; i < j; i++) {
                v[i] = new Item(i);
            }
                   
            Heap.heapsort(v, j-1);
            
            System.out.print(Heap.comparacoes + " ");
        }
        
        System.out.println();
        System.out.println("Decrescente");

        for(int j=10000; j<= 100000; j += 10000) {
            Item v[] = new Item[j];

            for (int i = 0; i < j; i++) {
                v[i] = new Item(j-i);
            }
                   
            Heap.heapsort(v, j-1);
            
            System.out.print(Heap.comparacoes + " ");
        }
        System.out.println();
        System.out.println("Aleatório");
        
        for(int j=10000; j<= 100000; j += 10000) {
            Item v[] = new Item[j];
            
            ArrayList<Integer> arrayList = new ArrayList<>();
            
            for(int x=0; x<j; x++)
                arrayList.add(x);
            
            Random random = new Random();
            
            for(int x=0; !arrayList.isEmpty(); x++) {
                int randomIndex = random.nextInt(arrayList.size());
                int number = arrayList.get(randomIndex);

                v[x] = new Item(number);
                arrayList.remove(randomIndex);
            }
                   
            Heap.heapsort(v, j-1);
            
            System.out.print(Heap.comparacoes + " ");
        }
        System.out.println();
    }
    
}
