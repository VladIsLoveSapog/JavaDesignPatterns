package concurrency5_synchronization.syn4_example.code;

import java.util.ArrayList;
import java.util.List;

/**
 * Пример 2 — счётчик с {@code join()}, но без синхронизации доступа.
 *
 * <p>По сравнению с {@link WithoutAny} добавлен {@code join()}: главный поток
 * ждёт завершения всех рабочих потоков и только потом печатает результат.
 * Результат заметно выше, но всё ещё далёк от 1 000 000.</p>
 *
 * <p><b>Почему всё ещё неверно:</b> {@code join()} решает проблему "слишком
 * раннего вывода", но не устраняет гонку данных. Потоки по-прежнему параллельно
 * читают и записывают {@code counter++} без синхронизации. Потерянные обновления
 * (lost updates) происходят постоянно — просто теперь мы хотя бы дожидаемся
 * всех потоков перед выводом.</p>
 *
 * <p>Типичный результат: {@code 120 000 – 180 000} — как будто работали 1–2 потока.</p>
 */
public class JoinMain {

    // Разделяемая переменная — гонка данных не устранена
    static int counter = 0;

    /** 100 000 неатомарных инкрементов в одном потоке. */
    public static void cyclicAdd() {
        for (int j = 0; j < 100_000; j++)
            counter++; // Гонка данных: несколько потоков одновременно читают и пишут
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== join() без синхронизации: ожидаем 1 000 000 ===\n");

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(() -> cyclicAdd()));
        }

        for (var t : threads) t.start();

        // join() гарантирует, что все потоки завершились до вывода результата
        for (var t : threads) t.join();

        System.out.println("Результат: " + counter);
        System.out.println("Ожидалось: 1 000 000");
        System.out.println("Причина расхождения: потоки вступают в гонку — часть инкрементов теряется.");
    }
}
