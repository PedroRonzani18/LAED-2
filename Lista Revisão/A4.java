/*
 * A cidade A tem 40.000 habitantes. A cidade B tem 100.000 habitantes e por fim, a cidade C
 * tem 80.000 habitantes. Especialistas acreditam que essas 3 cidades terão um crescimento
 * populacional de 2% por ano. Escreva um programa de computador que armazene em uma
 * matriz a população estimada dessas cidades ano após ano, começando por 2023   e
 * encerrando no ano 2028. No qual, cada linha represente uma cidade e cada coluna uma
 * previsão de tamanho populacional
 */

public class A4 {
    public static void main(String[] args) {
        double[][] mat = new double[3][6];

        mat[0][0] =  40000;
        mat[1][0] = 100000;
        mat[2][0] =  80000;

        char[] letras = {'A', 'B', 'C'};

        System.out.println("Crescimentos");

        for(int i=0; i<3; i++) {
            System.out.print(letras[i] + ": ");
            
            for(int j=0; j<6; j++) {
                mat[i][j] = mat[i][0] * Math.pow(1.02, j) ;
                System.out.print(mat[i][j] + " | ");
            }
            System.out.println();
        }
    }
}
