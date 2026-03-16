package concurrency5_synchronization.syn2_mutex.code;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Неблокирующий захват мьютекса через {@link Lock#tryLock()}.
 *
 * <p>{@code tryLock()} пытается захватить блокировку <b>без ожидания</b>:
 * <ul>
 *   <li>Если блокировка свободна — захватывает её и возвращает {@code true}.</li>
 *   <li>Если блокировка занята — немедленно возвращает {@code false}, не блокируя поток.</li>
 * </ul>
 * </p>
 *
 * <p>Это отличает {@code tryLock()} от {@code lock()}, который блокирует поток
 * до тех пор, пока блокировка не станет доступна. {@code tryLock()} позволяет
 * реализовать альтернативное поведение: пропустить задачу, повторить попытку
 * позже или выбрать другой ресурс.</p>
 *
 * <p>Сценарий: несколько потоков пытаются обновить счётчик. При занятой блокировке
 * поток фиксирует «пропуск» вместо блокировки.</p>
 */
public class MutexTryLockExample {

    private static int count = 0;
    private static int skipped = 0; // число пропущенных операций

    private static final Lock lock = new ReentrantLock();

    /**
     * Попытка инкремента без блокировки потока.
     * Если монитор занят — операция пропускается.
     */
    public static void tryIncrement() {
        if (lock.tryLock()) {
            // Блокировка получена — выполняем критическую секцию
            try {
                count++;
            } finally {
                lock.unlock(); // Всегда освобождаем в finally
            }
        } else {
            // Блокировка занята — поток НЕ блокируется, просто пропускает
            skipped++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Мьютекс: tryLock() — неблокирующий захват ===\n");

        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    tryIncrement();
                }
            }, "Поток-" + i);
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        System.out.println("Выполнено инкрементов: " + count);
        System.out.println("Пропущено (lock занят): " + skipped);
        System.out.println("Итого попыток:          " + (count + skipped));
        System.out.println("\nПримечание: count < 5000, потому что часть операций была пропущена.");
    }
}
