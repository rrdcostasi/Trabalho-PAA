package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Impressoes {

    // Imprime a matriz de distância Levenshtein (normal, tamanho m+1 x n+1)
    private void imprimirMatriz(int[][] matriz, String a, String b) {
        // Cabeçalho
        System.out.print("   λ ");
        for (int j = 0; j < b.length(); j++) {
            System.out.print(" " + b.charAt(j) + " ");
        }
        System.out.println();

        // Linhas
        for (int i = 0; i <= a.length(); i++) {
            if (i == 0)
                System.out.print("λ ");
            else
                System.out.print(a.charAt(i - 1) + " ");

            for (int j = 0; j <= b.length(); j++) {
                System.out.print(String.format("%2d ", matriz[i][j]));
            }
            System.out.println();
        }
    }

    // Imprime a matriz de distância Damerau (deslocada, tamanho m+2 x n+2)
    private void imprimirMatrizDamerau(int[][] matriz, String a, String b) {
        // Cabeçalho (coluna 0 = λ)
        System.out.print("   λ ");
        for (int j = 0; j < b.length(); j++) {
            System.out.print(" " + b.charAt(j) + " ");
        }
        System.out.println();

        // Linhas (linha 0 = λ)
        for (int i = 1; i <= a.length() + 1; i++) {
            if (i == 1)
                System.out.print("λ ");
            else
                System.out.print(a.charAt(i - 2) + " ");

            // Valores da matriz da coluna 1 até n+1
            for (int j = 1; j <= b.length() + 1; j++) {
                System.out.print(String.format("%2d ", matriz[i][j]));
            }
            System.out.println();
        }
    }

    // Imprime as operações para matriz deslocada (Damerau)
    private void imprimirOperacoesDam(String a, String b, String[][] operacoes) {
        int i = a.length() + 1;
        int j = b.length() + 1;

        List<String> caminho = new ArrayList<>();

        while (i > 1 || j > 1) {
            String op = operacoes[i][j];

            switch (op) {
                case "substituição":
                    caminho.add("Substituir '" + a.charAt(i - 2) + "' por '" + b.charAt(j - 2) + "'");
                    i--;
                    j--;
                    break;
                case "inserção":
                    caminho.add("Inserir '" + b.charAt(j - 2) + "'");
                    j--;
                    break;
                case "deleção":
                    caminho.add("Remover '" + a.charAt(i - 2) + "'");
                    i--;
                    break;
                case "nenhuma":
                    i--;
                    j--;
                    break;
                case "transposição":
                    caminho.add("Trocar '" + a.charAt(i - 3) + "' com '" + a.charAt(i - 2) + "'");
                    i -= 2;
                    j -= 2;
                    break;
                default:
                    i--;
                    j--;
                    break;
            }
        }

        for (int k = caminho.size() - 1; k >= 0; k--) {
            System.out.println("- " + caminho.get(k));
        }
    }

    // Imprime as operações para matriz normal (Levenshtein)
    private void imprimirOperacoesLev(String a, String b, String[][] operacoes) {
        int i = a.length();
        int j = b.length();

        List<String> caminho = new ArrayList<>();

        while (i > 0 || j > 0) {
            String op = operacoes[i][j];

            switch (op) {
                case "substituição":
                    caminho.add("Substituir '" + a.charAt(i - 1) + "' por '" + b.charAt(j - 1) + "'");
                    i--;
                    j--;
                    break;
                case "inserção":
                    caminho.add("Inserir '" + b.charAt(j - 1) + "'");
                    j--;
                    break;
                case "deleção":
                    caminho.add("Remover '" + a.charAt(i - 1) + "'");
                    i--;
                    break;
                case "nenhuma":
                    i--;
                    j--;
                    break;
                default:
                    i--;
                    j--;
                    break;
            }
        }

        for (int k = caminho.size() - 1; k >= 0; k--) {
            System.out.println("- " + caminho.get(k));
        }
    }

    // Imprime matriz e operações Levenshtein
    public void imprimirMatOpLev(Resultado dist, String seqA, String seqB) {
        System.out.println("Matriz - Levenshtein:\n");
        imprimirMatriz(dist.matrizEd, seqA, seqB);
        System.out.println("\nDistância: " + dist.distancia);
        System.out.println("\nOperações necessárias: ");
        imprimirOperacoesLev(seqA, seqB, dist.operacoes);
    }

    // Imprime matriz e operações Damerau-Levenshtein
    public void imprimirMatOpDistEd(Resultado dist, String seqA, String seqB) {
        System.out.println("Matriz - Damerau:\n");
        imprimirMatrizDamerau(dist.matrizEd, seqA, seqB);
        System.out.println("\nDistância: " + dist.distancia);
        System.out.println("\nOperações necessárias: ");
        imprimirOperacoesDam(seqA, seqB, dist.operacoes);
    }

    // Comparações simplificadas
    public void comparacoesLev(String seqA, String seqB, Resultado dist, double tempo, String tipo) {
        if (!Objects.equals(tipo, "volumetria")) {
            System.out.println("Comparando \"" + seqA + "\" e \"" + seqB + "\":");
            System.out.println("Levenshtein:");
            imprimirMatOpLev(dist, seqA, seqB);
            System.out.println("  Tempo: " + tempo + " ms");
        } else {
            System.out.println("Comparando string de " + seqA.length() + " caracteres");
            System.out.println("Levenshtein:");
            System.out.println("  Distância: " + dist.distancia);
            System.out.println("  Tempo: " + tempo + " ms");
        }
    }

    public void comparacoesDam(String seqA, String seqB, Resultado dist, double tempo, String tipo) {
        if (!Objects.equals(tipo, "volumetria")) {
            System.out.println("\nDamerau-Levenshtein:");
            imprimirMatOpDistEd(dist, seqA, seqB);
            System.out.println("  Tempo: " + tempo + " ms");
            System.out.println("_____________________________________________________________");
        } else {
            System.out.println("\nDamerau-Levenshtein:");
            System.out.println("  Distância: " + dist.distancia);
            System.out.println("  Tempo: " + tempo + " ms");
            System.out.println("_____________________________________________________________");
        }
    }
}
