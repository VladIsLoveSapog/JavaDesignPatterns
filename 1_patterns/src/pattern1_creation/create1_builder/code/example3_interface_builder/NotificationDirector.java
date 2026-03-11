package pattern1_creation.create1_builder.code.example3_interface_builder;

/**
 * Director — знает, КАК строить типовые уведомления.
 * <p>
 * Director инкапсулирует алгоритмы сборки (какие поля и в каком порядке заполнять).
 * Он работает ТОЛЬКО с интерфейсом NotificationBuilder, не зная о конкретных классах.
 * <p>
 * Это ключевое преимущество: если завтра появится WhatsAppNotificationBuilder,
 * методы Director заработают с ним без изменений.
 * <p>
 * Director НЕ обязателен в паттерне Builder — это опциональная часть.
 * Его используют, когда нужно переиспользовать логику построения объектов.
 */
public class NotificationDirector {

    /**
     * Строит приветственное уведомление для нового пользователя.
     * Используется при регистрации — всегда NORMAL приоритет, не требует ответа.
     *
     * @param builder          любой Builder: Email, SMS или Push
     * @param recipientAddress адрес/номер/токен получателя
     * @param userName         имя нового пользователя
     * @return готовое уведомление
     */
    public Notification buildWelcomeNotification(NotificationBuilder builder,
                                                 String recipientAddress,
                                                 String userName) {
        return builder
                .recipient(recipientAddress)
                .subject("Добро пожаловать!")
                .body("Привет, " + userName + "! Рады видеть тебя в нашем сервисе. " +
                        "Нажми здесь, чтобы начать знакомство с возможностями.")
                .priority("NORMAL")
                .requiresAck(false)
                .build();
    }

    /**
     * Строит уведомление об угрозе безопасности (подозрительный вход, смена пароля и т.д.).
     * URGENT приоритет, требует подтверждения прочтения.
     *
     * @param builder          любой Builder
     * @param recipientAddress адрес/номер/токен получателя
     * @param securityEvent    описание события безопасности
     * @return готовое уведомление
     */
    public Notification buildSecurityAlert(NotificationBuilder builder,
                                           String recipientAddress,
                                           String securityEvent) {
        return builder
                .recipient(recipientAddress)
                .subject("ВАЖНО: Уведомление безопасности")
                .body("Обнаружено подозрительное действие: " + securityEvent +
                        ". Если это были не вы — немедленно смените пароль и обратитесь в поддержку.")
                .priority("URGENT")
                .requiresAck(true)
                .build();
    }

    /**
     * Строит промо-уведомление.
     * LOW приоритет — не критично, пользователь может прочитать позже.
     *
     * @param builder          любой Builder
     * @param recipientAddress адрес/номер/токен получателя
     * @param promoText        текст рекламного предложения
     * @return готовое уведомление
     */
    public Notification buildPromoNotification(NotificationBuilder builder,
                                               String recipientAddress,
                                               String promoText) {
        return builder
                .recipient(recipientAddress)
                .subject("Специальное предложение для вас")
                .body(promoText)
                .priority("LOW")
                .requiresAck(false)
                .build();
    }
}
