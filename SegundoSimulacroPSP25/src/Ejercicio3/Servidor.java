package Ejercicio3;

import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {
    private static final int PUERTO = 6000;
    public static final String FICHERO = "empleados.txt";

    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor de empleados iniciado en puerto " + PUERTO);
            while (true) {
                Socket cliente = servidor.accept();
                new HilosClientes(cliente).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

