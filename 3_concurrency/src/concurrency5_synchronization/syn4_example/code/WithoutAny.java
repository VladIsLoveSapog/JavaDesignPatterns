package concurrency5_synchronization.syn4_example.code;

import java.util.ArrayList;
import java.util.List;

/**
 * Пример 1 — счётчик без какой-либо синхронизации.
 *
 * <p>10 потоков параллельно выполняют {@code counter++} по 100 000 раз.
 * Ожидаемый результат: <b>1 000 000</b>. Фактический: намного меньше.</p>
 *
 * <p><b>Проблема 1 — гонка данных (data race):</b> операция {@code counter++}
 * не атомарна. Она разворачивается в три шага:
 * <ol>
 *   <li>read — поток читает значение {@code counter} в регистр.</li>
 *   <li>modify — прибавляет 1 в регистре.</li>
 *   <li>write — записывает регистр обратно в память.</li>
 * </ol>
 * Между этими шагами планировщик может переключить поток, и другой поток
 * перезапишет результат — инкремент будет потерян.</p>
 *
 * <p><b>Проблема 2 — отсутствие join():</b> главный поток выводит результат
 * немедленно, не дожидаясь завершения рабочих потоков. Счётчик успевает
 * измениться лишь за время запуска потоков.</p>
 */
public class WithoutAny {

    // Разделяемая переменная: к ней обращаются все потоки без синхронизации
    static int counter = 0;

    /** Задача каждого потока: 100 000 неатомарных инкрементов. */
    public static void cyclicAdd() {
        for (int j = 0; j < 100_000; j++)
            counter++; // НЕ атомарно: read → modify → write могут прервать другие потоки
    }

    public static void main(String[] args) {
        System.out.println("=== Без синхронизации: ожидаем 1 000 000 ===\n");

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(() -> cyclicAdd()));
        }

        for (var t : threads) t.start();

        // Нет join() — главный поток не ждёт рабочих, выводит счётчик сразу
        System.out.println("Результат (без join): " + counter);
        System.out.println("Ожидалось: 1 000 000 — фактически намного меньше.");
    }
}
