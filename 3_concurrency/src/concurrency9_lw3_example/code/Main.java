package concurrency8_lw3_example.code;

public class Main {
    public static void main(String[] args) {
        // Создаем сервер
        Server server = new Server();
        Thread serverThread = new Thread(server, "Сервер");
        serverThread.start();

        // Создаем и запускаем несколько клиентов
        int numberOfClients = 3;
        int requestsPerClient = 5;

        for (int i = 1; i <= numberOfClients; i++) {
            Client client = new Client("Клиент-" + i, server, requestsPerClient);
            Thread clientThread = new Thread(client, "Клиент-" + i + " Thread");
            clientThread.start();
        }

        // Ждем некоторое время для обработки запросов
        try {
            // Время должно быть достаточно для обработки всех запросов
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Главный поток прерван.");
        }

        // Останавливаем сервер
        server.stopServer();
        // Прерываем серверный поток, чтобы он завершился, если ожидает новых запросов
        serverThread.interrupt();

        try {
            serverThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Главный поток прерван при ожидании завершения сервера.");
        }

        System.out.println("Симуляция завершена.");
    }
}
