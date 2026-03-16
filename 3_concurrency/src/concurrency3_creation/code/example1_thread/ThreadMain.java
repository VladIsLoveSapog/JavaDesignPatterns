package concurrency3_creation.code.example1_thread;

public class ThreadMain {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Пример 1: Наследование от Thread ===\n");

        // --- Создание и запуск двух потоков ---
        MyThread t1 = new MyThread("МойПоток-1", Thread.NORM_PRIORITY);
        MyThread t2 = new MyThread("МойПоток-2", Thread.MIN_PRIORITY);

        System.out.println("Состояние до start(): t1=" + t1.getState() + ", t2=" + t2.getState()); // NEW

        t1.start();
        t2.start();

        System.out.println("Состояние после start(): t1=" + t1.getState() + ", t2=" + t2.getState()); // RUNNABLE

        System.out.println("Состояние после join():  t1=" + t1.getState() + ", t2=" + t2.getState()); // TERMINATED

        // --- Демонстрация прерывания ---
        System.out.println("\n--- Демонстрация interrupt() ---");
        MyThread t3 = new MyThread("МойПоток-3", Thread.NORM_PRIORITY);
        t3.start();

        Thread.sleep(500); // даём t3 поработать немного
        t3.interrupt();    // посылаем сигнал прерывания

        System.out.println("Состояние t3 после join(): " + t3.getState()); // TERMINATED
    }
}
