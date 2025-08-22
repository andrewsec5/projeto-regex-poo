package projetoRegex.menus;

import projetoRegex.administrador.Administrador;
import projetoRegex.ferramentas.Entrada;
import projetoRegex.ferramentas.Validador;

public class MenuInicial implements Menu {

    public static void cadastrarAdm() {
        String nome;
        String email = null;
        String doc;
        String senha = null;
        boolean validacaoEmail = false;
        boolean validacaoDoc = false;
        boolean validacaoSenha = false;
        boolean validacaoConfirmacaoSenha = false;
        System.out.println("=======CADASTRO DE ADMINISTRADOR=======");
        System.out.print("Nome: ");
        nome = Entrada.scanner.nextLine();
        while (!validacaoEmail) {
            System.out.print("Email: ");
            email = Entrada.scanner.nextLine();
            if (Validador.validarEmail(email)) validacaoEmail = true;
            else System.out.println("Email inválido! ");
        }
        Administrador adm = new Administrador(nome, email);
        while(!validacaoDoc) {
            System.out.print("CPF/CNPJ: ");
            doc = Entrada.scanner.nextLine();
            if(Validador.validarDoc(doc, adm))validacaoDoc = true;
            else System.out.println("CPF/CNPJ inválido!");
        }
        while(!validacaoSenha) {
            System.out.print("Senha: ");
            senha = Entrada.scanner.nextLine();
        }
    }
    //SEPARAR ENTRADA DE DADOS EM METODOS DE OUTRA CLASSE

    public static void exibirMenu(){

    }
}
