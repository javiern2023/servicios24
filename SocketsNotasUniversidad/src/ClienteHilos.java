import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class ClienteHilos {
    private final String host = "localhost";
    private final int puerto = 5000;

    public void iniciar() {
        try (Socket socket = new Socket(host, puerto);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {
            
            while (true) {
                System.out.println("Menú:");
                System.out.println("1. Insertar nota");
                System.out.println("2. Modificar nota");
                System.out.println("3. Consultar nota");
                System.out.println("4. Eliminar nota");
                System.out.println("5. Salir");
                System.out.print("Elija una opción: ");
                String opcion = scanner.nextLine();
                if (opcion.equals("5")) break;
                
                System.out.print("Ingrese el nombre del alumno: ");
                String nombre = scanner.nextLine();
                if (nombre.isEmpty()) {
                    System.out.println("Error: El nombre no puede estar vacío.");
                    continue;
                }
                salida.println(opcion);
                salida.flush();
                salida.println(nombre);
                salida.flush();
                
                if (opcion.equals("1") || opcion.equals("2")) {
                    System.out.print("Ingrese la nota: ");
                    String nota = scanner.nextLine();
                    if (nota.isEmpty()) {
                        System.out.println("Error: La nota no puede estar vacía.");
                        continue;
                    }
                    salida.println(nota);
                    salida.flush();
                }
                
                String respuesta = entrada.readLine();
                if (respuesta == null) {
                    System.out.println("Error: el servidor cerró la conexión.");
                    break;
                }
                System.out.println("Servidor: " + respuesta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
