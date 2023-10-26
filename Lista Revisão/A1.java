/*
 * Faça um programa de computador que solicite ao usuário para preencher uma matriz, 
 * do tamanho que ele desejar, com números. Em seguida o programa deverá imprimir a
 * quantidade de números pares e ímpares que ele digitou.
 */

import java.util.Scanner;

class A1 {

    public static void main(String[] args) {
        int linhas, colunas;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Linhas: "); linhas = scanner.nextInt();
        System.out.println("Colunas: "); colunas = scanner.nextInt();

        int mat[][] = new int[linhas][colunas];

        System.out.println("Digite cada elemento: ");

        for(int i=0; i<linhas; i++) {
           for(int j=0; j<colunas; j++) {
                mat[i][j] = scanner.nextInt();
            }
        }


        int pares=0, impares=0;

        for(int i=0; i<linhas; i++) {
            for(int j=0; j<colunas; j++) {
                if(mat[i][j] % 2 == 0) pares++;
                else impares++;
            }
        }

        System.out.println("Pares: " + pares + "\nImpares: " + impares);
    }
}