package projetoRegex.menus;

import projetoRegex.administrador.Administrador;
import projetoRegex.ferramentas.Entrada;
import projetoRegex.ferramentas.Validador;

import java.util.Objects;

public class MenuConfig implements Menu {

    public static void exibirMenu(Administrador adm) {
        byte escolha;
        boolean manterMenu = true;

        while (manterMenu) {
            System.out.println("=======CONFIGURAÇÕES DE ADMINISTRADOR=======");
            if (Objects.isNull(adm.getTelefone()) || adm.getTelefone().isBlank()) System.out.println("1 - Cadastrar número de telefone");
            else System.out.println("1 - Alterar número de telefone");
            if (Objects.isNull(adm.getDataNascimentoFundacao()) || adm.getDataNascimentoFundacao().isBlank()) {
                if (adm.isPessoaFisica()) System.out.println("2 - Cadastrar data de nascimento");
                if (!adm.isPessoaFisica()) System.out.println("2 - Cadastrar data de fundação");
            } else {
                if (adm.isPessoaFisica()) System.out.println("2 - Alterar data de nascimento");
                if (!adm.isPessoaFisica()) System.out.println("2 - Alterar data de fundação");
            }
            System.out.println("3 - Alterar senha");
            if (adm.isPessoaFisica()) System.out.println("4 - Alterar CPF");
            else System.out.println("4 - Alterar CNPJ");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            escolha = Validador.validarEscolha((byte) 4);
            Entrada.scanner.nextLine();

            switch (escolha) {
                case 1 -> alterarNumero(adm);
                case 2 -> alterarData(adm);
                case 3 -> alterarSenha(adm);
                case 4 -> alterarDoc(adm);
                case 0 -> {
                    return;
                }
            }
        }
    }

    private static void alterarNumero(Administrador adm){
        String numero;
        boolean validacaoNumero = false;

            if (Objects.isNull(adm.getTelefone()) || adm.getTelefone().isBlank()) System.out.println("Sem número de telefone cadastrado");
            else System.out.println("Número cadastrado: " + adm.getTelefone());
        while(!validacaoNumero) {
            System.out.print("Digite o número de telefone novo (Digite 0 para cancelar): ");
            numero = Entrada.scanner.nextLine();
            if (numero.equals("0")) return;
            validacaoNumero = Validador.validarTelefone(numero);
            if(!validacaoNumero) System.out.println("\nNúmero inválido!");
            else adm.setTelefone(numero);
        }
    }

    private static void alterarData(Administrador adm){
        String data;
        boolean validacaoData = false;

        while(!validacaoData){
            if(adm.isPessoaFisica()){
                if(Objects.isNull(adm.getDataNascimentoFundacao()) || adm.getDataNascimentoFundacao().isBlank()) System.out.println("Nenhuma data de nascimento cadastrada");
                else System.out.println("Data de nascimento cadastrada: " + adm.getDataNascimentoFundacao());
                System.out.print("Digite a data de nascimento nova (Digite 0 para cancelar): ");
            }
            else{
                if(Objects.isNull(adm.getDataNascimentoFundacao()) || adm.getDataNascimentoFundacao().isBlank()) System.out.println("Nenhuma data de fundação cadastrada");
                else System.out.println("Data de fundação cadastrada: " + adm.getDataNascimentoFundacao());
                System.out.print("Digite a data de fundaçao nova (Digite 0 para cancelar): ");
            }
            data = Entrada.scanner.nextLine();
            if(data.equals("0")) return;
            validacaoData = Validador.validarData(data);
            if(!validacaoData) System.out.println("\nData inválida!");
            else adm.setDataNascimentoFundacao(data);
        }
    }

    private static void alterarSenha(Administrador adm){
        String doc;
        String confirmacaoSenha;
        String novaSenha = null;
        boolean validacaoSenhaNova = false;
        boolean validacaoDoc = false;
        boolean validacaoSenha = false;

        while(!validacaoDoc) {
            if (adm.isPessoaFisica())
                System.out.print("Confirme os 3 primeiros dígitos do seu CPF (Digite 0 para cancelar): ");
            else System.out.println("Confirme os 3 primeiros dígitos do seu CNPJ (Digite 0 para cancelar): ");
            doc = Entrada.scanner.nextLine();
            if(doc.equals("0")) return;
            if(doc.matches(adm.getCpfCnpj().substring(0, 3))){
                validacaoDoc = true;
            }else System.out.println("\nValidação inválida!");
        }
        while(!validacaoSenha) {
            System.out.println("Confirme novamente sua senha atual (Digite 0 para cancelar): ");
            confirmacaoSenha = Entrada.scanner.nextLine();
            if(confirmacaoSenha.equals("0")) return;
            if(confirmacaoSenha.matches(adm.getSenha())) validacaoSenha = true;
            else System.out.println("Senha incorreta!");
        }
        while(!validacaoSenhaNova) {
            System.out.println("Digite sua nova senha (Digite 0 para cancelar): ");
            novaSenha = Entrada.scanner.nextLine();
            if(novaSenha.equals("0")) return;
            validacaoSenhaNova = Validador.validarSenha(novaSenha);
            if(!validacaoSenhaNova) System.out.println("Senha deve possuir uma letra maiúscula, um caractere especial, um número e pelo menos 8 dígitos.");
        }
        adm.setSenha(novaSenha);
    }

    private static void alterarDoc(Administrador adm){
        String doc;
        String novoDoc;
        boolean validacaoDoc = false;
        boolean validacaoDocNovo = false;

        while(!validacaoDoc) {
            if (adm.isPessoaFisica())
                System.out.print("Confirme os 3 primeiros dígitos do seu CPF (Digite 0 para cancelar): ");
            else System.out.println("Confirme os 3 primeiros dígitos do seu CNPJ (Digite 0 para cancelar): ");
            doc = Entrada.scanner.nextLine();
            if(doc.equals("0")) return;
            if(doc.matches(adm.getCpfCnpj().substring(0, 3))){
                validacaoDoc = true;
            }else System.out.println("Validação inválida!");
        }
        while(!validacaoDocNovo) {
            if (adm.isPessoaFisica()) {
                System.out.println("CPF atual: " + adm.getCpfCnpj());
                System.out.print("Insira o novo CPF: ");
            } else {
                System.out.println("CNPJ atual: " + adm.getCpfCnpj());
                System.out.println("Insira o novo CNPJ: ");
            }
            novoDoc = Entrada.scanner.nextLine();
            if (novoDoc.equals("0")) return;
            validacaoDocNovo = Validador.validarDoc(novoDoc, adm);
            if(!validacaoDocNovo) System.out.println("Documento inválido!");
        }
    }

}
