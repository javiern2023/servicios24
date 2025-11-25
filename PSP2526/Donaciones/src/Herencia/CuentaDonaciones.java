package Herencia;

public class CuentaDonaciones {
    public static void main(String[] args) throws InterruptedException {
        String[] nombres = {"Ana", "Luis", "Marta", "Pedro", "Sofía"};
        Donante[] donantes = new Donante[nombres.length];

        System.out.println("Se ha abierto la cuenta para donaciones.");

        // Crear e iniciar hilos de donantes
        for (int i = 0; i < nombres.length; i++) {
            donantes[i] = new Donante(nombres[i]);
            donantes[i].start();
        }

        // Mensaje inmediato, no esperamos a que terminen
        System.out.println("Las donaciones están en curso...");
        
        // Esperar a que todos los donantes terminen
        for (Donante d : donantes) {
            d.join();
        }

        // Mostrar resultados finales
        System.out.println("\n--- Resumen de la recaudación ---");
        System.out.println("Número de donantes: " + donantes.length);
        System.out.println("Total recaudado: " + Donante.totalRecaudado + " euros");
        
    }
}
