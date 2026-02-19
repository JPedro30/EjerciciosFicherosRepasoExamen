package ejerciciosFichero;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class DescifrarMensaje {

    private final String archivo = "mensajeSecreto.txt";
    private int[] mensajeCifrado;
    private int numPal = 0;


    public DescifrarMensaje(){

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String dato = "";
            dato = br.readLine();
            String[] datoStr = dato.split(",");
            mensajeCifrado = new int[datoStr.length];
            for (int i = 0; i < datoStr.length; i++) {
                mensajeCifrado[i] = Integer.parseInt(datoStr[i].trim());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(Arrays.toString(mensajeCifrado));

        numPal = mensajeCifrado[0];

        String mensajeDesc = descifra();
        System.out.println(mensajeDesc);


    }

    private String descifra() {
        String mensaje = "";
        int numLetras = 0;
        int c = 1; // controla posicion
        for (int i = 0; i < numPal; i++) {
            numLetras = mensajeCifrado[c];
            int[] palabra = new int[numLetras*2];
            c++; // 2
            for (int j = 0; j < palabra.length; j++) {
                palabra[j] = mensajeCifrado[c]; // asigna el 2 y se suma 1
                c++;
            }
            mensaje+=desencriptarMens(palabra);
        }

        return mensaje;

    }

    private String desencriptarMens(int[] palabra) {
        String abcPAR = "abcdefghijklmnopqrstuvwxyz";
        String abcIMP = "zyxwvutsrqponmlkjihgfedcba";
        String mensaje = "";
        for (int i = 0; i < palabra.length; i+=2) {
            if (palabra[i+1]%2==0) {
                mensaje+=String.valueOf(abcPAR.charAt(palabra[i]-1));
            } else {
                mensaje+=String.valueOf(abcIMP.charAt(palabra[i]-1));
            }
        }

        mensaje+=" ";
        return mensaje;
    }

}
