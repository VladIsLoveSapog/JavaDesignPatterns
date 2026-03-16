package concurrency3_creation.code.example5_join;

import java.util.ArrayList;
import java.util.List;

/**
 * Параллельные вычисления с {@link Thread#join()} для сбора результатов.
 *
 * <p>Паттерн «разветвление–слияние» (fork–join):
 * <ol>
 *   <li><b>Fork</b> — создаём и запускаем N потоков, каждый считает свою часть задачи.</li>
 *   <li><b>Join</b> — главный поток ждёт завершения всех N потоков через {@code join()}.</li>
 *   <li><b>Reduce</b> — собираем частичные результаты и вычисляем итог.</li>
 * </ol>
 * </p>
 *
 * <p>{@code synchronized (results)} необходим: {@code ArrayList} не является потокобезопасным,
 * и параллельный {@code add()} без синхронизации приведёт к гонке данных.</p>
 */
public class ParallelComputation {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Параллельные вычисления с join() ===\n");

        List<Thread> threads = new ArrayList<>();

        // Разделяемый список результатов — требует синхронизации при записи
        List<Integer> results = new ArrayList<>();

        // --- FORK: создаём и запускаем потоки ---
        for (int i = 0; i < 5; i++) {
            // Захватываем i в локальную переменную: лямбда не может захватить
            // изменяемую переменную цикла, но может захватить effectively-final локальную
            int index = i;

            Thread thread = new Thread(() -> {
                int result = compute(index);

                // Синхронизированный блок гарантирует атомарность add():
                // только один поток одновременно изменяет список
                synchronized (results) {
                    results.add(result);
                }
            });

            threads.add(thread);
            thread.start();
        }

        // --- JOIN: ждём завершения каждого потока ---
        for (Thread thread : threads) {
            // join() блокирует main до завершения конкретного потока;
            // после выхода из цикла все потоки гарантированно завершены
            thread.join();
        }

        // --- REDUCE: собираем итог, все данные уже записаны ---
        int total = results.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Результаты: " + results);
        System.out.println("Сумма квадратов (0²+1²+2²+3²+4²): " + total);
    }

    /**
     * Вычисляет квадрат числа, имитируя длительную обработку паузой в {@code value} секунд.
     *
     * @param value входное число (индекс потока)
     * @return {@code value * value}
     */
    public static int compute(int value) {
        System.out.println("[" + Thread.currentThread().getName() + "] compute(" + value + ") начат");
        try {
            // Имитируем задачу, длительность которой зависит от входных данных
            Thread.sleep(500L * value);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("[" + Thread.currentThread().getName() + "] compute(" + value + ") = " + (value * value));
        return value * value;
    }
}
