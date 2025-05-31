/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] numeros = new int[100];  
        Random random = new Random();  
        int soma = 0;  

            for (int i = 0; i < 100; i++) {
            numeros[i] = random.nextInt(101);  
            soma += numeros[i];  
        }

        double media = soma / 100.0;

        System.out.println("Média dos 100 números: " + media);

        System.out.println("\nNúmeros maiores que a média:");
        int contador = 0;

        for (int i = 0; i < 100; i++) {
            if (numeros[i] > media) {
                System.out.print(numeros[i] + " ");
                contador++;
            }
        }

                System.out.println("\n\nQuantidade de números maiores que a média: " + contador);
    }
}
