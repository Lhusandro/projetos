/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.*;

class Cliente {
    private String cpf;
    private String nome;
    private String dataNascimento;

    public Cliente(String cpf, String nome, String dataNascimento) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public String getDados() {
        return "CPF: " + cpf + ", Nome: " + nome + ", Nascimento: " + dataNascimento;
    }
}

class Conta {
    private int numeroConta;
    private String usuario;
    private String senha;
    private double saldo;
    private Cliente cliente;

    public Conta(int numeroConta, String usuario, String senha, Cliente cliente) {
        this.numeroConta = numeroConta;
        this.usuario = usuario;
        this.senha = senha;
        this.cliente = cliente;
        this.saldo = 0;
    }

    public boolean autenticar(String usuario, String senha) {
        return this.usuario.equals(usuario) && this.senha.equals(senha);
    }

    public double getSaldo() {
        return saldo;
    }

    public String getUsuario() {
        return usuario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
        }
    }

    public boolean sacar(double valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            return true;
        }
        return false;
    }

    public boolean transferir(Conta destino, double valor) {
        if (this.sacar(valor)) {
            destino.depositar(valor);
            return true;
        }
        return false;
    }

    public boolean pix(String usuarioDestino, double valor, List<Conta> contas) {
        for (Conta c : contas) {
            if (c.getUsuario().equals(usuarioDestino)) {
                return this.transferir(c, valor);
            }
        }
        return false;
    }

    public double investir(int tipo, double valor) {
        if (sacar(valor)) {
            if (tipo == 1) {
                return valor * 1.03;
            } else if (tipo == 2) {
                return valor * 1.05;
            }
        }
        return 0;
    }
}

class Agencia {
    private int numero;
    private List<Conta> contas;

    public Agencia(int numero) {
        this.numero = numero;
        this.contas = new ArrayList<>();
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public Conta buscarPorUsuario(String usuario) {
        for (Conta c : contas) {
            if (c.getUsuario().equals(usuario)) {
                return c;
            }
        }
        return null;
    }

    public Conta buscarPorNumero(int numeroConta) {
        for (Conta c : contas) {
            if (c.getNumeroConta() == numeroConta) {
                return c;
            }
        }
        return null;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public int getNumero() {
        return numero;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criar clientes
        Cliente c1 = new Cliente("432.186.103-97", "Lhusandro", "24/06/1973");
        Cliente c2 = new Cliente("568.321.568-89", "Ionar", "25/10/2000");

        // Criar contas
        Conta conta1 = new Conta(1001, "Lhusandro", "1234", c1);
        Conta conta2 = new Conta(2001, "Ionar", "1234", c2);
        conta1.depositar(1000);
        conta2.depositar(2000);

        // Criar agências
        Agencia a1 = new Agencia(1);
        Agencia a2 = new Agencia(2);
        a1.adicionarConta(conta1);
        a2.adicionarConta(conta2);

        // Login
        System.out.print("Usuário: ");
        String usuario = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Conta contaLogada = null;
        List<Agencia> agencias = Arrays.asList(a1, a2);
        for (Agencia ag : agencias) {
            Conta c = ag.buscarPorUsuario(usuario);
            if (c != null && c.autenticar(usuario, senha)) {
                contaLogada = c;
                break;
            }
        }

        if (contaLogada == null) {
            System.out.println("Login inválido.");
            return;
        }

        int op;
        do {
            System.out.println("\n1. Mostrar Saldo");
            System.out.println("2. Depósito");
            System.out.println("3. Saque");
            System.out.println("4. Fazer Pix");
            System.out.println("5. Investimentos");
            System.out.println("6. Transferência");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1:
                    System.out.println("Saldo: R$ " + contaLogada.getSaldo());
                    break;
                case 2:
                    System.out.print("Valor: ");
                    double d = scanner.nextDouble();
                    contaLogada.depositar(d);
                    break;
                case 3:
                    System.out.print("Valor: ");
                    double s = scanner.nextDouble();
                    if (contaLogada.sacar(s)) {
                        System.out.println("Saque ok.");
                    } else {
                        System.out.println("Erro no saque.");
                    }
                    break;
                case 4:
                    System.out.print("Chave Pix (usuário): ");
                    String chave = scanner.nextLine();
                    System.out.print("Valor: ");
                    double v = scanner.nextDouble();
                    List<Conta> todas = new ArrayList<>();
                    todas.addAll(a1.getContas());
                    todas.addAll(a2.getContas());
                    if (contaLogada.pix(chave, v, todas)) {
                        System.out.println("Pix ok.");
                    } else {
                        System.out.println("Erro no pix.");
                    }
                    break;
                case 5:
                    System.out.print("Valor: ");
                    double inv = scanner.nextDouble();
                    System.out.println("1. Poupança\n2. Renda Fixa");
                    int tipo = scanner.nextInt();
                    double rend = contaLogada.investir(tipo, inv);
                    System.out.println("Retorno: R$ " + rend);
                    break;
                case 6:
                    System.out.print("Conta destino: ");
                    int num = scanner.nextInt();
                    System.out.print("Agência: ");
                    int agn = scanner.nextInt();
                    System.out.print("Valor: ");
                    double val = scanner.nextDouble();
                    Agencia agDestino = (agn == 1) ? a1 : a2;
                    Conta destino = agDestino.buscarPorNumero(num);
                    if (destino != null && contaLogada.transferir(destino, val)) {
                        System.out.println("Transferência ok.");
                    } else {
                        System.out.println("Erro na transferência.");
                    }
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (op != 0);
    }
}
