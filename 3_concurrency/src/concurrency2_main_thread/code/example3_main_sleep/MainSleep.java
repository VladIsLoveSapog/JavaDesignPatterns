package concurrency2_main_thread.code.example3_main_sleep;

/**
 * Приостановка главного потока через {@link Thread#sleep(long)}.
 *
 * <p>{@code Thread.sleep(millis)} переводит поток в состояние {@code TIMED_WAITING}
 * на указанное время. Метод гарантирует <em>минимальную</em> паузу, но не точную:
 * реальная задержка может быть длиннее из-за загрузки планировщика ОС.</p>
 *
 * <p>{@link InterruptedException} нужно обрабатывать, потому что другой поток
 * может прервать ожидание вызовом {@code thread.interrupt()}.</p>
 */
public class MainSleep {

    public static void main(String[] args) {
        System.out.println("=== Приостановка главного потока ===\n");

        long pauseMillis = 2000;

        long before = System.currentTimeMillis();
        System.out.println("Время до паузы:  " + before + " мс (epoch)");
        System.out.println("Засыпаем на " + pauseMillis + " мс...");

        try {
            // sleep() гарантирует минимальную паузу, а не точную задержку
            Thread.sleep(pauseMillis);
        } catch (InterruptedException e) {
            // Восстанавливаем флаг прерывания, чтобы вышестоящий код мог его обнаружить
            Thread.currentThread().interrupt();
        }

        long after = System.currentTimeMillis();
        System.out.println("Время после паузы: " + after + " мс (epoch)");
        System.out.println("Фактическая пауза: " + (after - before) + " мс");
    }
}
