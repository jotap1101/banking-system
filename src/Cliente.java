import java.util.ArrayList;
import java.util.List;

public class Cliente {
    // Construtor
    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.contas = new ArrayList<Conta>();
        this.contaCorrente = null;
        this.contaPoupanca = null;
    }

    // Atributos (características)
    private String nome;
    private String cpf;
    private List<Conta> contas;
    private ContaCorrente contaCorrente;
    private ContaPoupanca contaPoupanca;

    // Métodos (comportamentos)
    public void adicionarConta(Conta conta) {
        if (conta instanceof ContaCorrente) {
            if (this.contaCorrente == null) {
                this.contaCorrente = (ContaCorrente) conta;
                this.contas.add(conta);
            } else {
                System.out.println("O cliente já possui uma conta corrente.");
            }
        } else if (conta instanceof ContaPoupanca) {
            if (this.contaPoupanca == null) {
                this.contaPoupanca = (ContaPoupanca) conta;
                this.contas.add(conta);
            } else {
                System.out.println("O cliente já possui uma conta poupança.");
            }
        }
    }

    public void removerConta(Conta conta) {
        if (conta instanceof ContaCorrente) {
            if (this.contaCorrente == conta) {
                this.contaCorrente = null;
            }
        } else if (conta instanceof ContaPoupanca) {
            if (this.contaPoupanca == conta) {
                this.contaPoupanca = null;
            }
        }

        this.contas.remove(conta);
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    public ContaCorrente getContaCorrente() {
        return contaCorrente;
    }

    public void setContaCorrente(ContaCorrente contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    public ContaPoupanca getContaPoupanca() {
        return contaPoupanca;
    }

    public void setContaPoupanca(ContaPoupanca contaPoupanca) {
        this.contaPoupanca = contaPoupanca;
    }

    @Override
    public String toString() {
        String contaCorrenteInfo = (this.contaCorrente != null) ? String.valueOf(this.contaCorrente.getNumero()) : "Não possui conta corrente";
        String contaPoupancaInfo = (this.contaPoupanca != null) ? String.valueOf(this.contaPoupanca.getNumero()) : "Não possui conta poupança";
        
        return "Nome: " + this.nome + "\nCPF: " + this.cpf + "\nConta corrente: " + contaCorrenteInfo + "\nConta poupança: " + contaPoupancaInfo;
    }
}
