/*
 * Implemente o QuickSort em uma função para ordernar uma lista de números
 * recebida como parâmetro. Após a ordenação, a função deve retornar a lista
 * ordenada.
*/

import java.util.Scanner;

public class A5 {

    public static int separar(int[] list, int ini, int fim) {
        int pivo = list[ini];
        int i = ini + 1, f = fim;
        
        while (i <= f) {
            if      (list[i] <= pivo) i++;
            else if (list[f] > pivo)  f--;
            else {
                int troca = list[i];
                list[i] = list[f];
                list[f] = troca;
                i++;
                f--;
            }
        }

        list[ini] = list[f];
        list[f] = pivo;
        return f;
    }

    public static void sort(int[] list, int ini, int fim) {
        if (ini < fim) {
            int pivot = separar(list, ini, fim);
            sort(list, ini, pivot - 1);
            sort(list, pivot + 1, fim);
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Quantidade de elementos: ");

        int size = scanner.nextInt();
        int[] list = new int[size];

        System.out.print("Lista original: ");

        for(int i=0; i<size; i++) 
            list[i] = scanner.nextInt();

        sort(list,0,size-1);

        System.out.print("Lista ordenada: ");
        for (int i : list) System.out.print(i + " "); System.out.println();
    }
}
