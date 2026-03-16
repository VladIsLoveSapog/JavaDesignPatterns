package concurrency6_wait_notify.code.example2_notify_all;

/**
 * Демонстрация {@code notifyAll()} на примере паттерна "Производитель-Потребитель"
 * с <b>двумя потребителями</b> и флагом завершения производства.
 *
 * <p>Ключевые отличия от примера 1 (notify):</p>
 * <ul>
 *   <li>{@code notifyAll()} будит <b>все</b> ожидающие потоки, а не один случайный.</li>
 *   <li>Без {@code notifyAll()} один из двух потребителей может уснуть навсегда:
 *       {@code notify()} разбудит другого потребителя, а тот снова уснёт
 *       (данных нет), и ни один из них больше не получит уведомления —
 *       это называется <b>missed notification</b>.</li>
 *   <li>Флаг {@code finished} позволяет потребителям корректно завершить работу,
 *       когда производитель исчерпал все данные.</li>
 * </ul>
 *
 * <p><b>Правило:</b> используйте {@code notifyAll()} всегда, когда ожидающих потоков
 * больше одного или когда несколько разных условий могут пробудить потоки.</p>
 */
public class NotifyAllMain {

    // -------------------------------------------------------------------------
    // Общий ресурс
    // -------------------------------------------------------------------------

    /**
     * Разделяемый буфер на одну ячейку с поддержкой флага завершения.
     *
     * <p>Три состояния ресурса:</p>
     * <ol>
     *   <li>{@code available == false, finished == false} — пусто, производство идёт</li>
     *   <li>{@code available == true}  — данные лежат, потребитель может взять</li>
     *   <li>{@code available == false, finished == true}  — пусто, производство окончено</li>
     * </ol>
     *
     * <p>Метод {@code consume()} возвращает {@code null} в состоянии 3, сигнализируя
     * потребителю о необходимости выйти из цикла.</p>
     */
    static class SharedResource {

        private int data;
        private boolean available = false;
        private boolean finished  = false; // true после того, как производитель закончил

        /**
         * Кладёт значение в буфер.
         *
         * <p>Если буфер занят, производитель ждёт через {@code wait()}.
         * После записи данных вызывается {@code notifyAll()}, чтобы разбудить
         * <em>обоих</em> потребителей: один возьмёт данные, другой проверит условие
         * и снова уснёт.</p>
         *
         * @param value производимое значение
         */
        public synchronized void produce(int value) {
            while (available) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            data = value;
            available = true;
            System.out.println(Thread.currentThread().getName() + " произвёл: " + value);

            // notifyAll — нужен, чтобы оба потребителя получили шанс проверить условие
            notifyAll();
        }

        /**
         * Забирает значение из буфера.
         *
         * <p>Возвращает {@code null}, если производство завершено и буфер пуст —
         * это сигнал потребителю для выхода из цикла.</p>
         *
         * <p>Цикл {@code while (!available)} содержит проверку флага {@code finished}:
         * если производитель уже закончил и данных нет — выходим немедленно.</p>
         *
         * @return считанное значение или {@code null}, если производство завершено
         */
        public synchronized Integer consume() {
            // Ждём данных; при каждом пробуждении перепроверяем условие
            while (!available) {
                if (finished) {
                    return null; // сигнал о завершении для потребителя
                }
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            available = false;
            System.out.println(Thread.currentThread().getName() + " потребил: " + data);

            // Будим всех: производитель ждёт освобождения буфера,
            // второй потребитель должен узнать, что данных уже нет
            notifyAll();
            return data;
        }

        /**
         * Устанавливает флаг завершения производства.
         *
         * <p>После вызова потребители, которые ждут в {@code wait()}, пробуждаются
         * через {@code notifyAll()} и видят {@code finished == true} при пустом буфере —
         * после чего выходят из цикла.</p>
         */
        public synchronized void setFinished() {
            finished = true;
            // Критически важно: без notifyAll() потребители могут спать вечно,
            // не зная, что производство завершено
            notifyAll();
        }
    }

    // -------------------------------------------------------------------------
    // Производитель
    // -------------------------------------------------------------------------

    /**
     * Поток-производитель: кладёт числа 1..5, затем устанавливает флаг завершения.
     */
    static class Producer implements Runnable {

        private final SharedResource shared;

        Producer(SharedResource shared) {
            this.shared = shared;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                shared.produce(i);
                try {
                    Thread.sleep(400); // имитация времени производства
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            // Сообщаем потребителям: больше данных не будет
            shared.setFinished();
            System.out.println(Thread.currentThread().getName() + " завершил производство.");
        }
    }

    // -------------------------------------------------------------------------
    // Потребитель
    // -------------------------------------------------------------------------

    /**
     * Поток-потребитель: читает значения до тех пор, пока {@code consume()} не вернёт {@code null}.
     *
     * <p>Возврат {@code null} означает: буфер пуст <em>и</em> производство закончено —
     * пора выходить.</p>
     */
    static class Consumer implements Runnable {

        private final SharedResource shared;

        Consumer(SharedResource shared) {
            this.shared = shared;
        }

        @Override
        public void run() {
            while (true) {
                Integer value = shared.consume();
                if (value == null) {
                    break; // производство завершено, данных больше не будет
                }
                try {
                    Thread.sleep(600); // имитация обработки
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println(Thread.currentThread().getName() + " завершил потребление.");
        }
    }

    // -------------------------------------------------------------------------
    // Точка входа
    // -------------------------------------------------------------------------

    /**
     * Запускает одного производителя и двух потребителей, ждёт завершения всех трёх потоков.
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== wait / notifyAll: один производитель, два потребителя ===\n");

        SharedResource shared = new SharedResource();

        Thread producer  = new Thread(new Producer(shared),  "Производитель");
        Thread consumer1 = new Thread(new Consumer(shared), "Потребитель-1");
        Thread consumer2 = new Thread(new Consumer(shared), "Потребитель-2");

        producer.start();
        consumer1.start();
        consumer2.start();

        producer.join();
        consumer1.join();
        consumer2.join();

        System.out.println("\nВсе потоки завершены.");
    }
}
