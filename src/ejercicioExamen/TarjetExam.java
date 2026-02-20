package ejercicioExamen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class TarjetExam {

    private final String ARCHIVO = "usuarios.txt";
    private String linea = "";
    private String nombreTec = "";
    private String nombre = "";
    private int passTec = 0;
    private int pass = 0;
    private String[] claves;
    private int[][] clavesMatriz = new int[5][5];

    public TarjetExam(){

        Scanner sc = new Scanner(System.in);

        System.out.print("Introduzca usuario: ");
        nombreTec = sc.nextLine();

        boolean usuarioExiste = false;

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            while ((linea = br.readLine())!=null) {
                if (nombreTec.equalsIgnoreCase(linea)) {
                    usuarioExiste = true;
                    nombre = linea;
                    pass = Integer.parseInt(linea=br.readLine());
                    linea = br.readLine();
                    claves = linea.split(",");
                    int c = 0;
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            clavesMatriz[i][j] = Integer.parseInt(claves[c].trim());
                            c++;
                        }  
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        boolean todoBien = true;
        if (usuarioExiste) {
            
            System.out.print("Introduzca la contraseña: ");
            try {
                passTec = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                todoBien = false;
                System.out.println("Por favor introduzca una contraseña numerica...");
            }

            if (passTec == pass) {
                System.out.println("Contraseña correcta...");

                String letras = "ABCDE";
                System.out.print("\n      ");
                for (int i = 0; i < 5; i++) {
                    System.out.printf("%-5s",letras.charAt(i));
                }
                System.out.println();
                for (int i = 0; i < 5; i++) {
                    System.out.printf("%-5d",(i+1));
                    for (int j = 0; j < 5; j++) {
                        System.out.printf("%-5s",clavesMatriz[i][j]);
                    }
                    System.out.println();
                }

                int coordF = (int) (Math.random()*(4-0+1)-0);
                int coordC = (int) (Math.random()*(4-0+1)-0);

                System.out.print("\nIntroduzca la clave de las coordenadas "+(coordF+1)+letras.charAt(coordC)+": ");
                int claveUsu = 0;
                try {
                    claveUsu = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    todoBien = false;
                    System.out.println("Error, introduzca una clave numerica...");
                }

                if (todoBien) {
                    if (claveUsu == clavesMatriz[coordF][coordC]) {
                        System.out.println("Clave correcta...");
                    } else System.out.println("Clave incorrecta...");
                }

            } else {
                if (todoBien) {
                    System.out.println("Contraseña incorrecta...");
                }
            }
                
            




        } else {
            if (todoBien) {
                System.out.println("Usuario no encontrado...");
            }
        }
    }

}
