package projetoRegex.menus;

import projetoRegex.administrador.Administrador;
import projetoRegex.ferramentas.Entrada;
import projetoRegex.ferramentas.Validador;

public class MenuInicial implements Menu {

    public static Administrador cadastrarAdm() {
        String nome;
        String email;
        String doc;
        String senha;
        Administrador adm = null;
        boolean confirmacaoCadastro = false;
        while (!confirmacaoCadastro) {
            //CHAMA METODOS QUE RETORNAM OS DADOS PARA CADASTRO DOS CLIENTES
            System.out.println("=======CADASTRO DE ADMINISTRADOR=======");
            nome = entradaNome();
            email = entradaEmail();
            //CRIA OBJETO ADM COM NOME E EMAIL
            adm = new Administrador(nome, email);
            doc = entradaDoc(adm);
            //INICIALIZA O CPF/CNPJ E, DENTRO DO METODO, DEFINE SE É PESSOA FÍSICA OU JURÍDICA
            adm.setCpfCnpj(doc);
            senha = entradaSenha();
            //INICIALIZA SENHA
            adm.setSenha(senha);
            //CONFIRMA CADASTRO E MOSTRA DADOS
            confirmacaoCadastro = confirmarCadastro(adm);
        }
        return adm;
    }

    private static String entradaNome() {
        //ENTRADA E VALIDACAO DE NOME PARA CADASTRO
        String nome;
        System.out.print("Nome: ");
        nome = Entrada.scanner.nextLine();
        return nome;
    }

    private static String entradaEmail() {
        String email = null;
        boolean validacaoEmail = false;
        //ENTRADA E VALIDACAO DE EMAIL PARA CADASTRO
        while (!validacaoEmail) {
            System.out.print("Email: ");
            email = Entrada.scanner.nextLine();
            if (Validador.validarEmail(email)) validacaoEmail = true;
            else System.out.println("Email inválido! ");
        }
        return email;
    }

    private static String entradaDoc(Administrador adm) {
        String doc = null;
        boolean validacaoDoc = false;
        //ENTRADA E VALIDACAO DE CPF/CNPJ, E DEFINE SE ADM É PESSOA FISICA OU JURIDICA, PARA CADASTRO
        while (!validacaoDoc) {
            System.out.print("CPF/CNPJ: ");
            doc = Entrada.scanner.nextLine();
            if (Validador.validarDoc(doc, adm)) validacaoDoc = true;
            else System.out.println("CPF/CNPJ inválido!");
        }
        if (adm.isPessoaFisica()) doc = doc.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        if (!adm.isPessoaFisica()) doc = doc.replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
        return doc;
    }

    private static String entradaSenha() {
        String senha = null;
        String confirmacaoSenha;
        boolean validacaoSenha = false;
        boolean validacaoConfirmacaoSenha = false;
        //ENTRADA, VALIDACAO E CONFIRMACAO DE SENHA PARA CADASTRO
        while (!validacaoSenha) {
            System.out.print("Senha: ");
            senha = Entrada.scanner.nextLine();
            if (Validador.validarSenha(senha)) validacaoSenha = true;
            else
                System.out.println("A senha deve conter pelo menos 1 número, 1 caractere especial, 1 letra maiúscula e 8 dígitos!");
        }
        while (!validacaoConfirmacaoSenha) {
            System.out.print("Confirme a senha: ");
            confirmacaoSenha = Entrada.scanner.nextLine();
            if (confirmacaoSenha.equals(senha)) validacaoConfirmacaoSenha = true;
            else System.out.println("As senhas não conferem!");
        }
        return senha;
    }

    private static boolean confirmarCadastro(Administrador adm) {
        byte escolha;
        //IMPRIME E CONFIRMA OS DADOS DO CADASTRO, CASO NEGATIVO, REFAZ O CADASTRO
        System.out.println();
        System.out.println("CADASTRO REALIZADO!");
        System.out.println("Nome: " + adm.getNome());
        if (adm.isPessoaFisica()) {
            System.out.println("CPF: " + adm.getCpfCnpj());
        } else System.out.println("CNPJ: " + adm.getCpfCnpj());
        System.out.println("Email: " + adm.getEmail());
        System.out.println("Confirma os dados: ");
        System.out.println("1 - Sim, os dados estão corretos");
        System.out.println("0 - Não, os dados NÃO estão corretos");
        System.out.print("Opção: ");
        escolha = Validador.validarEscolha((byte) 1);
        Entrada.scanner.nextLine();
        if (escolha == 0) return false;
        else return true;
    }

    public static void exibirMenu(Administrador adm) {
        byte escolha;
        boolean manterMenu = true;
        //Exibe menu principal -> necessita de senha para acessar configurações de adm
        while(manterMenu) {
            System.out.println("=======MENU-PRINCIPAL=======");
            System.out.println("1 - Verificar logs");
            System.out.println("2 - Configurações de usuário");
            System.out.println("0 - Encerrar");
            System.out.print("Opção: ");
            escolha = Validador.validarEscolha((byte) 2);
            switch (escolha){
                case 1 -> MenuLogs.exibirMenu();
                case 2 -> {
                    String tentarSenha;
                    boolean tentativa = true;
                    Entrada.scanner.nextLine();
                    while(tentativa) {
                        System.out.println("Insira sua senha para acessar (Digite 0 para cancelar): ");
                        tentarSenha = Entrada.scanner.nextLine();
                        if(tentarSenha.equals("0")) return;
                        if(tentarSenha.equals(adm.getSenha())) tentativa = false;
                        else System.out.println("Senha incorreta!");
                    }
                    MenuConfig.exibirMenu(adm);
                }
                case 0 -> {
                    System.out.println("Encerrando aplicação...");
                    return;
                }
            }

        }
    }
}
