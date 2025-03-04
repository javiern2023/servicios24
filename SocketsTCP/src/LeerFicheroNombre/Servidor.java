package LeerFicheroNombre;

import java.io.*;
import java.net.*;
import java.util.HashSet;

public class Servidor {
    private static final int PUERTO = 5000;
    private static final String FICHERO_NOMBRES = "usuarios.txt";

    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor escuchando en el puerto " + PUERTO);

            while (true) {
                try (Socket socketCliente = servidor.accept();
                     BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                     PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true)) {

                    String nombre = entrada.readLine();
                    System.out.println("Cliente conectado, nombre recibido: " + nombre);

                    // Comprobamos si el nombre está en el archivo
                    if (nombreExiste(nombre)) {
                        salida.println("El usuario " + nombre + " ya está dado de alta.");
                    } else {
                        guardarNombre(nombre);
                        salida.println("El usuario " + nombre + " ha sido dado de alta.");
                    }
                } catch (IOException e) {
                    System.out.println("Error con el cliente: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }

    private static boolean nombreExiste(String nombre) throws IOException {
        File fichero = new File(FICHERO_NOMBRES);
        if (!fichero.exists()) {
            return false;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.equalsIgnoreCase(nombre)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void guardarNombre(String nombre) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHERO_NOMBRES, true))) {
            bw.write(nombre);
            bw.newLine();
        }
    }
}

