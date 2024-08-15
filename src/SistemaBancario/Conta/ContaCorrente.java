package SistemaBancario.Conta;

public class ContaCorrente extends Conta {

    private double limiteEspecial;

    public ContaCorrente() {
        super();
    }

    public ContaCorrente(double limiteEspecial) {
        super();
        this.limiteEspecial = limiteEspecial;
    }

    public double getLimiteEspecial() {
        return limiteEspecial;
    }

    public void setLimiteEspecial(double limiteEspecial) {
        this.limiteEspecial = limiteEspecial;
    }

    @Override
    public void sacar(double valor) {
        double saldoDisponivel = saldo + limiteEspecial;
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do saque deve ser positivo.");
        }
        if (valor > saldoDisponivel) {
            throw new IllegalArgumentException("Saldo insuficiente, incluindo limite especial.");
        }
        saldo -= valor;
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("=== Extrato Conta Corrente ===");
        System.out.println(String.format("Agência: %d", getAgencia()));
        System.out.println(String.format("Número: %d", getNumero()));
        System.out.println(String.format("Saldo: %.2f", getSaldo()));
        System.out.println(String.format("Limite Especial: %.2f", getLimiteEspecial()));
    }
}
