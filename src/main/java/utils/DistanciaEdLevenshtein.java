package utils;

public class DistanciaEdLevenshtein {

    //Algoritmo Levenshtein
    public Resultado calcularDistancia(String a, String b) {
        Resultado resultado = new Resultado();
        int tamanhoA = a.length();
        int tamanhoB = b.length();
        int[][] matrizEdicao = new int[tamanhoA + 1][tamanhoB + 1];
        String[][] operacoes = new String[tamanhoA + 1][tamanhoB + 1];

        for (int i = 0; i <= tamanhoA; i++) {
            matrizEdicao[i][0] = i;
            operacoes[i][0] = "deleção";
        }

        for (int j = 0; j <= tamanhoB; j++) {
            matrizEdicao[0][j] = j;
            operacoes[0][j] = "inserção";
        }

        for (int i = 1; i <= tamanhoA; i++) {
            for (int j = 1; j <= tamanhoB; j++) {
                char caractereA = a.charAt(i - 1);
                char caractereB = b.charAt(j - 1);

                int custoSubstituicao = (caractereA == caractereB) ? 0 : 1;

                int custoDelecao = matrizEdicao[i - 1][j] + 1;
                int custoInsercao = matrizEdicao[i][j - 1] + 1;
                int custoSubstituir = matrizEdicao[i - 1][j - 1] + custoSubstituicao;
                int menorCusto = Math.min(custoDelecao, Math.min(custoInsercao, custoSubstituir));
                String operacaoEscolhida;

                // Atribui a operação com base no menor custo
                if (menorCusto == custoSubstituir) {
                    operacaoEscolhida = (custoSubstituicao == 0) ? "nenhuma" : "substituição";
                } else if (menorCusto == custoInsercao) {
                    operacaoEscolhida = "inserção";
                } else  {
                    operacaoEscolhida = "deleção";
                }

                // Atualiza a célula da matriz
                matrizEdicao[i][j] = menorCusto;
                operacoes[i][j] = operacaoEscolhida;
            }
        }

        resultado.matrizEd = matrizEdicao;
        resultado.distancia = matrizEdicao[tamanhoA][tamanhoB]; //última celula da matriz
        resultado.operacoes = operacoes;

        return resultado;
    }
}
