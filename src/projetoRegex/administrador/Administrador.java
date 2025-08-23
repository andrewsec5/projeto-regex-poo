package projetoRegex.administrador;

public class Administrador {
    private String nome;
    private String cpfCnpj;
    private boolean pessoaFisica;
    private String telefone;
    private String email;
    private String dataNascimentoFundacao;
    private String senha;

    public Administrador(String nome, String email){
        this.nome = nome;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public String getEmail() {
        return email;
    }

    public boolean isPessoaFisica() {
        return pessoaFisica;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public void setDataNascimentoFundacao(String dataNascimentoFundacao) {
        this.dataNascimentoFundacao = dataNascimentoFundacao;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setPessoaFisica(boolean pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }
}
