package concurrency2_main_thread.code.example2_change_property;

/**
 * Изменение имени и приоритета главного потока.
 *
 * <p>Приоритет потока — целое число от {@link Thread#MIN_PRIORITY} (1)
 * до {@link Thread#MAX_PRIORITY} (10). По умолчанию главный поток создаётся с
 * приоритетом {@link Thread#NORM_PRIORITY} (5).</p>
 */
public class ChangeProperty {

    public static void main(String[] args) {
        System.out.println("=== Изменение свойств главного потока ===\n");

        // Thread.currentThread() возвращает ссылку на поток, выполняющий текущий код
        Thread mainThread = Thread.currentThread();

        System.out.println("До изменения:");
        System.out.println("  Имя:       " + mainThread.getName());
        System.out.println("  Приоритет: " + mainThread.getPriority());

        // setName() — изменяет отображаемое имя потока
        mainThread.setName("МойГлавныйПоток");
        // setPriority() — задаёт приоритет; допустимый диапазон: 1 (MIN) – 10 (MAX)
        mainThread.setPriority(Thread.MAX_PRIORITY);

        System.out.println("\nПосле изменения:");
        System.out.println("  Имя:       " + mainThread.getName());
        System.out.println("  Приоритет: " + mainThread.getPriority());
    }
}
