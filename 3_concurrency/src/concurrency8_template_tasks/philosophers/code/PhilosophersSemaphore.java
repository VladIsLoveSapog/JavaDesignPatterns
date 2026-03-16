package concurrency8_template_tasks.philosophers.code;

import java.util.concurrent.Semaphore;

/**
 * Решение задачи «Обедающие философы» через {@link Semaphore}.
 *
 * <p>Идея: если за столом 5 философов и 5 вилок, разрешим <b>одновременно садиться
 * за стол не более чем 4-м</b>. По принципу Дирихле: 4 философа на 5 вилок —
 * хотя бы один гарантированно получит обе вилки и сможет поесть.</p>
 *
 * <p>Семафор {@code Semaphore(4, true)} выполняет роль «пропуска за стол»:
 * <ul>
 *   <li>{@code acquire()} — философ садится за стол (если уже 4 — ждёт)</li>
 *   <li>{@code release()} — философ встаёт, освобождая место</li>
 * </ul>
 * Параметр {@code fair = true} гарантирует FIFO-порядок, предотвращая starvation.</p>
 *
 * <p>Внутри семафора философы захватывают вилки в естественном порядке (left → right) —
 * deadlock невозможен, потому что одновременно пытаются есть максимум 4 из 5.</p>
 *
 * <p><b>Ожидаемый результат</b>: все философы по очереди едят, программа
 * работает ~5 секунд и завершается.</p>
 */
public class PhilosophersSemaphore {

    private static final int COUNT = 5;
    private static final long RUN_TIME_MS = 5_000;

    // Максимум 4 философа одновременно пытаются есть — гарантия отсутствия deadlock
    private static final Semaphore TABLE = new Semaphore(COUNT - 1, true);

    // -------------------------------------------------------------------------
    // Вилка
    // -------------------------------------------------------------------------

    /**
     * Вилка — общий ресурс, используемый как монитор для {@code synchronized}.
     */
    static class Fork {
        final int id;

        Fork(int id) {
            this.id = id;
        }
    }

    // -------------------------------------------------------------------------
    // Философ
    // -------------------------------------------------------------------------

    /**
     * Философ берёт вилки в естественном порядке (left → right), но перед этим
     * получает разрешение от семафора {@link #TABLE}.
     *
     * <p>Семафор ограничивает число одновременно обедающих, делая deadlock
     * невозможным даже при произвольном порядке захвата вилок.</p>
     */
    static class Philosopher implements Runnable {

        private final int id;
        private final Fork leftFork;
        private final Fork rightFork;

        Philosopher(int id, Fork leftFork, Fork rightFork) {
            this.id = id;
            this.leftFork = leftFork;
            this.rightFork = rightFork;
        }

        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    think();
                    eat();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Философ " + id + " завершил работу.");
        }

        private void think() throws InterruptedException {
            System.out.println("Философ " + id + " размышляет...");
            Thread.sleep(300 + (int) (Math.random() * 300));
        }

        private void eat() throws InterruptedException {
            // Получаем «пропуск за стол» — не более 4 одновременно
            TABLE.acquire();
            try {
                // Внутри семафора безопасно брать вилки в любом порядке
                synchronized (leftFork) {
                    synchronized (rightFork) {
                        System.out.println("Философ " + id
                                + " ест (вилки " + leftFork.id + " и " + rightFork.id + ")");
                        Thread.sleep(300 + (int) (Math.random() * 300));
                    }
                }
            } finally {
                // Освобождаем место за столом — обязательно в finally
                TABLE.release();
            }
        }
    }

    // -------------------------------------------------------------------------
    // Точка входа
    // -------------------------------------------------------------------------

    /**
     * Запускает 5 философов, даёт им поработать {@value #RUN_TIME_MS} мс,
     * затем прерывает все потоки.
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Обедающие философы: решение через Semaphore ===" + "\n");

        Fork[] forks = new Fork[COUNT];
        for (int i = 0; i < COUNT; i++) {
            forks[i] = new Fork(i);
        }

        Thread[] threads = new Thread[COUNT];
        for (int i = 0; i < COUNT; i++) {
            Fork left = forks[i];
            Fork right = forks[(i + 1) % COUNT];

            threads[i] = new Thread(new Philosopher(i, left, right), "Философ-" + i);
            threads[i].start();
        }

        // Даём философам поработать, затем завершаем
        Thread.sleep(RUN_TIME_MS);

        for (Thread t : threads) {
            t.interrupt();
        }
        for (Thread t : threads) {
            t.join();
        }

        System.out.println("\nВсе философы завершили работу.");
    }
}
