package concurrency3_creation.code.example5_join;

/**
 * Пример {@link Thread#join(long)} с тайм-аутом — ограниченное ожидание завершения потока.
 *
 * <p>{@code join(millis)} блокирует вызывающий поток не дольше указанного времени.
 * После истечения тайм-аута выполнение продолжается независимо от того,
 * завершился ли целевой поток. Проверить это можно методом {@link Thread#isAlive()}.</p>
 *
 * <p>Сценарий: рабочий поток спит 3 секунды, главный ждёт не более 1 секунды.
 * Главный поток продолжает работу раньше, чем рабочий завершится.</p>
 */
public class TimeOut {

    public static void main(String[] args) {
        System.out.println("=== Пример join() с тайм-аутом ===\n");

        Thread thread = new Thread(new LongTask(), "Долгий поток");
        thread.start();

        try {
            System.out.println("[main] Ждём не более 1 секунды...");

            // join(1000) — ждать завершения потока, но не дольше 1000 мс
            thread.join(1000);

            // После возврата из join() проверяем: поток всё ещё жив?
            if (thread.isAlive()) {
                // Тайм-аут истёк раньше, чем поток завершился
                System.out.println("[main] Тайм-аут! Долгий поток всё ещё работает.");
            } else {
                // Поток успел завершиться до истечения тайм-аута
                System.out.println("[main] Долгий поток завершился до истечения тайм-аута.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("[main] Главный поток завершён.");
    }
}

/**
 * Долгая задача: имитирует 3 секунды работы — дольше тайм-аута в {@link TimeOut}.
 */
class LongTask implements Runnable {

    @Override
    public void run() {
        System.out.println("[" + Thread.currentThread().getName() + "] Начало долгой работы (3 сек)...");

        try {
            // 3 000 мс > 1 000 мс тайм-аута в main — поток не успеет завершиться
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("[" + Thread.currentThread().getName() + "] Долгая работа завершена.");
    }
}
