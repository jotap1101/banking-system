# Sistema Bancário Java

Este é um projeto simples de um **Sistema Bancário** em Java, que permite operações como **depósitos**, **saques**, **transferências** e **cálculo de rendimentos**. O sistema suporta diferentes tipos de contas: **Conta Corrente** e **Conta Poupança**, com funcionalidades específicas para cada uma.

## Funcionalidades

- **Conta Corrente**:
  - Suporte a **cheque especial**.
  - Operações de **saque**, **depósito** e **transferência**.
  - Impressão de extrato com informações sobre saldo e limite do cheque especial.

- **Conta Poupança**:
  - Cálculo de **rendimentos** baseado em uma **taxa de rendimento**.
  - Operações de **saque**, **depósito** e **transferência**.
  - Impressão de extrato com informações sobre saldo e taxa de rendimento.

## Estrutura do Projeto

O sistema é composto pelas seguintes classes:

### `Conta`

Classe abstrata que define o comportamento básico de uma conta bancária. Contém métodos comuns como **sacar**, **depositar** e **transferir**, além de um método abstrato `imprimirExtrato` para ser implementado nas classes filhas.

#### Atributos:
- `numero`: Número da conta.
- `saldo`: Saldo atual da conta.
- `cliente`: Cliente associado à conta.

#### Métodos:
- `sacar(double valor)`: Método abstrato para saque.
- `depositar(double valor)`: Realiza o depósito e solicita confirmação do usuário.
- `transferir(Conta contaDestino, double valor)`: Transfere valores entre contas, com confirmação do usuário.
- `imprimirExtrato()`: Método abstrato para exibir os detalhes da conta.

### `ContaCorrente`

Extende a classe `Conta` e representa uma conta corrente com suporte a **cheque especial**.

#### Atributos:
- `limiteChequeEspecial`: Limite disponível para uso de cheque especial.

#### Métodos:
- `sacar(double valor)`: Permite o uso do cheque especial em caso de saldo insuficiente.
- `imprimirExtrato()`: Exibe os detalhes da conta corrente, incluindo limite do cheque especial.

### `ContaPoupanca`

Extende a classe `Conta` e representa uma conta poupança com suporte a **rendimentos**.

#### Atributos:
- `taxaRendimento`: Taxa de rendimento aplicada sobre o saldo da conta.

#### Métodos:
- `sacar(double valor)`: Permite saques somente se o saldo for suficiente.
- `render()`: Aplica o rendimento ao saldo com base na taxa de rendimento.
- `imprimirExtrato()`: Exibe os detalhes da conta poupança, incluindo a taxa de rendimento.

### `Cliente`

Representa um cliente do banco, associando-o a uma ou mais contas.

#### Atributos:
- `nome`: Nome do cliente.
- `contas`: Lista de contas associadas ao cliente.

#### Métodos:
- `adicionarConta(Conta conta)`: Adiciona uma nova conta ao cliente.

### `Main`

Classe principal que contém o método `main` para execução do sistema. Permite a interação com o usuário através de um menu de opções.

## Execução

Para executar o sistema, basta compilar os arquivos `.java` e executar a classe `Main`. O sistema exibirá um menu com as opções disponíveis, permitindo a realização de operações bancárias.

## Exemplo

A seguir, um exemplo de execução do sistema:

```plaintext
Bem-vindo ao Banco!

========== Menu do Banco ==========

1. Cadastrar Cliente
2. Listar Clientes
3. Editar Cliente
4. Remover Cliente
5. Criar Conta
6. Realizar Saque
7. Realizar Depósito
8. Realizar Transferência
9. Aplicar Rendimento
10. Consultar Saldo
11. Imprimir Extrato
0. Sair
```