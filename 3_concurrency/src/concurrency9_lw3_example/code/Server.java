package concurrency8_lw3_example.code;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Server implements Runnable {
    private final BlockingQueue<Request> requestQueue;
    private volatile boolean isRunning;

    public Server() {
        this.requestQueue = new LinkedBlockingQueue<>();
        this.isRunning = true;
    }

    // Метод для добавления запросов в очередь
    public void addRequest(Request request) {
        try {
            requestQueue.put(request);
            System.out.println("Сервер принял: " + request);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Сервер прерван при добавлении запроса.");
        }
    }

    // Метод для остановки сервера
    public void stopServer() {
        isRunning = false;
    }

    @Override
    public void run() {
        System.out.println("Сервер запущен и готов к обработке запросов.");
        while (isRunning || !requestQueue.isEmpty()) {
            try {
                // Извлекаем запрос из очереди, ожидая, если очередь пуста
                Request request = requestQueue.take();
                processRequest(request);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Сервер прерван.");
            }
        }
        System.out.println("Сервер остановлен.");
    }

    // Метод для обработки запроса
    private void processRequest(Request request) {
        System.out.println("Сервер обрабатывает: " + request);
        try {
            // Имитация времени обработки запроса
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Обработка запроса прервана.");
        }
        System.out.println("Сервер завершил обработку: " + request);
    }
}