/*
 * Faça um programa de computador que solicite ao usuário para preencher uma matriz com
 * números. O usuário também definirá o tamanho da matriz. Após o preenchimento, o
 * programa deverá calcular a soma da diagonal principal e também a soma da diagonal
 * secundária da matriz
 */

import java.util.Scanner;

public class A3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ordem da matriz: ");
        int size = scanner.nextInt();
        int[][] mat = new int[size][size];

        System.out.print("Preencha a matriz: ");

        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                mat[i][j] = scanner.nextInt();
            }
        }

        int principal=0, secundaria=0;

        for(int i=0; i<size; i++) {
            principal += mat[i][i];
            secundaria += mat[i][size-i-1];
        }

        System.out.println("Principal: " + principal);
        System.out.println("Secundaria: " + secundaria);
    }   
}
