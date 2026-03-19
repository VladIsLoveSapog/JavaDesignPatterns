package pattern1_creation.create1_builder.code.example3_interface_builder;

/**
 * Демонстрация паттерна Builder с интерфейсом и несколькими реализациями.
 *
 * Фокус: полиморфные Builder-ы. Director работает с интерфейсом NotificationBuilder
 * и не знает, Email это, SMS или Push.
 */
public class NotificationMain {
    public static void main(String[] args) {

        System.out.println("=== Пример 3: Builder через интерфейс — система уведомлений ===\n");

        NotificationDirector director = new NotificationDirector();

        // ─── Сценарий 1: Приветствие по трём каналам через Director ──────────
        System.out.println("── Сценарий 1: Приветствие одним методом Director — три канала ──");
        // Director один и тот же. Меняем только Builder — меняется канал доставки.

        Notification welcomeEmail = director.buildWelcomeNotification(
                new EmailNotificationBuilder(),
                "ivan@example.com",
                "Иван"
        );
        System.out.println("Email:  " + welcomeEmail);

        Notification welcomeSms = director.buildWelcomeNotification(
                new SmsNotificationBuilder(),
                "+79001234567",
                "Иван"
        );
        System.out.println("SMS:    " + welcomeSms);

        Notification welcomePush = director.buildWelcomeNotification(
                new PushNotificationBuilder(),
                "abcdef1234567890abcdef1234567890abcdef12",  // 40-символьный device token
                "Иван"
        );
        System.out.println("Push:   " + welcomePush);
        System.out.println();

        // ─── Сценарий 2: Тревога безопасности ────────────────────────────────
        System.out.println("── Сценарий 2: Уведомление о безопасности (URGENT + requiresAck) ──");
        Notification securityAlert = director.buildSecurityAlert(
                new EmailNotificationBuilder(),
                "admin@company.com",
                "вход с нового IP 185.220.101.45 (Нидерланды)"
        );
        System.out.println(securityAlert);
        System.out.println("Priority: " + securityAlert.getPriority());
        System.out.println("RequiresAck: " + securityAlert.isRequiresAck());
        System.out.println();

        // ─── Сценарий 3: Ручное создание без Director ────────────────────────
        System.out.println("── Сценарий 3: Ручное создание без Director ──");
        // Director не обязателен. Builder можно использовать напрямую.
        Notification customEmail = new EmailNotificationBuilder()
                .recipient("support@example.com")
                .subject("Тикет #12345 обновлён")
                .body("Ваш запрос в техподдержку получен и взят в работу. Ожидайте ответа в течение 24 часов.")
                .priority("HIGH")
                .requiresAck(true)
                .build();
        System.out.println(customEmail);
        System.out.println();

        // ─── Сценарий 4: Демонстрация ограничений SMS (обрезка) ──────────────
        System.out.println("── Сценарий 4: Демонстрация ограничений SMS — обрезка до 160 символов ──");
        String longText = "Уважаемый клиент! Мы рады сообщить вам об эксклюзивном предложении: " +
                "скидка 50% на все товары в нашем интернет-магазине действует только сегодня. " +
                "Успейте воспользоваться предложением до полуночи. Код купона: SAVE50. " +
                "Переходите на наш сайт прямо сейчас!";
        System.out.println("Длина исходного текста: " + longText.length() + " символов");

        SmsNotificationBuilder smsBuilder = new SmsNotificationBuilder();
        Notification smsPromo = smsBuilder
                .recipient("+79009876543")
                .subject("Акция")
                .body(longText)  // ← здесь сработает предупреждение об обрезке
                .build();

        System.out.println("Текст был обрезан: " + smsBuilder.isBodyWasTruncated());
        System.out.println("Длина тела в объекте: " + smsPromo.getBody().length() + " символов");
        System.out.println(smsPromo);
    }
}
