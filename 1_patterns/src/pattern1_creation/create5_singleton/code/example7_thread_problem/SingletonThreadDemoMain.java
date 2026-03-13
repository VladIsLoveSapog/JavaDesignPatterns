package pattern1_creation.create5_singleton.code.example7_thread_problem;

public class SingletonThreadDemoMain {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Демонстрация проблемы многопоточности в Singleton ===\n");

        final int threadCount = 10;

        // Массив для хранения экземпляров, полученных каждым потоком
        final SingletonThreadDemo.UnsafeSingleton[] instances =
                new SingletonThreadDemo.UnsafeSingleton[threadCount];

        // Создаём потоки — каждый будет запрашивать getInstance()
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            final int index = i;
            threads[i] = new Thread(() -> {
                instances[index] = SingletonThreadDemo.UnsafeSingleton.getInstance();
            }, "Поток-" + i);
        }

        // Запускаем все потоки как можно ближе по времени
        System.out.println("Запускаем " + threadCount + " потоков одновременно...\n");
        for (Thread t : threads) {
            t.start();
        }

        // Ждём завершения всех потоков
        for (Thread t : threads) {
            t.join();
        }

        // --- Анализ результатов ---
        System.out.println("\n=== Результаты ===\n");

        // Выводим identityHashCode каждого экземпляра
        for (int i = 0; i < threadCount; i++) {
            System.out.println("Поток-" + i + " получил экземпляр: "
                    + System.identityHashCode(instances[i]));
        }

        // Проверяем, все ли ссылки одинаковы
        boolean allSame = true;
        for (int i = 1; i < threadCount; i++) {
            if (instances[i] != instances[0]) {
                allSame = false;
                break;
            }
        }

        System.out.println("\nВсе ссылки одинаковы? "
                + (allSame ? "Да" : "НЕТ — контракт синглтона нарушен!"));
        System.out.println("Конструктор был вызван раз: "
                + SingletonThreadDemo.UnsafeSingleton.constructorCallCount.get());

        if (!allSame) {
            System.out.println("\n⚠ Это и есть проблема гонки потоков (Race Condition)!");
            System.out.println("  Решения: synchronized (пример 3), double-checked locking (пример 4),");
            System.out.println("           Bill Pugh holder (пример 5), enum (пример 6).");
        }
    }
}
