package vista;

import java.io.IOException;

import ejerciciosFichero.TarjetaClave;

public class Main {
    public static void main(String[] args) {
        
        try {
            TarjetaClave tarjeta = new TarjetaClave();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
