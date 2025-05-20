package Ejercicio1;

import java.util.concurrent.Semaphore;

public class Biblioteca {

    // Solo 12 llaves disponibles al inicio
    private final Semaphore llaves = new Semaphore(12, true); // true = acceso FIFO
   // private final Object monitor = new Object(); // para sincronización adicional si se desea
     
    public void solicitarLlave(String grupo) throws InterruptedException {
        System.out.println(grupo + " quiere una llave.");
        llaves.acquire(); // espera si no hay llaves
        System.out.println(grupo + " ha obtenido una llave y entra a una sala.");
    }

    public void devolverLlave(String grupo) {
        System.out.println(grupo + " ha terminado de usar la sala.");

        // Devolver la llave al semáforo
        llaves.release();
        System.out.println(grupo + " ha devuelto la llave.");
    }

    // Simulación de grupo de estudiantes
    public static class GrupoEstudiantes extends Thread {
        private final Biblioteca biblioteca;
        private final String nombreGrupo;

        public GrupoEstudiantes(Biblioteca biblio, String nombre) {
            this.biblioteca = biblio;
            this.nombreGrupo = nombre;
        }

        @Override
        public void run() {
            try {
                biblioteca.solicitarLlave(nombreGrupo);

                // Simulan estar en la sala durante cierto tiempo
                Thread.sleep((long)(Math.random() * 5000) + 1000);

                biblioteca.devolverLlave(nombreGrupo);
            } catch (InterruptedException e) {
                System.out.println(nombreGrupo + " fue interrumpido.");
            }
        }
    }

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        // Simular 20 grupos intentando acceder (más que llaves disponibles)
        for (int i = 1; i <= 20; i++) {
            new GrupoEstudiantes(biblioteca, "Grupo-" + i).start();
        }
    }
}

