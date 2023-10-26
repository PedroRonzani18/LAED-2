/*
 * Implemente a busca binária em uma função para procurar um determinado número
 * em uma lista que já está ordenada.
 */

import java.util.Scanner;

public class A7 {

    public static int binary_search(int[] list, int size, int number) {
        int start = 0, finish = size;
        int mid = (start + finish)/2;

        while(start != mid && finish != mid) {

            if(number < list[mid])  {
                finish = mid;
                mid = (start + mid) / 2;
            }
            else {
                start = mid;
                mid = (finish + mid) / 2;
            }
        }
        
        return (list[mid] == number ? mid : -1);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    
        System.out.print("Quantidade de elementos: ");
        
        int size = scanner.nextInt();
        int [] list = new int[size];

        System.out.print("Lista Ordenada: ");
        for(int i=0; i<size; i++) {
            list[i] = scanner.nextInt();
        }

        System.out.println("Numero: ");

        int index = binary_search(list, size, scanner.nextInt());

        System.out.println((index == -1 ? "Não encontrado" : ("Posicao: " + index)));
    }
}
