package LeerFicheroNombre;

import java.io.*;
import java.net.*;

public class Servidor {
    private static final int PUERTO = 5000;
    private static final String FICHERO_NOMBRES = "usuarios2.txt";
    private static final int TAMANO_BUFFER = 1024;

    public static void main(String[] args) {
        try (DatagramSocket socketServidor = new DatagramSocket(PUERTO)) {
            System.out.println("Servidor UDP escuchando en el puerto " + PUERTO);

            byte[] bufferRecepcion = new byte[TAMANO_BUFFER];

            while (true) {
                DatagramPacket paqueteRecibido = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);
                socketServidor.receive(paqueteRecibido);

                String nombre = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
                System.out.println("Cliente conectado, nombre recibido: " + nombre);

                String respuesta;
                if (nombreExiste(nombre)) {
                    respuesta = "El usuario " + nombre + " ya está dado de alta.";
                } else {
                    guardarNombre(nombre);
                    respuesta = "El usuario " + nombre + " ha sido dado de alta.";
                }

                // Enviar respuesta al cliente
                byte[] bufferEnvio = respuesta.getBytes();
                DatagramPacket paqueteEnvio = new DatagramPacket(
                        bufferEnvio, bufferEnvio.length,
                        paqueteRecibido.getAddress(), paqueteRecibido.getPort()
                );

                socketServidor.send(paqueteEnvio);
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

