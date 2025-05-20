package Ejercicio1;

public class BibliotecaMonitor {
    private int llavesDisponibles = 12;

    public synchronized void solicitarLlave(String grupo) throws InterruptedException {
        System.out.println(grupo + " quiere una llave.");
        while (llavesDisponibles == 0) {
            System.out.println(grupo + " espera porque no hay llaves.");
            wait(); // espera hasta que haya una llave
        }
        llavesDisponibles--;
        System.out.println(grupo + " ha obtenido una llave. Llaves restantes: " + llavesDisponibles);
    }

    public synchronized void devolverLlave(String grupo) {
        llavesDisponibles++;
        System.out.println(grupo + " ha devuelto una llave. Llaves disponibles: " + llavesDisponibles);
        notify(); // despierta a un grupo que espera por una llave
    }

    // Grupo de estudiantes simulando el comportamiento
    public static class GrupoEstudiantes extends Thread {
        private final BibliotecaMonitor biblioteca;
        private final String nombreGrupo;

        public GrupoEstudiantes(BibliotecaMonitor biblio, String nombre) {
            this.biblioteca = biblio;
            this.nombreGrupo = nombre;
        }

        @Override
        public void run() {
            try {
                biblioteca.solicitarLlave(nombreGrupo);

                // Simular uso de sala
                Thread.sleep((long) (Math.random() * 5000) + 1000);

                biblioteca.devolverLlave(nombreGrupo);
            } catch (InterruptedException e) {
                System.out.println(nombreGrupo + " fue interrumpido.");
            }
        }
    }

    public static void main(String[] args) {
        BibliotecaMonitor biblioteca = new BibliotecaMonitor();

        // Simular 20 grupos
        for (int i = 1; i <= 20; i++) {
            new GrupoEstudiantes(biblioteca, "Grupo-" + i).start();
        }
    }
}

