package pattern1_creation.create5_singleton.code.example7_thread_problem;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Демонстрация проблемы гонки потоков (Race Condition) в ленивом синглтоне.
 *
 * <p>Внутренний класс {@link UnsafeSingleton} намеренно реализован БЕЗ
 * синхронизации и с искусственной задержкой в конструкторе, чтобы расширить
 * «окно гонки» и наглядно показать, что несколько потоков могут создать
 * разные экземпляры.
 *
 * <p><b>Ожидаемый результат:</b> при запуске программа покажет, что было
 * создано более одного экземпляра, а ссылки у разных потоков — разные.
 * Это доказывает, что ленивый синглтон без синхронизации нарушает контракт
 * «ровно один экземпляр» в многопоточной среде.
 */
public class SingletonThreadDemo {

    /**
     * Небезопасный ленивый синглтон — специально для демонстрации проблемы.
     */
    static class UnsafeSingleton {

        /** Счётчик вызовов конструктора — показывает, сколько раз был создан объект. */
        private static final AtomicInteger constructorCallCount = new AtomicInteger(0);

        private static UnsafeSingleton instance;

        /**
         * Конструктор с искусственной задержкой.
         *
         * <p>{@code Thread.sleep(50)} расширяет «окно гонки»: пока один поток
         * спит внутри конструктора, другой успевает пройти проверку
         * {@code Objects.isNull(instance)} и тоже начать создание экземпляра.
         */
        private UnsafeSingleton() {
            int count = constructorCallCount.incrementAndGet();
            System.out.println("  [Конструктор] Вызов #" + count
                    + " из потока: " + Thread.currentThread().getName());
            try {
                // Имитация тяжёлой инициализации (загрузка конфигурации, соединение с БД и т.д.)
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        /**
         * Получение экземпляра БЕЗ синхронизации — содержит гонку потоков!
         *
         * <p>Сценарий гонки:
         * <ol>
         *   <li>Поток A проверяет {@code Objects.isNull(instance)} → true</li>
         *   <li>Поток A входит в конструктор, начинает sleep</li>
         *   <li>Поток B проверяет {@code Objects.isNull(instance)} → всё ещё true!</li>
         *   <li>Поток B тоже входит в конструктор</li>
         *   <li>Оба потока создают СВОЙ экземпляр</li>
         * </ol>
         */
        public static UnsafeSingleton getInstance() {
            if (Objects.isNull(instance)) {
                instance = new UnsafeSingleton();
            }
            return instance;
        }

        /** Сбрасывает состояние для повторного запуска демонстрации. */
        public static void reset() {
            instance = null;
            constructorCallCount.set(0);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Демонстрация проблемы многопоточности в Singleton ===\n");

        final int threadCount = 10;

        // Массив для хранения экземпляров, полученных каждым потоком
        final UnsafeSingleton[] instances = new UnsafeSingleton[threadCount];

        // Создаём потоки — каждый будет запрашивать getInstance()
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            final int index = i;
            threads[i] = new Thread(() -> {
                instances[index] = UnsafeSingleton.getInstance();
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

        System.out.println("\nВсе ссылки одинаковы? " + (allSame ? "Да" : "НЕТ — контракт синглтона нарушен!"));
        System.out.println("Конструктор был вызван раз: " + UnsafeSingleton.constructorCallCount.get());

        if (!allSame) {
            System.out.println("\n⚠ Это и есть проблема гонки потоков (Race Condition)!");
            System.out.println("  Решения: synchronized (пример 3), double-checked locking (пример 4),");
            System.out.println("           Bill Pugh holder (пример 5), enum (пример 6).");
        }
    }
}
