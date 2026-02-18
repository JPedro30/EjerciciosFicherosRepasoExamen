package ejerciciosFichero;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class CuadradoMagico {

    private final String archivo = "matrices.txt";

    public CuadradoMagico() throws IOException{

        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String dato = "";
        int numMatrices = 0;
        int tamañoMatriz = 0;
        String[][] matriz;

        dato = br.readLine();
        numMatrices = Integer.parseInt(dato);

        for (int x = 0; x < numMatrices; x++) {
            dato = br.readLine();
            tamañoMatriz = Integer.parseInt(dato);
            matriz = new String[tamañoMatriz][tamañoMatriz];
            for (int linea = 0; linea < matriz.length; linea++) {
                dato = br.readLine();
                matriz[linea] = dato.split(",");
            }
            boolean esMagico = compruebaMagico(matriz);
            if (esMagico) {
                System.out.println("La matriz numero: "+(x+1)+" es un cuadrado magico");
            } else System.out.println("La matriz numero: "+(x+1)+" no es un cuadrado magico");
        }

        br.close();
        br = null;

    }

    private boolean compruebaMagico(String[][] matriz) {
        int[][] matrizInt = new int[matriz.length][matriz.length];
        for (int columna = 0; columna < matriz.length; columna++) {
            for (int fila = 0; fila < matriz.length; fila++) {
                matrizInt[columna][fila] = Integer.parseInt(matriz[columna][fila].trim());
            }
        }

        int[] sumaFila = compruebaFila(matrizInt);
        int[] sumaColumna = compruebaColumna(matrizInt);
        int sumaDiagonal1 = compruebaDiagonal1(matrizInt);
        int sumaDiagonal2 = compruebaDiagonal2(matrizInt);

        /* System.out.println("sumaFila "+Arrays.toString(sumaFila));
        System.out.println("sumaColumna "+Arrays.toString(sumaColumna));
        System.out.println("sumaDiagonal1 "+sumaDiagonal1);
        System.out.println("sumaDiagonal2 "+sumaDiagonal2); */

        for (int suma : sumaFila) {
            if (suma != sumaDiagonal1 || suma != sumaDiagonal2) {
                return false;
            }
        }

        for (int suma : sumaColumna) {
            if (suma != sumaDiagonal1 || suma != sumaDiagonal2) {
                return false;
            }
        }

        return true;

    }

    private int compruebaDiagonal2(int[][] matrizInt) {
        int sumaDiagonal2 = 0;
        int j = 0;
        for (int i = matrizInt.length-1; i >= 0; i--) {
            sumaDiagonal2+=matrizInt[j][i];
            j++;            
        }
        return sumaDiagonal2;
    }

    private int compruebaDiagonal1(int[][] matrizInt) {
        int sumaDiagonal1 = 0;
        for (int i = 0; i < matrizInt.length; i++) {
            sumaDiagonal1+=matrizInt[i][i];            
        }
        return sumaDiagonal1;
    }

    private int[] compruebaColumna(int[][] matrizInt) {
        int[] sumaColumna = new int[matrizInt.length];
        for (int i = 0; i < sumaColumna.length; i++) {
            for (int j = 0; j < sumaColumna.length; j++) {
                sumaColumna[i]+=matrizInt[j][i]; 
            }
        }
        return sumaColumna;
    }

    private int[] compruebaFila(int[][] matrizInt) {
        int[] sumaFila = new int[matrizInt.length];
        for (int i = 0; i < sumaFila.length; i++) {
            for (int j = 0; j < sumaFila.length; j++) {
                sumaFila[i]+=matrizInt[i][j]; 
            }
        }
        return sumaFila;
    }

}
