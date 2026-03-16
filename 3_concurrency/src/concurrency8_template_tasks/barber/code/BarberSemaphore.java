package concurrency8_template_tasks.barber.code;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Решение задачи «Спящий парикмахер» через {@link Semaphore}.
 *
 * <p>Используются три семафора для координации:</p>
 * <ul>
 *   <li>{@code waitingRoom} ({@value #WAITING_SEATS} разрешений) — ограничивает число
 *       мест в зале ожидания. {@code tryAcquire()} — если мест нет, клиент сразу уходит.</li>
 *   <li>{@code customerReady} (0 разрешений) — сигнал парикмахеру: «есть клиент».
 *       Клиент делает {@code release()}, парикмахер {@code acquire()} — и просыпается.</li>
 *   <li>{@code barberReady} (0 разрешений) — сигнал клиенту: «парикмахер свободен».
 *       Парикмахер делает {@code release()}, клиент {@code acquire()} — и садится в кресло.</li>
 * </ul>
 *
 * <p>{@link AtomicInteger} {@code served} считает обслуженных клиентов — парикмахер
 * завершает работу, когда обслужил всех.</p>
 *
 * <p><b>Ожидаемый результат</b>: парикмахер обслуживает клиентов, часть уходит
 * (нет мест), после обслуживания всех программа завершается.</p>
 */
public class BarberSemaphore {

    private static final int WAITING_SEATS = 3;
    private static final int TOTAL_CUSTOMERS = 10;
    private static final long HAIRCUT_TIME_MS = 500;
    private static final long CUSTOMER_INTERVAL_MS = 300;

    // Ограничивает число клиентов в зале ожидания
    private static final Semaphore waitingRoom = new Semaphore(WAITING_SEATS);

    // Сигнал парикмахеру: клиент пришёл (начальное значение 0 — парикмахер спит)
    private static final Semaphore customerReady = new Semaphore(0);

    // Сигнал клиенту: парикмахер готов стричь (начальное значение 0 — клиент ждёт)
    private static final Semaphore barberReady = new Semaphore(0);

    // Счётчик обслуженных + ушедших клиентов (для корректного завершения)
    private static final AtomicInteger processed = new AtomicInteger(0);

    // -------------------------------------------------------------------------
    // Парикмахер
    // -------------------------------------------------------------------------

    /**
     * Поток парикмахера: ждёт сигнала {@code customerReady}, затем стрижёт клиента.
     *
     * <p>{@code customerReady.acquire()} блокирует парикмахера, пока не появится клиент.
     * После получения сигнала парикмахер отпускает {@code barberReady} — сообщает клиенту,
     * что можно садиться в кресло.</p>
     */
    static class BarberThread implements Runnable {

        @Override
        public void run() {
            while (processed.get() < TOTAL_CUSTOMERS) {
                try {
                    System.out.println("Парикмахер спит — клиентов нет...");
                    customerReady.acquire(); // засыпаем, пока клиент не разбудит

                    // Сигнализируем клиенту: «садись в кресло»
                    barberReady.release();

                    // Стрижём
                    System.out.println("  Парикмахер стрижёт клиента...");
                    Thread.sleep(HAIRCUT_TIME_MS);
                    System.out.println("  Стрижка завершена!");

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            System.out.println("Парикмахер закончил работу.");
        }
    }

    // -------------------------------------------------------------------------
    // Клиент
    // -------------------------------------------------------------------------

    /**
     * Поток клиента: пытается занять место в зале ожидания.
     *
     * <p>{@code waitingRoom.tryAcquire()} — неблокирующая попытка: если мест нет,
     * клиент сразу уходит (в отличие от {@code acquire()}, который бы заблокировался).
     * Если место есть — клиент будит парикмахера ({@code customerReady.release()})
     * и ждёт, пока парикмахер освободится ({@code barberReady.acquire()}).</p>
     */
    static class CustomerThread implements Runnable {

        private final String name;

        CustomerThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            // Пытаемся занять место — если нет, уходим сразу
            if (!waitingRoom.tryAcquire()) {
                System.out.println(name + " → нет свободных мест, ушёл.");
                processed.incrementAndGet();
                // Будим парикмахера на случай, если это был последний клиент
                if (processed.get() >= TOTAL_CUSTOMERS) {
                    customerReady.release();
                }
                return;
            }

            System.out.println(name + " → сел в зал ожидания"
                    + " (свободно мест: " + waitingRoom.availablePermits() + ")");

            // Будим парикмахера: «есть клиент!»
            customerReady.release();

            try {
                // Ждём, пока парикмахер позовёт
                barberReady.acquire();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Освобождаем место в зале — мы уже в кресле
            waitingRoom.release();

            System.out.println(name + " подстрижен!");
            processed.incrementAndGet();
        }
    }

    // -------------------------------------------------------------------------
    // Точка входа
    // -------------------------------------------------------------------------

    /**
     * Запускает парикмахера и {@value #TOTAL_CUSTOMERS} клиентов с интервалом
     * {@value #CUSTOMER_INTERVAL_MS} мс. Ждёт завершения всех потоков.
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Спящий парикмахер: решение через Semaphore ===" + "\n");

        Thread barber = new Thread(new BarberThread(), "Парикмахер");
        barber.start();

        Thread[] customers = new Thread[TOTAL_CUSTOMERS];
        for (int i = 1; i <= TOTAL_CUSTOMERS; i++) {
            customers[i - 1] = new Thread(
                    new CustomerThread("Клиент-" + i), "Клиент-" + i);
            customers[i - 1].start();

            Thread.sleep(CUSTOMER_INTERVAL_MS);
        }

        // Ждём завершения всех клиентов
        for (Thread c : customers) {
            c.join();
        }

        // Ждём завершения парикмахера
        barber.join(3000);
        if (barber.isAlive()) {
            barber.interrupt();
        }

        System.out.println("\nПарикмахерская закрыта.");
    }
}
