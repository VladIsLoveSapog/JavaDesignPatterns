package concurrency3_creation.code.example3_anonymous;

public class AnonymousThreadMain {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Пример 3: Анонимный класс ===\n");

        // Runnable задаётся анонимным классом прямо в конструкторе Thread
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    System.out.println(Thread.currentThread().getName() + ": i = " + i);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }, "Анонимный-Поток");

        // То же самое лямбдой:
        // Thread t = new Thread(() -> {
        //     for (int i = 1; i <= 5; i++) {
        //         System.out.println(Thread.currentThread().getName() + ": i = " + i);
        //         try { Thread.sleep(200); } catch (InterruptedException e) {
        //             Thread.currentThread().interrupt(); return;
        //         }
        //     }
        // }, "Анонимный-Поток");

        t.start();

        System.out.println("Поток завершён.");
    }
}
