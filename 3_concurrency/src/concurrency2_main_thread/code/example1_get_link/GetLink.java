package concurrency2_main_thread.code.example1_get_link;

/**
 * Получение ссылки на главный поток через {@link Thread#currentThread()}.
 *
 * <p>Метод {@code Thread.currentThread()} возвращает объект {@link Thread},
 * представляющий поток, в котором в данный момент выполняется вызывающий код.</p>
 */
public class GetLink {

    public static void main(String[] args) {
        System.out.println("=== Получение ссылки на главный поток ===\n");

        // Thread.currentThread() возвращает ссылку на поток, выполняющий текущий код
        Thread mainThread = Thread.currentThread();

        System.out.println("Имя потока:    " + mainThread.getName());
        System.out.println("Приоритет:     " + mainThread.getPriority());
        System.out.println("Группа:        " + mainThread.getThreadGroup().getName());
        System.out.println("Демон:         " + mainThread.isDaemon());
    }
}
