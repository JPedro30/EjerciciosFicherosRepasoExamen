package ejerciciosFichero;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class MaquinaGolosinas {

    String[][] nombreGol;
    double[][] precioGol;
    int[][] cantidGol;
    double[][] ventasGol;
    int numGolosinas = 0;

    private final File archivo = new File("golosinas.txt");

    public MaquinaGolosinas(){

        leerArchivo();

        boolean parar = false;
        Scanner sc = new Scanner(System.in);

        while (!parar) {
            mostrarOpciones();
            System.out.println("Que quieres hacer");
            String opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    mostrarChuches();
                    System.out.println("que chuches quieres");
                    String coor = sc.nextLine();
                    // falta añadir trycatch para excepcion de coordenadas
                    pedirChuche(coor);
                    break;

                case "2":
                    añadirChuche();
                    // falta añadir trycatch para excepcion coordenadas, contraseña y añadir(por si es negativo)
                    break;

                case "3":
                    apagarGuardar();
                    calcularVentas();
                    System.out.println("apagando...");
                    parar = true;
                    break;
            
                default:
                    break;
            }

            if (!parar) {
                System.out.println("ENTER PARA CONTINUAR...");
                sc.nextLine();
            }
            
        }

    }

    private void calcularVentas() {
        double ventas = 0;
        for (int i = 0; i < cantidGol.length; i++) {
            for (int j = 0; j < cantidGol.length; j++) {
                ventas+=ventasGol[i][j]*precioGol[i][j];
                /* if (ventasGol[i][j]>0) {
                    System.out.println("Se ha vendido "+ventasGol[i][j]+" de "+nombreGol[i][j]+" total : "+ventasGol[i][j]*precioGol[i][j]);
                } */
            }
        }
        System.out.println("se ha vendido "+ventas+" euros esta sesion");
    }

    private void apagarGuardar() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            String dato = String.valueOf(numGolosinas);
            bw.write(dato);
            bw.newLine();
            for (int i = 0; i < cantidGol.length; i++) {
                dato = "";
                for (int j = 0; j < cantidGol.length; j++) {
                    dato+=nombreGol[i][j];
                    if (!(j==cantidGol.length-1)) {
                        dato+=",";
                    }
                }
                bw.write(dato);
                bw.newLine();
            }
            for (int i = 0; i < cantidGol.length; i++) {
                dato = "";
                for (int j = 0; j < cantidGol.length; j++) {
                    dato+=precioGol[i][j];
                    if (!(j==cantidGol.length-1)) {
                        dato+=",";
                    }
                }
                bw.write(dato);
                bw.newLine();
            }
            for (int i = 0; i < cantidGol.length; i++) {
                dato = "";
                for (int j = 0; j < cantidGol.length; j++) {
                    dato+=cantidGol[i][j];
                    if (!(j==cantidGol.length-1)) {
                        dato+=",";
                    }
                }
                bw.write(dato);
                bw.newLine();
            }
            bw.flush();
        } catch (Exception e) {
            System.out.println("fallo al guardar");
        }
    }

    private void añadirChuche() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'añadirChuche'");
    }

    private void mostrarChuches() {
        for (int i = 0; i < cantidGol.length; i++) {
            for (int j = 0; j < cantidGol.length; j++) {
                if (cantidGol[i][j]>0) {
                    System.out.println(i+""+j+" "+nombreGol[i][j]);
                }
            }
        }
    }

    private void pedirChuche(String coor) {
        int coorFil = Integer.parseInt(coor.substring(0,1));
        int coorCol = Integer.parseInt(coor.substring(1));
        if (coorCol>3 || coorCol<0 || coorFil>3 || coorFil<0) {
            System.out.println("besitos miau miau no hay chuches con esas coord");
        }
        if (cantidGol[coorFil][coorCol]<=0) {
            System.out.println("no quedan chuches");
        }
        cantidGol[coorFil][coorCol]--;
        ventasGol[coorFil][coorCol]++;        
    }

    private void mostrarOpciones() {
        System.out.println("1. pedir chuches");
        System.out.println("2. añadir chuches");
        System.out.println("3. apagar");
    }

    private void leerArchivo() {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String dato = "";
            dato = br.readLine();
            numGolosinas = Integer.parseInt(dato);
            nombreGol = new String[numGolosinas][numGolosinas];
            precioGol = new double[numGolosinas][numGolosinas];
            cantidGol = new int[numGolosinas][numGolosinas];
            String[] line;
            for (int linea = 0; linea < numGolosinas; linea++) {
                dato = br.readLine();
                line = dato.split(",");
                for (int i = 0; i < line.length; i++) {
                    nombreGol[linea][i] = line[i].trim();
                }
            }
            for (int linea = 0; linea < numGolosinas; linea++) {
                dato = br.readLine();
                line = dato.split(",");
                for (int i = 0; i < line.length; i++) {
                    precioGol[linea][i] = Double.parseDouble(line[i].trim());
                }
            }
            for (int linea = 0; linea < numGolosinas; linea++) {
                dato = br.readLine();
                line = dato.split(",");
                for (int i = 0; i < line.length; i++) {
                    cantidGol[linea][i] = Integer.parseInt(line[i].trim());
                }
            }
            ventasGol = new double[numGolosinas][numGolosinas];
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
