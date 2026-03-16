package concurrency8_template_tasks.philosophers.code;

/**
 * Демонстрация Deadlock в задаче «Обедающие философы».
 *
 * <p>Пять философов сидят за круглым столом, между каждой парой лежит вилка (всего 5).
 * Каждый философ чередует размышления и еду. Чтобы поесть, нужно взять <b>обе</b>
 * соседние вилки — левую и правую.</p>
 *
 * <p>В этом примере каждый философ <b>всегда берёт сначала левую вилку, затем правую</b>.
 * Между захватами стоит {@code Thread.sleep(50)}, чтобы все философы успели взять
 * левую вилку до попытки взять правую. Это создаёт <b>циклическое ожидание</b> —
 * одно из четырёх условий Coffman:</p>
 * <ul>
 *   <li><b>Mutual Exclusion</b> — вилку держит только один философ</li>
 *   <li><b>Hold and Wait</b> — философ держит левую и ждёт правую</li>
 *   <li><b>No Preemption</b> — вилку нельзя отобрать</li>
 *   <li><b>Circular Wait</b> — Ф0→Ф1→Ф2→Ф3→Ф4→Ф0 (цикл)</li>
 * </ul>
 *
 * <p><b>Ожидаемый результат</b>: программа зависает — все философы взяли левую вилку
 * и бесконечно ждут правую.</p>
 */
public class PhilosophersDeadlock {

    private static final int COUNT = 5;

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
     * Философ циклически размышляет и ест.
     *
     * <p>Порядок захвата: <b>всегда left → right</b>. При 5 философах за круглым
     * столом это гарантирует циклическое ожидание и, как следствие, deadlock.</p>
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
                while (true) {
                    think();
                    eat();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        private void think() throws InterruptedException {
            System.out.println("Философ " + id + " размышляет...");
            Thread.sleep(500);
        }

        private void eat() throws InterruptedException {
            // Берём левую вилку
            synchronized (leftFork) {
                System.out.println("Философ " + id + " взял левую вилку " + leftFork.id);

                // Пауза — даём остальным философам взять свою левую вилку,
                // чтобы гарантировать deadlock
                Thread.sleep(50);

                // Пытаемся взять правую вилку — здесь программа зависнет
                synchronized (rightFork) {
                    System.out.println("Философ " + id + " взял правую вилку " + rightFork.id);
                    System.out.println("Философ " + id + " ест.");
                    Thread.sleep(500);
                }
            }
        }
    }

    // -------------------------------------------------------------------------
    // Точка входа
    // -------------------------------------------------------------------------

    /**
     * Создаёт 5 вилок и 5 философов. Каждый берёт вилки left → right,
     * что приводит к deadlock.
     */
    public static void main(String[] args) {
        System.out.println("=== Обедающие философы: демонстрация Deadlock ===" + "\n");

        Fork[] forks = new Fork[COUNT];
        for (int i = 0; i < COUNT; i++) {
            forks[i] = new Fork(i);
        }

        for (int i = 0; i < COUNT; i++) {
            Fork left = forks[i];
            Fork right = forks[(i + 1) % COUNT]; // следующая вилка по кругу

            Thread t = new Thread(new Philosopher(i, left, right), "Философ-" + i);
            t.start();
        }

        // Программа зависнет — все потоки заблокированы навсегда
    }
}
