package matias;
public class Main {
    public static void main(String[] args) {
        Balsas balsa = new Balsas();

        Thread montarThread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (balsa.naufragosRescatados < 5) {
                    balsa.MontarEnLaBalsa("Naufrago montado en la balsa");
                    // Simula el proceso de rescate
                    try {
                        Thread.sleep(1000); // Puedes ajustar esto según tus necesidades
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread bajarThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (balsa.naufragosRescatados < 5) {
                    System.out.println("Esperando a que se rescate al naufrago...");
                    try {
                        Thread.sleep(500); // Puedes ajustar esto según tus necesidades
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    balsa.Rescatar();
                }
            }
        });

        montarThread.start();
        bajarThread.start();

        try {
            montarThread.join();
            bajarThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("========================================\nNúmero total de naufragos rescatados: " + balsa.naufragosRescatados);
    }
}
