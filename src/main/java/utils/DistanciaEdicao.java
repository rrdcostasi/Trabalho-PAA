package utils;

import java.util.HashMap;
import java.util.Map;

public class DistanciaEdicao {

    public Resultado calcularDistancia(String a, String b) {
        Resultado resultado = new Resultado();
        int tamanhoA = a.length();
        int tamanhoB = b.length();

        int maxDist = tamanhoA + tamanhoB;

        int[][] matrizEdicao = new int[tamanhoA + 2][tamanhoB + 2];
        String[][] operacoes = new String[tamanhoA + 2][tamanhoB + 2];

        // Mapa para última ocorrência de cada caractere na string 'a'
        Map<Character, Integer> ultimaOcorrencia = new HashMap<>();

        // Inicialização da matriz
        matrizEdicao[0][0] = maxDist;
        for (int i = 0; i <= tamanhoA; i++) {
            matrizEdicao[i + 1][1] = i;
            matrizEdicao[i + 1][0] = maxDist;
            operacoes[i + 1][1] = (i == 0) ? "nenhuma" : "deleção";
        }
        for (int j = 0; j <= tamanhoB; j++) {
            matrizEdicao[1][j + 1] = j;
            matrizEdicao[0][j + 1] = maxDist;
            operacoes[1][j + 1] = (j == 0) ? "nenhuma" : "inserção";
        }

        for (int i = 1; i <= tamanhoA; i++) {
            char caractereA = a.charAt(i - 1);
            int ultimoB = 0;

            for (int j = 1; j <= tamanhoB; j++) {
                char caractereB = b.charAt(j - 1);

                int i1 = ultimaOcorrencia.getOrDefault(caractereB, 0);
                int j1 = ultimoB;

                int custoSubstituicao = (caractereA == caractereB) ? 0 : 1;

                if (custoSubstituicao == 0) {
                    ultimoB = j;
                }

                int del = matrizEdicao[i][j + 1] + 1;
                int ins = matrizEdicao[i + 1][j] + 1;
                int sub = matrizEdicao[i][j] + custoSubstituicao;
                int transp = matrizEdicao[i1][j1] + (i - i1 - 1) + 1 + (j - j1 - 1);

                int menorCusto = sub;
                String operacaoEscolhida = (custoSubstituicao == 0) ? "nenhuma" : "substituição";

                if (ins < menorCusto) {
                    menorCusto = ins;
                    operacaoEscolhida = "inserção";
                }

                if (del < menorCusto) {
                    menorCusto = del;
                    operacaoEscolhida = "deleção";
                }

                if (transp < menorCusto) {
                    menorCusto = transp;
                    operacaoEscolhida = "transposição";
                }

                matrizEdicao[i + 1][j + 1] = menorCusto;
                operacoes[i + 1][j + 1] = operacaoEscolhida;
            }

            ultimaOcorrencia.put(caractereA, i);
        }

        resultado.matrizEd = matrizEdicao;
        resultado.distancia = matrizEdicao[tamanhoA + 1][tamanhoB + 1];
        resultado.operacoes = operacoes;

        return resultado;
    }
}
