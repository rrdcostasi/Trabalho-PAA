package main;

import utils.*;

public class Main {

    public static void main(String[] args) {
        GeradorDados geraDados = new GeradorDados();
        int[] tamanhos = {10, 50, 100, 500, 1000, 5000, 10000, 11000};
        String[] exemplosVolumetria = new String[tamanhos.length * 2];
        String[] exemplosVolTransp = new String[tamanhos.length * 2];

        //Conjuntos de strings para os testes
        String[] exemplosBasicos = {
                "gasto", "gata",
                "teste","teste",
                "consolacao","sola",
                "ca","ac",
                "abxcdyezf","axbcdyzef"
        };

        //Conjuntos de strings baseados na volumetria de caracteres
        int index = 0;
        for (int tamanho : tamanhos) {
            exemplosVolumetria[index] = geraDados.gerarString(tamanho);
            exemplosVolumetria[index+1] = geraDados.gerarString(tamanho);
            String[] par = geraDados.transp(tamanho);
            exemplosVolTransp[index] = par[0];
            exemplosVolTransp[index+1] = par[1];
            index+=2;
        }

        for (int i = 0; i < exemplosBasicos.length; i+=2) {
            compararAlgoritmos(exemplosBasicos[i], exemplosBasicos[i+1], "basico");
        }

        System.out.printf("\nVOLUMETRIA:\n");
        for (int i = 0; i < exemplosVolumetria.length; i+=2) {
            compararAlgoritmos(exemplosVolumetria[i], exemplosVolumetria[i+1],"volumetria");
        }

        System.out.printf("\nVOLUMETRIA TRANSPOSIÇÃO:\n");
        for (int i = 0; i < exemplosVolTransp.length; i+=2) {
            compararAlgoritmos(exemplosVolTransp[i], exemplosVolTransp[i+1],"volumetria");
        }
    }

    public static void compararAlgoritmos(String seqA, String seqB,String tipo) {
        DistanciaEdLevenshtein lev = new DistanciaEdLevenshtein();
        DistanciaEdicao damerau = new DistanciaEdicao();
        Impressoes impressoes = new Impressoes();

        //Contagem dos tempos e execução
        long inicioLev = System.nanoTime();
        Resultado resultadoLev = lev.calcularDistancia(seqA, seqB);
        long fimLev = System.nanoTime();
        double tempoLev = (fimLev - inicioLev)/1000000.0;

        long inicioDam = System.nanoTime();
        Resultado resultadoDam = damerau.calcularDistancia(seqA, seqB);
        long fimDam = System.nanoTime();
        double tempoDam = (fimDam - inicioDam)/1000000.0;

        impressoes.comparacoesLev(seqA,seqB,resultadoLev,tempoLev,tipo);
        impressoes.comparacoesDam(seqA,seqB,resultadoDam,tempoDam,tipo);
    }

}