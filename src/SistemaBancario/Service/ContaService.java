package SistemaBancario.Service;

import SistemaBancario.Conta.Banco;
import SistemaBancario.Conta.Cliente;
import SistemaBancario.Conta.Conta;
import SistemaBancario.Conta.ContaCorrente;
import SistemaBancario.Conta.ContaPoupanca;
import SistemaBancario.Conta.TipoConta;

public class ContaService {

    private Banco banco;

    public ContaService(Banco banco) {
        this.banco = banco;
    }

    public void criarCliente(String nome, String cpf, String endereco) {
        if (banco.buscarCliente(cpf).isPresent()) {
            throw new IllegalArgumentException("Cliente já cadastrado com o CPF: " + cpf);
        }
        Cliente cliente = new Cliente(nome, cpf, endereco);
        banco.adicionarCliente(cliente);
    }


    public Conta criarConta(String cpf, TipoConta tipoConta, double parametro) {
        Cliente cliente = banco.buscarCliente(cpf).orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        Conta conta;
        switch (tipoConta) {
            case CORRENTE:
                conta = new ContaCorrente(parametro); // parâmetro para limite especial
                break;
            case POUPANCA:
                conta = new ContaPoupanca(parametro); // parâmetro para rendimento mensal
                break;
            default:
                throw new IllegalArgumentException("Tipo de conta inválido");
        }
        conta.setTitular(cliente);
        banco.adicionarConta(conta);
        return conta;
    }


    public void realizarSaque(int numeroConta, double valor) {
        Conta conta = banco.buscarConta(numeroConta).orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));
        conta.sacar(valor);
    }

    public void realizarDeposito(int numeroConta, double valor) {
        Conta conta = banco.buscarConta(numeroConta).orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));
        conta.depositar(valor);
    }

    public void realizarTransferencia(int numeroContaOrigem, int numeroContaDestino, double valor) {
        Conta contaOrigem = banco.buscarConta(numeroContaOrigem).orElseThrow(() -> new IllegalArgumentException("Conta origem não encontrada"));
        Conta contaDestino = banco.buscarConta(numeroContaDestino).orElseThrow(() -> new IllegalArgumentException("Conta destino não encontrada"));
        contaOrigem.transferir(valor, contaDestino);
    }

    public void imprimirExtrato(int numeroConta) {
        Conta conta = banco.buscarConta(numeroConta).orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));
        conta.imprimirExtrato();
    }
}
