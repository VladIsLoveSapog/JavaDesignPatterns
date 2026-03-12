package pattern1_creation.create2_factory_method.code.example4_interface;

/**
 * Интерфейс создателя — фабрика уведомлений.
 *
 * Ключевое отличие от примера 1: создатель — это интерфейс, а не абстрактный класс.
 * Это даёт максимальную гибкость: конкретные фабрики могут наследовать другие классы
 * и при этом реализовывать этот интерфейс (в Java нет множественного наследования классов,
 * но есть множественная реализация интерфейсов).
 *
 * Каждая конкретная фабрика реализует единственный метод — createNotification().
 * Это и есть фабричный метод.
 */
public interface NotificationFactory {

    /**
     * Фабричный метод — создать уведомление.
     *
     * Конкретный тип уведомления (Email, SMS и т.д.) определяется
     * реализующим классом.
     *
     * @return готовое уведомление
     */
    public Notification createNotification();

    /**
     * Вспомогательный метод с реализацией по умолчанию (default method).
     *
     * Интерфейс-создатель тоже может содержать общую логику!
     * С Java 8 интерфейсы поддерживают default-методы.
     *
     * @param recipient адресат
     * @param message   текст
     */
    default public void sendNotification(String recipient, String message) {
        // Используем фабричный метод — не знаем конкретный тип уведомления
        Notification notification = createNotification();
        System.out.println("Фабрика подготовила уведомление, отправляем...");
        notification.send(recipient, message);
    }
}
