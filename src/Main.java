import SistemaBancario.Conta.Banco;
import SistemaBancario.Conta.Conta;
import SistemaBancario.Conta.TipoConta;
import SistemaBancario.Service.ContaService;

import java.util.Scanner;

public class Main {

    private static Banco banco = new Banco();
    private static ContaService contaService = new ContaService(banco);
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean sair = false;

        while (!sair) {
            mostrarMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    criarCliente();
                    break;
                case 2:
                    criarConta();
                    break;
                case 3:
                    realizarSaque();
                    break;
                case 4:
                    realizarDeposito();
                    break;
                case 5:
                    realizarTransferencia();
                    break;
                case 6:
                    imprimirExtrato();
                    break;
                case 0:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }

        System.out.println("Saindo do sistema...");
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("=== Menu Principal ===");
        System.out.println("1. Criar Cliente");
        System.out.println("2. Criar Conta");
        System.out.println("3. Realizar Saque");
        System.out.println("4. Realizar Depósito");
        System.out.println("5. Realizar Transferência");
        System.out.println("6. Imprimir Extrato");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void criarCliente() {
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite o endereço do cliente: ");
        String endereco = scanner.nextLine();

        try {
            contaService.criarCliente(nome, cpf, endereco);
            System.out.println("Cliente criado com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void criarConta() {
        System.out.print("Digite o CPF do cliente para criar a conta: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite o tipo de conta (1 para Corrente, 2 para Poupança): ");
        int tipo = scanner.nextInt();
        TipoConta tipoConta = (tipo == 1) ? TipoConta.CORRENTE : TipoConta.POUPANCA;
        System.out.print("Digite o parâmetro adicional (limite especial para Corrente, rendimento mensal para Poupança): ");
        double parametro = scanner.nextDouble();

        try {
            Conta conta = contaService.criarConta(cpf, tipoConta, parametro);
            System.out.println("Conta criada com sucesso. Número da conta: " + conta.getNumero());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


    private static void realizarSaque() {
        System.out.print("Digite o número da conta: ");
        int numero = scanner.nextInt();
        System.out.print("Digite o valor do saque: ");
        double valor = scanner.nextDouble();

        try {
            contaService.realizarSaque(numero, valor);
            System.out.println("Saque realizado com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void realizarDeposito() {
        System.out.print("Digite o número da conta: ");
        int numero = scanner.nextInt();
        System.out.print("Digite o valor do depósito: ");
        double valor = scanner.nextDouble();

        try {
            contaService.realizarDeposito(numero, valor);
            System.out.println("Depósito realizado com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void realizarTransferencia() {
        System.out.print("Digite o número da conta origem: ");
        int origem = scanner.nextInt();
        System.out.print("Digite o número da conta destino: ");
        int destino = scanner.nextInt();
        System.out.print("Digite o valor da transferência: ");
        double valor = scanner.nextDouble();

        try {
            contaService.realizarTransferencia(origem, destino, valor);
            System.out.println("Transferência realizada com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void imprimirExtrato() {
        System.out.print("Digite o número da conta: ");
        int numero = scanner.nextInt();

        try {
            contaService.imprimirExtrato(numero);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
