/*
 * Implemente o quicksort em uma função para ordernar uma lista de números
 * recebida como parâmetro. Após a ordenação, a função deve retornar a lista
 * ordenada.
*/

public class A5 {
    
    class Wrapper {
        
        public int value;
        
        public Wrapper(int value) {
            this.value = value;
        }
    }

    public static void particao(int esq, int dir, Wrapper i, Wrapper j, int[] list) {
        int x, w;
        i.value = esq; j.value = dir;
        x = list[(i.value + j.value)/2];

        do {
            while(x > list[i.value]) i.value++;
            while(x < list[j.value]) j.value--;
            if(i.value <= j.value) {
                w = list[i.value];
                list[i.value] = list[j.value];
                list[j.value] = w;
                i.value++; 
                j.value--;
            }
        } while(i.value <= j.value);
    }

    public static void ordena(int esq, int dir, int[] list) {
        Wrapper i = new Wrapper(2); i.value = esq;   
        Wrapper j = new Wrapper(2); j.value = dir;
        
        particao(esq, dir, i, j, list);
        if(esq < j.value) ordena(esq, j.value, list);
        if(i.value < dir) ordena(i.value, dir, list);
    }
    public static void main(String[] args) {
        int[] list = {5,4,3,2,1};
        ordena(0, 4, list);
        for (int x : list) {
            System.out.print(x);
        }
    }
}
