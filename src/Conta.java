import java.util.Scanner;

public abstract class Conta {
    // Instanciando um objeto da classe Scanner para capturar a entrada do usuário
    Scanner scanner = new Scanner(System.in);

    // Construtor
    public Conta(int numero, Cliente cliente) {
        this.numero = numero;
        this.saldo = 0;
        this.cliente = cliente;
        cliente.adicionarConta(this);
    }

    // Atributos (características)
    private int numero;
    private double saldo;
    private Cliente cliente;

    // Métodos (comportamentos)
    public abstract void sacar(double valor);

    public void depositar(double valor) {
        if (valor > 0) {
            System.out.println("\nDeseja depositar R$ " + valor + " na conta de " + this.cliente.getNome() + " (S/N)?");

            String confirmacao = scanner.nextLine();

            if (confirmacao.equalsIgnoreCase("S")) {
                this.saldo += valor;

                System.out.println("\nDepósito de R$ " + valor + " realizado com sucesso.");

                System.out.println("\n========== Dados da Conta ==========\n");

                System.out.println(this.toString());
            } else {
                System.out.println("\nOperação cancelada.");
            }
        } else {
            System.out.println("\nValor de depósito inválido.");
        }
    }

    public void transferir(Conta contaDestino, double valor) {
        if (valor > 0) {
            if (this.saldo >= valor) {
                System.out.println("\nDeseja transferir R$ " + valor + " da conta de " + this.cliente.getNome() + " para a conta de " + contaDestino.getCliente().getNome() + " (S/N)?");

                String confirmacao = scanner.nextLine();

                if (confirmacao.equalsIgnoreCase("S")) {
                    this.saldo -= valor;
                    contaDestino.saldo += valor;

                    System.out.println("\nTransferência de R$ " + valor + " realizada com sucesso.");

                    System.out.println("\n========== Dados da Conta ==========\n");

                    System.out.println(this.toString());
                } else {
                    System.out.println("\nOperação cancelada.");
                }
            } else {
                System.out.println("\nSaldo insuficiente.");
            }
        } else {
            System.out.println("\nValor de transferência inválido.");
        }
    }

    public abstract void imprimirExtrato();

    // Getters e Setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
