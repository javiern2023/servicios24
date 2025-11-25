package Interfaz;

public class CarreraDeGalgos {
    public static void main(String[] args) {
        String[] nombres = {"Rayo", "Luna", "Furia", "Sombra", "Blitz"};

        System.out.println("La carrera ha comenzado!");

        // Crear e iniciar hilos
        for (String nombre : nombres) {
            Galgo galgo = new Galgo(nombre);
            Thread hilo = new Thread(galgo);
            hilo.start();
        }
    }
}
