package concurrency8_template_tasks.philosophers.code;

/**
 * Решение задачи «Обедающие философы» через упорядоченный захват ресурсов.
 *
 * <p>Deadlock возникает из-за <b>циклического ожидания</b>: каждый философ берёт
 * вилку с меньшим номером, затем с большим — или наоборот, но не единообразно.
 * Если <b>все</b> философы захватывают вилки строго по возрастанию {@code id},
 * цикл невозможен: последний философ (Ф4) попытается взять вилку 0 первой,
 * а не вилку 4, и цепочка ожидания не замкнётся.</p>
 *
 * <p>Ключевая идея: {@code first = min(left, right)}, {@code second = max(left, right)}.
 * Это нарушает условие Coffman «Circular Wait» — достаточно, чтобы deadlock
 * стал невозможен.</p>
 *
 * <p><b>Ожидаемый результат</b>: все философы по очереди едят, программа
 * работает ~5 секунд и завершается.</p>
 */
public class PhilosophersOrdered {

    private static final int COUNT = 5;
    private static final long RUN_TIME_MS = 5_000;

    // -------------------------------------------------------------------------
    // Вилка
    // -------------------------------------------------------------------------

    /**
     * Вилка — общий ресурс. Поле {@code id} определяет порядок захвата.
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
     * Философ всегда берёт вилку с <b>меньшим</b> {@code id} первой.
     *
     * <p>Конструктор сам определяет порядок: {@code first} = вилка с меньшим id,
     * {@code second} = вилка с большим id. Философу не нужно знать, какая из них
     * «левая», а какая «правая» — важен только порядок захвата.</p>
     */
    static class Philosopher implements Runnable {

        private final int id;
        private final Fork first;  // вилка с меньшим id — захватывается первой
        private final Fork second; // вилка с большим id — захватывается второй

        Philosopher(int id, Fork leftFork, Fork rightFork) {
            this.id = id;
            // Упорядоченный захват: всегда от меньшего id к большему
            if (leftFork.id < rightFork.id) {
                this.first = leftFork;
                this.second = rightFork;
            } else {
                this.first = rightFork;
                this.second = leftFork;
            }
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
            // Всегда захватываем вилку с меньшим id первой — нет цикла
            synchronized (first) {
                synchronized (second) {
                    System.out.println("Философ " + id
                            + " ест (вилки " + first.id + " и " + second.id + ")");
                    Thread.sleep(300 + (int) (Math.random() * 300));
                }
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
        System.out.println("=== Обедающие философы: упорядоченный захват вилок ===" + "\n");

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
