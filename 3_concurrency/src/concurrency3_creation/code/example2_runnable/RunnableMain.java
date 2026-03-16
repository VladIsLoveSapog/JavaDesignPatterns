package concurrency3_creation.code.example2_runnable;

public class RunnableMain {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Пример 2: Реализация Runnable ===\n");

        // task != thread: один экземпляр задачи — два разных потока
        MyRunnable task = new MyRunnable("Задача-А");

        Thread t1 = new Thread(task, "Поток-1");
        Thread t2 = new Thread(task, "Поток-2");

        // Оба потока выполняют одну и ту же задачу независимо
        t1.start();
        t2.start();

        System.out.println("\nОба потока завершены.");
    }
}
