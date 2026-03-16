package concurrency5_synchronization.syn4_example.code;

import java.util.ArrayList;
import java.util.List;

/**
 * Пример 7 — {@code synchronized}-метод: аналог правильного Mutex из примера 5.
 *
 * <p>Ключевое слово {@code synchronized} на статическом методе захватывает
 * монитор объекта {@code SynchronizationMain.class} при входе и освобождает
 * при выходе из метода. Это эквивалентно {@code synchronized(SynchronizationMain.class)}
 * вокруг всего тела метода.</p>
 *
 * <p><b>Результат:</b> стабильно 1 000 000, аналогично {@link MutexCorrectMain}.
 * Один поток выполняет весь цикл из 100 000 итераций, остальные блокируются
 * на входе в метод.</p>
 *
 * <p><b>Отличие от {@link MutexCorrectMain}:</b> {@code synchronized} лаконичнее
 * и не требует явного блока {@code try-finally}. Монитор освобождается
 * автоматически даже при исключении.</p>
 */
public class SynchronizationMain {

    static int counter = 0;

    /**
     * synchronized static — монитор класса захватывается на весь вызов метода.
     * Только один поток одновременно выполняет этот метод.
     */
    public synchronized static void cyclicAdd() {
        for (int j = 0; j < 100_000; j++)
            counter++; // Защищено монитором класса
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== synchronized метод: ожидаем 1 000 000 ===\n");

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(() -> cyclicAdd()));
        }

        for (var t : threads) t.start();
        for (var t : threads) t.join();

        System.out.println("Результат: " + counter + " (ожидалось 1 000 000)");
        System.out.println("Аналог MutexCorrectMain, но без явного ReentrantLock.");
    }
}
