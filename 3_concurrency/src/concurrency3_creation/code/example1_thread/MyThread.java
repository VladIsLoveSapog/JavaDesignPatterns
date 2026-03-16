package concurrency3_creation.code.example1_thread;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Пример создания потока через наследование от {@link Thread}.
 *
 * <p><b>Особенности этого подхода:</b>
 * <ul>
 *   <li>Объект {@code MyThread} <em>сам является</em> потоком — {@code this} == текущий поток
 *       внутри {@code run()}.</li>
 *   <li>Прямой доступ к методам {@link Thread} без {@code Thread.currentThread()}:
 *       {@link #getName()}, {@link #getId()}, {@link #getPriority()}, {@link #isDaemon()}.</li>
 *   <li>Вызов {@code start()} инициирует создание нативного потока ОС через JVM;
 *       именно в нём будет выполнен {@code run()}.</li>
 *   <li>Ограничение: нельзя наследоваться от другого класса (Java single inheritance).</li>
 * </ul>
 *
 * <p><b>Жизненный цикл:</b> NEW → RUNNABLE → (TIMED_WAITING при sleep) → TERMINATED.
 */
public class MyThread extends Thread {

    /**
     * Создаёт поток с заданным именем и приоритетом.
     *
     * @param name     имя потока (отображается в логах и отладчике)
     * @param priority приоритет {@link Thread#MIN_PRIORITY}..{@link Thread#MAX_PRIORITY}
     */
    public MyThread(String name, int priority) {
        setName(name);
        setPriority(priority);
    }

    /**
     * Тело потока — выполняется в новом нативном потоке ОС после вызова {@code start()}.
     *
     * <p>Внутри {@code run()} {@code this} == текущий поток, поэтому {@code getName()}
     * эквивалентно {@code Thread.currentThread().getName()}.
     */
    @Override
    public void run() {
        System.out.println(getName() + " стартовал (приоритет=" + getPriority() + ", daemon=" + isDaemon() + ")");
        for (int i = 1; i <= 5; i++) {
            System.out.println(getName() + ": i = " + i);
            try {
                // Случайная пауза 100–500 мс: каждый поток живёт в своём темпе,
                // поэтому порядок вывода меняется от запуска к запуску
                Thread.sleep(ThreadLocalRandom.current().nextLong(100, 500));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(getName() + " завершён штатно.");
    }

    /**
     * Переопределение {@code interrupt()} для демонстрации кастомной очистки ресурсов
     * перед установкой флага прерывания.
     *
     * <p><b>Важно:</b> {@code super.interrupt()} обязателен — именно он устанавливает
     * флаг прерывания и выбрасывает {@link InterruptedException} в блокирующих методах.
     */
    @Override
    public void interrupt() {
        System.out.println("[interrupt()] Кастомная очистка перед прерыванием потока: " + getName());
        super.interrupt(); // ОБЯЗАТЕЛЬНО — устанавливает флаг прерывания
    }
}
