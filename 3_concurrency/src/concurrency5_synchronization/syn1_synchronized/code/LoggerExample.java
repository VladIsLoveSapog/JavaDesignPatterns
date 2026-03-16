package concurrency5_synchronization.syn1_synchronized.code;

/**
 * Синхронизированный статический метод — потокобезопасный логгер.
 *
 * <p>{@code static synchronized} захватывает <b>монитор объекта {@code Class}</b>
 * (а не {@code this}), то есть блокировку разделяют все экземпляры класса и все статические синхронизированные методы
 * этого класса.</p>
 *
 * <p>Без синхронизации вывод нескольких потоков в {@code System.out} может
 * перемешаться: строки от разных потоков окажутся вперемешку.</p>
 */
public class LoggerExample {

    /**
     * Потокобезопасный вывод сообщения. Монитор {@code LoggerExample.class} гарантирует, что только один поток
     * одновременно выполняет этот метод, строки в консоли не перемешаются.
     */
    public static synchronized void log(String message) {
        // Дополнительно выводим имя потока, чтобы видеть порядок выполнения
        System.out.println("[" + Thread.currentThread().getName() + "] " + message);
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Синхронизированный статический метод: логгер ===\n");

        // Создаём 5 потоков, каждый пишет 3 сообщения
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            int num = i;
            threads[i] = new Thread(() -> {
                for (int j = 1; j <= 3; j++) {
                    // Каждый вызов log() полностью завершается до начала следующего
                    log("Сообщение " + j + " от потока " + num);
                }
            }, "Поток-" + i);
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        System.out.println("\nВсе сообщения напечатаны без перемешивания строк.");
    }
}
