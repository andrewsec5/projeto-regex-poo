package projetoRegex.administrador;

public class Administrador {
    private String nome;
    private String cpfCnpj;
    private String telefone;
    private String email;
    private String dataNascimentoFundacao;
    private String senha;

    public Administrador(String nome, String cpfCnpj, String email, String senha){
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.senha = senha;
    }

    public void setDataNascimentoFundacao(String dataNascimentoFundacao) {
        this.dataNascimentoFundacao = dataNascimentoFundacao;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
