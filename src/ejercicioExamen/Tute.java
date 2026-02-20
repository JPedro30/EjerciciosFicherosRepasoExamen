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
    private String[][] jugadoresJugadas;
    private String[] palos = { "E", "B", "C", "O" };

    public Tute() {

        // Uso try-with-resources para que el archivo se cierre solo pase lo que pase
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {

            numJug = Integer.parseInt(br.readLine());

            for (int i = 1; i <= numJug; i++) {
                System.out.println("PARTIDA " + i);
                jugadoresJugadas = new String[4][2];

                // 1. Lectura de jugadores y sus cartas
                for (int a = 0; a < 4; a++) {
                    // Suponiendo formato "Nombre:1C 2B 11E 12E..."
                    jugadoresJugadas[a] = br.readLine().split(":");
                }

                paloGan = br.readLine().trim();
                System.out.println("Triunfo: " + paloGan);

                // 2. Comprobación de cantes
                for (int a = 0; a < 4; a++) {
                    String nombre = jugadoresJugadas[a][0];
                    String cartas = jugadoresJugadas[a][1];
                    boolean canta = false;

                    for (String p : palos) {

                        boolean tieneCaballo = cartas.contains("11" + p);
                        boolean tieneRey = cartas.contains("12" + p);
                        

                        if (tieneCaballo && tieneRey) {
                            if (p.equals(paloGan)) {
                                System.out.println(nombre + " canta LAS CUARENTA (40 de " + p + ")");
                                canta = true;
                            } else {
                                System.out.println(nombre + " canta VEINTE en " + p);
                                canta = true;
                            }
                        }
                        
                            
                    }
                    if (!canta) {
                            System.out.println(nombre + " no canta nada");
                    }
                }
                System.out.println();
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}