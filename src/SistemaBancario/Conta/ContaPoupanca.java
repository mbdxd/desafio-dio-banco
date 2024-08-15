package SistemaBancario.Conta;

public class ContaPoupanca extends Conta {

    private double rendimentoMensal;

    public ContaPoupanca() {
        super();
    }

    public ContaPoupanca(double rendimentoMensal) {
        super();
        this.rendimentoMensal = rendimentoMensal;
    }

    public double getRendimentoMensal() {
        return rendimentoMensal;
    }

    public void setRendimentoMensal(double rendimentoMensal) {
        this.rendimentoMensal = rendimentoMensal;
    }

    public void aplicarRendimento() {
        if (rendimentoMensal > 0) {
            saldo += saldo * rendimentoMensal / 100;
        }
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("=== Extrato Conta Poupança ===");
        System.out.println(String.format("Agência: %d", getAgencia()));
        System.out.println(String.format("Número: %d", getNumero()));
        System.out.println(String.format("Saldo: %.2f", getSaldo()));
        System.out.println(String.format("Rendimento Mensal: %.2f%%", getRendimentoMensal()));
    }
}
