package main;

import utils.*;

public class Main {

    public static void main(String[] args) {
        GeradorDados geraDados = new GeradorDados();

        //Conjuntos de strings para os testes
        String[] exemplosBasicos = {
                "gasto", "gata",
                "teste","teste",
                "consolacao","sola",
                "ca","ac",
                "abxcdyezf","axbcdyzef"
        };

        //Conjuntos de strings baseados na volumetria de caracteres
        String[] exemplosVolumetria = {
                geraDados.gerarString(10),geraDados.gerarString(10),
                geraDados.gerarString(50),geraDados.gerarString(50),
                geraDados.gerarString(100),geraDados.gerarString(100),
                geraDados.gerarString(500),geraDados.gerarString(500),
                geraDados.gerarString(1000),geraDados.gerarString(1000),
                geraDados.gerarString(5000),geraDados.gerarString(5000),
                geraDados.gerarString(10000),geraDados.gerarString(10000),
                geraDados.gerarString(11000),geraDados.gerarString(11000)
        };

        for (int i = 0; i < exemplosBasicos.length; i+=2) {
            compararAlgoritmos(exemplosBasicos[i], exemplosBasicos[i+1], "basico");
        }

        for (int i = 0; i < exemplosVolumetria.length; i+=2) {
            compararAlgoritmos(exemplosVolumetria[i], exemplosVolumetria[i+1],"volumetria");
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