package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Impressoes {
    private void imprimirMatriz(int[][] matriz, String a, String b) {
        System.out.print("  λ  ");
        for (int j = 0; j < b.length(); j++) {
            System.out.print(b.charAt(j) + "  ");
        }
        System.out.println();

        for (int i = 0; i < matriz.length; i++) {
            if (i == 0) {
                System.out.print("λ ");
            } else {
                System.out.print(a.charAt(i - 1) + " ");
            }

            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + "  ");
            }
            System.out.println();
        }
    }

    private void imprimirOperacoes(String a, String b, String[][] operacoes) {
        int i = operacoes.length - 1;
        int j = operacoes[0].length - 1;

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
                    caminho.add("Inserir '" + b.charAt(j - 1) + "' na posição " + i);
                    j--;
                    break;
                case "deleção":
                    caminho.add("Remover '" + a.charAt(i - 1) + "' da posição " + (i - 1));
                    i--;
                    break;
                case "nenhuma":
                    i--;
                    j--;
                    break;
                case "transposição":
                    char c1 = a.charAt(i - 2);  // caractere antes
                    char c2 = a.charAt(i - 1);  // caractere atual
                    caminho.add("Trocar '" + c1 + "' com '" + c2 + "'");
                    i -= 2;
                    j -= 2;
                    break;
            }
        }

        // Imprimir em ordem reversa (do início até o fim)
        for (int k = caminho.size() - 1; k >= 0; k--) {
            System.out.println("- " + caminho.get(k));
        }
    }

    public void imprimirMatOpLev(Resultado dist, String seqA, String seqB){
        System.out.println("Matriz - Levenshtein:\n");
        imprimirMatriz(dist.matrizEd, seqA, seqB);
        System.out.println("\nDistância: " + dist.distancia );
        System.out.println("\nOperações necessárias: ");
        imprimirOperacoes(seqA,seqB,dist.operacoes);
    }

    public void imprimirMatOpDistEd(Resultado dist, String seqA, String seqB){
        System.out.println("Matriz - Damerau:\n");
        imprimirMatriz(dist.matrizEd, seqA, seqB);
        System.out.println("\nDistância: " + dist.distancia );
        System.out.println("\nOperações necessárias: ");
        imprimirOperacoes(seqA,seqB,dist.operacoes);
    }

    public void comparacoesLev(String seqA,String seqB, Resultado dist, double tempo,String tipo){
        if(!Objects.equals(tipo, "volumetria")){
            System.out.println("Comparando \"" + seqA + "\" e \"" + seqB + "\":");
            System.out.println("Levenshtein:");
            imprimirMatOpLev(dist,seqA,seqB);
            System.out.println("  Tempo: " + tempo + " ms");
        }
        else{
            System.out.println("Comparando string de "+seqA.length()+" caracteres");
            System.out.println("Levenshtein:");
            System.out.println("  Distância: " + dist.distancia);
            System.out.println("  Tempo: " + tempo + " ms");
        }

    }

    public void comparacoesDam( String seqA,String seqB, Resultado dist, double tempo,String tipo){
        if(!Objects.equals(tipo, "volumetria")){
            System.out.println("\nDamerau-Levenshtein:");
            imprimirMatOpDistEd(dist,seqA,seqB);
            System.out.println("  Tempo: " + tempo + " ms");
            System.out.println("_____________________________________________________________");
        }
        else{
            System.out.println("\nDamerau-Levenshtein:");
            System.out.println("  Distância: " + dist.distancia);
            System.out.println("  Tempo: " + tempo + " ms");
            System.out.println("_____________________________________________________________");
        }
    }
}
