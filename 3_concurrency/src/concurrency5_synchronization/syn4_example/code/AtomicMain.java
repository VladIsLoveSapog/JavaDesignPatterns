package concurrency5_synchronization.syn4_example.code;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Пример 4 — счётчик с {@link AtomicInteger}: первый корректный результат.
 *
 * <p>{@code AtomicInteger} обеспечивает <b>атомарные</b> операции над числом.
 * Метод {@link AtomicInteger#incrementAndGet()} выполняет read-modify-write
 * как единую неделимую операцию на уровне процессора (инструкция LOCK XADD
 * или CAS — Compare-And-Swap). Никакой другой поток не может вмешаться между
 * чтением и записью.</p>
 *
 * <p>Дополнительно {@code AtomicInteger} содержит внутри {@code volatile}-поле,
 * поэтому изменения немедленно видны всем потокам без кеширования.</p>
 *
 * <p>Результат: стабильно <b>1 000 000</b>.</p>
 *
 * <p>Отличие от следующих примеров (Mutex, synchronized): потоки здесь работают
 * <em>по-настоящему параллельно</em> — атомарная операция не блокирует поток,
 * а использует аппаратный примитив.</p>
 */
public class AtomicMain {

    // AtomicInteger — потокобезопасный счётчик без явных блокировок
    static AtomicInteger counter = new AtomicInteger(0);

    /** 100 000 атомарных инкрементов: каждый — неделимый read-modify-write. */
    public static void cyclicAdd() {
        for (int j = 0; j < 100_000; j++)
            counter.incrementAndGet(); // Атомарно: эквивалент ++counter, но потокобезопасно
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== AtomicInteger: ожидаем 1 000 000 ===\n");

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(() -> cyclicAdd()));
        }

        for (var t : threads) t.start();
        for (var t : threads) t.join();

        System.out.println("Результат: " + counter.get());
        System.out.println("Ожидалось: 1 000 000 — совпадает!");
    }
}
