package concurrency8_lw3_example.code;

import java.util.Random;

public class Client implements Runnable {
    private final String clientName;
    private final Server server;
    private final int numberOfRequests;
    private final Random random;

    public Client(String clientName, Server server, int numberOfRequests) {
        this.clientName = clientName;
        this.server = server;
        this.numberOfRequests = numberOfRequests;
        this.random = new Random();
    }

    @Override
    public void run() {
        System.out.println(clientName + " запущен и начинает отправку запросов.");
        for (int i = 1; i <= numberOfRequests; i++) {
            Request request = new Request(i, clientName);
            server.addRequest(request);
            try {
                // Имитация времени между отправками запросов
                Thread.sleep(random.nextInt(1000) + 1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(clientName + " прерван при отправке запросов.");
                break;
            }
        }
        System.out.println(clientName + " завершил отправку запросов.");
    }
}
