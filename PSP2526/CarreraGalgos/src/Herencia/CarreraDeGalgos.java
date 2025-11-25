package Herencia;

public class CarreraDeGalgos {
    public static void main(String[] args) {
        String[] nombres = {"Rayo", "Luna", "Furia", "Sombra", "Blitz"};
        Galgo[] galgos = new Galgo[nombres.length];

        // Crear e iniciar hilos
        for (int i = 0; i < nombres.length; i++) {
            galgos[i] = new Galgo(nombres[i]);
            galgos[i].start();
        }

        // Mensaje inmediato, sin esperar a que terminen los galgos
        System.out.println("La carrera ha comenzado!");
    }
}
