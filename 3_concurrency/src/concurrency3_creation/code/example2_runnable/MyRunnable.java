package concurrency3_creation.code.example2_runnable;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Пример создания задачи через реализацию интерфейса {@link Runnable}.
 *
 * <p><b>Ключевые отличия от наследования {@code Thread}:</b>
 * <ul>
 *   <li>{@code Runnable} — это <em>задача</em>, а не поток. Один экземпляр {@code Runnable}
 *       может быть передан нескольким потокам {@code Thread} одновременно.</li>
 *   <li>Внутри {@code run()} доступ к текущему потоку — <b>только</b> через
 *       {@link Thread#currentThread()} (не через {@code this}).</li>
 *   <li>Класс может наследоваться от любого другого класса (Java single inheritance).</li>
 * </ul>
 *
 * <p>{@code Runnable} — это {@code @FunctionalInterface}: содержит ровно один абстрактный
 * метод {@code run()}, поэтому его можно заменить лямбда-выражением.
 *
 * <p><b>Разделение ответственности:</b> задача ({@code Runnable}) знает <em>что</em> делать;
 * поток ({@code Thread}) знает <em>как</em> и <em>когда</em> это запустить.
 */
public class MyRunnable implements Runnable {

    /**
     * Идентификатор задачи — отдельно от имени потока, который её выполняет.
     */
    private final String taskName;

    /**
     * @param taskName логическое имя задачи (task != thread)
     */
    public MyRunnable(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Тело задачи. Имя потока получаем через {@code Thread.currentThread().getName()}, поскольку {@code this} — это
     * {@code Runnable}, а не {@code Thread}.
     */
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName(); // ОБЯЗАТЕЛЬНО через currentThread()
        System.out.println("[" + taskName + "] выполняется в потоке: " + threadName);
        for (int i = 1; i <= 5; i++) {
            System.out.println("[" + taskName + "] поток=" + threadName + ", i=" + i);
            try {
                // Случайная пауза 100–500 мс: каждый поток живёт в своём темпе,
                // поэтому порядок вывода меняется от запуска к запуску
                Thread.sleep(ThreadLocalRandom.current().nextLong(100, 500));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("[" + taskName + "] завершён в потоке: " + threadName);
    }
}
