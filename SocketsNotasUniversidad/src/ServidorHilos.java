import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

class ServidorHilos extends Thread {
    private Socket socket;
    private static final String FICHERO = "notas.txt";

    public ServidorHilos(Socket socket) {
        this.socket = socket;
    }

    public void run() {
    	try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    	         PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {
    	        
    	        String opcion;
    	        while ((opcion = entrada.readLine()) != null) {  // Leer opciones hasta que el cliente cierre la conexión
    	            String nombre = entrada.readLine();
    	            if (nombre == null) break;  // Si el cliente cierra, salir

    	            String respuesta = "";
    	            
    	            switch (opcion) {
    	                case "1": // Insertar nota
    	                    String nota = entrada.readLine();
    	                    if (nota == null) break;
    	                    respuesta = insertarNota(nombre, nota);
    	                    break;
    	                case "2": // Modificar nota
    	                    String nuevaNota = entrada.readLine();
    	                    if (nuevaNota == null) break;
    	                    respuesta = modificarNota(nombre, nuevaNota);
    	                    break;
    	                case "3": // Consultar nota
    	                    respuesta = consultarNota(nombre);
    	                    break;
    	                case "4": // Eliminar nota
    	                    respuesta = eliminarNota(nombre);
    	                    break;
    	                case "5": // Salir
    	                    respuesta = "Desconectando...";
    	                    salida.println(respuesta);
    	                    return;  // Cerrar el hilo
    	                default:
    	                    respuesta = "Opción no válida.";
    	            }
    	            salida.println(respuesta);  // Enviar respuesta al cliente
    	        }
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }
    }

    private synchronized String insertarNota(String nombre, String nota) throws IOException {
    	verificarArchivo(); // Asegurar que el archivo existe
    	List<String> lineas = new ArrayList<>(Files.readAllLines(new File(FICHERO).toPath()));
        for (String linea : lineas) {
            if (linea.startsWith(nombre + "-")) {
                return "El usuario ya tiene una nota registrada.";
            }
        }
        lineas.add(nombre + "-" + nota);
        Files.write(new File(FICHERO).toPath(), lineas);
        return "Nota añadida correctamente.";
    }

    private synchronized String modificarNota(String nombre, String nuevaNota) throws IOException {
    	verificarArchivo(); // Asegurar que el archivo existe
    	File archivo = new File(FICHERO);
        List<String> lineas = new ArrayList<>(Files.readAllLines(archivo.toPath()));
        boolean encontrado = false;
        for (int i = 0; i < lineas.size(); i++) {
            if (lineas.get(i).startsWith(nombre + "-")) {
                lineas.set(i, nombre + "-" + nuevaNota);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) return "El usuario no tiene nota registrada.";
        Files.write(archivo.toPath(), lineas);
        return "Nota modificada correctamente.";
    }

    private synchronized String consultarNota(String nombre) throws IOException {
    	verificarArchivo(); // Asegurar que el archivo existe
    	List<String> lineas = new ArrayList<>(Files.readAllLines(new File(FICHERO).toPath()));
        for (String linea : lineas) {
            if (linea.startsWith(nombre + "-")) {
                return "Nota de " + nombre + ": " + linea.split("-")[1];
            }
        }
        return "El usuario no tiene una nota registrada.";
    }

    private synchronized String eliminarNota(String nombre) throws IOException {
        File archivo = new File(FICHERO);
        List<String> lineas = new ArrayList<>(Files.readAllLines(archivo.toPath()));
        boolean eliminado = lineas.removeIf(linea -> linea.startsWith(nombre + "-"));
        if (!eliminado) return "El usuario no tiene nota registrada.";
        Files.write(archivo.toPath(), lineas);
        return "Nota eliminada correctamente.";
    }
    
    private void verificarArchivo() throws IOException {
        File archivo = new File(FICHERO);
        if (!archivo.exists()) {
            archivo.createNewFile(); // Crea el archivo vacío si no existe
        }
    }
}
