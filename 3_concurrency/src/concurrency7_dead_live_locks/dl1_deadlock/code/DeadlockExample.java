package concurrency7_dead_live_locks.dl1_deadlock.code;

/**
 * Демонстрация Deadlock — взаимной блокировки двух потоков.
 *
 * <p>Два потока захватывают два общих ресурса в <b>разном порядке</b>:
 * Поток-1 берёт {@code ресурсA → ресурсB}, Поток-2 берёт {@code ресурсB → ресурсA}.
 * Между захватами стоит {@code Thread.sleep(100)}, чтобы гарантировать, что оба
 * потока успеют захватить по одному ресурсу до попытки взять второй.</p>
 *
 * <p><b>Результат</b>: программа зависает навсегда — каждый поток ждёт ресурс,
 * удерживаемый другим. Все четыре условия Coffman выполнены одновременно.</p>
 */
public class DeadlockExample {

    public static void main(String[] args) {
        System.out.println("=== Deadlock: взаимная блокировка потоков ===\n");

        final Object resourceA = new Object();
        final Object resourceB = new Object();

        // Поток-1: захватывает A, затем пытается захватить B
        Thread поток1 = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println("Поток-1: захватил Ресурс A");
                try {
                    // Пауза, чтобы Поток-2 успел захватить Ресурс B
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Поток-1: пытается захватить Ресурс B...");
                synchronized (resourceB) {
                    System.out.println("Поток-1: захватил Ресурс B");
                }
            }
        }, "Поток-1");

        // Поток-2: захватывает B, затем пытается захватить A — обратный порядок!
        Thread поток2 = new Thread(() -> {
            synchronized (resourceB) {
                System.out.println("Поток-2: захватил Ресурс B");
                try {
                    // Пауза, чтобы Поток-1 успел захватить Ресурс A
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Поток-2: пытается захватить Ресурс A...");
                synchronized (resourceA) {
                    System.out.println("Поток-2: захватил Ресурс A");
                }
            }
        }, "Поток-2");

        поток1.start();
        поток2.start();

        // Программа зависнет — оба потока заблокированы навсегда
    }
}
