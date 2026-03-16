package concurrency6_wait_notify.code.example1_notify;

/**
 * Демонстрация механизма wait/notify на примере паттерна "Производитель-Потребитель".
 *
 * <p>Один производитель создаёт значения (1..5), один потребитель их считывает.
 * Потоки чередуются через общий ресурс: производитель ждёт, пока потребитель
 * не заберёт предыдущее значение, потребитель ждёт, пока производитель не
 * положит новое.</p>
 *
 * <p>Ключевые механизмы, показанные в примере:</p>
 * <ul>
 *   <li>{@code wait()} — поток освобождает монитор и засыпает до уведомления</li>
 *   <li>{@code notify()} — будит один ожидающий поток на этом мониторе</li>
 *   <li>{@code while}-условие вместо {@code if} — защита от ложных пробуждений</li>
 *   <li>{@code synchronized} — обязательный контекст для вызова wait/notify</li>
 * </ul>
 */
public class NotifyMain {
    /**
     * Разделяемый буфер на одну ячейку.
     *
     * <p>Хранит одно целое число и флаг {@code available}, показывающий,
     * есть ли в буфере непрочитанные данные.</p>
     *
     * <p>Оба метода {@code synchronized}: это гарантирует, что в каждый момент
     * только один поток исполняет тело метода и, следовательно, имеет право
     * вызывать {@code wait()} / {@code notify()} на мониторе {@code this}.</p>
     */
    static class SharedResource {

        private int data;
        private boolean available = false; // true — данные лежат, потребитель может взять

        /**
         * Кладёт значение в буфер.
         *
         * <p>Если буфер занят ({@code available == true}), производитель вызывает
         * {@code wait()} и засыпает. При этом монитор освобождается, давая
         * потребителю возможность забрать данные. После пробуждения цикл
         * {@code while} проверяет условие снова — это защита от ложных пробуждений
         * (spurious wakeups), которые допускает спецификация JVM.</p>
         *
         * @param value значение, которое производитель хочет положить
         */
        public synchronized void produce(int value) {
            // Ждём, пока потребитель не освободит буфер
            while (available) {
                try {
                    wait(); // освобождаем монитор и засыпаем
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // восстанавливаем флаг прерывания
                }
            }

            data = value;
            available = true; // сигнализируем: данные готовы
            System.out.println(Thread.currentThread().getName() + " произвёл: " + value);

            // Будим потребителя — он единственный ожидающий в этой схеме 1:1,
            // поэтому notify() достаточно
            notify();
        }

        /**
         * Забирает значение из буфера.
         *
         * <p>Если буфер пуст ({@code available == false}), потребитель вызывает
         * {@code wait()} и засыпает. Использование {@code while} вместо {@code if}
         * принципиально: пробуждённый поток должен заново проверить условие,
         * прежде чем читать данные.</p>
         *
         * @return считанное значение
         */
        public synchronized int consume() {
            // Ждём, пока производитель не положит данные
            while (!available) {
                try {
                    wait(); // освобождаем монитор и засыпаем
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            available = false; // буфер снова свободен
            System.out.println(Thread.currentThread().getName() + " потребил: " + data);

            // Будим производителя, который может ждать освобождения буфера
            notify();
            return data;
        }
    }

    // -------------------------------------------------------------------------
    // Производитель
    // -------------------------------------------------------------------------

    /**
     * Поток-производитель: кладёт числа 1..5 в общий ресурс.
     *
     * <p>Пауза {@code Thread.sleep(500)} имитирует реальную работу по «изготовлению»
     * данных и делает вывод нагляднее.</p>
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
                    Thread.sleep(500); // имитация времени производства
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    // -------------------------------------------------------------------------
    // Потребитель
    // -------------------------------------------------------------------------

    /**
     * Поток-потребитель: читает 5 значений из общего ресурса.
     *
     * <p>Потребитель знает заранее, что ждёт ровно 5 элементов, поэтому
     * цикл ограничен. В реальных системах используют флаг завершения
     * (см. пример 2 с {@code notifyAll}).</p>
     */
    static class Consumer implements Runnable {

        private final SharedResource shared;

        Consumer(SharedResource shared) {
            this.shared = shared;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                shared.consume();
                try {
                    Thread.sleep(700); // имитация времени обработки
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    // -------------------------------------------------------------------------
    // Точка входа
    // -------------------------------------------------------------------------

    /**
     * Запускает производителя и потребителя, ждёт их завершения через {@code join()}.
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== wait / notify: один производитель, один потребитель ===\n");

        SharedResource shared = new SharedResource();

        Thread producer = new Thread(new Producer(shared), "Производитель");
        Thread consumer = new Thread(new Consumer(shared), "Потребитель");

        producer.start();
        consumer.start();

        // Ждём завершения обоих потоков, чтобы main не вышел раньше времени
        producer.join();
        consumer.join();

        System.out.println("\nВсе потоки завершены.");
    }
}
