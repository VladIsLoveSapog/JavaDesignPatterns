package concurrency5_synchronization.syn4_example.code;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Пример 6 — неправильное использование Mutex: lock/unlock на каждой итерации.
 *
 * <p>Блокировка захватывается и освобождается <b>на каждом инкременте</b>,
 * то есть 10 потоков × 100 000 итераций = <b>1 000 000 пар lock/unlock</b>.</p>
 *
 * <p><b>Захват и снятие монитора — тяжёлые операции</b>: они требуют вызова
 * в ОС и переключения контекста при конкуренции. Накладные расходы в 1 000 000
 * блокировок замедляют программу в десятки раз по сравнению с
 * {@link MutexCorrectMain}, хотя результат всё равно верный.</p>
 *
 * <p><b>Вывод:</b> синхронизировать надо минимально необходимую критическую секцию,
 * но не дробить её без нужды. Здесь правильнее было бы вынести lock/unlock
 * за пределы цикла, как в {@link MutexCorrectMain}.</p>
 */
public class MutexIncorrectMain {

    static int counter = 0;
    static Lock lock = new ReentrantLock();

    /**
     * lock/unlock ВНУТРИ цикла — 100 000 блокировок на поток.
     * Результат верный, но крайне медленный из-за накладных расходов.
     */
    public static void cyclicAdd() {
        for (int j = 0; j < 100_000; j++) {
            lock.lock();   // 100 000 захватов на один поток
            try {
                counter++; // Критическая секция = одна операция
            } finally {
                lock.unlock(); // 100 000 освобождений на один поток
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Mutex (неправильно): lock на каждую итерацию ===\n");

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
        System.out.println("Сравните со временем MutexCorrectMain — разница в разы.");
    }
}
