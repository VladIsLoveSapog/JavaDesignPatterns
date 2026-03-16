package concurrency5_synchronization.syn2_mutex.code;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Мьютекс через {@link ReentrantLock} — явный захват и освобождение блокировки.
 *
 * <p>{@code ReentrantLock} — основная реализация интерфейса {@link Lock}
 * из пакета {@code java.util.concurrent.locks}. В отличие от {@code synchronized},
 * требует <b>явного вызова</b> {@code lock()} и {@code unlock()}.</p>
 *
 * <p><b>Критически важно</b> вызывать {@code unlock()} в блоке {@code finally}:
 * если внутри критической секции возникнет исключение и unlock() не будет вызван,
 * монитор останется захваченным навсегда — все остальные потоки заблокируются.</p>
 *
 * <p>Сценарий: 10 потоков инкрементируют общий счётчик. Мьютекс гарантирует,
 * что в любой момент времени к счётчику обращается ровно один поток.</p>
 */
public class MutexLockExample {

    // Разделяемая переменная — общий ресурс
    private static int count = 0;

    // ReentrantLock = мьютекс: в каждый момент только один поток держит блокировку
    private static final Lock lock = new ReentrantLock();

    /**
     * Атомарный инкремент с явной блокировкой.
     * lock() перед критической секцией, unlock() всегда в finally.
     */
    public static void increment() {
        lock.lock(); // Поток захватывает монитор; если уже занят — блокируется
        try {
            count++;  // Критическая секция: только один поток одновременно
        } finally {
            // finally гарантирует освобождение блокировки даже при исключении
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Мьютекс (ReentrantLock): инкремент счётчика ===\n");

        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100_000; j++) {
                    increment();
                }
            }, "Поток-" + i);
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        System.out.println("Ожидаемый результат:  1 000 000");
        System.out.println("Фактический результат: " + count);
    }
}
