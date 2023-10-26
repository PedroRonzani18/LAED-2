/*
 * Implemente um outro algoritmo de ordenação (que não seja o quicksort), para
 * ordenar uma lista de números recebida como parâmetro. 
 */

import java.util.Scanner;

public class A6 {
  
    public static void sort(int[] list, int size) {
        for(int i=0; i<size; i++) {
            for(int j=i; j<size; j++) {
                if(list[i] > list[j]) {
                    int aux = list[i];
                    list[i] = list[j];
                    list[j] = aux;
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    
        System.out.print("Quantidade de elementos: ");
        
        int size = scanner.nextInt();
        int [] list = new int[size];

        System.out.print(size + " numeros: ");
        for(int i=0; i<size; i++) {
            list[i] = scanner.nextInt();
        }

        sort(list, size);

        System.out.print("Lista Ordenada: ");
        for (int x : list) {
            System.out.print(x + " ");
        }
        System.out.println();
    }
}
