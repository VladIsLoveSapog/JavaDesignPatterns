package concurrency3_creation.code.example5_join;

/**
 * Базовый пример {@link Thread#join()} — главный поток ждёт завершения рабочего.
 *
 * <p>{@code join()} блокирует вызывающий поток до тех пор, пока целевой поток
 * не перейдёт в состояние {@code TERMINATED}. Без {@code join()} главный поток
 * мог бы завершиться раньше рабочего, и порядок вывода стал бы непредсказуемым.</p>
 *
 * <p>{@link InterruptedException} выбрасывается, если поток, вызвавший {@code join()},
 * был прерван во время ожидания — следует восстанавливать флаг прерывания.</p>
 */
public class SimpleJoin {

    public static void main(String[] args) {
        System.out.println("=== Простой пример join() ===\n");

        // Создаём поток и передаём ему задачу Task
        Thread thread = new Thread(new Task(), "Рабочий поток");

        // start() создаёт нативный поток ОС и запускает Task.run() асинхронно
        thread.start();

        try {
            System.out.println("[main] Ждём завершения рабочего потока...");

            // join() переводит главный поток в WAITING до тех пор,
            // пока "Рабочий поток" не завершит выполнение run()
            thread.join();

            // Эта строка гарантированно печатается ПОСЛЕ завершения рабочего потока
            System.out.println("[main] Рабочий поток завершён. Продолжаем работу.");
        } catch (InterruptedException e) {
            // Восстанавливаем флаг прерывания, чтобы вышестоящий код мог его обнаружить
            Thread.currentThread().interrupt();
        }

        System.out.println("[main] Главный поток завершён.");
    }
}

/**
 * Задача рабочего потока: имитирует 2 секунды работы через {@code Thread.sleep()}.
 */
class Task implements Runnable {

    @Override
    public void run() {
        System.out.println("[" + Thread.currentThread().getName() + "] Начало работы...");

        try {
            // Имитируем длительную работу (например, IO или вычисления)
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("[" + Thread.currentThread().getName() + "] Работа завершена.");
    }
}
