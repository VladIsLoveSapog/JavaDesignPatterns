package concurrency5_synchronization.syn3_semaphore.code;

import java.util.concurrent.Semaphore;

/**
 * Семафор для ограничения одновременного доступа к ресурсу.
 *
 * <p>Сценарий: есть некий ресурс (например, пул БД-соединений), который
 * выдерживает не более 3 одновременных пользователей. 10 потоков конкурируют
 * за доступ — семафор ограничивает параллелизм до 3.</p>
 *
 * <p>{@link Semaphore} хранит счётчик <em>разрешений</em>:
 * <ul>
 *   <li>{@code acquire()} — уменьшает счётчик на 1; если счётчик == 0, поток блокируется.</li>
 *   <li>{@code release()} — увеличивает счётчик на 1, пробуждает одного из ждущих потоков.</li>
 * </ul>
 * {@code release()} вызывается в блоке {@code finally}, чтобы разрешение
 * возвращалось даже при исключении в критической секции.</p>
 */
public class ResourceAccess {

    // Максимальное число потоков, одновременно работающих с ресурсом
    private static final int MAX_CONCURRENT = 3;

    // Семафор с 3 разрешениями — не более 3 потоков одновременно в секции
    private final Semaphore semaphore = new Semaphore(MAX_CONCURRENT);

    /**
     * Захват разрешения → использование ресурса → освобождение разрешения.
     */
    public void useResource() {
        try {
            semaphore.acquire(); // Если все 3 разрешения заняты — поток блокируется здесь

            System.out.println("[" + Thread.currentThread().getName() + "] использует ресурс"
                    + " | свободно разрешений: " + semaphore.availablePermits());

            // Имитируем работу с ресурсом (IO, запрос к БД и т.д.)
            Thread.sleep(1500);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            // release() обязательно в finally — разрешение возвращается при любом исходе
            semaphore.release();
            System.out.println("[" + Thread.currentThread().getName() + "] освободил ресурс"
                    + " | свободно разрешений: " + semaphore.availablePermits());
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Семафор: ограничение одновременного доступа (max=" + MAX_CONCURRENT + ") ===\n");

        ResourceAccess example = new ResourceAccess();

        // 10 потоков, но одновременно ресурс использует не более MAX_CONCURRENT=3
        for (int i = 0; i < 10; i++) {
            new Thread(example::useResource, "Поток-" + i).start();
        }
    }
}
