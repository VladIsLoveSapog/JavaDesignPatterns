package concurrency5_synchronization.syn4_example.code;

import java.util.ArrayList;
import java.util.List;

/**
 * Пример 3 — счётчик с {@code volatile}, но без атомарных операций.
 *
 * <p>{@code volatile} гарантирует <b>видимость</b>: каждое чтение/запись
 * переменной идёт напрямую в основную память, минуя кеш процессора.
 * Один поток сразу видит изменения, сделанные другим потоком.</p>
 *
 * <p><b>Почему volatile недостаточно для счётчика:</b>
 * {@code volatile} не делает {@code counter++} атомарным.
 * Операция по-прежнему состоит из трёх шагов (read → modify → write).
 * Между шагами планировщик может переключить поток — и инкремент потеряется.
 * {@code volatile} лишь гарантирует, что каждый поток читает <em>актуальное</em>
 * значение, но не защищает сам составной read-modify-write.</p>
 *
 * <p>Результат: {@code 150 000 – 200 000} — немного лучше без volatile,
 * но всё ещё далеко от 1 000 000.</p>
 */
public class VolatileMain {

    // volatile: запрещает кеширование — все потоки видят актуальное значение
    // Но НЕ защищает от гонки на составной операции counter++
    static volatile int counter = 0;

    /** 100 000 инкрементов volatile-переменной (всё ещё не атомарно). */
    public static void cyclicAdd() {
        for (int j = 0; j < 100_000; j++)
            counter++; // read(volatile) → modify → write(volatile) — три отдельных шага
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== volatile без атомарности: ожидаем 1 000 000 ===\n");

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(() -> cyclicAdd()));
        }

        for (var t : threads) t.start();
        for (var t : threads) t.join();

        System.out.println("Результат: " + counter);
        System.out.println("Ожидалось: 1 000 000");
        System.out.println("volatile решает видимость, но не атомарность — гонка остаётся.");
    }
}
