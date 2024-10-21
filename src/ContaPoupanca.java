public class ContaPoupanca extends Conta {
    // Construtor
    public ContaPoupanca(int numero, Cliente cliente, double taxaRendimento) {
        super(numero, cliente);
        this.taxaRendimento = taxaRendimento;
    }

    // Atributos (características)
    private double taxaRendimento;

    // Métodos (comportamentos)
    public void render() {
        this.setSaldo(this.getSaldo() + this.getSaldo() * this.getTaxaRendimento());

        System.out.println("\nRendimento de R$ " + this.getSaldo() * this.getTaxaRendimento() + " realizado com sucesso.");
    }

    // Getters e Setters
    public double getTaxaRendimento() {
        return taxaRendimento;
    }

    public void setTaxaRendimento(double taxaRendimento) {
        this.taxaRendimento = taxaRendimento;
    }

    // Métodos sobrescritos
    @Override
    public void sacar(double valor) {
        if (valor > 0) {
            if (valor <= this.getSaldo()) {
                System.out.println("\nDeseja sacar R$ " + valor + " da conta de " + this.getCliente().getNome() + " (S/N)?");

                String confirmacao = scanner.nextLine();

                if (confirmacao.equalsIgnoreCase("S")) {
                    this.setSaldo(this.getSaldo() - valor);

                    System.out.println("\nSaque de R$ " + valor + " realizado com sucesso.");

                    System.out.println("\n========== Dados da Conta Poupança ==========\n");

                    System.out.println(this.toString());
                } else {
                    System.out.println("\nOperação cancelada.");
                }
            } else {
                System.out.println("\nSaldo insuficiente.");
            }
        } else {
            System.out.println("\nValor de saque inválido.");
        }
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("\n========== Dados da Conta Poupança ==========\n");

        System.out.println("Titular: " + this.getCliente().getNome());
        System.out.println("Tipo de Conta: Conta Poupança");
        System.out.println("Número da Conta: " + this.getNumero());
        System.out.println("Saldo atual: R$ " + this.getSaldo());
        System.out.println("Taxa de Rendimento: " + this.getTaxaRendimento() * 100 + "%");

        System.out.println("\n=============================================");
    }

    @Override
    public String toString() {
        return "Titular: " + this.getCliente().getNome() + "\nTipo de Conta: Conta Poupança\nNúmero da Conta: " + this.getNumero() + "\nSaldo: R$ " + this.getSaldo() + "\nTaxa de Rendimento: " + this.getTaxaRendimento() * 100;
    }
}
