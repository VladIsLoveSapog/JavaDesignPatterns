package concurrency7_dead_live_locks.dl2_livelock.code;

/**
 * Демонстрация Livelock — потоки активны, но не продвигаются.
 *
 * <p>Два «вежливых» потока пытаются воспользоваться общим ресурсом.
 * Каждый поток, обнаружив, что ресурс занят другим, <b>уступает</b> его — освобождает ресурс и пробует снова. Но второй
 * поток делает то же самое, и ресурс бесконечно передаётся туда-сюда.</p>
 *
 * <p>В отличие от Deadlock, потоки <b>не заблокированы</b> — они активно
 * выполняют код (проверяют, уступают, повторяют). Но полезной работы не происходит: ни один поток не может завершить
 * свою задачу.</p>
 *
 * <p><b>Ограничение</b>: пример завершается после {@code MAX_ПОПЫТОК} итераций,
 * чтобы продемонстрировать проблему без бесконечного зависания.</p>
 */
public class LivelockExample {

    /**
     * Максимальное число попыток, после которого поток прекращает уступать
     */
    private static final int MAX_TRIES = 10;

    /**
     * Общий ресурс, за который конкурируют потоки
     */
    private static volatile String owner = null;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Livelock: потоки активны, но не продвигаются ===\n");

        Thread поток1 = new Thread(() -> {
            String имя = "Поток-1";
            for (int попытка = 1; попытка <= MAX_TRIES; попытка++) {

                // Пытаемся захватить ресурс
                while (owner != null && ! owner.equals(имя)) {
                    // Ресурс занят другим — ждём
                }

                // Захватываем ресурс
                owner = имя;
                System.out.println(имя + ": захватил ресурс (попытка " + попытка + ")");

                // Проверяем, не нужен ли ресурс другому потоку — и «вежливо» уступаем
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }

                // Уступаем ресурс другому потоку
                System.out.println(имя + ": уступает ресурс другому потоку");
                owner = null;

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            System.out.println(имя + ": не смог выполнить работу за " + MAX_TRIES + " попыток!");
        }, "Поток-1");

        Thread поток2 = new Thread(() -> {
            String имя = "Поток-2";
            for (int попытка = 1; попытка <= MAX_TRIES; попытка++) {

                // Пытаемся захватить ресурс
                while (owner != null && ! owner.equals(имя)) {
                    // Ресурс занят другим — ждём
                }

                // Захватываем ресурс
                owner = имя;
                System.out.println(имя + ": захватил ресурс (попытка " + попытка + ")");

                // Проверяем, не нужен ли ресурс другому потоку — и «вежливо» уступаем
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }

                // Уступаем ресурс другому потоку
                System.out.println(имя + ": уступает ресурс другому потоку");
                owner = null;

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            System.out.println(имя + ": не смог выполнить работу за " + MAX_TRIES + " попыток!");
        }, "Поток-2");

        поток1.start();
        поток2.start();

        поток1.join();
        поток2.join();

        System.out.println("\nОба потока завершились, но ни один не выполнил полезную работу — это Livelock!");
    }
}
