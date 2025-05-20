package Ejercicio3;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    private static final String HOST = "localhost";
    private static final int PUERTO = 6000;

    public static void main(String[] args) {
        try (
            Socket socket = new Socket(HOST, PUERTO);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
        ) {
            System.out.println(entrada.readLine()); // Menú
            String opcion = scanner.nextLine();
            salida.println(opcion);

            if ("INSERTAR".equalsIgnoreCase(opcion)) {
                System.out.print(entrada.readLine() + " ");
                String nombre = scanner.nextLine();
                salida.println(nombre);

                System.out.print(entrada.readLine() + " ");
                String id = scanner.nextLine();
                salida.println(id);

                System.out.println(entrada.readLine()); // Confirmación

            } else if ("CONSULTAR".equalsIgnoreCase(opcion)) {
                System.out.print(entrada.readLine() + " ");
                String nombre = scanner.nextLine();
                salida.println(nombre);

                System.out.println(entrada.readLine()); // ID o no encontrado

            } else {
                System.out.println(entrada.readLine()); // Opción inválida
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

