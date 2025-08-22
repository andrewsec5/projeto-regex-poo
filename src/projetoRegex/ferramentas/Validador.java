package projetoRegex.ferramentas;

import projetoRegex.administrador.Administrador;

public class Validador {

    public static boolean validarEmail(String email){
        if(email.matches("\\w+@\\w+\\.(com|net|org|pt|edu|gov)(.br)?"))return true;
        else return false;
    }

    public static boolean validarDoc(String doc, Administrador adm){
        if(doc.matches("\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}")){
            adm.setPessoaFisica(true);
            return true;
        }
        if(doc.matches("\\d{2}\\.?\\d{3}\\.?\\d{3}/?\\d{4}-?\\d{2}")){
            adm.setPessoaFisica(false);
            return true;
        }
    return false;
    }

    public static boolean validarSenha(String senha){
    //SENHA FORTE
    }

}
