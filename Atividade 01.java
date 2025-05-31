/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/


//UNIVERSIDADE FEDERAL RURAL DO SEMI-ÁRIDO - UFERSA 
//Núcleo de Educação a Distância - NEAD 
//LICENCIATURA EM COMPUTAÇÃO - EAD (POLO PAU DOS FERRO)
//Disciplina: Linguagem de Programação Orientada a Objetos I 
//Prof.: Paulo Henrique Lopes Silva 
//Tutores: Andre Luiz Viana Pereira e Jarbas de Sousa Viana 
//ALUNO: LHUSANDRO CESAR CAMPOS PINTO


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Entrar com o login 
        String loginCorreto = "usuario";
        String senhaCorreta = "1234";
        double saldo = 100.00;

        // Login
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        // Checa o login
        if (login.equals(loginCorreto) && senha.equals(senhaCorreta)) {
            System.out.println("Bem-vindo ao Banco!");

            // Exibe as opções 
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Mostrar Saldo");
            System.out.println("2 - Depósito");
            System.out.println("3 - Saque");
            System.out.println("4 - Fazer Pix");
            System.out.println("4 - Investimentos");
            System.out.print("Digite a opção: ");
            int opcao = scanner.nextInt();

            if (opcao == 1) {
                System.out.println("Seu saldo é: R$ " + saldo);
            } else if (opcao == 2) {
                System.out.print("Digite o valor do depósito: ");
                double deposito = scanner.nextDouble();
                if (deposito > 0) {
                    saldo += deposito;
                    System.out.println("Depósito feito. Novo saldo: R$ " + saldo);
                } else {
                    System.out.println("Valor inválido.");
                }
            } else if (opcao == 3) {
                System.out.print("Digite o valor do saque: ");
                double saque = scanner.nextDouble();
                if (saque > 0 && saque <= saldo) {
                    saldo -= saque;
                    System.out.println("Saque feito. Novo saldo: R$ " + saldo);
                } else {
                    System.out.println("Saque inválido ou saldo insuficiente.");
                }
            } else if (opcao == 4) {
                System.out.print("Digite o valor a investir: ");
                double investimento = scanner.nextDouble();
                if (investimento > 0 && investimento <= saldo) {
                    saldo -= investimento;
                    double retorno = investimento * 1.03;
                    System.out.println("Investimento feito. Retorno estimado: R$ " + retorno);
                    System.out.println("Saldo restante: R$ " + saldo);
                } else {
                    System.out.println("Valor inválido ou saldo insuficiente.");
                }
            } else {
                System.out.println("Opção inválida.");
            }

        } else {
            System.out.println("Login ou senha incorretos. Acesso negado.");
        }

        scanner.close();
    }
}

