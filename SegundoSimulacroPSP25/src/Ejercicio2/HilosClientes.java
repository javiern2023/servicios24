package Ejercicio2;

import java.io.*;
import java.net.*;
import java.util.Set;

public class HilosClientes extends Thread {
    private Socket socket;
    private Set<String> codigosGanadores;

    public HilosClientes(Socket socket, Set<String> codigosGanadores) {
        this.socket = socket;
        this.codigosGanadores = codigosGanadores;
    }

    @Override
    public void run() {
        try (
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
        ) {
            salida.println("Introduce un código de 6 caracteres:");
            String codigo = entrada.readLine();

            if (codigo != null && codigosGanadores.contains(codigo.trim().toUpperCase())) {
                salida.println("¡Felicidades! El código " + codigo + " es ganador.");
            } else {
                salida.println("Lo sentimos, el código " + codigo + " no ha tenido premio.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try { socket.close(); } catch (IOException e) {}
        }
    }
}

