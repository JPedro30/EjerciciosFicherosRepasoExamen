package ejercicioExamen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

public class Ley {

    private final File ARCHIVO = new File("leyd.txt");
    private int numEsc = 0;
    private int numPar = 0;
    private String[] nomPar;
    private int[] votPar;
    private double[][] matVotos;
    private int[] escPar;

    public Ley() {

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String dato = br.readLine();
            numEsc = Integer.parseInt(dato);
            dato = br.readLine();
            numPar = Integer.parseInt(dato);
            dato = br.readLine();
            nomPar = new String[numPar];
            nomPar = dato.split(",");
            dato = br.readLine();
            String[] votParStr = dato.split(",");
            votPar = new int[numPar];
            for (int i = 0; i < votParStr.length; i++) {
                votPar[i] = Integer.parseInt(votParStr[i].trim());
            }
            matVotos = new double[numPar][numEsc];
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < votPar.length; i++) {
            for (int j = 0; j < numEsc; j++) {
                matVotos[i][j] = (double) votPar[i] / (j + 1);
            }
        }

        System.out.print("             ");
        for (int i = 0; i < numEsc; i++) {
            System.out.printf("%-10d", (i + 1));
        }
        System.out.println();
        for (int i = 0; i < votPar.length; i++) {
            System.out.printf("%-7s", nomPar[i]);
            for (int j = 0; j < numEsc; j++) {
                System.out.printf("%10.2f", matVotos[i][j]);
            }
            System.out.println();
        }

        escPar = new int[numPar];
        escPar = reparteEsc();

        for (int i = 0; i < escPar.length; i++) {
            System.out.println(nomPar[i] + " tiene: " + escPar[i] + " escaños");
        }

    }

    private int[] reparteEsc() {

        int[] escRepartidos = new int[numPar];
        
        for (int e = 0; e < numEsc; e++) {

        double maxVot = -1;
        int parGan = -1;
        int divGan = -1;

            for (int i = 0; i < matVotos.length; i++) {

                for (int j = 0; j < matVotos[i].length; j++) {

                    if (matVotos[i][j] > maxVot) {

                        maxVot = matVotos[i][j];
                        parGan = i;
                        divGan = j;

                    } else if (matVotos[i][j] == maxVot && votPar[i] > votPar[parGan]) {

                        parGan = i;
                        divGan = j;

                    } else if (matVotos[i][j] == maxVot && votPar[i] == votPar[parGan] && Math.random() > 0.5) {

                        parGan = i;
                        divGan = j;

                    }
                }
            }

            matVotos[parGan][divGan] = 0;
            escRepartidos[parGan]++;

        }
        return escRepartidos;
    }

}
