package pattern1_creation.create2_factory_method.code.example4_interface;

/**
 * Демонстрация варианта «фабричный метод через интерфейс создателя».
 *
 * NotificationFactory — интерфейс (не абстрактный класс).
 * Это позволяет конкретным фабрикам наследовать другие классы при необходимости.
 * Интерфейс содержит default-метод sendNotification(), который использует
 * фабричный метод — это аналог логики абстрактного создателя из примера 1.
 */
public class NotificationMain {

    public static void main(String[] args) {
        System.out.println("=== Фабричный метод через интерфейс создателя ===");
        System.out.println();

        // Создаём конкретные фабрики, работаем через интерфейс NotificationFactory
        NotificationFactory emailFactory = new EmailNotificationFactory();
        NotificationFactory smsFactory = new SmsNotificationFactory();

        // Вариант 1: создаём уведомление вручную через фабричный метод
        System.out.println("--- Вариант 1: создание через createNotification() ---");
        Notification email = emailFactory.createNotification();
        email.send("user@example.com", "Ваш заказ подтверждён!");

        System.out.println();

        // Вариант 2: используем default-метод интерфейса — он сам вызывает фабричный метод
        System.out.println("--- Вариант 2: использование default-метода sendNotification() ---");
        smsFactory.sendNotification("+7-999-123-45-67", "Код подтверждения: 1234");

        System.out.println();

        // Смена фабрики без изменения клиентского кода
        System.out.println("--- Подмена фабрики на лету ---");
        NotificationFactory factory = emailFactory;
        factory.sendNotification("admin@example.com", "Важное системное уведомление!");

        // Меняем фабрику — поведение меняется, код не трогаем
        factory = smsFactory;
        factory.sendNotification("+7-800-000-00-00", "Важное системное уведомление!");
    }
}
