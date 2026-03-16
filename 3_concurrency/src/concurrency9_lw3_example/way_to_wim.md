# Клиент - серверное взаимодействие

## Эмуляция в многопоточной среде

Создадим многопоточную программу на языке Java, которая моделирует взаимодействие между несколькими клиентами и
сервером. В этой модели:

1. **Клиенты** отправляют запросы на сервер.
2. **Сервер** принимает эти запросы и помещает их в очередь.
3. **Сервер** последовательно обрабатывает запросы из очереди.

Целью является демонстрация базовых принципов многопоточности, синхронизации и управления ресурсами с использованием
очередей для обмена данными между потоками.

## Главная цель задачи

**Основная цель** — разработать многопоточную систему, в которой несколько клиентов могут одновременно отправлять
запросы, а сервер обрабатывает эти запросы последовательно из общей очереди. Это позволяет:

1. **Эффективно управлять запросами**: Клиенты могут отправлять запросы независимо друг от друга, а сервер обрабатывает
   их по мере поступления
2. **Избежать состояний гонки и конфликтов**: Использование потокобезопасных структур данных (
   например, ```BlockingQueue```) обеспечивает корректное взаимодействие между потоками.
3. **Обеспечить масштабируемость**: Система может обрабатывать любое количество запросов, ограниченное только ресурсами
   системы.

## [Пример](code%2FMain.java)

**Класс** ```Request```: Представляет запрос от клиента.

```java
public class Request {
    private final int requestId;
    private final String clientName;

    public Request(int requestId, String clientName) {
        this.requestId = requestId;
        this.clientName = clientName;
    }

    public int getRequestId() {
        return requestId;
    }

    public String getClientName() {
        return clientName;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestId=" + requestId +
                ", clientName='" + clientName + '\'' +
                '}';
    }
}
```

---

**Класс** ```Server```: Содержит очередь запросов и метод для их обработки.

<удем использовать ```BlockingQueue``` из пакета ```java.util.concurrent``` для управления очередью запросов, что
обеспечивает безопасное добавление и извлечение элементов из разных потоков без необходимости явной синхронизации.

```java
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
```

---

**Класс** ```Client```: Представляет клиента, который отправляет запросы на сервер.

```java
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
```

---

**Класс** ```Main```: Запускает сервер и несколько клиентов для демонстрации работы системы.

```java
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
```

1. **Запуск сервера:**
    - Создаётся экземпляр ```Server``` и запускается в отдельном потоке.
    - Сервер начинает слушать запросы и готов обрабатывать их из очереди
2. **Запуск клиентов:**
    - Создаются несколько экземпляров ```Client```, каждый из которых отправляет определённое количество запросов на
      сервер.
    - Каждый клиент отправляет запросы с небольшими задержками, имитируя реальное поведение пользователей.
3. **Отправка запросов:**
    - Клиенты добавляют запросы в очередь сервера с помощью метода ```addRequest```.
    - Сервер извлекает запросы из очереди и обрабатывает их последовательно, имитируя время обработки.
4. **Завершение работы**:
    - После определённого времени (```Thread.sleep(15000)```) главный поток останавливает сервер, прерывает его поток и
      ожидает завершения.
    - Все потоки завершаются корректно, и программа выводит сообщение о завершении симуляции.

## Пример вывода

```
Сервер запущен и готов к обработке запросов.
Клиент-1 запущен и начинает отправку запросов.
Клиент-1 вошёл в барбершоп.
Клиент-1 сел в кресло для ожидания.
Сервер принял: Request{requestId=1, clientName='Клиент-1'}
Сервер обрабатывает: Request{requestId=1, clientName='Клиент-1'}
Клиент-2 запущен и начинает отправку запросов.
Клиент-2 вошёл в барбершоп.
Клиент-2 сел в кресло для ожидания.
Сервер принял: Request{requestId=1, clientName='Клиент-2'}
Сервер завершил обработку: Request{requestId=1, clientName='Клиент-1'}
Сервер обрабатывает: Request{requestId=1, clientName='Клиент-2'}
Клиент-3 запущен и начинает отправку запросов.
Клиент-3 вошёл в барбершоп.
Клиент-3 сел в кресло для ожидания.
Сервер принял: Request{requestId=1, clientName='Клиент-3'}
Сервер завершил обработку: Request{requestId=1, clientName='Клиент-2'}
Сервер обрабатывает: Request{requestId=1, clientName='Клиент-3'}
Клиент-1 отправляет запрос: Request{requestId=2, clientName='Клиент-1'}
Сервер принял: Request{requestId=2, clientName='Клиент-1'}
Сервер завершил обработку: Request{requestId=1, clientName='Клиент-3'}
Сервер обрабатывает: Request{requestId=2, clientName='Клиент-1'}
...
Клиент-1 завершил отправку запросов.
Клиент-2 завершил отправку запросов.
Клиент-3 завершил отправку запросов.
Сервер остановлен.
Сервер завершил работу.
Симуляция завершена.
```

## Что же делать в ЛР3

Все варианты ЛР3 так или иначе можно свести к имитации клиент серверного взаимодействия.  
**Примеры:**

- Клиенты делают заказы в кофейне, где один бариста постепнно их выполняет.
- Некоторые генераторы запросов создают запросы на создание графов n-размерности.
- и т.д. и т.п.

### **Единсвтенное НО**

В отличии от данного примера ваша программа должно будет завершать работу после обработки всех запланированных запросов,
без таймера, т.е. не как в примере.