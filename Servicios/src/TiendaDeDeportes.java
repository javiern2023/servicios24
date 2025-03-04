
import java.util.concurrent.Semaphore;

class Cliente extends Thread {
    private final TiendaDeDeportes tienda;

    public Cliente(TiendaDeDeportes tienda, String nombre) {
        super(nombre);
        this.tienda = tienda;
    }

    @Override
    public void run() {
        tienda.comprar(getName());
    }
}

class TiendaDeDeportes {
    private final Semaphore semaphore;

    public TiendaDeDeportes(int capacidad) {
        this.semaphore = new Semaphore(capacidad);
    }

    public void comprar(String cliente) {
        if (semaphore.tryAcquire()) {
            try {
                System.out.println(cliente + " ha entrado a la tienda.");
                // Simular el tiempo que pasa comprando
                Thread.sleep(1000);
                System.out.println(cliente + " ha salido de la tienda.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                semaphore.release();
            }
        } else {
            System.out.println(cliente + " no pudo entrar a la tienda, está llena.");
        }
    }

    public static void main(String[] args) {
        TiendaDeDeportes tienda = new TiendaDeDeportes(3); // Capacidad para 3 clientes

        Cliente[] clientes = new Cliente[10];
        for (int i = 0; i < 10; i++) {
            clientes[i] = new Cliente(tienda, "Cliente " + (i + 1));
            clientes[i].start();
        }

        // Esperar que todos los clientes terminen
        for (Cliente cliente : clientes) {
            try {
                cliente.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
