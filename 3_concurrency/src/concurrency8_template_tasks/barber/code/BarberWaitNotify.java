package concurrency8_template_tasks.barber.code;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Решение задачи «Спящий парикмахер» через {@code synchronized} + {@code wait/notifyAll}.
 *
 * <p>Сценарий: один парикмахер, одно кресло для стрижки, {@value #WAITING_SEATS} мест
 * ожидания. Клиенты приходят с интервалом — если есть свободное место, садятся
 * и ждут; если мест нет, уходят. Парикмахер спит, пока нет клиентов, и просыпается,
 * когда приходит первый.</p>
 *
 * <p>Ключевые механизмы:</p>
 * <ul>
 *   <li>{@code synchronized} — защита общей очереди от гонок</li>
 *   <li>{@code wait()} — парикмахер засыпает, если клиентов нет</li>
 *   <li>{@code notifyAll()} — клиент будит парикмахера; парикмахер сигнализирует о закрытии</li>
 *   <li>{@code while}-условие — защита от ложных пробуждений (spurious wakeup)</li>
 * </ul>
 *
 * <p><b>Ожидаемый результат</b>: парикмахер обслуживает клиентов, часть уходит
 * (нет мест), после обслуживания всех программа завершается.</p>
 */
public class BarberWaitNotify {

    private static final int WAITING_SEATS = 3;
    private static final int TOTAL_CUSTOMERS = 10;
    private static final long HAIRCUT_TIME_MS = 500;
    private static final long CUSTOMER_INTERVAL_MS = 300;

    // -------------------------------------------------------------------------
    // Зал ожидания (общий ресурс)
    // -------------------------------------------------------------------------

    /**
     * Зал ожидания — общий ресурс, синхронизирующий парикмахера и клиентов.
     *
     * <p>Очередь {@code queue} ограничена {@code maxSeats} местами.
     * Парикмахер засыпает через {@code wait()}, когда очередь пуста.
     * Клиент будит парикмахера через {@code notifyAll()}, когда садится в очередь.</p>
     */
    static class WaitingRoom {

        private final int maxSeats;
        private final Queue<String> queue = new LinkedList<>();
        private boolean open = true; // false — парикмахерская закрыта

        WaitingRoom(int maxSeats) {
            this.maxSeats = maxSeats;
        }

        /**
         * Клиент пытается сесть в зал ожидания.
         *
         * <p>Если есть свободное место — клиент садится и будит парикмахера
         * через {@code notifyAll()}. Если мест нет — клиент уходит.</p>
         *
         * @param customerName имя клиента
         */
        public synchronized void enterShop(String customerName) {
            if (queue.size() >= maxSeats) {
                // Все места заняты — клиент уходит
                System.out.println(customerName + " → нет свободных мест, ушёл.");
                return;
            }

            queue.add(customerName);
            System.out.println(customerName + " → сел в зал ожидания"
                    + " (занято " + queue.size() + "/" + maxSeats + ")");

            // Будим парикмахера, если он спит
            notifyAll();
        }

        /**
         * Парикмахер берёт следующего клиента из очереди.
         *
         * <p>Если очередь пуста и парикмахерская открыта — парикмахер засыпает
         * через {@code wait()}, освобождая монитор. Цикл {@code while} защищает
         * от ложных пробуждений.</p>
         *
         * @return имя клиента или {@code null}, если парикмахерская закрыта
         *         и клиентов больше нет
         */
        public synchronized String nextCustomer() {
            // Ждём клиента или сигнала закрытия
            while (queue.isEmpty() && open) {
                try {
                    System.out.println("Парикмахер спит — клиентов нет...");
                    wait(); // освобождаем монитор и засыпаем
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return null;
                }
            }

            // Если закрыто и очередь пуста — завершаем работу
            if (queue.isEmpty()) {
                return null;
            }

            return queue.poll();
        }

        /**
         * Закрывает парикмахерскую и будит парикмахера через {@code notifyAll()}.
         *
         * <p>После вызова парикмахер достригает оставшихся клиентов в очереди
         * и завершает работу.</p>
         */
        public synchronized void closeShop() {
            open = false;
            notifyAll(); // будим парикмахера, чтобы он проверил условие и завершился
        }
    }

    // -------------------------------------------------------------------------
    // Парикмахер
    // -------------------------------------------------------------------------

    /**
     * Поток парикмахера: в цикле берёт клиента из {@link WaitingRoom} и стрижёт.
     *
     * <p>Когда {@code nextCustomer()} возвращает {@code null}, парикмахер
     * завершает работу — парикмахерская закрыта и очередь пуста.</p>
     */
    static class BarberThread implements Runnable {

        private final WaitingRoom room;

        BarberThread(WaitingRoom room) {
            this.room = room;
        }

        @Override
        public void run() {
            while (true) {
                String customer = room.nextCustomer();
                if (customer == null) {
                    break; // парикмахерская закрыта, клиентов нет
                }
                cutHair(customer);
            }
            System.out.println("Парикмахер закончил работу.");
        }

        private void cutHair(String customer) {
            System.out.println("  ✂ Парикмахер стрижёт " + customer + "...");
            try {
                Thread.sleep(HAIRCUT_TIME_MS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("  ✂ " + customer + " подстрижен!");
        }
    }

    // -------------------------------------------------------------------------
    // Клиент
    // -------------------------------------------------------------------------

    /**
     * Поток клиента: приходит в парикмахерскую и пытается сесть в зал ожидания.
     */
    static class CustomerThread implements Runnable {

        private final String name;
        private final WaitingRoom room;

        CustomerThread(String name, WaitingRoom room) {
            this.name = name;
            this.room = room;
        }

        @Override
        public void run() {
            room.enterShop(name);
        }
    }

    // -------------------------------------------------------------------------
    // Точка входа
    // -------------------------------------------------------------------------

    /**
     * Запускает парикмахера и {@value #TOTAL_CUSTOMERS} клиентов с интервалом
     * {@value #CUSTOMER_INTERVAL_MS} мс. После прихода всех клиентов закрывает
     * парикмахерскую и ждёт завершения парикмахера.
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Спящий парикмахер: wait/notify ===" + "\n");

        WaitingRoom room = new WaitingRoom(WAITING_SEATS);

        // Парикмахер работает, пока не получит сигнал закрытия
        Thread barber = new Thread(new BarberThread(room), "Парикмахер");
        barber.start();

        // Клиенты приходят с интервалом
        Thread[] customers = new Thread[TOTAL_CUSTOMERS];
        for (int i = 1; i <= TOTAL_CUSTOMERS; i++) {
            customers[i - 1] = new Thread(
                    new CustomerThread("Клиент-" + i, room), "Клиент-" + i);
            customers[i - 1].start();

            Thread.sleep(CUSTOMER_INTERVAL_MS);
        }

        // Ждём, пока все клиенты попытаются войти
        for (Thread c : customers) {
            c.join();
        }

        // Закрываем парикмахерскую — парикмахер достригает оставшихся и выходит
        room.closeShop();
        barber.join();

        System.out.println("\nПарикмахерская закрыта.");
    }
}
