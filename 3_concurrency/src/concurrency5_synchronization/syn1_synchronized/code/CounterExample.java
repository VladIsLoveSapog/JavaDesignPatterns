package concurrency5_synchronization.syn1_synchronized.code;

/**
 * Синхронизированные экземплярные методы — защита общего счётчика.
 *
 * <p>Ключевое слово {@code synchronized} на методе экземпляра захватывает
 * <b>монитор текущего объекта ({@code this})</b>. Пока один поток выполняет
 * {@code increment()}, все остальные потоки блокируются на входе в любой другой {@code synchronized}-метод <em>этого же
 * объекта</em>.</p>
 *
 * <p>Без {@code synchronized} операция {@code count++} не является атомарной:
 * она разворачивается в три шага (read → modify → write), между которыми планировщик может переключить поток — и часть
 * инкрементов потеряется.</p>
 */
public class CounterExample {

    // Общий изменяемый ресурс — счётчик, к которому обращаются несколько потоков
    private int count = 0;

    /**
     * Синхронизированный метод инкремента. Монитор захватывается на {@code this} — только один поток одновременно может
     * выполнять этот метод на данном экземпляре.
     */
    public synchronized void increment() {
        count++;
    }

    /**
     * Синхронизированный метод чтения. Чтение тоже синхронизировано: без этого другой поток мог бы увидеть устаревшее
     * значение из своего кеша.
     */
    public synchronized int getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Синхронизированные методы: счётчик ===\n");

        CounterExample counter = new CounterExample();

        // Создаём 10 потоков, каждый делает 100 000 инкрементов
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100_000; j++) {
                    counter.increment();
                }
            }, "Поток-" + i);
        }

        for (Thread t : threads) t.start();
        // join() — ждём завершения всех потоков перед чтением результата
        for (Thread t : threads) t.join();

        System.out.println("Ожидаемый результат:  1 000 000");
        System.out.println("Фактический результат: " + counter.getCount());
    }
}
