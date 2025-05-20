package Ejercicio2;

import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {
    private static final int PUERTO = 5000;
    private static final Set<String> codigosGanadores = new HashSet<>();

    public static void main(String[] args) {
        generarCodigosGanadores(); // o podrías cargarlos de archivo

        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor del concurso iniciado en el puerto " + PUERTO);
            System.out.println("Códigos ganadores: " + codigosGanadores);

            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado desde " + cliente.getInetAddress());
                new HilosClientes(cliente, codigosGanadores).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generarCodigosGanadores() {
        Random rand = new Random();
        while (codigosGanadores.size() < 4) {
            String codigo = generarCodigo(rand);
            codigosGanadores.add(codigo);
        }
    }

    private static String generarCodigo(Random rand) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder codigo = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            codigo.append(caracteres.charAt(rand.nextInt(caracteres.length())));
        }
        return codigo.toString();
    }
}

