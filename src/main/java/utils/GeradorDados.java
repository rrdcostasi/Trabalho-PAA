package utils;

import java.util.Random;

public class GeradorDados {
    //Gera as strings usadas nos testes de volumetria
    public String gerarString(int tamanho) {
        String alfabeto = "abcd"; // ou outro que desejar
        String palavra;
        StringBuilder sb = new StringBuilder();
        Random randomico = new Random();
        for (int i = 0; i < tamanho; i++) {
            int index = randomico.nextInt(alfabeto.length());
            sb.append(alfabeto.charAt(index));
        }
        palavra = sb.toString();
        return palavra;
    }
}
