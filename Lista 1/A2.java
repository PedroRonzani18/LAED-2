/*
 * Faça um programa de computador  que possibilite a soma de duas matrizes informadas
 * pelo usuário. Faça validações para verificar se será possível somar as duas matrizes.
 */

import java.util.Scanner;

public class A2 {
    public static void main(String[] args) {
        int linhas1, linhas2, colunas1, colunas2;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Quantidade de linhas da matriz 1: "); linhas1 = scanner.nextInt();
        System.out.println("Quantidade de colunas da matriz 1: "); colunas1 = scanner.nextInt();
        System.out.println("Quantidade de linhas da matriz 2: "); linhas2 = scanner.nextInt();
        System.out.println("Quantidade de colunas da matriz 2: "); colunas2 = scanner.nextInt();
    
        if(!(linhas1 == linhas2 && colunas1 == colunas2)) {
            System.out.println("Impossivel realizar a soma");
            System.exit(0);
        }
    

        int mat1[][] = new int[linhas1][colunas1];
        int mat2[][] = new int[linhas2][colunas2];
      
        System.out.println("Digite cada elemento da matriz 1: ");
    
        for(int i=0; i<linhas1; i++) {
            for(int j=0; j<colunas1; j++) {
                mat1[i][j] = scanner.nextInt();
            }
        }
    
        System.out.print("Digite cada elemento da matriz 2: ");
    
        for(int i=0; i<linhas2; i++) {
            for(int j=0; j<colunas2; j++) {
                mat2[i][j] = scanner.nextInt();
            }
        }
    
        System.out.println("Matriz resultante:");
    
        for(int i=0; i<linhas2; i++) {
            for(int j=0; j<colunas2; j++) {
                System.out.print(mat1[i][j] + mat2[i][j] + " ");
            }
            System.out.println();
        }
    }
}
