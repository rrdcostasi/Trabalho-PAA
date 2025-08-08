package utils;

import java.util.Random;

public class GeradorDados {
    //Gera as strings usadas nos testes de volumetria
    public String gerarString(int tamanho) {
        String alfabeto = "abcd"; // Pode ser alterado
        StringBuilder sb = new StringBuilder();
        Random randomico = new Random();
        char ultimoChar = '\0'; // Valor inicial que não pertence ao alfabeto

        for (int i = 0; i < tamanho; i++) {
            char novoChar;
            int tentativas = 0;
            do {
                int index = randomico.nextInt(alfabeto.length());
                novoChar = alfabeto.charAt(index);
                tentativas++;
            } while (novoChar == ultimoChar && tentativas < 10); // tenta evitar repetição

            sb.append(novoChar);
            ultimoChar = novoChar;
        }

        return sb.toString();
    }

    private String geraTransp (String a){
        int tamanho=a.length();
        char[] letras = a.toCharArray();

        for(int i = 0; i<tamanho;i+=3){
            char temp = letras[i];
            if(i+1<tamanho) {
                letras[i] = letras[i + 1];
                letras[i + 1] = temp;
            }
        }
        return new String(letras);
    }

    public String [] transp(int tamanho){
        String a = gerarString(tamanho);
        String b = geraTransp(a);

        return new String[]{a,b};
    }
}
