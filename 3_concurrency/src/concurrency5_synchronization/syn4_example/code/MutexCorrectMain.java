package concurrency5_synchronization.syn4_example.code;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Пример 5 — правильное использование Mutex: весь цикл под одной блокировкой.
 *
 * <p>Мьютекс захватывается <b>один раз</b> перед всем циклом из 100 000 итераций
 * и освобождается после его завершения. Один поток обрабатывает весь цикл,
 * остальные ждут.</p>
 *
 * <p><b>Результат:</b> стабильно 1 000 000, но <em>без реального параллелизма</em>:
 * в каждый момент активен только один поток. Потоки выполняются последовательно,
 * по очереди захватывая мьютекс.</p>
 *
 * <p><b>Сравните с {@link MutexIncorrectMain}:</b> здесь lock/unlock вызываются
 * 10 раз (по числу потоков), а не 10 000 000 раз — поэтому накладные расходы
 * на блокировки минимальны и программа работает быстро.</p>
 */
public class MutexCorrectMain {

    static int counter = 0;
    static Lock lock = new ReentrantLock();

    /**
     * Захватываем блокировку один раз на весь цикл — минимум накладных расходов.
     * Критическая секция = весь цикл, не одна итерация.
     */
    public static void cyclicAdd() {
        lock.lock(); // Один захват мьютекса на поток
        try {
            for (int j = 0; j < 100_000; j++)
                counter++; // Под защитой мьютекса: другие потоки ждут
        } finally {
            lock.unlock(); // Один захват — одно освобождение
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Mutex (правильно): lock на весь цикл ===\n");

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(() -> cyclicAdd()));
        }

        long start = System.currentTimeMillis();
        for (var t : threads) t.start();
        for (var t : threads) t.join();
        long elapsed = System.currentTimeMillis() - start;

        System.out.println("Результат: " + counter + " (ожидалось 1 000 000)");
        System.out.println("Время: " + elapsed + " мс");
        System.out.println("Замечание: потоки работают последовательно — параллелизма нет.");
    }
}
