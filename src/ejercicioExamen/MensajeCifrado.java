package ejercicioExamen;

import java.io.BufferedReader;
import java.io.FileReader;

public class MensajeCifrado {

    private final String ARCHIVO = "mensajeSecreto.txt";
    private int[] mensajeCifrado;
    private int numPalabras;

    public MensajeCifrado(){

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea = "";
            linea = br.readLine();
            String[] lineaArray = linea.split(",");
            numPalabras = Integer.parseInt(lineaArray[0]);
            mensajeCifrado = new int[lineaArray.length];
            for (int i = 0; i < lineaArray.length; i++) {
                mensajeCifrado[i] = Integer.parseInt(lineaArray[i].trim());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String mensaje = partePalabra();
        System.out.println(mensaje.trim());

    }

    private String partePalabra() {
        int[] palabra;
        int tamañoPalabra = 0;
        int c = 1;
        String mensaje = "";
        for (int i = 0; i < numPalabras; i++) {
            tamañoPalabra = mensajeCifrado[c]*2;
            palabra = new int[tamañoPalabra];
            c++;
            for (int j = 0; j < palabra.length; j++) {
                palabra[j] = mensajeCifrado[c];
                c++;
            }
            mensaje+=descifraPalabra(palabra);
        }
        return mensaje;
    }

    private String descifraPalabra(int[] palabra) {
        String ABC = "abcdefghijklmnopqrstuvwxyz";
        String ZYX = "zyxwvutsrqponmlkjihgfedcba";
        String mensaje = "";
        for (int i = 0; i < palabra.length; i+=2) {
            if (palabra[i+1]%2==0) {
                mensaje += ABC.charAt(palabra[i]-1);
            } else mensaje += ZYX.charAt(palabra[i]-1);
        }
        mensaje+=" ";
        return mensaje;
    }

}
