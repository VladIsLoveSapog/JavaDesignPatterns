package concurrency4_synchronization.syn2_variables.code;

class VolatileDataExample {
    private int data;
    private volatile boolean ready = false;

    public void writer() {
        data = 42; // Запись данных
        ready = true; // Установка флага готовности
    }

    public void reader() {
        if (ready) {
            System.out.println("Данные прочитаны: " + data);
        } else {
            System.out.println("Данные еще не готовы.");
        }
    }
}

public class VolatileMain {
    public static void main(String[] args) {
        VolatileDataExample example = new VolatileDataExample();

        Thread writerThread = new Thread(example::writer);
        Thread readerThread = new Thread(example::reader);

        writerThread.start();
        readerThread.start();
    }
}
