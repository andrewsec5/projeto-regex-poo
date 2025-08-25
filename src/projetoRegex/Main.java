package projetoRegex;

import projetoRegex.administrador.Administrador;
import projetoRegex.menus.MenuInicial;

public class Main {
    public static void main(String[] args) {

        Administrador adm = MenuInicial.cadastrarAdm();

        MenuInicial.exibirMenu(adm);

    }
}
