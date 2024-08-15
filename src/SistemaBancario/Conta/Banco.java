package SistemaBancario.Conta;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Banco {

    private final Map<Integer, Conta> contas = new HashMap<>();
    private final Map<String, Cliente> clientes = new HashMap<>();
    private int sequencialConta = 1; // Para gerar números únicos de contas

    public void adicionarCliente(Cliente cliente) {
        if (clientes.containsKey(cliente.getCpf())) {
            throw new IllegalArgumentException("Cliente já cadastrado com o CPF: " + cliente.getCpf());
        }
        clientes.put(cliente.getCpf(), cliente);
    }

    public void removerCliente(String cpf) {
        if (!clientes.containsKey(cpf)) {
            throw new IllegalArgumentException("Cliente não encontrado com o CPF: " + cpf);
        }
        clientes.remove(cpf);
        // Remover contas associadas ao cliente (opcional)
        contas.values().removeIf(conta -> conta.getTitular() != null && conta.getTitular().getCpf().equals(cpf));
    }

    public void adicionarConta(Conta conta) {
        if (contas.containsKey(conta.getNumero())) {
            throw new IllegalArgumentException("Conta já cadastrada com o número: " + conta.getNumero());
        }
        conta.setNumero(sequencialConta++);
        contas.put(conta.getNumero(), conta);
    }

    public void removerConta(int numeroConta) {
        if (!contas.containsKey(numeroConta)) {
            throw new IllegalArgumentException("Conta não encontrada com o número: " + numeroConta);
        }
        contas.remove(numeroConta);
    }

    public Optional<Conta> buscarConta(int numeroConta) {
        return Optional.ofNullable(contas.get(numeroConta));
    }

    public Optional<Cliente> buscarCliente(String cpf) {
        return Optional.ofNullable(clientes.get(cpf));
    }

    public Map<Integer, Conta> getContas() {
        return new HashMap<>(contas);
    }

    public Map<String, Cliente> getClientes() {
        return new HashMap<>(clientes);
    }
}
