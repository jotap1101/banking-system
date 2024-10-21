import java.util.Scanner;

public class ContaCorrente extends Conta {
    // Instanciando um objeto da classe Scanner para capturar a entrada do usuário
    Scanner scanner = new Scanner(System.in);

    // Construtor
    public ContaCorrente(int numero, Cliente cliente, double limiteChequeEspecial) {
        super(numero, cliente);
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    // Atributos (características)
    private double limiteChequeEspecial;

    // Getters e Setters
    public double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    public void setLimiteChequeEspecial(double limiteChequeEspecial) {
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    // Métodos sobrescritos
    @Override
    public void sacar(double valor) {
        if (valor > 0) {
            if (valor <= this.getSaldo() + this.limiteChequeEspecial) {
                // Se o saldo for 0 ou negativo, informe que o saque foi realizado com sucesso e que o cliente está utilizando o cheque especial
                if (this.getSaldo() <= 0) {
                    // Mensagem falando que o saldo é 0 ou negativo e que o cliente está utilizando o cheque especial. Pergunte ao usuário se ele deseja prosseguir com o saque
                    System.out.print("\nSaldo insuficiente. Deseja utilizar o Cheque Especial (S/N)? ");
                    String confirmacao = scanner.nextLine();

                    if (confirmacao.equalsIgnoreCase("S")) {
                        this.setSaldo(this.getSaldo() - valor);

                        System.out.println("\nSaque de R$ " + valor + " realizado com sucesso. O valor foi debitado do Cheque Especial.");

                        System.out.println("\n========== Dados da Conta Corrente ==========\n");

                        System.out.println(this.toString());
                    } else {
                        System.out.println("\nOperação cancelada.");
                    }
                } else {
                    System.out.println("\nDeseja sacar R$ " + valor + " da conta de " + this.getCliente().getNome() + " (S/N)?");

                    String confirmacao = scanner.nextLine();

                    if (confirmacao.equalsIgnoreCase("S")) {
                        this.setSaldo(this.getSaldo() - valor);

                        System.out.println("\nSaque de R$ " + valor + " realizado com sucesso.");

                        System.out.println("\n========== Dados da Conta Corrente ==========\n");

                        System.out.println(this.toString());
                    } else {
                        System.out.println("\nOperação cancelada.");
                    }
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
        System.out.println("\n========== Extrato da Conta Corrente ==========\n");

        System.out.println("Titular: " + this.getCliente().getNome());
        System.out.println("Tipo de Conta: Conta Corrente");
        System.out.println("Número da Conta: " + this.getNumero());
        System.out.println("Saldo atual: R$ " + this.getSaldo());
        System.out.println("Limite do Cheque Especial: R$ " + this.getLimiteChequeEspecial());
        
        System.out.println("\n=============================================");
    }

    @Override
    public String toString() {
        return "Titular: " + this.getCliente().getNome() + "\nTipo de Conta: Conta Corrente\nNúmero da Conta: " + this.getNumero() + "\nSaldo: R$ " + this.getSaldo() + "\nLimite do Cheque Especial: R$ " + this.getLimiteChequeEspecial();
    }
}
