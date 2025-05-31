/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numeros = new int[20];  
        
        System.out.println("Digite 20 números inteiros:");
        for (int i = 0; i < 20; i++) {
            System.out.print("Número " + (i + 1) + ": ");
            numeros[i] = scanner.nextInt();
        }

        System.out.println("\nArray original:");
        for (int i = 0; i < 20; i++) {
            System.out.print(numeros[i] + " ");
        }

            for (int i = 0; i < 20; i++) {
            if (numeros[i] % 2 != 0) { // Verifica se é ímpar
                numeros[i] = 1;
            }
        }

        System.out.println("\n\nArray após substituir ímpares por 1:");
        for (int i = 0; i < 20; i++) {
            System.out.print(numeros[i] + " ");
        }

        scanner.close();  
    }
}
