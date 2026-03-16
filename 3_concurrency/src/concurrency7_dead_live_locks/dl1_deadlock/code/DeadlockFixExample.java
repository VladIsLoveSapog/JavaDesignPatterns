package concurrency7_dead_live_locks.dl1_deadlock.code;

/**
 * Предотвращение Deadlock через упорядочивание захвата ресурсов.
 *
 * <p>Тот же сценарий, что и в {@link DeadlockExample}: два потока работают
 * с двумя общими ресурсами. Разница в том, что <b>оба потока захватывают
 * ресурсы в одном и том же порядке</b>: сначала {@code ресурсA}, затем
 * {@code ресурсB}.</p>
 *
 * <p>Это нарушает условие <b>циклического ожидания</b> (Circular Wait) —
 * одно из четырёх условий Coffman. Без циклического ожидания Deadlock
 * невозможен: один поток захватит {@code ресурсA} первым, второй будет
 * ждать на нём, а не на {@code ресурсB}.</p>
 *
 * <p><b>Результат</b>: программа корректно завершается.</p>
 */
public class DeadlockFixExample {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Предотвращение Deadlock: упорядочивание ресурсов ===\n");

        final Object resourceA = new Object();
        final Object resourceB = new Object();

        // Поток-1: захватывает A → B
        Thread поток1 = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println("Поток-1: захватил Ресурс A");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                synchronized (resourceB) {
                    System.out.println("Поток-1: захватил Ресурс B");
                    System.out.println("Поток-1: выполняет работу с обоими ресурсами");
                }
            }
        }, "Поток-1");

        // Поток-2: тоже захватывает A → B (тот же порядок!)
        Thread поток2 = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println("Поток-2: захватил Ресурс A");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                synchronized (resourceB) {
                    System.out.println("Поток-2: захватил Ресурс B");
                    System.out.println("Поток-2: выполняет работу с обоими ресурсами");
                }
            }
        }, "Поток-2");

        поток1.start();
        поток2.start();

        поток1.join();
        поток2.join();

        System.out.println("\nОба потока завершились корректно — Deadlock предотвращён!");
    }
}
