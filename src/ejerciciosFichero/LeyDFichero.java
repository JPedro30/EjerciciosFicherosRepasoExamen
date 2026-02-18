package ejerciciosFichero;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class LeyDFichero {

    private final String archivo = "leyd.txt";
    String[] partidos;
    String[] votosStr;
    int[] votos;

    public LeyDFichero(){

        String dato = "";
        int esca = 0;
        int numPar = 0;
        String partidosStr = "";
        String votosString = "";
        

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            dato = br.readLine();
            esca = Integer.parseInt(dato);
            dato = br.readLine();
            numPar = Integer.parseInt(dato);
            dato = br.readLine();
            partidosStr = dato;
            dato = br.readLine();
            votosString = dato;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        partidos = new String[numPar];
        votosStr = new String[numPar];
        votos = new int[numPar];

        partidos = partidosStr.split(",");
        votosStr = votosString.split(",");

        for (int x = 0; x < votosStr.length; x++) {
            votos[x] = Integer.parseInt(votosStr[x]);
        }

        double[][] matrizVotos = new double[numPar][esca];

        for (int c = 0; c < matrizVotos.length; c++) {
            for (int f = 0; f < matrizVotos[0].length; f++) {
                matrizVotos[c][f] = (double) votos[c] / (f+1);
            }
        }

        
        System.out.print("        ");
        for (int i = 0; i < matrizVotos[0].length; i++) {
            System.out.printf("%-10d",(i+1));
        }
        System.out.println();
        for (int c = 0; c < matrizVotos.length; c++) {
            System.out.print(partidos[c]);
            for (int f = 0; f < matrizVotos[0].length; f++) {
                System.out.printf("%10.2f",matrizVotos[c][f]);
            }
            System.out.println();
        }

        int[] escaRepartidos = reparteEsca(matrizVotos,esca);

        System.out.println(Arrays.toString(escaRepartidos));

    }

    private int[] reparteEsca(double[][] matrizVotos, int esca) {
        int[] escaRepartidos = new int[matrizVotos.length];

        for (int x = 0; x < esca; x++) {
            double max = -1;
            int par = -1;
            int div = -1;
            for (int c = 0; c < matrizVotos.length; c++) {
                for (int f = 0; f < matrizVotos[c].length; f++) {
                    if (matrizVotos[c][f]>max) {
                        max = matrizVotos[c][f];
                        par = c;
                        div = f;
                    } else if (matrizVotos[c][f]==max && votos[c]>votos[par]) {
                        par = c;
                        div = f;
                    } else if (matrizVotos[c][f]==max && votos[c]==votos[par] && Math.random()<0.5) {
                        par = c;
                        div = f;
                    }
                    
                }
            }

            escaRepartidos[par]++;
            matrizVotos[par][div] = 0;

        }

        return escaRepartidos;

    }

}
