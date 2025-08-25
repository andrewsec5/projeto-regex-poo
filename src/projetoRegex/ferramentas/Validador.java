package projetoRegex.ferramentas;

import projetoRegex.administrador.Administrador;

import java.util.InputMismatchException;

public class Validador {

    public static boolean validarEmail(String email) {
        String regexEmail = "\\w+@\\w+\\.(com|net|org|pt|edu|gov)(.br)?";
        //VALIDAR EMAIL
        if (email.matches(regexEmail)) return true;
        else return false;
    }

    public static boolean validarDoc(String doc, Administrador adm) {
        //VALIDA CPF/CNPJ E DETERMINA O ADMINISTRADOR COMO PESSOA FISICA OU JURIDICA
        //Valida CPF
        String regexCpf = "\\d{3}(?:\\.?|-?)\\d{3}(?:\\.?|-?)\\d{3}(?:\\.?|-?)\\d{2}";
        if (doc.matches(regexCpf)) {
            adm.setPessoaFisica(true);
            return true;
        }
        //Valida CNPJ
        String regexCnpj = "\\d{2}\\.?\\d{3}\\.?\\d{3}/?\\d{4}-?\\d{2}";
        if (doc.matches(regexCnpj)) {
            adm.setPessoaFisica(false);
            return true;
        }
        return false;
    }

    public static boolean validarSenha(String senha) {
        //VERIFICAR SENHA FORTE
        return senha.matches(".*[A-Z].*")                       //VERIFICA SE POSSUI LETRA MAISCULA
                && senha.matches(".*[!@#$%^&*()\\-/_+=].*")     //VERIFICA SE POSSUI CARACTERE ESPECIAL
                && senha.matches(".*\\d+.*")                    //VERIFICA SE POSSUI NUMERO
                && senha.length() >= 8;                               //VERIFICA SE TEM TAMANHO DE PELO MENOS 8 DIGITOS
    }

    public static byte validarEscolha(byte max) {
        byte escolha = 0;
        boolean validacao = false;
        //VALIDA ESCOLHA DE OPCAO EM MENUS
        while (!validacao) {
            try {
                escolha = Entrada.scanner.nextByte();
                if (escolha < 0 || escolha > max) {
                    System.out.println("Opção inválida!");
                }else validacao = true;
            } catch (InputMismatchException e) {
                Entrada.scanner.nextLine();
                System.out.println("Entrada inválida!");
            }
        }
        return escolha;
    }

    public static boolean validarTelefone(String numero){
        //Valida numero de telefone (celular ou fixo)
        String regexTelefone = "\\(?\\d{2}\\)?\\s?\\d{4,5}[.-]?\\d{4}";
        return numero.matches(regexTelefone);
    }

    public static boolean validarData(String data){
        //Valida formatação da data
        String regexData = "\\d{2}(?:\\.?|/?|-?)\\d{2}(?:\\.?|/?|-?)\\d{4}";
        return data.matches(regexData);
    }

}
