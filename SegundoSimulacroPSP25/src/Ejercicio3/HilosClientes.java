package Ejercicio3;

import java.io.*;
import java.net.*;
import java.util.*;

public class HilosClientes extends Thread {
    private Socket socket;

    public HilosClientes(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
        ) {
            salida.println("Bienvenido. Elija opción: INSERTAR o CONSULTAR");
            String opcion = entrada.readLine();

            if ("INSERTAR".equalsIgnoreCase(opcion)) {
                salida.println("Introduce nombre:");
                String nombre = entrada.readLine();
                salida.println("Introduce ID:");
                String id = entrada.readLine();

                synchronized (Servidor.FICHERO) {
                    if (existeEmpleado(nombre)) {
                        salida.println("ERROR: El empleado ya existe.");
                    } else {
                        insertarEmpleado(nombre, id);
                        salida.println("Empleado insertado correctamente.");
                    }
                }
            } else if ("CONSULTAR".equalsIgnoreCase(opcion)) {
                salida.println("Introduce nombre del empleado:");
                String nombre = entrada.readLine();

                String id = buscarID(nombre);
                if (id != null) {
                    salida.println("ID del empleado " + nombre + ": " + id);
                } else {
                    salida.println("El empleado no está registrado.");
                }
            } else {
                salida.println("Opción no válida.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try { socket.close(); } catch (IOException e) {}
        }
    }

    private boolean existeEmpleado(String nombre) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(Servidor.FICHERO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes[0].equalsIgnoreCase(nombre)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            // Fichero no existe aún
        }
        return false;
    }

    private void insertarEmpleado(String nombre, String id) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Servidor.FICHERO, true))) {
            writer.write(nombre + ":" + id);
            writer.newLine();
        }
    }

    private String buscarID(String nombre) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(Servidor.FICHERO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes[0].equalsIgnoreCase(nombre)) {
                    return partes[1];
                }
            }
        } catch (FileNotFoundException e) {
            // Fichero aún no creado
        }
        return null;
    }
}

