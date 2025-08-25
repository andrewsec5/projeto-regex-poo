package projetoRegex.menus;

import projetoRegex.ferramentas.Validador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MenuLogs implements Menu{

    private static final String CAMINHO_LOG = "src/projetoRegex/resources/logs_sistema.csv";

    public static void exibirMenu() {
        byte escolha;
        boolean manterMenu = true;

        while(manterMenu) {
            System.out.println("=======MENU-LOGS=======");
            System.out.println("1 - Imprimir logs (24h)");
            System.out.println("2 - Verificar categoria específica");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            escolha = Validador.validarEscolha((byte) 2);
            switch (escolha) {
                case 1 -> imprimirLogs();
                case 2 -> separarCategoria();
                case 0 -> {
                    return;
                }
            }
        }
    }

    private static void imprimirLogs() {
        System.out.println("-------Logs do dia 21/08/2025-------");
        try (BufferedReader buffRead = new BufferedReader(new FileReader(CAMINHO_LOG))) {
            String linha;
            while ((linha = buffRead.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }

    private static void separarCategoria() {
        Map<String, List<String>> mapa = new HashMap<>();

        // Regex para lidar com vírgulas dentro de aspas
        String regexCSV = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";

        try (BufferedReader buffRead = new BufferedReader(new FileReader(CAMINHO_LOG))) {
            String cabecalho = buffRead.readLine(); // pula cabeçalho
            if (cabecalho == null) {
                System.out.println("Arquivo CSV vazio.");
                return;
            }
            String linha;
            while ((linha = buffRead.readLine()) != null) {
                String[] partes = linha.split(regexCSV, -1);
                String categoria = partes[4];

                mapa.putIfAbsent(categoria, new ArrayList<>());
                mapa.get(categoria).add(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
            return;
        }
        // Imprime categorias, ordenadas por data e hora, e estatísticas
        for (String categoria : mapa.keySet()) {
            List<String> logs = mapa.get(categoria);
            // Ordena logs pela data e hora
            logs.sort((linha1, linha2) -> {
                String[] colunas1 = linha1.split(regexCSV, -1);
                String[] colunas2 = linha2.split(regexCSV, -1);
                return colunas1[0].compareTo(colunas2[0]);
            });
            System.out.println("\n--- " + categoria + " ---");
            logs.forEach(System.out::println);
            System.out.println("Total de logs nesta categoria: " + logs.size());
        }
        System.out.println();
    }

}
