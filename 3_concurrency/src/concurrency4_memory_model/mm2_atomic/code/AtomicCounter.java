package concurrency4_synchronization.syn2_variables.code;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    private static final int NUM_THREADS = 10;
    private static final int INCREMENTS_PER_THREAD = 1000;

    private final AtomicInteger atomicCounter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        AtomicCounter example = new AtomicCounter();
        example.startThreads();
    }

    public void startThreads() throws InterruptedException {
        List<Thread> threads = new ArrayList<>(NUM_THREADS);

        // Создаем и запускаем потоки
        for (int i = 0; i < NUM_THREADS; i++) {
            threads.add(new Thread(new CounterTask()));
            threads.get(i).start();
        }

        // Ждем завершения всех потоков
        for (Thread thread : threads) {
            thread.join();
        }

        // Выводим окончательное значение счетчика
        System.out.println("Окончательное значение счетчика: " + atomicCounter.get());
    }

    private class CounterTask implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < INCREMENTS_PER_THREAD; i++) {
                atomicCounter.incrementAndGet(); // Атомарное увеличение счетчика на 1
            }
        }
    }
}
