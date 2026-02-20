package ejercicioExamen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Tute {

    private final File ARCHIVO = new File("tute.txt");
    private int numJug = 0;
    private String paloGan = "";
    private String[] jugadasMat;
    private String[] jugadasArray;
    int[] cantaPalos = new int[4];
    String[][] palos = { { "11O", "12O" }, { "11E", "12E" }, { "11C", "12C" }, { "11B", "12B" } };
    String[] pal = { "O", "E", "C", "B" };

    public Tute() throws IOException {

        String linea = "";

        BufferedReader br = new BufferedReader(new FileReader(ARCHIVO));

        linea = br.readLine();
        numJug = Integer.parseInt(linea);
        jugadasMat = new String[4];

        for (int jugadas = 0; jugadas < numJug; jugadas++) {
            for (int jugadores = 0; jugadores < 4; jugadores++) {
                linea = br.readLine();
                jugadasArray = linea.split(":");
                jugadasMat[jugadores] = jugadasArray[1];
            }

            linea = br.readLine();
            paloGan = linea;

            for (int i = 0; i < palos.length; i++) {
                for (int c = 0; c < palos[i].length; c++) {
                    if (jugadasMat[0].indexOf(palos[i][c])>=0) {
                        cantaPalos[i] ++;
                    }
                }
            }

            System.out.println(Arrays.toString(cantaPalos));
        }

    }

    private void canta() {

    }

}
