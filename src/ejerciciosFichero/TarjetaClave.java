package ejerciciosFichero;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TarjetaClave {

    private final String archivo = "usuarios.txt";

    public TarjetaClave() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(archivo));

        String dato = "";

        String nom = "";
        String pass = "";
        String cla = "";
        String nomTec = "";
        String passTec = "";
        String claveTec = "";
        String[] coor1 = { "A", "B", "C", "D", "E" };

        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce tu usuario: ");
        nomTec = sc.nextLine();

        boolean usuarioEncontrado = false;

        while ((dato = br.readLine()) != null) {
            if (nomTec.equalsIgnoreCase(dato)) {
                nom = dato;
                dato = br.readLine();
                pass = dato;
                dato = br.readLine();
                cla = dato;
                usuarioEncontrado = true;
                break;
            }
        }

        if (usuarioEncontrado) {

            System.out.print("Introduzca la contraseña: ");
            passTec = sc.nextLine();

            if (passTec.equals(pass)) {
                System.out.println("Contraseña correcta...");
                String[] claArr = cla.split(",");
                int[][] claIntMat = partirArray(claArr);

                System.out.print("    ");
                for (int x = 0; x < 5; x++) {
                    System.out.printf("%-5s", coor1[x]);
                }
                for (int col = 0; col < 5; col++) {
                    System.out.print("\n" + (col + 1));
                    for (int fil = 0; fil < 5; fil++) {
                        System.out.printf("%5s", claIntMat[col][fil]);
                    }
                }

                int colAle = (int) (Math.random() * (4 - 0 + 1) + 0);
                int filAle = (int) (Math.random() * (4 - 0 + 1) + 0);

                System.out.print("\nIntroduzca la clave para la coordenada " + coor1[filAle] + (colAle + 1) + " : ");
                claveTec = sc.nextLine();
                int claveTecInt = Integer.parseInt(claveTec);

                if (claveTecInt == claIntMat[colAle][filAle]) {
                    System.out.println("Clave correcta...");
                } else
                    System.out.println("Clave incorrecta...");
            } else
                System.out.println("Contraseña incorrecta...");
        } else
            System.out.println("Usuario incorrecto...");

    }

    private int[][] partirArray(String[] claArr) {
        int[][] matriz = new int[5][5];
        int x = 0;
        for (int col = 0; col < matriz.length; col++) {
            for (int fil = 0; fil < matriz.length; fil++) {
                matriz[col][fil] = Integer.parseInt(claArr[x].trim());
                x++;
            }
        }
        return matriz;
    }

}
