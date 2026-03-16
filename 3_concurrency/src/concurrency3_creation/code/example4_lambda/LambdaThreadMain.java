package concurrency3_creation.code.example4_lambda;

public class LambdaThreadMain {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Пример 4: Лямбда ===\n");

        // Лямбда передаётся напрямую в конструктор Thread (Runnable — @FunctionalInterface)
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println(Thread.currentThread().getName() + ": i = " + i);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    // InterruptedException должен ловиться внутри лямбды —
                    // нельзя пробросить checked exception через Runnable.run()
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }, "Лямбда-Поток-1");

        // Лямбда сохранена в переменной типа Runnable
        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + ": выполняю задачу");
        };
        Thread t2 = new Thread(task, "Лямбда-Поток-2");

        t1.start();
        t2.start();

        System.out.println("Оба лямбда-потока завершены.");
    }
}
