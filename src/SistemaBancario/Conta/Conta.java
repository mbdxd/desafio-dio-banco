package SistemaBancario.Conta;

public abstract class Conta implements InterfaceConta {

    private static int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente titular;

    public Conta() {

    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getTitular() {
        return titular;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    @Override
    public void sacar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do saque deve ser positivo.");
        }
        if (valor > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }
        saldo -= valor;
    }

    @Override
    public void depositar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do depósito deve ser positivo.");
        }
        saldo += valor;
    }

    @Override
    public void transferir(double valor, Conta contaDestino) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor da transferência deve ser positivo.");
        }
        sacar(valor);
        contaDestino.depositar(valor);
    }

    public abstract void imprimirExtrato();

    @Override
    public String toString() {
        return String.format("Conta %d-%d | Saldo: %.2f", agencia, numero, saldo);
    }

    public void setNumero(int i) {
    }
}
