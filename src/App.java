import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    // Instanciando um objeto da classe Scanner para capturar a entrada do usuário
    static Scanner scanner = new Scanner(System.in);

    // Lista para armazenar os clientes cadastrados
    private static List<Cliente> clientes = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        System.out.println("\nBem-vindo ao Banco!");

        int opcao;

        do {
            exibirMenu();

            opcao = scanner.nextInt();

            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 0:
                    System.out.println("\nObrigado por utilizar o Banco! Até mais!");
                    break;
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    listarClientes();
                    break;
                case 3:
                    editarCliente();
                    break;
                case 4:
                    removerCliente();
                    break;
                case 5:
                    criarConta();
                    break;
                case 6:
                    realizarSaque();
                    break;
                case 7:
                    realizarDeposito();
                    break;
                case 8:
                    realizarTransferencia();
                    break;
                case 9:
                    aplicarRendimento();
                    break;
                case 10:
                    consultarSaldo();
                    break;
                case 11:
                    imprimirExtrato();
                    break;
                default:
                    System.out.println("\nOpção inválida. Tente novamente.\n");
                    break;
            }
        } while (opcao != 0);
    }

    // Método para exibir o menu
    private static void exibirMenu() {
        System.out.println("\n========== Menu do Banco ==========\n");
        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Listar Clientes");
        System.out.println("3 - Editar Cliente");
        System.out.println("4 - Remover Cliente");
        System.out.println("5 - Criar Conta");
        System.out.println("6 - Realizar Saque");
        System.out.println("7 - Realizar Depósito");
        System.out.println("8 - Realizar Transferência");
        System.out.println("9 - Aplicar Rendimento");
        System.out.println("10 - Consultar Saldo");
        System.out.println("11 - Imprimir Extrato");
        System.out.println("0 - Sair");
        System.out.print("\nEscolha uma opção: ");
    }

    // Método para cadastrar um cliente
    private static void cadastrarCliente() {
        System.out.print("\nDigite o nome do cliente: ");
        String nome = scanner.nextLine();

        System.out.print("\nDigite o CPF do cliente: ");
        String cpf = scanner.next();

        Cliente cliente = new Cliente(nome, cpf);

        clientes.add(cliente);

        System.out.println("\nCliente cadastrado com sucesso!");

        System.out.println("\n========== Dados do Cliente ==========\n");

        System.out.println(cliente.toString());
    }

    // Método para exibir os clientes cadastrados
    private static void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("\nNão há clientes cadastrados.");
        } else {
            System.out.println("\n========== Lista de Clientes ==========");

            for (int i = 0; i < clientes.size(); i++) {
                System.out.println("\n" + (i + 1) + " - " + clientes.get(i).toString());
            }
        }
    }

    // Método auxiliar para selecionar um cliente
    private static Cliente selecionarCliente() {
        listarClientes();

        if (!clientes.isEmpty()) {
            System.out.print("\nEscolha o cliente (número): ");
            int numero = scanner.nextInt() - 1;

            scanner.nextLine(); // Limpar o buffer

            if (numero >= 0 && numero < clientes.size()) {
                return clientes.get(numero);
            } else {
                System.out.println("\nCliente não encontrado.");
            }
        }

        return null;
    }

    // Método para editar um cliente
    private static void editarCliente() {
        Cliente cliente = selecionarCliente();

        if (cliente != null) {
            System.out.println("\nDigite o novo nome do cliente ou pressione ENTER para manter o mesmo nome: ");
            String nome = scanner.nextLine();

            System.out.println("\nDigite o novo CPF do cliente ou pressione ENTER para manter o mesmo CPF: ");
            String cpf = scanner.nextLine();

            // Mensagem de confirmação
            System.out.print("\nTem certeza que deseja editar o cliente (S/N)? ");

            String confirmacao = scanner.nextLine();

            if (confirmacao.equalsIgnoreCase("S")) {
                if (!nome.isEmpty()) {
                    cliente.setNome(nome);
                }

                if (!cpf.isEmpty()) {
                    cliente.setCpf(cpf);
                }

                System.out.println("\nCliente editado com sucesso!");

                System.out.println("\n========== Dados do Cliente ==========\n");

                System.out.println(cliente.toString());
            } else {
                System.out.println("\nOperação cancelada.");
            }
        }
    }

    // Método para remover um cliente
    private static void removerCliente() {
        Cliente cliente = selecionarCliente();

        if (cliente != null) {
            System.out.print("\nTem certeza que deseja remover o cliente (S/N)? ");

            String confirmacao = scanner.nextLine();

            if (confirmacao.equalsIgnoreCase("S")) {
                clientes.remove(cliente);
                cliente.getContas().clear();

                System.out.println("\nCliente removido com sucesso!");
            } else {
                System.out.println("\nOperação cancelada.");
            }
        }
    }

    // Método para escolher o tipo de conta a ser criada
    private static void criarConta() {
        System.out.println("\n========== Tipos de conta ==========\n");

        System.out.println("1 - Conta Corrente");
        System.out.println("2 - Conta Poupança");

        System.out.print("\nEscolha o tipo de conta (número): ");

        int numero = scanner.nextInt();

        scanner.nextLine(); // Limpar o buffer

        switch (numero) {
            case 1:
                criarContaCorrente();
                break;
            case 2:
                criarContaPoupanca();
                break;
            default:
                System.out.println("\nOpção inválida. Tente novamente.");
                break;
        }
    }

    // Método para verificar se o cliente já possui alguma conta
    public void verificarConta(Cliente cliente) {
        if (cliente.getContaCorrente() == null && cliente.getContaPoupanca() == null) {
            System.out.println("\nNão é possível realizar a operação. O cliente não possui contas.");
        }
    }


    // Método para criar uma conta corrente
    private static void criarContaCorrente() {
        Cliente cliente = selecionarCliente();

        if (cliente != null) {
            if (cliente.getContaCorrente() != null) {
                System.out.println("\nO cliente já possui uma conta corrente.");
                return;
            }

            int numero = (int) (Math.random() * 900000 + 100000);

            System.out.print("\nLimite do Cheque Especial: ");
            double limiteChequeEspecial = scanner.nextDouble();

            ContaCorrente contaCorrente = new ContaCorrente(numero, cliente, limiteChequeEspecial);

            System.out.println("\nConta Corrente criada com sucesso!");

            System.out.println("\n========== Dados da Conta Corrente ==========\n");

            System.out.println(contaCorrente.toString());
        }
    }

    // Método para criar uma conta poupança
    private static void criarContaPoupanca() {
        Cliente cliente = selecionarCliente();

        if (cliente != null) {
            if (cliente.getContaPoupanca() != null) {
                System.out.println("\nO cliente já possui uma conta poupança.");
                return;
            }

            int numero = (int) (Math.random() * 900000 + 100000);

            System.out.print("\nTaxa de Rendimento: ");
            double taxaRendimento = scanner.nextDouble();

            ContaPoupanca contaPoupanca = new ContaPoupanca(numero, cliente, taxaRendimento);

            System.out.println("\nConta Poupança criada com sucesso!");

            System.out.println("\n========== Dados da Conta Poupança ==========\n");

            System.out.println(contaPoupanca.toString());
        }
    }

    // Método para selecionar uma conta para operar
    private static Conta selecionarConta() {
        Cliente cliente = selecionarCliente();

        if (cliente != null && !cliente.getContas().isEmpty()) {
            List<Conta> contas = cliente.getContas();

            if (!contas.isEmpty()) {
                System.out.println("\n========== Contas do Cliente ==========\n");

                for (int i = 0; i < contas.size(); i++) {
                    System.out.println((i + 1) + " - " + contas.get(i).toString());
                }

                System.out.print("\nEscolha a conta (número): ");
                int numero = scanner.nextInt() - 1;

                scanner.nextLine(); // Limpar o buffer

                if (numero >= 0 && numero < contas.size()) {
                    return contas.get(numero);
                } else {
                    System.out.println("\nConta não encontrada.");
                }
            } else {
                System.out.println("\nNão há contas cadastradas para este cliente.");
            }
        }

        return null;
    }

    // Método para realizar um saque
    private static void realizarSaque() {
        Conta conta = selecionarConta();

        if (conta != null) {
            System.out.print("\nDigite o valor do saque: ");
            double valor = scanner.nextDouble();

            conta.sacar(valor);
        } else {
            System.out.println("\nNão há contas cadastradas para este cliente.");
        }
    }

    // Método para realizar um depósito
    private static void realizarDeposito() {
        Conta conta = selecionarConta();

        if (conta != null) {
            System.out.print("\nDigite o valor do depósito: ");
            double valor = scanner.nextDouble();

            conta.depositar(valor);
        } else {
            System.out.println("\nNão há contas cadastradas para este cliente.");
        }
    }

    // Método para realizar uma transferência
    private static void realizarTransferencia() {
        System.out.println("\nDigite o número da conta de origem: ");

        Conta contaOrigem = selecionarConta();

        if (contaOrigem != null) {
            System.out.println("\nDigite o número da conta de destino: ");
            int numero = scanner.nextInt();

            Cliente clienteDestino = null;

            for (Cliente cliente : clientes) {
                for (Conta conta : cliente.getContas()) {
                    if (conta.getNumero() == numero) {
                        clienteDestino = cliente;
                        break;
                    }
                }
            }

            if (clienteDestino != null) {
                Conta contaDestino = null;

                for (Conta conta : clienteDestino.getContas()) {
                    if (conta.getNumero() == numero) {
                        contaDestino = conta;
                        break;
                    }
                }

                if (contaDestino != null) {
                    System.out.print("\nDigite o valor da transferência: ");
                    double valor = scanner.nextDouble();

                    contaOrigem.transferir(contaDestino, valor);
                } else {
                    System.out.println("\nConta de destino não encontrada.");
                }
            } else {
                System.out.println("\nCliente de destino não encontrado.");
            }
        } else {
            System.out.println("\nConta de origem não encontrada.");
        }
    }

    // Método para aplicar rendimento
    private static void aplicarRendimento() {
        Cliente cliente = selecionarCliente();

        if (cliente != null) {
            if (cliente.getContaPoupanca() != null) {
                cliente.getContaPoupanca().render();
            } else {
                System.out.println("\nO cliente não possui uma conta poupança.");
            }
        }
    }

    // Método para consultar o saldo
    private static void consultarSaldo() {
        Conta conta = selecionarConta();

        if (conta != null) {
            System.out.println("\nSaldo: R$ " + conta.getSaldo());
        }
    }

    // Método para imprimir extrato
    private static void imprimirExtrato() {
        Conta conta = selecionarConta();

        if (conta != null) {
            conta.imprimirExtrato();
        }
    }
}   
